package me.alpha.kitpvp.PitRemake.RenownShop.CookieMonster;

import me.alpha.kitpvp.CustomEvents.ReduxDamageEvent;
import me.alpha.kitpvp.KitPvP;
import me.alpha.kitpvp.PitRemake.ItemStacks.enchants;
import me.alpha.kitpvp.utils.Sounds;
import net.citizensnpcs.api.CitizensAPI;
import net.citizensnpcs.api.npc.NPC;
import net.citizensnpcs.api.trait.trait.Equipment;
import net.citizensnpcs.trait.LookClose;
import net.citizensnpcs.trait.SkinTrait;
import net.minecraft.server.v1_8_R3.PacketPlayOutAnimation;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Effect;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftEntity;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Zombie;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;
import org.bukkit.util.Vector;

import java.util.ArrayList;
import java.util.List;
import static me.alpha.kitpvp.utils.CitizensHelper.getNPC;
import static me.alpha.kitpvp.utils.CitizensHelper.isNPC;
import static me.alpha.kitpvp.utils.ColorUtil.colorCode;

public class MonsterHandler implements Listener {

    public static void percentageSpawn(Player player){/*
        if(Math.random() <= ((double)Monster.getMonsterChance(String.valueOf(player.getUniqueId()))/3000)){
            createMonsterBoss(player);
            player.sendMessage(colorCode("&c&lWOAH! &7a wild &bCookie Monster &7has appeared!"));
            Sounds.PRESTIGE.play(player);
        }
        */
    }

    @EventHandler(priority = EventPriority.HIGH)
    public void MonsterDamageEvent(ReduxDamageEvent event){
        if(isNPC(event.getDefender().getPlayerObject()) &&
                getNPC(event.getDefender().getPlayerObject()).getName().equals(ChatColor.AQUA + "CookieMonster")){
            NPC npc = getNPC(event.getDefender().getPlayerObject());

            event.subtractReduxDamageMultiplier(100);

            event.getDefender().getPlayerObject().setHealth(Math.min(event.getDefender().getPlayerObject().getMaxHealth(),
                    event.getDefender().getPlayerObject().getHealth()+2));

            npc.getEntity().setVelocity(new Vector(0,0,0));

            npc.getEntity().getWorld().playEffect(event.getDefender().getPlayerObject().getLocation(), Effect.HEART, 1);
            npc.getEntity().getWorld().playEffect(event.getDefender().getPlayerObject().getLocation(), Effect.HEART, 1);
            npc.getEntity().getWorld().playEffect(event.getDefender().getPlayerObject().getLocation(), Effect.HEART, 1);
            npc.getEntity().getWorld().playEffect(event.getDefender().getPlayerObject().getLocation(), Effect.HEART, 1);
            npc.getEntity().getWorld().playEffect(event.getDefender().getPlayerObject().getLocation(), Effect.HEART, 1);
        }else if(isNPC(event.getAttacker().getPlayerObject()) &&
        getNPC(event.getAttacker().getPlayerObject()).getName().equals(ChatColor.AQUA + "CookieMonster")){
            NPC npc = getNPC(event.getAttacker().getPlayerObject());

            npc.getEntity().getWorld().strikeLightningEffect(event.getDefender().getPlayerObject().getLocation());
            event.getAttacker().addPotionEffect(PotionEffectType.POISON, 15, 1);
        }
    }

    private static void skin(NPC npc) {
        SkinTrait skinTrait = npc.getTrait(SkinTrait.class);
        skinTrait.setSkinName("HawaiiFox");

        LookClose lookClose = npc.getTrait(LookClose.class);
        lookClose.lookClose(true);
    }

    public static void createMonsterBoss(Player player){
        NPC npc = CitizensAPI.getNPCRegistry().createNPC(EntityType.PLAYER, ChatColor.AQUA + "CookieMonster");
        npc.getOrAddTrait(Equipment.class).set(Equipment.EquipmentSlot.HAND, enchants.malding_sword);

        npc.setBukkitEntityType(EntityType.PLAYER);

        skin(npc);

        npc.spawn(player.getLocation());

        npc.teleport(player.getLocation(), PlayerTeleportEvent.TeleportCause.COMMAND);

        npc.setProtected(false);

        npc.getNavigator().getDefaultParameters()
                .speedModifier(1);

        npc.getNavigator().setTarget(player, false);

        MonsterData.addMonsterInstance(npc, player);

        long timeInTicks = 5L;

        BukkitTask runnable = new BukkitRunnable() {

            @Override
            public void run() {

                if(npc.isSpawned()){
                    npc.getNavigator().setTarget(player.getLocation());
                    npc.faceLocation(player.getLocation());

                    if (getNearby(npc.getEntity(), 3.5, 3.5, 3.5).contains(player)) {
                        if(player.isOnGround() || player.isFlying()){
                            for (Player players : getNearby(npc.getEntity(), 4, 4, 4)){
                                PacketPlayOutAnimation animationPacket = new PacketPlayOutAnimation(((CraftEntity) npc.getEntity()).getHandle(), 0);
                                ((CraftPlayer) players).getHandle().playerConnection.sendPacket(animationPacket);
                            }

                            player.damage(MonsterData.getNPCDamage(), npc.getEntity()); // Use your event's data
                        }

                    }
                }else this.cancel();



            }
        }.runTaskTimer(KitPvP.INSTANCE, timeInTicks, timeInTicks);

        Bukkit.getScheduler().scheduleSyncDelayedTask(KitPvP.INSTANCE, new Runnable() {
            @Override
            public void run() {
                if(MonsterData.isOwner(npc, player)){

                    int renown = MonsterData.getRenownRollLoss(player.getUniqueId());
                    MonsterData.removeMonsterInstance(npc);
                    npc.despawn();
                    npc.destroy();
                    CitizensAPI.getNPCRegistry().deregister(npc);
                    player.sendMessage(colorCode("&c&lFAILURE! &7you failed to kill the cookie monster and lost &b" + renown + " &7renown."));
                    Sounds.DEATH_GHAST_SCREAM.play(player);
                }
            }
        }, 600L);
    }

    public static void handleMonsterDeath(Player player, NPC npc){

        Player attacker = player;



        if(MonsterData.isOwner(npc, attacker)){

            int renown = MonsterData.getRenownRoll(attacker.getUniqueId());
            MonsterData.removeMonsterInstance(npc);
            npc.despawn();
            npc.destroy();
            CitizensAPI.getNPCRegistry().deregister(npc);
            attacker.sendMessage(colorCode("&a&lCONGRATS! &7you killed the cookie monster and got &b" + renown + " &7renown."));
            Sounds.DEATH_GHAST_SCREAM.play(attacker);
        }

    }

    public static List<Player> getNearby(Entity hunter, double x, double y, double z){

        List<Player> playerList = new ArrayList<Player>();

        for (org.bukkit.entity.Entity player : hunter.getNearbyEntities(x, y, z)){

            if (player instanceof Player){
                if(!isNPC((Player) player)){
                    playerList.add((Player) player);
                }

            }
        }

        return playerList;
    }

    public static List<Entity> getNearbyEntity(Entity hunter, double x, double y, double z){

        List<Entity> playerList = new ArrayList<Entity>();

        for (org.bukkit.entity.Entity player : hunter.getNearbyEntities(x, y, z)){

            if(player instanceof Zombie){
                playerList.add(player);
            }else if (player instanceof Player){
                if(!isNPC((Player) player)){
                    playerList.add(player);
                }

            }
        }

        return playerList;
    }

}
