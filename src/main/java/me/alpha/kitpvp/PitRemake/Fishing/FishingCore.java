package me.alpha.kitpvp.PitRemake.Fishing;

import me.alpha.kitpvp.CustomEvents.ReduxDamageEvent;
import me.alpha.kitpvp.CustomEvents.ReduxDeathEvent;
import me.alpha.kitpvp.PitRemake.ItemStacks.itemManager;
import me.alpha.kitpvp.PitRemake.PitCommands.Stash.StashCore;
import me.alpha.kitpvp.utils.CitizensHelper;
import me.alpha.kitpvp.utils.ColorUtil;
import net.citizensnpcs.api.CitizensAPI;
import net.citizensnpcs.api.npc.NPC;
import net.citizensnpcs.trait.LookClose;
import net.citizensnpcs.trait.SkinTrait;
import org.bukkit.ChatColor;
import org.bukkit.Effect;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerTeleportEvent;

public class FishingCore implements Listener {
    public static String monsterName = ColorUtil.colorCode("&b&lSea Monster &7[&4DEADLY&7]");

    @EventHandler
    public void handleBossDeaths(ReduxDeathEvent event){
        if(CitizensHelper.isNPC(event.getDefender()) &&
                CitizensHelper.getNPC(event.getDefender().getPlayerObject()).getName().equals(monsterName)){
            NPC npc = CitizensHelper.getNPC(event.getDefender().getPlayerObject());

            npc.despawn();
            CitizensAPI.getNPCRegistry().deregister(npc);

            event.getAttacker().getPlayerObject().sendMessage(ColorUtil.colorCode("&b&lSEA MONSTER &7- &4&lDEAD!"));

            StashCore.safeGiveMultiple(event.getAttacker().getPlayerObject(), itemManager.feather, 3);
        }
    }

    @EventHandler
    public void handleBossDamage(ReduxDamageEvent event){
        if(CitizensHelper.isNPC(event.getDefender()) &&
        CitizensHelper.getNPC(event.getDefender().getPlayerObject()).getName().equals(monsterName)){
            event.subtractReduxDamageMultiplier(300);

            NPC npc = CitizensHelper.getNPC(event.getDefender().getPlayerObject());

            npc.getEntity().getWorld().playEffect(event.getDefender().getPlayerObject().getLocation(), Effect.HEART, 1);
            npc.getEntity().getWorld().playEffect(event.getDefender().getPlayerObject().getLocation(), Effect.HEART, 1);
            npc.getEntity().getWorld().playEffect(event.getDefender().getPlayerObject().getLocation(), Effect.HEART, 1);
            npc.getEntity().getWorld().playEffect(event.getDefender().getPlayerObject().getLocation(), Effect.HEART, 1);
            npc.getEntity().getWorld().playEffect(event.getDefender().getPlayerObject().getLocation(), Effect.HEART, 1);

            event.getDefender().getPlayerObject().setHealth(Math.min(event.getDefender().getPlayerObject().getMaxHealth(),
                    event.getDefender().getPlayerObject().getHealth()+2));

        }

        if(CitizensHelper.isNPC(event.getAttacker()) &&
                CitizensHelper.getNPC(event.getAttacker().getPlayerObject()).getName().equals(monsterName)){
            event.addReduxDamageMultiplier(200);

            NPC npc = CitizensHelper.getNPC(event.getAttacker().getPlayerObject());

            npc.getEntity().getWorld().playEffect(event.getDefender().getPlayerObject().getLocation(), Effect.EXPLOSION_LARGE, 20, 20);


        }
    }

    public static void spawnSeaMonster(Player player){
        NPC npc = CitizensAPI.getNPCRegistry().createNPC(EntityType.PLAYER, monsterName);

        SkinTrait skinTrait = npc.getTrait(SkinTrait.class);
        skinTrait.setSkinName("BDIsTaken");

        npc.setProtected(false);

        npc.spawn(player.getLocation());
        npc.teleport(player.getLocation(), PlayerTeleportEvent.TeleportCause.PLUGIN);

        npc.getNavigator().setTarget(player, true);
    }
}
