package me.alpha.kitpvp.PitRemake.PitCommands.View;

import com.mojang.authlib.GameProfile;
import me.alpha.kitpvp.ChatManager.ChatManager;
import me.alpha.kitpvp.ChatManager.RankColor;
import me.alpha.kitpvp.Data.ClassInstances;
import me.alpha.kitpvp.Data.GoldData;
import me.alpha.kitpvp.utils.advancedInventory;
import net.minecraft.server.v1_8_R3.EntityPlayer;
import net.minecraft.server.v1_8_R3.MinecraftServer;
import net.minecraft.server.v1_8_R3.PlayerInteractManager;
import net.minecraft.server.v1_8_R3.WorldServer;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.craftbukkit.v1_8_R3.CraftServer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import static me.alpha.kitpvp.utils.ColorUtil.colorCode;
import static me.alpha.kitpvp.utils.advancedInventory.DirtMaker;
import static me.alpha.kitpvp.utils.advancedInventory.ItemMaker;

public class ViewCore implements Listener {

    public static ItemStack getTradeRequest(){
        return ItemMaker(Material.GOLD_INGOT, ChatColor.GOLD + "Send Trade Request",
                colorCode("&6Big business.\n\n&eClick to send!"), 1, true);
    }

    public static ItemStack getDirtyPerkItem(String uuid, int slot){
        return DirtMaker((short) 2, ChatColor.GREEN + "Perk Slot #" + String.valueOf(slot),
                ChatColor.YELLOW + "Dirty");
    }

    public static ItemStack getVampirePerkItem(String uuid, int slot){
        return ItemMaker(Material.FERMENTED_SPIDER_EYE, ChatColor.GREEN + "Perk Slot #" + String.valueOf(slot),
                        ChatColor.YELLOW + "Vampire", 1, true);
    }

    public static ItemStack getStrengthPerkItem(String uuid, int slot){
        return ItemMaker(Material.REDSTONE, ChatColor.GREEN + "Perk Slot #" + String.valueOf(slot),
                ChatColor.YELLOW + "Strength-Chaining", 1, true);
    }

    public static ItemStack getGladiatorPerkItem(String uuid, int slot){
        return ItemMaker(Material.BONE, ChatColor.GREEN + "Perk Slot #" + String.valueOf(slot),
                ChatColor.YELLOW + "Gladiator", 1, true);
    }

    public static ItemStack getInventoryItem(){
        return ItemMaker(Material.CHEST, ChatColor.GREEN + "Inventory",
                ChatColor.GRAY + "Check out this player's\n" + ChatColor.GRAY + "inventory.\n\n" +
                ChatColor.YELLOW + "Click to open!", 1, true);
    }

    public static ItemStack getEnderChestItem(){
        return ItemMaker(Material.ENDER_CHEST, ChatColor.DARK_PURPLE + "Ender Chest",
                ChatColor.GRAY + "Snoop around more of this\n" + ChatColor.GRAY + "player's belongings.\n\n" +
                        ChatColor.YELLOW + "Click to open!", 1, true);
    }


    public static ItemStack getPassivesItem(String uuid){
        return ItemMaker(Material.CAKE, ChatColor.GREEN + "Passives",
                ChatColor.GRAY + "XP Boost: " + ChatColor.RED + "Locked\n" +
                        ChatColor.GRAY + "Gold Boost: " + ChatColor.RED + "Locked\n" +
                        ChatColor.GRAY + "Melee Damage: " + ChatColor.RED + "Locked\n" +
                        ChatColor.GRAY + "Bow Damage: " + ChatColor.RED + "Locked\n" +
                        ChatColor.GRAY + "Damage Reduction: " + ChatColor.RED + "Locked\n" +
                        ChatColor.GRAY + "Build Battler: " + ChatColor.RED + "Locked\n" +
                        ChatColor.GRAY + "El Gato: " + ChatColor.RED + "Locked\n"
                , 1, true);
    }

    public static ItemStack getNameTagItem(Player player, String uuid, boolean online){
        DecimalFormat formatter = new DecimalFormat("#,###");

        ChatColor color = ChatColor.RED;

        String onlinestatus = color + "False";

        if(online) {
            color = ChatColor.GREEN;
            onlinestatus = color + "True";
        }

        GoldData.hasEconomy(uuid);

        return ItemMaker(Material.NAME_TAG, ChatManager.getLevelText(player) + RankColor.getNameColor(player) + player.getDisplayName(),
                ChatColor.GRAY + "Gold: " + ChatColor.GOLD + formatter.format(GoldData.getEconomy(player.getDisplayName(), true)) + "\n" +
                        ChatColor.GRAY + "Total XP: " + ChatColor.AQUA + formatter.format(ClassInstances.xpData.getXp(uuid)) + ChatColor.AQUA + " XP\n\n" +
                        ChatColor.GRAY + "Online: " + color + onlinestatus + "\n\n" +
                        ChatColor.GRAY + "Renown: " + ChatColor.YELLOW + formatter.format(ClassInstances.renownData.getRenown(uuid))
                , 1, true);
    }

