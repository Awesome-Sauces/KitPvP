package me.alpha.kitpvp.PitRemake.Gems;

import de.tr7zw.nbtapi.NBTCompound;
import de.tr7zw.nbtapi.NBTItem;
import me.alpha.kitpvp.PitRemake.MysticWell.enchanters.FreshPants;
import me.alpha.kitpvp.PitRemake.MysticWell.enchanters.MysticBow;
import me.alpha.kitpvp.PitRemake.MysticWell.enchanters.MysticSword;
import me.alpha.kitpvp.utils.ColorUtil;
import me.alpha.kitpvp.utils.IntegerHelper;
import me.alpha.kitpvp.utils.advancedInventory;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.LeatherArmorMeta;

import java.util.ArrayList;
import java.util.List;
import static me.alpha.kitpvp.PitRemake.MysticWell.loreChecker.*;

public class gemMain {

    public static boolean getEnchants(List<String> lore, String mysticType, boolean f){

        List<String> list = new ArrayList<>();

        if(MysticType.valueOf(mysticType).equals(MysticType.PANT)){
            list = CheckEnchantOnPant(lore);
        }else if(MysticType.valueOf(mysticType).equals(MysticType.SWORD)){
            list = CheckEnchantOnSword(lore);
        }else if(MysticType.valueOf(mysticType).equals(MysticType.BOW)){
            list = CheckEnchantOnBow(lore);
        }
        int hasEnchants = 0;

        String accepted = ChatColor.GREEN + " ACCEPTED";


        for(String obj : list){
            if(obj.contains("III"))
                hasEnchants += 1;
        }

        if(hasEnchants == list.size()) accepted = ChatColor.RED + " REJECTED";

        return !(hasEnchants == list.size());
    }

    public static ItemStack goBackButton = advancedInventory.ItemMaker(Material.ARROW, ChatColor.GREEN + "Go Back", ChatColor.GRAY + "To Totally Legit Selector", 1, true);

    public static ItemStack confirmButton = advancedInventory.ClayMaker((short) 5, ChatColor.GREEN + "Confirm", ChatColor.GRAY + "This action cannot be\n" +
            ChatColor.GRAY + "undone, careful!");

    public static ItemStack cancelButton = advancedInventory.ClayMaker((short) 14, ChatColor.RED + "Cancel", ChatColor.GRAY + "This action can be\n" +
            ChatColor.GRAY + "undone, careful!");

    public static Inventory makeGemGUI(Player player){
        Inventory gui = advancedInventory.inv(player, 27, ChatColor.GRAY + "Totally Legit Selector");


        ItemStack base_glass = advancedInventory.cGlass();
        List<ItemStack> mysticItems = new ArrayList<ItemStack>();


        for (ItemStack itemStack : player.getInventory().getContents()) {
            if(itemStack != null){
                if (itemStack.getType().equals(Material.LEATHER_LEGGINGS)) {
                    NBTItem nbtItem = new NBTItem(itemStack);
                    if(!nbtItem.getOrCreateCompound("enchants").getKeys().isEmpty() &&
                            !nbtItem.hasKey("gem")) {
                        for (int i = 0; i < gui.getSize(); i++) {
                            if (gui.getItem(i) == null) {
                                if(getEnchants(itemStack.getItemMeta().getLore(), "PANT", false))
                                    gui.setItem(i, itemStack);
                                break;
                            }
                        }
                    }
                }

                if (itemStack.getType().equals(Material.GOLD_SWORD) && itemStack.getItemMeta() != null) {
                    NBTItem nbtItem = new NBTItem(itemStack);
                    if(!nbtItem.getOrCreateCompound("enchants").getKeys().isEmpty() &&
                            !nbtItem.hasKey("gem")) {
                        for (int i = 0; i < gui.getSize(); i++) {
                            if (gui.getItem(i) == null) {
                                if(getEnchants(itemStack.getItemMeta().getLore(), "SWORD", false))
                                    gui.setItem(i, itemStack);
                                break;
                            }
                        }
                    }
                }

                if (itemStack.getType().equals(Material.BOW)) {
                    NBTItem nbtItem = new NBTItem(itemStack);
                        if (itemStack!=null&&
                    itemStack.getItemMeta()!=null&&
                    itemStack.getItemMeta().getDisplayName()!=null&&
                    !nbtItem.getOrCreateCompound("enchants").getKeys().isEmpty() &&
                        !nbtItem.hasKey("gem")) {
                        for (int i = 0; i < gui.getSize(); i++) {
                            if (gui.getItem(i) == null) {
                                if(getEnchants(itemStack.getItemMeta().getLore(), "BOW", false))
                                    gui.setItem(i, itemStack);
                                break;
                            }
                        }
                    }
                }
            }


        }

        for (int i = 0; i < gui.getSize(); i++) if (gui.getItem(i) == null) gui.setItem(i, base_glass);




        return gui;
    }

