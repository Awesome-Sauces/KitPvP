package me.alpha.kitpvp.PitRemake.MysticWell.BowEnchants;

// \u2764

import de.tr7zw.nbtapi.NBTItem;
import me.alpha.kitpvp.CustomEvents.ReduxBowEvent;
import me.alpha.kitpvp.CustomEvents.ReduxDamageEvent;
import me.alpha.kitpvp.PitRemake.MysticWell.EnchantRarity;
import me.alpha.kitpvp.PitRemake.MysticWell.PitEnchant;
import me.alpha.kitpvp.utils.CitizensHelper;
import me.alpha.kitpvp.utils.Sounds;
import org.bukkit.Material;
import org.bukkit.potion.PotionEffectType;

import static me.alpha.kitpvp.utils.IntegerHelper.integerToRoman;

public class ParasiteLore extends PitEnchant {

    @Override
    public void run(ReduxDamageEvent event) {
    }

    public void bow(ReduxBowEvent event){

        if (!CitizensHelper.isNPC(event.getAttacker().getPlayerObject()) &&
                event.getAttacker().getPlayerObject().getItemInHand()!=null &&
                event.getAttacker().getPlayerObject().getItemInHand().getType()!= Material.AIR){
            NBTItem item = new NBTItem(event.getAttacker().getPlayerObject().getItemInHand());

            if(!item.hasKey("parasite")) return;

            int level = item.getInteger("parasite");

            double multiplier = 0.0;

            if(level>=3){
                multiplier = ((double)(25+(25*level)))/100;
            }else{
                multiplier = ((double)((25*level)))/100;
            }


            double playerHealth = event.getAttacker().getPlayerObject().getHealth();
            double playerMaxHealth = event.getAttacker().getPlayerObject().getMaxHealth();

            event.getAttacker().getPlayerObject().setHealth(Math.min(playerMaxHealth, playerHealth+(multiplier*2)));

            Sounds.YUMMY_BREAD.play(event.getAttacker().getPlayerObject());

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

        return "&9Parasite" + tier;
    }

    @Override
    public String lore(int level) {
        String tier = "";
        if (level > 1){tier += " " + integerToRoman(level);}

        String multiplier = "";

        if(level>=3){
            multiplier = String.valueOf((25+(25*level))/100);
        }else{
            multiplier = String.valueOf(((25*level))/100);
        }

        String lore = "&9Parasite" + tier + "\n" +
                "&7Heal &c"+multiplier+"\u2764 &7on arrow hit"
                +"\n&7";

        return colorCode(lore);
    }
}
