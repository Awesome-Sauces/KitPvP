package me.alpha.kitpvp.PitRemake.MysticWell.PantEnchants;

import de.tr7zw.nbtapi.NBTItem;
import me.alpha.kitpvp.CustomEvents.ReduxDamageEvent;
import me.alpha.kitpvp.KitPvP;
import me.alpha.kitpvp.PitRemake.MysticWell.EnchantRarity;
import me.alpha.kitpvp.PitRemake.MysticWell.PitEnchant;
import me.alpha.kitpvp.events.TrueDamageHandler;
import me.alpha.kitpvp.utils.CitizensHelper;
import org.bukkit.scheduler.BukkitRunnable;

import static me.alpha.kitpvp.utils.IntegerHelper.integerToRoman;

public class PrickLore extends PitEnchant {

    @Override
    public void run(ReduxDamageEvent event) {

        if (!CitizensHelper.isNPC(event.getDefenders().getPlayerObject()) &&
                event.getDefenders().getPlayerObject().getInventory().getLeggings()!=null){
            NBTItem item = new NBTItem(event.getDefenders().getPlayerObject().getInventory().getLeggings());

            if(!item.hasKey("prick")) return;


        }else if(!CitizensHelper.isNPC(event.getDefenders().getPlayerObject()) &&
                event.getDefenders().getPlayerObject().getInventory().getLeggings()==null) return;
        else if(CitizensHelper.isNPC(event.getDefenders().getPlayerObject())) return;

        NBTItem item = new NBTItem(event.getDefenders().getPlayerObject().getInventory().getLeggings());
        int level = item.getInteger("prick");

        double damage = (double) ((5+(level*15)))*2;

        if (event.getDefenders().getPrickCD()){
            event.getDefenders().setPrickCD();
            new TrueDamageHandler(event.getDefenders(), event.getAttacker(), (damage/100), 0).run();
            new BukkitRunnable() {
                @Override
                public void run() {
                    event.getDefenders().setPrickCD();
                }
            }.runTaskLater(KitPvP.INSTANCE, 11L);
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

        return "&9Prick" + tier;
    }

    @Override
    public String lore(int level) {
        String tier = "";
        if (level > 1){tier += " " + integerToRoman(level);}

        double multiplier = (double) ((5+(level*15)));

        String lore = "&9Prick" + tier + "\n" +
                "&7Enemies hitting you receive\n&c"+(multiplier/100)+"\u2764 &7true damage" + "\n&7";

        return colorCode(lore);
    }
}

