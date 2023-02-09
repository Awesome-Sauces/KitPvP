package me.alpha.kitpvp.PitRemake.RenownShop.gui;

import me.alpha.kitpvp.Data.ClassInstances;
import me.alpha.kitpvp.PitRemake.Heresy.HeresyMenu;
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
import static me.alpha.kitpvp.utils.IntegerHelper.integerToRoman;
import static me.alpha.kitpvp.utils.advancedInventory.FishMaker;
import static me.alpha.kitpvp.utils.advancedInventory.ItemMaker;


public class RenownShopUpgradesGUI implements Listener {

    public static ItemStack getTheWayItem(String uuid){
        if(ClassInstances.prestigeData.getPrestige(uuid)<7){
            return ItemMaker(Material.BEDROCK, ChatColor.RED + "Unknown upgrade",
                    colorCode("&7Prestige: &e"+integerToRoman(7)),1, true);
        }

        if(ClassInstances.theWay.hasValue(uuid) &&
                ((Integer)ClassInstances.theWay.getValue(uuid))>=1){
            return ItemMaker(Material.ACACIA_DOOR_ITEM, ChatColor.GREEN + "The Way",
                    colorCode(ClassInstances.theWay.getLore() + "\n\n" +
                            "&aMaxed out!"),1, true);
        }else{
            return ItemMaker(Material.ACACIA_DOOR_ITEM, ChatColor.YELLOW + "The Way",
                    colorCode(ClassInstances.theWay.getLore() + "\n\n" +
                            "&7Cost: &e50 Renown\n" +
                            "&7You have: &e"+ClassInstances.renownData.getRenown(uuid)+" Renown\n\n" +
                            "&eClick to purchase!"),1, true);
        }
    }

    public static ItemStack getPromotionItem(String uuid){
        if(ClassInstances.prestigeData.getPrestige(uuid)<12){
            return ItemMaker(Material.BEDROCK, ChatColor.RED + "Unknown upgrade",
                    colorCode("&7Prestige: &e"+integerToRoman(12)),1, true);
        }

        if(ClassInstances.promotion.hasValue(uuid) &&
                ((Integer)ClassInstances.promotion.getValue(uuid))>=1){
            return ItemMaker(Material.FENCE, ChatColor.GREEN + "Promotion!!",
                    colorCode(ClassInstances.promotion.getLore() + "\n\n" +
                            "&aMaxed out!"),1, true);
        }else{
            return ItemMaker(Material.FENCE, ChatColor.YELLOW + "Promotion!!",
                    colorCode(ClassInstances.promotion.getLore() + "\n\n" +
                            "&7Cost: &e50 Renown\n" +
                            "&7You have: &e"+ClassInstances.renownData.getRenown(uuid)+" Renown\n\n" +
                            "&eClick to purchase!"),1, true);
        }
    }

    public static ItemStack getCelebrityItem(String uuid){
        if(ClassInstances.prestigeData.getPrestige(uuid)<20){
            return ItemMaker(Material.BEDROCK, ChatColor.RED + "Unknown upgrade",
                    colorCode("&7Prestige: &e"+integerToRoman(20)),1, true);
        }

        if(ClassInstances.celebrity.hasValue(uuid) &&
                ((Integer)ClassInstances.celebrity.getValue(uuid))>=1){
            return FishMaker((short) 3, ChatColor.GREEN + "Celebrity",
                    colorCode(ClassInstances.celebrity.getLore() + "\n\n" +
                            "&aMaxed out!"));
        }else{
            return FishMaker((short) 3, ChatColor.YELLOW + "Celebrity",
                    colorCode(ClassInstances.celebrity.getLore() + "\n\n" +
                            "&7Cost: &e300 Renown\n" +
                            "&7You have: &e"+ClassInstances.renownData.getRenown(uuid)+" Renown\n\n" +
                            "&eClick to purchase!"));
        }
    }

