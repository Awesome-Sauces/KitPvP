package me.alpha.kitpvp.PitRemake.InventoryRefresher;

import de.tr7zw.nbtapi.NBTCompound;
import de.tr7zw.nbtapi.NBTItem;
import me.alpha.kitpvp.KitPvP;
import me.alpha.kitpvp.PitRemake.ItemStacks.enchants;
import me.alpha.kitpvp.PitRemake.ItemStacks.itemManager;
import me.alpha.kitpvp.PitRemake.MysticWell.enchanters.FreshPants;
import me.alpha.kitpvp.PitRemake.MysticWell.enchanters.MysticBow;
import me.alpha.kitpvp.PitRemake.MysticWell.enchanters.MysticSword;
import me.alpha.kitpvp.PitRemake.MysticWell.loreChecker;
import me.alpha.kitpvp.utils.ColorUtil;
import me.alpha.kitpvp.utils.Sounds;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static me.alpha.kitpvp.PitRemake.RenownShop.RenownStorage.getUberDrop;
import static me.alpha.kitpvp.utils.ColorUtil.colorCode;

public class RefreshCore {
    public static void refreshInventory(Player player){
        int refreshCount = 0;

        Inventory inventory = player.getInventory();

        if(player.getInventory().getBoots()!=null){
            if(player.getInventory().getBoots().getType().equals(Material.LEATHER_BOOTS)&&
            player.getInventory().getBoots().getItemMeta().getDisplayName().contains(ColorUtil.colorCode("&cArmageddon Boots"))){
                player.getInventory().setBoots(enchants.arma);
            }
        }

        if(player.getInventory().getLeggings()!=null){
            ItemStack item = player.getInventory().getLeggings();
            if(item.getType().equals(Material.LEATHER_LEGGINGS)&&
                    item.getItemMeta()!=null){
                NBTItem nbtItem = new NBTItem(item);

                nbtItem.addCompound("enchants");

                NBTCompound nbtCompound = nbtItem.getOrCreateCompound("enchants");

                List<String> enchants = new ArrayList<>();

                enchants= loreChecker.CheckEnchantOnPant(item.getItemMeta().getLore());

                if(!enchants.isEmpty()){
                    for(String enchant : enchants){
                        int tier = enchant.length()-enchant.replaceAll("I", "").length();

                        nbtCompound.setInteger(FreshPants.convertEnchant(enchant.replaceAll("I", "")), tier);
                    }

                    nbtItem.mergeCompound(nbtCompound);

                    item=nbtItem.getItem();

                    player.getInventory().setLeggings(item);
                    refreshCount++;
                }

            }
        }

        for(int i = 0; i < inventory.getSize(); i++){
            if(inventory.getItem(i)==null||
            inventory.getItem(i).getType().equals(Material.AIR)) continue;

            ItemStack item = inventory.getItem(i);

            if(item.getType().equals(Material.LEATHER_BOOTS)&&
                    item.getItemMeta().getDisplayName().contains(ColorUtil.colorCode("&cArmageddon Boots"))){
                inventory.setItem(i, enchants.arma);
                refreshCount++;
                continue;
            }

            if(item.getType().equals(Material.FEATHER)){

                    ItemStack stack = itemManager.feather;

                    stack.setAmount(item.getAmount());

                    inventory.setItem(i, stack);
                    refreshCount++;

            }

            if(item.getType().equals(Material.COAL)){
                if(item.getItemMeta()!=null&&
                        !item.getItemMeta().getDisplayName().equals(enchants.vile.getItemMeta().getDisplayName())){
                    inventory.setItem(i, new ItemStack(Material.AIR));
                }else{
                    ItemStack stack = enchants.vile;

                    stack.setAmount(item.getAmount());

                    inventory.setItem(i, stack);
                    refreshCount++;
                }
            }

            if(item.getType().equals(Material.EMERALD)){
                if(item.getItemMeta()!=null&&
                        !item.getItemMeta().getDisplayName().equals(enchants.gem.getItemMeta().getDisplayName())){
                    inventory.setItem(i, new ItemStack(Material.AIR));
                    player.sendMessage(ColorUtil.colorCode("&5&lDUPE DETECTION! &7A duped item was detected in your inventory."));
                    Sounds.MEGA_RNGESUS.play(player);
                }else {
                    ItemStack stack = enchants.gem;

                    stack.setAmount(item.getAmount());

                    inventory.setItem(i, stack);
                    refreshCount++;
                }
            }

            if(item.getType().equals(Material.ENDER_CHEST)){
                if(item.getItemMeta()!=null&&
                        !item.getItemMeta().getDisplayName().equals(getUberDrop().getItemMeta().getDisplayName())){
                    inventory.setItem(i, new ItemStack(Material.AIR));
                    player.sendMessage(ColorUtil.colorCode("&5&lDUPE DETECTION! &7A duped item was detected in your inventory."));
                    Sounds.MEGA_RNGESUS.play(player);
                }else {
                    ItemStack stack = getUberDrop();

                    stack.setAmount(item.getAmount());

                    inventory.setItem(i, stack);
                    refreshCount++;
                }

            }

            if(item.getType().equals(Material.GOLD_SWORD)&&
            item.getItemMeta()!=null){
                NBTItem nbtItem = new NBTItem(item);

                nbtItem.addCompound("enchants");

                NBTCompound nbtCompound = nbtItem.getOrCreateCompound("enchants");

                List<String> enchants = new ArrayList<>();

                enchants= loreChecker.CheckEnchantOnSword(item.getItemMeta().getLore());

                if(enchants.isEmpty()) continue;

                for(String enchant : enchants){
                    int tier = enchant.length()-enchant.replaceAll("I", "").length();

                    nbtCompound.setInteger(MysticSword.convertEnchant(enchant.replaceAll("I", "")), tier);
                }

                nbtItem.mergeCompound(nbtCompound);

                item=nbtItem.getItem();

                inventory.setItem(i, item);
                refreshCount++;

            }else if(item.getType().equals(Material.LEATHER_LEGGINGS)&&
                    item.getItemMeta()!=null){
                NBTItem nbtItem = new NBTItem(item);

                nbtItem.addCompound("enchants");

                NBTCompound nbtCompound = nbtItem.getOrCreateCompound("enchants");

                List<String> enchants = new ArrayList<>();

                enchants= loreChecker.CheckEnchantOnPant(item.getItemMeta().getLore());

                if(enchants.isEmpty()) continue;

                for(String enchant : enchants){
                    int tier = enchant.length()-enchant.replaceAll("I", "").length();

                    nbtCompound.setInteger(FreshPants.convertEnchant(enchant.replaceAll("I", "")), tier);
                }

                nbtItem.mergeCompound(nbtCompound);

                item=nbtItem.getItem();

                inventory.setItem(i, item);
                refreshCount++;

            }else if(item.getType().equals(Material.BOW)&&
                    item.getItemMeta()!=null&&
                    item.getItemMeta().getDisplayName()!=null &&
                    item.getItemMeta().getDisplayName().contains("Super")){
                NBTItem nbtItem = new NBTItem(item);

                NBTCompound nbtCompound = nbtItem.getOrCreateCompound("pitdata");

                if(!nbtCompound.hasKey("real")) inventory.setItem(i, enchants.fresh_bow);
            }else if(item.getType().equals(Material.BOW)&&
                    item.getItemMeta()!=null){

                NBTItem nbtItem = new NBTItem(item);

                    NBTCompound nbtCompound = nbtItem.getOrCreateCompound("enchants");
                    NBTCompound compound = nbtItem.getOrCreateCompound("pitdata");

                    if(!compound.hasKey("real")) continue;


                    ItemMeta itemMeta = nbtItem.getItem().getItemMeta();
                    List<String> lore = new ArrayList<>();

                    lore.add(ChatColor.translateAlternateColorCodes('&', "&7Lives: &a5&7/5"));
                    lore.add("   ");

                    for (String key : nbtCompound.getKeys()){
                        int level = nbtCompound.getInteger(key);

                        lore.addAll(Arrays.asList(MysticBow.enchantTier(key, level).split("\n")));

                    }


                    itemMeta.setLore(lore);

                    nbtItem.getItem().setItemMeta(itemMeta);

                    inventory.setItem(i, nbtItem.getItem());
                    refreshCount++;
                }

            }



        player.getInventory().setContents(inventory.getContents());

        player.sendMessage(ColorUtil.colorCode("&e&lREFRESH! &7Successfully refreshed &a" + refreshCount + " &7item(s) in your inventory!"));
        Sounds.SUCCESS.play(player);
    }
}
