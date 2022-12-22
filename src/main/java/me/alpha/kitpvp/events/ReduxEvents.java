package me.alpha.kitpvp.events;

import me.alpha.kitpvp.CustomEvents.ArmorEvents.ArmorEquipEvent;
import me.alpha.kitpvp.CustomEvents.ReduxBowEvent;
import me.alpha.kitpvp.CustomEvents.ReduxDamageEvent;
import me.alpha.kitpvp.CustomEvents.ReduxDeathEvent;
import me.alpha.kitpvp.Data.ClassInstances;
import me.alpha.kitpvp.KitPvP;
import me.alpha.kitpvp.Objects.ReduxPlayerObject.ReduxPlayer;
import me.alpha.kitpvp.PitRemake.ItemStacks.enchants;
import me.alpha.kitpvp.PitRemake.RenownShop.CookieMonster.MonsterHandler;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;

import java.util.Random;
import static me.alpha.kitpvp.Objects.ReduxPlayerObject.ReduxPlayerHandler.playerExists;
import static me.alpha.kitpvp.PitRemake.Locations.getSpawnProtection;
import static me.alpha.kitpvp.utils.CitizensHelper.isNPC;

public class ReduxEvents implements Listener {
    @EventHandler
    public static void InterceptEntityDamage(ReduxDamageEvent event){
        ClassInstances.xpDragon.doPetAbility(event);
    }

    @EventHandler
    public void onEquips(ArmorEquipEvent event){
        ClassInstances.cricketLore.run(event);
        ClassInstances.gottaGoFastLore.run(event);
    }

    @EventHandler
    public void bowHitEvent(ReduxBowEvent event){
        ClassInstances.fasterThenTheirShadowLore.bow(event);
        ClassInstances.sprintDrainLore.bow(event);
        ClassInstances.waspLore.bow(event);
        ClassInstances.parasiteLore.bow(event);
    }

    @EventHandler
    public static void DeathEventHandler(ReduxDeathEvent event){

        ClassInstances.xpDragon.doPetAbility(event);

        MonsterHandler.percentageSpawn(event.getAttacker().getPlayerObject());

        ReduxPlayer ReduxAttacker = event.getAttacker();

        if(!isNPC(ReduxAttacker.getPlayerObject())){
            double r = new Random().nextDouble();
            if(ReduxAttacker.getPlayerObject().getInventory().getItemInHand().equals(enchants.reaper_scythe)){
                if (r < 0.5) {
                    ReduxAttacker.getPlayerObject().sendMessage(ChatColor.translateAlternateColorCodes('&', "&a&lWOW! &7you found a &3Player Soul&7!"));
                    ReduxAttacker.getPlayerObject().getInventory().addItem(enchants.playerSoul);
                }
            }else if (r < 0.005) {
                ReduxAttacker.getPlayerObject().sendMessage(ChatColor.translateAlternateColorCodes('&', "&a&lWOW! &7you found a &3Player Soul&7!"));
                ReduxAttacker.getPlayerObject().getInventory().addItem(enchants.playerSoul);
            }
        }


    }

    @EventHandler
    public void onPlace(BlockPlaceEvent event) {

        if(!event.getBlock().getType().equals(Material.OBSIDIAN) && !event.getPlayer().getGameMode().equals(GameMode.CREATIVE)){
            event.setCancelled(true);
            return;
        }

        if(event.getBlock().getType().equals(Material.CAKE_BLOCK) || event.getBlock().getType().equals(Material.CAKE)){
            event.setCancelled(true);
            return;
        }

        if(event.getPlayer().getGameMode().equals(GameMode.CREATIVE)) return;

        if(event.getPlayer().getLocation().getY() >= getSpawnProtection()-13){
            event.setCancelled(true);
            return;
        }

        Location playerloc = event.getBlock().getLocation();

        ReduxPlayer reduxPlayer = playerExists(event.getPlayer());


        Block replaced = event.getBlockReplacedState().getBlock();
        if(event.getPlayer().getGameMode().equals(GameMode.CREATIVE)) return;
        if (event.getBlockReplacedState().getType() != null && event.getBlockReplacedState().getType() == Material.AIR && event.getBlockReplacedState().getType() != Material.GRASS){
            if (event.getBlock().getType() == Material.OBSIDIAN) {

                Bukkit.getScheduler().scheduleSyncDelayedTask(KitPvP.INSTANCE, new Runnable() {

                    @Override
                    public void run() {

                        //Bukkit.broadcastMessage(String.valueOf(replaced));

                        //if(replaced != null) event.getBlock().setType(replaced.getType());
                        //else
                        event.getBlock().setType(Material.AIR);

                    }
                }, /**/reduxPlayer.getObsidianTime());


            }
        }else{
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onPlace(BlockBreakEvent event) {if(event.getPlayer().getGameMode().equals(GameMode.SURVIVAL)) event.setCancelled(true);}



}
