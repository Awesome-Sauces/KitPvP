package me.alpha.kitpvp.PitRemake.RenownShop.data;

import me.alpha.kitpvp.utils.DataStore;
import static me.alpha.kitpvp.utils.ColorUtil.colorCode;

public class Promotion extends DataStore{
    public Promotion(String refID) {
        super(refID);
    }

    public String getLore(){
        return colorCode("&7Upgrade the \"Assistant (to the)\n" +
                "&7Streak\" perk:\n\n" +
                "&aAssistant Streak\n" +
                "&7Earn &6+2g &7and &b+15% XP\n" +
                "&7from kills and assists.\n\n" +
                "&7Gain &e+1 &7streak every &c4th\n" +
                "&ckill&7.\n&8Save Mystic Lives at 100 streak");
    }
}
