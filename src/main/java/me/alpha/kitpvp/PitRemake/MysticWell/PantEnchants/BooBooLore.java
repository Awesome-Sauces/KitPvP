package me.alpha.kitpvp.PitRemake.MysticWell.PantEnchants;

import de.tr7zw.nbtapi.NBTItem;
import me.alpha.kitpvp.CustomEvents.ReduxDamageEvent;
import me.alpha.kitpvp.KitPvP;
import me.alpha.kitpvp.PitRemake.MysticWell.EnchantRarity;
import me.alpha.kitpvp.PitRemake.MysticWell.PitEnchant;
import me.alpha.kitpvp.utils.CitizensHelper;
import org.bukkit.scheduler.BukkitRunnable;

import static me.alpha.kitpvp.utils.IntegerHelper.integerToRoman;

public class BooBooLore extends PitEnchant {

    @Override
    public void run(ReduxDamageEvent event) {
        if (!CitizensHelper.isNPC(event.getDefenders().getPlayerObject()) &&
                event.getDefenders().getPlayerObject().getInventory().getLeggings()!=null){
            NBTItem item = new NBTItem(event.getDefenders().getPlayerObject().getInventory().getLeggings());

            if(!item.hasKey("booboo")) return;


        }else if(!CitizensHelper.isNPC(event.getDefenders().getPlayerObject()) &&
                event.getDefenders().getPlayerObject().getInventory().getLeggings()==null) return;
        else if(CitizensHelper.isNPC(event.getDefenders().getPlayerObject())) return;

        NBTItem item = new NBTItem(event.getDefenders().getPlayerObject().getInventory().getLeggings());
        int level = item.getInteger("booboo");

        if (event.getDefenders().getBooCD()){
            event.getDefenders().setBooCD();

            event.getDefenders().getPlayerObject().setHealth(Math.min(event.getDefenders().getPlayerObject().getMaxHealth(),
                    event.getDefenders().getPlayerObject().getHealth()+(.25*level)));

            new BukkitRunnable() {
                @Override
                public void run() {
                    event.getDefenders().setBooCD();
                }
            }.runTaskLater(KitPvP.INSTANCE, 11L);
        }

        //if (level==1) event.getDefenders().getPlayerObject().setHealth(Math.min(event.getDefenders().getPlayerObject().getHealth()+.5, event.getDefenders().getPlayerObject().getMaxHealth()));
    }

    @Override
    public void init() {
        rarity = EnchantRarity.NORMAL;
    }

    @Override
    public String title(int level) {
        String tier = "";
        if (level > 1){tier += " " + integerToRoman(level);}

        return "&9Boo-boo" + tier;
    }

    @Override
    public String lore(int level) {
        String tier = "";
        if (level > 1){tier += " " + integerToRoman(level);}

        String multiplier = String.valueOf(.25*level);

        String lore = "&9Boo-boo" + tier + "\n" +
                "&7Heal &c" + multiplier + "\u2764 &7when hit."+ "\n&7";

        return colorCode(lore);
    }
}

