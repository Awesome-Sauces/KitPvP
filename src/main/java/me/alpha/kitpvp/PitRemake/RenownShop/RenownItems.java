package me.alpha.kitpvp.PitRemake.RenownShop;

import me.alpha.kitpvp.Data.ClassInstances;
import me.alpha.kitpvp.PitRemake.RenownShop.CookieMonster.Monster;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import static me.alpha.kitpvp.utils.advancedInventory.ItemMaker;

public class RenownItems {


    public static ItemStack CookieItemIncrease(String uuid){
        return ItemMaker(Material.COOKIE, ChatColor.YELLOW + "Cookie Monster Hunter",
                ChatColor.GRAY + "Each purchase gives you " + ChatColor.RED + "+1% spawn chance\n" + ChatColor.GRAY +
                        ChatColor.GRAY + "Killing a " + ChatColor.AQUA + "Cookie Monster " + ChatColor.GRAY +
                        "can reward\n" + ChatColor.GRAY + "you anywhere from " + ChatColor.AQUA + "5-50" + ChatColor.GRAY +
                        " renown but\n" + ChatColor.GRAY + "failing to kill the " + ChatColor.AQUA + "Cookie Monster\n " + ChatColor.GRAY +
                        "in 15 seconds will\n"+ChatColor.GRAY+"result in you losing anywhere\n" + ChatColor.GRAY + "from " + ChatColor.AQUA + "5-15 " + ChatColor.GRAY +
                        "renown. (Will not go below 0 renown)\n" + ChatColor.GRAY + "Capped at " + "+10%\n\n" + ChatColor.GRAY +
                        "Current boost: " + ChatColor.RED +  "+" + Math.min(Monster.getMonsterChance(uuid), 10) + "%\n\n" +
                        ChatColor.GRAY + "Cost: " + ChatColor.YELLOW + "64 Renown", 1, true);
    }




    public static ItemStack XpItemIncrease(String uuid){
        return ItemMaker(Material.EXP_BOTTLE, ChatColor.YELLOW + "Factory",
                ChatColor.GRAY + "Each purchase gives you " + ChatColor.AQUA + "+5 xp cap!\n" + ChatColor.GRAY +
                        "But is capped at" + ChatColor.YELLOW + " 500\n\n" + ChatColor.GRAY +
                        ChatColor.GRAY + "Cost: " + ChatColor.YELLOW + "32 Renown", 1, true);
    }

    public static ItemStack GoldItemIncrease(String uuid){
        return ItemMaker(Material.GOLD_INGOT, ChatColor.YELLOW + "Gold n' Boosted",
                ChatColor.GRAY + "Each purchase gives you " + ChatColor.GOLD + "+5% gold!\n" + ChatColor.GRAY +
                        "But is capped at" + ChatColor.YELLOW + " 50%\n\n" + ChatColor.GRAY +
                        ChatColor.GRAY + "Cost: " + ChatColor.YELLOW + "48 Renown", 1, true);
    }

    public static ItemStack XpliciousIncrease(String uuid){
        return ItemMaker(Material.EXP_BOTTLE, ChatColor.YELLOW + "Xplicious",
                ChatColor.GRAY + "Each purchase gives you " + ChatColor.AQUA + "+15 xp cap and +5% xp!\n" + ChatColor.GRAY +
                        "But is capped at" + ChatColor.YELLOW + " 500\n\n" + ChatColor.GRAY +
                        ChatColor.RED + "Requires prestige X\n" +
                        ChatColor.GRAY + "Cost: " + ChatColor.YELLOW + "64 Renown", 1, true);
    }

    public static ItemStack CorruptedPearl(){
        return ItemMaker(Material.EYE_OF_ENDER, ChatColor.YELLOW + "Corrupted Pearl",
                ChatColor.GRAY + "Each purchase gives you " + ChatColor.LIGHT_PURPLE + "+1 Corrupted Pearl!\n" + ChatColor.GRAY +
                        "Using a corrupted pearl will teleport a\n" + ChatColor.GRAY +
                        "random player to you and strike them with" + ChatColor.DARK_PURPLE + " venom!" + "\n\n" +
                        ChatColor.GRAY + "Cost: " + ChatColor.YELLOW + "64 Renown", 1, true);
    }

