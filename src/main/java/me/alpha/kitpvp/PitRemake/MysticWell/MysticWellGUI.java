package me.alpha.kitpvp.PitRemake.MysticWell;

import de.tr7zw.nbtapi.NBTItem;
import me.alpha.kitpvp.Data.ClassInstances;
import me.alpha.kitpvp.Data.GoldData;
import me.alpha.kitpvp.PitRemake.MysticWell.enchanters.FreshPants;
import me.alpha.kitpvp.PitRemake.MysticWell.enchanters.MysticSword;
import me.alpha.kitpvp.utils.ColorUtil;
import me.alpha.kitpvp.utils.advancedInventory;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import javax.xml.crypto.Data;
import java.util.ArrayList;

import static me.alpha.kitpvp.PitRemake.MysticWell.loreChecker.CheckEnchantOnSword;
import static me.alpha.kitpvp.utils.advancedInventory.*;

public class MysticWellGUI {
    public static ItemStack getMysticWellItem(String uuid){
        return ItemMaker(Material.ENCHANTMENT_TABLE, ChatColor.LIGHT_PURPLE + "Mystic Well",
                ChatColor.GRAY + "Find a " + ChatColor.AQUA + "Mystic Bow" + ChatColor.GRAY + ", " + ChatColor.YELLOW + "Mystic\n" +
                ChatColor.YELLOW + "Sword " + ChatColor.GRAY + "or " + ChatColor.RED + "P" + ChatColor.GOLD + "a" + ChatColor.YELLOW + "n" + ChatColor.GREEN + "t" + ChatColor.BLUE + "s " + ChatColor.GRAY + "from\n" +
                ChatColor.GRAY + "killing players.\n\n" +
                ChatColor.GRAY + "Enchant these items in the well\n" +
                ChatColor.GRAY + "for tons of buffs.\n\n" +
                ChatColor.LIGHT_PURPLE + "Click an item in your inventory!",1, true);
    }

