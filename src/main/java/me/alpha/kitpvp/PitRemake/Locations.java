package me.alpha.kitpvp.PitRemake;

import org.bukkit.*;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static me.alpha.kitpvp.PitRemake.MysticWell.enchanters.FreshPants.percentChance;

public class Locations {

    /*
    private static Location cake = new Location(Bukkit.getWorld("world"), 57.5, 86, 66.5);


    public static Location getSpawnLocation(){
        return new Location(Bukkit.getWorld("world"), 0.5, 95, 10.5);
    }

    public static Location getBotSpawnLocation(){
        return new Location(Bukkit.getWorld("world"), 0.5, 95, 0.5);
    }

    public static Location getLeaderBoardLocation(){
        return new Location(Bukkit.getWorld("world"), 13.5, 119.5, 0.5);
    }

    public static int getSpawnProtection() {return 112;}

    */

    // Metor Map Locations
    /*
    public static Location getSpawnLocation(){
        return new Location(Bukkit.getWorld("world"), -2.5, 161, 4.5);
    }

    public static Location getEventNotifyLocation(){
        return new Location(Bukkit.getWorld("world"), -2.5, 164, 12.5);
    }

    public static Location getBotSpawnLocation(){
        return new Location(Bukkit.getWorld("world"), -2.5, 145, 12.5);
    }
    public static Location getLeaderBoardLocation(){
        return new Location(Bukkit.getWorld("world"), -14.5, 167, 12.5);
    }

    public static Location getEnderChestLocation(){return new Location(Bukkit.getWorld("world"), 8.5,163.5,7.5);}
    public static Location getMysticWellLocation(){return new Location(Bukkit.getWorld("world"), 9.5,164.5,12.5);}

    public static int getSpawnProtection() {return 156;}

     */

    // Coral Map Locations
    public static Location getSpawnLocation(World world){
        return MapType.getMapType(world).getSpawn(world);
    }

    public static Location getEventNotifyLocation(World world){
        return MapType.getMapType(world).getPlayPit(world).add(0, 2, 0);
    }
    public static Location getPlayPitLocation(World world){
        return MapType.getMapType(world).getPlayPit(world);
    }

    public static Location getBotSpawnLocation(World world){
        return MapType.getMapType(world).getBotSpawn(world);
    }
    public static Location getLeaderBoardLocation(World world){
        return MapType.getMapType(world).getLeaderBoard(world);
    }

    public static Location getEnderChestLocation(World world){
        return MapType.getMapType(world).getEnderChest(world);
    }

    public static Location getBetterPitLocation(World world){
        return MapType.getMapType(world).getPitLevel(world);
    }
    public static Location getMysticWellLocation(World world){
        return MapType.getMapType(world).getMysticWell(world);
    }

    // Put These guys back later
    public static Location getKingsQuestLocation(World world){
        return MapType.getMapType(world).getKingNPC(world);
    }

    public static Location getArmageddonLocation(World world){
        return MapType.getMapType(world).getBadNPC(world);
    }

    public static Location getArchAngelLocation(World world){
        return MapType.getMapType(world).getGoodNPC(world);
    }

    public static Location getMidLocation(World world){
        return MapType.getMapType(world).getRingMid(world);
    }

    public static int getSpawnProtection(World world) {
        return (int) MapType.getMapType(world).getBotSpawn(world).getY();
    }

    private static Location cake = new Location(Bukkit.getWorld("world"), 57.5, 91, 7.5);


    // Coral Map NPC
    public static Location perm_upgrades_loc = MapType.getMapType(Bukkit.getWorld("world")).getPermNPC(Bukkit.getWorld("world"));
    public static Location non_perm_upgrades_loc = MapType.getMapType(Bukkit.getWorld("world")).getNonPermNPC(Bukkit.getWorld("world"));

    public static Location leaderboard_npc_loc = MapType.getMapType(Bukkit.getWorld("world")).getStatsNPC(Bukkit.getWorld("world"));
    public static Location quest_npc_loc = MapType.getMapType(Bukkit.getWorld("world")).getQuestNPC(Bukkit.getWorld("world"));

    public static Location prestige_npc_loc = MapType.getMapType(Bukkit.getWorld("world")).getPrestigeNPC(Bukkit.getWorld("world"));

    public static Location lobby_perm_upgrades_loc = MapType.getMapType(Bukkit.getWorld("lobby")).getPermNPC(Bukkit.getWorld("lobby"));
    public static Location lobby_non_perm_upgrades_loc = MapType.getMapType(Bukkit.getWorld("lobby")).getNonPermNPC(Bukkit.getWorld("lobby"));

    public static Location lobby_leaderboard_npc_loc = MapType.getMapType(Bukkit.getWorld("lobby")).getStatsNPC(Bukkit.getWorld("lobby"));
    public static Location lobby_quest_npc_loc = MapType.getMapType(Bukkit.getWorld("lobby")).getQuestNPC(Bukkit.getWorld("lobby"));

