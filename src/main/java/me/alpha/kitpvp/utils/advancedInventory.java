package me.alpha.kitpvp.utils;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.bukkit.Bukkit.getServer;

public class advancedInventory {

    public static Inventory inv(Player player, int size , String string){
        return Bukkit.createInventory(player, size, string);
    }

    public static ItemStack cGlass(){
        return new ItemStack(Material.AIR);
    }

    public static ItemStack dGlass(boolean empty){
        ItemStack glass = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) (15));
        ItemMeta null_meta = glass.getItemMeta();
        List<String> lore = new ArrayList<>();
        if(empty) null_meta.setDisplayName(ChatColor.GRAY+"Click an item in your inventory!");
        else {
            null_meta.setDisplayName(ChatColor.GRAY + "Item in well!");
            lore.add((ChatColor.GRAY+"Click it to get it back."));
        }
        null_meta.setLore(lore);
        glass.setItemMeta(null_meta);
        return glass;
    }

    public static ItemStack yGlass(boolean empty, int tier, boolean dark){
        ItemStack glass = null;

        if(dark) glass = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) (10));
        else if(tier>=3) glass = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) (14));
        else if(tier==2) glass = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) (4));
        else if(tier==1) glass = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) (5));
        else glass = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) (6));

        ChatColor chatColor = ChatColor.GRAY;

        switch (tier){
            case 2:
                chatColor = ChatColor.YELLOW;
                break;
            case 1:
                chatColor = ChatColor.GREEN;
                break;
            case 3:
                chatColor = ChatColor.RED;
                break;
            default:
                chatColor = ChatColor.LIGHT_PURPLE;
                break;
        }

        if(dark)chatColor = ChatColor.DARK_PURPLE;

        ItemMeta null_meta = glass.getItemMeta();
        List<String> lore = new ArrayList<>();
        if(empty) {
            null_meta.setDisplayName(chatColor + "Click an item in your inventory!");
        }else {
            null_meta.setDisplayName(chatColor + "Item in well!");
            lore.add((chatColor+"Click it to get it back."));
        }

        null_meta.setLore(lore);
        glass.setItemMeta(null_meta);
        return glass;
    }

    public static ItemStack closeButton(){
        ItemStack glass = new ItemStack(Material.BARRIER, 1);
        ItemMeta null_meta = glass.getItemMeta();
        null_meta.setDisplayName(ChatColor.RED + "Close");
        glass.setItemMeta(null_meta);
        return glass;
    }

    public static ItemStack ItemMaker(Material material, String name, String lore){
        ItemStack item = new ItemStack(material, 1);
        ItemMeta null_meta = item.getItemMeta();
        null_meta.addItemFlags(ItemFlag.HIDE_ENCHANTS, ItemFlag.HIDE_UNBREAKABLE, ItemFlag.HIDE_ATTRIBUTES);
        String[] lines = lore.split("\\n");
        ArrayList<String> table_lore = new ArrayList<>(Arrays.asList(lines));
        null_meta.setDisplayName(name);
        if(!lore.isEmpty()) null_meta.setLore(table_lore);
        item.setItemMeta(null_meta);
        return item;
    }

    public static ItemStack ItemMaker(Material material, String name, String lore, boolean enchant){
        ItemStack item = new ItemStack(material, 1);
        ItemMeta null_meta = item.getItemMeta();
        if(enchant)null_meta.addEnchant(new GlowEnchant(1),1,false);
        null_meta.addItemFlags(ItemFlag.HIDE_ENCHANTS, ItemFlag.HIDE_UNBREAKABLE, ItemFlag.HIDE_ATTRIBUTES);
        String[] lines = lore.split("\\n");
        ArrayList<String> table_lore = new ArrayList<>(Arrays.asList(lines));
        null_meta.setDisplayName(name);
        if(!lore.isEmpty()) null_meta.setLore(table_lore);
        item.setItemMeta(null_meta);
        return item;
    }

    public static ItemStack ItemMaker(Material material, String name, String lore, int amount, Boolean guiItem){
        if (!guiItem){
            ItemStack item = new ItemStack(material, amount);
            ItemMeta null_meta = item.getItemMeta();
            if (!"NULL".equalsIgnoreCase(name)) {
                String[] lines = lore.split("\n");
                ArrayList<String> table_lore = new ArrayList<>(Arrays.asList(lines));
                null_meta.addItemFlags(ItemFlag.HIDE_ENCHANTS, ItemFlag.HIDE_UNBREAKABLE, ItemFlag.HIDE_ATTRIBUTES);
                null_meta.setDisplayName(name);
                null_meta.setLore(table_lore);
            }
            null_meta.spigot().setUnbreakable(true);
            item.setItemMeta(null_meta);
            return item;
        }
        ItemStack item = new ItemStack(material, amount);
        ItemMeta null_meta = item.getItemMeta();
        null_meta.addItemFlags(ItemFlag.HIDE_ENCHANTS, ItemFlag.HIDE_UNBREAKABLE, ItemFlag.HIDE_ATTRIBUTES);
        String[] lines = lore.split("\\n");
        ArrayList<String> table_lore = new ArrayList<>(Arrays.asList(lines));
        null_meta.setDisplayName(name);
        null_meta.setLore(table_lore);
        item.setItemMeta(null_meta);
        return item;
    }

    public static ItemStack DyeMaker(short dyeColor, String DisplayName, String lore){
        ItemStack dye = new ItemStack(351, 1, dyeColor);
        ItemMeta meta = dye.getItemMeta();
        meta.setDisplayName(DisplayName);
        String[] lines = lore.split("\\n");
        ArrayList<String> dye_lore = new ArrayList<>(Arrays.asList(lines));
        meta.setLore(dye_lore);
        dye.setItemMeta(meta);
        return dye;
    }

    public static ItemStack LeggingsMaker(Color dyeColor, String DisplayName, String lore){
        ItemStack dye = new ItemStack(Material.LEATHER_LEGGINGS, 1);
        LeatherArmorMeta meta = (LeatherArmorMeta) dye.getItemMeta();
        meta.setColor(dyeColor);
        meta.setDisplayName(DisplayName);
        String[] lines = lore.split("\\n");
        ArrayList<String> dye_lore = new ArrayList<>(Arrays.asList(lines));
        meta.setLore(dye_lore);
        dye.setItemMeta(meta);
        return dye;
    }

    public static ItemStack WoolMaker(short dyeColor, String DisplayName, String lore){
        ItemStack dye = new ItemStack(35, 1, dyeColor);
        ItemMeta meta = dye.getItemMeta();
        meta.setDisplayName(DisplayName);
        String[] lines = lore.split("\\n");
        ArrayList<String> dye_lore = new ArrayList<>(Arrays.asList(lines));
        meta.setLore(dye_lore);
        dye.setItemMeta(meta);
        return dye;
    }

    public static ItemStack EggMaker(short dyeColor, String DisplayName, String lore){
        ItemStack dye = new ItemStack(383, 1, dyeColor);
        ItemMeta meta = dye.getItemMeta();
        meta.setDisplayName(DisplayName);
        String[] lines = lore.split("\\n");
        ArrayList<String> dye_lore = new ArrayList<>(Arrays.asList(lines));
        meta.setLore(dye_lore);
        dye.setItemMeta(meta);
        return dye;
    }

    public static ItemStack FishMaker(short dyeColor, String DisplayName, String lore){
        ItemStack dye = new ItemStack(349, 1, dyeColor);
        ItemMeta meta = dye.getItemMeta();
        meta.setDisplayName(DisplayName);
        String[] lines = lore.split("\\n");
        ArrayList<String> dye_lore = new ArrayList<>(Arrays.asList(lines));
        meta.setLore(dye_lore);
        dye.setItemMeta(meta);
        return dye;
    }

    public static ItemStack DirtMaker(short dyeColor, String DisplayName, String lore){
        ItemStack dye = new ItemStack(3, 1, dyeColor);
        ItemMeta meta = dye.getItemMeta();
        meta.setDisplayName(DisplayName);
        String[] lines = lore.split("\\n");
        ArrayList<String> dye_lore = new ArrayList<>(Arrays.asList(lines));
        meta.setLore(dye_lore);
        dye.setItemMeta(meta);
        return dye;
    }

    public static ItemStack HeadMaker(String headName, String DisplayName, String lore){
        ItemStack skull = new ItemStack(Material.SKULL_ITEM, 1, (short) 3);
        SkullMeta meta = (SkullMeta) skull.getItemMeta();
        meta.setDisplayName(DisplayName);
        meta.setOwner(headName);
        String[] lines = lore.split("\\n");
        ArrayList<String> dye_lore = new ArrayList<>(Arrays.asList(lines));
        meta.setLore(dye_lore);
        skull.setItemMeta(meta);
        return skull;
    }

    public static ItemStack ClayMaker(short clayColor, String DisplayName, String lore){
        ItemStack dye = new ItemStack(159, 1, clayColor);
        ItemMeta meta = dye.getItemMeta();
        meta.setDisplayName(DisplayName);
        String[] lines = lore.split("\\n");
        ArrayList<String> dye_lore = new ArrayList<>(Arrays.asList(lines));
        meta.setLore(dye_lore);
        dye.setItemMeta(meta);
        return dye;
    }

    public static ItemStack ClayMaker(String DisplayName, String lore){
        ItemStack dye = new ItemStack(159, 1);
        ItemMeta meta = dye.getItemMeta();
        meta.setDisplayName(DisplayName);
        String[] lines = lore.split("\\n");
        ArrayList<String> dye_lore = new ArrayList<>(Arrays.asList(lines));
        meta.setLore(dye_lore);
        dye.setItemMeta(meta);
        return dye;
    }

    public static void addInv(Inventory inventory, ItemStack item, int x, int y, boolean null_stack){
        // Note: The first inventory slot in addInv starts 0 0 instead of 1 1 but supports 1 1 as origin as well.

        if (y <= 0){y++;}
        if (x <= 0){x++;}

        int invSize = inventory.getSize() / 9;

        if (y > invSize) {getServer().getConsoleSender().sendMessage(ChatColor.GREEN + "[ERROR] Y coordinate is greater than possible."); return;}else if(x > 9){getServer().getConsoleSender().sendMessage(ChatColor.GREEN + "[ERROR] X coordinate is greater than possible."); return;}

        int slot = ((y-1)*9) + (x-1);

        try{if (!null_stack) {inventory.setItem(slot, item);} else {inventory.setItem(slot, null);}
        }catch (Exception ignored){}

    }

}
