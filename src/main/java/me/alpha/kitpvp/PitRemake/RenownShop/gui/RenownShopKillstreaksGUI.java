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
import static me.alpha.kitpvp.utils.IntegerHelper.integerToRoman;
import static me.alpha.kitpvp.utils.advancedInventory.ItemMaker;


public class RenownShopKillstreaksGUI implements Listener {

    public static ItemStack getMoonItem(String uuid){
        if(ClassInstances.prestigeData.getPrestige(uuid)<14){
            return ItemMaker(Material.BEDROCK, ChatColor.RED + "Unknown upgrade",
                    colorCode("&7Prestige: &e"+integerToRoman(14)),1, true);
        }
        
        if(ClassInstances.moonStreak.hasValue(uuid) &&
                ((Integer)ClassInstances.moonStreak.getValue(uuid))>=1){
            return ItemMaker(Material.ENDER_STONE, ChatColor.GREEN + "Killstreaks: To the Moon",
                    colorCode(ClassInstances.moonStreak.getLore() + "\n\n" +
                            "&aUnlocked!"),1, true);
        }else{
            return ItemMaker(Material.ENDER_STONE, ChatColor.YELLOW + "Killstreaks: To the Moon",
                    colorCode(ClassInstances.moonStreak.getLore() + "\n\n" +
                            "&7Cost: &e70 Renown\n" +
                            "&7You have: &e"+ClassInstances.renownData.getRenown(uuid)+" Renown\n\n" +
                            "&eClick to purchase!"),1, true);
        }
    }

    public static ItemStack getBeastItem(String uuid){
        if(ClassInstances.prestigeData.getPrestige(uuid)<3){
            return ItemMaker(Material.BEDROCK, ChatColor.RED + "Unknown upgrade",
                    colorCode("&7Prestige: &e"+integerToRoman(3)),1, true);
        }
        
        if(ClassInstances.beastmodeStreak.hasValue(uuid) &&
                ((Integer)ClassInstances.beastmodeStreak.getValue(uuid))>=1){
            return ItemMaker(Material.DIAMOND_HELMET, ChatColor.GREEN + "Killstreaks: Beastmode",
                    colorCode(ClassInstances.beastmodeStreak.getLore() + "\n\n" +
                            "&aUnlocked!"),1, true);
        }else{
            return ItemMaker(Material.DIAMOND_HELMET, ChatColor.YELLOW + "Killstreaks: Beastmode",
                    colorCode(ClassInstances.beastmodeStreak.getLore() + "\n\n" +
                            "&7Cost: &e20 Renown\n" +
                            "&7You have: &e"+ClassInstances.renownData.getRenown(uuid)+" Renown\n\n" +
                            "&eClick to purchase!"),1, true);
        }
    }

    public static ItemStack getHermitItem(String uuid){
        if(ClassInstances.prestigeData.getPrestige(uuid)<12){
            return ItemMaker(Material.BEDROCK, ChatColor.RED + "Unknown upgrade",
                    colorCode("&7Prestige: &e"+integerToRoman(12)),1, true);
        }

        if(ClassInstances.hermitStreak.hasValue(uuid) &&
                ((Integer)ClassInstances.hermitStreak.getValue(uuid))>=1){
            return ItemMaker(Material.BED, ChatColor.GREEN + "Killstreaks: Hermit",
                    colorCode(ClassInstances.hermitStreak.getLore() + "\n\n" +
                            "&aUnlocked!"),1, true);
        }else{
            return ItemMaker(Material.BED, ChatColor.YELLOW + "Killstreaks: Hermit",
                    colorCode(ClassInstances.hermitStreak.getLore() + "\n\n" +
                            "&7Cost: &e100 Renown\n" +
                            "&7You have: &e"+ClassInstances.renownData.getRenown(uuid)+" Renown\n\n" +
                            "&eClick to purchase!"),1, true);
        }
    }

    public static ItemStack getHighlanderItem(String uuid){
        if(ClassInstances.prestigeData.getPrestige(uuid)<7){
            return ItemMaker(Material.BEDROCK, ChatColor.RED + "Unknown upgrade",
                    colorCode("&7Prestige: &e"+integerToRoman(7)),1, true);
        }

        if(ClassInstances.highlanderStreak.hasValue(uuid) &&
                ((Integer)ClassInstances.highlanderStreak.getValue(uuid))>=1){
            return ItemMaker(Material.GOLD_BOOTS, ChatColor.GREEN + "Killstreaks: Highlander",
                    colorCode(ClassInstances.highlanderStreak.getLore() + "\n\n" +
                            "&aUnlocked!"),1, true);
        }else{
            return ItemMaker(Material.GOLD_BOOTS, ChatColor.YELLOW + "Killstreaks: Highlander",
                    colorCode(ClassInstances.highlanderStreak.getLore() + "\n\n" +
                            "&7Cost: &e50 Renown\n" +
                            "&7You have: &e"+ClassInstances.renownData.getRenown(uuid)+" Renown\n\n" +
                            "&eClick to purchase!"),1, true);
        }
    }

