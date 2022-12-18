package me.alpha.kitpvp.PitRemake.PitMenus;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import static me.alpha.kitpvp.utils.advancedInventory.*;

public class NonPermanentItems {
    public NonPermanentItems(Player player){
        Inventory gui = inv(player, 36, ChatColor.GRAY + "Non-permanent items");

        ItemStack base_glass = cGlass();

        ItemStack Diamond_Sword = ItemMaker(Material.DIAMOND_SWORD, ChatColor.YELLOW + "Diamond Sword",
                ChatColor.BLUE + "+20% damage vs bountied\n\n" + ChatColor.GRAY + ChatColor.ITALIC + "Lost on death.\n" +
                        ChatColor.RESET + ChatColor.GRAY + "Cost: " + ChatColor.GOLD + "150g\n" + ChatColor.YELLOW + "Click to purchase!",
                1, true);

        ItemStack Pants_bundle = ItemMaker(Material.MINECART, ChatColor.YELLOW + "Pants Bundle",
                ChatColor.GRAY + "Kept on Death\n\n" + ChatColor.GRAY + "Hold and right-click to store 10 fresh pair of pants.\n\n" +
                        ChatColor.RESET + ChatColor.GRAY + "Cost: " + ChatColor.GOLD + "150g\n" + ChatColor.YELLOW + "Click to purchase!",
                1, true);

        ItemStack Sword_bundle = ItemMaker(Material.MINECART, ChatColor.YELLOW + "Sword Bundle",
                ChatColor.GRAY + "Kept on Death\n\n" + ChatColor.GRAY + "Hold and right-click to store 10 mystic swords.\n\n" +
                        ChatColor.RESET + ChatColor.GRAY + "Cost: " + ChatColor.GOLD + "150g\n" + ChatColor.YELLOW + "Click to purchase!",
                1, true);

        ItemStack Diamond_Spade = ItemMaker(Material.DIAMOND_SPADE, ChatColor.YELLOW + "Combat Spade",
                ChatColor.GRAY + "Deals " + ChatColor.BLUE + "+1 damage per\n" + ChatColor.AQUA + "diamond piece " + ChatColor.GRAY + "on enemy." + "\n\n"  +
                        ChatColor.BLUE + "+7 Attack Damage\n\n" + ChatColor.GRAY + ChatColor.ITALIC + "Lost on death.\n" +
                        ChatColor.RESET + ChatColor.GRAY + "Cost: " + ChatColor.GOLD + "750g\n" + ChatColor.YELLOW + "Click to purchase!",
                1, true);

        ItemStack Obsidian = ItemMaker(Material.OBSIDIAN, ChatColor.YELLOW + "Obsidian",
                ChatColor.GRAY + "Remains for 10 minutes.\n\n" + ChatColor.GRAY + ChatColor.ITALIC + "Lost on death.\n" +
                        ChatColor.RESET + ChatColor.GRAY + "Cost: " + ChatColor.GOLD + "40g\n" + ChatColor.YELLOW + "Click to purchase!",
                8, true);

        ItemStack FirstAid = EggMaker((short) 96, ChatColor.YELLOW + "First-Aid Egg",
                ChatColor.GRAY + "Heals " + ChatColor.RED +
                        "2.5\u2764\n" + ChatColor.GRAY +
                        "5 second cooldown.\n\n" + ChatColor.GRAY + ChatColor.ITALIC + "Lost on death.\n" +
                        ChatColor.RESET + ChatColor.GRAY + "Cost: " + ChatColor.GOLD + "2000g\n" + ChatColor.YELLOW + "Click to purchase!");

        ItemStack Iron_Kit = ItemMaker(Material.IRON_HELMET, ChatColor.YELLOW + "Iron Pack",
                ChatColor.GRAY + "Contains:\n" + ChatColor.WHITE + " Iron Helmet\n " + ChatColor.WHITE + "Iron Chestplate\n " + ChatColor.WHITE + "Iron Leggings\n " +
                        ChatColor.WHITE + "Iron Boots" + "\n\n" + ChatColor.GRAY + ChatColor.ITALIC + "Lost on death.\n" +
                        ChatColor.RESET + ChatColor.GRAY + "Cost: " + ChatColor.GOLD + "200g\n" + ChatColor.YELLOW + "Click to purchase!",
                1, true);

        ItemStack Diamond_Chestplate = ItemMaker(Material.DIAMOND_CHESTPLATE, ChatColor.RED + "Diamond Chestplate",
                ChatColor.BLUE + "Auto-equips on buy!\n\n" + ChatColor.GRAY + ChatColor.ITALIC + "Lost on death.\n" +
                        ChatColor.RESET + ChatColor.GRAY + "Cost: " + ChatColor.GOLD + "500g\n" + ChatColor.YELLOW + "Click to purchase!",
                1, true);

        ItemStack Diamond_Leggings = ItemMaker(Material.DIAMOND_LEGGINGS, ChatColor.RED + "Diamond Leggings",
                ChatColor.BLUE + "Auto-equips on buy!\n\n" + ChatColor.GRAY + ChatColor.ITALIC + "Lost on death.\n" +
                        ChatColor.RESET + ChatColor.GRAY + "Cost: " + ChatColor.GOLD + "1500g\n" + ChatColor.YELLOW + "Click to purchase!",
                1, true);

        ItemStack Diamond_Boots = ItemMaker(Material.DIAMOND_BOOTS, ChatColor.RED + "Diamond Boots",
                ChatColor.BLUE + "Auto-equips on buy!\n\n" + ChatColor.GRAY + ChatColor.ITALIC + "Lost on death.\n" +
                        ChatColor.RESET + ChatColor.GRAY + "Cost: " + ChatColor.GOLD + "300g\n" + ChatColor.YELLOW + "Click to purchase!",
                1, true);

        addInv(gui, Diamond_Sword, 2, 2, false);
        addInv(gui, Diamond_Spade, 3, 2, false);
        addInv(gui, Obsidian, 4, 2, false);
        addInv(gui, Iron_Kit, 5, 2, false);
        addInv(gui, Diamond_Chestplate, 6, 2, false);
        addInv(gui, Diamond_Leggings, 7, 2, false);
        addInv(gui, Diamond_Boots, 8, 2, false);
        addInv(gui, Pants_bundle, 2, 3, false);
        addInv(gui, Sword_bundle, 3, 3, false);
        addInv(gui, FirstAid, 4, 3, false);




        player.openInventory(gui);
    }

}
