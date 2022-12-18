package me.alpha.kitpvp.PitRemake.MysticWell.SwordEnchants;

import de.tr7zw.nbtapi.NBTItem;
import me.alpha.kitpvp.CustomEvents.ReduxDamageEvent;
import me.alpha.kitpvp.KitPvP;
import me.alpha.kitpvp.Objects.ReduxPlayerObject.ReduxPlayer;
import me.alpha.kitpvp.PitRemake.MysticWell.EnchantRarity;
import me.alpha.kitpvp.PitRemake.MysticWell.PitEnchant;
import me.alpha.kitpvp.events.TrueDamageHandler;
import me.alpha.kitpvp.utils.CitizensHelper;
import me.alpha.kitpvp.utils.Sounds;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Random;

import static me.alpha.kitpvp.Objects.ReduxPlayerObject.ReduxPlayerHandler.playerExists;
import static me.alpha.kitpvp.utils.IntegerHelper.integerToRoman;


public class GambleLore extends PitEnchant {

    @Override
    public void run(ReduxDamageEvent event) {
        if (!CitizensHelper.isNPC(event.getAttacker().getPlayerObject()) &&
                event.getAttacker().getPlayerObject().getItemInHand()!=null){
            NBTItem item = new NBTItem(event.getAttacker().getPlayerObject().getItemInHand());

            if(!item.hasKey("gamble")) return;


        }else if(!CitizensHelper.isNPC(event.getAttacker().getPlayerObject()) &&
                event.getAttacker().getPlayerObject().getItemInHand()!=null) return;
        else if(CitizensHelper.isNPC(event.getAttacker().getPlayerObject())) return;

        NBTItem item = new NBTItem(event.getAttacker().getPlayerObject().getItemInHand());
        int level = item.getInteger("gamble");

        Random rand = new Random(); //instance of random class
        int upperbound = 2;
        int int_random = rand.nextInt(upperbound);
        switch (int_random){
            case 0:
                if(!gambleCooldown(event.getAttacker())) break;
                Sounds.GAMBLE_YES.play(event.getAttacker().getPlayerObject());
                event.addReduxTrueDamage(level*2);
                break;
            case 1:
                if(!gambleCooldown(event.getAttacker())) break;
                Sounds.GAMBLE_NO.play(event.getAttacker().getPlayerObject());
                gambleCalc(event.getAttacker().getPlayerObject(), level*2);
                break;
        }
    }

    private boolean gambleCooldown(ReduxPlayer owner){
        if (owner.getGambleCD()){
            owner.setGambleCD();
            new BukkitRunnable() {
                @Override
                public void run() {
                    owner.setGambleCD();
                }
            }.runTaskLater(KitPvP.INSTANCE, 5L);
            return true;
        }

        return false;
    }

    private void gambleCalc(Player player, double multiplier){
        new TrueDamageHandler(playerExists(player), playerExists(player), multiplier, 0).run();
    }

    @Override
    public void init() {
        rarity = EnchantRarity.RARE;
    }

    @Override
    public String title(int level) {
        String tier = "";
        if (level > 1){tier += " " + integerToRoman(level);}

        return "&dRARE! &9Gamble!" + tier;
    }

    @Override
    public String lore(int level) {
        String tier = "";
        if (level > 1){tier += " " + integerToRoman(level);}

        String multiplier = String.valueOf(level);

        String lore = "&dRARE! &9Gamble!" + tier + "\n" +
                "&d50% chance &7to deal &c"+multiplier+"\u2764&7 true\n" +
                "&7damage to whoever you hit, or to\n" + "&7yourself" + "\n&7";

        return colorCode(lore);
    }
}
