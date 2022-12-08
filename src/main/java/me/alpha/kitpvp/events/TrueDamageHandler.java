package me.alpha.kitpvp.events;

import me.alpha.kitpvp.Objects.ReduxPlayerObject.ReduxPlayer;


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

    public void run(){

        if(this.damage <= 0) return;

        if(this.defender.getPlayerObject().getHealth() - (this.damage + this.finalDamage) <= 2) {
            defender.getPlayerObject().setHealth(defender.getPlayerObject().getMaxHealth());
            //defender.killPlayer(attacker.getPlayerObject());
        }
        else this.defender.getPlayerObject().setHealth(Math.max(this.defender.getPlayerObject().getHealth()-damage, 1));

    }
}
