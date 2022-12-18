package me.alpha.kitpvp.PitRemake.QuestMaster;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import static me.alpha.kitpvp.utils.ColorUtil.colorCode;
import static me.alpha.kitpvp.utils.advancedInventory.ItemMaker;

public class questMenuItems {
    public static ItemStack claimRewards(String uuid){
        return ItemMaker(Material.GOLD_BLOCK, ChatColor.YELLOW + "Quests!", ChatColor.GRAY + "Complete these quests for rewards!\n" + ChatColor.GRAY + " For each quest completed gain\n" + ChatColor.GRAY + "small stat boosts!", 1, true);
    }

    public static ItemStack PlayerSoulsToFresh(String uuid){
        return ItemMaker(Material.MINECART, ChatColor.YELLOW +
                "Yummy Souls", colorCode("&7Bring the &bQuest Master &364\n" +
                "&3Player Souls &7to convert\n&7them into a &ePants Bundle\n\n" +
                "&650,000g &7conversion cost!"), 1, true);
    }

    public static ItemStack PlayerSoulsToMysticSword(String uuid){
        return ItemMaker(Material.STORAGE_MINECART, ChatColor.YELLOW +
                "Yummy Souls+", colorCode("&7Bring the &bQuest Master &3128\n" +
                "&3Player Souls &7to convert\n&7them into a &eSword Bundle\n\n" +
                "&650,000g &7conversion cost!"), 1, true);
    }

    public static ItemStack PlayerSoulsToJewelPant(String uuid){
        return ItemMaker(Material.SLIME_BALL, ChatColor.YELLOW +
                "Yummy Souls++", colorCode("&7Bring the &bQuest Master &3128\n" +
                "&3Player Souls &7to convert\n&7them into a &3Hidden Jewel Pant\n\n" +
                "&650,000g &7conversion cost!"), 1, true);
    }

    public static ItemStack PlayerSoulsToJewelSword(String uuid){
        return ItemMaker(Material.SLIME_BLOCK, ChatColor.YELLOW +
                "Yummy Souls+++", colorCode("&7Bring the &bQuest Master &3128\n" +
                "&3Player Souls &7to convert\n&7them into a &3Hidden Jewel Sword\n\n" +
                "&675,000g &7conversion cost!"), 1, true);
    }

    public static ItemStack PlayerSoulsToMalding(String uuid){
        return ItemMaker(Material.DIAMOND_HOE, ChatColor.YELLOW +
                "Soul Reaper", colorCode("&7Bring the &bQuest Master &332\n" +
                "&3Player Souls &7to convert\n&7them into a &bCookie Monster &7kill\n" +
                "&7him for special rewards!\n\n" +
                "&625,000g &7conversion cost!"), 1, true);
    }

}
