package me.alpha.kitpvp.Data;

import me.alpha.kitpvp.KitPvP;
import me.alpha.kitpvp.PitRemake.Perks.data.PerkSlotFour;
import me.alpha.kitpvp.PitRemake.Perks.data.PerkSlotOne;
import me.alpha.kitpvp.PitRemake.Perks.data.PerkSlotThree;
import me.alpha.kitpvp.PitRemake.Perks.data.PerkSlotTwo;

import java.util.ArrayList;
import java.util.List;

public class ClassInstances {
    public static List<Integer> XpAmounts = new ArrayList<Integer>();

    // Save
    public static GoldRequirementData goldRequirementData = new GoldRequirementData("goldreq");
    public static PrestigeData prestigeData = new PrestigeData("prestige");
    public static XpData xpData = new XpData("exp");
    public static MegaStreakData megaStreakData = new MegaStreakData("megastreakData");
    public static PerkSlotOne perkSlotOne = new PerkSlotOne("perkone");
    public static PerkSlotTwo perkSlotTwo = new PerkSlotTwo("perktwo");
    public static PerkSlotThree perkSlotThree = new PerkSlotThree("perkthree");
    public static PerkSlotFour perkSlotFour = new PerkSlotFour("perkfour");


    // Don't save
    public static StreakData streakData = new StreakData("DON'T SAVE");

    public static void save(){
        goldRequirementData.saveHashMap();
        prestigeData.saveHashMap();
        xpData.saveHashMap();
        megaStreakData.saveHashMap();

        perkSlotOne.saveHashMap();
        perkSlotTwo.saveHashMap();
        perkSlotThree.saveHashMap();
        perkSlotFour.saveHashMap();
    }

    public static void load(){
        goldRequirementData.loadHashMap();
        prestigeData.loadHashMap();
        xpData.loadHashMap();

        megaStreakData.loadHashMap(true);
        perkSlotOne.loadHashMap(true);
        perkSlotTwo.loadHashMap(true);
        perkSlotThree.loadHashMap(true);
        perkSlotFour.loadHashMap(true);
    }

}