    public static ItemStack QuesterKit(){
        return ItemMaker(Material.NETHER_STAR, ChatColor.YELLOW + "3 Questeteers",
                ChatColor.GRAY + "Gives you the 3 necessary\n" + ChatColor.GRAY +
                        "questing tools. Use these\n" + ChatColor.GRAY +
                        "while doing quests or finding cakes\n" + ChatColor.GRAY + "or maybe just for fun!\n\n" +
                        ChatColor.GRAY + "Cost: " + ChatColor.YELLOW + "640 Renown", 1, true);
    }

    public static ItemStack ReaperScythe(){
        return ItemMaker(Material.DIAMOND_HOE, ChatColor.YELLOW + "Reaper Scythe",
                ChatColor.GRAY + "Gives you the necessary\n" + ChatColor.GRAY +
                        "tool to harvest souls.\n" + ChatColor.GRAY +
                        "Be careful how you use it!\n\n" +
                        ChatColor.RED + "Requires prestige XV\n" +
                        ChatColor.GRAY + "Cost: " + ChatColor.YELLOW + "192 Renown", 1, true);
    }

    public static ItemStack MysticismDropItem(String uuid){
        return ItemMaker(Material.ENCHANTMENT_TABLE, ChatColor.YELLOW + "Mysticism",
                ChatColor.GRAY + "Current: " + ChatColor.LIGHT_PURPLE +
                        "+" + Math.round(ClassInstances.mysticism.getMysticismChance(uuid) * 1000) + "% chance\n" +
                        ChatColor.GRAY + "Grants you a higher drop\n" + ChatColor.GRAY + "chance for mystics.\n\n" +
                        ChatColor.RED + "Requires prestige V\n" +
                        ChatColor.GRAY + "Cost: " + ChatColor.YELLOW + Math.round((ClassInstances.mysticism.getMysticismChance(uuid)) * 64) +" Renown", 1, true);
    }

    public static ItemStack NuclearAtomizer(){
        return ItemMaker(Material.FIREWORK_CHARGE, ChatColor.YELLOW + "Nuclear Atomizer",
                ChatColor.GRAY + "Atomizes renown tokens to\n" + ChatColor.GRAY + "produce temporary stat\n" +
                        ChatColor.GRAY + "boosts. Stat boosts will\n" + ChatColor.GRAY + "randomly decay and\n" +
                        ChatColor.GRAY + "return to ashes.\n\n" + ChatColor.RED + "Requires prestige XXX\n" +
                        ChatColor.GRAY + "Cost: " + ChatColor.YELLOW + "640 Renown", 1, true);
    }

    public static ItemStack Boxers(){
        return ItemMaker(Material.LEATHER_LEGGINGS, ChatColor.YELLOW + "Colorful Boxers",
                ChatColor.GRAY + "Each purchase gives you a\n" + ChatColor.GRAY + "to roll 3 of these dyes:\n" +
                        ChatColor.GRAY + "- " + ChatColor.DARK_GRAY + "Midnight Dye\n" +
                        ChatColor.GRAY + "- " + ChatColor.RED + "Crimson Dye\n" +
                        ChatColor.GRAY + "- " + ChatColor.LIGHT_PURPLE + "Strawberry Dye\n" +
                        ChatColor.GRAY + "- " + ChatColor.YELLOW + "Lemon Dye\n" +
                        ChatColor.GRAY + "- " + ChatColor.DARK_AQUA + "Sapphire Dye\n" +
                        ChatColor.GRAY + "- " + ChatColor.GOLD + "Tangerine Dye\n" +
                        ChatColor.GRAY + "- " + ChatColor.DARK_GREEN + "Prickly Dye\n\n" +
                        ChatColor.GRAY + "Cost: " + ChatColor.YELLOW + "5 Renown", 1, true);
    }
}
