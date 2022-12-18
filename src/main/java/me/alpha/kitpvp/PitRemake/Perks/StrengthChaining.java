package me.alpha.kitpvp.PitRemake.Perks;


import me.alpha.kitpvp.CustomEvents.ReduxDamageEvent;
import me.alpha.kitpvp.CustomEvents.ReduxDeathEvent;
import me.alpha.kitpvp.Data.ClassInstances;
import me.alpha.kitpvp.Objects.ReduxPlayerObject.ReduxPlayer;
import org.bukkit.Material;
import static me.alpha.kitpvp.utils.ColorUtil.colorCode;

public class StrengthChaining extends PitPerk {

    public StrengthChaining(){
        this.setRefID("strength");
        this.setMaterial(Material.REDSTONE);
        this.setName(colorCode("Strength-Chaining"));
        this.setLore(colorCode("&c+8% damage &7for 7s stacking\n" +
                "&7on kill."));
        this.setCost(4000);
        this.setLevel(25);
    }

    @Override
    public PerkExecute getPerkExecute() {
        return new PerkExecute(){
            @Override
            public void run(ReduxDamageEvent event){
                ReduxPlayer player = event.getAttacker();


                if(player.getPerks().contains(ClassInstances.strengthChaining.getRefID())){
                    if(player.getStrength()<=0) return;
                    event.addReduxDamageMultiplier(player.getStrength()*100);
                }



            }

            @Override
            public void run(ReduxDeathEvent event){
                ReduxPlayer player = event.getAttacker();

                if(player.getPerks().contains(ClassInstances.strengthChaining.getRefID())){
                    player.strengthTick();
                }

            }
        };
    }


}

