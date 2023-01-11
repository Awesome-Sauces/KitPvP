package me.alpha.kitpvp.PitRemake.MysticWell.PantEnchants;

import de.tr7zw.nbtapi.NBTItem;
import me.alpha.kitpvp.CustomEvents.ReduxDamageEvent;
import me.alpha.kitpvp.Objects.ReduxPlayerObject.ReduxPlayer;
import me.alpha.kitpvp.PitRemake.MysticWell.EnchantRarity;
import me.alpha.kitpvp.PitRemake.MysticWell.PitEnchant;
import me.alpha.kitpvp.utils.CitizensHelper;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import static me.alpha.kitpvp.utils.IntegerHelper.integerToRoman;

public class SolitudeLore extends PitEnchant {

    @Override
    public void run(ReduxDamageEvent event) {

        if (!CitizensHelper.isNPC(event.getDefender().getPlayerObject()) &&
                event.getDefender().getPlayerObject().getInventory().getLeggings()!=null){
            NBTItem item = new NBTItem(event.getDefender().getPlayerObject().getInventory().getLeggings());

            if(!item.hasKey("solitude")) return;


        }else if(!CitizensHelper.isNPC(event.getDefender().getPlayerObject()) &&
                event.getDefender().getPlayerObject().getInventory().getLeggings()==null) return;
        else if(CitizensHelper.isNPC(event.getDefender().getPlayerObject())) return;

        NBTItem item = new NBTItem(event.getDefender().getPlayerObject().getInventory().getLeggings());
        int level = item.getInteger("solitude");

        double damage = 30+((level-1)*10);

        if (solitaryCheck(event.getDefender())) event.subtractReduxDamageMultiplier(damage);

    }

    private boolean solitaryCheck(ReduxPlayer player){
        Player location = player.getPlayerObject();
        int playerAmount = 0;

        for(Entity entity : location.getNearbyEntities(7, 7, 7))
            if(entity instanceof Player) playerAmount += 1;

        return playerAmount <= 2;
    }

    @Override
    public void init() {
        rarity = EnchantRarity.RARE;
    }

    @Override
    public String title(int level) {
        String tier = "";
        if (level > 1){tier += " " + integerToRoman(level);}

        return "&dRARE! &9Solitude" + tier;
    }

    @Override
    public String lore(int level) {
        String tier = "";
        if (level > 1){tier += " " + integerToRoman(level);}

        String multiplier = String.valueOf(40+((level-1)*10));

        String lore = "&dRARE! &9Solitude" + tier + "\n" +
                "&7Receive &9-"+multiplier+"% &7damage when two\n" +
                "&7or less players are within 7\n" +
                "&7blocks." + "\n&7";

        return colorCode(lore);
    }
}