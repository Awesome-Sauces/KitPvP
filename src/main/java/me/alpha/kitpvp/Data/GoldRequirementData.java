package me.alpha.kitpvp.Data;

import me.alpha.kitpvp.utils.DataStore;

public class GoldRequirementData extends DataStore {
    public GoldRequirementData(String refID) {
        super(refID);
    }

    public void addGoldReq(String uuid, int amount){
        addValue(uuid, amount);
    }

    public int getGoldReq(String uuid){
        return (int) getValue(uuid, 0);
    }

    public void setGoldReq(String uuid, int amount){
        setValue(uuid, amount);
    }

    public static int getGoldRequirement(int prestige){

        if(prestige==0) return 0;
        if(prestige==1) return 10000;
        if(prestige==2) return 20000;
        if(prestige==3) return 20000;
        if(prestige==4) return 20000;
        if(prestige==5) return 30000;
        if(prestige==6) return 35000;
        if(prestige==7) return 40000;
        if(prestige==8) return 45000;
        if(prestige==9) return 50000;
        if(prestige==10) return 60000;

        if(prestige==11) return 70000;
        if(prestige==12) return 80000;
        if(prestige==13) return 90000;
        if(prestige==14) return 100000;
        if(prestige==15) return 125000;
        if(prestige==16) return 150000;
        if(prestige==17) return 175000;
        if(prestige==18) return 200000;
        if(prestige==19) return 250000;
        if(prestige==20) return 300000;


        if (prestige <= 25) return prestige*15000;

        if (prestige <= 30) return prestige*100000;

        if(prestige <= 35) return prestige*150000;

        if(prestige <= 40) return prestige*200000;

        if(prestige <= 45) return prestige*250000;

        if(prestige <= 50) return prestige*500000;

        if(prestige <= 55) return prestige*600000;

        if(prestige <= 60) return prestige*700000;

        if(prestige <= 65) return prestige*800000;

        if(prestige <= 80) return prestige*850000;

        if(prestige <= 85) return prestige*900000;

        if(prestige <= 94) return prestige*950000;

        if(prestige <= 100) return prestige*1000000;


        return 1;
    }
}
