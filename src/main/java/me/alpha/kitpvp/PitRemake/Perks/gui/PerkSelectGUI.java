package me.alpha.kitpvp.PitRemake.Perks.gui;


import me.alpha.kitpvp.ChatManager.ChatManager;
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

import java.util.Objects;

import static me.alpha.kitpvp.Data.XpData.GetCurrentLevel;
import static me.alpha.kitpvp.utils.ColorUtil.colorCode;
import static me.alpha.kitpvp.utils.advancedInventory.HeadMaker;
import static me.alpha.kitpvp.utils.advancedInventory.ItemMaker;

public class PerkSelectGUI implements Listener {

    public static boolean getPerkSlot(String uuid, String string){
        if(String.valueOf(ClassInstances.perkSlotOne.getValue(uuid, "")).equals(string)) return true;
        else if(String.valueOf(ClassInstances.perkSlotTwo.getValue(uuid, "")).equals(string)) return true;
        else if(String.valueOf(ClassInstances.perkSlotThree.getValue(uuid, "")).equals(string)) return true;
        else return String.valueOf(ClassInstances.perkSlotFour.getValue(uuid, "")).equals(string);
    }

    public static ItemStack getGoBackItem(String uuid){
        return ItemMaker(Material.ARROW, ChatColor.GREEN + "Go Back",
                ChatColor.GRAY+"To permanent upgrades",1, true);
    }

    public static ItemStack getNoPerkItem(String uuid){
        return ItemMaker(Material.DIAMOND_BLOCK, ChatColor.RED + "No perk",
                ChatColor.GRAY+"Are you hardcore enough that you\n" +
                        ChatColor.GRAY + "don't need any perk for this\n" +
                        ChatColor.GRAY + "slot?\n\n" +
                ChatColor.YELLOW + "Click to remove perk!",1, true);
    }

    public static ItemStack getVampireItem(String uuid, Player player){
        
        int[] playerData = GetCurrentLevel(uuid, ClassInstances.xpData.getXp(uuid), ClassInstances.prestigeData.getPrestige(uuid), player);
        int level = playerData[1];
        int neededXP = playerData[0];
        boolean theWay = ClassInstances.theWay.hasValue(uuid) &&
                ((Integer) ClassInstances.theWay.getValue(uuid)) >= 1;

        if(level<25 && !theWay) return ItemMaker(Material.BEDROCK, ChatColor.RED + ClassInstances.vampire.getName(),
                colorCode(ClassInstances.vampire.getLore() + "\n\n" +
                        "&cThis requires level " + ChatManager.getBracketsWithLevel(uuid,25) + "\n" +
                        "&cor higher."), 1, true);

        if(getPerkSlot(uuid, ClassInstances.vampire.getRefID())){
            return ItemMaker(ClassInstances.vampire.getMaterial(), ChatColor.GREEN + ClassInstances.vampire.getName(),
                    colorCode(ClassInstances.vampire.getLore() + "\n\n" +
                            "&aAlready selected!"),1, true);
        }else{
            return ItemMaker(ClassInstances.vampire.getMaterial(), ChatColor.YELLOW + ClassInstances.vampire.getName(),
                    colorCode(ClassInstances.vampire.getLore() + "\n\n"+
                            "&eClick to select!"),1, true);
        }
    }

    public static ItemStack getDirtyItem(String uuid, Player player){
        
        int[] playerData = GetCurrentLevel(uuid, ClassInstances.xpData.getXp(uuid), ClassInstances.prestigeData.getPrestige(uuid), player);
        int level = playerData[1];
        int neededXP = playerData[0];
        boolean theWay = ClassInstances.theWay.hasValue(uuid) &&
                ((Integer) ClassInstances.theWay.getValue(uuid)) >= 1;

        if(level<30 && !theWay) return ItemMaker(Material.BEDROCK, ChatColor.RED + ClassInstances.dirty.getName(),
                colorCode(ClassInstances.dirty.getLore() + "\n\n" +
                        "&cThis requires level " + ChatManager.getBracketsWithLevel(uuid,30) + "\n" +
                        "&cor higher."), 1, true);

        if(getPerkSlot(uuid, ClassInstances.dirty.getRefID())){
            return ItemMaker(ClassInstances.dirty.getMaterial(), ChatColor.GREEN + ClassInstances.dirty.getName(),
                    colorCode(ClassInstances.dirty.getLore() + "\n\n" +
                            "&aAlready selected!"),1, true);
        }else{
            return ItemMaker(ClassInstances.dirty.getMaterial(), ChatColor.YELLOW + ClassInstances.dirty.getName(),
                    colorCode(ClassInstances.dirty.getLore() + "\n\n"+
                            "&eClick to select!"),1, true);
        }
    }

