package me.alpha.kitpvp.PitRemake.PitCommands.Repairs;

import me.alpha.kitpvp.utils.advancedInventory;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import static me.alpha.kitpvp.PitRemake.Gems.gemMain.cancelButton;
import static me.alpha.kitpvp.PitRemake.Gems.gemMain.confirmButton;

public class menu {
    public static Inventory confirmationGui(Player player){
        Inventory gui = advancedInventory.inv(player, 27, ChatColor.GRAY + "Mystic Repair");


        ItemStack base_glass = advancedInventory.cGlass();

        for (int i = 0; i < gui.getSize(); i++) if (gui.getItem(i) == null) gui.setItem(i, base_glass);

        gui.setItem(11, confirmButton);
        gui.setItem(13, null);
        gui.setItem(15, cancelButton);


        return gui;
    }
}
