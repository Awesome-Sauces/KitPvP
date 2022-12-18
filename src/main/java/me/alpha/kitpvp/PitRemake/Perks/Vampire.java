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

public class Vampire extends PitPerk {

    public Vampire(){
        this.setRefID("vampire");
        this.setMaterial(Material.FERMENTED_SPIDER_EYE);
        this.setName(colorCode("Vampire"));
        this.setLore(colorCode("&7Don't earn golden apples.\n" +
                "&7Heal &c0.5\u2764 &7 on hit.\n" +
                "&7Tripled on arrow crit.\n" +
                "&cRegen I &7(8s) on kill."));
        this.setCost(4000);
        this.setLevel(60);
    }

    @Override
    public PerkExecute getPerkExecute() {
        return new PerkExecute(){
            @Override
            public void run(ReduxDamageEvent event){
                ReduxPlayer player = event.getAttacker();



                if(player.getPerks().contains(ClassInstances.vampire.getRefID())){
                    //player.getPlayerObject().sendMessage("Vampire Worked");
                    if (player.getVampireCD()){
                        player.setVampireCD();
                        new BukkitRunnable() {
                            @Override
                            public void run() {
                                player.setVampireCD();
                            }
                        }.runTaskLater(KitPvP.INSTANCE, 3L);
                        player.getPlayerObject().setHealth(Math.min(player.getPlayerObject().getHealth()+1, player.getPlayerObject().getMaxHealth()));
                    }
                }

            }

            @Override
            public void run(ReduxDeathEvent event){
                ReduxPlayer player = event.getAttacker();

                    //player.getPlayerObject().removePotionEffect(PotionEffectType.REGENERATION);


                if(player.getPerks().contains(ClassInstances.vampire.getRefID())){
                    player.getPlayerObject().addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 8*20, 0, true, true));
                }

            }
        };
    }


}

