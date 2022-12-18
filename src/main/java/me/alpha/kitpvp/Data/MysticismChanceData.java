package me.alpha.kitpvp.Data;

import me.alpha.kitpvp.utils.DataStore;

public class MysticismChanceData extends DataStore {
    public MysticismChanceData(String refID) {
        super(refID);
    }

    public void addMysticismChance(String uuid, int amount){
        addValue(uuid, amount);
    }

    public void setMysticismChance(String uuid, int amount){
        setValue(uuid, amount);
    }

    public int getMysticismChance(String uuid){
        return (int) getValue(uuid, 0);
    }
}
