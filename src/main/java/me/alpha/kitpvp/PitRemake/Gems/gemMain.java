package me.alpha.kitpvp.PitRemake.Gems;

import me.alpha.kitpvp.utils.advancedInventory;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

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

    public static ItemStack confirmButton = advancedInventory.ClayMaker((short) 5, ChatColor.GREEN + "Confirm", ChatColor.GRAY + "This action cannot be\n" +
            ChatColor.GRAY + "undone, careful!");

    public static ItemStack cancelButton = advancedInventory.ClayMaker((short) 14, ChatColor.RED + "Cancel", ChatColor.GRAY + "This action can be\n" +
            ChatColor.GRAY + "undone, careful!");

    public static Inventory makeGemGUI(Player player){
        Inventory gui = advancedInventory.inv(player, 27, ChatColor.GRAY + "Totally Legit Gem");


        ItemStack base_glass = advancedInventory.cGlass();
        List<ItemStack> mysticItems = new ArrayList<ItemStack>();


        for (ItemStack itemStack : player.getInventory().getContents()) {
            if(itemStack != null){
                if (itemStack.getType().equals(Material.LEATHER_LEGGINGS)) {
                    if (ChatColor.stripColor(itemStack.getItemMeta().getDisplayName()).contains("Tier")) {
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
                    if (ChatColor.stripColor(itemStack.getItemMeta().getDisplayName()).contains("Tier")) {
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
                    if (itemStack!=null&&
                    itemStack.getItemMeta()!=null&&
                    itemStack.getItemMeta().getDisplayName()!=null&&
                            ChatColor.stripColor(itemStack.getItemMeta().getDisplayName()).contains("Tier")) {
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

    public static Inventory confirmationGui(Player player){
        Inventory gui = advancedInventory.inv(player, 27, ChatColor.GRAY + "Confirm or Cancel");


        ItemStack base_glass = advancedInventory.cGlass();

        for (int i = 0; i < gui.getSize(); i++) if (gui.getItem(i) == null) gui.setItem(i, base_glass);

        gui.setItem(12, confirmButton);
        gui.setItem(14, cancelButton);


        return gui;
    }
}
