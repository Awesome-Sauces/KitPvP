package me.alpha.kitpvp.PitRemake.PitMenus;

import me.alpha.kitpvp.ChatManager.PrestigeBracketColors;
import me.alpha.kitpvp.Data.ClassInstances;
import me.alpha.kitpvp.utils.ColorUtil;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.text.DecimalFormat;

import static me.alpha.kitpvp.Data.GoldRequirementData.getGoldRequirement;
import static me.alpha.kitpvp.Data.PrestigeData.PrestigeXpAmount;
import static me.alpha.kitpvp.Data.XpData.GetCurrentLevel;
import static me.alpha.kitpvp.PitRemake.RenownShop.renownAmount.GetByPrestige;
import static me.alpha.kitpvp.utils.IntegerHelper.integerToRoman;
import static me.alpha.kitpvp.utils.advancedInventory.*;

public class PrestigeMenu {
    public static void PrestigeMenu(Player player){
        int[] randomDUDE = GetCurrentLevel(String.valueOf(player.getUniqueId()), ClassInstances.xpData.getXp(String.valueOf(player.getUniqueId())), ClassInstances.prestigeData.getPrestige(String.valueOf(player.getUniqueId())), player);
        String req_level;

        if (randomDUDE[0] >= 120){
            DecimalFormat formatter = new DecimalFormat("#,###");
            req_level = ChatColor.translateAlternateColorCodes('&', "&7Costs:" + "\n" +"&c&l⚫ Resets &blevel &c to 1" + "\n" + "&c&l⚫ Resets &6gold &c to 0" + "\n" + "&c&l⚫ Resets &cALL &aperks and upgrades" + "\n" +
                    "&c&l⚫ &c&lGrinded &6" + formatter.format(ClassInstances.goldRequirementData.getGoldReq(String.valueOf(player.getUniqueId()))) + "&c/&6" +
                    formatter.format(getGoldRequirement(ClassInstances.prestigeData.getPrestige(String.valueOf(player.getUniqueId())))) + "&6g"
                    + "\n\n" + "&7Reward: &e" + GetByPrestige(ClassInstances.prestigeData.getPrestige(String.valueOf(player.getUniqueId()))) + " &eRenown" + "\n\n" + "&7New prestige: &e" + integerToRoman(ClassInstances.prestigeData.getPrestige(String.valueOf(player.getUniqueId())) + 1) + "\n" + ChatColor.AQUA + "+" + ((int) PrestigeXpAmount(ClassInstances.prestigeData.getPrestige(String.valueOf(player.getUniqueId()))) * 10) + "%" + ChatColor.GRAY + " needed xp than normal!\n\n" + "&eClick to purchase!");
        }else{
            req_level  = ChatColor.AQUA + "+" + ((int) PrestigeXpAmount(ClassInstances.prestigeData.getPrestige(String.valueOf(player.getUniqueId()))) * 10) + "%" + ChatColor.GRAY + " needed xp than normal!\n\n" + ChatColor.GRAY + "Required Level: " + PrestigeBracketColors.getBracketColor(player) + "[" + ChatColor.AQUA + ChatColor.BOLD + "120" + PrestigeBracketColors.getBracketColor(player) + "]" + "\n\n" + ChatColor.GRAY + "Level up to prestige!";
        }

        Inventory gui = inv(player, 27, ChatColor.GRAY + "Prestige & Renown");

        ItemStack base_glass = cGlass();

        ItemStack Prestige_Block = ItemMaker(Material.DIAMOND, ChatColor.AQUA + "Prestige", ChatColor.GRAY + "Current: " + ChatColor.YELLOW + integerToRoman(ClassInstances.prestigeData.getPrestige(String.valueOf(player.getUniqueId()))) + "\n" + req_level, 1, true);

        ItemStack Prestige_Shop = ItemMaker(Material.BEACON, ChatColor.YELLOW + "Renown shop",
                ChatColor.GRAY + "Use " + ChatColor.YELLOW + "Renown " + ChatColor.GRAY + "earned from\n" +
                        ChatColor.AQUA + "Prestige " + ChatColor.GRAY + "to unlock unique\n" + ChatColor.GRAY + "upgrades!\n\n" +
                        ChatColor.GRAY + ChatColor.ITALIC + "These upgrades are safe\n" + ChatColor.GRAY + ChatColor.ITALIC + "from prestige reset.\n\n" +
                        ChatColor.GRAY + "Renown " + ChatColor.YELLOW + ClassInstances.renownData.getRenown(String.valueOf(player.getUniqueId())) + " Renown\n\n" +
                        ChatColor.YELLOW + "Click to browse!"
                , 1, true);

        ItemStack pitSupport = ItemMaker(Material.WATCH, ChatColor.GOLD + "Pit Supporter",
                ColorUtil.colorCode("&7Prestige: &eI\n\n" +
                        "&7Show your support for The Pit and\n" +
                        "&7unlock exclusive features!\n\n" +
                        "&7Features:\n" +
                        "&e- &7Toggleable suffix!\n" +
                        "&e- &7/events to view upcoming &devents&7!\n" +
                        "&e- &7/show to link a held item in chat!\n" +
                        "&e- &7/dye to color your pants!\n" +
                        "&e- &7And many more!\n\n" +
                        "&7Buy on the store!\n\n" +
                        "&8Pit Supporter is a way you can help support\n" +
                        "&8the server and keep it funded!\n\n" +
                        "&eVisit store.pitredux.net!")
                , 1, true);

        for (int i = 0; i < 10; i++) {
            addInv(gui, base_glass, i, 1, false);
            addInv(gui, base_glass, i, 2, false);
            addInv(gui, base_glass, i, 3, false);
        }
        addInv(gui, Prestige_Block, 3, 2, false);

        addInv(gui, Prestige_Shop, 7, 2, false);

        addInv(gui, pitSupport, 9, 3, false);

        player.openInventory(gui);
    }

}
