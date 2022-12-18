package me.alpha.kitpvp.CustomEvents;

import me.alpha.kitpvp.Objects.ReduxPlayerObject.ReduxPlayer;
import org.bukkit.Material;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

import java.util.List;

import static me.alpha.kitpvp.PitRemake.MysticWell.loreChecker.CheckEnchantOnBow;

public class ReduxBowEvent extends Event implements Cancellable {
    private static final HandlerList HANDLERS = new HandlerList();
    private final ReduxPlayer attacker;
    private final ReduxPlayer defender;
    private final double damage;
    private final EntityDamageByEntityEvent event;
    private boolean isCancelled;

    public static HandlerList getHandlerList() {
        return HANDLERS;
    }

    public ReduxBowEvent(ReduxPlayer attacker, ReduxPlayer defender, double damage, EntityDamageByEntityEvent event) {
        this.attacker = attacker;
        this.defender = defender;
        this.damage = damage;
        this.event = event;
        this.isCancelled = false;
    }


    @Override
    public HandlerList getHandlers() {
        return HANDLERS;
    }

    @Override
    public boolean isCancelled() {
        return this.isCancelled;
    }

    @Override
    public void setCancelled(boolean isCancelled) {
        this.isCancelled = isCancelled;
    }

    public EntityDamageByEntityEvent getBukkitEvent(){
        return this.event;
    }

    public List<String> getEnchants(){
        if(this.attacker.getPlayerObject().getItemInHand() != null && this.attacker.getPlayerObject().getItemInHand().getType().equals(Material.BOW) &&
                this.attacker.getPlayerObject().getItemInHand().getItemMeta() != null && this.attacker.getPlayerObject().getItemInHand().getItemMeta().getLore() != null){
            return CheckEnchantOnBow(this.attacker.getPlayerObject().getItemInHand().getItemMeta().getLore());
        }

        return null;
    }

    public ReduxPlayer getAttacker() {
        return this.attacker;
    }

    public ReduxPlayer getDefender() {
        return this.defender;
    }

    public Double getReduxDamage() {
        return this.damage;
    }
}
