package me.alpha.kitpvp.PitRemake.RenownShop.data;

import me.alpha.kitpvp.utils.DataStore;
import org.bukkit.ChatColor;

public class BeastmodeStreak extends DataStore {
    public BeastmodeStreak(String refID) {
        super(refID);
    }

    public String getLore(){
        return ChatColor.GRAY + "Includes:\n" +
                ChatColor.DARK_GRAY+"&8- "+ChatColor.GREEN+"R&&R"+ChatColor.GREEN+"R\n"+
                ChatColor.DARK_GRAY+"&8- "+ChatColor.GREEN+"Tough Skin\n" +
                ChatColor.DARK_GRAY+"&8- "+ChatColor.GREEN+"Tactical Retreat\n" +
                ChatColor.DARK_GRAY+"&8- "+ChatColor.GREEN+"Monster\n\n" +
                ChatColor.GRAY + "Megastreak: " + ChatColor.GREEN + "Beastmode";
    }
}
