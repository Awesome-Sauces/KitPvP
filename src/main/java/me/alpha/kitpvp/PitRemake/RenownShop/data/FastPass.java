package me.alpha.kitpvp.PitRemake.RenownShop.data;

import me.alpha.kitpvp.utils.DataStore;
import static me.alpha.kitpvp.utils.ColorUtil.colorCode;

public class FastPass extends DataStore{
    public FastPass(String refID) {
        super(refID);
    }

    public String getLore(){
        return colorCode("&7Start at level 50 after\n" +
                "&7prestige.");
    }
}
