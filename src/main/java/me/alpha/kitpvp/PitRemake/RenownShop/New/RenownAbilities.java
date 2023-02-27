package me.alpha.kitpvp.PitRemake.RenownShop.New;

import me.alpha.kitpvp.PitRemake.RenownShop.data.SelfConfidence;
import me.alpha.kitpvp.utils.DataStore;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import javax.script.ScriptException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class RenownAbilities {
    static HashMap<String, RenownUpgrade> upgrades = new HashMap<>();

    public static void registerRenownUpgrade(RenownUpgrade c){
        upgrades.put(c.getRefID(), c);
    }

    public static void autoRegistry(){
        registerRenownUpgrade(new FishingClub("fishingClub"));
        registerRenownUpgrade(new Heresy("heresy"));
    }

    public static void testAllClass(Player player) throws ScriptException {
        for(String refID : upgrades.keySet()){
            RenownUpgrade upgrade = upgrades.get(refID);

            Bukkit.broadcastMessage(String.valueOf(upgrade.getClass()));
            Bukkit.broadcastMessage(upgrade.getLore(3));

            player.getInventory().addItem(upgrade.getShopItem(player.getUniqueId().toString()));
        }
    }
}
