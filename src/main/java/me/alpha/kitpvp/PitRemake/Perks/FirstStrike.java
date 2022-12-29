package me.alpha.kitpvp.PitRemake.Perks;

import me.alpha.kitpvp.CustomEvents.ReduxDamageEvent;
import me.alpha.kitpvp.CustomEvents.ReduxDeathEvent;
import me.alpha.kitpvp.Data.ClassInstances;
import me.alpha.kitpvp.KitPvP;
import me.alpha.kitpvp.Objects.ReduxPlayerObject.ReduxPlayer;
import me.alpha.kitpvp.utils.CitizensHelper;
import org.bukkit.Material;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import static me.alpha.kitpvp.utils.ColorUtil.colorCode;

public class FirstStrike extends PitPerk {

    public FirstStrike(){
        this.setRefID("firstStrike");
        this.setMaterial(Material.COOKED_CHICKEN);
        this.setName(colorCode("First Strike"));
        this.setLore(colorCode("&7First hit on a player deals\n" +
                "&c+35% damage &7and grants\n" +
                "&eSpeed I &7(5s)."));
        this.setCost(8000);
        this.setLevel(60);
    }

    @Override
    public PerkExecute getPerkExecute() {
        return new PerkExecute(){
            @Override
            public void run(ReduxDamageEvent event){
                ReduxPlayer player = event.getAttacker();



                if(player.getPerks().contains(ClassInstances.firstStrike.getRefID()) &&
                !CitizensHelper.isNPC(player)){
                    //player.getPlayerObject().sendMessage("Vampire Worked");

                    if(CitizensHelper.isNPC(event.getDefender())){
                        event.addReduxDamageMultiplier(35);
                    }

                }

            }

            @Override
            public void run(ReduxDeathEvent event){
                ReduxPlayer player = event.getAttacker();

                if(player.getPerks().contains(ClassInstances.firstStrike.getRefID())){

                }

            }
        };
    }


}

