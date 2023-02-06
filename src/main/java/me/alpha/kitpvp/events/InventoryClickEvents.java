package me.alpha.kitpvp.events;

import com.nametagedit.plugin.NametagEdit;
import me.alpha.kitpvp.ChatManager.ChatManager;
import me.alpha.kitpvp.ChatManager.RankColor;
import me.alpha.kitpvp.CustomEvents.ReduxInventoryEvent;
import me.alpha.kitpvp.Data.ClassInstances;
import me.alpha.kitpvp.DataSave.DatabaseConnector;
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


import java.sql.SQLException;

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

    public static void main(ReduxInventoryEvent event) {


        Player player = event.getPlayer();
        String uuid = String.valueOf(player.getUniqueId());

        if (!event.isInventory("Mystic Well")){

            if (event.getItemType() == Material.ENCHANTMENT_TABLE) {
                ItemStack items = event.getInventory().getItem(20);
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

            }else if (!event.isInventory("Mystic Well")){
                    if (event.getItemType().equals(Material.LEATHER_LEGGINGS) ||
                            event.getItemType().equals(Material.GOLD_SWORD) ||
                            event.getItemType().equals(Material.BOW)) {

                        StashCore.safeGive(player, event.getInventory().getItem(20));
                        event.getInventory().setItem(20, null);

                    }
            }

            }
        if (event.getInventory().getTitle().equalsIgnoreCase(player.getInventory().getTitle())){
            if (event.getItemName().contains("Fresh") ||
                    event.getItemName().contains("Mystic Sword") ||
                    event.getItemName().contains("Mystic Bow")){
                Inventory gui = MysticWellGUI.openMysticWell(player,event.getClickedItem());

                player.openInventory(gui);

                StashCore.safeRemove(player, event.getClickedItem());


            }else if (event.getItemName().contains("Tier I")){
                player.openInventory(MysticWellGUI.openMysticWell(player, event.getClickedItem()));
                StashCore.safeRemove(player, event.getClickedItem());

            }else if (event.getItemName().contains("Tier II")){
                player.openInventory(MysticWellGUI.openMysticWell(player, event.getInventory().getItem(21)));
                StashCore.safeRemove(player, event.getClickedItem());

            }else{
                Sounds.MYSTIC_WELL_NO.play(player);
                player.sendMessage(colorCode("&cThis item cannot be enchanted!"));
                player.sendMessage(colorCode("&cYou need a &bMystic Bow&c, &eMystic Sword &cor &cP&6a&en&at&9s&c!"));
            }
            }

        }

    public static void NonPermItems(ReduxInventoryEvent event) {
        Player player = event.getPlayer();
        String uuid = String.valueOf(player.getUniqueId());

        switch (event.getItemType()) {
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

                    break;

                }
            case MINECART:
                if (hasEconomy(String.valueOf(player.getUniqueId()))) {
                    if (getEconomy(String.valueOf(player.getUniqueId())) >= 150 && event.getItemName().contains("Pants Bundle")) {
                        removeEconomy(String.valueOf(player.getUniqueId()), 150);
                        StashCore.safeGive(player,enchants.pantsPB);

                        player.getWorld().playSound(player.getLocation(), Sound.LEVEL_UP, 1, 20);

                        ScoreboardCore.CreateScore(player);

                    }else if (getEconomy(String.valueOf(player.getUniqueId())) >= 150 && event.getItemName().contains("Sword Bundle")) {
                        removeEconomy(String.valueOf(player.getUniqueId()), 150);
                        StashCore.safeGive(player, enchants.swordPB);

                        player.getWorld().playSound(player.getLocation(), Sound.LEVEL_UP, 1, 20);

                        ScoreboardCore.CreateScore(player);

                    } else {
                        player.sendMessage(ChatColor.RED + "You can't afford this!");
                    }

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
                    break;

                }
        }
    }

    public static void PrestigeItems(ReduxInventoryEvent event){
        Player player = event.getPlayer();
        if(event.getItemType()==Material.WATCH){
            player.closeInventory();
            player.sendMessage(ColorUtil.colorCode("&6&lBLOXICLE STORE! &7Click on: &ehttp://store.pitredux.net"));
            return;
        }

        if (event.getItemType() == Material.DIAMOND) {
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

                try {
                    DatabaseConnector.updatePrestige(player);
                } catch (SQLException e) {
                    e.printStackTrace();
                    throw new RuntimeException(e);
                }
            } else {
                player.sendMessage(ChatColor.RED + "You need Level 120 to prestige!");
            }
        }else if (event.getItemType() == Material.BEACON){
            player.openInventory(RenownShopGUI.getRenownShopGUI(player));
        }

    }

}
