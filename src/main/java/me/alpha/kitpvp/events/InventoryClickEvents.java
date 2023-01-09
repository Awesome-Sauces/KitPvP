package me.alpha.kitpvp.events;

import com.nametagedit.plugin.NametagEdit;
import me.alpha.kitpvp.ChatManager.ChatManager;
import me.alpha.kitpvp.ChatManager.RankColor;
import me.alpha.kitpvp.Data.ClassInstances;
import me.alpha.kitpvp.PitRemake.ItemStacks.enchants;
import me.alpha.kitpvp.PitRemake.ItemStacks.itemManager;
import me.alpha.kitpvp.PitRemake.MysticWell.MysticWellGUI;
import me.alpha.kitpvp.PitRemake.MysticWell.enchanters.FreshPants;
import me.alpha.kitpvp.PitRemake.MysticWell.enchanters.MysticBow;
import me.alpha.kitpvp.PitRemake.MysticWell.enchanters.MysticSword;
import me.alpha.kitpvp.PitRemake.PitCommands.Stash.StashCore;
import me.alpha.kitpvp.PitRemake.RenownShop.gui.RenownShopGUI;
import me.alpha.kitpvp.PitRemake.Scoreboard.ScoreboardCore;
import me.alpha.kitpvp.utils.ColorUtil;
import me.alpha.kitpvp.utils.Sounds;
import net.minecraft.server.v1_8_R3.IChatBaseComponent;
import net.minecraft.server.v1_8_R3.PacketPlayOutTitle;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;


import static me.alpha.kitpvp.Data.GoldData.*;
import static me.alpha.kitpvp.Data.GoldRequirementData.getGoldRequirement;
import static me.alpha.kitpvp.Data.PrestigeData.PrestigeXpAmount;
import static me.alpha.kitpvp.Data.XpData.GetCurrentLevel;
import static me.alpha.kitpvp.Data.XpData.getLevelXP;
import static me.alpha.kitpvp.PitRemake.RenownShop.renownAmount.GetByPrestige;
import static me.alpha.kitpvp.utils.ColorUtil.colorCode;
import static me.alpha.kitpvp.utils.IntegerHelper.integerToRoman;
import static me.alpha.kitpvp.utils.advancedInventory.ItemMaker;
import static me.alpha.kitpvp.utils.advancedInventory.addInv;

public class InventoryClickEvents {

    public static void main(InventoryClickEvent event) {

        if(event==null ||
                event.getClickedInventory()==null ||
                event.getClickedInventory().getTitle()==null) return;

        Player player = (Player) event.getWhoClicked();
        String uuid = String.valueOf(player.getUniqueId());

        if (event.getClickedInventory().getTitle().equalsIgnoreCase(ChatColor.GRAY + "Mystic Well")){
            event.setCancelled(true);
            if (event.getCurrentItem().getType() == Material.ENCHANTMENT_TABLE) {
                ItemStack items = event.getClickedInventory().getItem(20);
                hasEconomy(uuid);

                if (items.getType().equals(Material.LEATHER_LEGGINGS)) {
                    FreshPants.clickFresh(event);
                }

                if (items.getType().equals(Material.GOLD_SWORD)) {
                    MysticSword.clickSword(event);
                    return;
                }

                if (items.getType().equals(Material.BOW)) {
                    MysticBow.clickBow(event);
                }

            }else if (event.getClickedInventory().getTitle().equalsIgnoreCase(ChatColor.GRAY + "Mystic Well")){
                    if (event.getCurrentItem().getType().equals(Material.LEATHER_LEGGINGS) ||
                            event.getCurrentItem().getType().equals(Material.GOLD_SWORD) ||
                            event.getCurrentItem().getType().equals(Material.BOW)) {

                        StashCore.safeGive(player, event.getClickedInventory().getItem(20));
                        event.getClickedInventory().setItem(20, null);

                    }
            }

            }
        if (event.getClickedInventory().getTitle().equalsIgnoreCase(player.getInventory().getTitle())){
            if (event.getCurrentItem().getItemMeta().getDisplayName().contains("Fresh") ||
                    event.getCurrentItem().getItemMeta().getDisplayName().contains("Mystic Sword") ||
                    event.getCurrentItem().getItemMeta().getDisplayName().contains("Mystic Bow")){
                Inventory gui = MysticWellGUI.openMysticWell(player,event.getCurrentItem());

                player.openInventory(gui);

                StashCore.safeRemove(player, event.getCurrentItem());
                event.setCancelled(true);

            }else if (event.getCurrentItem().getItemMeta().getDisplayName().contains("Tier I")){
                player.openInventory(MysticWellGUI.openMysticWell(player, event.getCurrentItem()));
                StashCore.safeRemove(player, event.getCurrentItem());
                event.setCancelled(true);
            }else if (event.getCurrentItem().getItemMeta().getDisplayName().contains("Tier II")){
                player.openInventory(MysticWellGUI.openMysticWell(player, event.getClickedInventory().getItem(21)));
                StashCore.safeRemove(player, event.getCurrentItem());
                event.setCancelled(true);
            }
            }

        }
        /*else {
            hasFresh(uuid);
            if (event.getClickedInventory().getTitle().equalsIgnoreCase(ChatColor.GRAY + "Mystic Well")){
                if (event.getCurrentItem().getItemMeta().equals(enchants.fresh_reds.getItemMeta())){
                    base(player);
                    player.getInventory().addItem(enchants.fresh_reds);
                    setFresh(uuid, false);
                    event.setCancelled(true);
                }
            }

        }*/


