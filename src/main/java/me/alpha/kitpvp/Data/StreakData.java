package me.alpha.kitpvp.Data;

import me.alpha.kitpvp.utils.DataStore;

public class StreakData extends DataStore {
    public StreakData(String refID) {
        super(refID);
    }

    public int getStreak(String uuid){
        return (int) getValue(uuid, 0);
    }

    public void addStreak(String uuid, int streak){
        addValue(uuid, streak);
    }

    public void setStreak(String uuid, int streak){
        setValue(uuid, streak);
    }
}
