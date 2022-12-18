package me.alpha.kitpvp.PitRemake.Gems;

import de.tr7zw.nbtapi.NBTCompound;
import de.tr7zw.nbtapi.NBTItem;
import me.alpha.kitpvp.PitRemake.ItemStacks.enchants;
import me.alpha.kitpvp.PitRemake.MysticWell.enchanters.FreshPants;
import me.alpha.kitpvp.PitRemake.MysticWell.enchanters.MysticBow;
import me.alpha.kitpvp.PitRemake.MysticWell.enchanters.MysticSword;
import me.alpha.kitpvp.events.InventoryClickEvents;
import me.alpha.kitpvp.utils.Sounds;
import me.alpha.kitpvp.utils.advancedInventory;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.*;

import static me.alpha.kitpvp.PitRemake.Gems.gemMain.*;
import static me.alpha.kitpvp.PitRemake.MysticWell.loreChecker.*;
import static me.alpha.kitpvp.utils.ColorUtil.colorCode;


public class gemEvents implements Listener {

    private static HashMap<String, ItemStack> storedMystic = new HashMap<>();

    public void InventoryClickEvent(InventoryClickEvent event){


        Player player = (Player) event.getWhoClicked();


    }
}
