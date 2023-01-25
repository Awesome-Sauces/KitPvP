package me.alpha.kitpvp.Data;

import me.alpha.kitpvp.utils.DataStore;
import org.bukkit.entity.Player;

public class XpData extends DataStore {

    public XpData(String refID) {
        super(refID);
    }

    public void setXp(String uuid, int xp){
        setValue(uuid, xp);
    }

    public int getXp(String uuid){
        return (int) getValue(uuid, 0);
    }

    public void addXp(String uuid, int xp){
        addValue(uuid, xp);
    }

    public static void XpLevelCalculation(){
        // Total XP = 59355
        // Simple For loop
        // Levels 1-9 = 135 xp -> 180
        ClassInstances.XpAmounts.add(20);
        ClassInstances.XpAmounts.add(20);
        ClassInstances.XpAmounts.add(20);
        ClassInstances.XpAmounts.add(20);
        ClassInstances.XpAmounts.add(20);
        ClassInstances.XpAmounts.add(20);
        ClassInstances.XpAmounts.add(20);
        ClassInstances.XpAmounts.add(20);
        ClassInstances.XpAmounts.add(20);
        // Levels 10-19 = 270 xp -> 360
        ClassInstances.XpAmounts.add(40);
        ClassInstances.XpAmounts.add(40);
        ClassInstances.XpAmounts.add(40);
        ClassInstances.XpAmounts.add(40);
        ClassInstances.XpAmounts.add(40);
        ClassInstances.XpAmounts.add(40);
        ClassInstances.XpAmounts.add(40);
        ClassInstances.XpAmounts.add(40);
        ClassInstances.XpAmounts.add(40);
        ClassInstances.XpAmounts.add(40);
        // Levels 20-29 = 450 xp -> 585
        ClassInstances.XpAmounts.add(65);
        ClassInstances.XpAmounts.add(65);
        ClassInstances.XpAmounts.add(65);
        ClassInstances.XpAmounts.add(65);
        ClassInstances.XpAmounts.add(65);
        ClassInstances.XpAmounts.add(65);
        ClassInstances.XpAmounts.add(65);
        ClassInstances.XpAmounts.add(65);
        ClassInstances.XpAmounts.add(65);
        ClassInstances.XpAmounts.add(65);
        // Levels 30-39 = 675 xp
        ClassInstances.XpAmounts.add(95);
        ClassInstances.XpAmounts.add(95);
        ClassInstances.XpAmounts.add(95);
        ClassInstances.XpAmounts.add(95);
        ClassInstances.XpAmounts.add(95);
        ClassInstances.XpAmounts.add(95);
        ClassInstances.XpAmounts.add(95);
        ClassInstances.XpAmounts.add(95);
        ClassInstances.XpAmounts.add(95);
        ClassInstances.XpAmounts.add(95);
        // Levels 40-49 = 1125 xp
        ClassInstances.XpAmounts.add(190);
        ClassInstances.XpAmounts.add(190);
        ClassInstances.XpAmounts.add(190);
        ClassInstances.XpAmounts.add(190);
        ClassInstances.XpAmounts.add(190);
        ClassInstances.XpAmounts.add(190);
        ClassInstances.XpAmounts.add(190);
        ClassInstances.XpAmounts.add(190);
        ClassInstances.XpAmounts.add(190);
        ClassInstances.XpAmounts.add(190);
        // Levels 50-59 = 2700 xp
        ClassInstances.XpAmounts.add(300);
        ClassInstances.XpAmounts.add(300);
        ClassInstances.XpAmounts.add(300);
        ClassInstances.XpAmounts.add(300);
        ClassInstances.XpAmounts.add(300);
        ClassInstances.XpAmounts.add(300);
        ClassInstances.XpAmounts.add(300);
        ClassInstances.XpAmounts.add(300);
        ClassInstances.XpAmounts.add(300);
        ClassInstances.XpAmounts.add(300);
        // Levels 60-69 = 5400 xp
        ClassInstances.XpAmounts.add(600);
        ClassInstances.XpAmounts.add(600);
        ClassInstances.XpAmounts.add(600);
        ClassInstances.XpAmounts.add(600);
        ClassInstances.XpAmounts.add(600);
        ClassInstances.XpAmounts.add(600);
        ClassInstances.XpAmounts.add(600);
        ClassInstances.XpAmounts.add(600);
        ClassInstances.XpAmounts.add(600);
        ClassInstances.XpAmounts.add(600);
        // Levels 70-79 = 7200 xp
        ClassInstances.XpAmounts.add(800);
        ClassInstances.XpAmounts.add(800);
        ClassInstances.XpAmounts.add(800);
        ClassInstances.XpAmounts.add(800);
        ClassInstances.XpAmounts.add(800);
        ClassInstances.XpAmounts.add(800);
        ClassInstances.XpAmounts.add(800);
        ClassInstances.XpAmounts.add(800);
        ClassInstances.XpAmounts.add(800);
        ClassInstances.XpAmounts.add(800);
        // Levels 80-89 = 8100 xp
        ClassInstances.XpAmounts.add(900);
        ClassInstances.XpAmounts.add(900);
        ClassInstances.XpAmounts.add(900);
        ClassInstances.XpAmounts.add(900);
        ClassInstances.XpAmounts.add(900);
        ClassInstances.XpAmounts.add(900);
        ClassInstances.XpAmounts.add(900);
        ClassInstances.XpAmounts.add(900);
        ClassInstances.XpAmounts.add(900);
        ClassInstances.XpAmounts.add(900);
        // Levels 90-99 = 9000 xp
        ClassInstances.XpAmounts.add(1200);
        ClassInstances.XpAmounts.add(1200);
        ClassInstances.XpAmounts.add(1200);
        ClassInstances.XpAmounts.add(1200);
        ClassInstances.XpAmounts.add(1200);
        ClassInstances.XpAmounts.add(1200);
        ClassInstances.XpAmounts.add(1200);
        ClassInstances.XpAmounts.add(1200);
        ClassInstances.XpAmounts.add(1200);
        ClassInstances.XpAmounts.add(1200);
        // 100-109 = 10800 xp
        ClassInstances.XpAmounts.add(1300);
        ClassInstances.XpAmounts.add(1300);
        ClassInstances.XpAmounts.add(1300);
        ClassInstances.XpAmounts.add(1300);
        ClassInstances.XpAmounts.add(1300);
        ClassInstances.XpAmounts.add(1300);
        ClassInstances.XpAmounts.add(1300);
        ClassInstances.XpAmounts.add(1300);
        ClassInstances.XpAmounts.add(1300);
        ClassInstances.XpAmounts.add(1300);
        // 110 - 119 = 13500 xp
        ClassInstances.XpAmounts.add(1700);
        ClassInstances.XpAmounts.add(1700);
        ClassInstances.XpAmounts.add(1700);
        ClassInstances.XpAmounts.add(1700);
        ClassInstances.XpAmounts.add(1700);
        ClassInstances.XpAmounts.add(1700);
        ClassInstances.XpAmounts.add(1700);
        ClassInstances.XpAmounts.add(1700);
        ClassInstances.XpAmounts.add(1700);
        ClassInstances.XpAmounts.add(1700);
        ClassInstances.XpAmounts.add(1700);
        ClassInstances.XpAmounts.add(1700);

    }

