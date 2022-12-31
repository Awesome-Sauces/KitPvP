package me.alpha.kitpvp.PitRemake.Boosters;

import me.alpha.hunter.api.HunterAPI;
import me.alpha.hunter.bot.BotPlayer;
import me.alpha.kitpvp.Data.ClassInstances;
import me.alpha.kitpvp.KitPvP;
import me.alpha.kitpvp.PitRemake.Locations;
import me.alpha.kitpvp.utils.Sounds;
import me.alpha.kitpvp.utils.advancedInventory;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import static me.alpha.kitpvp.utils.CitizensHelper.isNPC;
import static me.alpha.kitpvp.utils.ColorUtil.colorCode;
import static me.alpha.kitpvp.utils.advancedInventory.ItemMaker;

public class Booster {

    public static boolean xpActive = false;
    public static boolean goldActive = false;
    public static boolean botActive = false;

    public static ItemStack XpBoosterItem(String uuid){
        return ItemMaker(Material.EXP_BOTTLE, ChatColor.RED + "XP Booster",
                ChatColor.GRAY + "All players on the server gain\n" + ChatColor.AQUA +
                "2x XP " + ChatColor.GRAY + " and " + ChatColor.AQUA + "2x cap XP\n\n" +
                ChatColor.GRAY + "Active: " + ChatColor.YELLOW + xpActive + "!\n" +
                ChatColor.GRAY + "Use a booster to activate\n\n" + ChatColor.GRAY +
                "You have: " + ChatColor.YELLOW + ClassInstances.xpBoosterData.getBooster(uuid) + "\n\n" + ChatColor.YELLOW +
                "Click to activate booster!", 1, true);
    }

    public static ItemStack GoldBoosterItem(String uuid){
        return ItemMaker(Material.GOLD_INGOT, ChatColor.RED + "Gold Booster",
                ChatColor.GRAY + "All players on the server gain\n" + ChatColor.AQUA +
                        "2x gold\n\n" + ChatColor.GRAY + "Active: " + ChatColor.YELLOW + goldActive + "!\n" +
                        ChatColor.GRAY + "Use a booster to activate\n\n" + ChatColor.GRAY +
                        "You have: " + ChatColor.YELLOW + ClassInstances.goldBoosterData.getBooster(uuid) + "\n\n" + ChatColor.YELLOW +
                        "Click to activate booster!", 1, true);
    }

    public static ItemStack BotBoosterItem(String uuid){
        return ItemMaker(Material.IRON_SWORD, ChatColor.RED + "Bot Booster",
                ChatColor.GRAY + "Spawns in +15 more bots for\n" + ChatColor.GRAY +
                        "the whole server to enjoy\n\n" +
                        ChatColor.GRAY + "Active: " + ChatColor.YELLOW + botActive + "!\n" +
                        ChatColor.GRAY + "Use a booster to activate\n\n" + ChatColor.GRAY +
                        "You have: " + ChatColor.YELLOW + ClassInstances.botBoosterData.getBooster(uuid) + "\n\n" + ChatColor.YELLOW +
                        "Click to activate booster!", 1, true);
    }

    public static Inventory getBoosterGUI(Player player){
        Inventory gui = advancedInventory.inv(player, 27, ChatColor.GRAY + "Boosters");

        ItemStack base_glass = advancedInventory.cGlass();
        ItemStack bot = BotBoosterItem(String.valueOf(player.getUniqueId()));
        ItemStack gold = GoldBoosterItem(String.valueOf(player.getUniqueId()));
        ItemStack xp = XpBoosterItem(String.valueOf(player.getUniqueId()));

        for (int i = 0; i < 10; i++) {
            advancedInventory.addInv(gui, base_glass, i, 1, false);
            advancedInventory.addInv(gui, base_glass, i, 2, false);
            advancedInventory.addInv(gui, base_glass, i, 3, false);
        }

        advancedInventory.addInv(gui, xp, 3, 2, false);
        advancedInventory.addInv(gui, bot, 5, 2, false);
        advancedInventory.addInv(gui, gold, 7, 2, false);

        return gui;
    }

