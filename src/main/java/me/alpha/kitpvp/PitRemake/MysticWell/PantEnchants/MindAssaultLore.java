package me.alpha.kitpvp.PitRemake.MysticWell.PantEnchants;

import de.tr7zw.nbtapi.NBTItem;
import me.alpha.kitpvp.CustomEvents.ReduxDamageEvent;
import me.alpha.kitpvp.PitRemake.MysticWell.EnchantRarity;
import me.alpha.kitpvp.PitRemake.MysticWell.PitEnchant;
import me.alpha.kitpvp.utils.CitizensHelper;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import java.util.List;

import static me.alpha.kitpvp.PitRemake.RenownShop.CookieMonster.MonsterHandler.getNearby;
import static me.alpha.kitpvp.utils.IntegerHelper.integerToRoman;

public class MindAssaultLore extends PitEnchant {

    @Override
    public void run(ReduxDamageEvent event) {
        if (!CitizensHelper.isNPC(event.getAttacker().getPlayerObject()) &&
                event.getAttacker().getPlayerObject().getInventory().getLeggings()!=null){
            NBTItem item = new NBTItem(event.getAttacker().getPlayerObject().getInventory().getLeggings());

            if(item.hasKey("mind")) {
                event.subtractReduxDamageMultiplier(25);

                event.addBaseDamage(Math.min(3*getMultiplier(event.getAttacker().getPlayerObject()), 9));
            }


        }

    }

    public double getMultiplier(Player player){
        List<Player> players = getNearby(player, 11, 11, 11);

        double count = 0;

        for(Player temp: players){
            if(temp.getInventory().getLeggings()!=null&&
            temp.getInventory().getLeggings().getType().equals(Material.LEATHER_LEGGINGS)){
                count++;
            }
        }

        return count;
    }

    @Override
    public void init() {
        rarity = EnchantRarity.NORMAL;
    }

    @Override
    public String title(int level) {
        String tier = "";
        if (level > 1){tier += " " + integerToRoman(level);}

        return "&9Mind Assault" + tier;
    }

    @Override
    public String lore(int level) {

        String lore = "&9Mind Assault" + "\n" +
                "&7Deal &5-60% &7damage. Once\n" +
                "&7applied, deal &c+3\u2764 &7damage\n" +
                "&7per player with leather armor\n" +
                "&7within &f11 blocks&7, up to &c+8\u2764\n" +
                "&7damage"
                + "\n&7";

        return colorCode(lore);
    }
}