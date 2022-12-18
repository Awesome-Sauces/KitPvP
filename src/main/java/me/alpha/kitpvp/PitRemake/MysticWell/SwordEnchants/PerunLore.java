package me.alpha.kitpvp.PitRemake.MysticWell.SwordEnchants;

import de.tr7zw.nbtapi.NBTItem;
import me.alpha.kitpvp.CustomEvents.ReduxDamageEvent;
import me.alpha.kitpvp.Data.ClassInstances;
import me.alpha.kitpvp.KitPvP;
import me.alpha.kitpvp.Objects.ReduxPlayerObject.ReduxPlayer;
import me.alpha.kitpvp.PitRemake.MysticWell.EnchantRarity;
import me.alpha.kitpvp.PitRemake.MysticWell.PitEnchant;
import me.alpha.kitpvp.utils.CitizensHelper;
import org.bukkit.scheduler.BukkitRunnable;

import static me.alpha.kitpvp.PitRemake.LightningStrike.strikeLightningForPlayers;
import static me.alpha.kitpvp.utils.IntegerHelper.integerToRoman;

public class PerunLore extends PitEnchant {

    @Override
    public void run(ReduxDamageEvent event) {

        if (!CitizensHelper.isNPC(event.getAttacker().getPlayerObject()) &&
                event.getAttacker().getPlayerObject().getItemInHand()!=null){
            NBTItem item = new NBTItem(event.getAttacker().getPlayerObject().getItemInHand());

            if(!item.hasKey("perun")) return;


        }else if(!CitizensHelper.isNPC(event.getAttacker().getPlayerObject()) &&
                event.getAttacker().getPlayerObject().getItemInHand()!=null) return;
        else if(CitizensHelper.isNPC(event.getAttacker().getPlayerObject())) return;

        NBTItem item = new NBTItem(event.getAttacker().getPlayerObject().getItemInHand());
        int level = item.getInteger("perun");

        int count = 5;

        if (perunCooldown(event.getAttacker()) && level > 1){count = 4;}

        addCounter(event);

        if(trigger(event, count)){
            event.addReduxTrueDamage(level*2);
        }
    }

    private boolean perunCooldown(ReduxPlayer owner){
        if (owner.getPerunCD()){
            owner.setPerunCD();
            new BukkitRunnable() {
                @Override
                public void run() {
                    owner.setPerunCD();
                }
            }.runTaskLater(KitPvP.INSTANCE, 11L);
            return true;
        }

        return false;
    }

    private void addCounter(ReduxDamageEvent event){
        if(ClassInstances.PerunHitCounter.containsKey(event.getAttacker().getPlayerUUID())){
            ClassInstances.PerunHitCounter.put(event.getAttacker().getPlayerUUID(), ClassInstances.PerunHitCounter.get(event.getAttacker().getPlayerUUID()) + 1);
        }else{
            ClassInstances.PerunHitCounter.put(event.getAttacker().getPlayerUUID(), 1);
        }
    }

    private boolean trigger(ReduxDamageEvent event, int count){
        if(ClassInstances.PerunHitCounter.get(event.getAttacker().getPlayerUUID()) >= count){
            ClassInstances.PerunHitCounter.put(event.getAttacker().getPlayerUUID(), 0);
            strikeLightningForPlayers(event.getDefenders().getPlayerObject().getLocation(), event.getAttacker().getPlayerObject(), event.getDefenders().getPlayerObject());
            return true;
        }else return false;
    }

    @Override
    public void init() {
        rarity = EnchantRarity.RARE;
    }

    @Override
    public String title(int level) {
        String tier = "";
        if (level > 1){tier += " " + integerToRoman(level);}

        return "&dRARE! &9Combo: Perun's Wrath" + tier;
    }

    @Override
    public String lore(int level) {

        String tier = "";
        if (level > 1){tier += " " + integerToRoman(level);}

        String multiplier = String.valueOf(level);

        String hit = "";

        if (level > 1){
            hit = "fourth";
        }else {hit = "fifth";}

        String lore = "&dRARE! &9Combo: Perun's Wrath" + tier + "\n" +
                "&7Each &e" + hit + "&7 hit strikes" +
                "\n&elightning&7 for &c" + multiplier + "\u2764&7." +
                "\n&7&oLightning deals true damage" + "\n&7";

        return colorCode(lore);
    }
}