    public static ItemStack getStreakerItem(String uuid, Player player){
        
        int[] playerData = GetCurrentLevel(uuid, ClassInstances.xpData.getXp(uuid), ClassInstances.prestigeData.getPrestige(uuid), player);
        int level = playerData[1];
        int neededXP = playerData[0];
        boolean theWay = ClassInstances.theWay.hasValue(uuid) &&
                ((Integer) ClassInstances.theWay.getValue(uuid)) >= 1;

        if(level<30 && !theWay) return ItemMaker(Material.BEDROCK, ChatColor.RED + ClassInstances.streaker.getName(),
                colorCode(ClassInstances.streaker.getLore() + "\n\n" +
                        "&cThis requires level " + ChatManager.getBracketsWithLevel(uuid,30) + "\n" +
                        "&cor higher."), 1, true);

        if(getPerkSlot(uuid, ClassInstances.streaker.getRefID())){
            return ItemMaker(ClassInstances.streaker.getMaterial(), ChatColor.GREEN + ClassInstances.streaker.getName(),
                    colorCode(ClassInstances.streaker.getLore() + "\n\n" +
                            "&aAlready selected!"),1, true);
        }else{
            return ItemMaker(ClassInstances.streaker.getMaterial(), ChatColor.YELLOW + ClassInstances.streaker.getName(),
                    colorCode(ClassInstances.streaker.getLore() + "\n\n"+
                            "&eClick to select!"),1, true);
        }
    }

    public static ItemStack getAssistantStreakerItem(String uuid, Player player){
        
        int[] playerData = GetCurrentLevel(uuid, ClassInstances.xpData.getXp(uuid), ClassInstances.prestigeData.getPrestige(uuid), player);
        int level = playerData[1];
        int neededXP = playerData[0];
        boolean theWay = ClassInstances.theWay.hasValue(uuid) &&
                ((Integer) ClassInstances.theWay.getValue(uuid)) >= 1;

        if(level<50 && !theWay) return ItemMaker(Material.BEDROCK, ChatColor.RED + ClassInstances.assistantStreaker.getName(),
                colorCode(ClassInstances.assistantStreaker.getLore() + "\n\n" +
                        "&cThis requires level " + ChatManager.getBracketsWithLevel(uuid,50) + "\n" +
                        "&cor higher."), 1, true);

        if(getPerkSlot(uuid, ClassInstances.assistantStreaker.getRefID())){
            return ItemMaker(ClassInstances.assistantStreaker.getMaterial(), ChatColor.GREEN + ClassInstances.assistantStreaker.getName(),
                    colorCode(ClassInstances.assistantStreaker.getLore() + "\n\n" +
                            "&aAlready selected!"),1, true);
        }else{
            return ItemMaker(ClassInstances.assistantStreaker.getMaterial(), ChatColor.YELLOW + ClassInstances.assistantStreaker.getName(),
                    colorCode(ClassInstances.assistantStreaker.getLore() + "\n\n"+
                            "&eClick to select!"),1, true);
        }
    }

    public static ItemStack getGladiatorItem(String uuid, Player player){
        
        int[] playerData = GetCurrentLevel(uuid, ClassInstances.xpData.getXp(uuid), ClassInstances.prestigeData.getPrestige(uuid), player);
        int level = playerData[1];
        int neededXP = playerData[0];
        boolean theWay = ClassInstances.theWay.hasValue(uuid) &&
                ((Integer) ClassInstances.theWay.getValue(uuid)) >= 1;

        if(level<5 && !theWay) return ItemMaker(Material.BEDROCK, ChatColor.RED + ClassInstances.gladiator.getName(),
                colorCode(ClassInstances.gladiator.getLore() + "\n\n" +
                        "&cThis requires level " + ChatManager.getBracketsWithLevel(uuid,5) + "\n" +
                        "&cor higher."), 1, true);

        if(getPerkSlot(uuid, ClassInstances.gladiator.getRefID())){
            return ItemMaker(ClassInstances.gladiator.getMaterial(), ChatColor.GREEN + ClassInstances.gladiator.getName(),
                    colorCode(ClassInstances.gladiator.getLore() + "\n\n" +
                            "&aAlready selected!"),1, true);
        }else{
            return ItemMaker(ClassInstances.gladiator.getMaterial(), ChatColor.YELLOW + ClassInstances.gladiator.getName(),
                    colorCode(ClassInstances.gladiator.getLore() + "\n\n"+
                            "&eClick to select!"),1, true);
        }
    }