    public static void NonPermItems(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();
        String uuid = String.valueOf(player.getUniqueId());

        switch (event.getCurrentItem().getType()) {
            case DIAMOND_SWORD:
                if (hasEconomy(String.valueOf(player.getUniqueId()))) {
                    if (getEconomy(String.valueOf(player.getUniqueId())) >= 150) {
                        removeEconomy(String.valueOf(player.getUniqueId()), 150);

                        StashCore.safeGive(player, ItemMaker(Material.DIAMOND_SWORD, "NULL", "NULL", 1, false));

                        player.getWorld().playSound(player.getLocation(), Sound.LEVEL_UP, 1, 20);

                        ScoreboardCore.CreateScore(player);

                    } else {
                        player.sendMessage(ChatColor.RED + "You can't afford this!");
                    }
                    event.setCancelled(true);
                    break;

            }
            case DIAMOND_SPADE:
                if (hasEconomy(String.valueOf(player.getUniqueId()))) {
                    if (getEconomy(String.valueOf(player.getUniqueId())) >= 750) {
                        removeEconomy(String.valueOf(player.getUniqueId()), 750);

                        StashCore.safeGive(player, ItemMaker(Material.DIAMOND_SPADE, ChatColor.AQUA + "Combat Spade", ChatColor.GRAY + "Deals " + ChatColor.BLUE +
                                "+1 damage per\n" + ChatColor.AQUA + "diamond piece " + ChatColor.GRAY + "on enemy." + "\n\n"  +
                                ChatColor.BLUE + "+7 Attack Damage", 1, false));

                        player.getWorld().playSound(player.getLocation(), Sound.LEVEL_UP, 1, 20);

                        ScoreboardCore.CreateScore(player);

                    } else {
                        player.sendMessage(ChatColor.RED + "You can't afford this!");
                    }
                    event.setCancelled(true);
                    break;

                }
            case IRON_HELMET:
                if (hasEconomy(String.valueOf(player.getUniqueId()))) {
                    if (getEconomy(String.valueOf(player.getUniqueId())) >= 200) {
                        removeEconomy(String.valueOf(player.getUniqueId()), 200);

                        StashCore.safeGive(player, itemManager.IronHelmet);
                        StashCore.safeGive(player, itemManager.IronChestplate);
                        StashCore.safeGive(player, itemManager.IronLeggings);
                        StashCore.safeGive(player, itemManager.IronBoots);

                        }

                        player.getWorld().playSound(player.getLocation(), Sound.LEVEL_UP, 1, 20);

                        ScoreboardCore.CreateScore(player);

                    } else {
                        player.sendMessage(ChatColor.RED + "You can't afford this!");
                    }
                    event.setCancelled(true);
                    break;

            case DIAMOND_CHESTPLATE:
                if (hasEconomy(String.valueOf(player.getUniqueId()))) {
                    if (getEconomy(String.valueOf(player.getUniqueId())) >= 500) {
                        removeEconomy(String.valueOf(player.getUniqueId()), 500);

                        if(player.getInventory().getChestplate() != null &&
                                player.getInventory().getChestplate().getType().equals(Material.IRON_CHESTPLATE) || player.getInventory().getChestplate() == null){
                            Sounds.ARMOR_SWAP.play(player);
                            player.getInventory().setChestplate(ItemMaker(Material.DIAMOND_CHESTPLATE, "NULL", "NULL", 1, false));
                        }else{
                            StashCore.safeGive(player, ItemMaker(Material.DIAMOND_CHESTPLATE, "NULL", "NULL", 1, false));
                        }

                        player.getWorld().playSound(player.getLocation(), Sound.LEVEL_UP, 1, 20);

                        ScoreboardCore.CreateScore(player);

                    } else {
                        player.sendMessage(ChatColor.RED + "You can't afford this!");
                    }
                    event.setCancelled(true);
                    break;

                }
            case DIAMOND_LEGGINGS:
                if (hasEconomy(String.valueOf(player.getUniqueId()))) {
                    if (getEconomy(String.valueOf(player.getUniqueId())) >= 1500) {
                        removeEconomy(String.valueOf(player.getUniqueId()), 1500);

                        if(player.getInventory().getLeggings() != null &&
                        player.getInventory().getLeggings().getType().equals(Material.IRON_LEGGINGS) || player.getInventory().getLeggings() == null){
                            Sounds.ARMOR_SWAP.play(player);
                            player.getInventory().setLeggings(ItemMaker(Material.DIAMOND_LEGGINGS, "NULL", "NULL", 1, false));
                        }else{
                            StashCore.safeGive(player,ItemMaker(Material.DIAMOND_LEGGINGS, "NULL", "NULL", 1, false));
                        }

                        player.getWorld().playSound(player.getLocation(), Sound.LEVEL_UP, 1, 20);

                        ScoreboardCore.CreateScore(player);

                    } else {
                        player.sendMessage(ChatColor.RED + "You can't afford this!");
                    }
                    event.setCancelled(true);
                    break;

                }
            case OBSIDIAN:
                if (hasEconomy(String.valueOf(player.getUniqueId()))) {
                    if (getEconomy(String.valueOf(player.getUniqueId())) >= 40) {
                        removeEconomy(String.valueOf(player.getUniqueId()), 40);
                        StashCore.safeGiveMultiple(player,ItemMaker(Material.OBSIDIAN, "NULL", "NULL", 1, false), 8);

                        player.getWorld().playSound(player.getLocation(), Sound.LEVEL_UP, 1, 20);

                        ScoreboardCore.CreateScore(player);

                    } else {
                        player.sendMessage(ChatColor.RED + "You can't afford this!");
                    }
                    event.setCancelled(true);
                    break;

                }
            case MONSTER_EGG:
                if (hasEconomy(String.valueOf(player.getUniqueId()))) {
                    if (getEconomy(String.valueOf(player.getUniqueId())) >= 2000) {
                        removeEconomy(String.valueOf(player.getUniqueId()), 2000);

                        StashCore.safeGive(player, enchants.firstaidfull);
                        player.getWorld().playSound(player.getLocation(), Sound.LEVEL_UP, 1, 20);

                        ScoreboardCore.CreateScore(player);

                    } else {
                        player.sendMessage(ChatColor.RED + "You can't afford this!");
                    }
                    event.setCancelled(true);
                    break;

                }
            case MINECART:
                if (hasEconomy(String.valueOf(player.getUniqueId()))) {
                    if (getEconomy(String.valueOf(player.getUniqueId())) >= 150 && event.getCurrentItem().getItemMeta().getDisplayName().contains("Pants Bundle")) {
                        removeEconomy(String.valueOf(player.getUniqueId()), 150);
                        StashCore.safeGive(player,enchants.pantsPB);

                        player.getWorld().playSound(player.getLocation(), Sound.LEVEL_UP, 1, 20);

                        ScoreboardCore.CreateScore(player);

                    }else if (getEconomy(String.valueOf(player.getUniqueId())) >= 150 && event.getCurrentItem().getItemMeta().getDisplayName().contains("Sword Bundle")) {
                        removeEconomy(String.valueOf(player.getUniqueId()), 150);
                        StashCore.safeGive(player, enchants.swordPB);

                        player.getWorld().playSound(player.getLocation(), Sound.LEVEL_UP, 1, 20);

                        ScoreboardCore.CreateScore(player);

                    } else {
                        player.sendMessage(ChatColor.RED + "You can't afford this!");
                    }


                    event.setCancelled(true);
                    break;

                }
            case DIAMOND_BOOTS:
                if (hasEconomy(String.valueOf(player.getUniqueId()))) {
                    if (getEconomy(String.valueOf(player.getUniqueId())) >= 300) {
                        removeEconomy(String.valueOf(player.getUniqueId()), 300);

                        if(player.getInventory().getBoots() != null &&
                                player.getInventory().getBoots().getType().equals(Material.IRON_BOOTS) || player.getInventory().getBoots() == null){
                            Sounds.ARMOR_SWAP.play(player);
                            player.getInventory().setBoots(ItemMaker(Material.DIAMOND_BOOTS, "NULL", "NULL", 1, false));
                        }else{
                            StashCore.safeGive(player, ItemMaker(Material.DIAMOND_BOOTS, "NULL", "NULL", 1, false));
                        }


                        player.getWorld().playSound(player.getLocation(), Sound.LEVEL_UP, 1, 20);
                        ScoreboardCore.CreateScore(player);

                    } else {
                        player.sendMessage(ChatColor.RED + "You can't afford this!");
                    }
                    event.setCancelled(true);
                    break;

                }
        }
    }