    public static ItemStack getFastPassItem(String uuid){
        if(ClassInstances.prestigeData.getPrestige(uuid)<15){
            return ItemMaker(Material.BEDROCK, ChatColor.RED + "Unknown upgrade",
                    colorCode("&7Prestige: &e"+integerToRoman(15)),1, true);
        }

        if(ClassInstances.fastPass.hasValue(uuid) &&
                ((Integer)ClassInstances.fastPass.getValue(uuid))>=1){
            return ItemMaker(Material.ACTIVATOR_RAIL, ChatColor.GREEN + "Fast Pass",
                    colorCode(ClassInstances.fastPass.getLore() + "\n\n" +
                            "&aMaxed out!"),1, true);
        }else{
            return ItemMaker(Material.ACTIVATOR_RAIL, ChatColor.YELLOW + "Fast Pass",
                    colorCode(ClassInstances.fastPass.getLore() + "\n\n" +
                            "&7Cost: &e100 Renown\n" +
                            "&7You have: &e"+ClassInstances.renownData.getRenown(uuid)+" Renown\n\n" +
                            "&eClick to purchase!"),1, true);
        }
    }

    public static ItemStack getExperienceItem(String uuid){
        if(ClassInstances.prestigeData.getPrestige(uuid)<14){
            return ItemMaker(Material.BEDROCK, ChatColor.RED + "Unknown upgrade",
                    colorCode("&7Prestige: &e"+integerToRoman(14)),1, true);
        }

        if(ClassInstances.experienceIndustrialComplex.hasValue(uuid) &&
                ((Integer)ClassInstances.experienceIndustrialComplex.getValue(uuid))>=1){
            return ItemMaker(Material.DIAMOND_BARDING, ChatColor.GREEN + "Experience-Industrial Complex",
                    colorCode(ClassInstances.experienceIndustrialComplex.getLore() + "\n\n" +
                            "&aMaxed out!"),1, true);
        }else{
            return ItemMaker(Material.DIAMOND_BARDING, ChatColor.YELLOW + "Experience-Industrial Complex",
                    colorCode(ClassInstances.experienceIndustrialComplex.getLore() + "\n\n" +
                            "&7Cost: &e80 Renown\n" +
                            "&7You have: &e"+ClassInstances.renownData.getRenown(uuid)+" Renown\n\n" +
                            "&eClick to purchase!"),1, true);
        }
    }

    public static ItemStack getHeresyItem(String uuid){

        if(ClassInstances.prestigeData.getPrestige(uuid)<6){
            return ItemMaker(Material.BEDROCK, ChatColor.RED + "Unknown upgrade",
                    colorCode("&7Prestige: &e"+integerToRoman(6)),1, true);
        }

        if(ClassInstances.heresy.hasValue(uuid) &&
                ((Integer)ClassInstances.heresy.getValue(uuid))>=1){
            return ItemMaker(Material.COAL, ChatColor.GREEN + "Heresy",
                    colorCode(ClassInstances.heresy.getLore() + "\n\n" +
                            "&eClick to open menu!"),1, true);
        }else{
            return ItemMaker(Material.COAL, ChatColor.YELLOW + "Heresy",
                    colorCode(ClassInstances.heresy.getLore() + "\n\n" +
                            "&7Cost: &e50 Renown\n" +
                            "&7You have: &e"+ClassInstances.renownData.getRenown(uuid)+" Renown\n\n" +
                            "&eClick to purchase!"),1, true);
        }
    }

