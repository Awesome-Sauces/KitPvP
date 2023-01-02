package me.alpha.kitpvp.PitRemake.RenownShop.gui;

import me.alpha.kitpvp.Data.ClassInstances;
import me.alpha.kitpvp.utils.Sounds;
import me.alpha.kitpvp.utils.advancedInventory;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import static me.alpha.kitpvp.utils.ColorUtil.colorCode;
import static me.alpha.kitpvp.utils.advancedInventory.ItemMaker;

public class RenownShopGUI implements Listener {
    public static ItemStack getKillStreaksItem(String uuid){
        return ItemMaker(Material.BLAZE_POWDER, ChatColor.RED + "Killstreaks",
                colorCode("&7Pick bundles of killstreaks to\n" +
                        "&7unlock in the upgrades npc.\n\n" +
                        "&7Renown: &e" + ClassInstances.renownData.getRenown(uuid) +
                        " &eRenown\n\n&eClick to browse!"),1, true);
    }

    public static ItemStack getShopItems(String uuid){
        return ItemMaker(Material.GOLD_BARDING, ChatColor.GOLD + "Shop Items",
                colorCode("&7Get access to new items in the\n" +
                        "&7non-permanent items shop.\n\n" +
                        "&7Renown: &e" + ClassInstances.renownData.getRenown(uuid) +
                        " &eRenown\n\n&eComing soon!"),1, true);
    }

    public static ItemStack getPerksItem(String uuid){
        return ItemMaker(Material.DIAMOND, ChatColor.AQUA + "Perks",
                colorCode("&7Unlock new perks available for\n" +
                        "&7purchase at the upgrades npc.\n\n" +
                        "&7Renown: &e" + ClassInstances.renownData.getRenown(uuid) +
                        " &eRenown\n\n&eComing soon!"),1, true);
    }

    public static ItemStack getUpgradesItem(String uuid){
        return ItemMaker(Material.EMERALD, ChatColor.GREEN + "Upgrades",
                colorCode("&7Variety of upgrades, buffs and\n" +
                        "&7special unlocks.\n\n" +
                        "&7Renown: &e" + ClassInstances.renownData.getRenown(uuid) +
                        " &eRenown\n\n&eClick to browse!"),1, true);
    }

    public static ItemStack getGoBackItem(String uuid){
        return ItemMaker(Material.ARROW, ChatColor.GREEN + "Go Back",
                ChatColor.GRAY+"To Prestige & Renown",1, true);
    }

    public static Inventory getRenownShopGUI(Player player){
        String uuid = String.valueOf(player.getUniqueId());
        Inventory gui = advancedInventory.inv(player, 27, ChatColor.GRAY + "Renown Shop");
        ItemStack base_glass = advancedInventory.cGlass();

        for (int i = 0; i < 10; i++) {
            advancedInventory.addInv(gui, base_glass, i, 1, false);
            advancedInventory.addInv(gui, base_glass, i, 2, false);
            advancedInventory.addInv(gui, base_glass, i, 3, false);
        }

        advancedInventory.addInv(gui, getUpgradesItem(uuid), 2, 2, false);
        advancedInventory.addInv(gui, getPerksItem(uuid), 4, 2, false);
        advancedInventory.addInv(gui, getShopItems(uuid), 6, 2, false);
        advancedInventory.addInv(gui, getKillStreaksItem(uuid), 8, 2, false);
        advancedInventory.addInv(gui, getGoBackItem(uuid), 5, 3, false);

        //advancedInventory.addInv(gui, getOverDriveItem(uuid), 2, 2, false);

        return gui;
    }

    @EventHandler
    public void HandleRenownShopClick(InventoryClickEvent event){
        if(event==null||
                event.getClickedInventory()==null) return;

        if(event.getClickedInventory() != null &&
                event.getClickedInventory().getTitle() != null &&
                !event.getClickedInventory().getTitle().equals(ChatColor.GRAY + "Renown Shop")) return;

        Player player = (Player) event.getWhoClicked();
        String uuid = player.getUniqueId().toString();

        event.setCancelled(true);

        if(event.getCurrentItem().getType().equals(Material.EMERALD)){
            player.openInventory(RenownShopUpgradesGUI.getRenownShopUpgradesGUI(player));
            Sounds.BUTTON.play(player);
        }else if(event.getCurrentItem().getType().equals(Material.BLAZE_POWDER)){
            player.openInventory(RenownShopKillstreaksGUI.getRenownShopKillstreaksGUI(player));
            Sounds.BUTTON.play(player);
        }else if(event.getCurrentItem().getType().equals(Material.ARROW)){
            //gui.PrestigeMenu(player);
            Sounds.BUTTON.play(player);
        }

    }
}
