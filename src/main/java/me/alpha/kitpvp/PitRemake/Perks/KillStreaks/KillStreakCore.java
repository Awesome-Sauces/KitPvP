package me.alpha.kitpvp.PitRemake.Perks.KillStreaks;

import me.alpha.kitpvp.CustomEvents.ReduxDamageEvent;
import me.alpha.kitpvp.CustomEvents.ReduxDeathEvent;
import me.alpha.kitpvp.Data.ClassInstances;
import me.alpha.kitpvp.Data.GoldData;
import me.alpha.kitpvp.Data.GoldRequirementData;
import me.alpha.kitpvp.KitPvP;
import me.alpha.kitpvp.Objects.ReduxPlayerObject.ReduxPlayer;
import me.alpha.kitpvp.PitRemake.ItemStacks.enchants;
import me.alpha.kitpvp.utils.CitizensHelper;
import me.alpha.kitpvp.utils.Sounds;
import net.minecraft.server.v1_8_R3.EntityHuman;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.ArrayList;
import java.util.List;

import static me.alpha.kitpvp.utils.ColorUtil.colorCode;

public class KillStreakCore implements Listener {

    @EventHandler
    public static void runPerks(ReduxDeathEvent event){


        String perkOne = ClassInstances.killStreakPerkOne.getPerk(event.getAttacker().getPlayerUUID());
        String perkTwo = ClassInstances.killStreakPerkTwo.getPerk(event.getAttacker().getPlayerUUID());

        String perkOneDefender = ClassInstances.killStreakPerkOne.getPerk(event.getDefender().getPlayerUUID());
        String perkTwoDefender = ClassInstances.killStreakPerkTwo.getPerk(event.getDefender().getPlayerUUID());

        ReduxPlayer player = event.getAttacker();


        // 3 Kills
        if(!CitizensHelper.isNPC(player) &&ClassInstances.streakData.getStreak(event.getAttacker().getPlayerUUID()) % 2 == 0) {

            if (perkOne.equals("gapple") ||
                    perkTwo.equals("gapple")) {
                player.addPlayerEXP(5);
                player.addPlayerGold(5);

                ClassInstances.goldRequirementData.addGoldReq(player.getPlayerUUID(), 5);

                if(player.getPerks().contains(ClassInstances.goldenHeads.getRefID())){
                    if(player.getPlayerObject().getInventory().contains(enchants.goldenhead.getType(), 2)) return;
                    player.getPlayerObject().getInventory().addItem(enchants.goldenhead);
                }
            }

            if (perkOne.equals("exp") ||
                    perkTwo.equals("exp")) {
                player.addPlayerEXP(12);
            }

            if (perkOne.equals("rr") ||
                    perkTwo.equals("rr")) {
                player.addPotionEffect(PotionEffectType.REGENERATION, 3, 2);
                player.addPotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 3, 1);
            }

            if (perkOne.equals("khante") ||
                    perkTwo.equals("khante")) {
                player.setKhanteStack(Math.min(40, player.getKhanteStack()+4));
            }

            if (perkOne.equals("leech")) {
                player.setLeechAbility();
            }

        }

        // 5 kills
        if(!CitizensHelper.isNPC(player) && ClassInstances.streakData.getStreak(event.getAttacker().getPlayerUUID()) % 4 == 0){


            if (perkOne.equals("toughSkin") ||
                    perkTwo.equals("toughSkin")) {
                player.setToughSkinStack(Math.min(24, player.getToughSkinStack()+3));
            }

            if (perkOne.equals("fof") ||
                    perkTwo.equals("fof")) {
                if(player.getPlayerObject().getHealth()>=(player.getPlayerObject().getMaxHealth()/2)){
                    player.setFightOrFlight();
                    Bukkit.getScheduler().scheduleSyncDelayedTask(KitPvP.INSTANCE, new Runnable() {
                        @Override
                        public void run() {
                            player.setFightOrFlight();
                        }
                    }, 140);
                }else{
                    player.addPotionEffect(PotionEffectType.SPEED, 7, 1);
                    player.addPotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 7, 1);
                }
            }

