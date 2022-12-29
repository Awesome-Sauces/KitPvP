package me.alpha.kitpvp.PitRemake.MysticWell.PantEnchants;

import de.tr7zw.nbtapi.NBTItem;
import me.alpha.kitpvp.CustomEvents.ReduxDamageEvent;
import me.alpha.kitpvp.Objects.ReduxPlayerObject.ReduxPlayer;
import me.alpha.kitpvp.PitRemake.MysticWell.EnchantRarity;
import me.alpha.kitpvp.PitRemake.MysticWell.PitEnchant;
import me.alpha.kitpvp.events.TrueDamageHandler;
import me.alpha.kitpvp.utils.CitizensHelper;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import static me.alpha.kitpvp.Objects.ReduxPlayerObject.ReduxPlayerHandler.playerExists;
import static me.alpha.kitpvp.utils.IntegerHelper.integerToRoman;

public class MiseryLore extends PitEnchant {

    @Override
    public void run(ReduxDamageEvent event) {
        if (!CitizensHelper.isNPC(event.getAttacker().getPlayerObject()) &&
                event.getAttacker().getPlayerObject().getInventory().getLeggings()!=null){
            NBTItem item = new NBTItem(event.getAttacker().getPlayerObject().getInventory().getLeggings());

            if(item.hasKey("misery")) {
                if(event.getDefender().getLeggings()!=null&&
                event.getDefender().getLeggings().getType().equals(Material.LEATHER_LEGGINGS)){
                    event.addReduxTrueDamage(1);
                    trueDamage(event.getDefender(), event.getAttacker(), .6);
                }
            }


        }
    }

    private void trueDamage(ReduxPlayer attacker, ReduxPlayer defender, double multiplier){
        new TrueDamageHandler(attacker, defender, multiplier, 0).run();
    }

    @Override
    public void init() {
        rarity = EnchantRarity.NORMAL;
    }

    @Override
    public String title(int level) {
        String tier = "";
        if (level > 1){tier += " " + integerToRoman(level);}

        return "&9Misery" + tier;
    }

    @Override
    public String lore(int level) {

        String lore = "&9Misery" + "\n" +
                "&7Deal &c+0.5\u2764 &7true damage against\n" +
                "&7players wearing leather pants but\n" +
                "&7take &c0.3\u2764"
                + "\n&7";

        return colorCode(lore);
    }
}