    public static ItemStack getKillStreaksItem(Material material, String name){
        return ItemMaker(material, ChatColor.GREEN + "Killstreaks",
                ChatColor.GRAY + "Megastreak: " + name
                , 1, true);
    }

    public static ItemStack getPlayerHead(String player, boolean online){
        ItemStack skull = new ItemStack(Material.SKULL_ITEM,1, (byte) 3);
        SkullMeta skullMeta = (SkullMeta) skull.getItemMeta();

        skullMeta.setDisplayName(colorCode("&b" + player));

        List<String> lore = new ArrayList<>();

        lore.add(colorCode("&7Hypixel level: &669"));
        lore.add(colorCode("&7Achievement Points: &e4,200"));
        lore.add(colorCode("&7Guild: &bFearTheYutes"));
        lore.add("");

        if(!online){
            lore.add(colorCode("&aCurrently online!"));
        }else{
            lore.add(colorCode("&cCurrently offline!"));
        }

        skullMeta.setLore(lore);
        skullMeta.setOwner(player);
        skull.setItemMeta(skullMeta);


        return skull;
    }


    public static Inventory getViewInventory(Player main, Player player, String name, boolean online){
        String uuid = String.valueOf(player.getUniqueId());
        Inventory gui = advancedInventory.inv(main, 45, ChatColor.GRAY + ChatColor.stripColor(player.getDisplayName()) + ChatColor.GRAY + "'s " + ChatColor.GRAY + "Profile Viewer");
        ItemStack base_glass = advancedInventory.cGlass();

        for (int i = 0; i < 10; i++) {
            advancedInventory.addInv(gui, base_glass, i, 1, false);
            advancedInventory.addInv(gui, base_glass, i, 2, false);
            advancedInventory.addInv(gui, base_glass, i, 3, false);
            advancedInventory.addInv(gui, base_glass, i, 4, false);
            advancedInventory.addInv(gui, base_glass, i, 5, false);
        }


        if (player.getInventory().getHelmet() != null) advancedInventory.addInv(gui, player.getInventory().getHelmet(), 1, 1, false);
        if(player.getInventory().getChestplate() != null) advancedInventory.addInv(gui, player.getInventory().getChestplate(), 1, 2, false);
        if(player.getInventory().getLeggings() != null) advancedInventory.addInv(gui, player.getInventory().getLeggings(), 1, 3, false);
        if(player.getInventory().getBoots() != null) advancedInventory.addInv(gui, player.getInventory().getBoots(), 1, 4, false);

        Material megastreak = Material.BLAZE_POWDER;
        String megastreakName = "&aOverdrive";

        if(ClassInstances.megaStreakData.getMegaStreak(uuid).equals("uber")){
            megastreak = Material.GOLD_SWORD;
            megastreakName = "&aUberstreak";
        }else if(ClassInstances.megaStreakData.getMegaStreak(uuid).equals("highlander")){
            megastreak = Material.GOLD_BOOTS;
            megastreakName = "&aHighlander";
        }else if(ClassInstances.megaStreakData.getMegaStreak(uuid).equals("beastmode")){
            megastreak = Material.DIAMOND_HELMET;
            megastreakName = "&aBeastmode";
        }else if(ClassInstances.megaStreakData.getMegaStreak(uuid).equals("moon")){
            megastreak = Material.ENDER_STONE;
            megastreakName = "&aTo the Moon";
        }

        String rank;
        String namecolor = String.valueOf(ChatColor.GRAY);
        if (player.isOp()){
            rank = ChatColor.RED + "[OWNER] ";
            namecolor = String.valueOf(ChatColor.RED);
        }else if(player.hasPermission("NARDUPE")){
            rank = ChatColor.RED + "[OWNER"  + ChatColor.RED + "] ";
            namecolor = String.valueOf(ChatColor.RED);
        }else if(player.hasPermission("PETER")){
            rank = ChatColor.translateAlternateColorCodes('&', "&3[&4You&atub&1er &bC&2r&9a&cc&6k&4e&ed] ");
            namecolor = String.valueOf(ChatColor.DARK_AQUA);
        }else if(player.hasPermission("LEFT")){
            rank = ChatColor.YELLOW + "[HUNT] ";
            namecolor = String.valueOf(ChatColor.YELLOW);
        }else if(player.hasPermission("MVP++")){
            rank = ChatColor.GOLD + "[MVP" + ChatColor.BLACK + "++" + ChatColor.GOLD + "] ";
            namecolor = String.valueOf(ChatColor.GOLD);
        }else if(player.hasPermission("VIP")){
            rank = ChatColor.GREEN + "[VIP] ";
            namecolor = String.valueOf(ChatColor.GREEN);
        }else if(player.hasPermission("MVP+")){
            rank = ChatColor.AQUA + "[MVP" + ChatColor.RED + "+" + ChatColor.AQUA + "] ";
            namecolor = String.valueOf(ChatColor.AQUA);
        }else if(player.hasPermission("MVP")){
            rank = ChatColor.AQUA + "[MVP] ";
            namecolor = String.valueOf(ChatColor.AQUA);
        }else if(player.hasPermission("VIP+")){
            rank = ChatColor.GREEN + "[VIP" + ChatColor.WHITE + "+" + ChatColor.GREEN + "] ";
            namecolor = String.valueOf(ChatColor.GREEN);
        }else if(player.hasPermission("VIP")){
            rank = ChatColor.GREEN + "[VIP] ";
            namecolor = String.valueOf(ChatColor.GREEN);
        }else if(player.hasPermission("HELPER")){
            rank = ChatColor.BLUE + "[HELPER] ";
            namecolor = String.valueOf(ChatColor.BLUE);
        }else if(player.hasPermission("MOD")){
            rank = ChatColor.DARK_GREEN + "[MODERATOR] ";
            namecolor = String.valueOf(ChatColor.GREEN);
        }else if(player.hasPermission("ADMIN")){
            rank = ChatColor.RED + "[ADMIN] ";
            namecolor = String.valueOf(ChatColor.RED);
        }else if(player.hasPermission("YOUTUBE")){
            rank = ChatColor.RED + "[" + ChatColor.WHITE + "YOUTUBE" + ChatColor.RED + "] ";
            namecolor = String.valueOf(ChatColor.RED);
        }else if(player.hasPermission("SALMON")){
            rank = ChatColor.translateAlternateColorCodes('&', "&4[&fS&4A&fL&4M&fO&4N&f]&4+&f+ ");
            namecolor = String.valueOf(ChatColor.RED);
        }else{
            rank = "";
        }

        ItemStack vampire = getVampirePerkItem(uuid, 1);
        ItemStack strength = getStrengthPerkItem(uuid, 2);
        ItemStack gladiator = getGladiatorPerkItem(uuid, 3);
        ItemStack dirty = getDirtyPerkItem(uuid, 4);

        advancedInventory.addInv(gui, vampire, 5, 2, false);
        advancedInventory.addInv(gui, strength, 6, 2, false);
        advancedInventory.addInv(gui, gladiator, 7, 2, false);
        advancedInventory.addInv(gui, dirty, 8, 2, false);

        advancedInventory.addInv(gui, getPlayerHead(rank+namecolor+player.getDisplayName(), online), 3, 2, false);
        advancedInventory.addInv(gui, getNameTagItem(player, uuid, online), 3, 3, false);

        advancedInventory.addInv(gui, getPassivesItem(uuid), 6, 3, false);
        if(online &&
        main.getWorld().equals(player.getWorld())) advancedInventory.addInv(gui, getTradeRequest(), 6, 4, false);
        advancedInventory.addInv(gui, getKillStreaksItem(megastreak, colorCode(megastreakName)), 7, 3, false);

        advancedInventory.addInv(gui, getInventoryItem(), 8, 3, false);
        advancedInventory.addInv(gui, getEnderChestItem(), 9, 3, false);

        ItemStack item1 = player.getInventory().getItem(0); // First slot of the hotbar
        ItemStack item2 = player.getInventory().getItem(1); // First slot of the hotbar
        ItemStack item3 = player.getInventory().getItem(2); // First slot of the hotbar
        ItemStack item4 = player.getInventory().getItem(3); // First slot of the hotbar
        ItemStack item5 = player.getInventory().getItem(4); // First slot of the hotbar
        ItemStack item6 = player.getInventory().getItem(5); // First slot of the hotbar
        ItemStack item7 = player.getInventory().getItem(6); // First slot of the hotbar
        ItemStack item8 = player.getInventory().getItem(7); // First slot of the hotbar
        ItemStack item9 = player.getInventory().getItem(8); // Last slot of the hotbar

        if(item1 != null) advancedInventory.addInv(gui, item1, 1, 5, false);
        if(item2 != null) advancedInventory.addInv(gui, item2, 2, 5, false);
        if(item3 != null) advancedInventory.addInv(gui, item3, 3, 5, false);
        if(item4 != null) advancedInventory.addInv(gui, item4, 4, 5, false);
        if(item5 != null) advancedInventory.addInv(gui, item5, 5, 5, false);
        if(item6 != null) advancedInventory.addInv(gui, item6, 6, 5, false);
        if(item7 != null) advancedInventory.addInv(gui, item7, 7, 5, false);
        if(item8 != null) advancedInventory.addInv(gui, item8, 8, 5, false);
        if(item9 != null) advancedInventory.addInv(gui, item9, 9, 5, false);

        return gui;
    }

