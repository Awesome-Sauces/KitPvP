package me.alpha.kitpvp.Data;

import me.alpha.kitpvp.utils.DataStore;

import java.util.HashMap;

public class PrestigeData extends DataStore {

    public PrestigeData(String refID) {
        super(refID);
    }

    public int getPrestige(String uuid){
        return (int) getValue(uuid, 0);
    }

    public void setPrestige(String uuid, int prestige){
        setValue(uuid, prestige);
    }

    public void addPrestige(String uuid, int prestige){addValue(uuid, prestige);}

    public static double PrestigeXpAmount(Integer Prestige){
        double DivideValue;
        switch (Prestige){
            case 1:
            case 2:
                DivideValue = .5;
                return DivideValue;
            case 3:
                DivideValue = 1;
                return DivideValue;
            case 4:
            case 5:
                DivideValue = 1.5;
                return DivideValue;
            case 6:
                DivideValue = 2;
                return DivideValue;
            case 7:
                DivideValue = 2.5;
                return DivideValue;
            case 8:
                DivideValue = 2.75;
                return DivideValue;
            case 9:
                DivideValue = 3.25;
                return DivideValue;
            case 10:
                DivideValue = 5;
                return DivideValue;
            case 11:
                DivideValue = 6;
                return DivideValue;
            case 12:
                DivideValue = 8;
                return DivideValue;
            case 13:
                DivideValue = 9;
                return DivideValue;
            case 14:
                DivideValue = 11;
                return DivideValue;
            case 15:
                DivideValue = 12;
                return DivideValue;
            case 16:
                DivideValue = 14;
                return DivideValue;
            case 17:
                DivideValue = 15;
                return DivideValue;
            case 18:
                DivideValue = 17;
                return DivideValue;
            case 19:
                DivideValue = 19;
                return DivideValue;
            case 20:
                DivideValue = 25;
                return DivideValue;
            case 21:
                DivideValue = 27;
                return DivideValue;
            case 22:
                DivideValue = 29;
                return DivideValue;
            case 23:
                DivideValue = 31;
                return DivideValue;
            case 24:
                DivideValue = 32;
                return DivideValue;
            case 25:
                DivideValue = 37;
                return DivideValue;
            case 26:
                DivideValue = 40;
                return DivideValue;
            case 27:
                DivideValue = 44;
                return DivideValue;
            case 28:
                DivideValue = 55;
                return DivideValue;
            case 29:
                DivideValue = 67;
                return DivideValue;
            case 30:
                DivideValue = 100;
                return DivideValue;
            case 31:
                DivideValue = 135;
                return DivideValue;
            case 32:
                DivideValue = 145;
                return DivideValue;
            case 33:
                DivideValue = 155;
                return DivideValue;
            case 34:
                DivideValue = 165;
                return DivideValue;
            case 35:
                DivideValue = 235;
                return DivideValue;
            case 36:
                DivideValue = 265;
                return DivideValue;
            case 37:
                DivideValue = 285;
                return DivideValue;
            case 38:
                DivideValue = 300;
                return DivideValue;
            case 39:
                DivideValue = 335;
                return DivideValue;
            case 40:
                DivideValue = 385;
                return DivideValue;
            case 41:
                DivideValue = 400;
                return DivideValue;
            case 42:
                DivideValue = 450;
                return DivideValue;
            case 43:
                DivideValue = 500;
                return DivideValue;
            case 44:
                DivideValue = 575;
                return DivideValue;
            case 45:
                DivideValue = 725;
                return DivideValue;
            case 46:
                DivideValue = 835;
                return DivideValue;
            case 47:
                DivideValue = 955;
                return DivideValue;
            case 48:
                DivideValue = 1125;
                return DivideValue;
            case 49:
                DivideValue = 1255;
                return DivideValue;
            case 50:
                DivideValue = 1545;
                return DivideValue;
            case 51:
                DivideValue = 2130;
                return DivideValue;
            case 52:
                DivideValue = 3540;
                return DivideValue;
            case 53:
                DivideValue = 5250;
                return DivideValue;
            case 54:
                DivideValue = 6825;
                return DivideValue;
            case 55:
                DivideValue = 7600;
                return DivideValue;
            case 56:
                DivideValue = 7700;
                return DivideValue;
            case 57:
                DivideValue = 7800;
                return DivideValue;
            case 58:
                DivideValue = 7900;
                return DivideValue;
            case 59:
                DivideValue = 8000;
                return DivideValue;
            case 60:
                DivideValue = 9000;
                return DivideValue;
            case 61:
                DivideValue = 8000;
                return DivideValue;
            case 62:
                DivideValue = 10000;
                return DivideValue;
            case 63:
                DivideValue = 10000;
                return DivideValue;
            case 64:
                DivideValue = 11000;
                return DivideValue;
            case 65:
                DivideValue = 12000;
                return DivideValue;
            case 66:
                DivideValue = 13000;
                return DivideValue;
            case 67:
                DivideValue = 14000;
                return DivideValue;
            case 68:
                DivideValue = 15000;
                return DivideValue;
            case 69:
                DivideValue = 16000;
                return DivideValue;
            case 70:
            case 71:
            case 72:
            case 73:
            case 74:
            case 75:
            case 76:
            case 77:
            case 78:
            case 79:
            case 80:
            case 81:
            case 82:
            case 83:
            case 84:
            case 85:
            case 86:
            case 87:
            case 88:
            case 89:
            case 90:
            case 91:
            case 92:
            case 93:
            case 94:
            case 95:
            case 96:
            case 97:
            case 98:
            case 99:
            case 100:
                DivideValue = 25000;
                return DivideValue;
        }
        return 1;
    }

