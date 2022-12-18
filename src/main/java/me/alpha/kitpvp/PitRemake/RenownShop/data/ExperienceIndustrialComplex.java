package me.alpha.kitpvp.PitRemake.RenownShop.data;

import me.alpha.kitpvp.utils.DataStore;
import static me.alpha.kitpvp.utils.ColorUtil.colorCode;

public class ExperienceIndustrialComplex extends DataStore{
    public ExperienceIndustrialComplex(String refID) {
        super(refID);
    }

    public String getLore(){
        return colorCode("&7Streak &bXP &7bonus scales up to &c200 kills&7,\n" +
                "&b+50 max XP &7per kill.");
    }
}
