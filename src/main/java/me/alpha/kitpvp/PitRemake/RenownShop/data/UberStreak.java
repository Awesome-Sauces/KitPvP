package me.alpha.kitpvp.PitRemake.RenownShop.data;

import me.alpha.kitpvp.utils.DataStore;
import static me.alpha.kitpvp.utils.ColorUtil.colorCode;

public class UberStreak extends DataStore{
    public UberStreak(String refID) {
        super(refID);
    }

    public String getLore(){
        return colorCode("&7Includes:\n\n" +
                "&7Megastreak: &dUberstreak");
    }
}