    public static ItemStack getMysticismItem(String uuid){
        if(ClassInstances.prestigeData.getPrestige(uuid)<1){
            return ItemMaker(Material.BEDROCK, ChatColor.RED + "Unknown upgrade",
                    colorCode("&7Prestige: &e"+integerToRoman(1)),1, true);
        }

        if(ClassInstances.mysticism.hasValue(uuid) &&
                ((Integer)ClassInstances.mysticism.getValue(uuid))>=20){
            return ItemMaker(Material.ENCHANTMENT_TABLE, ChatColor.GREEN + "Mysticism",
                    colorCode("&7Current: &d+" + (((Integer)ClassInstances.mysticism.getValue(uuid))*5) +
                            "&d% chance\n" +
                            "&7Tier: &a" + integerToRoman((Integer) ClassInstances.mysticism.getValue(uuid)) + "\n\n" +
                            ClassInstances.mysticism.getLore((Integer) ClassInstances.mysticism.getValue(uuid)+1) + "\n\n" +
                            "&aMaxed out!"),1, true);
        }else if(((int)ClassInstances.mysticism.getValue(uuid, 0))>=1){
            return ItemMaker(Material.ENCHANTMENT_TABLE, ChatColor.GREEN + "Mysticism",
                    colorCode("&7Current: &d+" + (((Integer)ClassInstances.mysticism.getValue(uuid))*5) +
                            "&d% chance\n" +
                            "&7Tier: &a" + integerToRoman((Integer) ClassInstances.mysticism.getValue(uuid)) + "\n\n" +
                            ClassInstances.mysticism.getLore((Integer) ClassInstances.mysticism.getValue(uuid)+1) + "\n\n" +
                            "&7Upgrade Cost: &e"+(((Integer)ClassInstances.mysticism.getValue(uuid))*5)+" &eRenown\n" +
                            "&7You have: &e"+ClassInstances.renownData.getRenown(uuid)+" Renown\n\n" +
                            "&eClick to upgrade!"),1, true);
        }else{
            return ItemMaker(Material.ENCHANTMENT_TABLE, ChatColor.YELLOW + "Mysticism",
                    colorCode(ClassInstances.mysticism.getLore(1) + "\n\n" +
                            "&7Cost: &e25 Renown\n" +
                            "&7You have: &e"+ClassInstances.renownData.getRenown(uuid)+" Renown\n\n" +
                            "&eClick to purchase!"),1, true);
        }
    }

    public static ItemStack getExtraHearts(String uuid){
        if(ClassInstances.prestigeData.getPrestige(uuid)<5){
            return ItemMaker(Material.BEDROCK, ChatColor.RED + "Unknown upgrade",
                    colorCode("&7Prestige: &e"+integerToRoman(5)),1, true);
        }

        if(ClassInstances.extraHearts.hasValue(uuid) &&
                ((Integer)ClassInstances.extraHearts.getValue(uuid))>=2){
            return ItemMaker(Material.APPLE, ChatColor.GREEN + "Extra Hearts",
                    colorCode("&7Current: &c+" + ClassInstances.extraHearts.getValue(uuid) +
                            "&c\u2764\n" +
                            "&7Tier: &a" + integerToRoman((Integer) ClassInstances.extraHearts.getValue(uuid)) + "\n\n" +
                            ClassInstances.extraHearts.getLore() + "\n\n" +
                            "&aMaxed out!"),1, true);
        }else if(((int)ClassInstances.extraHearts.getValue(uuid, 0))>=1){
            return ItemMaker(Material.APPLE, ChatColor.GREEN + "Extra Hearts",
                    colorCode("&7Current: &c+" + ClassInstances.extraHearts.getValue(uuid) +
                            "&c\u2764\n" +
                            "&7Tier: &a" + integerToRoman((Integer) ClassInstances.extraHearts.getValue(uuid)) + "\n\n" +
                            ClassInstances.extraHearts.getLore() + "\n\n" +
                            "&7Upgrade Cost: &e"+(((Integer)ClassInstances.extraHearts.getValue(uuid))*20)+" &eRenown\n" +
                            "&7You have: &e"+ClassInstances.renownData.getRenown(uuid)+" Renown\n\n" +
                            "&eClick to upgrade!"),1, true);
        }else{
            return ItemMaker(Material.APPLE, ChatColor.YELLOW + "Extra Hearts",
                    colorCode(ClassInstances.extraHearts.getLore() + "\n\n" +
                            "&7Cost: &e20 Renown\n" +
                            "&7You have: &e"+ClassInstances.renownData.getRenown(uuid)+" Renown\n\n" +
                            "&eClick to purchase!"),1, true);
        }
    }

