package me.alpha.kitpvp.Data;

import me.alpha.kitpvp.utils.DataStore;

public class MegaStreakData extends DataStore {
    public MegaStreakData(String refID) {
        super(refID);
    }

    public void setMegaStreak(String uuid, String mega){
        setValue(uuid, mega);
    }

    public String getMegaStreak(String uuid){
        return getValue(uuid, "overdrive").toString();
    }

}
