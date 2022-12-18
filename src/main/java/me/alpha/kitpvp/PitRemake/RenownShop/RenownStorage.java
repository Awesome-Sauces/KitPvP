package me.alpha.kitpvp.PitRemake.RenownShop;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;


import static me.alpha.kitpvp.utils.ColorUtil.colorCode;
import static me.alpha.kitpvp.utils.advancedInventory.ItemMaker;

public class RenownStorage {


    public static ItemStack getToken(){
        return ItemMaker(Material.TRIPWIRE_HOOK, ChatColor.YELLOW + "Renown Tokens",
                ChatColor.GRAY + "Used to buy upgrades from\n" +  ChatColor.GRAY + "the renown shop!\n\n" +
                        ChatColor.RED + "Gained on prestige!", 1, true);
    }

    public static ItemStack getCorruptedPearl(){
        return ItemMaker(Material.EYE_OF_ENDER, ChatColor.YELLOW + "Corrupted Pearl",
                ChatColor.GRAY + "Using a corrupted pearl will teleport a\n" + ChatColor.GRAY +
                        "random player to you and strike them with" + ChatColor.DARK_PURPLE + " venom!" + "\n\n" +
                        ChatColor.RED + "5 second cool down!", 1, true);
    }

    public static ItemStack getUberDrop(){
        return ItemMaker(Material.ENDER_CHEST, ChatColor.LIGHT_PURPLE + "Uberdrop",
                ChatColor.GRAY + "Kept on death\n" + ChatColor.GRAY + "Contains: " + ChatColor.LIGHT_PURPLE + colorCode("&kUberdrop!\n\n") +
                        ChatColor.YELLOW + "Hold and click to open!", 1, true);
    }

    public static ItemStack getStickQuest(){
        return ItemMaker(Material.STICK, ChatColor.DARK_AQUA + "Normal Stick",
                ChatColor.GRAY + "Kept on death\n\n" + ChatColor.GRAY +
                        "Magically allows you to pick up\n" + ChatColor.LIGHT_PURPLE +
                        "cakes " + ChatColor.GRAY + "granting a chance\n" + ChatColor.GRAY +
                        "for " + ChatColor.YELLOW + "Renown Tokens " + ChatColor.GRAY +
                        "to\n" + ChatColor.GRAY + "randomly drop!\n\n" +
                ChatColor.GRAY + ChatColor.ITALIC + "Quest Item", 1, true);
    }

    public static ItemStack getBlazeQuest(){
        return ItemMaker(Material.BLAZE_ROD, ChatColor.RED + "Flaming Rod",
                ChatColor.GRAY + "Kept on death\n\n" + ChatColor.GRAY +
                        "Grants +75% chance to get vile from kills\n" +
                        ChatColor.GRAY + "Allows you to fly around\n" + ChatColor.GRAY +
                        "the map in a " + ChatColor.RED + "blaze" + ChatColor.GRAY + ",\n" + ChatColor.GRAY + "while outside of combat\n\n" +
                        ChatColor.GRAY + ChatColor.ITALIC + "Quest Item", 1, true);
    }

    public static ItemStack NuclearAtomizerItem(){
        return ItemMaker(Material.FIREWORK_CHARGE, ChatColor.LIGHT_PURPLE + "Nuclear Atomizer",
                ChatColor.GRAY + "Atomizes renown tokens to\n" + ChatColor.GRAY + "produce temporary stat\n" +
                        ChatColor.GRAY + "boosts. Stat boosts will\n" + ChatColor.GRAY + "randomly decay and\n" +
                        ChatColor.GRAY + "return to ashes.\n" + ChatColor.RED + "Right click to open", 1, true);
    }

    public static ItemStack getGoldQuest(){
        return ItemMaker(Material.GOLD_NUGGET, ChatColor.GOLD + "Technical Enhancement",
                ChatColor.GRAY + "Kept on death\n\n" + ChatColor.GRAY +
                        "Provides you with the technology\n" + ChatColor.GRAY +
                        "needed to receive double\n" + ChatColor.GRAY + "gold from kills but \n" + ChatColor.GRAY +
                        "you're heavily slowed down!\n\n" +
                        ChatColor.GRAY + ChatColor.ITALIC + "Quest Item", 1, true);
    }

}