    public static double GoldRequirement(Integer Prestige) {
        double MultiplyValue;
        switch (Prestige) {
            case 1:
            case 2:
                MultiplyValue = .5;
                return Math.max(MultiplyValue / 2, MultiplyValue);
            case 3:
                MultiplyValue = 1;
                return Math.max(MultiplyValue / 2, MultiplyValue);
            case 4:
            case 5:
                MultiplyValue = 1.5;
                return Math.max(MultiplyValue / 2, MultiplyValue);
            case 6:
                MultiplyValue = 2;
                return Math.max(MultiplyValue / 2, MultiplyValue);
            case 7:
                MultiplyValue = 2.5;
                return Math.max(MultiplyValue / 2, MultiplyValue);
            case 8:
                MultiplyValue = 2.75;
                return Math.max(MultiplyValue / 2, MultiplyValue);
            case 9:
                MultiplyValue = 3.25;
                return Math.max(MultiplyValue / 2, MultiplyValue);
            case 10:
                MultiplyValue = 5;
                return Math.max(MultiplyValue / 2, MultiplyValue);
            case 11:
                MultiplyValue = 6;
                return Math.max(MultiplyValue / 2, MultiplyValue);
            case 12:
                MultiplyValue = 8;
                return Math.max(MultiplyValue / 2, MultiplyValue);
            case 13:
                MultiplyValue = 9;
                return Math.max(MultiplyValue / 2, MultiplyValue);
            case 14:
                MultiplyValue = 11;
                return Math.max(MultiplyValue / 2, MultiplyValue);
            case 15:
                MultiplyValue = 12;
                return Math.max(MultiplyValue / 2, MultiplyValue);
            case 16:
                MultiplyValue = 14;
                return Math.max(MultiplyValue / 2, MultiplyValue);
            case 17:
                MultiplyValue = 15;
                return Math.max(MultiplyValue / 2, MultiplyValue);
            case 18:
                MultiplyValue = 17;
                return Math.max(MultiplyValue / 2, MultiplyValue);
            case 19:
                MultiplyValue = 19;
                return Math.max(MultiplyValue / 2, MultiplyValue);
            case 20:
                MultiplyValue = 25;
                return Math.max(MultiplyValue / 2, MultiplyValue);
            case 21:
                MultiplyValue = 27;
                return Math.max(MultiplyValue / 2, MultiplyValue);
            case 22:
                MultiplyValue = 29;
                return Math.max(MultiplyValue / 2, MultiplyValue);
            case 23:
                MultiplyValue = 31;
                return Math.max(MultiplyValue / 2, MultiplyValue);
            case 24:
                MultiplyValue = 32;
                return Math.max(MultiplyValue / 2, MultiplyValue);
            case 25:
                MultiplyValue = 37;
                return Math.max(MultiplyValue / 2, MultiplyValue);
            case 26:
                MultiplyValue = 40;
                return Math.max(MultiplyValue / 2, MultiplyValue);
            case 27:
                MultiplyValue = 44;
                return Math.max(MultiplyValue / 2, MultiplyValue);
            case 28:
                MultiplyValue = 55;
                return Math.max(MultiplyValue / 2, MultiplyValue);
            case 29:
                MultiplyValue = 67;
                return Math.max(MultiplyValue / 2, MultiplyValue);
            case 30:
                MultiplyValue = 89;
                return Math.max(MultiplyValue / 2, MultiplyValue);
            case 31:
                MultiplyValue = 120;
                return Math.max(MultiplyValue / 2, MultiplyValue);
            case 32:
                MultiplyValue = 140;
                return Math.max(MultiplyValue / 2, MultiplyValue);
            case 34:
                MultiplyValue = 150;
                return Math.max(MultiplyValue / 2, MultiplyValue);
            case 35:
                MultiplyValue = 200;
                return Math.max(MultiplyValue / 2, MultiplyValue);
            case 36:
                MultiplyValue = 300;
                return Math.max(MultiplyValue / 2, MultiplyValue);
            case 37:
                MultiplyValue = 400;
                return Math.max(MultiplyValue / 2, MultiplyValue);
            case 38:
                MultiplyValue = 500;
                return Math.max(MultiplyValue / 2, MultiplyValue);
            case 39:
                MultiplyValue = 700;
                return Math.max(MultiplyValue / 2, MultiplyValue);
            case 40:
                MultiplyValue = 850;
                return Math.max(MultiplyValue / 2, MultiplyValue);
            case 41:
                MultiplyValue = 900;
                return Math.max(MultiplyValue / 2, MultiplyValue);
            case 42:
                MultiplyValue = 1000;
                return Math.max(MultiplyValue / 2, MultiplyValue);
            case 43:
                MultiplyValue = 1100;
                return Math.max(MultiplyValue / 2, MultiplyValue);
            case 44:
                MultiplyValue = 1200;
                return Math.max(MultiplyValue / 2, MultiplyValue);
            case 45:
                MultiplyValue = 1500;
                return Math.max(MultiplyValue / 2, MultiplyValue);
            case 46:
                MultiplyValue = 1600;
                return Math.max(MultiplyValue / 2, MultiplyValue);
            case 47:
                MultiplyValue = 1700;
                return Math.max(MultiplyValue / 2, MultiplyValue);
            case 48:
                MultiplyValue = 1800;
                return Math.max(MultiplyValue / 2, MultiplyValue);
            case 49:
                MultiplyValue = 2000;
                return Math.max(MultiplyValue / 2, MultiplyValue);
            case 50:
                MultiplyValue = 5000;
                return Math.max(MultiplyValue / 2, MultiplyValue);
            case 51:
                MultiplyValue = 5100;
                return Math.max(MultiplyValue / 2, MultiplyValue);
            case 52:
                MultiplyValue = 5300;
                return Math.max(MultiplyValue / 2, MultiplyValue);
            case 53:
                MultiplyValue = 5400;
                return Math.max(MultiplyValue / 2, MultiplyValue);
            case 54:
                MultiplyValue = 5500;
                return Math.max(MultiplyValue / 2, MultiplyValue);
            case 55:
                MultiplyValue = 5600;
                return Math.max(MultiplyValue / 2, MultiplyValue);
            case 56:
                MultiplyValue = 5700;
                return Math.max(MultiplyValue / 2, MultiplyValue);
            case 57:
                MultiplyValue = 5800;
                return Math.max(MultiplyValue / 2, MultiplyValue);
            case 58:
                MultiplyValue = 5900;
                return Math.max(MultiplyValue / 2, MultiplyValue);
            case 59:
                MultiplyValue = 6000;
                return Math.max(MultiplyValue / 2, MultiplyValue);
            case 60:
                MultiplyValue = 7000;
                return Math.max(MultiplyValue / 2, MultiplyValue);
            case 61:
                MultiplyValue = 8000;
                return Math.max(MultiplyValue / 2, MultiplyValue);
            case 62:
                MultiplyValue = 9000;
                return Math.max(MultiplyValue / 2, MultiplyValue);
            case 63:
                MultiplyValue = 10000;
                return Math.max(MultiplyValue / 2, MultiplyValue);
            case 64:
                MultiplyValue = 11000;
                return Math.max(MultiplyValue / 2, MultiplyValue);
            case 65:
                MultiplyValue = 12000;
                return Math.max(MultiplyValue / 2, MultiplyValue);
            case 66:
                MultiplyValue = 13000;
                return Math.max(MultiplyValue / 2, MultiplyValue);
            case 67:
                MultiplyValue = 14000;
                return Math.max(MultiplyValue / 2, MultiplyValue);
            case 68:
                MultiplyValue = 15000;
                return Math.max(MultiplyValue / 2, MultiplyValue);
            case 69:
                MultiplyValue = 16000;
                return Math.max(MultiplyValue / 2, MultiplyValue);
            case 70:
            case 71:
            case 72:
            case 73:
            case 74:
            case 75:
            case 76:
            case 77:
            case 78:
            case 79:
            case 80:
            case 81:
            case 82:
            case 83:
            case 84:
            case 85:
            case 86:
            case 87:
            case 88:
            case 89:
            case 90:
            case 91:
            case 92:
            case 93:
            case 94:
            case 95:
            case 96:
            case 97:
            case 98:
            case 99:
            case 100:
                MultiplyValue = 17000;
                return Math.max(MultiplyValue / 2, MultiplyValue);
        }
        return 1;
    }

}