    public static ItemStack getTenacityItem(String uuid){
        if(ClassInstances.prestigeData.getPrestige(uuid)<1){
            return ItemMaker(Material.BEDROCK, ChatColor.RED + "Unknown upgrade",
                    colorCode("&7Prestige: &e"+integerToRoman(1)),1, true);
        }

        if(ClassInstances.tenacity.hasValue(uuid) &&
                ((Integer)ClassInstances.tenacity.getValue(uuid))>=5){
            return ItemMaker(Material.MAGMA_CREAM, ChatColor.GREEN + "Tenacity",
                    colorCode("&7Current: Heal &c+0." + ClassInstances.tenacity.getValue(uuid) +
                            "&c\u2764 &7on kill.\n" +
                            "&7Tier: &a" + integerToRoman((Integer) ClassInstances.tenacity.getValue(uuid)) + "\n\n" +
                            ClassInstances.tenacity.getLore() + "\n\n" +
                            "&aMaxed out!"),1, true);
        }else if(((int)ClassInstances.tenacity.getValue(uuid, 0))>=1){
            return ItemMaker(Material.MAGMA_CREAM, ChatColor.GREEN + "Tenacity",
                    colorCode("&7Current: Heal &c+0." + ClassInstances.tenacity.getValue(uuid) +
                            "&c\u2764 &7on kill.\n" +
                            "&7Tier: &a" + integerToRoman((Integer) ClassInstances.tenacity.getValue(uuid)) + "\n\n" +
                            ClassInstances.tenacity.getLore() + "\n\n" +
                            "&7Upgrade Cost: &e"+(((Integer)ClassInstances.tenacity.getValue(uuid))*25)+" &eRenown\n" +
                            "&7You have: &e"+ClassInstances.renownData.getRenown(uuid)+" Renown\n\n" +
                            "&eClick to upgrade!"),1, true);
        }else{
            return ItemMaker(Material.MAGMA_CREAM, ChatColor.YELLOW + "Tenacity",
                    colorCode(ClassInstances.tenacity.getLore() + "\n\n" +
                            "&7Cost: &e25 Renown\n" +
                            "&7You have: &e"+ClassInstances.renownData.getRenown(uuid)+" Renown\n\n" +
                            "&eClick to purchase!"),1, true);
        }
    }

    public static ItemStack getXpBumpItem(String uuid){
        if(ClassInstances.renownXpBump.hasValue(uuid) &&
                ((Integer)ClassInstances.renownXpBump.getValue(uuid))>=10){
            return ItemMaker(Material.EXP_BOTTLE, ChatColor.GREEN + "Renown XP Bump",
                    colorCode("&7Current: &b+" + ClassInstances.renownXpBump.getValue(uuid) +
                            " &bkill XP\n" +
                            "&7Tier: &a" + integerToRoman((Integer) ClassInstances.renownXpBump.getValue(uuid)) + "\n\n" +
                            ClassInstances.renownXpBump.getLore() + "\n\n" +
                            "&aMaxed out!"),1, true);
        }else if(((int)ClassInstances.renownXpBump.getValue(uuid, 0))>=1){
            return ItemMaker(Material.EXP_BOTTLE, ChatColor.GREEN + "Renown XP Bump",
                    colorCode("&7Current: &b+" + ClassInstances.renownXpBump.getValue(uuid) +
                            " &bkill XP\n" +
                            "&7Tier: &a" + integerToRoman((Integer) ClassInstances.renownXpBump.getValue(uuid)) + "\n\n" +
                            ClassInstances.renownXpBump.getLore() + "\n\n" +
                            "&7Upgrade Cost: &e"+(((Integer)ClassInstances.renownXpBump.getValue(uuid))*5)+" &eRenown\n" +
                            "&7You have: &e"+ClassInstances.renownData.getRenown(uuid)+" Renown\n\n" +
                            "&eClick to upgrade!"),1, true);
        }else{
            return ItemMaker(Material.EXP_BOTTLE, ChatColor.YELLOW + "Renown XP Bump",
                    colorCode(ClassInstances.renownXpBump.getLore() + "\n\n" +
                            "&7Cost: &e5 Renown\n" +
                            "&7You have: &e"+ClassInstances.renownData.getRenown(uuid)+" Renown\n\n" +
                            "&eClick to purchase!"),1, true);
        }
    }