    public static ItemStack getMagnumOpusItem(String uuid){
        if(ClassInstances.prestigeData.getPrestige(uuid)<10){
            return ItemMaker(Material.BEDROCK, ChatColor.RED + "Unknown upgrade",
                    colorCode("&7Prestige: &e"+integerToRoman(10)),1, true);
        }

        if(ClassInstances.magnumOpus.hasValue(uuid) &&
                ((Integer)ClassInstances.magnumOpus.getValue(uuid))>=1){
            return ItemMaker(Material.NETHER_STAR, ChatColor.GREEN + "Killstreaks: Magnum Opus",
                    colorCode(ClassInstances.magnumOpus.getLore() + "\n\n" +
                            "&aUnlocked!"),1, true);
        }else{
            return ItemMaker(Material.NETHER_STAR, ChatColor.YELLOW + "Killstreaks: Magnum Opus",
                    colorCode(ClassInstances.magnumOpus.getLore() + "\n\n" +
                            "&7Cost: &e50 Renown\n" +
                            "&7You have: &e"+ClassInstances.renownData.getRenown(uuid)+" Renown\n\n" +
                            "&eClick to purchase!"),1, true);
        }
    }

    public static ItemStack getUberItem(String uuid){

        if(ClassInstances.prestigeData.getPrestige(uuid)<20){
            return ItemMaker(Material.BEDROCK, ChatColor.RED + "Unknown upgrade",
                    colorCode("&7Prestige: &e"+integerToRoman(20)),1, true);
        }

        if(ClassInstances.uberStreak.hasValue(uuid) &&
                ((Integer)ClassInstances.uberStreak.getValue(uuid))>=1){
            return ItemMaker(Material.GOLD_SWORD, ChatColor.GREEN + "Killstreaks: Uberstreak",
                    colorCode(ClassInstances.uberStreak.getLore() + "\n\n" +
                            "&aUnlocked!"),1, true);
        }else{
            return ItemMaker(Material.GOLD_SWORD, ChatColor.YELLOW + "Killstreaks: Uberstreak",
                    colorCode(ClassInstances.uberStreak.getLore() + "\n\n" +
                            "&7Cost: &e100 Renown\n" +
                            "&7You have: &e"+ClassInstances.renownData.getRenown(uuid)+" Renown\n\n" +
                            "&eClick to purchase!"),1, true);
        }
    }
    
    public static ItemStack getGoBackItem(String uuid){
        return ItemMaker(Material.ARROW, ChatColor.GREEN + "Go Back",
                ChatColor.GRAY+"To Prestige & Renown",1, true);
    }

    public static Inventory getRenownShopKillstreaksGUI(Player player){
        String uuid = String.valueOf(player.getUniqueId());
        Inventory gui = advancedInventory.inv(player, 45, ChatColor.GRAY + "Renown Shop - Killstreaks");
        ItemStack base_glass = advancedInventory.cGlass();

        for (int i = 0; i < 10; i++) {
            advancedInventory.addInv(gui, base_glass, i, 1, false);
            advancedInventory.addInv(gui, base_glass, i, 2, false);
            advancedInventory.addInv(gui, base_glass, i, 3, false);
            advancedInventory.addInv(gui, base_glass, i, 4, false);
            advancedInventory.addInv(gui, base_glass, i, 5, false);
        }

        advancedInventory.addInv(gui, getBeastItem(uuid), 2, 2, false);

        advancedInventory.addInv(gui, getHighlanderItem(uuid), 3, 2, false);

        advancedInventory.addInv(gui, getHermitItem(uuid), 4, 2, false);

        advancedInventory.addInv(gui, getMagnumOpusItem(uuid), 5, 2, false);

        advancedInventory.addInv(gui, getMoonItem(uuid), 6, 2, false);

        advancedInventory.addInv(gui, getUberItem(uuid), 7, 2, false);
        
        advancedInventory.addInv(gui, getGoBackItem(uuid), 5,5, false);

        //advancedInventory.addInv(gui, getOverDriveItem(uuid), 2, 2, false);

        return gui;
    }

