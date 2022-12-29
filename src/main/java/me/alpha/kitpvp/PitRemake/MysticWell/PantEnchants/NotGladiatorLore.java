package me.alpha.kitpvp.PitRemake.MysticWell.PantEnchants;

import de.tr7zw.nbtapi.NBTItem;
import me.alpha.kitpvp.CustomEvents.ReduxDamageEvent;
import me.alpha.kitpvp.PitRemake.MysticWell.EnchantRarity;
import me.alpha.kitpvp.PitRemake.MysticWell.PitEnchant;
import me.alpha.kitpvp.utils.CitizensHelper;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import static me.alpha.kitpvp.utils.IntegerHelper.integerToRoman;

public class NotGladiatorLore extends PitEnchant {

    @Override
    public void run(ReduxDamageEvent event) {

        if (!CitizensHelper.isNPC(event.getDefender().getPlayerObject()) &&
                event.getDefender().getPlayerObject().getInventory().getLeggings()!=null){
            NBTItem item = new NBTItem(event.getDefender().getPlayerObject().getInventory().getLeggings());

            if(!item.hasKey("notgladiator")) return;


        }else if(!CitizensHelper.isNPC(event.getDefender().getPlayerObject()) &&
                event.getDefender().getPlayerObject().getInventory().getLeggings()==null) return;
        else if(CitizensHelper.isNPC(event.getDefender().getPlayerObject())) return;

        NBTItem item = new NBTItem(event.getDefender().getPlayerObject().getInventory().getLeggings());
        int level = item.getInteger("notgladiator");

        double damage = 1+((level-1)*.5);

        event.subtractReduxDamageMultiplier(getMultiplier(event.getDefender().getPlayerObject(), (damage)));

    }

    private double getMultiplier(Player player, double multiplier){

        double power = 0;

        for(Entity entity : player.getNearbyEntities(7, 7, 7))
            if(entity instanceof Player) power += multiplier;

        return Math.min(power, multiplier * 5);
    }

    @Override
    public void init() {
        rarity = EnchantRarity.NORMAL;
    }

    @Override
    public String title(int level) {
        String tier = "";
        if (level > 1){tier += " " + integerToRoman(level);}

        return "&9\"Not\" Gladiator" + tier;
    }

    @Override
    public String lore(int level) {
        String tier = "";
        if (level > 1){tier += " " + integerToRoman(level);}

        String multiplier = String.valueOf(1+((level-1)*.5));

        String lore = "&9\"Not\" Gladiator" + tier + "\n" +
                "&7Receive &9-"+multiplier+"% damage per nearby\n" +
                "&7player (max 10 players)" + "\n&7";

        return colorCode(lore);
    }
}