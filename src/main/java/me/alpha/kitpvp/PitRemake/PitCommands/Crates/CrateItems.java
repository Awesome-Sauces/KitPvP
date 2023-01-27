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

import static me.alpha.kitpvp.PitRemake.MysticWell.enchanters.FreshPants.percentChance;
import static me.alpha.kitpvp.utils.ColorUtil.colorCode;

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

            while (true){
                if(percentChance(.25)){
                    nbtItem.setInteger("maxLives", 7);
                    nbtItem.setInteger("lives", 7);
                    break;
                }else if(percentChance(.05)){
                    nbtItem.setInteger("maxLives", 10);
                    nbtItem.setInteger("lives", 10);
                    break;
                }else if(percentChance(.04)){
                    nbtItem.setInteger("maxLives", 10);
                    nbtItem.setInteger("lives", 10);
                    break;
                }else if(percentChance(.03)){
                    nbtItem.setInteger("maxLives", 15);
                    nbtItem.setInteger("lives", 15);
                    break;
                }else if(percentChance(.02)){
                    nbtItem.setInteger("maxLives", 20);
                    nbtItem.setInteger("lives", 20);
                    break;
                }else if(percentChance(.01)){
                    nbtItem.setInteger("maxLives", 25);
                    nbtItem.setInteger("lives", 25);
                    break;
                }else if(percentChance(.001)){
                    nbtItem.setInteger("maxLives", 100);
                    nbtItem.setInteger("lives", 100);
                    break;
                }
            }


        nbtItem.addCompound("enchants");
        nbtItem.setInteger("mysticTier", 1);

        nbtItem.mergeCompound(nbtCompound);

        item = nbtItem.getItem();

        int currentLives = nbtItem.getInteger("lives");
        int maxLives = nbtItem.getInteger("maxLives");
        String currentLivesColor = "&a";

        if(currentLives<=(maxLives/3)) currentLivesColor = "&c";

        String livesTemplate = colorCode("&7Lives: " + currentLivesColor + currentLives+"&7/"+maxLives);

        LeatherArmorMeta meta = (LeatherArmorMeta) item.getItemMeta();
        meta.setColor(Color.AQUA);
        meta.setDisplayName(colorCode("&cTier I Aqua Pants"));
        List<String> lore = new ArrayList<>();
        lore.add(livesTemplate);
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

    private static ItemStack helmet = null;
    private static ItemStack chestplate = null;
    private static ItemStack leggings = null;
    private static ItemStack boots = null;


    public static ItemStack getDiamondHelmet() {
        if(helmet!=null) return helmet;

        ItemStack item = new ItemStack(Material.DIAMOND_HELMET, 1);
        List<String> lore = new ArrayList<>();
        ItemMeta meta = item.getItemMeta();
        meta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 2, false);
        meta.spigot().setUnbreakable(true);
        item.setItemMeta(meta);

        helmet = item;
        return getDiamondHelmet();
    }
    public static ItemStack getDiamondChestplate() {
        if(chestplate!=null) return chestplate;

        ItemStack item = new ItemStack(Material.DIAMOND_CHESTPLATE, 1);
        ItemMeta meta = item.getItemMeta();
        meta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 2, false);
        meta.spigot().setUnbreakable(true);
        item.setItemMeta(meta);

        chestplate=item;

        return getDiamondChestplate();
    }
    public static ItemStack getDiamondLeggings() {
        if(leggings!=null) return leggings;

        ItemStack item = new ItemStack(Material.DIAMOND_LEGGINGS, 1);
        ItemMeta meta = item.getItemMeta();
        meta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 2, false);
        meta.spigot().setUnbreakable(true);
        item.setItemMeta(meta);

        leggings = item;

        return getDiamondLeggings();
    }
    public static ItemStack getDiamondBoots() {
        if(boots!=null) return boots;

        ItemStack item = new ItemStack(Material.DIAMOND_BOOTS, 1);
        ItemMeta meta = item.getItemMeta();
        meta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 2, false);
        meta.spigot().setUnbreakable(true);
        item.setItemMeta(meta);

        boots = item;

        return getDiamondBoots();
    }


    
}
