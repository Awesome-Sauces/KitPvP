package me.alpha.kitpvp;

import me.alpha.hunter.api.HunterAPI;
import me.alpha.hunter.bot.BotPlayer;
import me.alpha.kitpvp.Data.ClassInstances;
import me.alpha.kitpvp.Data.XpData;
import me.alpha.kitpvp.DataSave.DatabaseConnector;
import me.alpha.kitpvp.PitRemake.ItemStacks.enchants;
import me.alpha.kitpvp.PitRemake.ItemStacks.itemManager;
import me.alpha.kitpvp.PitRemake.Leaderboards.Leaderboard;
import me.alpha.kitpvp.PitRemake.Locations;
import me.alpha.kitpvp.PitRemake.MapType;
import me.alpha.kitpvp.PitRemake.PitCommands.Stash.StashCore;
import me.alpha.kitpvp.PitRemake.Startup.CreateVillagers;
import me.alpha.kitpvp.events.MainDamageEvent;
import me.alpha.kitpvp.utils.ColorUtil;
import me.alpha.kitpvp.utils.CommandRegistrar;
import me.alpha.kitpvp.utils.EventRegistrar;
import me.alpha.kitpvp.utils.Sounds;
import net.citizensnpcs.nms.v1_12_R1.util.CustomEntityRegistry;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import static me.alpha.hunter.api.HunterAPI.getRandomName;
import static me.alpha.kitpvp.PitRemake.Leaderboards.Leaderboard.RefreshBoard;
import static me.alpha.kitpvp.PitRemake.PitEvents.TwoTimesEvent.handleTwoEvent;
import static me.alpha.kitpvp.PitRemake.Scoreboard.ScoreboardCore.boardMap;
import static me.alpha.kitpvp.PitRemake.Scoreboard.ScoreboardCore.updateBoard;

public class KitPvP extends JavaPlugin {

    public static KitPvP INSTANCE;
    @Override
    public void onEnable() {

        INSTANCE = this;

        // Start Database
        //DatabaseConnector.startConnection();

        // Load Data
        ClassInstances.load();

        // Load items
        enchants.init();
        itemManager.init();

        // Register Commands
        CommandRegistrar.registerCommands();

        // Register Events
        EventRegistrar.registerEvents();

        // XP Amounts
        XpData.XpLevelCalculation();

        // Load NPC
        CreateVillagers.loadNPC();

        // Update Scoreboard
        Bukkit.getScheduler().scheduleSyncDelayedTask(this, new Runnable() {
            @Override
            public void run() {

                getServer().getScheduler().runTaskTimer(INSTANCE, () -> {
                    for(UUID uuid : boardMap.keySet()){
                        updateBoard(boardMap.get(uuid),Bukkit.getPlayer(uuid));
                    }
                },0,20);

                getServer().getScheduler().runTaskTimer(INSTANCE, () -> {
                    for(Player player : Bukkit.getOnlinePlayers()){
                        StashCore.reminderMessage(player);
                    }
                },0,6000);

                getServer().getScheduler().runTaskTimer(INSTANCE, () -> {
                    for(Player player : Bukkit.getOnlinePlayers()){
                        player.sendMessage(ColorUtil.colorCode("&a&lREMINDER! &7Remember to do &e/refresh &7to load your items into the new system"));
                        Sounds.BOOSTER_REMIND.play(player);
                    }
                },0,3000);

                getServer().getScheduler().runTaskTimer(INSTANCE, () -> {
                    for(Player player : Bukkit.getOnlinePlayers()){
                        player.sendMessage(ColorUtil.colorCode("&e&lNOTE! &7Found a bug? Report it on the Bloxicle forums!"));
                        Sounds.BOOSTER_REMIND.play(player);
                    }
                },0,7000);

                getServer().getScheduler().runTaskTimer(INSTANCE, () -> {
                    for(Player player : Bukkit.getOnlinePlayers()){
                        player.sendMessage(" ");
                        player.sendMessage(ColorUtil.colorCode("&c&l[BETTER PIT ANNOUNCEMENT]"));
                        player.sendMessage(ColorUtil.colorCode("&7Better Pit is a Pit Remake server dedicated to providing you with the best experience possible!" +
                                "&7Although there may be some bugs rest assured that you can report them on the discord! If you wish" +
                                "&7to support Better Pit you can purchase something at the store: &bstore.pitredux.net"));
                        player.sendMessage(" ");
                        player.sendMessage(ColorUtil.colorCode("&7Make sure to join the discord here: &bdiscord.gg/XyY2tUvT"));
                        player.sendMessage(" ");
                        Sounds.BOOSTER_REMIND.play(player);
                    }
                },0,14000);
            }
        }, 200L);

        // Refresh Leaderboard
        Bukkit.getScheduler().scheduleSyncRepeatingTask(this, new Runnable() {
            @Override
            public void run() {
                Bukkit.broadcastMessage(ColorUtil.colorCode("&c&lWARNING! &7The server may lag temporarily as leaderboard refreshes!"));
                for(Player player : Bukkit.getOnlinePlayers()) Sounds.WARNING_LOUD.play(player);
                RefreshBoard();
                handleTwoEvent();
            }
        }, 0L, 12000L); //0 Tick initial delay, 20 Tick (1 Second) between repeats


/*
        for (int i = 0; i < 30; i++) {
            HunterAPI.createHunterNon(Locations.getBotSpawnLocation(Bukkit.getWorld("world")), 0, false, (int) MapType.getMapType(Bukkit.getWorld("world")).getRingMid(Bukkit.getWorld("world")).getY());
            HunterAPI.createHunterNon(Locations.getBotSpawnLocation(Bukkit.getWorld("lobby2")), 0, false, (int) MapType.getMapType(Bukkit.getWorld("lobby2")).getRingMid(Bukkit.getWorld("lobby2")).getY());
            HunterAPI.createHunterNon(Locations.getBotSpawnLocation(Bukkit.getWorld("lobby")), 0, false, (int) MapType.getMapType(Bukkit.getWorld("lobby")).getRingMid(Bukkit.getWorld("lobby")).getY());

            //HunterAPI.createHunterNon(Locations.getBotSpawnLocation(Bukkit.getWorld("world")), 0, false);
        }

 */




    }

    @Override
    public void onDisable() {
        // Save Data
        ClassInstances.save();

        // Unload NPC
        CreateVillagers.unloadNPC();

        // Delete Leaderboard
        Leaderboard.delBoard();
    }


}
