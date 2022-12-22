package me.alpha.kitpvp.Data;

import me.alpha.kitpvp.utils.DataStore;
import org.bukkit.Bukkit;

import java.util.Arrays;
import java.util.List;

public class PetData extends DataStore {
    public PetData(String refID) {
        super(refID);
    }

    public String getPetData(String uuid){
        return getValue(uuid, "none-1-1").toString();
    }

    public void addPetXp(String uuid, int xp){
        List<String> data = Arrays.asList(getValue(uuid, "none-1-1").toString().split("-"));

        String pet = data.get(0);
        String level = data.get(1);

        xp = Integer.parseInt(data.get(2))+xp;

        setValue(uuid, pet+"-"+String.valueOf(level)+"-"+String.valueOf(xp));


    }

    public void addPetLevel(String uuid, int level){
        List<String> data = Arrays.asList(getValue(uuid, "none-1-1").toString().split("-"));

        String pet = data.get(0);
        String xp = data.get(2);

        level = Integer.parseInt(data.get(1))+level;

        setValue(uuid, pet+"-"+String.valueOf(level)+"-"+String.valueOf(xp));
    }

    public void setPetXp(String uuid, int xp){
        List<String> data = Arrays.asList(getValue(uuid, "none-1-1").toString().split("-"));

        String pet = data.get(0);
        String level = data.get(1);

        setValue(uuid, pet+"-"+String.valueOf(level)+"-"+String.valueOf(xp));
    }

    public void setPetLevel(String uuid, int level){
        List<String> data = Arrays.asList(getValue(uuid, "none-1-1").toString().split("-"));

        String pet = data.get(0);
        String xp = data.get(2);

        setValue(uuid, pet+"-"+String.valueOf(level)+"-"+String.valueOf(xp));
    }

    public int getLevelFromXP(String uuid, int xpPerLevel){
        int xp = getPetXp(uuid)+75000;
        int xpLevel = 0;

        int level = 1;
        int xpForCurrentLevel = 0;

        for(int i = 0; i <= 100; i++){
            xpLevel+=i*xpPerLevel;
            if(xpLevel<=xp) {
                xpForCurrentLevel = xpLevel;
                level = i;
            }
        }

        if(xp>=xpLevel){
            level=100;
        }

        if(xp==xpLevel){
            level++;
        }

        return level;

        //Bukkit.broadcastMessage(String.valueOf(level)+":"+(Math.abs(xp-xpForCurrentLevel)));

    }

    public int getLevelFromXP(String uuid, int xpPerLevel, int xp){
        xp =+75000;
        int xpLevel = 0;

        int level = 1;
        int xpForCurrentLevel = 0;

        for(int i = 0; i <= 100; i++){
            xpLevel+=i*xpPerLevel;
            if(xpLevel<=xp) {
                xpForCurrentLevel = xpLevel;
                level = i;
            }
        }

        if(xp>=xpLevel){
            level=100;
        }

        if(xp==xpLevel){
            level++;
        }

        return level;

        //Bukkit.broadcastMessage(String.valueOf(level)+":"+(Math.abs(xp-xpForCurrentLevel)));

    }

    public int getXPForLevel(String uuid, int xpPerLevel, int level){
        int xpLevel = 0;

        for(int i = 0; i <= 100; i++){
            xpLevel+=i*xpPerLevel;
            if(i==level){
                return xpLevel;
            }
        }

        return (int) ((xpPerLevel/2)*(Math.pow(level,2)))+(xpPerLevel*level);
    }

    public int getPetLevel(String uuid, int xpPerLevel){
        return ClassInstances.petData.getLevelFromXP(uuid, xpPerLevel);
    }

    public int getPetXp(String uuid){
        return Integer.parseInt(Arrays.asList(getValue(uuid, "none-1-1").toString().split("-")).get(2));
    }

    public String getPet(String uuid){
        return Arrays.asList(getValue(uuid, "none-1-1").toString().split("-")).get(0);
    }

    public void setPetData(String uuid, String pet, int level, int xp, int xpPerLevel){
        setValue(uuid, pet+"-"+getLevelFromXP(uuid,xpPerLevel,xp)+"-"+String.valueOf(xp));
    }
}
