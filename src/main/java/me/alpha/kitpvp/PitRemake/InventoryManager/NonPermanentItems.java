package me.alpha.kitpvp.PitRemake.InventoryManager;

import de.tr7zw.nbtapi.NBTItem;
import me.alpha.kitpvp.PitRemake.ItemStacks.enchants;
import me.alpha.kitpvp.PitRemake.ItemStacks.itemManager;
import me.alpha.kitpvp.PitRemake.PitCommands.Crates.CrateItems;
import me.alpha.kitpvp.PitRemake.PitCommands.Stash.StashCore;
import me.alpha.kitpvp.utils.ColorUtil;
import me.alpha.kitpvp.utils.Sounds;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

import static me.alpha.kitpvp.PitRemake.InventoryManager.MysticLivesHandler.registerCommonItems;
import static me.alpha.kitpvp.PitRemake.RenownShop.RenownStorage.getUberDrop;
import static me.alpha.kitpvp.utils.advancedInventory.ItemMaker;

public class NonPermanentItems {

    public static void ClearAndCheck(Player player){
        try {
            List<ItemStack> hotbar = new ArrayList<>();

            hotbar.add(player.getInventory().getItem(0)); // 1
            hotbar.add(player.getInventory().getItem(1)); // 2
            hotbar.add(player.getInventory().getItem(2)); // 3
            hotbar.add(player.getInventory().getItem(3)); // 4
            hotbar.add(player.getInventory().getItem(4)); // 5
            hotbar.add(player.getInventory().getItem(5)); // 6
            hotbar.add(player.getInventory().getItem(6)); // 7
            hotbar.add(player.getInventory().getItem(7)); // 8
            hotbar.add(player.getInventory().getItem(8)); // 9

            boolean feather = false;

            for(ItemStack itemStack : hotbar){
                if(itemStack==null || itemStack.getType().equals(Material.AIR)) continue;

                NBTItem nbtItem = new NBTItem(itemStack);

                if(nbtItem.hasKey("feather") || itemStack.equals(itemManager.feather)) {
                    feather = true;
                    break;
                }
            }

            if(feather && player.getInventory().containsAtLeast(itemManager.feather, 1)){

                StashCore.safeRemove(player, itemManager.feather);
                Sounds.FUNKY_FEATHER.play(player);
                player.sendMessage(ColorUtil.colorCode("&3&lFUNKY FEATHER! &7Inventory protected."));
                return;
            }

                if(player.getInventory().getLeggings()!=null&&
            player.getInventory().getLeggings().getType().equals(Material.LEATHER_LEGGINGS)){
                player.getInventory().setLeggings(MysticLivesHandler.MysticLivesHandler(player, player.getInventory().getLeggings()));
            }

                registerCommonItems(player);

                try {
                    if (player.getInventory().contains(ItemMaker(Material.OBSIDIAN, "NULL", "NULL", 1, false))){
                        player.getInventory().remove(ItemMaker(Material.OBSIDIAN, "NULL", "NULL", 1, false));
                    }
                }catch (Exception e){

                }

                if(player.getInventory().contains(enchants.firstaidempty)){
                    player.getInventory().remove(enchants.firstaidempty);
                }

                if(player.getInventory().contains(enchants.firstaidfull)){
                    player.getInventory().remove(enchants.firstaidfull);
                }

                try {
                    if (player.getInventory().contains(ItemMaker(Material.DIAMOND_SWORD, "NULL", "NULL", 1, false))) {
                        player.getInventory().remove(ItemMaker(Material.DIAMOND_SWORD, "NULL", "NULL", 1, false));
                    }
                }catch (Exception e){

                }

                try {
                    if (player.getInventory().contains(ItemMaker(Material.DIAMOND_SPADE, ChatColor.AQUA + "Combat Spade", ChatColor.GRAY + "Deals " + ChatColor.BLUE +
                            "+1 damage per\n" + ChatColor.AQUA + "diamond piece " + ChatColor.GRAY + "on enemy." + "\n\n" +
                            ChatColor.BLUE + "+7 Attack Damage", 1, false))) {
                        player.getInventory().remove(ItemMaker(Material.DIAMOND_SPADE, ChatColor.AQUA + "Combat Spade", ChatColor.GRAY + "Deals " + ChatColor.BLUE +
                                "+1 damage per\n" + ChatColor.AQUA + "diamond piece " + ChatColor.GRAY + "on enemy." + "\n\n" +
                                ChatColor.BLUE + "+7 Attack Damage", 1, false));
                    }
                }catch (Exception e){

                }

                try {
                    if (player.getInventory().getLeggings().equals(itemManager.DiamondLeggings)) {
                        player.getInventory().setLeggings(null);
                    }
                }catch (Exception e){

                }

            try {
                if (player.getInventory().getChestplate().equals(itemManager.arch)) {
                    player.getInventory().setChestplate(null);
                }
            }catch (Exception e){

            }

            try {
                if (player.getInventory().getHelmet().equals(itemManager.goldHelm)) {
                    player.getInventory().setHelmet(null);
                }
            }catch (Exception e){

            }

                try {
                    if ((player.getInventory().getChestplate().equals(itemManager.DiamondChestplate))) {
                        player.getInventory().setChestplate(null);
                    }
                }catch (Exception e){

                }


                try {
                    if ((player.getInventory().getBoots().equals(itemManager.DiamondBoots))) {
                        player.getInventory().setBoots(null);
                    }
                }catch (Exception e){

                }

                try {
                    if ((player.getInventory().getHelmet().equals(itemManager.DiamondHelmet))) {
                        player.getInventory().setHelmet(null);
                    }
                }catch (Exception e){

                }


                try {
                    if (player.getInventory().contains(itemManager.DiamondBoots)) {
                        player.getInventory().remove(itemManager.DiamondBoots);
                    }
                }catch (Exception e){

                }

                try {
                    if (player.getInventory().contains(itemManager.DiamondChestplate)) {
                        player.getInventory().remove(itemManager.DiamondChestplate);
                    }
                }catch (Exception e){

                }

            try {
                if (player.getInventory().contains(itemManager.arch)) {
                    player.getInventory().remove(itemManager.arch);
                }
            }catch (Exception e){

            }

            try {
                if (player.getInventory().contains(itemManager.goldHelm)) {
                    player.getInventory().remove(itemManager.goldHelm);
                }
            }catch (Exception e){

            }

                try {
                    if (player.getInventory().contains(itemManager.DiamondSword)) {
                        player.getInventory().remove(itemManager.DiamondSword);
                    }
                }catch (Exception e){

                }

                try {
                    if (player.getInventory().contains(itemManager.DiamondBoots)) {
                        player.getInventory().remove(itemManager.DiamondBoots);
                    }
                }catch (Exception e){

                }

                try {
                    if (player.getInventory().contains(itemManager.DiamondLeggings)) {
                        player.getInventory().remove(itemManager.DiamondLeggings);
                    }
                }catch (Exception e){

                }

                try {
                    if (player.getInventory().contains(itemManager.DiamondHelmet)) {
                        player.getInventory().remove(itemManager.DiamondHelmet);
                    }
                }catch (Exception e){

                }

                if(player.getInventory().getHelmet()!=null&&
                        player.getInventory().getHelmet().equals(CrateItems.getDiamondHelmet())){
                    player.getInventory().setHelmet(null);
                }if(player.getInventory().containsAtLeast(CrateItems.getDiamondHelmet(), 1)){
                    player.getInventory().remove(CrateItems.getDiamondHelmet());
                }

            if(player.getInventory().getChestplate()!=null&&
                    player.getInventory().getChestplate().equals(CrateItems.getDiamondChestplate())){
                player.getInventory().setChestplate(null);
            }
            if(player.getInventory().containsAtLeast(CrateItems.getDiamondChestplate(), 1)){
                player.getInventory().remove(CrateItems.getDiamondChestplate());
            }

            if(player.getInventory().getLeggings()!=null&&
                    player.getInventory().getLeggings().equals(CrateItems.getDiamondLeggings())){
                player.getInventory().setLeggings(null);
            }
            if(player.getInventory().containsAtLeast(CrateItems.getDiamondLeggings(), 1)){
                player.getInventory().remove(CrateItems.getDiamondLeggings());
            }

            if(player.getInventory().getBoots()!=null&&
                    player.getInventory().getBoots().equals(CrateItems.getDiamondBoots())){
                player.getInventory().setBoots(null);
            }
            if(player.getInventory().containsAtLeast(CrateItems.getDiamondBoots(), 1)){
                player.getInventory().remove(CrateItems.getDiamondBoots());
            }

                try {
                    if (player.getInventory().contains(ItemMaker(Material.DIAMOND_BOOTS, "NULL", "NULL", 1, false))) {
                        player.getInventory().remove(ItemMaker(Material.DIAMOND_BOOTS, "NULL", "NULL", 1, false));
                    }

                }catch (Exception e){

                }

                try {
                    if (player.getInventory().contains(ItemMaker(Material.DIAMOND_LEGGINGS, "NULL", "NULL", 1, false))) {
                        player.getInventory().remove(ItemMaker(Material.DIAMOND_LEGGINGS, "NULL", "NULL", 1, false));
                    }
                }catch (Exception e){

                }

                try {
                    if (player.getInventory().contains(ItemMaker(Material.DIAMOND_CHESTPLATE, "NULL", "NULL", 1, false))) {
                        player.getInventory().remove(ItemMaker(Material.DIAMOND_CHESTPLATE, "NULL", "NULL", 1, false));
                    }
                }catch (Exception e){

                }

                try {
                    if (player.getInventory().getLeggings().equals(ItemMaker(Material.DIAMOND_LEGGINGS, "NULL", "NULL", 1, false))) {
                        player.getInventory().setLeggings(null);
                    }
                }catch (Exception e){

                }

                try {
                    if (player.getInventory().getChestplate().equals(ItemMaker(Material.DIAMOND_CHESTPLATE, "NULL", "NULL", 1, false))) {
                        player.getInventory().setChestplate(null);
                    }
                }catch (Exception e){

                }

                try {
                    if (player.getInventory().getBoots().equals(ItemMaker(Material.DIAMOND_BOOTS, "NULL", "NULL", 1, false))) {
                        player.getInventory().setBoots(null);
                    }

                }catch (Exception e){

                }

        }catch(Exception e){
                return;
            }


        }

