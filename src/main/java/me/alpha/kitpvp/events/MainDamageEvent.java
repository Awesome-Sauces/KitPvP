package me.alpha.kitpvp.events;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class MainDamageEvent implements Listener {

    @EventHandler
    public void HandleDeathEvent(EntityDamageByEntityEvent event){
        if(!(event.getDamager() instanceof Player) &&
                !(event.getEntity() instanceof Player)) return;

        Player attacker = (Player) event.getDamager();

        Player defender = (Player) event.getEntity();

        if((defender.getHealth()/2)-event.getDamage()<=2){
            event.setCancelled(true);

            defender.setHealth(defender.getMaxHealth());

            Bukkit.broadcastMessage(defender.getDisplayName() + " was killed by " + attacker.getDisplayName());
        }

    }

}
