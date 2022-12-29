package me.alpha.kitpvp.PitRemake;

import org.bukkit.*;

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

        if(world.getName().contains("NewMap")){
            return new Location(world, -1.5, 61, -15.5);
        }

        while(true){
            if(percentChance(.25)){
                return new Location(world, -2.5, 161, 3.5,0.5F,0.5F);
            }else if(percentChance(.25)){
                return new Location(world, 5.5, 161, 12.5,90,2.5F);
            }else if(percentChance(.25)){
                return new Location(world, 4.5, 161, 20.5,-45,1.5F);
            }else if(percentChance(.25)){
                return new Location(world, -9.5, 161, 18.5,40,0.5F);
            }
        }
    }

    public static Location getEventNotifyLocation(World world){
        return new Location(world, -2.5, 165, 12.5);
    }
    public static Location getPlayPitLocation(World world){
        return new Location(world, -2.5, 163, 12.5);
    }

    public static Location getBotSpawnLocation(World world){
        return new Location(world, -2.5, 145, 12.5);
    }
    public static Location getLeaderBoardLocation(World world){
        return new Location(world, -14.5, 167, 12.5);
    }

    public static Location getEnderChestLocation(World world){
        return new Location(world, 8.5, 164, 7.5);
    }

    public static Location getBetterPitLocation(World world){
        return new Location(world, 1.5, 164, 3.5);
    }
    public static Location getMysticWellLocation(World world){
        return new Location(world, 9.5, 165, 12.5);
    }

    // Put These guys back later
    public static Location getKingsQuestLocation(World world){
        return new Location(world, -10.5, 95, -5.5);
    }

    public static Location getArmageddonLocation(World world){
        return new Location(world, 13.5,95, -12.5);
    }

    public static Location getArchAngelLocation(World world){
        return new Location(world, 13.5, 95, 13.5);
    }

    public static int getSpawnProtection() {return 145;}

    /*public static Location getCakeLocation(){
        return cake;
    }

     */

    /*
    public static void changeCakeLocation(){
        Random rand = new Random(); //instance of random class
        int upperbound = 10;
        int int_random = rand.nextInt(upperbound);

        switch (int_random){
            case 0:
                cake.getBlock().setType(Material.AIR);
                cake = new Location(Bukkit.getWorld("world"), 57.5, 86, 66.5);
                Bukkit.broadcastMessage(ChatColor.LIGHT_PURPLE + "The cake has changed location!");
                getCakeLocation().getBlock().setType(Material.CAKE_BLOCK);
                break;
            case 1:
                cake.getBlock().setType(Material.AIR);
                cake = new Location(Bukkit.getWorld("world"), 6.5, 84, 113.5);
                Bukkit.broadcastMessage(ChatColor.LIGHT_PURPLE + "The cake has changed location!");
                getCakeLocation().getBlock().setType(Material.CAKE_BLOCK);
                break;
            case 2:
                cake.getBlock().setType(Material.AIR);
                cake = new Location(Bukkit.getWorld("world"), -59.5, 97, 105.5);
                Bukkit.broadcastMessage(ChatColor.LIGHT_PURPLE + "The cake has changed location!");
                getCakeLocation().getBlock().setType(Material.CAKE_BLOCK);
                break;
            case 3:
                cake.getBlock().setType(Material.AIR);
                cake = new Location(Bukkit.getWorld("world"), -105.5, 96, 23.5);
                Bukkit.broadcastMessage(ChatColor.LIGHT_PURPLE + "The cake has changed location!");
                getCakeLocation().getBlock().setType(Material.CAKE_BLOCK);
                break;
            case 4:
                cake.getBlock().setType(Material.AIR);
                cake = new Location(Bukkit.getWorld("world"), -64.5, 85, -63.5);
                Bukkit.broadcastMessage(ChatColor.LIGHT_PURPLE + "The cake has changed location!");
                getCakeLocation().getBlock().setType(Material.CAKE_BLOCK);
                break;
            case 5:
                cake.getBlock().setType(Material.AIR);
                cake = new Location(Bukkit.getWorld("world"), -5.5, 88, -74.5);
                Bukkit.broadcastMessage(ChatColor.LIGHT_PURPLE + "The cake has changed location!");
                getCakeLocation().getBlock().setType(Material.CAKE_BLOCK);
                break;
            case 6:
                cake.getBlock().setType(Material.AIR);
                cake = new Location(Bukkit.getWorld("world"), 66.5, 78, -53.5);
                Bukkit.broadcastMessage(ChatColor.LIGHT_PURPLE + "The cake has changed location!");
                getCakeLocation().getBlock().setType(Material.CAKE_BLOCK);
                break;
            case 7:
                cake.getBlock().setType(Material.AIR);
                cake = new Location(Bukkit.getWorld("world"), 95.5, 86, 36.5);
                Bukkit.broadcastMessage(ChatColor.LIGHT_PURPLE + "The cake has changed location!");
                getCakeLocation().getBlock().setType(Material.CAKE_BLOCK);
                break;
            case 8:
                cake.getBlock().setType(Material.AIR);
                cake = new Location(Bukkit.getWorld("world"), 92.5, 96, 6.5);
                Bukkit.broadcastMessage(ChatColor.LIGHT_PURPLE + "The cake has changed location!");
                getCakeLocation().getBlock().setType(Material.CAKE_BLOCK);
                break;
            case 9:
                cake.getBlock().setType(Material.AIR);
                cake = new Location(Bukkit.getWorld("world"), 21.5, 112, -69.5);
                Bukkit.broadcastMessage(ChatColor.LIGHT_PURPLE + "The cake has changed location!");
                getCakeLocation().getBlock().setType(Material.CAKE_BLOCK);
                break;
        }
    }

     */

    // Metor Map Cake locations


    private static Location cake = new Location(Bukkit.getWorld("world"), 57.5, 91, 7.5);

    // Meteor Map NPCS
    /*
    public static Location perm_upgrades_loc = new Location(Bukkit.getWorld("world"), -1.5, 161, 1.5);
    public static Location non_perm_upgrades_loc = new Location(Bukkit.getWorld("world"), -4.5, 161, 1.5);

    public static Location leaderboard_npc_loc = new Location(Bukkit.getWorld("world"), -12.5, 161, 8.5);
    public static Location quest_npc_loc = new Location(Bukkit.getWorld("world"), -12.5, 161, 16.5);

    public static Location prestige_npc_loc = new Location(Bukkit.getWorld("world"), -2.5, 162, 23.5);


     */

    // Coral Map NPC
    public static Location perm_upgrades_loc = new Location(Bukkit.getWorld("world"), -1.5, 161, 1.5, -0, 0);
    public static Location non_perm_upgrades_loc = new Location(Bukkit.getWorld("world"), -4.5, 161, 1.5, -0, 0);

    public static Location leaderboard_npc_loc = new Location(Bukkit.getWorld("world"), -12.5, 161, 8.5, -90, 0);
    public static Location quest_npc_loc = new Location(Bukkit.getWorld("world"), -12.5, 161, 16.5, -90, 0);

    public static Location prestige_npc_loc = new Location(Bukkit.getWorld("world"), -2.5, 162, 23.5, 175, -0);

    public static Location lobby_perm_upgrades_loc = new Location(Bukkit.getWorld("lobby"), -1.5, 161, 1.5, -0, 0);
    public static Location lobby_non_perm_upgrades_loc = new Location(Bukkit.getWorld("lobby"), -4.5, 161, 1.5, -0, 0);

    public static Location lobby_leaderboard_npc_loc = new Location(Bukkit.getWorld("lobby"), -12.5, 161, 8.5, -90, 0);
    public static Location lobby_quest_npc_loc = new Location(Bukkit.getWorld("lobby"), -12.5, 161, 16.5, -90, 0);

    public static Location lobby_prestige_npc_loc = new Location(Bukkit.getWorld("lobby"), -2.5, 162, 23.5, 175, -0);

    public static Location lobby2_perm_upgrades_loc = new Location(Bukkit.getWorld("lobby2"), -1.5, 161, 1.5, -0, 0);
    public static Location lobby2_non_perm_upgrades_loc = new Location(Bukkit.getWorld("lobby2"), -4.5, 161, 1.5, -0, 0);

    public static Location lobby2_leaderboard_npc_loc = new Location(Bukkit.getWorld("lobby2"), -12.5, 161, 8.5, -90, 0);
    public static Location lobby2_quest_npc_loc = new Location(Bukkit.getWorld("lobby2"), -12.5, 161, 16.5, -90, 0);

    public static Location lobby2_prestige_npc_loc = new Location(Bukkit.getWorld("lobby2"), -2.5, 162, 23.5, 175, -0);

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
