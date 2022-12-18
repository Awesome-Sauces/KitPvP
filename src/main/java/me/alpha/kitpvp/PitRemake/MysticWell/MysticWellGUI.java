package me.alpha.kitpvp.PitRemake.MysticWell;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

import static me.alpha.kitpvp.utils.advancedInventory.cGlass;
import static me.alpha.kitpvp.utils.advancedInventory.inv;

public class MysticWellGUI {
    public static void base(Player player){
        Inventory gui = inv(player, 45, ChatColor.GRAY + "Mystic Well");

        ItemStack glass = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) (7));
        ItemStack table = new ItemStack(Material.ENCHANTMENT_TABLE);

        ItemMeta null_meta = glass.getItemMeta();
        null_meta.setDisplayName(ChatColor.GRAY + " ");
        glass.setItemMeta(null_meta);

        ItemMeta table_meta = table.getItemMeta();
        ArrayList<String> table_lore = new ArrayList<>();
        table_meta.setDisplayName(ChatColor.LIGHT_PURPLE + "Mystic Well");
        table_lore.add(ChatColor.GRAY + "Find a " + ChatColor.AQUA + "Mystic Bow" + ChatColor.GRAY + "," + ChatColor.YELLOW + "Mystic");
        table_lore.add(ChatColor.YELLOW + "Sword " + ChatColor.GRAY + "or " + ChatColor.RED + "P" + ChatColor.GOLD + "a" + ChatColor.YELLOW + "n" + ChatColor.GREEN + "t" + ChatColor.BLUE + "s " + ChatColor.GRAY + "from");
        table_lore.add(ChatColor.GRAY + "killing players.");
        table_lore.add("");
        table_lore.add(ChatColor.GRAY + "Enchant these items in the well");
        table_lore.add(ChatColor.GRAY + "for tons of buffs.");
        table_lore.add("  ");
        table_lore.add(ChatColor.LIGHT_PURPLE + "Click an item in your inventory!");
        table_meta.setLore(table_lore);
        table.setItemMeta(table_meta);

        ItemStack[] menu_items = {null,null,null,null,null,null,null,null,null,null,glass,glass,glass,null,null,null,null,null,null,glass,null,glass,null,null,table,null,null,null,glass,glass,glass,null,null,null,null,null,null,null,null,null,null,null,null,null,null};
        gui.setContents(menu_items);
        player.openInventory(gui);
    }

    public static Inventory enchanting(Player player){
        Inventory gui = inv(player, 45, ChatColor.GRAY + "Mystic Well");

        ItemStack glass = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) (7));
        ItemStack table = new ItemStack(Material.ENCHANTMENT_TABLE);

        ItemMeta null_meta = glass.getItemMeta();
        null_meta.setDisplayName(ChatColor.GRAY + " ");
        glass.setItemMeta(null_meta);

        ItemMeta table_meta = table.getItemMeta();
        ArrayList<String> table_lore = new ArrayList<>();
        table_meta.setDisplayName(ChatColor.LIGHT_PURPLE + "Mystic Well");
        table_lore.add(ChatColor.GRAY + "Find a " + ChatColor.AQUA + "Mystic Bow" + ChatColor.GRAY + "," + ChatColor.YELLOW + "Mystic");
        table_lore.add(ChatColor.YELLOW + "Sword " + ChatColor.GRAY + "or " + ChatColor.RED + "P" + ChatColor.GOLD + "a" + ChatColor.YELLOW + "n" + ChatColor.GREEN + "t" + ChatColor.BLUE + "s " + ChatColor.GRAY + "from");
        table_lore.add(ChatColor.GRAY + "killing players.");
        table_lore.add("");
        table_lore.add(ChatColor.GRAY + "Enchant these items in the well");
        table_lore.add(ChatColor.GRAY + "for tons of buffs.");
        table_lore.add("  ");
        table_lore.add(ChatColor.LIGHT_PURPLE + "Click an item in your inventory!");
        table_meta.setLore(table_lore);
        table.setItemMeta(table_meta);

        ItemStack[] menu_items = {null,null,null,null,null,null,null,null,null,null,glass,glass,glass,null,null,null,null,null,null,glass,null,glass,null,null,table,null,null,null,glass,glass,glass,null,null,null,null,null,null,null,null,null,null,null,null,null,null};
        gui.setContents(menu_items);
        player.openInventory(gui);
        return gui;
    }

    public static void finish(Player player, ItemStack item){
        Inventory gui = inv(player, 45, ChatColor.GRAY + "Mystic Well");

        ItemStack glass = cGlass();
        ItemStack table = new ItemStack(Material.ENCHANTMENT_TABLE);

        ItemMeta table_meta = table.getItemMeta();
        ArrayList<String> table_lore = new ArrayList<>();
        table_meta.setDisplayName(ChatColor.LIGHT_PURPLE + "Mystic Well");
        table_lore.add(ChatColor.GRAY + "Find a " + ChatColor.AQUA + "Mystic Bow" + ChatColor.GRAY + "," + ChatColor.YELLOW + "Mystic");
        table_lore.add(ChatColor.YELLOW + "Sword " + ChatColor.GRAY + "or " + ChatColor.RED + "P" + ChatColor.GOLD + "a" + ChatColor.YELLOW + "n" + ChatColor.GREEN + "t" + ChatColor.BLUE + "s " + ChatColor.GRAY + "from");
        table_lore.add(ChatColor.GRAY + "killing players.");
        table_lore.add("");
        table_lore.add(ChatColor.GRAY + "Enchant these items in the well");
        table_lore.add(ChatColor.GRAY + "for tons of buffs.");
        table_lore.add("  ");
        table_lore.add(ChatColor.LIGHT_PURPLE + "Click an item in your inventory!");
        table_meta.setLore(table_lore);
        table.setItemMeta(table_meta);

        ItemStack[] menu_items = {null,null,null,null,null,null,null,null,null,null,glass,glass,glass,null,null,null,null,null,null,glass,item,glass,null,null,table,null,null,null,glass,glass,glass,null,null,null,null,null,null,null,null,null,null,null,null,null,null};
        gui.setContents(menu_items);
        player.openInventory(gui);
    }

    public static void enchanting_tierI(Player player, ItemStack item){
        Inventory gui = inv(player, 45, ChatColor.GRAY + "Mystic Well");

        ItemStack glass = cGlass();
        ItemStack table = new ItemStack(Material.ENCHANTMENT_TABLE);
        ItemMeta table_meta = table.getItemMeta();
        ArrayList<String> table_lore = new ArrayList<>();
        table_meta.setDisplayName(ChatColor.LIGHT_PURPLE + "Mystic Well");
        table_lore.add(ChatColor.GRAY + "Find a " + ChatColor.AQUA + "Mystic Bow" + ChatColor.GRAY + "," + ChatColor.YELLOW + "Mystic");
        table_lore.add(ChatColor.YELLOW + "Sword " + ChatColor.GRAY + "or " + ChatColor.RED + "P" + ChatColor.GOLD + "a" + ChatColor.YELLOW + "n" + ChatColor.GREEN + "t" + ChatColor.BLUE + "s " + ChatColor.GRAY + "from");
        table_lore.add(ChatColor.GRAY + "killing players.");
        table_lore.add("");
        table_lore.add(ChatColor.GRAY + "Enchant these items in the well");
        table_lore.add(ChatColor.GRAY + "for tons of buffs.");
        table_lore.add("  ");
        table_lore.add(ChatColor.LIGHT_PURPLE + "Click an item in your inventory!");
        table_meta.setLore(table_lore);
        table.setItemMeta(table_meta);

        ItemStack[] menu_items = {null,null,null,null,null,null,null,null,null,null,glass,glass,glass,null,null,null,null,null,null,glass,item,glass,null,null,table,null,null,null,glass,glass,glass,null,null,null,null,null,null,null,null,null,null,null,null,null,null};
        gui.setContents(menu_items);
        player.openInventory(gui);
    }
}