    public static void PrestigeItems(InventoryClickEvent event){
        Player player = (Player) event.getWhoClicked();
        if(event.getCurrentItem().getType()==Material.WATCH){
            event.setCancelled(true);
            player.closeInventory();
            player.sendMessage(ColorUtil.colorCode("&6&lBLOXICLE STORE! &7Click on: &ehttp://store.pitredux.net"));
            return;
        }

        if (event.getCurrentItem().getType() == Material.DIAMOND) {
            event.setCancelled(true);
            int[] randomDUDE;
            randomDUDE = GetCurrentLevel(String.valueOf(player.getUniqueId()), ClassInstances.xpData.getXp(String.valueOf(player.getUniqueId())), ClassInstances.prestigeData.getPrestige(String.valueOf(player.getUniqueId())), player);

            if (randomDUDE[1] >= 120) {

                if(ClassInstances.goldRequirementData.getGoldReq(String.valueOf(player.getUniqueId())) <
                        getGoldRequirement(ClassInstances.prestigeData.getPrestige(String.valueOf(player.getUniqueId())))){
                    player.sendMessage(ChatColor.RED + "You can't afford this!");
                    player.closeInventory();
                    return;
                }

                if (ClassInstances.prestigeData.getPrestige(String.valueOf(player.getUniqueId())) >= 100) {
                    ClassInstances.prestigeData.setPrestige(String.valueOf(player.getUniqueId()), 100);
                    player.sendMessage(ChatColor.RED + "You've reached the max Prestige! Congrats!");
                    return;
                }
                ClassInstances.megaStreakData.setMegaStreak(String.valueOf(player.getUniqueId()), "overdrive");
                ClassInstances.streakData.setStreak(String.valueOf(player.getUniqueId()), 0);
                hasEconomy(String.valueOf(player.getUniqueId()));
                setEconomy(String.valueOf(player.getUniqueId()), 0);
                ClassInstances.xpData.setXp(String.valueOf(player.getUniqueId()), 0);
                ClassInstances.goldRequirementData.setGoldReq(String.valueOf(player.getUniqueId()), 0);

                ClassInstances.renownData.addRenown(String.valueOf(player.getUniqueId()),GetByPrestige(ClassInstances.prestigeData.getPrestige(String.valueOf(player.getUniqueId()))));

                player.setExp(0);
                player.setLevel(0);
                NametagEdit.getApi().setNametag(player, ChatManager.getLevelText(player) + RankColor.getNameColor(player), "");
                ClassInstances.prestigeData.addPrestige(String.valueOf(player.getUniqueId()), 1);
                ClassInstances.xpData.addXp(String.valueOf(player.getUniqueId()), (int) (15 + (15* PrestigeXpAmount(ClassInstances.prestigeData.getPrestige(String.valueOf(player.getUniqueId()))))));
                ClassInstances.xpData.addXp(String.valueOf(player.getUniqueId()), (int) (15 + (15* PrestigeXpAmount(ClassInstances.prestigeData.getPrestige(String.valueOf(player.getUniqueId()))))));
                ClassInstances.xpData.addXp(String.valueOf(player.getUniqueId()), 1);
                randomDUDE = GetCurrentLevel(String.valueOf(player.getUniqueId()), ClassInstances.xpData.getXp(String.valueOf(player.getUniqueId())), ClassInstances.prestigeData.getPrestige(String.valueOf(player.getUniqueId())), player);
                Bukkit.broadcastMessage(ChatColor.YELLOW + colorCode("&lPRESTIGE! ") + ChatColor.GOLD + player.getDisplayName() + ChatColor.GRAY + " unlocked prestige " + ChatColor.YELLOW + ClassInstances.prestigeData.getPrestige(String.valueOf(player.getUniqueId())) + ChatColor.GRAY + " gg!");
                ScoreboardCore.CreateScore(player);
                player.closeInventory();
                if(ClassInstances.fastPass.hasValue(String.valueOf(player.getUniqueId()))){
                    ClassInstances.xpData.setXp(player.getUniqueId().toString(),getLevelXP(player, 50, ClassInstances.prestigeData.getPrestige(player.getUniqueId().toString())));
                }
                player.playSound(player.getLocation(), Sound.ENDERDRAGON_GROWL, 1, 0);
                PacketPlayOutTitle title = new PacketPlayOutTitle(PacketPlayOutTitle.EnumTitleAction.TITLE,
                        IChatBaseComponent.ChatSerializer.a("{\"text\":\"PRESTIGE!\",\"bold\":true,\"color\":\"yellow\"}"), 100, 20, 20);
                ((CraftPlayer) player).getHandle().playerConnection.sendPacket(title);
                PacketPlayOutTitle sub_title = new PacketPlayOutTitle(PacketPlayOutTitle.EnumTitleAction.SUBTITLE, IChatBaseComponent.ChatSerializer.a("[\"\",{\"text\":\"\",\"color\":\"gray\"},{\"text\":\"" + "You unlocked prestige" + "\",\"color\":\"gray\"},{\"text\":\"\",\"color\":\"gray\"},{\"text\":\" \",\"color\":\"gray\"},{\"text\":\"\",\"color\":\"yellow\"},{\"text\":\"" + integerToRoman(ClassInstances.prestigeData.getPrestige(String.valueOf(player.getUniqueId()))) + "\",\"color\":\"gray\"},{\"text\":\"\",\"color\":\"gray\"}]"), 100, 20, 20);
                ((CraftPlayer) player).getHandle().playerConnection.sendPacket(sub_title);
            } else {
                player.sendMessage(ChatColor.RED + "You need Level 120 to prestige!");
            }
        }else if (event.getCurrentItem().getType() == Material.BEACON){
            player.openInventory(RenownShopGUI.getRenownShopGUI(player));
            event.setCancelled(true);
        }
        event.setCancelled(true);

    }

}
