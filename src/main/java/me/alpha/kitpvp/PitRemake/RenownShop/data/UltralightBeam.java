package me.alpha.kitpvp.PitRemake.RenownShop.data;

import me.alpha.kitpvp.utils.DataStore;

import static me.alpha.kitpvp.utils.ColorUtil.colorCode;

public class UltralightBeam extends DataStore {
    public UltralightBeam(String refID) {
        super(refID);
    }

    public String getLore(int level){
        return colorCode("&7Gain a &f" + level + "% &7chance to\n" +
                "&7spawn an &dUltralight-Beam &7upon your\n" +
                "&7death. &8(Random Mid Location)");
    }
}