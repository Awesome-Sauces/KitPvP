package me.alpha.kitpvp.PitRemake.Perks.data;

import me.alpha.kitpvp.utils.DataStore;

import java.util.List;

public class KillStreakPerkOne extends DataStore {
    public KillStreakPerkOne(String refID) {
        super(refID);
    }

    public String getPerk(String uuid){
        return getValue(uuid, "NONE").toString();
    }

    public void setPerk(String uuid, String perk){
        setValue(uuid, perk);
    }

}
