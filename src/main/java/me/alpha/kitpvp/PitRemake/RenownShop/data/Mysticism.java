package me.alpha.kitpvp.PitRemake.RenownShop.data;

import me.alpha.kitpvp.utils.DataStore;
import static me.alpha.kitpvp.utils.ColorUtil.colorCode;

public class Mysticism extends DataStore{
    public Mysticism(String refID) {
        super(refID);
    }

    public String getLore(Integer tier){
        return colorCode("&7Next tier:\n" +
                "&7You find mystic items and pants\n" +
                "&d+"+(tier*5)+"% &7more often than\n&7normal.");
    }

    public void setMysticismChance(String uuid, int amount){
         setValue(uuid, amount);
    }

    public void addMysticismChance(String uuid, int amount){
        addValue(uuid, amount);
    }

    public int getMysticismChance(String uuid){
        return (int) getValue(uuid, 0);
    }
}
