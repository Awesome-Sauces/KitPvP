package me.alpha.kitpvp.PitRemake.RenownShop.data;

import me.alpha.kitpvp.utils.DataStore;
import static me.alpha.kitpvp.utils.ColorUtil.colorCode;

public class RenownXpBump extends DataStore{
    public RenownXpBump(String refID) {
        super(refID);
    }

    public String getLore(){
        return colorCode("&7Each tier:\n" +
                "&7Earn &b+1 kill XP&7.");
    }
}