    public static void purchaseBoosterCommand(String type, String uuid, int amount){
        switch (type){
            case "GOLD":
                ClassInstances.goldBoosterData.addBooster(uuid, amount);
                break;
            case "BOT":
                ClassInstances.botBoosterData.addBooster(uuid, amount);
                break;
            default:
                ClassInstances.xpBoosterData.addBooster(uuid, amount);
                break;
        }
    }

    public static void constantBoosterNotify(){
        new BukkitRunnable() {

            @Override
            public void run() {
                if(goldActive){
                    for (Player player : Bukkit.getOnlinePlayers()){
                        if(!isNPC(player)){
                            Sounds.BOOSTER_REMIND.play(player.getLocation(), 1);
                        }
                    }

                    Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', "&e&lBOOSTER! &7There is currently an " +
                            "&7active &e&l2x &6gold &7booster!"));

                }

                if(xpActive){
                    for (Player player : Bukkit.getOnlinePlayers()){
                        if(!isNPC(player)){
                            Sounds.BOOSTER_REMIND.play(player.getLocation(), 1);
                        }
                    }

                    Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', "&e&lBOOSTER! &7There is currently an " +
                            "&7active &e&l2x &bxp &7booster!"));

                }

                if(botActive){
                    for (Player player : Bukkit.getOnlinePlayers()){
                        if(!isNPC(player)){
                            Sounds.BOOSTER_REMIND.play(player.getLocation(), 1);
                        }
                    }

                    Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', "&e&lBOOSTER! &7There is currently an " +
                            "&7active &e&l+30 &cbot &7booster!"));

                }

            }
        }.runTaskTimer(KitPvP.INSTANCE, 3600, 3600);
    }

    public static boolean activateBooster(Player player, String booster){
        String uuid = String.valueOf(player.getUniqueId());

        switch (booster){
            case "GOLD":
                if(ClassInstances.goldBoosterData.getBooster(uuid) <= 0 || goldActive){return true;}

                ClassInstances.goldBoosterData.subtractBooster(uuid, 1);

                goldActive = true;

                Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', "&e&lBOOSTER! &7There is currently an " +
                        "&7active &e&l2x &6gold &7booster!"));

                Bukkit.getScheduler().scheduleSyncDelayedTask(KitPvP.INSTANCE, new Runnable() {
                    @Override
                    public void run() {
                        goldActive = false;

                        Bukkit.broadcastMessage(colorCode("&e&lBOOSTER &7ended, all things will now return to normal"));
                    }
                }, 72000L);

                return false;
            case "BOT":
                if(ClassInstances.botBoosterData.getBooster(uuid) <= 0 || botActive){return true;}

                ClassInstances.botBoosterData.subtractBooster(uuid, 1);

                botActive = true;

                Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', "&e&lBOOSTER! &7There is currently an " +
                        "&7active &e&l+30 &cbot &7booster!"));

                for (int i = 0; i < 15; i++) {
                    HunterAPI.createHunterNon(Locations.getBotSpawnLocation(Bukkit.getWorld("world")), 3600, false);
                    HunterAPI.createHunterNon(Locations.getBotSpawnLocation(Bukkit.getWorld("lobby2")), 3600, false);
                    HunterAPI.createHunterNon(Locations.getBotSpawnLocation(Bukkit.getWorld("lobby")), 3600, false);
                }

                Bukkit.getScheduler().scheduleSyncDelayedTask(KitPvP.INSTANCE, new Runnable() {
                    @Override
                    public void run() {
                        botActive = false;

                        Bukkit.broadcastMessage(colorCode("&e&lBOOSTER &7ended, all things will now return to normal"));
                    }
                }, 72000L);

                return false;
            default:
                if(ClassInstances.xpBoosterData.getBooster(uuid) <= 0 || xpActive){return true;}

                ClassInstances.xpBoosterData.subtractBooster(uuid,1);

                xpActive = true;

                Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', "&e&lBOOSTER! &7There is currently an " +
                        "&7active &e&l2x &bxp &7booster!"));

                Bukkit.getScheduler().scheduleSyncDelayedTask(KitPvP.INSTANCE, new Runnable() {
                    @Override
                    public void run() {
                        xpActive = false;

                        Bukkit.broadcastMessage(colorCode("&e&lBOOSTER &7ended, all things will now return to normal"));
                    }
                }, 72000L);

                return false;

        }
    }

}
