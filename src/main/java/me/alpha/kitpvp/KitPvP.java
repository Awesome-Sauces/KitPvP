package me.alpha.kitpvp;

import me.alpha.hunter.api.HunterAPI;

import me.alpha.kitpvp.Data.ClassInstances;
import me.alpha.kitpvp.Data.XpData;
import me.alpha.kitpvp.DataSave.Converter64;
import me.alpha.kitpvp.DataSave.DatabaseConnector;
import me.alpha.kitpvp.DataSave.PlayerData;
import me.alpha.kitpvp.PitRemake.ItemStacks.enchants;
import me.alpha.kitpvp.PitRemake.ItemStacks.itemManager;
import me.alpha.kitpvp.PitRemake.Leaderboards.Leaderboard;
import me.alpha.kitpvp.PitRemake.Locations;
import me.alpha.kitpvp.PitRemake.MapType;
import me.alpha.kitpvp.PitRemake.MysticWell.New.EnchantMechanic;
import me.alpha.kitpvp.PitRemake.PitCommands.Stash.StashCore;
import me.alpha.kitpvp.PitRemake.RenownShop.CookieMonster.MonsterHandler;
import me.alpha.kitpvp.PitRemake.RenownShop.New.RenownAbilities;
import me.alpha.kitpvp.PitRemake.Startup.CreateVillagers;
import me.alpha.kitpvp.events.MainDamageEvent;
import me.alpha.kitpvp.utils.*;
import net.citizensnpcs.nms.v1_12_R1.util.CustomEntityRegistry;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.lang.Runnable;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import static me.alpha.hunter.api.HunterAPI.getRandomName;
import static me.alpha.kitpvp.PitRemake.Leaderboards.Leaderboard.RefreshBoard;
import static me.alpha.kitpvp.PitRemake.PitEvents.TwoTimesEvent.handleTwoEvent;
import static me.alpha.kitpvp.PitRemake.Scoreboard.ScoreboardCore.boardMap;
import static me.alpha.kitpvp.PitRemake.Scoreboard.ScoreboardCore.updateBoard;
import static org.bukkit.Bukkit.getServer;

public class KitPvP extends JavaPlugin {

    public static KitPvP INSTANCE;
    @Override
    public void onEnable() {

        INSTANCE = this;

        // Start Database
        //DatabaseConnector.startConnection();

        // Auto register enchants
        EnchantMechanic.autoRegistry();

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

        // Register Enchants
        EnchantUtils.registerGlow();

        // Sewer Rat
        MonsterHandler.initialize();

        // Register Renown Upgrades
        RenownAbilities.autoRegistry();

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
            }
        }, 200L);

        // Refresh Leaderboard
        Bukkit.getScheduler().scheduleSyncRepeatingTask(this, new Runnable() {
            @Override
            public void run() {
                Bukkit.broadcastMessage(ColorUtil.colorCode("&c&lWARNING! &7The server may lag temporarily as leaderboard refreshes!"));
                for(Player player : Bukkit.getOnlinePlayers()) Sounds.WARNING_LOUD.play(player);
                try {
                    RefreshBoard();
                } catch (SQLException e) {
                    e.printStackTrace();
                    throw new RuntimeException(e);
                }
                handleTwoEvent();
            }
        }, 0L, 12000L); //0 Tick initial delay, 20 Tick (1 Second) between repeats

        Bukkit.getScheduler().scheduleSyncRepeatingTask(this, new Runnable() {
            @Override
            public void run() {
                Bukkit.broadcastMessage(ColorUtil.colorCode("&c&lWARNING! &7The server may lag temporarily as all online player data is being saved!"));
                for(Player player : Bukkit.getOnlinePlayers()) DatabaseConnector.savePlayer(player);

            }
        }, 0L, 36000L); //0 Tick initial delay, 20 Tick (1 Second) between repeats


        //HunterAPI.createHunterNon(Locations.getSpawnLocation(Bukkit.getWorld("lobby2")), 0, true, 1);

        for(World world : CreateVillagers.getWorlds()){
            if(MapType.getMapName(world).equals("PheonixMap")){
                for(Location location : MapType.getMapType(world).getBotRegions(world)){

                    for (int i = 0; i < 15; i++) {
                        //HunterAPI.createAdvancedHunter(null, location, 0, false);
                    }

                    //
                   // HunterAPI.createHunterNon(location, 0, true, 1);
                }
            }
        }

        /*
        for(World world : CreateVillagers.getWorlds()){
            if(MapType.getMapName(world).equals("PheonixMap")){
                for(Location location : MapType.getMapType(world).getBotRegions(world)){
                    for (int i = 0; i < 1; i++) {
                        HunterAPI.createHunterNon(location, 0, true, 1);
                    }
                }
            }
        }

         */

/*
        for (int i = 0; i < 20; i++) {
            HunterAPI.createHunterNon(Locations.getBotSpawnLocation(Bukkit.getWorld("world")), 0, false, (int) MapType.getMapType(Bukkit.getWorld("world")).getRingMid(Bukkit.getWorld("world")).getY());
            HunterAPI.createHunterNon(Locations.getBotSpawnLocation(Bukkit.getWorld("lobby2")), 0, false, (int) MapType.getMapType(Bukkit.getWorld("lobby2")).getRingMid(Bukkit.getWorld("lobby2")).getY());
            HunterAPI.createHunterNon(Locations.getBotSpawnLocation(Bukkit.getWorld("lobby")), 0, false, (int) MapType.getMapType(Bukkit.getWorld("lobby")).getRingMid(Bukkit.getWorld("lobby")).getY());

            //HunterAPI.createHunterNon(Locations.getBotSpawnLocation(Bukkit.getWorld("world")), 0, false);
        }

 */


    }

    @Override
    public void onDisable() {

        // Unload NPC
        CreateVillagers.unloadNPC();

        for(World world : Bukkit.getServer().getWorlds()){
            for(Entity entity : world.getEntities()){
                if(entity instanceof Item){
                    entity.remove();
                }
            }
        }

        // Delete Leaderboard
        Leaderboard.delBoard();

        // Save Data
        ClassInstances.save();

        for(Player player : Bukkit.getOnlinePlayers()){

            DatabaseConnector.savePlayer(player);

        }
    }


}
