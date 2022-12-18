package me.alpha.kitpvp.PitRemake.Jewels;

import de.tr7zw.nbtapi.NBTCompound;
import de.tr7zw.nbtapi.NBTItem;
import me.alpha.kitpvp.ChatManager.RankColor;
import me.alpha.kitpvp.Data.ClassInstances;
import me.alpha.kitpvp.PitRemake.MysticWell.enchanters.FreshPants;
import me.alpha.kitpvp.PitRemake.MysticWell.enchanters.MysticSword;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import static me.alpha.kitpvp.PitRemake.MysticWell.loreChecker.CheckEnchantOnSword;
import static me.alpha.kitpvp.utils.FancyText.compileListToString;
import static me.alpha.kitpvp.utils.FancyText.hoverText;


public class swordJewl {

    public static ItemStack generateJewelSword(Player player){

        ItemStack item = new ItemStack(Material.GOLD_SWORD, 1);

        NBTItem nbtItem = new NBTItem(item);

        NBTCompound nbtCompound = nbtItem.getOrCreateCompound("enchants");

        String enchant = MysticSword.getEnchant(new ArrayList<>());

        nbtCompound.setInteger(enchant, 3);

        nbtItem.mergeCompound(nbtCompound);

        ItemMeta meta = nbtItem.getItem().getItemMeta();
        meta.setDisplayName(ChatColor.translateAlternateColorCodes('&',"&cTier I Sword"));

        meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE, ItemFlag.HIDE_ATTRIBUTES);
        meta.spigot().setUnbreakable(true);

        meta.setLore(pantJewl.getLore(nbtItem.getItem()));

        nbtItem.getItem().setItemMeta(meta);


        hoverText(ChatColor.translateAlternateColorCodes('&', "&3&lJEWEL! " + RankColor.getNameColor(player) + player.getDisplayName() + ChatColor.GRAY + " obtained: " + ChatColor.RED + "Jewel Enchant!"), compileListToString(pantJewl.getLore(nbtItem.getItem())));
        return nbtItem.getItem();
    }
}
