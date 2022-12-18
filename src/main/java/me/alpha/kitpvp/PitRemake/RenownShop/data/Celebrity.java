package me.alpha.kitpvp.PitRemake.RenownShop.data;

import me.alpha.kitpvp.utils.DataStore;
import static me.alpha.kitpvp.utils.ColorUtil.colorCode;

public class Celebrity extends DataStore {
    public Celebrity(String refID) {
        super(refID);
    }

    public String getLore(){
        return colorCode("&7Literally earn &62x gold\n" +
                "&7from kills.");
    }
}
