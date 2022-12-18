package me.alpha.kitpvp.PitRemake.MysticWell.SwordEnchants;

import de.tr7zw.nbtapi.NBTItem;
import me.alpha.kitpvp.CustomEvents.ReduxDamageEvent;
import me.alpha.kitpvp.Data.ClassInstances;
import me.alpha.kitpvp.KitPvP;
import me.alpha.kitpvp.Objects.ReduxPlayerObject.ReduxPlayer;
import me.alpha.kitpvp.PitRemake.MysticWell.EnchantRarity;
import me.alpha.kitpvp.PitRemake.MysticWell.PitEnchant;
import me.alpha.kitpvp.utils.CitizensHelper;
import me.alpha.kitpvp.utils.Sounds;
import org.bukkit.scheduler.BukkitRunnable;

import static me.alpha.kitpvp.utils.IntegerHelper.integerToRoman;

public class ComboDamageLore extends PitEnchant {

    @Override
    public void run(ReduxDamageEvent event) {


        if (!CitizensHelper.isNPC(event.getAttacker().getPlayerObject()) &&
                event.getAttacker().getPlayerObject().getItemInHand()!=null){
            NBTItem item = new NBTItem(event.getAttacker().getPlayerObject().getItemInHand());

            if(!item.hasKey("combodamage")) return;


        }else if(!CitizensHelper.isNPC(event.getAttacker().getPlayerObject()) &&
                event.getAttacker().getPlayerObject().getItemInHand()==null) return;
        else if(CitizensHelper.isNPC(event.getAttacker().getPlayerObject())) return;

        NBTItem item = new NBTItem(event.getAttacker().getPlayerObject().getItemInHand());
        int level = item.getInteger("combodamage");

        int count = 4;

        double damage = (double) ((10*level)+10);

        if (ComboDamageCooldown(event.getAttacker()) && level > 1){count = 3;}

        addCounter(event);

        if(trigger(event, count)){
            event.addReduxDamageMultiplier(damage);
        }
    }

    private boolean ComboDamageCooldown(ReduxPlayer owner){
        if (owner.getComboDamageCD()){
            owner.setComboDamageCD();
            new BukkitRunnable() {
                @Override
                public void run() {
                    owner.setComboDamageCD();
                }
            }.runTaskLater(KitPvP.INSTANCE, 5L);
            return true;
        }

        return false;
    }

    private void addCounter(ReduxDamageEvent event){
        if(ClassInstances.ComboDamageHitCounter.containsKey(event.getAttacker().getPlayerUUID())){
            ClassInstances.ComboDamageHitCounter.put(event.getAttacker().getPlayerUUID(), ClassInstances.ComboDamageHitCounter.get(event.getAttacker().getPlayerUUID()) + 1);
        }else{
            ClassInstances.ComboDamageHitCounter.put(event.getAttacker().getPlayerUUID(), 1);
        }
    }

    private boolean trigger(ReduxDamageEvent event, int count){
        if(ClassInstances.ComboDamageHitCounter.get(event.getAttacker().getPlayerUUID()) >= count){
            ClassInstances.ComboDamageHitCounter.put(event.getAttacker().getPlayerUUID(), 0);
            Sounds.COMBO_PROC.play(event.getAttacker().getPlayerObject());
            return true;
        }else return false;
    }

    @Override
    public void init() {
        EnchantRarity rarity = EnchantRarity.RARE;
    }

    @Override
    public String title(int level) {
        String tier = "";
        if (level > 1){tier += " " + integerToRoman(level);}

        return "&9Combo: Damage" + tier;
    }

    @Override
    public String lore(int level) {

        String tier = "";
        if (level > 1){tier += " " + integerToRoman(level);}

        String multiplier = String.valueOf((10 * level) + 10);

        String hit = "";

        if (level > 1){
            hit = "third";
        }else {hit = "fourth";}

        String lore = "&9Combo: Damage" + tier + "\n" +
                "&7Every &e" + hit + " &7strike deals\n" + "&c+" + multiplier + "% &7damage" + "\n&7";

        return colorCode(lore);
    }
}

