package me.alpha.kitpvp.PitRemake.RenownShop.data;

import me.alpha.kitpvp.utils.DataStore;

import static me.alpha.kitpvp.utils.ColorUtil.colorCode;

public class SelfConfidence extends DataStore {
    public SelfConfidence(String refID) {
        super(refID);
    }

    public String getLore(int level){
        return colorCode("&7Gain &6+"+(level*10)+"g &7cap and\n" +
                "&7gain &b+"+(level*10)+" XP &7cap");
    }
}
