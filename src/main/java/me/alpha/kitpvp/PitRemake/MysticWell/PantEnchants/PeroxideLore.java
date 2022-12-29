package me.alpha.kitpvp.PitRemake.MysticWell.PantEnchants;

import de.tr7zw.nbtapi.NBTItem;
import me.alpha.kitpvp.CustomEvents.ReduxDamageEvent;
import me.alpha.kitpvp.PitRemake.MysticWell.EnchantRarity;
import me.alpha.kitpvp.PitRemake.MysticWell.PitEnchant;
import me.alpha.kitpvp.utils.CitizensHelper;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import static me.alpha.kitpvp.utils.IntegerHelper.integerToRoman;

public class PeroxideLore extends PitEnchant {

    @Override
    public void run(ReduxDamageEvent event) {
        if (!CitizensHelper.isNPC(event.getDefender().getPlayerObject()) &&
                event.getDefender().getPlayerObject().getInventory().getLeggings()!=null){
            NBTItem item = new NBTItem(event.getDefender().getPlayerObject().getInventory().getLeggings());

            if(!item.hasKey("peroxide")) return;


        }else if(!CitizensHelper.isNPC(event.getDefender().getPlayerObject()) &&
                event.getDefender().getPlayerObject().getInventory().getLeggings()==null) return;
        else if(CitizensHelper.isNPC(event.getDefender().getPlayerObject())) return;

        NBTItem item = new NBTItem(event.getDefender().getPlayerObject().getInventory().getLeggings());
        int level = item.getInteger("peroxide");

        int time = 1;

        if (level > 1) {time = 8;}else{time=5;}

        giveRegen(event.getDefender().getPlayerObject(), Math.max(level-1, 1), time);

        //if (level==1) event.getDefenders().getPlayerObject().setHealth(Math.min(event.getDefenders().getPlayerObject().getHealth()+.5, event.getDefenders().getPlayerObject().getMaxHealth()));
    }

    private void giveRegen(Player player, int power, int time){
        if(!player.hasPotionEffect(PotionEffectType.REGENERATION)){
            player.removePotionEffect(PotionEffectType.REGENERATION);
            player.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, time*20, Math.max(power-1, 0), true, true));
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

        return "&9Peroxide" + tier;
    }

    @Override
    public String lore(int level) {
        String tier = "";
        if (level > 1){tier += " " + integerToRoman(level);}

        String multiplier = String.valueOf(integerToRoman(Math.max(level-1, 1)));

        String time = "";

        if (level > 1) {time = "8";}else{time="5";}

        String lore = "&9Peroxide" + tier + "\n" +
                "&7Gain &cRegen "+multiplier+" &7("+time+"s) when hit" + "\n&7";

        return colorCode(lore);
    }
}
