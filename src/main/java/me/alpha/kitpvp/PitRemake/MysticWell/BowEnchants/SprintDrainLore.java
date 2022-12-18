package me.alpha.kitpvp.PitRemake.MysticWell.BowEnchants;

import de.tr7zw.nbtapi.NBTItem;
import me.alpha.kitpvp.CustomEvents.ReduxBowEvent;
import me.alpha.kitpvp.CustomEvents.ReduxDamageEvent;
import me.alpha.kitpvp.PitRemake.MysticWell.EnchantRarity;
import me.alpha.kitpvp.PitRemake.MysticWell.PitEnchant;
import me.alpha.kitpvp.utils.CitizensHelper;
import org.bukkit.Material;
import org.bukkit.potion.PotionEffectType;

import static me.alpha.kitpvp.utils.IntegerHelper.integerToRoman;

public class SprintDrainLore extends PitEnchant {

    @Override
    public void run(ReduxDamageEvent event) {
    }

    public void bow(ReduxBowEvent event){

        if (!CitizensHelper.isNPC(event.getAttacker().getPlayerObject()) &&
                event.getAttacker().getPlayerObject().getItemInHand()!=null &&
                event.getAttacker().getPlayerObject().getItemInHand().getType()!= Material.AIR){
            NBTItem item = new NBTItem(event.getAttacker().getPlayerObject().getItemInHand());

            if(!item.hasKey("sprintdrain")) return;

            int level = item.getInteger("sprintdrain");

            event.getAttacker().fShotCount++;

            int multiplier = (int) ((int) 4+(Math.round(level*.5)));
            int speed = Math.max(level-1,1);

            event.getAttacker().addPotionEffect(PotionEffectType.SPEED, multiplier,speed);
            event.getDefender().addPotionEffect(PotionEffectType.SLOW, 3, speed);

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

        return "&9Sprint Drain" + tier;
    }

    @Override
    public String lore(int level) {
        String tier = "";
        if (level > 1){tier += " " + integerToRoman(level);}

        String multiplier = String.valueOf(4+(Math.round(level*.5)));
        String speed = String.valueOf(integerToRoman(Math.max(level-1,1)));

        String lore = "&9Sprint Drain" + tier + "\n" +
                "&7Arrow shots gran you &eSpeed "+speed+"\n" +
                "&7("+multiplier+"s) and apply &8Slowness I\n" +
                "&7(3s)" + "\n&7";

        return colorCode(lore);
    }
}

