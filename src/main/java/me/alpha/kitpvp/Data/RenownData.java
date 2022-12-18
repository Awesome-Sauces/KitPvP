package me.alpha.kitpvp.Data;

import me.alpha.kitpvp.utils.DataStore;

public class RenownData extends DataStore {
    public RenownData(String refID) {
        super(refID);
    }

    public int getRenown(String uuid){
        return (int) getValue(uuid, 0);
    }

    public void setRenown(String uuid, int amount){
        setValue(uuid, amount);
    }

    public void addRenown(String uuid, int amount){
        addValue(uuid, amount);
    }
}
