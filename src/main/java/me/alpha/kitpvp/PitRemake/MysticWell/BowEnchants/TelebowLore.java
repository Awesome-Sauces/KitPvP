package me.alpha.kitpvp.PitRemake.MysticWell.BowEnchants;

import de.tr7zw.nbtapi.NBTItem;
import me.alpha.kitpvp.CustomEvents.ReduxBowEvent;
import me.alpha.kitpvp.CustomEvents.ReduxDamageEvent;
import me.alpha.kitpvp.Data.ClassInstances;
import me.alpha.kitpvp.KitPvP;
import me.alpha.kitpvp.Objects.ReduxPlayerObject.ReduxPlayer;
import me.alpha.kitpvp.PitRemake.MysticWell.EnchantRarity;
import me.alpha.kitpvp.PitRemake.MysticWell.PitEnchant;
import me.alpha.kitpvp.utils.CitizensHelper;
import me.alpha.kitpvp.utils.ColorUtil;
import me.alpha.kitpvp.utils.Sounds;
import org.bukkit.Bukkit;
import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.scheduler.BukkitRunnable;

import static me.alpha.kitpvp.Objects.ReduxPlayerObject.ReduxPlayerHandler.playerExists;
import static me.alpha.kitpvp.utils.IntegerHelper.integerToRoman;

public class TelebowLore extends PitEnchant {

    @Override
    public void run(ReduxDamageEvent event) {
    }

    public void bow(ReduxBowEvent event, int level){

    }

    public void run(EntityShootBowEvent event){

        if(!(event.getEntity()instanceof Player)) return;

        Arrow arrow = (Arrow) event.getProjectile();
        Player shooter = (Player) event.getEntity();
        ReduxPlayer player = playerExists(shooter);

        if (!CitizensHelper.isNPC(player.getPlayerObject()) &&
                player.getPlayerObject().getItemInHand()!=null &&
                player.getPlayerObject().getItemInHand().getType()!= Material.AIR){
            NBTItem item = new NBTItem(player.getPlayerObject().getItemInHand());

            if(!item.hasKey("telebow")) return;

            int level = item.getInteger("telebow");

            double multiplier = Math.round((double) (90/level)/2);

            if(shooter.isSneaking()) {

                if(!getCooldown(playerExists(shooter), multiplier)){
                    Sounds.NO.play(shooter);
                    shooter.sendMessage(ColorUtil.colorCode("&c&lERROR! &7Telebow is still on cooldown!"));
                    return;
                }

                ClassInstances.ArrowStore.put(player.getPlayerObject().getUniqueId(), arrow);

                new BukkitRunnable() {
                    @Override
                    public void run() {
                        if(!ClassInstances.ArrowStore.containsKey(player.getPlayerObject().getUniqueId())) return;

                        Arrow temp_arrow = ClassInstances.ArrowStore.get(player.getPlayerObject().getUniqueId());

                        if(temp_arrow==null||temp_arrow.isOnGround()||temp_arrow.isDead()) this.cancel();
                        else for(int j = 0; j < 10; j++) temp_arrow.getWorld().playEffect(temp_arrow.getLocation(), Effect.POTION_SWIRL, 0, 30);
                    }
                }.runTaskTimer(KitPvP.INSTANCE, 0L, 1L);
            }

        }
    }

    public void runIt(ProjectileHitEvent event){

        if(!(event.getEntity().getShooter()instanceof Player)) return;

        Arrow arrow = (Arrow) event.getEntity();
        Player shooter = (Player) event.getEntity().getShooter();
        ReduxPlayer player = playerExists(shooter);

        if(!ClassInstances.ArrowStore.containsKey(player.getPlayerObject().getUniqueId())) return;
        if(!ClassInstances.ArrowStore.get(player.getPlayerObject().getUniqueId()).getUniqueId().equals(arrow.getUniqueId())) return;

        Location teleportLoc = arrow.getLocation().clone();
        teleportLoc.setYaw(-arrow.getLocation().getYaw());
        teleportLoc.setPitch(-arrow.getLocation().getPitch());

        player.getPlayerObject().teleport(teleportLoc);

        Sounds.TELEBOW.play(player.getPlayerObject());
        ClassInstances.ArrowStore.remove(player.getPlayerObject().getUniqueId());
    }

    private boolean getCooldown(ReduxPlayer owner, double multiplier){
        if (owner.getTelebowCD()){
            owner.setTelebowCD();
            new BukkitRunnable() {
                @Override
                public void run() {
                    owner.setTelebowCD();
                }
            }.runTaskLater(KitPvP.INSTANCE, (long) (multiplier*20));
            return true;
        }

        return false;
    }

    @Override
    public void init() {
        rarity = EnchantRarity.RARE;
    }

    @Override
    public String title(int level) {
        String tier = "";
        if (level > 1){tier += " " + integerToRoman(level);}

        return "&dRARE! &9Telebow" + tier;
    }

    @Override
    public String lore(int level) {
        String tier = "";
        if (level > 1){tier += " " + integerToRoman(level);}

        String multiplier = String.valueOf((90/level)/2);

        String lore = "&dRARE! &9Telebow" + tier + "\n" +
                "&7Sneak to shoot a teleportation\n" +
                "&7arrow ("+multiplier+"s cooldown)" + "\n&7";

        return colorCode(lore);
    }
}