    public static void ClearRegular(Player player){
        try {
            List<ItemStack> hotbar = new ArrayList<>();

            hotbar.add(player.getInventory().getItem(0)); // 1
            hotbar.add(player.getInventory().getItem(1)); // 2
            hotbar.add(player.getInventory().getItem(2)); // 3
            hotbar.add(player.getInventory().getItem(3)); // 4
            hotbar.add(player.getInventory().getItem(4)); // 5
            hotbar.add(player.getInventory().getItem(5)); // 6
            hotbar.add(player.getInventory().getItem(6)); // 7
            hotbar.add(player.getInventory().getItem(7)); // 8
            hotbar.add(player.getInventory().getItem(8)); // 9

            for (ItemStack item : hotbar){
                if(item != null &&
                        item.getItemMeta() != null
                        && item.getItemMeta().getLore() != null &&
                        item.getItemMeta().getLore().equals(itemManager.feather.getItemMeta().getLore())){
                    StashCore.safeRemove(player, itemManager.feather);
                    Sounds.FUNKY_FEATHER.play(player);
                    player.sendMessage(ChatColor.AQUA + "Your funky feather just saved your items!");
                    return;
                }
            }

            try {
                if (player.getInventory().contains(ItemMaker(Material.OBSIDIAN, "NULL", "NULL", 1, false))){
                    player.getInventory().remove(ItemMaker(Material.OBSIDIAN, "NULL", "NULL", 1, false));
                }
            }catch (Exception e){

            }

            if(player.getInventory().contains(enchants.firstaidempty)){
                player.getInventory().remove(enchants.firstaidempty);
            }

            if(player.getInventory().contains(enchants.firstaidfull)){
                player.getInventory().remove(enchants.firstaidfull);
            }

            try {
                if (player.getInventory().contains(ItemMaker(Material.DIAMOND_SWORD, "NULL", "NULL", 1, false))) {
                    player.getInventory().remove(ItemMaker(Material.DIAMOND_SWORD, "NULL", "NULL", 1, false));
                }
            }catch (Exception e){

            }

            try {
                if (player.getInventory().contains(ItemMaker(Material.DIAMOND_SPADE, ChatColor.AQUA + "Combat Spade", ChatColor.GRAY + "Deals " + ChatColor.BLUE +
                        "+1 damage per\n" + ChatColor.AQUA + "diamond piece " + ChatColor.GRAY + "on enemy." + "\n\n" +
                        ChatColor.BLUE + "+7 Attack Damage", 1, false))) {
                    player.getInventory().remove(ItemMaker(Material.DIAMOND_SPADE, ChatColor.AQUA + "Combat Spade", ChatColor.GRAY + "Deals " + ChatColor.BLUE +
                            "+1 damage per\n" + ChatColor.AQUA + "diamond piece " + ChatColor.GRAY + "on enemy." + "\n\n" +
                            ChatColor.BLUE + "+7 Attack Damage", 1, false));
                }
            }catch (Exception e){

            }

            try {
                if (player.getInventory().getLeggings().equals(itemManager.DiamondLeggings)) {
                    player.getInventory().setLeggings(null);
                }
            }catch (Exception e){

            }

            try {
                if ((player.getInventory().getChestplate().equals(itemManager.DiamondChestplate))) {
                    player.getInventory().setChestplate(null);
                }
            }catch (Exception e){

            }


            try {
                if ((player.getInventory().getBoots().equals(itemManager.DiamondBoots))) {
                    player.getInventory().setBoots(null);
                }
            }catch (Exception e){

            }

            try {
                if ((player.getInventory().getHelmet().equals(itemManager.DiamondHelmet))) {
                    player.getInventory().setHelmet(null);
                }
            }catch (Exception e){

            }


            try {
                if (player.getInventory().contains(itemManager.DiamondBoots)) {
                    player.getInventory().remove(itemManager.DiamondBoots);
                }
            }catch (Exception e){

            }

            try {
                if (player.getInventory().contains(itemManager.DiamondChestplate)) {
                    player.getInventory().remove(itemManager.DiamondChestplate);
                }
            }catch (Exception e){

            }

            try {
                if (player.getInventory().contains(itemManager.DiamondSword)) {
                    player.getInventory().remove(itemManager.DiamondSword);
                }
            }catch (Exception e){

            }

            try {
                if (player.getInventory().contains(itemManager.DiamondBoots)) {
                    player.getInventory().remove(itemManager.DiamondBoots);
                }
            }catch (Exception e){

            }

            try {
                if (player.getInventory().contains(itemManager.DiamondLeggings)) {
                    player.getInventory().remove(itemManager.DiamondLeggings);
                }
            }catch (Exception e){

            }

            try {
                if (player.getInventory().contains(itemManager.DiamondHelmet)) {
                    player.getInventory().remove(itemManager.DiamondHelmet);
                }
            }catch (Exception e){

            }

            try {
                if (player.getInventory().contains(ItemMaker(Material.DIAMOND_BOOTS, "NULL", "NULL", 1, false))) {
                    player.getInventory().remove(ItemMaker(Material.DIAMOND_BOOTS, "NULL", "NULL", 1, false));
                }

            }catch (Exception e){

            }

            try {
                if (player.getInventory().contains(ItemMaker(Material.DIAMOND_LEGGINGS, "NULL", "NULL", 1, false))) {
                    player.getInventory().remove(ItemMaker(Material.DIAMOND_LEGGINGS, "NULL", "NULL", 1, false));
                }
            }catch (Exception e){

            }

            try {
                if (player.getInventory().contains(ItemMaker(Material.DIAMOND_CHESTPLATE, "NULL", "NULL", 1, false))) {
                    player.getInventory().remove(ItemMaker(Material.DIAMOND_CHESTPLATE, "NULL", "NULL", 1, false));
                }
            }catch (Exception e){

            }

            try {
                if (player.getInventory().getLeggings().equals(ItemMaker(Material.DIAMOND_LEGGINGS, "NULL", "NULL", 1, false))) {
                    player.getInventory().setLeggings(null);
                }
            }catch (Exception e){

            }

            try {
                if (player.getInventory().getChestplate().equals(ItemMaker(Material.DIAMOND_CHESTPLATE, "NULL", "NULL", 1, false))) {
                    player.getInventory().setChestplate(null);
                }
            }catch (Exception e){

            }

            try {
                if (player.getInventory().getBoots().equals(ItemMaker(Material.DIAMOND_BOOTS, "NULL", "NULL", 1, false))) {
                    player.getInventory().setBoots(null);
                }

            }catch (Exception e){

            }

        }catch(Exception e){
            return;
        }


    }
    }
