package me.alpha.kitpvp.PitRemake.PitSupporter;

import me.alpha.kitpvp.CustomEvents.ReduxDeathEvent;
import me.alpha.kitpvp.utils.ColorUtil;
import me.alpha.kitpvp.utils.Sounds;
import me.alpha.kitpvp.utils.advancedInventory;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import static me.alpha.kitpvp.utils.advancedInventory.ItemMaker;

public class PitSupporterCore implements Listener {
    public static void pitCommand(Player player){
        if(!player.hasPermission("pitSupporter")) return;

        Sounds.BOOSTER_REMIND.play(player);
        if(player.hasPermission("pitSupporterPlus")){
            player.sendMessage(ColorUtil.colorCode("&ePit Supporter&f+&7: pit supporter has multiple perks" +
                    ". For right now &ePit Supporter&f+&7 only has these perks:"));
            player.sendMessage(ColorUtil.colorCode("&e- &b+25% xp (+200 xp cap)"));
            player.sendMessage(ColorUtil.colorCode("&e- &6+25% gold"));
            player.sendMessage(ColorUtil.colorCode("&e- &7A cool suffix!"));
            player.sendMessage(ColorUtil.colorCode("&e- &7More coming soon!"));
        }else{
            player.sendMessage(ColorUtil.colorCode("&ePit Supporter&7: pit supporter has multiple perks" +
                    ". For right now &ePit Supporter&7 only has these perks:"));
            player.sendMessage(ColorUtil.colorCode("&e- &b+10% xp (+100 xp cap)"));
            player.sendMessage(ColorUtil.colorCode("&e- &6+10% gold"));
            player.sendMessage(ColorUtil.colorCode("&e- &7A cool suffix!"));
            player.sendMessage(ColorUtil.colorCode("&e- &7More coming soon!"));
        }
    }

    public void doBenefits(ReduxDeathEvent event){

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
