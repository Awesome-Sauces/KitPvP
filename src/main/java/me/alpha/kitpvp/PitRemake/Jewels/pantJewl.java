package me.alpha.kitpvp.PitRemake.Jewels;

import de.tr7zw.nbtapi.NBTCompound;
import de.tr7zw.nbtapi.NBTItem;
import me.alpha.kitpvp.ChatManager.RankColor;
import me.alpha.kitpvp.Data.ClassInstances;
import me.alpha.kitpvp.PitRemake.MysticWell.enchanters.FreshPants;
import me.alpha.kitpvp.PitRemake.MysticWell.enchanters.MysticBow;
import me.alpha.kitpvp.PitRemake.MysticWell.enchanters.MysticSword;
import me.alpha.kitpvp.utils.Sounds;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.LeatherArmorMeta;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import static me.alpha.kitpvp.PitRemake.MysticWell.loreChecker.CheckEnchantOnPant;
import static me.alpha.kitpvp.utils.ColorUtil.colorCode;
import static me.alpha.kitpvp.utils.FancyText.compileListToString;
import static me.alpha.kitpvp.utils.FancyText.hoverText;

public class pantJewl {

    public static List<String> getLore(ItemStack item){
        NBTItem nbtItem = new NBTItem(item);

        nbtItem.addCompound("enchants");

        NBTCompound nbtCompound = nbtItem.getOrCreateCompound("enchants");

        List<String> lore = new ArrayList<>();
        lore.add(ChatColor.translateAlternateColorCodes('&', "&7Lives: &a5&7/5"));
        lore.add("   ");

        for (String key : nbtItem.getCompound("enchants").getKeys()){
            int level = nbtCompound.getInteger(key);

            if(item.getType().equals(Material.LEATHER_LEGGINGS)){
                lore.addAll(Arrays.asList(FreshPants.enchantTier(key, level).split("\n")));
            }else if(item.getType().equals(Material.GOLD_SWORD)){
                lore.addAll(Arrays.asList(MysticSword.enchantTier(key, level).split("\n")));
            }else if(item.getType().equals(Material.BOW)){
                lore.addAll(Arrays.asList(MysticBow.enchantTier(key, level).split("\n")));
            }
        }


        if(item.getType().equals(Material.LEATHER_LEGGINGS)){
            lore.add(ChatColor.RED + "As strong as iron");
        }else if(item.getType().equals(Material.GOLD_SWORD)){
            lore.add(ChatColor.BLUE + "+6.5 Attack Damage");
        }else if(item.getType().equals(Material.BOW)){
            lore.add(ChatColor.BLUE + "+6.5 Attack Damage");
        }

        return lore;
    }
    public static ItemStack generateJewlPants(Player player){

        ItemStack item = new ItemStack(Material.LEATHER_LEGGINGS, 1);

        NBTItem nbtItem = new NBTItem(item);

        NBTCompound nbtCompound = nbtItem.getOrCreateCompound("enchants");

        String enchant = FreshPants.getEnchant(new ArrayList<>());

        nbtCompound.setInteger(enchant, 3);

        nbtItem.mergeCompound(nbtCompound);
        item = nbtItem.getItem();

        LeatherArmorMeta meta = (LeatherArmorMeta) item.getItemMeta() ;
        meta.setColor(Color.RED);
        meta.setDisplayName(ChatColor.translateAlternateColorCodes('&',"&cTier I Red Pants"));

        meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
        meta.spigot().setUnbreakable(true);

        meta.setLore(getLore(item));

        item.setItemMeta(meta);


        hoverText(ChatColor.translateAlternateColorCodes('&', "&3&lJEWEL! " + RankColor.getNameColor(player) + player.getDisplayName() + ChatColor.GRAY + " obtained: " + ChatColor.RED + "Jewel Enchant!"), compileListToString(item.getItemMeta().getLore()));

        return item;
    }
}
