package me.alpha.kitpvp.PitRemake.RenownShop.data;

import me.alpha.kitpvp.utils.DataStore;
import static me.alpha.kitpvp.utils.ColorUtil.colorCode;

public class MoonStreak extends DataStore {
    public MoonStreak(String refID) {
        super(refID);
    }

    public String getLore(){
        return colorCode("&7Includes:\n" +
                "&8- &bSuper Streaker\n"+
                "&8- &bGold Stack\n"+
                "&8- &bXP Stack\n\n"+
                "&7Megastreak: &bTo the Moon");
    }
}
