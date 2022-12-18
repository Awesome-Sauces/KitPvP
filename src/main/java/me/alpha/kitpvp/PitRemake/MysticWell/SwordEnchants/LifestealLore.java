package me.alpha.kitpvp.PitRemake.MysticWell.SwordEnchants;

import de.tr7zw.nbtapi.NBTItem;
import me.alpha.kitpvp.CustomEvents.ReduxDamageEvent;
import me.alpha.kitpvp.PitRemake.MysticWell.EnchantRarity;
import me.alpha.kitpvp.PitRemake.MysticWell.PitEnchant;
import me.alpha.kitpvp.utils.CitizensHelper;

import static me.alpha.kitpvp.utils.IntegerHelper.integerToRoman;

public class LifestealLore extends PitEnchant {

    @Override
    public void run(ReduxDamageEvent event) {
        if (!CitizensHelper.isNPC(event.getAttacker().getPlayerObject()) &&
                event.getAttacker().getPlayerObject().getItemInHand()!=null){
            NBTItem item = new NBTItem(event.getAttacker().getPlayerObject().getItemInHand());

            if(!item.hasKey("lifesteal")) return;


        }else if(!CitizensHelper.isNPC(event.getAttacker().getPlayerObject()) &&
                event.getAttacker().getPlayerObject().getItemInHand()!=null) return;
        else if(CitizensHelper.isNPC(event.getAttacker().getPlayerObject())) return;

        NBTItem item = new NBTItem(event.getAttacker().getPlayerObject().getItemInHand());
        int level = item.getInteger("lifesteal");

        double multiplier = 0;

        if (level > 2) {
            multiplier += (level*4) + 1;
        }else {multiplier += level*4;}

        double playerHealth = event.getAttacker().getPlayerObject().getHealth();
        double maxHealth = event.getAttacker().getPlayerObject().getMaxHealth();

        double healAmount = Math.min(event.getReduxDamage()*(multiplier/100), 3);

        event.getAttacker().getPlayerObject().setHealth(Math.min(maxHealth,
                playerHealth+healAmount));
    }

    @Override
    public void init() {
        rarity = EnchantRarity.NORMAL;
    }

    @Override
    public String title(int level) {
        String tier = "";
        if (level > 1){tier += " " + integerToRoman(level);}

        return "&9Lifesteal" + tier;
    }

    @Override
    public String lore(int level) {
        String tier = "";
        if (level > 1){tier += " " + integerToRoman(level);}

        String multiplier = "";

        if (level > 2) {
            multiplier += String.valueOf((level*4) + 1);
        }else {multiplier += String.valueOf(level*4);}

        String lore = "&9Lifesteal" + tier + "\n" +
                "&7Heal for &c" + multiplier + "%&7 of damage dealt up" +
                "\n&7to &c1.5\u2764" + "\n&7";

        return colorCode(lore);
    }
}