    public static int[] GetCurrentLevel(String player, Integer PlayerXpAmount, Integer PlayerPrestige, Player players) {
        int CurrentXpMoment = 0;
        int current_level = 0;
        int xp_to_next_level = 0;
        for (int i = 0; i < ClassInstances.XpAmounts.size(); i++) {
            CurrentXpMoment += ClassInstances.XpAmounts.get(i) + (ClassInstances.XpAmounts.get(i) * PrestigeData.PrestigeXpAmount(PlayerPrestige));
            if (CurrentXpMoment >= PlayerXpAmount) {
                xp_to_next_level = PlayerXpAmount - CurrentXpMoment;

                current_level = Math.max(i - 1, 1);

                int[] returns = new int[2];
                returns[0] = xp_to_next_level;
                returns[1] = current_level;

                players.setLevel(current_level);
                return returns;
            }
        }

        int[] failedReturn = new int[2];
        failedReturn[0] = 323232323;
        failedReturn[1] = 120;

        return failedReturn;
    }

    public static int[] GetCurrentLevel(String player, Integer PlayerXpAmount, Integer PlayerPrestige) {
        int CurrentXpMoment = 0;
        int current_level = 0;
        int xp_to_next_level = 0;
        for (int i = 0; i < ClassInstances.XpAmounts.size(); i++) {
            CurrentXpMoment += ClassInstances.XpAmounts.get(i) + (ClassInstances.XpAmounts.get(i) * PrestigeData.PrestigeXpAmount(PlayerPrestige));
            if (CurrentXpMoment >= PlayerXpAmount) {
                xp_to_next_level = PlayerXpAmount - CurrentXpMoment;

                current_level = Math.max(i - 1, 1);

                int[] returns = new int[2];
                returns[0] = xp_to_next_level;
                returns[1] = current_level;

                return returns;
            }
        }

        int[] failedReturn = new int[2];
        failedReturn[0] = 323232323;
        failedReturn[1] = 120;

        return failedReturn;
    }

    public static int getLevelXP(Player player, int level, int PlayerPrestige) {
        int CurrentXpMoment = 0;
        int current_level = 0;
        int xp_to_next_level = 0;
        for (int i = 0; i < ClassInstances.XpAmounts.size(); i++) {

            current_level=i;

            CurrentXpMoment += ClassInstances.XpAmounts.get(i) + (ClassInstances.XpAmounts.get(i) * PrestigeData.PrestigeXpAmount(PlayerPrestige));

            if(current_level-1==level){
                return CurrentXpMoment;
            }
        }
        return 0;
    }


    public static int[] GetCurrentLevels(String player, Integer PlayerXpAmount, Integer PlayerPrestige) {
        int CurrentXpMoment = 0;
        int current_level = 0;
        int xp_to_next_level = 0;
        for (int i = 0; i < ClassInstances.XpAmounts.size(); i++) {
            CurrentXpMoment += ClassInstances.XpAmounts.get(i) + (ClassInstances.XpAmounts.get(i) * PrestigeData.PrestigeXpAmount(PlayerPrestige));
            if (CurrentXpMoment >= PlayerXpAmount) {
                xp_to_next_level = PlayerXpAmount - CurrentXpMoment;

                current_level = Math.max(i - 1, 1);

                int[] returns = new int[2];
                returns[0] = xp_to_next_level;
                returns[1] = current_level;

                return returns;
            }
        }

        int[] failedReturn = new int[2];
        failedReturn[0] = 323232323;
        failedReturn[1] = 120;

        return failedReturn;
    }

}