    @EventHandler
    public void HandleRenownShopUpgradesClick(InventoryClickEvent event){
        if(event==null||
                event.getClickedInventory()==null) return;

        if(event.getClickedInventory() != null &&
                event.getClickedInventory().getTitle() != null &&
                !event.getClickedInventory().getTitle().equals(ChatColor.GRAY + "Renown Shop - Killstreaks")) return;

        Player player = (Player) event.getWhoClicked();
        String uuid = player.getUniqueId().toString();

        event.setCancelled(true);

        if(event.getCurrentItem().getType().equals(Material.GOLD_BOOTS)){

            if(ClassInstances.highlanderStreak.hasValue(uuid) &&
                    ((Integer)ClassInstances.highlanderStreak.getValue(uuid))>=1){
                Sounds.NO.play(player);
            }else if(ClassInstances.renownData.getRenown(uuid)>=50){
                Sounds.RENOWN_SHOP_PURCHASE.play(player);
                ClassInstances.renownData.setRenown(uuid, ClassInstances.renownData.getRenown(uuid)-50);
                ClassInstances.highlanderStreak.setValue(uuid, (Integer) 1);
            }else{
                Sounds.NO.play(player);
            }

            player.openInventory(getRenownShopKillstreaksGUI(player));
        }else if(event.getCurrentItem().getType().equals(Material.DIAMOND_HELMET)){

            if(ClassInstances.beastmodeStreak.hasValue(uuid) &&
                    ((Integer)ClassInstances.beastmodeStreak.getValue(uuid))>=1){
                Sounds.NO.play(player);
            }else if(ClassInstances.renownData.getRenown(uuid)>=20){
                Sounds.RENOWN_SHOP_PURCHASE.play(player);
                ClassInstances.renownData.setRenown(uuid, ClassInstances.renownData.getRenown(uuid)-20);
                ClassInstances.beastmodeStreak.setValue(uuid, (Integer) 1);
            }else{
                Sounds.NO.play(player);
            }

            player.openInventory(getRenownShopKillstreaksGUI(player));
        }else if(event.getCurrentItem().getType().equals(Material.BED)){

            if(ClassInstances.hermitStreak.hasValue(uuid) &&
                    ((Integer)ClassInstances.hermitStreak.getValue(uuid))>=1){
                Sounds.NO.play(player);
            }else if(ClassInstances.renownData.getRenown(uuid)>=100){
                Sounds.RENOWN_SHOP_PURCHASE.play(player);
                ClassInstances.renownData.setRenown(uuid, ClassInstances.renownData.getRenown(uuid)-100);
                ClassInstances.hermitStreak.setValue(uuid, (Integer) 1);
            }else{
                Sounds.NO.play(player);
            }

            player.openInventory(getRenownShopKillstreaksGUI(player));
        }else if(event.getCurrentItem().getType().equals(Material.ENDER_STONE)){

            if(ClassInstances.moonStreak.hasValue(uuid) &&
                    ((Integer)ClassInstances.moonStreak.getValue(uuid))>=1){
                Sounds.NO.play(player);
            }else if(ClassInstances.renownData.getRenown(uuid)>=70){
                Sounds.RENOWN_SHOP_PURCHASE.play(player);
                ClassInstances.renownData.setRenown(uuid, ClassInstances.renownData.getRenown(uuid)-70);
                ClassInstances.moonStreak.setValue(uuid, (Integer) 1);
            }else{
                Sounds.NO.play(player);
            }

            player.openInventory(getRenownShopKillstreaksGUI(player));
        }else if(event.getCurrentItem().getType().equals(Material.NETHER_STAR)){

            if(ClassInstances.magnumOpus.hasValue(uuid) &&
                    ((Integer)ClassInstances.magnumOpus.getValue(uuid))>=1){
                Sounds.NO.play(player);
            }else if(ClassInstances.renownData.getRenown(uuid)>=50){
                Sounds.RENOWN_SHOP_PURCHASE.play(player);
                ClassInstances.renownData.setRenown(uuid, ClassInstances.renownData.getRenown(uuid)-5);
                ClassInstances.magnumOpus.setValue(uuid, (Integer) 1);
            }else{
                Sounds.NO.play(player);
            }

            player.openInventory(getRenownShopKillstreaksGUI(player));
        }else if(event.getCurrentItem().getType().equals(Material.GOLD_SWORD)){

            if(ClassInstances.uberStreak.hasValue(uuid) &&
                    ((Integer)ClassInstances.uberStreak.getValue(uuid))>=1){
                Sounds.NO.play(player);
            }else if(ClassInstances.renownData.getRenown(uuid)>=100){
                Sounds.RENOWN_SHOP_PURCHASE.play(player);
                ClassInstances.renownData.setRenown(uuid, ClassInstances.renownData.getRenown(uuid)-100);
                ClassInstances.uberStreak.setValue(uuid, (Integer) 1);
            }else{
                Sounds.NO.play(player);
            }

            player.openInventory(getRenownShopKillstreaksGUI(player));
        }else if(event.getCurrentItem().getType().equals(Material.BEDROCK)){
            player.sendMessage(ChatColor.RED + "You are too low prestige to acquire this!");
            Sounds.NO.play(player);
        }else if(event.getCurrentItem().getType().equals(Material.ARROW)){
            player.openInventory(RenownShopGUI.getRenownShopGUI(player));
            Sounds.BUTTON.play(player);
        }

    }
}
