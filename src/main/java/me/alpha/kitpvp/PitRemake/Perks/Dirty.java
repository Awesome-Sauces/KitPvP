package me.alpha.kitpvp.PitRemake.Perks;


import me.alpha.kitpvp.CustomEvents.ReduxDamageEvent;
import me.alpha.kitpvp.CustomEvents.ReduxDeathEvent;
import me.alpha.kitpvp.Data.ClassInstances;
import me.alpha.kitpvp.Objects.ReduxPlayerObject.ReduxPlayer;
import org.bukkit.Material;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import static me.alpha.kitpvp.utils.ColorUtil.colorCode;

public class Dirty extends PitPerk {

    public Dirty(){
        this.setRefID("dirty");
        this.setMaterial(Material.DIRT);
        this.setName(colorCode("Dirty"));
        this.setLore(colorCode("&7Gain &9Resistance II &7(4s) on\n" +
                "&7kill."));
        this.setCost(8000);
        this.setLevel(25);
    }

    @Override
    public PerkExecute getPerkExecute() {
        return new PerkExecute(){
            @Override
            public void run(ReduxDamageEvent event){}

            @Override
            public void run(ReduxDeathEvent event){
                ReduxPlayer player = event.getAttacker();

                if(player.getPerks().contains(ClassInstances.dirty.getRefID())){
                    if(!player.getPlayerObject().hasPotionEffect(PotionEffectType.DAMAGE_RESISTANCE)){
                        player.getPlayerObject().removePotionEffect(PotionEffectType.DAMAGE_RESISTANCE);
                        player.getPlayerObject().addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 4*20, 1, true, true));
                    }
                }


            }
        };
    }


}