    @EventHandler
    public void HandleClickEvent(InventoryClickEvent event){
        if(event.getClickedInventory()==null &&
        event.getClickedInventory().getTitle() == null &&
        !event.getClickedInventory().getTitle().contains(ChatColor.GRAY + "Profile Viewer")) return;

        String playerName = ChatColor.stripColor((event.getClickedInventory().getTitle().replaceAll(ChatColor.GRAY  + "Profile Viewer", "")).replaceAll(ChatColor.GRAY + "'s ", ""));

        if(event.getCurrentItem().getType().equals(Material.CHEST)){
            Player tempPlayer = Bukkit.getPlayer(playerName);
            OfflinePlayer offline;

            if(tempPlayer==null){
                offline = Bukkit.getOfflinePlayer(Bukkit.getOfflinePlayer(playerName).getUniqueId());
                tempPlayer = ViewCore.loadPlayer(offline);
            }
            if(tempPlayer!=null) {
                Inventory inv = tempPlayer.getInventory();
                Inventory inventory = advancedInventory.inv((Player) event.getWhoClicked(), inv.getSize(), colorCode("&7Inventory Viewer"));
                inventory.setContents(inv.getContents());
                event.getWhoClicked().openInventory(inventory);
            }
        }else if(event.getCurrentItem().getType().equals(Material.ENDER_CHEST)){
            Player tempPlayer = Bukkit.getPlayer(playerName);
            OfflinePlayer offline;

            if(tempPlayer==null){
                offline = Bukkit.getOfflinePlayer(Bukkit.getOfflinePlayer(playerName).getUniqueId());
                tempPlayer = ViewCore.loadPlayer(offline);
            }
            if(tempPlayer!=null) {
                Inventory inv = tempPlayer.getEnderChest();

                Inventory enderchest = advancedInventory.inv((Player) event.getWhoClicked(), inv.getSize(), colorCode("&7Enderchest Viewer"));

                enderchest.setContents(inv.getContents());

                event.getWhoClicked().openInventory(enderchest);
            }
        }else if(event.getCurrentItem().getType().equals(Material.GOLD_INGOT)){
            Player tempPlayer = Bukkit.getPlayer(playerName);
            OfflinePlayer offline;

            if(tempPlayer!=null){
                Player player = (Player) event.getWhoClicked();
                player.performCommand("trade " + playerName);
                player.closeInventory();
            }
        }

        if(event.getClickedInventory().getTitle().contains("Profile Viewer")) event.setCancelled(true);
    }

