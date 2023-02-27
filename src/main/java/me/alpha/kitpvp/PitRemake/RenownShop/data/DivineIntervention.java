package me.alpha.kitpvp.PitRemake.RenownShop.data;

import me.alpha.kitpvp.utils.DataStore;

import static me.alpha.kitpvp.utils.ColorUtil.colorCode;

public class DivineIntervention extends DataStore {
    public DivineIntervention(String refID) {
        super(refID);
    }

    public String getLore(int level){
        return colorCode("&e"+(level*5)+"% &7to keep your\n" +
                "&7inventory on death.");
    }
}