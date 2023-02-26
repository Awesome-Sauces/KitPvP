package me.alpha.kitpvp.PitRemake.MysticWell.PantEnchants;

import de.tr7zw.nbtapi.NBTItem;
import me.alpha.kitpvp.CustomEvents.ReduxDamageEvent;
import me.alpha.kitpvp.KitPvP;
import me.alpha.kitpvp.PitRemake.MysticWell.EnchantRarity;
import me.alpha.kitpvp.PitRemake.MysticWell.PitEnchant;
import me.alpha.kitpvp.utils.CitizensHelper;
import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitRunnable;

import static me.alpha.kitpvp.utils.IntegerHelper.integerToRoman;

public class RegularityLore extends PitEnchant {

    @Override
    public void run(ReduxDamageEvent event) {

        if (!CitizensHelper.isNPC(event.getAttacker().getPlayerObject()) &&
                event.getAttacker().getPlayerObject().getInventory().getLeggings()!=null){
            NBTItem item = new NBTItem(event.getAttacker().getPlayerObject().getInventory().getLeggings());

            if(!item.hasKey("regularity")) return;


        }else if(!CitizensHelper.isNPC(event.getAttacker().getPlayerObject()) &&
                event.getAttacker().getPlayerObject().getInventory().getLeggings()==null) return;
        else if(CitizensHelper.isNPC(event.getAttacker().getPlayerObject())) return;

        NBTItem item = new NBTItem(event.getAttacker().getPlayerObject().getInventory().getLeggings());
        int level = item.getInteger("regularity");

        if(event.getBukkitEvent().getFinalDamage()>=maxFinalDamage(level)) return;

        triggerAttack(event, level);
    }

    public static double maxFinalDamage(int enchantLvl) {
        return enchantLvl * 0.4 + 1.2;
    }

    public static double secondHitDamage(int enchantLvl) {
        return (enchantLvl * 15 + 30);
    }

    public void triggerAttack(ReduxDamageEvent event, int multiplier){
        if (event.getAttacker().getRegCD()){

            new BukkitRunnable() {
                @Override
                public void run() {
                    double damage = event.getBaseDamage()*(secondHitDamage(multiplier)*.01);
                    event.getDefender().getPlayerObject().setNoDamageTicks(0);
                    event.getDefender().getPlayerObject().damage(damage, event.getAttacker().getPlayerObject());

                }
            }.runTaskLater(KitPvP.INSTANCE, 3L);

            event.getAttacker().setRegCD();

            new BukkitRunnable() {
                @Override
                public void run() {
                    event.getAttacker().setRegCD();
                }
            }.runTaskLater(KitPvP.INSTANCE, 5L);
        }
    }

    @Override
    public void init() {
        rarity = EnchantRarity.RARE;
    }

    @Override
    public String title(int level) {
        String tier = "";
        if (level > 1){tier += " " + integerToRoman(level);}

        return "&dRARE! &9Regularity" + tier;
    }

    @Override
    public String lore(int level) {
        String tier = "";
        if (level > 1){tier += " " + integerToRoman(level);}

        String multiplier = String.valueOf(25+((level-1)*25));

        String lore = "&dRARE! &9Regularity" + tier + "\n" +
                "&7If the final damage of your strike\n" +
                "&7deals less than &c1.5\u2764&7 &7damage,\n" +
                "&7strike again in &a0.1s &7for &c"+multiplier+"%\n" +
                "&7damage" + "\n&7";

        return colorCode(lore);
    }
}