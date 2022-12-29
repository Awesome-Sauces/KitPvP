package me.alpha.kitpvp.PitRemake.MysticWell.PantEnchants;

import de.tr7zw.nbtapi.NBTItem;
import me.alpha.kitpvp.CustomEvents.ReduxDamageEvent;
import me.alpha.kitpvp.PitRemake.MysticWell.EnchantRarity;
import me.alpha.kitpvp.PitRemake.MysticWell.PitEnchant;
import me.alpha.kitpvp.utils.CitizensHelper;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;

import static me.alpha.kitpvp.utils.IntegerHelper.integerToRoman;

public class EscapePodLore extends PitEnchant {

    @Override
    public void run(ReduxDamageEvent event) {
        if (!CitizensHelper.isNPC(event.getDefender().getPlayerObject()) &&
                event.getDefender().getPlayerObject().getInventory().getLeggings()!=null){
            NBTItem item = new NBTItem(event.getDefender().getPlayerObject().getInventory().getLeggings());

            if(!item.hasKey("escapepod")) return;


        }else if(!CitizensHelper.isNPC(event.getDefender().getPlayerObject()) &&
                event.getDefender().getPlayerObject().getInventory().getLeggings()==null) return;
        else if(CitizensHelper.isNPC(event.getDefender().getPlayerObject())) return;

        NBTItem item = new NBTItem(event.getDefender().getPlayerObject().getInventory().getLeggings());
        int level = item.getInteger("escapepod");

        if(event.getDefender().getEscape() && event.getDefender().getPlayerObject().getHealth()-event.getReduxDamage() <= 8){
            giveRegen(event.getDefender().getPlayerObject(), level+2, 15+((level-1)*15));
        }
    }

    private void giveRegen(Player player, int power, int time){
        player.setVelocity(new Vector(0, 10, 0));
        player.removePotionEffect(PotionEffectType.REGENERATION);
        player.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, time*20, Math.max(power-1, 0), true, true));
    }

    @Override
    public void init() {
        rarity = EnchantRarity.RARE;
    }

    @Override
    public String title(int level) {
        String tier = "";
        if (level > 1){tier += " " + integerToRoman(level);}

        return "&dRARE! &9Escape Pod" + tier;
    }

    @Override
    public String lore(int level) {
        String tier = "";
        if (level > 1){tier += " " + integerToRoman(level);}

        String multiplier = String.valueOf(15+((level-1)*15));

        String lore = "&dRARE! &9Escape Pod" + tier + "\n" +
                "&7When hit below &c2 &7, launch\n" +
                "&7into the air dealing &c3 &7damage\n" +
                "&7to nearby enemies and gaining\n" +
                "&aRegen IV&7 ("+multiplier+"s), Can launch\n" +
                "&7once per life." + "\n&7";

        return colorCode(lore);
    }
}