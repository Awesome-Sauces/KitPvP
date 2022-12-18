package me.alpha.kitpvp.PitRemake.MysticWell.BowEnchants;

import de.tr7zw.nbtapi.NBTItem;
import me.alpha.kitpvp.CustomEvents.ReduxBowEvent;
import me.alpha.kitpvp.CustomEvents.ReduxDamageEvent;
import me.alpha.kitpvp.KitPvP;
import me.alpha.kitpvp.Objects.ReduxPlayerObject.ReduxPlayer;
import me.alpha.kitpvp.PitRemake.MysticWell.EnchantRarity;
import me.alpha.kitpvp.PitRemake.MysticWell.PitEnchant;
import me.alpha.kitpvp.utils.CitizensHelper;
import org.bukkit.Material;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import static me.alpha.kitpvp.Objects.ReduxPlayerObject.ReduxPlayerHandler.playerExists;
import static me.alpha.kitpvp.utils.IntegerHelper.integerToRoman;

public class FasterThenTheirShadowLore extends PitEnchant {

    @Override
    public void run(ReduxDamageEvent event) {
    }

    public void bow(ReduxBowEvent event){

        if (!CitizensHelper.isNPC(event.getAttacker().getPlayerObject()) &&
                event.getAttacker().getPlayerObject().getItemInHand()!=null &&
                event.getAttacker().getPlayerObject().getItemInHand().getType()!= Material.AIR){
            NBTItem item = new NBTItem(event.getAttacker().getPlayerObject().getItemInHand());

            if(!item.hasKey("fasterthantheirshadow")) return;

            int level = item.getInteger("fasterthantheirshadow");

            event.getAttacker().fShotCount++;

            int multiplier = (int) (4+(Math.round(level*.5)));
            int speed = level+1;
            int shots = 5-level;

            if (event.getAttacker().fShotCount>=shots){
                event.getAttacker().fShotCount=0;
                event.getAttacker().addPotionEffect(PotionEffectType.SPEED, multiplier,speed);
            }

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

        return "&9Faster than their shadow" + tier;
    }

    @Override
    public String lore(int level) {
        String tier = "";
        if (level > 1){tier += " " + integerToRoman(level);}

        String multiplier = String.valueOf(4+(Math.round(level*.5)));
        String speed = String.valueOf(integerToRoman(level+1));
        String shots = String.valueOf(5-level);

        String lore = "&9Faster than their shadow" + tier + "\n" +
                "&7Hitting &f"+shots+" &7shots without\n" +
                "&7missing grants &eSpeed "+speed+" &7("+multiplier+"s)"
                + "\n&7";

        return colorCode(lore);
    }
}
