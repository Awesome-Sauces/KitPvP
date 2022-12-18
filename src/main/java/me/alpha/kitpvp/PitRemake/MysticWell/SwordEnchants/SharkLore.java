package me.alpha.kitpvp.PitRemake.MysticWell.SwordEnchants;

import de.tr7zw.nbtapi.NBTItem;
import me.alpha.kitpvp.CustomEvents.ReduxDamageEvent;
import me.alpha.kitpvp.PitRemake.MysticWell.EnchantRarity;
import me.alpha.kitpvp.PitRemake.MysticWell.PitEnchant;
import me.alpha.kitpvp.utils.CitizensHelper;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

import static me.alpha.kitpvp.utils.IntegerHelper.integerToRoman;

public class SharkLore extends PitEnchant {

    @Override
    public void run(ReduxDamageEvent event) {
        if (!CitizensHelper.isNPC(event.getAttacker().getPlayerObject()) &&
                event.getAttacker().getPlayerObject().getItemInHand()!=null){
            NBTItem item = new NBTItem(event.getAttacker().getPlayerObject().getItemInHand());

            if(!item.hasKey("shark")) return;


        }else if(!CitizensHelper.isNPC(event.getAttacker().getPlayerObject()) &&
                event.getAttacker().getPlayerObject().getItemInHand()!=null) return;
        else if(CitizensHelper.isNPC(event.getAttacker().getPlayerObject())) return;

        NBTItem item = new NBTItem(event.getAttacker().getPlayerObject().getItemInHand());
        int level = item.getInteger("sharp");

        double multiplier = 1;

        if (level > 2) {
            multiplier += (level * 2) + 1;
        }else {multiplier += level*2;}


        event.addReduxDamageMultiplier(
                (Math.min(Math.min(getSharkPlayers(event.getAttacker().getPlayerObject()), 3) * (multiplier), 3*(multiplier) )));


    }

    public int getSharkPlayers(Player player){

        int pAmount = 0;

        for(Entity players : player.getNearbyEntities(7, 7, 7)){
            if(players instanceof Player){
                Player current = (Player) players;
                if((current.getHealth()/2) <= 10){
                    pAmount++;
                }
            }
        }
        return pAmount;
    }

    @Override
    public void init() {
        rarity = EnchantRarity.NORMAL;
    }

    @Override
    public String title(int level) {
        String tier = "";
        if (level > 1){tier += " " + integerToRoman(level);}

        return "&9Shark" + tier;
    }

    @Override
    public String lore(int level) {
        String tier = "";
        if (level > 1){tier += " " + integerToRoman(level);}

        String multiplier = "";

        if (level > 2) {
            multiplier += String.valueOf((level * 2) + 1);
        }else {multiplier += String.valueOf(level*2);}

        String lore = "&9Shark" + tier + "\n" +
                "&7Deal &c+"+multiplier+"%&7 damage per other" +
                "\n&7player below &c6\u2764&7 within 12" + "\n&7blocks" + "\n&7";

        return colorCode(lore);
    }
}