    public static ItemStack getGoldBumpItem(String uuid){
        if(ClassInstances.renownGoldBoost.hasValue(uuid) &&
                ((Integer)ClassInstances.renownGoldBoost.getValue(uuid))>=10){
            return ItemMaker(Material.GOLD_NUGGET, ChatColor.GREEN + "Renown Gold Boost",
                    colorCode("&7Current: &6+" + ClassInstances.renownGoldBoost.getValue(uuid) +
                            "&6.0% gold (g)\n" +
                            "&7Tier: &a" + integerToRoman((Integer) ClassInstances.renownGoldBoost.getValue(uuid)) + "\n\n" +
                            ClassInstances.renownGoldBoost.getLore() + "\n\n" +
                            "&aMaxed out!"),1, true);
        }else if(((int)ClassInstances.renownGoldBoost.getValue(uuid, 0))>=1){
            return ItemMaker(Material.GOLD_NUGGET, ChatColor.GREEN + "Renown Gold Boost",
                    colorCode("&7Current: &6+" + ClassInstances.renownGoldBoost.getValue(uuid) +
                            "&6.0% gold (g)\n" +
                            "&7Tier: &a" + integerToRoman((Integer) ClassInstances.renownGoldBoost.getValue(uuid)) + "\n\n" +
                            ClassInstances.renownGoldBoost.getLore() + "\n\n" +
                            "&7Upgrade Cost: &e"+(((Integer)ClassInstances.renownGoldBoost.getValue(uuid))*5)+" &eRenown\n" +
                            "&7You have: &e"+ClassInstances.renownData.getRenown(uuid)+" Renown\n\n" +
                            "&eClick to upgrade!"),1, true);
        }else{
            return ItemMaker(Material.GOLD_NUGGET, ChatColor.YELLOW + "Renown Gold Boost",
                    colorCode(ClassInstances.renownGoldBoost.getLore() + "\n\n" +
                            "&7Cost: &e5 Renown\n" +
                            "&7You have: &e"+ClassInstances.renownData.getRenown(uuid)+" Renown\n\n" +
                            "&eClick to purchase!"),1, true);
        }
    }

    public static ItemStack getGoBackItem(String uuid){
        return ItemMaker(Material.ARROW, ChatColor.GREEN + "Go Back",
                ChatColor.GRAY+"To Prestige & Renown",1, true);
    }

    public static Inventory getRenownShopUpgradesGUI(Player player){
        String uuid = String.valueOf(player.getUniqueId());
        Inventory gui = advancedInventory.inv(player, 45, ChatColor.GRAY + "Renown Shop - Upgrades");
        ItemStack base_glass = advancedInventory.cGlass();

        for (int i = 0; i < 10; i++) {
            advancedInventory.addInv(gui, base_glass, i, 1, false);
            advancedInventory.addInv(gui, base_glass, i, 2, false);
            advancedInventory.addInv(gui, base_glass, i, 3, false);
            advancedInventory.addInv(gui, base_glass, i, 4, false);
            advancedInventory.addInv(gui, base_glass, i, 5, false);
        }

        advancedInventory.addInv(gui, getTenacityItem(uuid), 2, 2, false);
        advancedInventory.addInv(gui, getXpBumpItem(uuid), 3, 2, false);
        advancedInventory.addInv(gui, getGoldBumpItem(uuid), 4, 2, false);
        advancedInventory.addInv(gui, getTheWayItem(uuid), 5, 2, false);
        advancedInventory.addInv(gui, getExperienceItem(uuid), 6, 2, false);
        advancedInventory.addInv(gui, getHeresyItem(uuid), 7, 2, false);
        advancedInventory.addInv(gui, getFastPassItem(uuid), 8, 2, false);

        advancedInventory.addInv(gui, getMysticismItem(uuid), 2, 3, false);
        advancedInventory.addInv(gui, getCelebrityItem(uuid), 3, 3, false);
        advancedInventory.addInv(gui, getExtraHearts(uuid), 4, 3, false);
        advancedInventory.addInv(gui, getPromotionItem(uuid), 5, 3, false);

        advancedInventory.addInv(gui, getGoBackItem(uuid), 5, 5, false);

        //advancedInventory.addInv(gui, getOverDriveItem(uuid), 2, 2, false);

        return gui;
    }

