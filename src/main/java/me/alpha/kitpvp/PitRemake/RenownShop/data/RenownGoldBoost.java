package me.alpha.kitpvp.PitRemake.RenownShop.data;

import me.alpha.kitpvp.utils.DataStore;
import static me.alpha.kitpvp.utils.ColorUtil.colorCode;

public class RenownGoldBoost extends DataStore{
    public RenownGoldBoost(String refID) {
        super(refID);
    }

    public String getLore(){
        return colorCode("&7Each tier:\n" +
                "&7Earn &6+1% gold (g) &7from\n" +
                "&7kills and coin pickups.");
    }
}
