package me.alpha.kitpvp.PitRemake.MysticWell.PantEnchants;

import me.alpha.kitpvp.CustomEvents.ArmorEvents.ArmorEquipEvent;
import me.alpha.kitpvp.CustomEvents.ReduxDamageEvent;
import me.alpha.kitpvp.Objects.ReduxPlayerObject.ReduxPlayer;
import me.alpha.kitpvp.PitRemake.MysticWell.EnchantRarity;
import me.alpha.kitpvp.PitRemake.MysticWell.PitEnchant;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import static me.alpha.kitpvp.Objects.ReduxPlayerObject.ReduxPlayerHandler.playerExists;
import static me.alpha.kitpvp.PitRemake.MysticWell.loreChecker.CheckEnchantOnPant;
import static me.alpha.kitpvp.utils.CitizensHelper.isNPC;
import static me.alpha.kitpvp.utils.IntegerHelper.integerToRoman;

public class GottaGoFastLore extends PitEnchant {
    @Override
    public void run(ReduxDamageEvent event) {

    }

    public void run(ArmorEquipEvent event){
        if(isNPC(event.getPlayer())) return;
        if(event.getNewArmorPiece() == null) {
            playerExists(event.getPlayer()).setSpeed(0);
            return;
        }
        if(event.getOldArmorPiece() != null &&
                event.getOldArmorPiece().getType().equals(Material.LEATHER_LEGGINGS) &&
                !isNPC(event.getPlayer())){
            ItemStack leggings = event.getOldArmorPiece();

            boolean CRICKET = false;
            String ENCHANT = "";

            if(leggings.getItemMeta() != null &&
                    leggings.getItemMeta().getLore() != null){
                for(String enchant : CheckEnchantOnPant(leggings.getItemMeta().getLore())) {
                    if (enchant.contains("gottagofast")) {
                        ENCHANT = enchant;
                        CRICKET = true;
                        break;
                    }
                }
            }

            if(CRICKET){
                playerExists(event.getPlayer()).setSpeed(0);
            }

            return;
        }
        if(!event.getNewArmorPiece().getType().equals(Material.LEATHER_LEGGINGS)) return;


        ReduxPlayer player = playerExists(event.getPlayer());
        ItemStack leggings = event.getNewArmorPiece();

        boolean CRICKET = false;
        String ENCHANT = "";

        if(leggings != null &&
                leggings.getItemMeta() != null &&
                leggings.getItemMeta().getLore() != null){
            for(String enchant : CheckEnchantOnPant(leggings.getItemMeta().getLore())) {
                if (enchant.contains("gottagofast")) {
                    CRICKET = true;
                    ENCHANT = enchant;
                    break;
                }
            }
        }

        if(CRICKET){
            int level = ENCHANT.length() - ENCHANT.replaceAll("I", "").length();
            float speed = 5+(level*7);
            player.setSpeed((speed/100));
        }

    }

    @Override
    public void init() {
        rarity = EnchantRarity.NORMAL;
    }

    @Override
    public String title(int level) {
        String tier = "";
        if (level > 1){tier += " " + integerToRoman(level);}

        return "&9Gotta go fast" + tier;
    }

    // T1 = 5 T2 = 7 T3 = 15

    @Override
    public String lore(int level) {
        String tier = "";
        if (level > 1){tier += " " + integerToRoman(level);}

        String multiplier = String.valueOf(5+(level*5));

        String lore = "&9Gotta go fast" + tier + "\n" +
                "&7Move &e" + multiplier  + "% faster &7at all times" + "\n&7";

        return colorCode(lore);
    }
}

