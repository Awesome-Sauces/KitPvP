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

        if(prestige==21) return 350000;
        if(prestige==22) return 400000;
        if(prestige==23) return 500000;
        if(prestige==24) return 600000;
        if(prestige==25) return 700000;

        if(prestige==26) return 800000;
        if(prestige==27) return 900000;
        if(prestige==28) return 1000000;
        if(prestige==29) return 1000000;
        if(prestige==30) return 1000000;

        if(prestige==31) return 1000000;
        if(prestige==32) return 1000000;
        if(prestige==33) return 1000000;
        if(prestige==34) return 1000000;
        if(prestige==35) return 1000000;
        if(prestige==36) return 2000000;
        if(prestige==37) return 2000000;
        if(prestige==38) return 2000000;
        if(prestige==39) return 2000000;
        if(prestige==40) return 2000000;

        if(prestige==41) return 2000000;
        if(prestige==42) return 2000000;
        if(prestige==43) return 2000000;
        if(prestige==44) return 2000000;
        if(prestige==45) return 2000000;
        if(prestige==46) return 2000000;
        if(prestige==47) return 2000000;
        if(prestige==48) return 2000000;
        if(prestige==49) return 2000000;
        if(prestige==50) return 2000000;

        if(prestige <= 55) return prestige*50000;

        if(prestige <= 60) return prestige*60000;

        if(prestige <= 65) return prestige*65000;

        if(prestige <= 80) return prestige*80000;

        if(prestige <= 85) return prestige*85000;

        if(prestige <= 94) return prestige*95000;

        if(prestige <= 100) return prestige*250000;


        return 1;
    }
}
