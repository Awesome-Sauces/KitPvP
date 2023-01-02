package me.alpha.kitpvp.events;

import de.tr7zw.nbtapi.NBTItem;
import me.alpha.kitpvp.CustomEvents.ArmorEvents.ArmorEquipEvent;
import me.alpha.kitpvp.CustomEvents.ReduxBowEvent;
import me.alpha.kitpvp.CustomEvents.ReduxDamageEvent;
import me.alpha.kitpvp.CustomEvents.ReduxDeathEvent;
import me.alpha.kitpvp.Data.ClassInstances;
import me.alpha.kitpvp.KitPvP;
import me.alpha.kitpvp.Objects.ReduxPlayerObject.ReduxPlayer;
import me.alpha.kitpvp.PitRemake.ItemStacks.enchants;
import me.alpha.kitpvp.PitRemake.Locations;
import me.alpha.kitpvp.PitRemake.RenownShop.CookieMonster.MonsterHandler;
import me.alpha.kitpvp.utils.CitizensHelper;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.potion.PotionEffectType;

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
        ReduxPlayer defender = event.getDefender();
        ReduxPlayer attacker = event.getAttacker();

        boolean somberAttacker = false;
        boolean somberDefender = false;

        NBTItem attackerPants = null;
        NBTItem defenderPants = null;

        NBTItem defenderBoots = null;
        NBTItem attackerBoots = null;

        if(!CitizensHelper.isNPC(defender) && defender.getLeggings() !=null){
            defenderPants = new NBTItem(defender.getLeggings());
        }

        if(!CitizensHelper.isNPC(attacker) && attacker.getLeggings() !=null){
            attackerPants = new NBTItem(attacker.getLeggings());
        }

        if(!CitizensHelper.isNPC(defender) && defender.getBoots() !=null){
            defenderBoots = new NBTItem(defender.getBoots());
        }

        if(!CitizensHelper.isNPC(attacker) && attacker.getBoots() !=null){
            attackerBoots = new NBTItem(attacker.getBoots());
        }

        if(attackerPants != null && attackerPants.hasKey("somber")) {
            somberAttacker = true;
            somberDefender = true;
        }
        if(defenderPants != null && defenderPants.hasKey("somber")) somberDefender=true;

        if(defenderBoots != null && defenderBoots.hasKey("arma")  && !somberDefender) somberDefender=true;
        if(attackerBoots != null && attackerBoots.hasKey("arma") && !somberAttacker) {
            somberDefender = false;
        }

        if(attacker.getPlayerObject().getItemInHand()!=null &&
                attacker.getPlayerObject().getItemInHand().getType()!=Material.AIR &&
                !somberAttacker && !somberDefender &&
                !attacker.getPlayerObject().hasPotionEffect(PotionEffectType.POISON)){
            ClassInstances.fasterThenTheirShadowLore.bow(event);
            ClassInstances.sprintDrainLore.bow(event);
            ClassInstances.waspLore.bow(event);
            ClassInstances.parasiteLore.bow(event);
        }
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

        if(event.getPlayer().getLocation().getY() >= getSpawnProtection(event.getPlayer().getWorld())){
            event.setCancelled(true);
            return;
        }

        Location playerloc = event.getBlock().getLocation();

        ReduxPlayer reduxPlayer = playerExists(event.getPlayer());


        Block replaced = event.getBlockReplacedState().getBlock();
        if(event.getPlayer().getGameMode().equals(GameMode.CREATIVE)) return;
        if (event.getBlockReplacedState().getType() != null &&
                event.getBlockReplacedState().getType() == Material.AIR &&
                event.getBlockReplacedState().getType() != Material.GRASS){
            if (event.getBlock().getType() == Material.OBSIDIAN) {

                if(event.getBlock().getLocation().distance(Locations.getMidLocation(event.getBlock().getWorld()))<=8){
                    event.setCancelled(true);
                    return;
                }

                if(event.getBlock().getLocation().distance(Locations.getMidLocation(event.getBlock().getWorld()))>=10){
                    event.setCancelled(true);
                    return;
                }

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
