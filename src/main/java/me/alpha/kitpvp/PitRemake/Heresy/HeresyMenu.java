package me.alpha.kitpvp.PitRemake.Heresy;

import me.alpha.kitpvp.Data.ClassInstances;
import me.alpha.kitpvp.PitRemake.ItemStacks.enchants;
import me.alpha.kitpvp.PitRemake.PitCommands.Stash.StashCore;
import me.alpha.kitpvp.PitRemake.RenownShop.gui.RenownShopUpgradesGUI;
import me.alpha.kitpvp.utils.ColorUtil;
import me.alpha.kitpvp.utils.IntegerHelper;
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

import static me.alpha.kitpvp.utils.advancedInventory.ItemMaker;

public class HeresyMenu implements Listener {
    public static ItemStack getGoBackItem(String uuid){
        return ItemMaker(Material.ARROW, ChatColor.GREEN + "Go Back",
                ChatColor.GRAY+"To Renown Shop - Upgrades",1, true);
    }

    public static ItemStack getHeresy(String uuid){
        ClassInstances.heresy.hasValue(uuid);
        int tier = ((Integer)ClassInstances.heresy.getValue(uuid))+1;
        int renown = ClassInstances.renownData.getRenown(uuid);

        int newAmount = 64;

        int upgradeCost = ((tier*2)*100);

        switch (tier){
            case 1:
                newAmount=48;
                break;
            case 2:
                newAmount=32;
                break;
            case 3:
                newAmount=16;
                break;
            case 4:
                newAmount=8;
                break;
            case 5:
                newAmount=4;
                break;
        }

        if(tier>=5){
            return ItemMaker(Material.COAL, ChatColor.RED + "Heresy",
                    ColorUtil.colorCode("&7Tier: &a" + IntegerHelper.integerToRoman(tier-1) + "\n\n" +
                            "&7Current tier:\n" +
                            "&7Deal &c+5% damage &7against\n" +
                            "&7players wearing leather armor.\n\n" +
                            "&d&7Dark Pants Cost:\n" +
                            "&54 Chunks of Vile!\n" +
                            "&e4 Renown\n\n" +
                            "&aMaxed out!!"),1, true);
        }

        if(renown>=((tier*2)*100)){
            return ItemMaker(Material.COAL, ChatColor.RED + "Heresy",
                    ColorUtil.colorCode("&7Tier: &a" + IntegerHelper.integerToRoman(tier-1) + "\n\n" +
                            "&7Next tier:\n" +
                            "&7Deal &c+1% damage &7against\n" +
                            "&7players wearing leather armor.\n\n" +
                            "&d+ &7Dark Pants Cost:\n" +
                            "&5"+newAmount+" Chunks of Vile!\n" +
                            "&e"+newAmount+" Renown\n\n" +
                            "&7Upgrade cost: &e"+(((tier-1)*2)*100)+" Renown\n" +
                            "&7You have: &e" + ClassInstances.renownData.getRenown(uuid) + " &eRenown\n\n" +
                            "&eClick to purchase!!"),1, true);
        }else return ItemMaker(Material.COAL, ChatColor.RED + "Heresy",
                ColorUtil.colorCode("&7Tier: &a" + IntegerHelper.integerToRoman(tier-1) + "\n\n" +
                        "&7Next tier:\n" +
                        "&7Deal &c+1% damage &7against\n" +
                        "&7players wearing leather armor.\n\n" +
                        "&d+ &7Dark Pants Cost:\n" +
                        "&5"+newAmount+" Chunks of Vile!\n" +
                        "&e"+newAmount+" Renown\n\n" +
                        "&7Upgrade cost: &e"+(((tier-1)*2)*100)+" Renown\n" +
                        "&7You have: &e" + ClassInstances.renownData.getRenown(uuid) + " &eRenown\n\n" +
                        "&cNot enough renown!"),1, true);
    }

