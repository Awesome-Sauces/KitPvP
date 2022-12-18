package me.alpha.kitpvp.PitRemake.RenownShop.data;

import me.alpha.kitpvp.utils.DataStore;
import static me.alpha.kitpvp.utils.ColorUtil.colorCode;

public class Heresy extends DataStore{
    public Heresy(String refID) {
        super(refID);
    }

    public String getLore(){
        return colorCode("&7You have trained in the profane\n" +
                "&7arts of counter-mysticism.");
    }
}