    public static ItemStack getMysticWellItem(String uuid, ItemStack itemStack){
        if(itemStack==null||
        itemStack.getType().equals(Material.AIR)) return getMysticWellItem("");

        if(itemStack.getType().equals(Material.GOLD_SWORD)){
            GoldData.hasEconomy(uuid);

            NBTItem nbtItem = new NBTItem(itemStack);

            if(nbtItem.hasKey("mysticTier") && nbtItem.getInteger("mysticTier")>=3) return ClayMaker((short) 14, ChatColor.RED + "Mystic Well",
                    ColorUtil.colorCode("&7This item cannot be upgraded any\n&7further.\n\n&cMaxed out upgrade tier!"));

            int tokens = MysticSword.getTokens(itemStack.getItemMeta().getLore());

            if(tokens==0 && GoldData.getEconomy(uuid)>=1000){
                return ItemMaker(Material.ENCHANTMENT_TABLE, ChatColor.LIGHT_PURPLE + "Mystic Well",
                        ChatColor.GRAY+"Upgrade: " + ChatColor.RED + "Tier I\n" +
                                ChatColor.GRAY+"Cost: " + ChatColor.GOLD + "1,000g\n\n"+
                                ChatColor.YELLOW + "Click to enchant!",1, true);
            }else if(tokens==0 && GoldData.getEconomy(uuid)<1000){
                return ItemMaker(Material.ENCHANTMENT_TABLE, ChatColor.LIGHT_PURPLE + "Mystic Well",
                        ChatColor.GRAY+"Upgrade: " + ChatColor.RED + "Tier I\n" +
                                ChatColor.GRAY+"Cost: " + ChatColor.GOLD + "1,000g\n\n"+
                                ChatColor.RED + "Not enough gold!",1, true);
            }

            if(CheckEnchantOnSword(itemStack.getItemMeta().getLore()).size()==1 && !itemStack.getItemMeta().getItemFlags().contains(ItemFlag.HIDE_ENCHANTS) &&
                    GoldData.getEconomy(uuid)>=4000){
                return ItemMaker(Material.ENCHANTMENT_TABLE, ChatColor.LIGHT_PURPLE + "Mystic Well",
                        ChatColor.GRAY+"Upgrade: " + ChatColor.RED + "Tier II\n" +
                                ChatColor.GRAY+"Cost: " + ChatColor.GOLD + "4,000g\n\n"+
                                ChatColor.YELLOW + "Click to enchant!",1, true);
            }else if(CheckEnchantOnSword(itemStack.getItemMeta().getLore()).size()==1 && !itemStack.getItemMeta().getItemFlags().contains(ItemFlag.HIDE_ENCHANTS) &&
                    GoldData.getEconomy(uuid)<4000){
                return ItemMaker(Material.ENCHANTMENT_TABLE, ChatColor.LIGHT_PURPLE + "Mystic Well",
                        ChatColor.GRAY+"Upgrade: " + ChatColor.RED + "Tier II\n" +
                                ChatColor.GRAY+"Cost: " + ChatColor.GOLD + "4,000g\n\n"+
                                ChatColor.RED + "Not enough gold!",1, true);
            }

            if(itemStack.getItemMeta().getItemFlags().contains(ItemFlag.HIDE_ENCHANTS) &&
                    GoldData.getEconomy(uuid)>=8000
            ){
                return ItemMaker(Material.ENCHANTMENT_TABLE, ChatColor.LIGHT_PURPLE + "Mystic Well",
                        ChatColor.GRAY+"Upgrade: " + ChatColor.RED + "Tier III\n" +
                                ChatColor.GRAY+"Cost: " + ChatColor.GOLD + "8,000g\n\n"+
                                ChatColor.YELLOW + "Click to enchant!",1, true);
            }else if(itemStack.getItemMeta().getItemFlags().contains(ItemFlag.HIDE_ENCHANTS) &&
                    GoldData.getEconomy(uuid)<8000
            ){
                return ItemMaker(Material.ENCHANTMENT_TABLE, ChatColor.LIGHT_PURPLE + "Mystic Well",
                        ChatColor.GRAY+"Upgrade: " + ChatColor.RED + "Tier III\n" +
                                ChatColor.GRAY+"Cost: " + ChatColor.GOLD + "8,000g\n\n"+
                                ChatColor.RED + "Not enough gold!",1, true);
            }
        }else if(itemStack.getType().equals(Material.BOW)){
            GoldData.hasEconomy(uuid);

            NBTItem nbtItem = new NBTItem(itemStack);

            if(nbtItem.hasKey("mysticTier") && nbtItem.getInteger("mysticTier")>=3) return ClayMaker((short) 14, ChatColor.RED + "Mystic Well",
                    ColorUtil.colorCode("&7This item cannot be upgraded any\n&7further.\n\n&cMaxed out upgrade tier!"));

            if(itemStack.getItemMeta().getDisplayName().contains("Mystic Bow")  && GoldData.getEconomy(uuid)>=1000){
                return ItemMaker(Material.ENCHANTMENT_TABLE, ChatColor.LIGHT_PURPLE + "Mystic Well",
                        ChatColor.GRAY+"Upgrade: " + ChatColor.RED + "Tier I\n" +
                                ChatColor.GRAY+"Cost: " + ChatColor.GOLD + "1,000g\n\n"+
                                ChatColor.YELLOW + "Click to enchant!",1, true);
            }else if(itemStack.getItemMeta().getDisplayName().contains("Mystic Bow")  && GoldData.getEconomy(uuid)<1000){
                return ItemMaker(Material.ENCHANTMENT_TABLE, ChatColor.LIGHT_PURPLE + "Mystic Well",
                        ChatColor.GRAY+"Upgrade: " + ChatColor.RED + "Tier I\n" +
                                ChatColor.GRAY+"Cost: " + ChatColor.GOLD + "1,000g\n\n"+
                                ChatColor.RED + "Not enough gold!",1, true);
            }

            if(!itemStack.getItemMeta().getDisplayName().contains("Tier II") &&
                    itemStack.getItemMeta().getDisplayName().contains("Tier I") &&
                    GoldData.getEconomy(uuid)>=4000){
                return ItemMaker(Material.ENCHANTMENT_TABLE, ChatColor.LIGHT_PURPLE + "Mystic Well",
                        ChatColor.GRAY+"Upgrade: " + ChatColor.RED + "Tier II\n" +
                                ChatColor.GRAY+"Cost: " + ChatColor.GOLD + "4,000g\n\n"+
                                ChatColor.YELLOW + "Click to enchant!",1, true);
            }else if(!itemStack.getItemMeta().getDisplayName().contains("Tier II") &&
                    itemStack.getItemMeta().getDisplayName().contains("Tier I")  &&
                    GoldData.getEconomy(uuid)<4000){
                return ItemMaker(Material.ENCHANTMENT_TABLE, ChatColor.LIGHT_PURPLE + "Mystic Well",
                        ChatColor.GRAY+"Upgrade: " + ChatColor.RED + "Tier II\n" +
                                ChatColor.GRAY+"Cost: " + ChatColor.GOLD + "4,000g\n\n"+
                                ChatColor.RED + "Not enough gold!",1, true);
            }

            if(itemStack.getItemMeta().getDisplayName().contains("Tier II")&&
                    GoldData.getEconomy(uuid)>=8000
            ){
                return ItemMaker(Material.ENCHANTMENT_TABLE, ChatColor.LIGHT_PURPLE + "Mystic Well",
                        ChatColor.GRAY+"Upgrade: " + ChatColor.RED + "Tier III\n" +
                                ChatColor.GRAY+"Cost: " + ChatColor.GOLD + "8,000g\n\n"+
                                ChatColor.YELLOW + "Click to enchant!",1, true);
            }else if(itemStack.getItemMeta().getDisplayName().contains("Tier II") &&
                    GoldData.getEconomy(uuid)<8000
            ){
                return ItemMaker(Material.ENCHANTMENT_TABLE, ChatColor.LIGHT_PURPLE + "Mystic Well",
                        ChatColor.GRAY+"Upgrade: " + ChatColor.RED + "Tier III\n" +
                                ChatColor.GRAY+"Cost: " + ChatColor.GOLD + "8,000g\n\n"+
                                ChatColor.RED + "Not enough gold!",1, true);
            }
        }else if(itemStack.getType().equals(Material.LEATHER_LEGGINGS)){
            GoldData.hasEconomy(uuid);

            NBTItem nbtItem = new NBTItem(itemStack);

            if(nbtItem.hasKey("mysticTier") && nbtItem.getInteger("mysticTier")>=3) return ClayMaker((short) 14, ChatColor.RED + "Mystic Well",
                    ColorUtil.colorCode("&7This item cannot be upgraded any\n&7further.\n\n&cMaxed out upgrade tier!"));

            if(nbtItem.hasKey("darkPant")){

                if(nbtItem.hasKey("darktier") && nbtItem.getInteger("darktier")>=2){
                    return getMysticWellItem("");
                }

                if(!nbtItem.hasKey("darktier") && GoldData.getEconomy(uuid)>=50000){
                    return ItemMaker(Material.ENCHANTMENT_TABLE, ChatColor.LIGHT_PURPLE + "Mystic Well",
                            ChatColor.GRAY+"Upgrade: " + ChatColor.DARK_PURPLE + "Tier I\n" +
                                    ChatColor.GRAY+"Cost: " + ChatColor.GOLD + "50,000g\n\n"+
                                    ChatColor.YELLOW + "Click to enchant!",1, true);
                }else if(!nbtItem.hasKey("darktier") && GoldData.getEconomy(uuid)<50000){
                    return ItemMaker(Material.ENCHANTMENT_TABLE, ChatColor.LIGHT_PURPLE + "Mystic Well",
                            ChatColor.GRAY+"Upgrade: " + ChatColor.DARK_PURPLE + "Tier I\n" +
                                    ChatColor.GRAY+"Cost: " + ChatColor.GOLD + "50,000g\n\n"+
                                    ChatColor.RED + "Not enough gold!",1, true);
                }

                if(nbtItem.hasKey("darktier") && GoldData.getEconomy(uuid)>=100000){
                    return ItemMaker(Material.ENCHANTMENT_TABLE, ChatColor.LIGHT_PURPLE + "Mystic Well",
                            ChatColor.GRAY+"Upgrade: " + ChatColor.DARK_PURPLE + "Tier II\n" +
                                    ChatColor.GRAY+"Cost: " + ChatColor.GOLD + "100,000g\n\n"+
                                    ChatColor.YELLOW + "Click to enchant!",1, true);
                }else if(nbtItem.hasKey("darktier") && GoldData.getEconomy(uuid)<100000){
                    return ItemMaker(Material.ENCHANTMENT_TABLE, ChatColor.LIGHT_PURPLE + "Mystic Well",
                            ChatColor.GRAY+"Upgrade: " + ChatColor.DARK_PURPLE + "Tier II\n" +
                                    ChatColor.GRAY+"Cost: " + ChatColor.GOLD + "100,000g\n\n"+
                                    ChatColor.RED + "Not enough gold!",1, true);
                }
            }

            int tokens = FreshPants.getTokens(itemStack.getItemMeta().getLore());

            if(tokens==0 && GoldData.getEconomy(uuid)>=1000){
                return ItemMaker(Material.ENCHANTMENT_TABLE, ChatColor.LIGHT_PURPLE + "Mystic Well",
                        ChatColor.GRAY+"Upgrade: " + ChatColor.RED + "Tier I\n" +
                                ChatColor.GRAY+"Cost: " + ChatColor.GOLD + "1,000g\n\n"+
                                ChatColor.YELLOW + "Click to enchant!",1, true);
            }else if(tokens==0 && GoldData.getEconomy(uuid)<1000){
                return ItemMaker(Material.ENCHANTMENT_TABLE, ChatColor.LIGHT_PURPLE + "Mystic Well",
                        ChatColor.GRAY+"Upgrade: " + ChatColor.RED + "Tier I\n" +
                                ChatColor.GRAY+"Cost: " + ChatColor.GOLD + "1,000g\n\n"+
                                ChatColor.RED + "Not enough gold!",1, true);
            }

            if(loreChecker.CheckEnchantOnPant(itemStack.getItemMeta().getLore()).size()==1&&
                    !itemStack.getItemMeta().getItemFlags().contains(ItemFlag.HIDE_ATTRIBUTES)  &&
                    GoldData.getEconomy(uuid)>=4000){
                return ItemMaker(Material.ENCHANTMENT_TABLE, ChatColor.LIGHT_PURPLE + "Mystic Well",
                        ChatColor.GRAY+"Upgrade: " + ChatColor.RED + "Tier II\n" +
                                ChatColor.GRAY+"Cost: " + ChatColor.GOLD + "4,000g\n\n"+
                                ChatColor.YELLOW + "Click to enchant!",1, true);
            }else if(loreChecker.CheckEnchantOnPant(itemStack.getItemMeta().getLore()).size()==1&&
                    !itemStack.getItemMeta().getItemFlags().contains(ItemFlag.HIDE_ATTRIBUTES) &&
                    GoldData.getEconomy(uuid)<4000){
                return ItemMaker(Material.ENCHANTMENT_TABLE, ChatColor.LIGHT_PURPLE + "Mystic Well",
                        ChatColor.GRAY+"Upgrade: " + ChatColor.RED + "Tier II\n" +
                                ChatColor.GRAY+"Cost: " + ChatColor.GOLD + "4,000g\n\n"+
                                ChatColor.RED + "Not enough gold!",1, true);
            }

            if(itemStack.getItemMeta().getItemFlags().contains(ItemFlag.HIDE_ATTRIBUTES) &&
                    !itemStack.getItemMeta().getItemFlags().contains(ItemFlag.HIDE_ENCHANTS) &&
                    GoldData.getEconomy(uuid)>=8000
            ){
                return ItemMaker(Material.ENCHANTMENT_TABLE, ChatColor.LIGHT_PURPLE + "Mystic Well",
                        ChatColor.GRAY+"Upgrade: " + ChatColor.RED + "Tier III\n" +
                                ChatColor.GRAY+"Cost: " + ChatColor.GOLD + "8,000g\n\n"+
                                ChatColor.YELLOW + "Click to enchant!",1, true);
            }else if(itemStack.getItemMeta().getItemFlags().contains(ItemFlag.HIDE_ATTRIBUTES) &&
                    !itemStack.getItemMeta().getItemFlags().contains(ItemFlag.HIDE_ENCHANTS) &&
                    GoldData.getEconomy(uuid)<8000
            ){
                return ItemMaker(Material.ENCHANTMENT_TABLE, ChatColor.LIGHT_PURPLE + "Mystic Well",
                        ChatColor.GRAY+"Upgrade: " + ChatColor.RED + "Tier III\n" +
                                ChatColor.GRAY+"Cost: " + ChatColor.GOLD + "8,000g\n\n"+
                                ChatColor.RED + "Not enough gold!",1, true);
            }
        }

        return ItemMaker(Material.ENCHANTMENT_TABLE, ChatColor.LIGHT_PURPLE + "Mystic Well",
                ChatColor.GRAY + "Find a " + ChatColor.AQUA + "Mystic Bow" + ChatColor.GRAY + ", " + ChatColor.YELLOW + "Mystic\n" +
                        ChatColor.YELLOW + "Sword " + ChatColor.GRAY + "or " + ChatColor.RED + "P" + ChatColor.GOLD + "a" + ChatColor.YELLOW + "n" + ChatColor.GREEN + "t" + ChatColor.BLUE + "s " + ChatColor.GRAY + "from\n" +
                        ChatColor.GRAY + "killing players.\n\n" +
                        ChatColor.GRAY + "Enchant these items in the well\n" +
                        ChatColor.GRAY + "for tons of buffs.\n\n" +
                        ChatColor.LIGHT_PURPLE + "Click an item in your inventory!",1, true);
    }

