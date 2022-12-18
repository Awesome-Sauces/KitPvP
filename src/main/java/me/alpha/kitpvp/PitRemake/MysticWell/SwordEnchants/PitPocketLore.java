package me.alpha.kitpvp.PitRemake.MysticWell.SwordEnchants;

import de.tr7zw.nbtapi.NBTItem;
import me.alpha.kitpvp.CustomEvents.ReduxDamageEvent;
import me.alpha.kitpvp.KitPvP;
import me.alpha.kitpvp.PitRemake.MysticWell.EnchantRarity;
import me.alpha.kitpvp.PitRemake.MysticWell.PitEnchant;
import me.alpha.kitpvp.utils.CitizensHelper;
import org.bukkit.scheduler.BukkitRunnable;
import static me.alpha.kitpvp.utils.IntegerHelper.integerToRoman;

public class PitPocketLore extends PitEnchant {

    @Override
    public void run(ReduxDamageEvent event) {

        if (!CitizensHelper.isNPC(event.getAttacker().getPlayerObject()) &&
                event.getAttacker().getPlayerObject().getItemInHand()!=null){
            NBTItem item = new NBTItem(event.getAttacker().getPlayerObject().getItemInHand());

            if(!item.hasKey("pitpocket")) return;


        }else if(!CitizensHelper.isNPC(event.getAttacker().getPlayerObject()) &&
                event.getAttacker().getPlayerObject().getItemInHand()!=null) return;
        else if(CitizensHelper.isNPC(event.getAttacker().getPlayerObject())) return;

        NBTItem item = new NBTItem(event.getAttacker().getPlayerObject().getItemInHand());
        int level = item.getInteger("pitpocket");

        if (event.getAttacker().getPitPocketCD()){
            event.getAttacker().setPitPocketCD();

            int gold = 10+(level*5);

            event.getAttacker().getPlayerObject().sendMessage(colorCode("&6&lPITPOCKET! &7you stole &6" + gold + "g &7from &6" + event.getDefenders().getPlayerObject().getDisplayName()));

            event.getAttacker().addPlayerGold(gold);

            new BukkitRunnable() {
                @Override
                public void run() {
                    event.getAttacker().setPitPocketCD();
                }
            }.runTaskLater(KitPvP.INSTANCE, 30000L);
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

        return "&9Pitpocket" + tier;
    }

    @Override
    public String lore(int level) {
        String tier = "";
        if (level > 1){tier += " " + integerToRoman(level);}

        String multiplier = String.valueOf(10+(level*5));

        String lore = "&9Pitpocket" + tier + "\n" +
                "&7Steal &6" + multiplier + "g &7on melee hit (25s\n" +
                "&7cooldown)" + "\n&7";

        return colorCode(lore);
    }
}