    public static Location lobby_prestige_npc_loc = MapType.getMapType(Bukkit.getWorld("lobby")).getPrestigeNPC(Bukkit.getWorld("lobby"));

    public static Location lobby2_perm_upgrades_loc = MapType.getMapType(Bukkit.getWorld("lobby2")).getPermNPC(Bukkit.getWorld("lobby2"));
    public static Location lobby2_non_perm_upgrades_loc = MapType.getMapType(Bukkit.getWorld("lobby2")).getNonPermNPC(Bukkit.getWorld("lobby2"));

    public static Location lobby2_leaderboard_npc_loc = MapType.getMapType(Bukkit.getWorld("lobby2")).getStatsNPC(Bukkit.getWorld("lobby2"));
    public static Location lobby2_quest_npc_loc = MapType.getMapType(Bukkit.getWorld("lobby2")).getQuestNPC(Bukkit.getWorld("lobby2"));

    public static Location lobby2_prestige_npc_loc = MapType.getMapType(Bukkit.getWorld("lobby2")).getPrestigeNPC(Bukkit.getWorld("lobby2"));

    public static Location getCakeLocation(){
        return cake;
    }

    public static void changeCakeLocation(){
        Random rand = new Random(); //instance of random class
        int upperbound = 10;
        int int_random = rand.nextInt(upperbound);

        switch (int_random){
            case 0:
                cake.getBlock().setType(Material.AIR);
                cake = new Location(Bukkit.getWorld("world"), -40.5, 99, 33.5);
                Bukkit.broadcastMessage(ChatColor.LIGHT_PURPLE + "The cake has changed location!");
                getCakeLocation().getBlock().setType(Material.CAKE_BLOCK);
                break;
            case 1:
                cake.getBlock().setType(Material.AIR);
                cake = new Location(Bukkit.getWorld("world"), 18.5, 83, 19.5);
                Bukkit.broadcastMessage(ChatColor.LIGHT_PURPLE + "The cake has changed location!");
                getCakeLocation().getBlock().setType(Material.CAKE_BLOCK);
                break;
            case 2:
                cake.getBlock().setType(Material.AIR);
                cake = new Location(Bukkit.getWorld("world"), 20.5, 83, -20.5);
                Bukkit.broadcastMessage(ChatColor.LIGHT_PURPLE + "The cake has changed location!");
                getCakeLocation().getBlock().setType(Material.CAKE_BLOCK);
                break;
            case 3:
                cake.getBlock().setType(Material.AIR);
                cake = new Location(Bukkit.getWorld("world"), 53.5, 99, -55.5);
                Bukkit.broadcastMessage(ChatColor.LIGHT_PURPLE + "The cake has changed location!");
                getCakeLocation().getBlock().setType(Material.CAKE_BLOCK);
                break;
            case 4:
                cake.getBlock().setType(Material.AIR);
                cake = new Location(Bukkit.getWorld("world"), 9.5, 85, -93.5);
                Bukkit.broadcastMessage(ChatColor.LIGHT_PURPLE + "The cake has changed location!");
                getCakeLocation().getBlock().setType(Material.CAKE_BLOCK);
                break;
            case 5:
                cake.getBlock().setType(Material.AIR);
                cake = new Location(Bukkit.getWorld("world"), -35.5, 87, -65.5);
                Bukkit.broadcastMessage(ChatColor.LIGHT_PURPLE + "The cake has changed location!");
                getCakeLocation().getBlock().setType(Material.CAKE_BLOCK);
                break;
            case 6:
                cake.getBlock().setType(Material.AIR);
                cake = new Location(Bukkit.getWorld("world"), 2.5, 67, -189.5);
                Bukkit.broadcastMessage(ChatColor.LIGHT_PURPLE + "The cake has changed location!");
                getCakeLocation().getBlock().setType(Material.CAKE_BLOCK);
                break;
            case 7:
                cake.getBlock().setType(Material.AIR);
                cake = new Location(Bukkit.getWorld("world"), -99.5, 85, -4.5);
                Bukkit.broadcastMessage(ChatColor.LIGHT_PURPLE + "The cake has changed location!");
                getCakeLocation().getBlock().setType(Material.CAKE_BLOCK);
                break;
            case 8:
                cake.getBlock().setType(Material.AIR);
                cake = new Location(Bukkit.getWorld("world"), -62.5, 85, -1.5);
                Bukkit.broadcastMessage(ChatColor.LIGHT_PURPLE + "The cake has changed location!");
                getCakeLocation().getBlock().setType(Material.CAKE_BLOCK);
                break;
            case 9:
                cake.getBlock().setType(Material.AIR);
                cake = new Location(Bukkit.getWorld("world"), -69.5, 95, 77.5);
                Bukkit.broadcastMessage(ChatColor.LIGHT_PURPLE + "The cake has changed location!");
                getCakeLocation().getBlock().setType(Material.CAKE_BLOCK);
                break;
        }
    }

     //
}
