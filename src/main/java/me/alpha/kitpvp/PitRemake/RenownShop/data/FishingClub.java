package me.alpha.kitpvp.PitRemake.RenownShop.data;

import me.alpha.kitpvp.utils.DataStore;

import static me.alpha.kitpvp.utils.ColorUtil.colorCode;

public class FishingClub extends DataStore {
    public FishingClub(String refID) {
        super(refID);
    }

    public String getLore(int level){
        return colorCode("Fish &b+"+(level*5)+"&% &7better loot.");
    }
}