    @EventHandler
    public void HandleRenownShopUpgradesClick(InventoryClickEvent event){
        if(event==null||
        event.getClickedInventory()==null) return;

        if(event.getClickedInventory() != null &&
                event.getClickedInventory().getTitle() != null &&
                !event.getClickedInventory().getTitle().equals(ChatColor.GRAY + "Renown Shop - Upgrades")) return;

        Player player = (Player) event.getWhoClicked();
        String uuid = player.getUniqueId().toString();

        event.setCancelled(true);

        if(event.getCurrentItem().getType().equals(Material.BEDROCK)){
            Sounds.ERROR.play(player);
            player.sendMessage(ChatColor.RED + "You are too low prestige to acquire this!");
        }

        if(event.getCurrentItem().getType().equals(Material.GOLD_NUGGET)){
            if(ClassInstances.renownGoldBoost.hasValue(uuid) &&
                    ClassInstances.renownGoldBoost.getInt(uuid, 0)>=10){
                Sounds.NO.play(player);
            }else if(ClassInstances.renownGoldBoost.getInt(uuid, 0)>=1 && ClassInstances.renownData.getRenown(uuid)>=(((Integer)ClassInstances.renownGoldBoost.getValue(uuid))*5)){
                Sounds.RENOWN_SHOP_PURCHASE.play(player);
                ClassInstances.renownData.setRenown(uuid, ClassInstances.renownData.getRenown(uuid)-(((Integer)ClassInstances.renownGoldBoost.getValue(uuid))*5));
                ClassInstances.renownGoldBoost.addValue(uuid, (Integer) 1);
            }else if(ClassInstances.renownGoldBoost.getInt(uuid, 0) <= 0 &&
                    ClassInstances.renownData.getRenown(uuid)>=5){
                Sounds.RENOWN_SHOP_PURCHASE.play(player);
                ClassInstances.renownData.setRenown(uuid, ClassInstances.renownData.getRenown(uuid)-5);
                ClassInstances.renownGoldBoost.setValue(uuid, (Integer) 1);
            }else{
                Sounds.NO.play(player);
            }

            player.openInventory(getRenownShopUpgradesGUI(player));
        }else if(event.getCurrentItem().getType().equals(Material.EXP_BOTTLE)){
            if(ClassInstances.renownXpBump.hasValue(uuid) &&
                    ((Integer)ClassInstances.renownXpBump.getValue(uuid))>=10){
                Sounds.NO.play(player);
            }else if(((int)ClassInstances.renownXpBump.getValue(uuid, 0))>=1 &&
                    ClassInstances.renownData.getRenown(uuid)>=(((Integer)ClassInstances.renownXpBump.getValue(uuid))*5)){
                Sounds.RENOWN_SHOP_PURCHASE.play(player);
                ClassInstances.renownData.setRenown(uuid, ClassInstances.renownData.getRenown(uuid)-(((Integer)ClassInstances.renownXpBump.getValue(uuid))*5));
                ClassInstances.renownXpBump.addValue(uuid, (Integer) 1);
            }else if(ClassInstances.renownXpBump.getInt(uuid, 0) <= 0 &&
                    ClassInstances.renownData.getRenown(uuid)>=5){
                Sounds.RENOWN_SHOP_PURCHASE.play(player);
                ClassInstances.renownData.setRenown(uuid, ClassInstances.renownData.getRenown(uuid)-5);
                ClassInstances.renownXpBump.setValue(uuid, (Integer) 1);
            }else{
                Sounds.NO.play(player);
            }

            player.openInventory(getRenownShopUpgradesGUI(player));
        }else if(event.getCurrentItem().getType().equals(Material.APPLE)){

            if(ClassInstances.extraHearts.hasValue(uuid) &&
                    ((Integer)ClassInstances.extraHearts.getValue(uuid))>=2){
                Sounds.NO.play(player);
            }else if(((int)ClassInstances.extraHearts.getValue(uuid, 0))>=1 &&
                    ClassInstances.renownData.getRenown(uuid)>=(((Integer)ClassInstances.extraHearts.getValue(uuid))*20)){
                Sounds.RENOWN_SHOP_PURCHASE.play(player);
                ClassInstances.renownData.setRenown(uuid, ClassInstances.renownData.getRenown(uuid)-(((Integer)ClassInstances.extraHearts.getValue(uuid))*20));
                ClassInstances.extraHearts.addValue(uuid, (Integer) 1);

                player.setMaxHealth(20+((Integer)ClassInstances.extraHearts.getValue(uuid, 1)*2));
            }else if(ClassInstances.extraHearts.getInt(uuid, 0) <= 0 &&
                    ClassInstances.renownData.getRenown(uuid)>=20){
                Sounds.RENOWN_SHOP_PURCHASE.play(player);
                ClassInstances.renownData.setRenown(uuid, ClassInstances.renownData.getRenown(uuid)-20);
                ClassInstances.extraHearts.setValue(uuid, (Integer) 1);
                player.setMaxHealth(20+((Integer)ClassInstances.extraHearts.getValue(uuid, 1)*2));
            }else{
                Sounds.NO.play(player);
            }

            player.openInventory(getRenownShopUpgradesGUI(player));
        }else if(event.getCurrentItem().getType().equals(Material.ENCHANTMENT_TABLE)){

            if(ClassInstances.mysticism.hasValue(uuid) &&
                    ((Integer)ClassInstances.mysticism.getValue(uuid))>=20){
                Sounds.NO.play(player);
            }else if(((int)ClassInstances.mysticism.getValue(uuid, 0))>=1 &&
                    ClassInstances.renownData.getRenown(uuid)>=(((Integer)ClassInstances.mysticism.getValue(uuid))*5)){
                Sounds.RENOWN_SHOP_PURCHASE.play(player);
                ClassInstances.renownData.setRenown(uuid, ClassInstances.renownData.getRenown(uuid)-(((Integer)ClassInstances.mysticism.getValue(uuid))*5));
                ClassInstances.mysticism.addValue(uuid, (Integer) 1);
            }else if(ClassInstances.mysticism.getInt(uuid, 0) <= 0 &&
                    ClassInstances.renownData.getRenown(uuid)>=5){
                Sounds.RENOWN_SHOP_PURCHASE.play(player);
                ClassInstances.renownData.setRenown(uuid, ClassInstances.renownData.getRenown(uuid)-5);
                ClassInstances.mysticism.setValue(uuid, (Integer) 1);
            }else{
                Sounds.NO.play(player);
            }

            player.openInventory(getRenownShopUpgradesGUI(player));
        }else if(event.getCurrentItem().getType().equals(Material.MAGMA_CREAM)){

            if(ClassInstances.tenacity.hasValue(uuid) &&
                    ((Integer)ClassInstances.tenacity.getValue(uuid))>=5){
                Sounds.NO.play(player);
            }else if(((int)ClassInstances.tenacity.getValue(uuid, 0))>=1 && ClassInstances.renownData.getRenown(uuid)>=(((Integer)ClassInstances.tenacity.getValue(uuid))*25)){
                Sounds.RENOWN_SHOP_PURCHASE.play(player);
                ClassInstances.renownData.setRenown(uuid, ClassInstances.renownData.getRenown(uuid)-(((Integer)ClassInstances.tenacity.getValue(uuid))*25));
                ClassInstances.tenacity.addValue(uuid, (Integer) 1);
            }else if(ClassInstances.tenacity.getInt(uuid, 0) <= 0 &&
                    ClassInstances.renownData.getRenown(uuid)>=25){
                Sounds.RENOWN_SHOP_PURCHASE.play(player);
                ClassInstances.renownData.setRenown(uuid, ClassInstances.renownData.getRenown(uuid)-25);
                ClassInstances.tenacity.setValue(uuid, (Integer) 1);
            }else{
                Sounds.NO.play(player);
            }

            player.openInventory(getRenownShopUpgradesGUI(player));
        }else if(event.getCurrentItem().getType().equals(Material.ACACIA_DOOR_ITEM)){

            if(ClassInstances.theWay.hasValue(uuid) &&
                    ((Integer)ClassInstances.theWay.getValue(uuid))>=1){
                Sounds.NO.play(player);
            }else if(ClassInstances.renownData.getRenown(uuid)>=50){
                Sounds.RENOWN_SHOP_PURCHASE.play(player);
                ClassInstances.renownData.setRenown(uuid, ClassInstances.renownData.getRenown(uuid)-50);
                ClassInstances.theWay.setValue(uuid, (Integer) 1);
            }else{
                Sounds.NO.play(player);
            }

            player.openInventory(getRenownShopUpgradesGUI(player));
        }else if(event.getCurrentItem().getType().equals(Material.ACTIVATOR_RAIL)){

            if(ClassInstances.fastPass.hasValue(uuid) &&
                    ((Integer)ClassInstances.fastPass.getValue(uuid))>=1){
                Sounds.NO.play(player);
            }else if(ClassInstances.renownData.getRenown(uuid)>=100){
                Sounds.RENOWN_SHOP_PURCHASE.play(player);
                ClassInstances.renownData.setRenown(uuid, ClassInstances.renownData.getRenown(uuid)-100);
                ClassInstances.fastPass.setValue(uuid, (Integer) 1);
            }else{
                Sounds.NO.play(player);
            }

            player.openInventory(getRenownShopUpgradesGUI(player));
        }else if(event.getCurrentItem().getType().equals(Material.COAL)){

            if(ClassInstances.heresy.hasValue(uuid) &&
                    ((Integer)ClassInstances.heresy.getValue(uuid))>=1){
                player.openInventory(HeresyMenu.getHeresyMenu(player));
                return;
            }else if(ClassInstances.renownData.getRenown(uuid)>=50){
                Sounds.RENOWN_SHOP_PURCHASE.play(player);
                ClassInstances.renownData.setRenown(uuid, ClassInstances.renownData.getRenown(uuid)-50);
                ClassInstances.heresy.setValue(uuid, (Integer) 1);
            }else{
                Sounds.NO.play(player);
            }

            player.openInventory(getRenownShopUpgradesGUI(player));
        }else if(event.getCurrentItem().getType().equals(Material.DIAMOND_BARDING)){

            if(ClassInstances.experienceIndustrialComplex.hasValue(uuid) &&
                    ((Integer)ClassInstances.experienceIndustrialComplex.getValue(uuid))>=1){
                Sounds.NO.play(player);
            }else if(ClassInstances.renownData.getRenown(uuid)>=80){
                Sounds.RENOWN_SHOP_PURCHASE.play(player);
                ClassInstances.renownData.setRenown(uuid, ClassInstances.renownData.getRenown(uuid)-80);
                ClassInstances.experienceIndustrialComplex.setValue(uuid, (Integer) 1);
            }else{
                Sounds.NO.play(player);
            }

            player.openInventory(getRenownShopUpgradesGUI(player));
        }else if(event.getCurrentItem().getType().equals(Material.FENCE)){

            if(ClassInstances.promotion.hasValue(uuid) &&
                    ((Integer)ClassInstances.promotion.getValue(uuid))>=1){
                Sounds.NO.play(player);
            }else if(ClassInstances.renownData.getRenown(uuid)>=50){
                Sounds.RENOWN_SHOP_PURCHASE.play(player);
                ClassInstances.renownData.setRenown(uuid, ClassInstances.renownData.getRenown(uuid)-50);
                ClassInstances.promotion.setValue(uuid, (Integer) 1);
            }else{
                Sounds.NO.play(player);
            }

            player.openInventory(getRenownShopUpgradesGUI(player));
        }else if(event.getCurrentItem().getItemMeta()!=null&&
                event.getCurrentItem().getItemMeta().getDisplayName().contains("Celebrity")){

            if(ClassInstances.celebrity.hasValue(uuid) &&
                    ((Integer)ClassInstances.celebrity.getValue(uuid))>=1){
                Sounds.NO.play(player);
            }else if(ClassInstances.renownData.getRenown(uuid)>=300){
                Sounds.RENOWN_SHOP_PURCHASE.play(player);
                ClassInstances.renownData.setRenown(uuid, ClassInstances.renownData.getRenown(uuid)-300);
                ClassInstances.celebrity.setValue(uuid, (Integer) 1);
            }else{
                Sounds.NO.play(player);
            }

            player.openInventory(getRenownShopUpgradesGUI(player));
        }else if(event.getCurrentItem().getType().equals(Material.ARROW)){
            player.openInventory(RenownShopGUI.getRenownShopGUI(player));
            Sounds.BUTTON.play(player);
        }

    }
}