    public static void openMysticWell(Player player){
        String uuid = player.getUniqueId().toString();
        Inventory gui = advancedInventory.inv(player, 45, "Mystic Well");
        ItemStack base_glass = advancedInventory.cGlass();
        ItemStack mystic = gui.getItem(20);

        boolean empty = mystic == null || mystic.getType().equals(Material.AIR);

        ItemStack dGlass = advancedInventory.dGlass(empty);

        for (int i = 0; i < 10; i++) {
            advancedInventory.addInv(gui, base_glass, i, 1, false);
            advancedInventory.addInv(gui, base_glass, i, 2, false);
            advancedInventory.addInv(gui, base_glass, i, 3, false);
            advancedInventory.addInv(gui, base_glass, i, 4, false);
            advancedInventory.addInv(gui, base_glass, i, 5, false);
        }

        for(int i = 0; i < 3; i++){
            advancedInventory.addInv(gui, dGlass, 2+i, 2, false);
        }

        for(int i = 0; i < 3; i++){
            advancedInventory.addInv(gui, dGlass, 2+i, 4, false);
        }

        advancedInventory.addInv(gui, dGlass, 2, 3, false);
        advancedInventory.addInv(gui, dGlass, 4, 3, false);

        advancedInventory.addInv(gui, getMysticWellItem(uuid), 7, 3, false);

        advancedInventory.addInv(gui, null, 3, 3, true);

        player.openInventory(gui);

    }

