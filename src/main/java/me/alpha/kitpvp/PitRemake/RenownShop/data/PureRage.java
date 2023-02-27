package me.alpha.kitpvp.PitRemake.RenownShop.data;

import me.alpha.kitpvp.utils.DataStore;

import static me.alpha.kitpvp.utils.ColorUtil.colorCode;

public class PureRage extends DataStore {
    public PureRage(String refID) {
        super(refID);
    }

    public String getLore(int level){
        return colorCode("&7Rush through your opponents with\n" +
                "&4PURE RAGE &7shredding &c"+(level)+"% &7of\n" +
                "&7your opponents health! &8(Aura Power)");
    }
}