    public static ItemStack getFirstStrikeItem(String uuid, Player player){

        int[] playerData = GetCurrentLevel(uuid, ClassInstances.xpData.getXp(uuid), ClassInstances.prestigeData.getPrestige(uuid), player);
        int level = playerData[1];
        int neededXP = playerData[0];
        boolean theWay = ClassInstances.theWay.hasValue(uuid) &&
                ((Integer) ClassInstances.theWay.getValue(uuid)) >= 1;

        if(level<60 && !theWay) return ItemMaker(Material.BEDROCK, ChatColor.RED + ClassInstances.firstStrike.getName(),
                colorCode(ClassInstances.firstStrike.getLore() + "\n\n" +
                        "&cThis requires level " + ChatManager.getBracketsWithLevel(uuid,60) + "\n" +
                        "&cor higher."), 1, true);

        if(getPerkSlot(uuid, ClassInstances.firstStrike.getRefID())){
            return ItemMaker(ClassInstances.firstStrike.getMaterial(), ChatColor.GREEN + ClassInstances.firstStrike.getName(),
                    colorCode(ClassInstances.firstStrike.getLore() + "\n\n" +
                            "&aAlready selected!"),1, true);
        }else{
            return ItemMaker(ClassInstances.firstStrike.getMaterial(), ChatColor.YELLOW + ClassInstances.firstStrike.getName(),
                    colorCode(ClassInstances.firstStrike.getLore() + "\n\n"+
                            "&eClick to select!"),1, true);
        }
    }

    public static ItemStack getSoupItem(String uuid, Player player){

        int[] playerData = GetCurrentLevel(uuid, ClassInstances.xpData.getXp(uuid), ClassInstances.prestigeData.getPrestige(uuid), player);
        int level = playerData[1];
        int neededXP = playerData[0];
        boolean theWay = ClassInstances.theWay.hasValue(uuid) &&
                ((Integer) ClassInstances.theWay.getValue(uuid)) >= 1;

        if(level<25 && !theWay) return ItemMaker(Material.BEDROCK, ChatColor.RED + ClassInstances.soup.getName(),
                colorCode(ClassInstances.soup.getLore() + "\n\n" +
                        "&cThis requires level " + ChatManager.getBracketsWithLevel(uuid,25) + "\n" +
                        "&cor higher."), 1, true);

        if(getPerkSlot(uuid, ClassInstances.soup.getRefID())){
            return ItemMaker(ClassInstances.soup.getMaterial(), ChatColor.GREEN + ClassInstances.soup.getName(),
                    colorCode(ClassInstances.soup.getLore() + "\n\n" +
                            "&aAlready selected!"),1, true);
        }else{
            return ItemMaker(ClassInstances.soup.getMaterial(), ChatColor.YELLOW + ClassInstances.soup.getName(),
                    colorCode(ClassInstances.soup.getLore() + "\n\n"+
                            "&eClick to select!"),1, true);
        }
    }

    public static ItemStack getFishingRodItem(String uuid, Player player){

        int[] playerData = GetCurrentLevel(uuid, ClassInstances.xpData.getXp(uuid), ClassInstances.prestigeData.getPrestige(uuid), player);
        int level = playerData[1];
        int neededXP = playerData[0];
        boolean theWay = ClassInstances.theWay.hasValue(uuid) &&
                ((Integer) ClassInstances.theWay.getValue(uuid)) >= 1;

        if(level<5 && !theWay) return ItemMaker(Material.BEDROCK, ChatColor.RED + ClassInstances.fishingRod.getName(),
                colorCode(ClassInstances.fishingRod.getLore() + "\n\n" +
                        "&cThis requires level " + ChatManager.getBracketsWithLevel(uuid,5) + "\n" +
                        "&cor higher."), 1, true);

        if(getPerkSlot(uuid, ClassInstances.fishingRod.getRefID())){
            return ItemMaker(ClassInstances.fishingRod.getMaterial(), ChatColor.GREEN + ClassInstances.fishingRod.getName(),
                    colorCode(ClassInstances.fishingRod.getLore() + "\n\n" +
                            "&aAlready selected!"),1, true);
        }else{
            return ItemMaker(ClassInstances.fishingRod.getMaterial(), ChatColor.YELLOW + ClassInstances.fishingRod.getName(),
                    colorCode(ClassInstances.fishingRod.getLore() + "\n\n"+
                            "&eClick to select!"),1, true);
        }
    }



