package me.alpha.kitpvp.PitRemake.RenownShop.data;

import me.alpha.kitpvp.utils.DataStore;
import static me.alpha.kitpvp.utils.ColorUtil.colorCode;

public class TheWay extends DataStore{
    public TheWay(String refID) {
        super(refID);
    }

    public String getLore(){
        return colorCode("&7No perk level requirements.");
    }
}