    public static Inventory openMysticWell(Player player, ItemStack itemStack){
        String uuid = player.getUniqueId().toString();
        Inventory gui = advancedInventory.inv(player, 45, "Mystic Well");
        ItemStack base_glass = advancedInventory.cGlass();

        ItemStack mystic = gui.getItem(20);

        boolean empty = mystic == null || mystic.getType().equals(Material.AIR);

        ItemStack dGlass = advancedInventory.dGlass(empty);

        for (int i = 0; i < 10; i++) {
            advancedInventory.addInv(gui, base_glass, i, 1, false);
            advancedInventory.addInv(gui, base_glass, i, 2, false);
            advancedInventory.addInv(gui, base_glass, i, 3, false);
            advancedInventory.addInv(gui, base_glass, i, 4, false);
            advancedInventory.addInv(gui, base_glass, i, 5, false);
        }

        for(int i = 0; i < 3; i++){
            advancedInventory.addInv(gui, dGlass, 2+i, 2, false);
        }

        for(int i = 0; i < 3; i++){
            advancedInventory.addInv(gui, dGlass, 2+i, 4, false);
        }

        advancedInventory.addInv(gui, dGlass, 2, 3, false);
        advancedInventory.addInv(gui, dGlass, 4, 3, false);

        advancedInventory.addInv(gui, getMysticWellItem(uuid, itemStack), 7, 3, false);

        advancedInventory.addInv(gui, itemStack, 3, 3, false);

        return gui;
    }
}
