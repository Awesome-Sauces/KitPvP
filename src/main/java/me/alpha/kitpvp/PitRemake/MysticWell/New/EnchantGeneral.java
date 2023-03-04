package me.alpha.kitpvp.PitRemake.MysticWell.New;

public enum EnchantGeneral {
    COMMON("&9", .15),
    UNCOMMON("&9", .10),
    RARE("&dRARE! &9", .05),
    EPIC("&5&lEPIC! &9", .0005),
    LEGENDARY("&6&lLEGENDARY! &9", .0001),
    NONE("&9", .01);

    private final String colorCode;
    private final double rollChance;

    EnchantGeneral(String colorCode, double rollChance) {
        this.colorCode = colorCode;
        this.rollChance = rollChance;
    }

    public String getColorCode() {
        return colorCode;
    }

    public double getRollChance(){
        return rollChance;
    }
}