    public static ItemStack getAnvil(Player player, String uuid){

        Inventory inventory = player.getInventory();

        int tier = ((Integer)ClassInstances.heresy.getValue(uuid));
        int renown = ClassInstances.renownData.getRenown(uuid);

        int newAmount = 64;

        int upgradeCost = ((tier*2)*100);

        switch (tier){
            case 1:
                newAmount=48;
                break;
            case 2:
                newAmount=32;
                break;
            case 3:
                newAmount=16;
                break;
            case 4:
                newAmount=8;
                break;
            case 5:
                newAmount=4;
                break;
        }

        boolean hasVile = inventory.containsAtLeast(enchants.vile, newAmount);
        boolean hasRenown = ClassInstances.renownData.getRenown(uuid)>=newAmount;

        if(!hasVile)return ItemMaker(Material.ANVIL, ChatColor.DARK_PURPLE + "Dark Pants",
                ColorUtil.colorCode("&7Assemble &5Chunks of Vile &7and\n" +
                        "&eRenown &7to create pants who\n" +
                        "&7are really strong against\n" +
                        "&dMysticism&7.\n\n" +
                        "&7Costs: &5"+newAmount+" Chunks of Vile\n" +
                        "&7Also costs: &e"+newAmount+" Renown\n\n" +
                        "&7Creating: &5Fresh Dark Pants\n\n" +
                        "&7You have: &e" + ClassInstances.renownData.getRenown(uuid) + " &eRenown\n" +
                        "&cWarning! This COSTS renown!\n\n" +
                        "&5Missing Chunks of Vile!"),1, true);
        else if(!hasRenown)return ItemMaker(Material.ANVIL, ChatColor.DARK_PURPLE + "Dark Pants",
                ColorUtil.colorCode("&7Assemble &5Chunks of Vile &7and\n" +
                        "&eRenown &7to create pants who\n" +
                        "&7are really strong against\n" +
                        "&dMysticism&7.\n\n" +
                        "&7Costs: &5"+newAmount+" Chunks of Vile\n" +
                        "&7Also costs: &e"+newAmount+" Renown\n\n" +
                        "&7Creating: &5Fresh Dark Pants\n\n" +
                        "&7You have: &e" + ClassInstances.renownData.getRenown(uuid) + " &eRenown\n" +
                        "&cWarning! This COSTS renown!\n\n" +
                        "&cMissing renown!"),1, true);
        else return ItemMaker(Material.ANVIL, ChatColor.DARK_PURPLE + "Dark Pants",
                ColorUtil.colorCode("&7Assemble &5Chunks of Vile &7and\n" +
                        "&eRenown &7to create pants who\n" +
                        "&7are really strong against\n" +
                        "&dMysticism&7.\n\n" +
                        "&7Costs: &5"+newAmount+" Chunks of Vile\n" +
                        "&7Also costs: &e"+newAmount+" Renown\n\n" +
                        "&7Creating: &5Fresh Dark Pants\n\n" +
                        "&7You have: &e" + ClassInstances.renownData.getRenown(uuid) + " &eRenown\n" +
                        "&eClick to create!!"),1, true);
    }

    public static ItemStack getNightQuests(String uuid){
        String enabled = ChatColor.GREEN + "ON";

        if(ClassInstances.NightQuestsToggle.containsKey(uuid) &&
        !ClassInstances.NightQuestsToggle.get(uuid)){
            enabled = ChatColor.RED + "OFF";
        }

        return ItemMaker(Material.LAPIS_BLOCK,
                ChatColor.BLUE + "Night Quests",
                ColorUtil.colorCode("&7Find &5Chunks of Vile &7by\n" +
                        "&7doing &9Night Quests&7.\n\n" +
                        "&7You may disabled the night\n" +
                        "&7quests if you desire.\n\n" +
                        "&7Enabled: " + enabled + "\n\n" +
                        "&7Note: You can also earn\n" +
                        "&5chunks &7from contracts.\n\n" +
                        "&eClick to toggle!"),1, true);
    }

    public static Inventory getHeresyMenu(Player player){
        String uuid = String.valueOf(player.getUniqueId());
        Inventory gui = advancedInventory.inv(player, 36, ChatColor.GRAY + "Heresy");
        ItemStack base_glass = advancedInventory.cGlass();

        for (int i = 0; i < 10; i++) {
            advancedInventory.addInv(gui, base_glass, i, 1, false);
            advancedInventory.addInv(gui, base_glass, i, 2, false);
            advancedInventory.addInv(gui, base_glass, i, 3, false);
            advancedInventory.addInv(gui, base_glass, i, 4, false);
        }

        advancedInventory.addInv(gui, getGoBackItem(uuid), 5, 4, false);
        advancedInventory.addInv(gui, getNightQuests(uuid), 6, 4, false);

        advancedInventory.addInv(gui, getAnvil(player,uuid), 3, 2, false);
        advancedInventory.addInv(gui, getHeresy(uuid), 7, 2, false);

        return gui;
    }

