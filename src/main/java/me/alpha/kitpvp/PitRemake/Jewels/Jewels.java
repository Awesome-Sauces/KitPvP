package me.alpha.kitpvp.PitRemake.Jewels;

import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.List;

import static me.alpha.kitpvp.PitRemake.Jewels.pantJewl.generateJewlPants;
import static me.alpha.kitpvp.PitRemake.Jewels.swordJewl.generateJewelSword;
import static me.alpha.kitpvp.PitRemake.MysticWell.loreChecker.CheckEnchantOnPant;

public class Jewels {
    public static HashMap<String, Integer> jewl_pant_counter = new HashMap<>();
    public static HashMap<String, Integer> jewl_sword_counter = new HashMap<>();

    public static boolean Jewel(List<String> lore){
        List<String> list = CheckEnchantOnPant(lore);

        for (String s : list) {
            switch (s) {
                case "jewel":
                    return true;
            }
        }


        return false;
    }

    public static void PlayerFinishedJewl(Player player){
        try {
            if(Jewel(player.getInventory().getLeggings().getItemMeta().getLore())){

                if(!jewl_pant_counter.containsKey(String.valueOf(player.getUniqueId()))){
                    jewl_pant_counter.put(String.valueOf(player.getUniqueId()), 1);
                }else{
                    jewl_pant_counter.put(String.valueOf(player.getUniqueId()), jewl_pant_counter.get(String.valueOf(player.getUniqueId())) + 1);
                }

                if(jewl_pant_counter.get(String.valueOf(player.getUniqueId())) >= 42){
                    player.getInventory().setLeggings(generateJewlPants(player));
                    jewl_pant_counter.put(String.valueOf(player.getUniqueId()), 0);
                }

            }
        } catch (Exception e) {

        }

        try {
            if(Jewel(player.getInventory().getItemInHand().getItemMeta().getLore())){

                if(!jewl_sword_counter.containsKey(String.valueOf(player.getUniqueId()))){
                    jewl_sword_counter.put(String.valueOf(player.getUniqueId()), 1);
                }else{
                    jewl_sword_counter.put(String.valueOf(player.getUniqueId()), jewl_sword_counter.get(String.valueOf(player.getUniqueId())) + 1);
                }

                if(jewl_sword_counter.get(String.valueOf(player.getUniqueId())) >= 42){
                    player.getInventory().setItemInHand(generateJewelSword(player));
                    jewl_sword_counter.put(String.valueOf(player.getUniqueId()), 0);
                }

            }
        } catch (Exception e) {

        }
    }
}