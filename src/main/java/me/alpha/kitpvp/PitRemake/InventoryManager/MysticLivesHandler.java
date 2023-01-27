package me.alpha.kitpvp.PitRemake.InventoryManager;


import de.tr7zw.nbtapi.NBTItem;
import me.alpha.kitpvp.PitRemake.PitCommands.Stash.StashCore;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

import static me.alpha.kitpvp.utils.ColorUtil.colorCode;


public class MysticLivesHandler {
    public static ItemStack MysticLivesHandler(Player player, ItemStack itemStack){
        if(itemStack==null||itemStack.getType().equals(Material.AIR)) return null;

        List<String> lore = new ArrayList<>();

        if(itemStack.getItemMeta()!=null&&itemStack.getItemMeta().getLore()!=null) lore = itemStack.getItemMeta().getLore();

        String lives = lore.get(0);

        int currentLives = 0;
        int maxLives = 0;
        String currentLivesColor = "&a";

        NBTItem nbtItem = new NBTItem(itemStack);

        if(!nbtItem.hasKey("maxLives")) return null;

        currentLives = nbtItem.getInteger("lives");
        maxLives = nbtItem.getInteger("maxLives");

        currentLives-=1;

        if(currentLives<=(maxLives/3)) currentLivesColor = "&c";

        String livesTemplate = colorCode("&7Lives: " + currentLivesColor + currentLives+"&7/"+maxLives);

        lore.set(0,livesTemplate);

        nbtItem.setInteger("lives", currentLives);

        ItemMeta meta = nbtItem.getItem().getItemMeta();

        meta.setLore(lore);

        nbtItem.getItem().setItemMeta(meta);

        if(currentLives<=0) return null;
        return nbtItem.getItem();

    }


    public static ItemStack MysticRepairs(ItemStack itemStack){
        if(itemStack==null||itemStack.getType().equals(Material.AIR)) return itemStack;

        List<String> lore = new ArrayList<>();

        if(itemStack.getItemMeta()!=null&&itemStack.getItemMeta().getLore()!=null) lore = itemStack.getItemMeta().getLore();

        String lives = lore.get(0);

        int currentLives = 0;
        int maxLives = 0;
        String currentLivesColor = "&a";

        NBTItem nbtItem = new NBTItem(itemStack);

        if(!nbtItem.hasKey("maxLives")) return itemStack;

        currentLives = nbtItem.getInteger("lives");
        maxLives = nbtItem.getInteger("maxLives");

        currentLives++;

        currentLives = Math.min(maxLives, currentLives);

        nbtItem.setInteger("lives", currentLives);

        if(currentLives<=(maxLives/3)) currentLivesColor = "&c";

        String livesTemplate = colorCode("&7Lives: " + currentLivesColor + currentLives+"&7/"+maxLives);

        lore.set(0,livesTemplate);

        ItemMeta meta = nbtItem.getItem().getItemMeta();

        meta.setLore(lore);

        nbtItem.getItem().setItemMeta(meta);

        return nbtItem.getItem();
    }


    public static void registerCommonItems(Player p){
        try{
            ItemStack i = p.getInventory().getHelmet();

            if(i.getType().equals(Material.GOLD_HELMET)){
                if(i.getItemMeta()!=null&&
                        i.getItemMeta().getLore()!=null&&
                        i.getItemMeta().getLore().get(0).contains("Lives")){
                    p.getInventory().setHelmet(MysticLivesHandler(p, i));
                }
            }
        }catch (Exception e){

        }

        try{
            ItemStack i = p.getInventory().getChestplate();

            if(i.getType().equals(Material.DIAMOND_CHESTPLATE)){
                if(i.getItemMeta()!=null&&
                        i.getItemMeta().getLore()!=null&&
                        i.getItemMeta().getLore().get(0).contains("Lives")){
                    p.getInventory().setChestplate(MysticLivesHandler(p, i));
                }
            }
        }catch (Exception e){

        }

        if(p.getInventory().getBoots()!=null &&
        p.getInventory().getBoots().getItemMeta() !=null&&
        p.getInventory().getBoots().getItemMeta().getLore()!=null){
            ItemStack i = p.getInventory().getBoots();

            if(i.getType().equals(Material.LEATHER_BOOTS)){
                if(i.getItemMeta()!=null&&
                        i.getItemMeta().getLore()!=null&&
                        i.getItemMeta().getLore().get(0).contains("Lives")){
                    p.getInventory().setBoots(MysticLivesHandler(p, i));
                }
            }
        }


        for(int iter = 0; iter<p.getInventory().getSize(); iter++){
            ItemStack i=p.getInventory().getItem(iter);

            if(i!=null &&
                    !i.getType().equals(Material.AIR)){
                NBTItem nbtItem = new NBTItem(i);

                if(nbtItem.hasKey("maxLives")){
                    p.getInventory().setItem(iter, MysticLivesHandler(p, i));
                }
            }
        }


    }
}
