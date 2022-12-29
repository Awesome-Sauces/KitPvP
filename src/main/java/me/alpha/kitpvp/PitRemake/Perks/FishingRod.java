package me.alpha.kitpvp.PitRemake.Perks;

import me.alpha.kitpvp.CustomEvents.ReduxDamageEvent;
import me.alpha.kitpvp.CustomEvents.ReduxDeathEvent;
import me.alpha.kitpvp.Data.ClassInstances;
import me.alpha.kitpvp.KitPvP;
import me.alpha.kitpvp.Objects.ReduxPlayerObject.ReduxPlayer;
import org.bukkit.Material;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import static me.alpha.kitpvp.utils.ColorUtil.colorCode;

public class FishingRod extends PitPerk {

    public FishingRod(){
        this.setRefID("fishing");
        this.setMaterial(Material.FISHING_ROD);
        this.setName(colorCode("Fishing Rod"));
        this.setLore(colorCode("&7Spawn with a fishing rod."));
        this.setCost(1000);
        this.setLevel(5);
    }

    @Override
    public PerkExecute getPerkExecute() {
        return new PerkExecute(){
            @Override
            public void run(ReduxDamageEvent event){
                ReduxPlayer player = event.getAttacker();

            }

            @Override
            public void run(ReduxDeathEvent event){
                ReduxPlayer player = event.getAttacker();

                //player.getPlayerObject().removePotionEffect(PotionEffectType.REGENERATION);


                if(player.getPerks().contains(ClassInstances.fishingRod.getRefID())){
                }

            }
        };
    }


}

