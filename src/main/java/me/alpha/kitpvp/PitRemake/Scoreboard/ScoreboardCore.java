package me.alpha.kitpvp.PitRemake.Scoreboard;

import me.alpha.kitpvp.ChatManager.LevelColor;
import me.alpha.kitpvp.ChatManager.PrestigeBracketColors;
import me.alpha.kitpvp.Data.ClassInstances;
import me.alpha.kitpvp.Data.GoldData;
import me.alpha.kitpvp.Data.XpData;
import me.alpha.kitpvp.KitPvP;
import me.alpha.kitpvp.Objects.ReduxPlayerObject.ReduxPlayer;
import me.alpha.kitpvp.Objects.ReduxPlayerObject.ReduxPlayerHandler;
import me.alpha.kitpvp.utils.ColorUtil;
import me.alpha.kitpvp.utils.IntegerHelper;
import me.alpha.kitpvp.utils.PacketScoreboard.FastBoard;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.inventivetalent.bossbar.BossBar;
import org.inventivetalent.bossbar.BossBarAPI;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

import static java.lang.Math.abs;

public class ScoreboardCore  implements Listener {

    public static Map<UUID, FastBoard> boardMap = new HashMap<>();

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();

        /*
        if (twoTimesEvent == 2) {
            BossBar bossBar = BossBarAPI.addBar(player,
                    new TextComponent(ColorUtil.colorCode("&d&lMINOR EVENT! &e2x in &e&lPit Area")),
                    BossBarAPI.Color.GREEN,
                    BossBarAPI.Style.NOTCHED_6,
                    1.0f);
        } else {
            BossBarAPI.removeAllBars(player);
        }

         */

        try {
            ReduxPlayerHandler.playerExists(event.getPlayer()).setSpeed(0);
        } catch (Exception ignored) {
        }

        FastBoard board = new FastBoard(player);
        board.updateTitle(ChatColor.YELLOW + ColorUtil.colorCode("&lTHE BETTER PIT"));
        boardMap.put(player.getUniqueId(), board);
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();

        BossBarAPI.removeAllBars(player);

        try {
            ReduxPlayerHandler.playerExists(event.getPlayer()).setSpeed(0);
        } catch (Exception ignored) {
        }

        FastBoard board = boardMap.remove(player.getUniqueId());

