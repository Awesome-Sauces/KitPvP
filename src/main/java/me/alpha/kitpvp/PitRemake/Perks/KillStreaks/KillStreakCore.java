package me.alpha.kitpvp.PitRemake.Perks.KillStreaks;

import java.util.ArrayList;
import java.util.List;

public class KillStreakCore {
    public static List<String> getPerks(){
        List<String> perks = new ArrayList<>();

        // 3 kills
        perks.add("explicious");
        perks.add("rr");
        perks.add("khanate");
        // 5 kills
        perks.add("toughSkin");
        perks.add("rush");
        perks.add("haste");
        // 7 kills
        perks.add("feast");
        perks.add("counterStrike");
        perks.add("nanoFactory");
        // 10 Kills
        perks.add("aura");
        perks.add("iceCube");
        // 25 kills
        perks.add("monster");
        perks.add("spongeSteve");
        perks.add("apostle");

        return perks;
    }
}
