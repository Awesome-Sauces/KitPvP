package me.alpha.kitpvp.PitRemake.MysticWell.PantEnchants;

import de.tr7zw.nbtapi.NBTItem;
import me.alpha.kitpvp.CustomEvents.ReduxDamageEvent;
import me.alpha.kitpvp.PitRemake.MysticWell.EnchantRarity;
import me.alpha.kitpvp.PitRemake.MysticWell.PitEnchant;
import me.alpha.kitpvp.utils.CitizensHelper;
import org.bukkit.entity.Player;
import static me.alpha.kitpvp.utils.IntegerHelper.integerToRoman;

public class CriticallyFunkyLore extends PitEnchant {

    @Override
    public void run(ReduxDamageEvent event) {

        if (!CitizensHelper.isNPC(event.getDefenders().getPlayerObject()) &&
                event.getDefenders().getPlayerObject().getInventory().getLeggings()!=null){
            NBTItem item = new NBTItem(event.getDefenders().getPlayerObject().getInventory().getLeggings());

            if(!item.hasKey("criticallyfunky")) return;


        }else if(!CitizensHelper.isNPC(event.getDefenders().getPlayerObject()) &&
                event.getDefenders().getPlayerObject().getInventory().getLeggings()==null) return;
        else if(CitizensHelper.isNPC(event.getDefenders().getPlayerObject())) return;

        NBTItem item = new NBTItem(event.getDefenders().getPlayerObject().getInventory().getLeggings());
        int level = item.getInteger("criticallyfunky");
      
        double damage = .0001;

        if (level > 2) {
            damage += (level*7) + 9;
        }else {damage += level*7;}

        double dmg = 80-((level-1)*15);

        if(criticalHit(event.getAttacker().getPlayerObject().getPlayer())){
            event.getDefenders().setPlayerIncrease(damage/100);
            event.subtractReduxDamageMultiplier(dmg);
        }
    
    }

    private boolean criticalHit(Player player){
        return !player.isOnGround();
    }

    @Override
    public void init() {
        rarity = EnchantRarity.NORMAL;
    }

    @Override
    public String title(int level) {
        String tier = "";
        if (level > 1){tier += " " + integerToRoman(level);}

        return "&9Critically Funky" + tier;
    }

    @Override
    public String lore(int level) {
        String tier = "";
        if (level > 1){tier += " " + integerToRoman(level);}

        String multiplier = String.valueOf(80-((level-1)*15));
        String damage = "";

        if (level > 2) {
            damage += String.valueOf((level*7) + 9);
        }else {damage += String.valueOf(level*7);}

        String lore = "&9Critically Funky" + tier + "\n" +
                "&7Critical hits against you deal\n" +
                "&9" + multiplier + "% &7of the damage they\n" +
                "&7normally would and empower your\n" +
                "&7next strike for &c+" + damage + "%&7 damage" + "\n&7";

        return colorCode(lore);
    }
}