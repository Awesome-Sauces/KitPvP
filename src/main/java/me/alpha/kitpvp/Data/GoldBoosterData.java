package me.alpha.kitpvp.Data;

import me.alpha.kitpvp.utils.DataStore;

public class GoldBoosterData extends DataStore {
    public GoldBoosterData(String refID) {
        super(refID);
    }

    public int getBooster(String uuid){
        return (int) getValue(uuid, 0);
    }

    public void setBooster(String uuid, int amount){
        setValue(uuid, amount);
    }

    public void addBooster(String uuid, int amount){
        setBooster(uuid, getBooster(uuid)+amount);
    }

    public void subtractBooster(String uuid, int amount){
        setBooster(uuid, Math.max(0, getBooster(uuid)-amount));
    }
}
