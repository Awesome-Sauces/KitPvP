package me.alpha.kitpvp.utils;

import me.alpha.kitpvp.KitPvP;
import org.bukkit.Bukkit;

import java.util.HashMap;

public abstract class DataStore {

    private String refID;
    private HashMap<Object,Object> hashMap = new HashMap<>();

    public DataStore(String refID){this.refID=refID;}

    public HashMap<Object, Object> getHashMap(){return this.hashMap;}

    public void hasValue(Object id, Object replacement){
        if(!this.hashMap.containsKey(id)) this.hashMap.put(id, replacement);
    }

    public boolean hasValue(Object id){
        return this.hashMap.containsKey(id);
    }

    public void addValue(Object id, Integer value){
        this.hasValue(id, value);
        this.hashMap.put(id, ((Integer)getValue(id, value))+value);
    }

    public void setValue(Object id, Object value){
        this.hashMap.put(id, value);
    }

    public Object getValue(Object id){
        if(hasValue(id)) return this.hashMap.get(id);
        return null;
    }

    public Object getValue(Object id, Object value){
        hasValue(id, value);
        return this.hashMap.get(id);
    }

    public void saveHashMap(){
        for(Object value : getHashMap().keySet()){
            KitPvP.INSTANCE.getConfig().set(this.refID+"."+value, getHashMap().get(value));
        }
        KitPvP.INSTANCE.saveConfig();
    }

    public void loadHashMap(){
        if (!KitPvP.INSTANCE.getConfig().contains(this.refID)) return;
        for (String s : KitPvP.INSTANCE.getConfig().getConfigurationSection(this.refID).getKeys(false))
        {
            this.hashMap.put(s, KitPvP.INSTANCE.getConfig().getInt(this.refID+"."+s));
        }
    }

    public void loadHashMap(boolean nonInt){
        if (!KitPvP.INSTANCE.getConfig().contains(this.refID)) return;
        for (String s : KitPvP.INSTANCE.getConfig().getConfigurationSection(this.refID).getKeys(false))
        {
            this.hashMap.put(s, KitPvP.INSTANCE.getConfig().get(this.refID+"."+s));
        }
    }

    public void test(){
        for(Object value : this.hashMap.values()){
            Bukkit.broadcastMessage(ColorUtil.colorCode("&c&l"+this.refID+"&7:&c&l"+value+"&7:&c&l"+value.getClass()));
        }
    }

}