package me.alpha.kitpvp.PitRemake.RenownShop.data;

import me.alpha.kitpvp.utils.DataStore;
import static me.alpha.kitpvp.utils.ColorUtil.colorCode;

public class HighlanderStreak extends DataStore{
    public HighlanderStreak(String refID) {
        super(refID);
    }

    public String getLore(){
        return colorCode("&7Includes:\n" +
                "&8- &6Khanate\n"+
                "&8- &6Rush\n"+
                "&8- &6Gold Nano-factory\n\n"+
                "&7Megastreak: &6Highlander");
    }
}
