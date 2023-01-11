package me.alpha.kitpvp.events;

import de.tr7zw.nbtapi.NBTItem;
import me.alpha.kitpvp.Data.ClassInstances;
import me.alpha.kitpvp.Objects.ReduxPlayerObject.ReduxPlayer;
import me.alpha.kitpvp.utils.CitizensHelper;
import org.bukkit.Bukkit;

import java.util.Objects;


public class TrueDamageHandler {

    ReduxPlayer attacker;
    ReduxPlayer defender;
    double finalDamage;
    double damage;

    public TrueDamageHandler(ReduxPlayer attacker, ReduxPlayer defender, double damage, double addcalc){
        this.attacker = attacker;
        this.defender = defender;
        this.damage = damage;
        this.finalDamage = addcalc;
    }

    public boolean run(){

        if(!CitizensHelper.isNPC(defender.getPlayerObject()) &&
                ClassInstances.streakData.getStreak(defender.getPlayerUUID())>=50 &&
        ClassInstances.megaStreakData.getMegaStreak(defender.getPlayerUUID()).equals("hermit")){
            damage=0;
        }

        if (!CitizensHelper.isNPC(defender.getPlayerObject()) &&
                defender.getPlayerObject().getInventory().getLeggings()!=null){
            NBTItem item = new NBTItem(defender.getPlayerObject().getInventory().getLeggings());

            if(item.hasKey("mirror") && !Objects.equals(attacker.getPlayerUUID(), defender.getPlayerUUID())){
                damage=0;
            }

        }

        if(this.damage <= 0) return false;

        if(this.defender.getPlayerObject().getHealth() - (this.damage + this.finalDamage) <= 2) {
            //defender.getPlayerObject().setHealth(defender.getPlayerObject().getMaxHealth());
            defender.killPlayer(attacker.getPlayerObject());
            return true;
        }
        else {
            this.defender.getPlayerObject().setHealth(Math.max(this.defender.getPlayerObject().getHealth() - damage, 1));
            defender.getPlayerObject().damage(0);
        }

        return false;
    }
}
