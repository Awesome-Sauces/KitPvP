package me.alpha.kitpvp.PitRemake.InventoryManager;


import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

import static me.alpha.kitpvp.utils.ColorUtil.colorCode;


public class MysticLivesHandler {
    public static void MysticLivesHandler(Player player, ItemStack item){
        String lives = item.getItemMeta().getLore().get(0);

        if(ChatColor.stripColor(lives).contains("Lives: 1/5")){
            player.getInventory().removeItem(item);
        }

        try{
            if(ChatColor.stripColor(player.getInventory().getLeggings().getItemMeta().getLore().get(0)).contains("Lives: 1/5")){
                player.getInventory().setLeggings(null);
            }

        } catch (Exception e) {

        }

        try{
            if(ChatColor.stripColor(player.getInventory().getChestplate().getItemMeta().getLore().get(0)).contains("Lives: 1/5")){
                player.getInventory().setChestplate(null);
            }

        } catch (Exception e) {

        }

        try{
            if(ChatColor.stripColor(player.getInventory().getHelmet().getItemMeta().getLore().get(0)).contains("Lives: 1/5")){
                player.getInventory().setHelmet(null);
            }

        } catch (Exception e) {

        }

        if(ChatColor.stripColor(lives).contains("Lives: 0/5")){
            player.getInventory().removeItem(item);
        }

        try{
            if(ChatColor.stripColor(player.getInventory().getLeggings().getItemMeta().getLore().get(0)).contains("Lives: 0/5")){
                player.getInventory().setLeggings(null);
            }

        } catch (Exception e) {

        }

        ItemMeta meta = item.getItemMeta();
        meta.setLore(MysticLivesCounter(meta.getLore()));
        item.setItemMeta(meta);

    }

    public static List<String> MysticLivesCounter(List<String> lore){
        String lives = lore.get(0);

        if(ChatColor.stripColor(lives).contains("Lives: 5/5")){
            lore.set(0, lore.get(0).replaceAll(colorCode("&7Lives: &a5&7/5"), colorCode("&7Lives: &a4&7/5")));
            return lore;
        }else if(ChatColor.stripColor(lives).contains("Lives: 4/5")){
            lore.set(0, lore.get(0).replaceAll(colorCode("&7Lives: &a4&7/5"), colorCode("&7Lives: &a3&7/5")));
            return lore;
        }else if(ChatColor.stripColor(lives).contains("Lives: 3/5")){
            lore.set(0, lore.get(0).replaceAll(colorCode("&7Lives: &a3&7/5"), colorCode("&7Lives: &a2&7/5")));
            return lore;
        }else if(ChatColor.stripColor(lives).contains("Lives: 2/5")){
            lore.set(0, lore.get(0).replaceAll(colorCode("&7Lives: &a2&7/5"), colorCode("&7Lives: &a1&7/5")));
            return lore;
        }else if(ChatColor.stripColor(lives).contains("Lives: 1/5")){
            lore.set(0, lore.get(0).replaceAll(colorCode("&7Lives: &a1&7/5"), colorCode("&7Lives: &a0&7/5")));
            return lore;
        }else{
            return lore;
        }
    }

    public static List<String> MysticRepairs(List<String> lore){
        String lives = lore.get(0);

        if(ChatColor.stripColor(lives).contains("Lives: 5/5")){
            lore.set(0, lore.get(0).replaceAll(colorCode("&7Lives: &a5&7/5"), colorCode("&7Lives: &a6&7/5")));
            return lore;
        }else if(ChatColor.stripColor(lives).contains("Lives: 4/5")){
            lore.set(0, lore.get(0).replaceAll(colorCode("&7Lives: &a4&7/5"), colorCode("&7Lives: &a5&7/5")));
            return lore;
        }else if(ChatColor.stripColor(lives).contains("Lives: 3/5")){
            lore.set(0, lore.get(0).replaceAll(colorCode("&7Lives: &a3&7/5"), colorCode("&7Lives: &a4&7/5")));
            return lore;
        }else if(ChatColor.stripColor(lives).contains("Lives: 2/5")){
            lore.set(0, lore.get(0).replaceAll(colorCode("&7Lives: &a2&7/5"), colorCode("&7Lives: &a3&7/5")));
            return lore;
        }else if(ChatColor.stripColor(lives).contains("Lives: 1/5")){
            lore.set(0, lore.get(0).replaceAll(colorCode("&7Lives: &a1&7/5"), colorCode("&7Lives: &a2&7/5")));
            return lore;
        }else{
            return lore;
        }
    }


    public static void registerCommonItems(Player p){
        try{
            ItemStack i = p.getInventory().getHelmet();

            if(i.getType().equals(Material.GOLD_HELMET)){
                if(i.getItemMeta()!=null&&
                        i.getItemMeta().getLore()!=null&&
                        i.getItemMeta().getLore().get(0).contains("Lives")){
                    MysticLivesHandler(p, i);
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
                    MysticLivesHandler(p, i);
                }
            }
        }catch (Exception e){

        }

        try{
            ItemStack i = p.getInventory().getBoots();

            if(i.getType().equals(Material.LEATHER_BOOTS)){
                if(i.getItemMeta()!=null&&
                        i.getItemMeta().getLore()!=null&&
                        i.getItemMeta().getLore().get(0).contains("Lives")){
                    MysticLivesHandler(p, i);
                }
            }
        }catch (Exception e){

        }

        for(ItemStack i : p.getInventory().getContents()){
            try{
                if(i.getType().equals(Material.GOLD_SWORD)){
                    if(i.getItemMeta()!=null&&
                    i.getItemMeta().getLore()!=null&&
                    i.getItemMeta().getLore().get(0).contains("Lives")){
                        MysticLivesHandler(p, i);
                    }
                }
            }catch (Exception e){

            }

            try{
                if(i.getType().equals(Material.GOLD_HELMET)){
                    if(i.getItemMeta()!=null&&
                            i.getItemMeta().getLore()!=null&&
                            i.getItemMeta().getLore().get(0).contains("Lives")){
                        MysticLivesHandler(p, i);
                    }
                }
            }catch (Exception e){

            }

            try{
                if(i.getType().equals(Material.DIAMOND_CHESTPLATE)){
                    if(i.getItemMeta()!=null&&
                            i.getItemMeta().getLore()!=null&&
                            i.getItemMeta().getLore().get(0).contains("Lives")){
                        MysticLivesHandler(p, i);
                    }
                }
            }catch (Exception e){

            }

            try{
                if(i.getType().equals(Material.LEATHER_LEGGINGS)){
                    if(i.getItemMeta()!=null&&
                            i.getItemMeta().getLore()!=null&&
                            i.getItemMeta().getLore().get(0).contains("Lives")){
                        MysticLivesHandler(p, i);
                    }
                }
            } catch (Exception e) {

            }

            try{
                if(i.getType().equals(Material.BOW)){
                    if(i.getItemMeta()!=null&&
                            i.getItemMeta().getLore()!=null&&
                            i.getItemMeta().getLore().get(0).contains("Lives")){
                        MysticLivesHandler(p, i);
                    }
                }
            } catch (Exception e) {

            }

        }

    }
}