        if (board != null) {
            board.delete();
        }
    }

    private static String getMegaData(String uuid) {
        String select_mega = "";
        String mega_color = "";

        if (Objects.equals(ClassInstances.megaStreakData.getMegaStreak(uuid), "highlander")) {
            select_mega = "Highlander";
            mega_color = ChatColor.translateAlternateColorCodes('&', "&6");
        } else if (Objects.equals(ClassInstances.megaStreakData.getMegaStreak(uuid), "beastmode")) {
            select_mega = "Beastmode";
            mega_color = ChatColor.translateAlternateColorCodes('&', "&a");
        } else if (Objects.equals(ClassInstances.megaStreakData.getMegaStreak(uuid), "magnum")) {
            select_mega = "Magnum Opus";
            mega_color = ChatColor.translateAlternateColorCodes('&', "&e");
        } else if (Objects.equals(ClassInstances.megaStreakData.getMegaStreak(uuid), "uber")) {
            select_mega = "Uberstreak";
            mega_color = ChatColor.translateAlternateColorCodes('&', "&d");
        } else if (Objects.equals(ClassInstances.megaStreakData.getMegaStreak(uuid), "moon")) {
            select_mega = "To the Moon";
            mega_color = ChatColor.translateAlternateColorCodes('&', "&b");
        } else if (Objects.equals(ClassInstances.megaStreakData.getMegaStreak(uuid), "overdrive")) {
            select_mega = "Overdrive";
            mega_color = ChatColor.translateAlternateColorCodes('&', "&c");
        }

        return mega_color + select_mega;
    }

    private static int getMegaActiveData(String uuid) {
        if (Objects.equals(ClassInstances.megaStreakData.getMegaStreak(uuid), "highlander") ||
                Objects.equals(ClassInstances.megaStreakData.getMegaStreak(uuid), "beastmode") ||
                Objects.equals(ClassInstances.megaStreakData.getMegaStreak(uuid), "overdrive") ||
                Objects.equals(ClassInstances.megaStreakData.getMegaStreak(uuid), "magnum")) {
            return 50;
        } else if (Objects.equals(ClassInstances.megaStreakData.getMegaStreak(uuid), "uber") ||
                Objects.equals(ClassInstances.megaStreakData.getMegaStreak(uuid), "moon")) {
            return 100;
        } else {
            return 50;
        }
    }

    public static void updateBoard(FastBoard board, Player player) {

        if (player == null) return;

        if (board == null) {

            FastBoard boardy = new FastBoard(player);
            boardy.updateTitle(ChatColor.YELLOW + ColorUtil.colorCode("&lTHE BETTER PIT"));
            boardMap.put(player.getUniqueId(), boardy);

            return;
        }

        String uuid = String.valueOf(player.getUniqueId());
        ReduxPlayer reduxPlayer = ReduxPlayerHandler.playerExists(player);
        int STRENGTH = reduxPlayer.getStrengthTier();

        //Bukkit.broadcastMessage(String.valueOf(reduxPlayer.getSTRENGTH_TIMER()));
        GoldData.hasEconomy(uuid);

        DecimalFormat formatter = new DecimalFormat("#,###");

        int[] playerData = XpData.GetCurrentLevel(String.valueOf(player.getUniqueId()), ClassInstances.xpData.getXp(String.valueOf(player.getUniqueId())), ClassInstances.prestigeData.getPrestige(String.valueOf(player.getUniqueId())), player);
        int level = playerData[1];
        int neededXP = playerData[0];

        boolean MOON_ACTIVE = false;

        String MOON_STORAGE = "";

        if (ClassInstances.megaStreakData.getMegaStreak(uuid).equals("moon") && ClassInstances.streakData.getStreak(uuid) >= 101) {
            MOON_ACTIVE = true;
            ReduxPlayer player1 = ReduxPlayerHandler.playerExists(player);

            MOON_STORAGE = ColorUtil.colorCode("&fStored XP: &b" + formatter.format(player1.getMoonXP()));
        }

        String prestigeColor = PrestigeBracketColors.getBracketColor(player);
        String lobby = "M2C";

        if (player.getWorld().getName().equals("world")) {
            lobby = "M6B";
        } else if (player.getWorld().getName().equals("lobby")) {
            lobby = "M14E";
        }

        String version = ChatColor.GRAY + "v1.5.1 " + ChatColor.DARK_GRAY + lobby; // Pit Redux Version

        String spacer1 = " "; //blank space
        String spacer2 = "  "; //blank space
        String spacer3 = "   "; //blank space
        String spacer4 = "    "; //blank space
        String spacer5 = "     "; //blank space
        String spacer6 = "      "; //blank space
        String spacer7 = "       "; //blank space
        String spacer8 = "        "; //blank space
        String spacer9 = "         "; //blank space
        String spacer10 = "         "; //blank space

        String levelData = ChatColor.WHITE + "Level: " + prestigeColor + "[" + ChatColor.AQUA + LevelColor.getLevelColor(level) + level + prestigeColor + "]";
        String goldData = ChatColor.WHITE + "Gold: " + ChatColor.GOLD + formatter.format(GoldData.getEconomy(String.valueOf(player.getUniqueId()))) + "g";
        String streakData = ChatColor.WHITE + "Streak: " + ChatColor.GREEN + ClassInstances.streakData.getStreak(uuid);
        String ipData = ChatColor.YELLOW + "mc.pitredux.net";
        String prestigeData = ChatColor.WHITE + "Prestige: " + ChatColor.YELLOW + IntegerHelper.integerToRoman(ClassInstances.prestigeData.getPrestige(uuid));

        String strengthData;
        String xpData;
        String statusData;

        if (ClassInstances.streakData.getStreak(String.valueOf(player.getUniqueId())) >= getMegaActiveData(uuid)) {
            statusData = ChatColor.WHITE + "Status: " + ChatColor.RESET + getMegaData(uuid);
        } else if (KitPvP.combatTag.containsKey(String.valueOf(player.getUniqueId())) &&
                KitPvP.combatTag.get(String.valueOf(player.getUniqueId())) > System.currentTimeMillis()) {
            long timeleft = (KitPvP.combatTag.get(String.valueOf(player.getUniqueId())) - System.currentTimeMillis()) / 1000;
            statusData = ChatColor.WHITE + "Status: " + ChatColor.RED + "Fighting " + ChatColor.GRAY + "(" + timeleft + ")";
        } else {
            statusData = ChatColor.WHITE + "Status: " + ChatColor.GREEN + "Idling";
        }

        // If statement to determine needed xp position
        if (neededXP == 323232323) {
            xpData = ChatColor.WHITE + "XP: " + ChatColor.AQUA + "MAXED!";
        } else {
            xpData = ChatColor.WHITE + "Needed XP: " + ChatColor.AQUA + formatter.format(abs(neededXP));
        }


        if (STRENGTH <= 0) {
            strengthData = ChatColor.WHITE + "Strength: " + ChatColor.RED + "NONE";
        } else {
            strengthData = ChatColor.WHITE + "Strength: " + ChatColor.RED + IntegerHelper.integerToRoman(STRENGTH) + ChatColor.GRAY + " (" + String.valueOf(reduxPlayer.getSTRENGTH_TIMER()) + "s)";
        }


        if (MOON_ACTIVE) {
            board.updateLines(version,
                    spacer4,
                    prestigeData,
                    levelData,
                    xpData,
                    spacer3,
                    goldData,
                    spacer2,
                    statusData,
                    streakData,
                    MOON_STORAGE,
                    spacer1,
                    ipData);
        } else if (STRENGTH > 0 &&
                ClassInstances.streakData.getStreak(uuid) > 0 && ClassInstances.prestigeData.getPrestige(uuid) > 0) {
            board.updateLines(version,
                    spacer4,
                    prestigeData,
                    levelData,
                    xpData,
                    spacer3,
                    goldData,
                    spacer2,
                    statusData,
                    streakData,
                    strengthData,
                    spacer1,
                    ipData);
        } else if (STRENGTH > 0 &&
                ClassInstances.streakData.getStreak(uuid) > 0 && ClassInstances.prestigeData.getPrestige(uuid) == 0) {

            board.updateLines(version,
                    spacer4,
                    levelData,
                    xpData,
                    spacer3,
                    goldData,
                    spacer2,
                    statusData,
                    streakData,
                    strengthData,
                    spacer1,
                    ipData);
        } else if (
                ClassInstances.streakData.getStreak(uuid) > 0 && ClassInstances.prestigeData.getPrestige(uuid) > 0) {

            board.updateLines(version,
                    spacer4,
                    prestigeData,
                    levelData,
                    xpData,
                    spacer3,
                    goldData,
                    spacer2,
                    statusData,
                    streakData,
                    spacer1,
                    ipData);
        } else if (
                ClassInstances.streakData.getStreak(uuid) > 0 && ClassInstances.prestigeData.getPrestige(uuid) == 0) {

            board.updateLines(version,
                    spacer4,
                    levelData,
                    xpData,
                    spacer3,
                    goldData,
                    spacer2,
                    statusData,
                    streakData,
                    spacer1,
                    ipData);
        } else if (STRENGTH <= 0 &&
                ClassInstances.streakData.getStreak(uuid) <= 0 && ClassInstances.prestigeData.getPrestige(uuid) > 0) {
            board.updateLines(version,
                    spacer4,
                    prestigeData,
                    levelData,
                    xpData,
                    spacer3,
                    goldData,
                    spacer2,
                    statusData,
                    spacer1,
                    ipData);
        } else if (ClassInstances.prestigeData.getPrestige(uuid) <= 0) {
            board.updateLines(version,
                    spacer4,
                    levelData,
                    xpData,
                    spacer3,
                    goldData,
                    spacer2,
                    statusData,
                    spacer1,
                    ipData);
        } else {
            board.updateLines(version,
                    spacer4,
                    prestigeData,
                    levelData,
                    xpData,
                    spacer3,
                    goldData,
                    spacer2,
                    statusData,
                    spacer1,
                    ipData);
        }

    }

    public static void CreateScore(Player player) {
        updateBoard(boardMap.get(player.getUniqueId()), player);
    }
}