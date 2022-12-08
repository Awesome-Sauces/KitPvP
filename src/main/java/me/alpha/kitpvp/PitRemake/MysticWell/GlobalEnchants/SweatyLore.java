package me.alpha.kitpvp.PitRemake.MysticWell.GlobalEnchants;

import com.alpha.redux.DeathHandler.ReduxDeathEvent;
import com.alpha.redux.entityHandlers.ReduxPlayer;
import com.alpha.redux.eventManagers.ReduxDamageEvent;
import com.alpha.redux.well.enchants.EnchantRarity;
import com.alpha.redux.well.enchants.PitEnchant;

import java.util.ArrayList;
import java.util.List;

import static com.alpha.redux.events.boards.integerToRoman;

public class SweatyLore extends PitEnchant {
    @Override
    public void run(ReduxDamageEvent event, int level) {

    }

    public void run(ReduxDeathEvent event){

        List<String> penchants = new ArrayList<>();
        List<String> senchants = new ArrayList<>();

        ReduxPlayer player = event.getAttacker();

        if(player.getPantEnchants() != null) penchants = player.getPantEnchants();
        if(player.getSwordEnchants() != null) senchants = player.getSwordEnchants();

        String PANT_SWEATY = "";
        String SWORD_SWEATY = "";

        for(String ench : penchants){
            if(ench.contains("sweaty")){
                PANT_SWEATY = ench;
            }
        }

        for(String ench : senchants){
            if(ench.contains("sweaty")){
                SWORD_SWEATY = ench;
            }
        }

        if(PANT_SWEATY.contains("sweaty")){
            int level = PANT_SWEATY.length() - PANT_SWEATY.replaceAll("I", "").length();

            double xp = (15*level)+1;

            event.setXp_cap(event.getXp_cap()+(25*(level+1)));
            event.addXp(Math.round(event.getXp()*(xp/100)));
        }

        if(SWORD_SWEATY.contains("sweaty")){
            int level = SWORD_SWEATY.length() - SWORD_SWEATY.replaceAll("I", "").length();

            double xp = (15*level)+1;

            event.setXp_cap(event.getXp_cap()+(25*(level+1)));
            event.addXp(Math.round(event.getXp()*(xp/100)));
        }
    }

    @Override
    public void init() {
        EnchantRarity rarity = EnchantRarity.NORMAL;
    }

    @Override
    public String title(int level) {
        String tier = "";
        if (level > 1){tier += " " + integerToRoman(level);}

        return "&9Sweaty" + tier;
    }

    @Override
    public String lore(int level) {
        String tier = "";
        if (level > 1){tier += " " + integerToRoman(level);}

        String multiplier = String.valueOf(15*level);

        String max = String.valueOf(25*(level+1));

        String lore = "&9Sweaty" + tier + "\n" +
                "&7Earn &b+" + multiplier + "% XP&7 from streak XP\n" +
                "&bbonus and &b+" + max + " max XP&7 per kill" + "\n&7";

        return colorCode(lore);
    }
}
