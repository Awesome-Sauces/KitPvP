package me.alpha.kitpvp.PitRemake.Gems;

import de.tr7zw.nbtapi.NBTCompound;
import de.tr7zw.nbtapi.NBTItem;
import me.alpha.kitpvp.PitRemake.ItemStacks.enchants;
import me.alpha.kitpvp.PitRemake.MysticWell.enchanters.FreshPants;
import me.alpha.kitpvp.PitRemake.MysticWell.enchanters.MysticBow;
import me.alpha.kitpvp.PitRemake.MysticWell.enchanters.MysticSword;
import me.alpha.kitpvp.PitRemake.PitCommands.Stash.StashCore;
import me.alpha.kitpvp.events.InventoryClickEvents;
import me.alpha.kitpvp.utils.ColorUtil;
import me.alpha.kitpvp.utils.Sounds;
import me.alpha.kitpvp.utils.advancedInventory;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
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

    @EventHandler
    public void InventoryClickEvent(InventoryClickEvent event){
        if(event==null ||
        event.getClickedInventory()==null ||
        event.getClickedInventory().getTitle()==null ||
        !event.getClickedInventory().getTitle().equalsIgnoreCase(ChatColor.GRAY + "Totally Legit Selector")) return;

        event.setCancelled(true);

        Player player = (Player) event.getWhoClicked();
        ItemStack clicked = event.getCurrentItem();

        if(clicked.getType().equals(Material.GOLD_SWORD)||
                clicked.getType().equals(Material.LEATHER_LEGGINGS) ||
                clicked.getType().equals(Material.BOW)){
            if(!player.getInventory().containsAtLeast(enchants.gem,1)){
                player.sendMessage(ColorUtil.colorCode("&c&lERROR! &7It seems as if you don't have a gem in your inventory!"));
                Sounds.ERROR.play(player);
                player.closeInventory();
                return;
            }

            if(!player.getInventory().contains(clicked)){
                player.sendMessage(ColorUtil.colorCode("&c&lERROR! &7It seems as if you don't have this item in your inventory!"));
                Sounds.ERROR.play(player);
                player.closeInventory();
                return;
            }

            storedMystic.put(player.getUniqueId().toString(), clicked);
            player.openInventory(gemMain.gemItemGUI(player, clicked));
        }

    }

    @EventHandler
    public void ConfirmMenu(InventoryClickEvent event){
        if(event==null ||
                event.getClickedInventory()==null ||
                event.getClickedInventory().getTitle()==null ||
                !event.getClickedInventory().getTitle().equalsIgnoreCase(ChatColor.GRAY + "Totally Legit Gem")) return;

        event.setCancelled(true);

        Player player = (Player) event.getWhoClicked();
        ItemStack clicked = event.getCurrentItem();

        if(clicked.getType().equals(Material.GOLD_SWORD)||
                clicked.getType().equals(Material.LEATHER_LEGGINGS) ||
                clicked.getType().equals(Material.BOW)){

            if(!player.getInventory().containsAtLeast(enchants.gem,1)){
                player.sendMessage(ColorUtil.colorCode("&c&lERROR! &7It seems as if you don't have a gem in your inventory!"));
                Sounds.ERROR.play(player);
                player.closeInventory();
                return;
            }

            if(clicked.getItemMeta().getLore().get(6).contains(ColorUtil.colorCode("&cMaxed out!"))){
                player.sendMessage(ColorUtil.colorCode("&cThis enchant is already maxed out!"));
                Sounds.NO.play(player);
                return;
            }

            ItemStack item = storedMystic.get(player.getUniqueId().toString());

            NBTItem nbtItem = new NBTItem(item);

            String enchant = ChatColor.stripColor(clicked.getItemMeta().getLore().get(1)).replaceAll("Upgrade: ", "");

            NBTCompound nbtCompound = nbtItem.getOrCreateCompound("enchants");

            nbtCompound.setInteger(enchant, Math.min(nbtCompound.getInteger(enchant)+1,3));

            nbtItem.mergeCompound(nbtCompound);
            nbtItem.setBoolean("gem", true);

            item = nbtItem.getItem();

            ItemMeta meta = item.getItemMeta();

            List<String> lore = new ArrayList<>();

            lore.add(ChatColor.translateAlternateColorCodes('&', "&7Lives: &a5&7/5"));
            lore.add("   ");

            for (String key : nbtCompound.getKeys()){
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
                meta.setDisplayName(colorCode("&cTier III Pants"));
                lore.add(ChatColor.RED + "As strong as iron");
            }else if(item.getType().equals(Material.GOLD_SWORD)){
                meta.setDisplayName(colorCode("&cTier III Sword"));
                lore.add(ChatColor.BLUE + "+6.5 Attack Damage");
            }else if(item.getType().equals(Material.BOW)){
                meta.setDisplayName(colorCode("&cTier III Bow"));
                lore.add(ChatColor.BLUE + "+6.5 Attack Damage");
            }

            lore.set(0, lore.get(0)+ChatColor.GREEN+" \u29EB");

            meta.setLore(lore);

            item.setItemMeta(meta);

            StashCore.safeRemove(player, storedMystic.get(player.getUniqueId().toString()));

            storedMystic.put(player.getUniqueId().toString(), new ItemStack(Material.AIR));
            storedMystic.put(player.getUniqueId().toString(), new ItemStack(Material.AIR));

            StashCore.safeGive(player, item);

            Sounds.GEM_USE.play(player);

            StashCore.safeRemove(player, enchants.gem);

            player.closeInventory();
        }else if(clicked.getType().equals(Material.ARROW)){
            player.openInventory(gemMain.makeGemGUI(player));
        }

    }
}
