package me.alpha.kitpvp.PitRemake.QuestMaster;

import me.alpha.kitpvp.PitRemake.ItemStacks.enchants;
import me.alpha.kitpvp.PitRemake.PitCommands.Stash.StashCore;
import me.alpha.kitpvp.utils.Sounds;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import static me.alpha.kitpvp.Data.GoldData.*;
import static me.alpha.kitpvp.PitRemake.RenownShop.CookieMonster.MonsterHandler.createMonsterBoss;
import static me.alpha.kitpvp.utils.ColorUtil.colorCode;

public class questInventoryManager {
    public static void main(InventoryClickEvent event){
        String uuid = String.valueOf(event.getWhoClicked().getUniqueId());
        Player player = (Player) event.getWhoClicked();

        hasEconomy(uuid);

        ItemStack claimRewards = questMenuItems.claimRewards(uuid);
        ItemStack FreshPants = questMenuItems.PlayerSoulsToFresh(uuid);
        ItemStack MysticSword = questMenuItems.PlayerSoulsToMysticSword(uuid);
        ItemStack PantJewel = questMenuItems.PlayerSoulsToJewelPant(uuid);
        ItemStack SwordJewel = questMenuItems.PlayerSoulsToJewelSword(uuid);
        ItemStack Malding = questMenuItems.PlayerSoulsToMalding(uuid);

        if(event.getCurrentItem().equals(Malding)){
            if(player.getInventory().containsAtLeast(enchants.playerSoul, 32)) {
                if (getEconomy(uuid) >= 25000) {
                    for (int i = 0; i < 32; i++) {
                        player.getInventory().removeItem(enchants.playerSoul);
                    }

                    removeEconomy(uuid, 25000);

                    createMonsterBoss(player);
                    player.sendMessage(colorCode("&c&lWOAH! &7a wild &bCookie Monster &7has appeared!"));
                    Sounds.PRESTIGE.play(player);
                }else{
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&bQuest Master&8 >> &7Hmm it seems as you don't have enough gold for that, come back when you have 100,000 gold!"));
                }
            }
        }else if(event.getCurrentItem().equals(FreshPants)){
            if(player.getInventory().containsAtLeast(enchants.playerSoul, 64) &&
            getEconomy(uuid)>=50000){
                for (int i = 0; i < 64; i++) {
                    player.getInventory().removeItem(enchants.playerSoul);
                }

                StashCore.safeGive(player, enchants.fullPantPB);

                removeEconomy(uuid, 50000);

                player.sendMessage(colorCode("&a&lCONGRATS! &7succesfully purchased!"));
                Sounds.RENOWN_SHOP_PURCHASE.play(player);
            }
        }else if(event.getCurrentItem().equals(MysticSword)){
            if(player.getInventory().containsAtLeast(enchants.playerSoul, 128) &&
                    getEconomy(uuid)>=50000){
                for (int i = 0; i < 256; i++) {
                    player.getInventory().removeItem(enchants.playerSoul);
                }

                StashCore.safeGive(player, enchants.fullSwordPB);

                removeEconomy(uuid, 50000);

                player.sendMessage(colorCode("&a&lCONGRATS! &7succesfully purchased!"));
                Sounds.RENOWN_SHOP_PURCHASE.play(player);
            }
        }else if(event.getCurrentItem().equals(PantJewel)){
            if(player.getInventory().containsAtLeast(enchants.playerSoul, 128) &&
                    getEconomy(uuid)>=50000){
                for (int i = 0; i < 256; i++) {
                    player.getInventory().removeItem(enchants.playerSoul);
                }

                StashCore.safeGive(player, enchants.jewl_pant);

                removeEconomy(uuid, 50000);

                player.sendMessage(colorCode("&a&lCONGRATS! &7succesfully purchased!"));
                Sounds.RENOWN_SHOP_PURCHASE.play(player);
            }
        }else if(event.getCurrentItem().equals(SwordJewel)){
            if(player.getInventory().containsAtLeast(enchants.playerSoul, 128) &&
                    getEconomy(uuid)>=75000){
                for (int i = 0; i < 256; i++) {
                    player.getInventory().removeItem(enchants.playerSoul);
                }

                StashCore.safeGive(player, enchants.jewl_sword);

                removeEconomy(uuid, 75000);

                player.sendMessage(colorCode("&a&lCONGRATS! &7succesfully purchased!"));
                Sounds.RENOWN_SHOP_PURCHASE.play(player);
            }
        }



        event.setCancelled(true);
    }
}
