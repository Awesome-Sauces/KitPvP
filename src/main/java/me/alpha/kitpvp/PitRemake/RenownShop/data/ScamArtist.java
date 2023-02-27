package me.alpha.kitpvp.PitRemake.RenownShop.data;

import me.alpha.kitpvp.utils.DataStore;

import static me.alpha.kitpvp.utils.ColorUtil.colorCode;

public class ScamArtist extends DataStore {
    public ScamArtist(String refID) {
        super(refID);
    }

    public String getLore(int level){
        return colorCode("&7Contracts cost &6"+(level*5)+"% &7less");
    }
}