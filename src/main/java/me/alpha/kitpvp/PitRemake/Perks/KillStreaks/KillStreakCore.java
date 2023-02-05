package me.alpha.kitpvp.PitRemake.Perks.KillStreaks;

import me.alpha.kitpvp.CustomEvents.ReduxDamageEvent;
import me.alpha.kitpvp.CustomEvents.ReduxDeathEvent;
import me.alpha.kitpvp.Data.ClassInstances;
import me.alpha.kitpvp.Data.GoldData;
import me.alpha.kitpvp.Data.GoldRequirementData;
import me.alpha.kitpvp.Objects.ReduxPlayerObject.ReduxPlayer;
import me.alpha.kitpvp.PitRemake.ItemStacks.enchants;
import org.bukkit.Material;
import org.bukkit.potion.PotionEffectType;

import java.util.ArrayList;
import java.util.List;

public class KillStreakCore {
    
    public static void runPerks(ReduxDeathEvent event){
        String perkOne = ClassInstances.killStreakPerkOne.getPerk(event.getAttacker().getPlayerUUID());
        String perkTwo = ClassInstances.killStreakPerkTwo.getPerk(event.getAttacker().getPlayerUUID());

        ReduxPlayer player = event.getAttacker();
        
        // 3 Kills
        if(ClassInstances.streakData.getStreak(event.getAttacker().getPlayerUUID()) % 3 == 0) {
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

            // 5 Kills
            if (perkOne.equals("toughSkin") ||
                    perkTwo.equals("toughSkin")) {
                player.setToughSkinStack(Math.min(24, player.getToughSkinStack()+3));
            }

            if (perkOne.equals("fof") ||
                    perkTwo.equals("fof")) {

            }

            if (perkOne.equals("pungent") ||
                    perkTwo.equals("pungent")) {
            }

            if (perkOne.equals("heroHaste") ||
                    perkTwo.equals("heroHaste")) {
            }

            if (perkOne.equals("rush") ||
                    perkTwo.equals("rush")) {
            }
        }


        // 7 Kills
        if(ClassInstances.streakData.getStreak(event.getAttacker().getPlayerUUID()) % 7 == 0) {
            if (perkOne.equals("feast") ||
                    perkTwo.equals("feast")) {
            }

            if (perkOne.equals("csgo") ||
                    perkTwo.equals("csgo")) {
            }

            if (perkOne.equals("nanoFactory") ||
                    perkTwo.equals("nanoFactory")) {
            }

            if (perkOne.equals("tactRetreat") ||
                    perkTwo.equals("tactRetreat")) {
            }

            if (perkOne.equals("pickaxe") ||
                    perkTwo.equals("pickaxe")) {
            }

            if (perkOne.equals("strike") ||
                    perkTwo.equals("strike")) {
            }
        }

        // 10 Kills
        if(ClassInstances.streakData.getStreak(event.getAttacker().getPlayerUUID()) % 10 == 0) {
            if (perkOne.equals("aura") ||
                    perkTwo.equals("aura")) {
            }

            if (perkOne.equals("iceCube") ||
                    perkTwo.equals("iceCube")) {
            }

            if (perkOne.equals("superStreaker") ||
                    perkTwo.equals("superStreaker")) {
            }
        }

        // 25 Kills
        if(ClassInstances.streakData.getStreak(event.getAttacker().getPlayerUUID()) % 25 == 0) {
            if (perkOne.equals("monster") ||
                    perkTwo.equals("monster")) {
            }

            if (perkOne.equals("steve") ||
                    perkTwo.equals("steve")) {
            }
        }
    }
    
    public static void runPerks(ReduxDamageEvent event){
       
    }
}
