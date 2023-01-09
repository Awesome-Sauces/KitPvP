package me.alpha.kitpvp.PitRemake.PitSupporter;

import me.alpha.kitpvp.utils.ColorUtil;
import me.alpha.kitpvp.utils.Sounds;
import me.alpha.kitpvp.utils.advancedInventory;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import static me.alpha.kitpvp.utils.advancedInventory.ItemMaker;

public class PitSupporterCore {
    public static void pitCommand(Player player){
        if(!player.hasPermission("pitsupporter")) return;

        Sounds.BOOSTER_REMIND.play(player);
    }

    public static ItemStack getCloseItem(){
        return ItemMaker(Material.BARRIER, ChatColor.RED + "Close",
                "NULL",1, true);
    }

    public static Inventory getPitSupporterMenu(Player player){
        String uuid = String.valueOf(player.getUniqueId());
        Inventory gui = advancedInventory.inv(player, 45, ChatColor.GRAY + "Pit Supporter");
        ItemStack base_glass = advancedInventory.cGlass();

        for (int i = 0; i < 10; i++) {
            advancedInventory.addInv(gui, base_glass, i, 1, false);
            advancedInventory.addInv(gui, base_glass, i, 2, false);
            advancedInventory.addInv(gui, base_glass, i, 3, false);
            advancedInventory.addInv(gui, base_glass, i, 4, false);
            advancedInventory.addInv(gui, base_glass, i, 5, false);
        }

        advancedInventory.addInv(gui, getCloseItem(), 5, 5, false);


        return gui;
    }
}