    @EventHandler
    public void HandleClickEvent(InventoryClickEvent event){
        if(event==null||event.getClickedInventory()==null||
        event.getClickedInventory().getTitle()==null||
                !event.getClickedInventory().getTitle().equalsIgnoreCase(ChatColor.GRAY + "Heresy")) return;

        event.setCancelled(true);

        Player player = (Player) event.getWhoClicked();
        String uuid = player.getUniqueId().toString();

        String notEnoughVileMessage = ColorUtil.colorCode("&cYou are missing Chunks of Vile to create dark pants!");
        String notEnoughRenownMessage = ColorUtil.colorCode("&cYou are missing renown to create dark pants");
        String cantAffordMessagxe = ColorUtil.colorCode("&cYou don't have enough renown to afford this!");

        ItemStack item = event.getCurrentItem();
        Inventory inventory = player.getInventory();

        if(item==null) return;

        ClassInstances.heresy.hasValue(uuid);
        int tier = ((Integer)ClassInstances.heresy.getValue(uuid));
        int renown = ClassInstances.renownData.getRenown(uuid);

        int newAmount = 64;

        int upgradeCost = ((tier*2)*100);

        switch (tier){
            case 1:
                newAmount=48;
                break;
            case 2:
                newAmount=32;
                break;
            case 3:
                newAmount=16;
                break;
            case 4:
                newAmount=8;
                break;
            case 5:
                newAmount=4;
                break;
        }

        boolean hasVile = inventory.containsAtLeast(enchants.vile, newAmount);
        boolean hasRenown = ClassInstances.renownData.getRenown(uuid)>=newAmount;

        if(item.getType().equals(Material.ANVIL)){
            if(hasVile&&hasRenown){
                for(int i = 0; i < newAmount; i++) player.getInventory().removeItem(enchants.vile);
                Sounds.SUCCESS.play(player);
                Sounds.HERESY.play(player);
                ClassInstances.renownData.setRenown(uuid, Math.max(0, ClassInstances.renownData.getRenown(uuid)-newAmount));
                StashCore.safeGive(player, enchants.fresh_dark);

                event.getClickedInventory().setItem(15, getHeresy(uuid));
                event.getClickedInventory().setItem(event.getSlot(), getAnvil(player, uuid));
            }else if(!hasRenown){
                player.sendMessage(notEnoughRenownMessage);
                Sounds.NO_MONEY.play(player);
            }else {
                player.sendMessage(notEnoughVileMessage);
                Sounds.NO_MONEY.play(player);
            }
        }else if(item.getType().equals(Material.LAPIS_BLOCK)){
            if(ClassInstances.NightQuestsToggle.containsKey(uuid)){
                ClassInstances.NightQuestsToggle.put(uuid, !ClassInstances.NightQuestsToggle.get(uuid));
                player.openInventory(RenownShopUpgradesGUI.getRenownShopUpgradesGUI(player));
                Sounds.BUTTON.play(player);
            }else{
                ClassInstances.NightQuestsToggle.put(uuid, false);
                player.openInventory(RenownShopUpgradesGUI.getRenownShopUpgradesGUI(player));
                Sounds.BUTTON.play(player);
            }
        }else if(item.getType().equals(Material.COAL)){
            if(((Integer)ClassInstances.heresy.getValue(uuid))>=5){
                Sounds.NO.play(player);
                player.sendMessage(ColorUtil.colorCode("&aYou have already maxed this upgrade!"));
            }else if(renown>=upgradeCost){
                ClassInstances.renownData.setRenown(uuid, Math.max(0, ClassInstances.renownData.getRenown(uuid)-upgradeCost));

                ClassInstances.heresy.addValue(uuid,1);

                event.getClickedInventory().setItem(event.getSlot(), getHeresy(uuid));
                event.getClickedInventory().setItem(11, getAnvil(player, uuid));
            }
        }else if(item.getType().equals(Material.ARROW)){
            Sounds.BUTTON.play(player);
            player.openInventory(RenownShopUpgradesGUI.getRenownShopUpgradesGUI(player));
        }

    }
}
