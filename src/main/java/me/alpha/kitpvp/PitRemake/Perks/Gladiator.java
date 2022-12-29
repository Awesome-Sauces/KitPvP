package me.alpha.kitpvp.PitRemake.Perks;

import me.alpha.kitpvp.CustomEvents.ReduxDamageEvent;
import me.alpha.kitpvp.CustomEvents.ReduxDeathEvent;
import me.alpha.kitpvp.Data.ClassInstances;
import me.alpha.kitpvp.Objects.ReduxPlayerObject.ReduxPlayer;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

import static me.alpha.kitpvp.utils.ColorUtil.colorCode;

public class Gladiator extends PitPerk {

    public Gladiator(){
        this.setRefID("gladiator");
        this.setMaterial(Material.BONE);
        this.setName(colorCode("Gladiator"));
        this.setLore(colorCode("&7Receive &9-3% &7damage per\n" +
                "&7nearby player.\n\n" +
                "&712 blocks range.\n" +
                "&7Minimum 3, max 10 players."));
        this.setCost(4000);
        this.setLevel(10);
    }

    @Override
    public PerkExecute getPerkExecute() {
        return new PerkExecute(){
            @Override
            public void run(ReduxDamageEvent event){
                ReduxPlayer player = event.getDefender();

                if(player.getPerks().contains(ClassInstances.gladiator.getRefID())){
                    event.subtractReduxDamageMultiplier(getGladiator(player.getPlayerObject(), 3));
                }

            }

            @Override
            public void run(ReduxDeathEvent event){}
        };
    }

    public static double getGladiator(Player player, double multiplier){

        double power = 0;

        for(Entity entity : player.getNearbyEntities(7, 7, 7))
            if(entity instanceof Player) power += multiplier;

        return Math.min(power, multiplier * 10);
    }

}

