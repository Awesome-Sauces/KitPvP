package me.alpha.kitpvp.PitRemake.MysticWell.PantEnchants;

import de.tr7zw.nbtapi.NBTItem;
import me.alpha.kitpvp.CustomEvents.ReduxDamageEvent;
import me.alpha.kitpvp.PitRemake.Bounties.Bounty;
import me.alpha.kitpvp.PitRemake.MysticWell.EnchantRarity;
import me.alpha.kitpvp.PitRemake.MysticWell.PitEnchant;
import me.alpha.kitpvp.utils.CitizensHelper;

import java.text.DecimalFormat;
import static me.alpha.kitpvp.utils.IntegerHelper.integerToRoman;

public class SelfCheckoutLore extends PitEnchant {

    @Override
    public void run(ReduxDamageEvent event) {
        if (!CitizensHelper.isNPC(event.getDefender().getPlayerObject()) &&
                event.getDefender().getPlayerObject().getInventory().getLeggings()!=null){
            NBTItem item = new NBTItem(event.getDefender().getPlayerObject().getInventory().getLeggings());

            if(!item.hasKey("self-checkout")) return;


        }else if(!CitizensHelper.isNPC(event.getDefender().getPlayerObject()) &&
                event.getDefender().getPlayerObject().getInventory().getLeggings()==null) return;
        else if(CitizensHelper.isNPC(event.getDefender().getPlayerObject())) return;

        NBTItem item = new NBTItem(event.getDefender().getPlayerObject().getInventory().getLeggings());
        int level = item.getInteger("self-checkout");

        if(!Bounty.BountiesMap.containsKey(event.getDefender().getPlayerUUID())) return;
        double bounty = Bounty.BountiesMap.get(event.getDefender().getPlayerUUID());

        if(bounty < 5000) return;

        double amount = ((level-1)*1000L) + (level* 1000L);
        DecimalFormat formatter = new DecimalFormat("#,###");

        event.getDefender().getPlayerObject().sendMessage(colorCode("&6&lSELF-CHECKOUT! &7you cashed in your bounty for &6" + formatter.format(amount) + "g"));

        event.getDefender().addPlayerGold((int) amount);

        Bounty.BountiesMap.put(event.getDefender().getPlayerUUID(), 0);

    }

    @Override
    public void init() {
        rarity = EnchantRarity.NORMAL;
    }

    @Override
    public String title(int level) {
        String tier = "";
        if (level > 1){tier += " " + integerToRoman(level);}

        return "&9Self-checkout" + tier;
    }

    @Override
    public String lore(int level) {
        String tier = "";
        if (level > 1){tier += " " + integerToRoman(level);}
        DecimalFormat formatter = new DecimalFormat("#,###");
        String multiplier = formatter.format(((level-1)*1000L) + (level* 1000L));

        String lore = "&9Self-checkout" + tier + "\n" +
                "&7Upon reaching a &65,000g\n" +
                "&7bounty, clear it and gain\n" +
                "&6+"+multiplier+"g&7. Consumes 1 life of\n" +
                "&7this item" + "\n&7";

        return colorCode(lore);
    }
}