            if (perkOne.equals("pungent") ||
                    perkTwo.equals("pungent")) {
                for(Entity entity : player.getPlayerObject().getNearbyEntities(3, 3, 3)){
                    if(entity instanceof Player && !CitizensHelper.isNPC(entity)){
                        ((Player) entity).addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 100, 0, true, true));
                    }
                }
            }

            if (perkOne.equals("heroHaste") ||
                    perkTwo.equals("heroHaste")) {
                player.addPotionEffect(PotionEffectType.SPEED, 5, 2);
            }

            if (perkOne.equals("rush") ||
                    perkTwo.equals("rush")) {
                player.addPotionEffect(PotionEffectType.SPEED, 7, 2);
            }
        }


        // 7 Kills
        if(!CitizensHelper.isNPC(player) && ClassInstances.streakData.getStreak(event.getAttacker().getPlayerUUID()) % 6 == 0) {
            if (perkOne.equals("feast") ||
                    perkTwo.equals("feast")) {
                player.setFeastSteak();
                player.addPotionEffect(PotionEffectType.SPEED, 4, 1);
                player.addPotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 4, 1);
                Bukkit.getScheduler().scheduleSyncDelayedTask(KitPvP.INSTANCE, new Runnable() {
                    @Override
                    public void run() {
                        player.setFeastSteak();
                    }
                }, 80);
            }

            if (perkOne.equals("csgo") ||
                    perkTwo.equals("csgo")) {
                player.setCounterStrike();
                Bukkit.getScheduler().scheduleSyncDelayedTask(KitPvP.INSTANCE, new Runnable() {
                    @Override
                    public void run() {
                        player.setCounterStrike();
                    }
                }, 140);
            }

            if (perkOne.equals("nanoFactory") ||
                    perkTwo.equals("nanoFactory")) {
                player.addPlayerGold(1500);
                player.addPotionEffect(PotionEffectType.REGENERATION, 2, 4);
            }

            if (perkOne.equals("tactRetreat") ||
                    perkTwo.equals("tactRetreat")) {
                player.addPotionEffect(PotionEffectType.REGENERATION, 5, 4);
                player.addPotionEffect(PotionEffectType.WEAKNESS, 5, 4);
            }

            if (perkOne.equals("pickaxe") ||
                    perkTwo.equals("pickaxe")) {
                player.getPlayerObject().sendMessage(colorCode("&c&lERROR! &7This killstreak has been disabled!"));
                Sounds.ERROR.play(player.getPlayerObject());
            }

            if (perkOne.equals("strike") ||
                    perkTwo.equals("strike")) {
            }
        }

        // 10 Kills
        if(!CitizensHelper.isNPC(player) && ClassInstances.streakData.getStreak(event.getAttacker().getPlayerUUID()) % 9 == 0) {
            if (perkOne.equals("aura") ||
                    perkTwo.equals("aura")) {
            }

            if (perkOne.equals("iceCube") ||
                    perkTwo.equals("iceCube")) {
                player.getPlayerObject().sendMessage(colorCode("&c&lERROR! &7This killstreak has been disabled!"));
                Sounds.ERROR.play(player.getPlayerObject());
            }

            if (perkOne.equals("superStreaker") ||
                    perkTwo.equals("superStreaker")) {
                event.addBaseXp(50);

                player.setSuperStreaker(Math.min(50, player.getSuperStreaker()+5));
            }
        }



        if (perkOne.equals("superStreaker") ||
                perkTwo.equals("superStreaker")) {
            event.addXpIncrease(player.getSuperStreaker());
        }

        if (perkOneDefender.equals("superStreaker") ||
                perkTwoDefender.equals("superStreaker")) {
            event.getDefender().setSuperStreaker(0);
        }

        // 25 Kills
        if(!CitizensHelper.isNPC(player) && ClassInstances.streakData.getStreak(event.getAttacker().getPlayerUUID()) % 24 == 0) {
            if (perkOne.equals("monster") ||
                    perkTwo.equals("monster")) {
                if(ClassInstances.extraHearts.hasValue(player.getPlayerUUID()) &&
                        ClassInstances.extraHearts.getInt(player.getPlayerUUID(), 0) >=1){
                    int maximum = ((ClassInstances.extraHearts.getInt(player.getPlayerUUID(), 0 )*2)+4)+20;

                    player.getPlayerObject().setMaxHealth(Math.min(maximum, player.getPlayerObject().getMaxHealth()+2));
                }else{
                    int maximum = 4+20;

                    player.getPlayerObject().setMaxHealth(Math.min(maximum, player.getPlayerObject().getMaxHealth()+2));
                }
            }

            if (perkOne.equals("steve") ||
                    perkTwo.equals("steve")) {
                CraftPlayer craftAttacker = (CraftPlayer) player.getPlayerObject(); //CraftBukkit
                EntityHuman entityAttacker = craftAttacker.getHandle(); //NMS

                //EntityHuman entityDefender = craftDefender.getHandle(); //NMS
                double abs = entityAttacker.getAbsorptionHearts();
                entityAttacker.setAbsorptionHearts((float) Math.min(abs + 50, 50));
            }
        }
    }

    @EventHandler
    public static void runPerks(ReduxDamageEvent event){
       if(!CitizensHelper.isNPC(event.getAttacker())) {
           event.getAttacker().doAssuredStrike(event);
           event.getAttacker().doCounterStrike(event);
           event.getAttacker().doFeastSteak(event);
           event.getAttacker().doKhanteAbility(event);
           event.getAttacker().doFightOrFlight(event);
           event.getAttacker().doToughSkinAbility(event);
           event.getAttacker().doLeechAbility(event);
       }

       if(!CitizensHelper.isNPC(event.getDefender())){
           event.getDefender().doAssuredStrike(event);
           event.getDefender().doCounterStrike(event);
           event.getDefender().doFeastSteak(event);
           event.getDefender().doKhanteAbility(event);
           event.getDefender().doFightOrFlight(event);
           event.getDefender().doToughSkinAbility(event);
           event.getDefender().doLeechAbility(event);
       }

    }
}
