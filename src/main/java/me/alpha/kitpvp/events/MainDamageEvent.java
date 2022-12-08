package me.alpha.kitpvp.events;

import me.alpha.kitpvp.CustomEvents.ReduxDamageEvent;
import me.alpha.kitpvp.KitPvP;
import me.alpha.kitpvp.PitRemake.Locations;
import me.alpha.kitpvp.utils.CitizensHelper;
import org.bukkit.Bukkit;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

import static me.alpha.kitpvp.Objects.ReduxPlayerObject.ReduxPlayerHandler.playerExists;

public class MainDamageEvent implements Listener {

    @EventHandler
    public void HandleDamageEvent(EntityDamageByEntityEvent event){

        if(!(event.getEntity() instanceof Player) ||
        !((event.getDamager()) instanceof Player)){
            event.setCancelled(true);
            return;
        }

        Player attacker = (Player) event.getDamager();
        Player defender = (Player) event.getEntity();

        if(event.getDamager().getLocation().getY() <= Locations.getSpawnProtection()){
            assert defender != null;
            defender.setAllowFlight(false);
            KitPvP.combatTag.put(String.valueOf(event.getDamager().getUniqueId()), System.currentTimeMillis() + (5 * 1000));
            KitPvP.combatTag.put(String.valueOf(event.getEntity().getUniqueId()), System.currentTimeMillis() + (5 * 1000));

            ReduxDamageEvent mainEvent = new ReduxDamageEvent(playerExists(attacker), playerExists(defender), event.getDamage(), event);
            if(mainEvent!=null)Bukkit.getPluginManager().callEvent(mainEvent);
            if (!mainEvent.isCancelled()) {

                mainEvent.run();

                event.setDamage(mainEvent.getReduxDamage());

                if(CitizensHelper.isNPC(defender)){
                    if(((LivingEntity) event.getEntity()).getHealth() - event.getFinalDamage() <= 3){
                        event.setCancelled(true);
                        ((LivingEntity) event.getEntity()).setHealth(((LivingEntity) event.getEntity()).getMaxHealth());
                        //KillMan((Player) event.getDamager(), (Player) event.getEntity());
                        return;
                    }
                }else{
                    if(((LivingEntity) event.getEntity()).getHealth() - event.getFinalDamage() <= 1){
                        event.setCancelled(true);
                        ((LivingEntity) event.getEntity()).setHealth(((LivingEntity) event.getEntity()).getMaxHealth());
                        //KillMan((Player) event.getDamager(), (Player) event.getEntity());
                        return;
                    }
                }


                new TrueDamageHandler(playerExists(attacker), playerExists(defender), mainEvent.getReduxTrueDamage(), event.getFinalDamage()).run();
            }else{
                event.setCancelled(true);
            }
        }else{
            event.setCancelled(true);
        }

    }

}