    public static ItemStack getEnchantItem(ItemStack item, String enchant, int tier, int slot){
        Material material = item.getType();

        if(tier>=3){

            if(material.equals(Material.LEATHER_LEGGINGS)){
                return advancedInventory.LeggingsMaker(((LeatherArmorMeta)item.getItemMeta()).getColor(),
                        ChatColor.YELLOW + "Upgrade Slot " + slot,
                        ColorUtil.colorCode("\n&7Upgrade: &9" + enchant +
                                "\n&7Tier: &e" + IntegerHelper.integerToRoman(tier) +
                                "\n\n&7Cost: &a1 Totally Legit Gem\n\n&cMaxed out!"));
            }

            return advancedInventory.ItemMaker(material,
                    ChatColor.YELLOW + "Upgrade Slot " + slot,
                    ColorUtil.colorCode("\n&7Upgrade: &9" + enchant +
                            "\n&7Tier: &e" + IntegerHelper.integerToRoman(tier) +
                            "\n\n&7Cost: &a1 Totally Legit Gem\n\n&cMaxed out!"), 1, true);
        }

        if(material.equals(Material.LEATHER_LEGGINGS)){
            return advancedInventory.LeggingsMaker(((LeatherArmorMeta)item.getItemMeta()).getColor(),
                    ChatColor.YELLOW + "Upgrade Slot " + slot,
                    ColorUtil.colorCode("\n&7Upgrade: &9" + enchant +
                            "\n&7Tier: &e" + IntegerHelper.integerToRoman(tier) +
                            "\n\n&7Cost: &a1 Totally Legit Gem\n\n&eClick to tier up!"));
        }

        return advancedInventory.ItemMaker(material,
                ChatColor.YELLOW + "Upgrade Slot " + slot,
                ColorUtil.colorCode("\n&7Upgrade: &9" + enchant +
                        "\n&7Tier: &e" + IntegerHelper.integerToRoman(tier) +
                        "\n\n&7Cost: &a1 Totally Legit Gem\n\n&eClick to tier up!"), 1, true);
    }

    public static Inventory gemItemGUI(Player player, ItemStack itemStack){
        Inventory gui = advancedInventory.inv(player, 27, ChatColor.GRAY + "Totally Legit Gem");


        ItemStack base_glass = advancedInventory.cGlass();

        NBTItem nbtItem = new NBTItem(itemStack);

        NBTCompound nbtCompound = nbtItem.getOrCreateCompound("enchants");

        int count = nbtCompound.getKeys().size();

        ItemStack itemStack1 = null;
        ItemStack itemStack2 = null;
        ItemStack itemStack3 = null;

        if(itemStack.getType().equals(Material.GOLD_SWORD)){
            for(String key : nbtCompound.getKeys()){
                if(itemStack1==null){
                    itemStack1=getEnchantItem(itemStack, key, nbtCompound.getInteger(key), 1);
                }else if(itemStack2==null){
                    itemStack2=getEnchantItem(itemStack, key, nbtCompound.getInteger(key), 2);
                }else if(itemStack3==null){
                    itemStack3=getEnchantItem(itemStack, key, nbtCompound.getInteger(key), 3);
                }
            }
        }else if(itemStack.getType().equals(Material.LEATHER_LEGGINGS)){
            for(String key : nbtCompound.getKeys()){
                if(itemStack1==null){
                    itemStack1=getEnchantItem(itemStack, key, nbtCompound.getInteger(key), 1);
                }else if(itemStack2==null){
                    itemStack2=getEnchantItem(itemStack, key, nbtCompound.getInteger(key), 2);
                }else if(itemStack3==null){
                    itemStack3=getEnchantItem(itemStack, key, nbtCompound.getInteger(key), 3);
                }
            }
        }else if(itemStack.getType().equals(Material.BOW)){
            for(String key : nbtCompound.getKeys()){
                if(itemStack1==null){
                    itemStack1=getEnchantItem(itemStack, key, nbtCompound.getInteger(key), 1);
                }else if(itemStack2==null){
                    itemStack2=getEnchantItem(itemStack, key, nbtCompound.getInteger(key), 2);
                }else if(itemStack3==null){
                    itemStack3=getEnchantItem(itemStack, key, nbtCompound.getInteger(key), 3);
                }
            }
        }

        if(itemStack1!=null) advancedInventory.addInv(gui, itemStack1, 3, 2, false);
        if(itemStack2!=null) advancedInventory.addInv(gui, itemStack2, 5, 2, false);
        if(itemStack3!=null) advancedInventory.addInv(gui, itemStack3, 7, 2, false);

        for (int i = 0; i < gui.getSize(); i++) if (gui.getItem(i) == null) gui.setItem(i, base_glass);




        return gui;
    }

    public static Inventory confirmationGui(Player player){
        Inventory gui = advancedInventory.inv(player, 27, ChatColor.GRAY + "Confirm or Cancel");


        ItemStack base_glass = advancedInventory.cGlass();

        for (int i = 0; i < gui.getSize(); i++) if (gui.getItem(i) == null) gui.setItem(i, base_glass);

        gui.setItem(12, confirmButton);
        gui.setItem(14, cancelButton);


        return gui;
    }
}
