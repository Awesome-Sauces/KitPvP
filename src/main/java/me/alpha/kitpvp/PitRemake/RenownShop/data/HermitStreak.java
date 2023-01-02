package me.alpha.kitpvp.PitRemake.RenownShop.data;

import me.alpha.kitpvp.utils.DataStore;
import org.bukkit.ChatColor;

public class HermitStreak extends DataStore {
    public HermitStreak(String refID) {
        super(refID);
    }

    public String getLore(){
        return ChatColor.GRAY + "Includes:\n" +
                ChatColor.DARK_GRAY+"&8- "+ChatColor.BLUE+"Pungent\n"+
                ChatColor.DARK_GRAY+"&8- "+ChatColor.BLUE+"Glass Pickaxe\n" +
                ChatColor.DARK_GRAY+"&8- "+ChatColor.BLUE+"Aura of Protection\n" +
                ChatColor.DARK_GRAY+"&8- "+ChatColor.BLUE+"Ice Cube\n\n" +
                ChatColor.GRAY + "Megastreak: " + ChatColor.BLUE + "Hermit";
    }
}
