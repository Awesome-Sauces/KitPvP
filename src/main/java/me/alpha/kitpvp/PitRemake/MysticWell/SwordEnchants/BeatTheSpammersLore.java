package me.alpha.kitpvp.PitRemake.MysticWell.SwordEnchants;

import de.tr7zw.nbtapi.NBTItem;
import me.alpha.kitpvp.CustomEvents.ReduxDamageEvent;
import me.alpha.kitpvp.CustomEvents.ReduxDeathEvent;
import me.alpha.kitpvp.PitRemake.MysticWell.EnchantRarity;
import me.alpha.kitpvp.PitRemake.MysticWell.PitEnchant;
import me.alpha.kitpvp.utils.CitizensHelper;
import org.bukkit.potion.PotionEffectType;

import static me.alpha.kitpvp.utils.IntegerHelper.integerToRoman;

public class BeatTheSpammersLore extends PitEnchant {

    @Override
    public void run(ReduxDamageEvent event) {
        if (!CitizensHelper.isNPC(event.getAttacker().getPlayerObject()) &&
                event.getAttacker().getPlayerObject().getItemInHand()!=null){
            NBTItem item = new NBTItem(event.getAttacker().getPlayerObject().getItemInHand());

            if(!item.hasKey("beatthespammer")) return;


        }else if(!CitizensHelper.isNPC(event.getAttacker().getPlayerObject()) &&
                event.getAttacker().getPlayerObject().getItemInHand()!=null) return;
        else if(CitizensHelper.isNPC(event.getAttacker().getPlayerObject())) return;

        NBTItem item = new NBTItem(event.getAttacker().getPlayerObject().getItemInHand());
        int level = item.getInteger("beatthespammer");

        if(event.getDefender().getPlayerObject().getItemInHand()!=null &&
        !event.getDefender().getPlayerObject().getItemInHand().getType().equals(Material.AIR) &&
        event.getDefender().getPlayerObject().getItemInHand().getType().equals(Material.BOW)){
            int damage = level*15;

            event.addReduxDamageMultiplier(damage);
        }
    }

    public void run(ReduxDeathEvent event){
    }

    @Override
    public void init() {
        rarity = EnchantRarity.NORMAL;
    }

    @Override
    public String title(int level) {
        String tier = "";
        if (level > 1){tier += " " + integerToRoman(level);}

        return "&9Beat the Spammers" + tier;
    }

    @Override
    public String lore(int level) {
        String tier = "";
        if (level > 1){tier += " " + integerToRoman(level);}

        String multiplier = String.valueOf(level*15);

        String lore = "&9Beat the Spammers" + tier + "\n" +
                "&7Deal &a"+multiplier+"%&7 more damage\n"
                +"&7to players holding bows"
                + "\n&7";

        return colorCode(lore);
    }
}
