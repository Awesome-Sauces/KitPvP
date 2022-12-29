package me.alpha.kitpvp.PitRemake.MysticWell.PantEnchants;

import de.tr7zw.nbtapi.NBTItem;
import me.alpha.kitpvp.CustomEvents.ReduxDamageEvent;
import me.alpha.kitpvp.PitRemake.MysticWell.EnchantRarity;
import me.alpha.kitpvp.PitRemake.MysticWell.PitEnchant;
import me.alpha.kitpvp.utils.CitizensHelper;

import static me.alpha.kitpvp.utils.IntegerHelper.integerToRoman;

public class MirrorLore extends PitEnchant {

    @Override
    public void run(ReduxDamageEvent event) {

        if (!CitizensHelper.isNPC(event.getDefender().getPlayerObject()) &&
                event.getDefender().getPlayerObject().getInventory().getLeggings()!=null){
            NBTItem item = new NBTItem(event.getDefender().getPlayerObject().getInventory().getLeggings());

            if(!item.hasKey("mirror")) return;


        }else if(!CitizensHelper.isNPC(event.getDefender().getPlayerObject()) &&
                event.getDefender().getPlayerObject().getInventory().getLeggings()==null) return;
        else if(CitizensHelper.isNPC(event.getDefender().getPlayerObject())) return;

        NBTItem item = new NBTItem(event.getDefender().getPlayerObject().getInventory().getLeggings());
        int level = item.getInteger("mirror");

        event.setReduxTrueDamage(0);
    }

    @Override
    public void init() {
        rarity = EnchantRarity.NORMAL;
    }

    @Override
    public String title(int level) {
        String tier = "";
        if (level > 1){tier += " " + integerToRoman(level);}

        return "&9Mirror" + tier;
    }

    @Override
    public String lore(int level) {
        String tier = "";
        if (level > 1){tier += " " + integerToRoman(level);}

        String lore = "&9Mirror" + tier + "\n" +
                "&7You are immune to true damage" + "\n&7";

        return colorCode(lore);
    }
}