    @EventHandler
    public void HandleInventoryViewer(InventoryClickEvent event){
        if(event.getClickedInventory()!=null&&
        event.getClickedInventory().getTitle()!=null&&
        event.getClickedInventory().getTitle().equals(colorCode("&7Inventory Viewer"))) event.setCancelled(true);
    }

    @EventHandler
    public void HandlEnderChestViewer(InventoryClickEvent event){
        if(event.getClickedInventory()!=null&&
                event.getClickedInventory().getTitle()!=null&&
                event.getClickedInventory().getTitle().equals(colorCode("&7Enderchest Viewer"))) event.setCancelled(true);
    }

    public static Player loadPlayer(final OfflinePlayer offline) {
        // Ensure player has data
        if (!offline.hasPlayedBefore()) {
            return null;
        }

        // Create a profile and entity to load the player data
        // See net.minecraft.server.PlayerList#attemptLogin
        GameProfile profile = new GameProfile(offline.getUniqueId(),
                offline.getName() != null ? offline.getName() : offline.getUniqueId().toString());
        MinecraftServer server = ((CraftServer) Bukkit.getServer()).getServer();
        WorldServer worldServer = server.getWorldServer(1);

        if (worldServer == null) {
            return null;
        }

        EntityPlayer entity = new EntityPlayer(server, worldServer, profile, new PlayerInteractManager(worldServer));


        // Get the bukkit entity
        Player target = entity.getBukkitEntity();
        if (target != null) {
            // Load data
            target.loadData();
        }


        // Return the entity
        return target;


    }


}
