package me.alpha.kitpvp.PitRemake.Pets.PetGUI;

import me.alpha.kitpvp.Data.ClassInstances;
import me.alpha.kitpvp.PitRemake.RenownShop.gui.RenownShopKillstreaksGUI;
import me.alpha.kitpvp.PitRemake.RenownShop.gui.RenownShopUpgradesGUI;
import me.alpha.kitpvp.utils.Sounds;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import static me.alpha.kitpvp.utils.ColorUtil.colorCode;
import static me.alpha.kitpvp.utils.advancedInventory.ItemMaker;

public class PetMenu implements Listener {
    public static ItemStack getPetItem(String uuid){
        String pet = ClassInstances.petData.getPet(uuid);

        if(pet.equalsIgnoreCase(ClassInstances.xpDragon.getRefID())){
            return ItemMaker(Material.ARROW, ClassInstances.xpDragon.getNameColor() + ClassInstances.xpDragon.getPetName(),
                    ChatColor.GRAY+"",1, true);
        }

        return ItemMaker(Material.ARROW, ChatColor.RED + "Close",
                ChatColor.GRAY+"",1, true);
    }

    @EventHandler
    public void HandleInventoryClick(InventoryClickEvent event){
        if(event.getClickedInventory() != null &&
                event.getClickedInventory().getTitle() != null &&
                !event.getClickedInventory().getTitle().equals(ChatColor.GRAY + "Pet Menu")) return;

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
