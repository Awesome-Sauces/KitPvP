package me.alpha.kitpvp.PitRemake.QuestMaster;

import me.alpha.kitpvp.utils.advancedInventory;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class questMenu {
    public static Inventory makeMainMenu(Player player){
        Inventory gui = advancedInventory.inv(player, 27, ChatColor.YELLOW + "Quest Master");

        // Get String uuid of player in
        String uuid = String.valueOf(player.getUniqueId());

        // placeholder glass
        ItemStack base_glass = advancedInventory.cGlass();

        // Custom Items
        ItemStack claimRewards = questMenuItems.claimRewards(uuid);
        ItemStack KillPlayersQuest = questMenuItems.PlayerSoulsToFresh(uuid);
        ItemStack FindCakeQuest = questMenuItems.PlayerSoulsToMysticSword(uuid);
        ItemStack BringVileQuest = questMenuItems.PlayerSoulsToJewelPant(uuid);
        ItemStack BringCoinsQuest = questMenuItems.PlayerSoulsToJewelSword(uuid);
        ItemStack BringFreshQuest = questMenuItems.PlayerSoulsToMalding(uuid);

        for (int i = 0; i < 10; i++) {
            advancedInventory.addInv(gui, base_glass, i, 1, false);
            advancedInventory.addInv(gui, base_glass, i, 2, false);
            advancedInventory.addInv(gui, base_glass, i, 3, false);
        }

        advancedInventory.addInv(gui, claimRewards, 9, 3, false);

        advancedInventory.addInv(gui, BringFreshQuest, 3, 2, false);
        advancedInventory.addInv(gui, BringVileQuest, 4, 2, false);
        advancedInventory.addInv(gui, KillPlayersQuest, 5, 2, false);
        advancedInventory.addInv(gui, FindCakeQuest, 6, 2, false);
        advancedInventory.addInv(gui, BringCoinsQuest, 7, 2, false);

        return gui;
    }
}
