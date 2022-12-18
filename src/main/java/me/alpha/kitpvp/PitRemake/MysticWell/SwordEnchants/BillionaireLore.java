package me.alpha.kitpvp.PitRemake.MysticWell.SwordEnchants;

import de.tr7zw.nbtapi.NBTItem;
import me.alpha.kitpvp.CustomEvents.ReduxDamageEvent;
import me.alpha.kitpvp.PitRemake.MysticWell.EnchantRarity;
import me.alpha.kitpvp.PitRemake.MysticWell.PitEnchant;
import me.alpha.kitpvp.utils.CitizensHelper;
import me.alpha.kitpvp.utils.Sounds;
import org.bukkit.Bukkit;

import static me.alpha.kitpvp.utils.IntegerHelper.integerToRoman;

public class BillionaireLore extends PitEnchant {

    @Override
    public void run(ReduxDamageEvent event) {

        if (!CitizensHelper.isNPC(event.getAttacker().getPlayerObject()) &&
        event.getAttacker().getPlayerObject().getItemInHand()!=null){
            NBTItem item = new NBTItem(event.getAttacker().getPlayerObject().getItemInHand());

            if(!item.hasKey("billionaire")) return;
            
            
        }else if(!CitizensHelper.isNPC(event.getAttacker().getPlayerObject()) &&
                event.getAttacker().getPlayerObject().getItemInHand()!=null) return;
        else if(CitizensHelper.isNPC(event.getAttacker().getPlayerObject())) return;

        NBTItem item = new NBTItem(event.getAttacker().getPlayerObject().getItemInHand());
        int level = item.getInteger("billionaire");

        
        int gold = 0;

        if (level > 2) {
            gold += (level*100)+50;
        }else{gold+=level*100;}

        double multiplier = 0;

        if (level > 1) {
            multiplier += Math.round(level*33)+1;
        }else {multiplier += (level*33);}

        multiplier+=1;

        if (event.getAttacker().getPlayerGold() >= gold){
            event.getAttacker().setPlayerGold((int) (event.getAttacker().getPlayerGold() - gold));
            event.getAttacker().refreshScoreBoard();
            Sounds.BILLIONAIRE.play(event.getAttacker().getPlayerObject());
            event.addReduxDamageMultiplier(multiplier);
        }
    }

    @Override
    public void init() {
        rarity = EnchantRarity.RARE;
    }

    @Override
    public String title(int level) {
        String tier = "";
        if (level > 1){tier += " " + integerToRoman(level);}

        return "&dRARE! &9Billionaire" + tier;
    }

    @Override
    public String lore(int level) {

        String tier = "";
        if (level > 1){tier += " " + integerToRoman(level);}

        String multiplier = "";

        if (level > 1) {
            multiplier += String.valueOf((level*.33)+1.01);
        }else {multiplier += String.valueOf((level*.33)+1);}

        String gold = "";

        if (level > 2) {
            gold += String.valueOf((level*100)+50);
        }else{gold+=String.valueOf(level*100);}

        String lore = "&dRARE! &9Billionaire" + tier + "\n" +
                        "&7Hits with this sword deal &c" + multiplier + "x\n" +
                        "&cdamage&7 but cost &6" + gold + "g" + "\n&7";

        return colorCode(lore);
    }
}