    public static ItemStack getStrengthItem(String uuid, Player player){
        int[] playerData = GetCurrentLevel(uuid, ClassInstances.xpData.getXp(uuid), ClassInstances.prestigeData.getPrestige(uuid), player);
        int level = playerData[1];
        int neededXP = playerData[0];
        boolean theWay = ClassInstances.theWay.hasValue(uuid) &&
                ((Integer) ClassInstances.theWay.getValue(uuid)) >= 1;

        if(level<10 && !theWay) return ItemMaker(Material.BEDROCK, ChatColor.RED + ClassInstances.strengthChaining.getName(),
                colorCode(ClassInstances.strengthChaining.getLore() + "\n\n" +
                        "&cThis requires level " + ChatManager.getBracketsWithLevel(uuid,10) + "\n" +
                        "&cor higher."), 1, true);

        if(getPerkSlot(uuid, ClassInstances.strengthChaining.getRefID())){
            return ItemMaker(ClassInstances.strengthChaining.getMaterial(), ChatColor.GREEN + ClassInstances.strengthChaining.getName(),
                    colorCode(ClassInstances.strengthChaining.getLore() + "\n\n" +
                            "&aAlready selected!"),1, true);
        }else{
            return ItemMaker(ClassInstances.strengthChaining.getMaterial(), ChatColor.YELLOW + ClassInstances.strengthChaining.getName(),
                    colorCode(ClassInstances.strengthChaining.getLore() + "\n\n"+
                            "&eClick to select!"),1, true);
        }
    }



    public static ItemStack getGoldenHeadItem(String uuid, Player player){
        int[] playerData = GetCurrentLevel(uuid, ClassInstances.xpData.getXp(uuid), ClassInstances.prestigeData.getPrestige(uuid), player);
        int level = playerData[1];
        int neededXP = playerData[0];
        boolean theWay = ClassInstances.theWay.hasValue(uuid) &&
                ((Integer) ClassInstances.theWay.getValue(uuid)) >= 1;
        
        if(level<1 && !theWay) return ItemMaker(Material.BEDROCK, ChatColor.RED + ClassInstances.goldenHeads.getName(),
                colorCode(ClassInstances.goldenHeads.getLore() + "\n\n" +
                        "&cThis requires level " + ChatManager.getBracketsWithLevel(uuid,1) + "\n" +
                        "&cor higher."), 1, true);

        if(getPerkSlot(uuid, ClassInstances.goldenHeads.getRefID())){
            return HeadMaker("ifishdupe", ChatColor.GREEN + ClassInstances.goldenHeads.getName(),
                    colorCode(ClassInstances.goldenHeads.getLore() + "\n\n" +
                            "&aAlready selected!"));
        }else{
            return HeadMaker("ifishdupe", ChatColor.YELLOW + ClassInstances.goldenHeads.getName(),
                    colorCode(ClassInstances.goldenHeads.getLore() + "\n\n"+
                            "&eClick to select!"));
        }
    }

