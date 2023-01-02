package me.alpha.kitpvp.PitRemake.PitCommands.Crates;

import de.tr7zw.nbtapi.NBTCompound;
import de.tr7zw.nbtapi.NBTItem;
import me.alpha.kitpvp.PitRemake.ItemStacks.enchants;
import me.alpha.kitpvp.PitRemake.ItemStacks.itemManager;
import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;

import java.util.ArrayList;
import java.util.List;

public class CrateItems {

    public String colorCode(String text){
        return ChatColor.translateAlternateColorCodes('&', text);
    }
    public ItemStack getJewelPant(){
        return enchants.jewl_pant;
    }

    public ItemStack getJewelSword(){
        return enchants.jewl_sword;
    }

    public ItemStack getPitBlob(){
        ItemStack item = new ItemStack(Material.LEATHER_LEGGINGS, 1);

        NBTItem nbtItem = new NBTItem(item);

        NBTCompound nbtCompound = nbtItem.getOrCreateCompound("enchants");

        nbtCompound.setInteger("pitblob", 3);

        nbtItem.mergeCompound(nbtCompound);

        item = nbtItem.getItem();

        LeatherArmorMeta meta = (LeatherArmorMeta) item.getItemMeta();
        meta.setColor(Color.AQUA);
        meta.setDisplayName(colorCode("&cTier I Aqua Pants"));
        List<String> lore = new ArrayList<>();
        lore.add(ChatColor.translateAlternateColorCodes('&', "&7Lives: &a5&7/5"));
        lore.add(" ");
        lore.add(colorCode("&dRARE! &9Pit Blob III"));
        lore.add(colorCode("&7Kills respawn &aThe Blob&7. This"));
        lore.add(colorCode("&7slimy pet will follow you around"));
        lore.add(colorCode("&7and kill your enemies. &aThe Blob"));
        lore.add(colorCode("&7grows and gains health for every"));
        lore.add(colorCode("&7enemy you kill."));

        meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
        meta.spigot().setUnbreakable(true);
        meta.setLore(lore);
        item.setItemMeta(meta);

        return item;
    }

    public ItemStack getFeather(){
        return itemManager.feather;
    }

    public ItemStack getVile(){
        return enchants.vile;
    }

    public ItemStack getGem(){return enchants.gem;}

    public ItemStack getDiamondHelmet() {
        ItemStack item = new ItemStack(Material.DIAMOND_HELMET, 1);
        List<String> lore = new ArrayList<>();
        ItemMeta meta = item.getItemMeta();
        meta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 2, false);
        meta.spigot().setUnbreakable(true);
        item.setItemMeta(meta);
        return item;
    }
    public ItemStack getDiamondChestplate() {
        ItemStack item = new ItemStack(Material.DIAMOND_CHESTPLATE, 1);
        ItemMeta meta = item.getItemMeta();
        meta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 2, false);
        meta.spigot().setUnbreakable(true);
        item.setItemMeta(meta);
        return item;
    }
    public ItemStack getDiamondLeggings() {
        ItemStack item = new ItemStack(Material.DIAMOND_LEGGINGS, 1);
        ItemMeta meta = item.getItemMeta();
        meta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 2, false);
        meta.spigot().setUnbreakable(true);
        item.setItemMeta(meta);
        return item;
    }
    public ItemStack getDiamondBoots() {
        ItemStack item = new ItemStack(Material.DIAMOND_BOOTS, 1);
        ItemMeta meta = item.getItemMeta();
        meta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 2, false);
        meta.spigot().setUnbreakable(true);
        item.setItemMeta(meta);
        return item;
    }
    
}
