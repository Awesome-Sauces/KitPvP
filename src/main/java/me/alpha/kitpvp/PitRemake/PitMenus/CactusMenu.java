package me.alpha.kitpvp.PitRemake.PitMenus;

import me.alpha.kitpvp.PitRemake.ItemStacks.enchants;
import me.alpha.kitpvp.utils.advancedInventory;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class CactusMenu {
    public static Inventory inventoryConstructor(Player player){

        Inventory gui = advancedInventory.inv(player, 27, ChatColor.GRAY + "Philosopher's Cactus");
        ItemStack base_glass = advancedInventory.cGlass();

        gui.setItem(11, enchants.fresh_greens);
        gui.setItem(12, enchants.fresh_blues);
        gui.setItem(13, enchants.fresh_reds);
        gui.setItem(14, enchants.fresh_oranges);
        gui.setItem(15, enchants.fresh_yellows);

        for (int i = 0; i < gui.getSize(); i++) if (gui.getItem(i) == null) gui.setItem(i, base_glass);

        return gui;
    }
}