    public static Inventory getPerkSelectMenu(Player player, int slot){
        String uuid = String.valueOf(player.getUniqueId());
        Inventory gui = advancedInventory.inv(player, 45, ChatColor.GRAY + "Choose a perk - Slot #" + slot);
        ItemStack base_glass = advancedInventory.cGlass();

        for (int i = 0; i < 10; i++) {
            advancedInventory.addInv(gui, base_glass, i, 1, false);
            advancedInventory.addInv(gui, base_glass, i, 2, false);
            advancedInventory.addInv(gui, base_glass, i, 3, false);
            advancedInventory.addInv(gui, base_glass, i, 4, false);
            advancedInventory.addInv(gui, base_glass, i, 5, false);
        }

        advancedInventory.addInv(gui, getGoldenHeadItem(uuid, player), 2, 2, false);
        advancedInventory.addInv(gui, getVampireItem(uuid, player), 3, 2, false);
        advancedInventory.addInv(gui, getDirtyItem(uuid, player), 4, 2, false);
        advancedInventory.addInv(gui, getStreakerItem(uuid, player), 5, 2, false);
        advancedInventory.addInv(gui, getStrengthItem(uuid, player), 6, 2, false);
        advancedInventory.addInv(gui, getGladiatorItem(uuid, player), 7, 2, false);
        advancedInventory.addInv(gui, getAssistantStreakerItem(uuid, player), 8, 2, false);

        advancedInventory.addInv(gui, getFirstStrikeItem(uuid, player), 2, 3, false);
        advancedInventory.addInv(gui, getFishingRodItem(uuid, player), 3, 3, false);
        advancedInventory.addInv(gui, getSoupItem(uuid, player), 4, 3, false);

        advancedInventory.addInv(gui, getGoBackItem(uuid), 5, 5, false);
        advancedInventory.addInv(gui, getNoPerkItem(uuid), 6, 5, false);


        //advancedInventory.addInv(gui, getOverDriveItem(uuid), 2, 2, false);

        return gui;
    }

    public boolean hasValue(String uuid, int slot){
        if(slot==1){
            return ClassInstances.perkSlotOne.hasValue(uuid);
        }else if(slot==2){
            return ClassInstances.perkSlotTwo.hasValue(uuid);
        }else if(slot==3){
            return ClassInstances.perkSlotThree.hasValue(uuid);
        }else if(slot==4){
            return ClassInstances.perkSlotFour.hasValue(uuid);
        }

        return false;
    }

    public String getValue(String uuid, int slot){
        if(slot==1){
            return String.valueOf(ClassInstances.perkSlotOne.getValue(uuid, ""));
        }else if(slot==2){
            return String.valueOf(ClassInstances.perkSlotTwo.getValue(uuid, ""));
        }else if(slot==3){
            return String.valueOf(ClassInstances.perkSlotThree.getValue(uuid, ""));
        }else if(slot==4){
            return String.valueOf(ClassInstances.perkSlotFour.getValue(uuid, ""));
        }

        return "";
    }

    public void setValue(String uuid, String data, int slot){
        if(slot==1){
            ClassInstances.perkSlotOne.setValue(uuid, data);
        }else if(slot==2){
            ClassInstances.perkSlotTwo.setValue(uuid, data);
        }else if(slot==3){
            ClassInstances.perkSlotThree.setValue(uuid, data);
        }else if(slot==4){
            ClassInstances.perkSlotFour.setValue(uuid, data);
        }
    }

    public boolean hasGoldenHead(String uuid){
        if(String.valueOf(ClassInstances.perkSlotOne.getValue(uuid, "")).equals(ClassInstances.goldenHeads.getRefID())) return true;
        if(String.valueOf(ClassInstances.perkSlotTwo.getValue(uuid, "")).equals(ClassInstances.goldenHeads.getRefID())) return true;
        if(String.valueOf(ClassInstances.perkSlotThree.getValue(uuid, "")).equals(ClassInstances.goldenHeads.getRefID())) return true;
        return String.valueOf(ClassInstances.perkSlotFour.getValue(uuid, "")).equals(ClassInstances.goldenHeads.getRefID());
    }

    public boolean hasSoup(String uuid){
        if(String.valueOf(ClassInstances.perkSlotOne.getValue(uuid, "")).equals(ClassInstances.soup.getRefID())) return true;
        if(String.valueOf(ClassInstances.perkSlotTwo.getValue(uuid, "")).equals(ClassInstances.soup.getRefID())) return true;
        if(String.valueOf(ClassInstances.perkSlotThree.getValue(uuid, "")).equals(ClassInstances.soup.getRefID())) return true;
        return String.valueOf(ClassInstances.perkSlotFour.getValue(uuid, "")).equals(ClassInstances.soup.getRefID());
    }

