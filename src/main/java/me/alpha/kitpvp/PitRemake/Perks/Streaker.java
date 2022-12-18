package me.alpha.kitpvp.PitRemake.Perks;

import me.alpha.kitpvp.CustomEvents.ReduxDamageEvent;
import me.alpha.kitpvp.CustomEvents.ReduxDeathEvent;
import me.alpha.kitpvp.Data.ClassInstances;
import me.alpha.kitpvp.Objects.ReduxPlayerObject.ReduxPlayer;
import org.bukkit.Material;
import static me.alpha.kitpvp.utils.ColorUtil.colorCode;

public class Streaker extends PitPerk {

    public Streaker(){
        this.setRefID("streaker");
        this.setMaterial(Material.WHEAT);
        this.setName(colorCode("Streaker"));
        this.setLore(colorCode("&7Triple streak kill &bbase XP &7bonus."));
        this.setCost(8000);
        this.setLevel(50);
    }

    @Override
    public PerkExecute getPerkExecute() {
        return new PerkExecute(){
            @Override
            public void run(ReduxDamageEvent event){

            }

            @Override
            public void run(ReduxDeathEvent event){
                ReduxPlayer player = event.getAttacker();

                if(player.getPerks().contains(ClassInstances.streaker.getRefID())){
                    event.addXp(event.getXp()*3);
                }

            }
        };
    }


}