    public boolean hasVampire(String uuid){
        if(String.valueOf(ClassInstances.perkSlotOne.getValue(uuid, "")).equals(ClassInstances.vampire.getRefID())) return true;
        if(String.valueOf(ClassInstances.perkSlotTwo.getValue(uuid, "")).equals(ClassInstances.vampire.getRefID())) return true;
        if(String.valueOf(ClassInstances.perkSlotThree.getValue(uuid, "")).equals(ClassInstances.vampire.getRefID())) return true;
        return String.valueOf(ClassInstances.perkSlotFour.getValue(uuid, "")).equals(ClassInstances.vampire.getRefID());
    }

    @EventHandler
    public void HandlePerkSelectEvent(InventoryClickEvent event) {
        if(event==null ||
                event.getClickedInventory()==null ||
                event.getClickedInventory().getTitle()==null) return;
        if (event.getClickedInventory() != null &&
                event.getClickedInventory().getTitle() != null &&
                !event.getClickedInventory().getTitle().contains(ChatColor.GRAY + "Choose a perk")) return;

        Player player = (Player) event.getWhoClicked();
        String uuid = player.getUniqueId().toString();

        int[] playerData = GetCurrentLevel(uuid, ClassInstances.xpData.getXp(uuid), ClassInstances.prestigeData.getPrestige(uuid), player);
        int level = playerData[1];
        int neededXP = playerData[0];
        
        boolean theWay = ClassInstances.theWay.hasValue(uuid) &&
                ((Integer) ClassInstances.theWay.getValue(uuid)) >= 1;

        event.setCancelled(true);

        int perkSlot = Integer.parseInt(ChatColor.stripColor(event.getClickedInventory().getName().replaceAll("Choose a perk - Slot #", "")));

        if (event.getCurrentItem().getType().equals(ClassInstances.assistantStreaker.getMaterial())) {
            

            if (hasValue(uuid, perkSlot) &&
                    Objects.equals(getValue(uuid, perkSlot), ClassInstances.assistantStreaker.getRefID())) {
                Sounds.NO.play(player);
            } else if (level >= 50 || theWay) {
                Sounds.GAMBLE_YES.play(player);
                setValue(uuid, ClassInstances.assistantStreaker.getRefID(), perkSlot);
            } else {
                Sounds.NO.play(player);
            }

            player.openInventory(PermanentUpgrades.getPermanentUpgrades(player));
        } else if (event.getCurrentItem().getType().equals(ClassInstances.vampire.getMaterial())) {
            

            if(hasGoldenHead(uuid) || hasSoup(uuid)) {
                Sounds.NO.play(player);
            }else if (hasValue(uuid, perkSlot) &&
                    Objects.equals(getValue(uuid, perkSlot), ClassInstances.vampire.getRefID())) {
                Sounds.NO.play(player);
            } else if (level >= 25 || theWay) {
                Sounds.GAMBLE_YES.play(player);
                setValue(uuid, ClassInstances.vampire.getRefID(), perkSlot);
            } else {
                Sounds.NO.play(player);
            }

            player.openInventory(PermanentUpgrades.getPermanentUpgrades(player));
        } else if (event.getCurrentItem().getType().equals(ClassInstances.dirty.getMaterial())) {
            

            if (hasValue(uuid, perkSlot) &&
                    Objects.equals(getValue(uuid, perkSlot), ClassInstances.dirty.getRefID())) {
                Sounds.NO.play(player);
            } else if (level >= 30 || theWay) {
                Sounds.GAMBLE_YES.play(player);
                setValue(uuid, ClassInstances.dirty.getRefID(), perkSlot);
            } else {
                Sounds.NO.play(player);
            }

            player.openInventory(PermanentUpgrades.getPermanentUpgrades(player));
        } else if (event.getCurrentItem().getType().equals(ClassInstances.strengthChaining.getMaterial())) {

            if (hasValue(uuid, perkSlot) &&
                    Objects.equals(getValue(uuid, perkSlot), ClassInstances.strengthChaining.getRefID())) {
                Sounds.NO.play(player);
            } else if (level >= 10 || theWay) {
                Sounds.GAMBLE_YES.play(player);
                setValue(uuid, ClassInstances.strengthChaining.getRefID(), perkSlot);
            } else {
                Sounds.NO.play(player);
            }

            player.openInventory(PermanentUpgrades.getPermanentUpgrades(player));
        } else if (event.getCurrentItem().getType().equals(ClassInstances.streaker.getMaterial())) {

            if (hasValue(uuid, perkSlot) &&
                    Objects.equals(getValue(uuid, perkSlot), ClassInstances.streaker.getRefID())) {
                Sounds.NO.play(player);
            } else if (level >= 30 || theWay) {
                Sounds.GAMBLE_YES.play(player);
                setValue(uuid, ClassInstances.streaker.getRefID(), perkSlot);
            } else {
                Sounds.NO.play(player);
            }

            player.openInventory(PermanentUpgrades.getPermanentUpgrades(player));
        } else if (event.getCurrentItem().getType().equals(ClassInstances.gladiator.getMaterial())) {
            

            if (hasValue(uuid, perkSlot) &&
                    Objects.equals(getValue(uuid, perkSlot), ClassInstances.gladiator.getRefID())) {
                Sounds.NO.play(player);
            } else if (level >= 5 || theWay) {
                Sounds.GAMBLE_YES.play(player);
                setValue(uuid, ClassInstances.gladiator.getRefID(), perkSlot);
            } else {
                Sounds.NO.play(player);
            }

            player.openInventory(PermanentUpgrades.getPermanentUpgrades(player));
        } else if (event.getCurrentItem().getType().equals(ClassInstances.firstStrike.getMaterial())) {


            if (hasValue(uuid, perkSlot) &&
                    Objects.equals(getValue(uuid, perkSlot), ClassInstances.firstStrike.getRefID())) {
                Sounds.NO.play(player);
            } else if (level >= 60 || theWay) {
                Sounds.GAMBLE_YES.play(player);
                setValue(uuid, ClassInstances.firstStrike.getRefID(), perkSlot);
            } else {
                Sounds.NO.play(player);
            }

            player.openInventory(PermanentUpgrades.getPermanentUpgrades(player));
        } else if (event.getCurrentItem().getType().equals(ClassInstances.soup.getMaterial())) {


            if(hasGoldenHead(uuid) || hasVampire(uuid)) {
                Sounds.NO.play(player);
            }else if (hasValue(uuid, perkSlot) &&
                    Objects.equals(getValue(uuid, perkSlot), ClassInstances.soup.getRefID())) {
                Sounds.NO.play(player);
            } else if (level >= 25 || theWay) {
                Sounds.GAMBLE_YES.play(player);
                setValue(uuid, ClassInstances.soup.getRefID(), perkSlot);
            } else {
                Sounds.NO.play(player);
            }

            player.openInventory(PermanentUpgrades.getPermanentUpgrades(player));
        } else if (event.getCurrentItem().getType().equals(ClassInstances.fishingRod.getMaterial())) {


            if (hasValue(uuid, perkSlot) &&
                    Objects.equals(getValue(uuid, perkSlot), ClassInstances.fishingRod.getRefID())) {
                Sounds.NO.play(player);
            } else if (level >= 5 || theWay) {
                Sounds.GAMBLE_YES.play(player);
                setValue(uuid, ClassInstances.fishingRod.getRefID(), perkSlot);
            } else {
                Sounds.NO.play(player);
            }

            player.openInventory(PermanentUpgrades.getPermanentUpgrades(player));
        } else if (event.getCurrentItem().getItemMeta() != null &&
                event.getCurrentItem().getItemMeta().getDisplayName().contains("Golden Heads")) {
            

            if(hasVampire(uuid) || hasSoup(uuid)) {
                Sounds.NO.play(player);
            }else if (hasValue(uuid, perkSlot) &&
                    Objects.equals(getValue(uuid, perkSlot), ClassInstances.goldenHeads.getRefID())) {
                Sounds.NO.play(player);
            } else if (level >= 1 || theWay) {
                Sounds.GAMBLE_YES.play(player);
                setValue(uuid, ClassInstances.goldenHeads.getRefID(), perkSlot);
            } else {
                Sounds.NO.play(player);
            }

            player.openInventory(PermanentUpgrades.getPermanentUpgrades(player));
        } else if (event.getCurrentItem().getType().equals(Material.ARROW)) {
            player.openInventory(PermanentUpgrades.getPermanentUpgrades(player));
            Sounds.BUTTON.play(player);
        } else if (event.getCurrentItem().getType().equals(Material.DIAMOND_BLOCK)) {
            setValue(uuid, "", perkSlot);
            Sounds.GAMBLE_YES.play(player);
            player.openInventory(PermanentUpgrades.getPermanentUpgrades(player));
        }
    }
}
