package me.alpha.kitpvp.PitRemake.StreakManager;

import me.alpha.kitpvp.Data.ClassInstances;
import me.alpha.kitpvp.Data.GoldData;
import me.alpha.kitpvp.PitRemake.ItemStacks.enchants;
import me.alpha.kitpvp.PitRemake.ItemStacks.itemManager;
import me.alpha.kitpvp.PitRemake.PitCommands.Stash.StashCore;
import me.alpha.kitpvp.utils.Sounds;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static me.alpha.kitpvp.Objects.ReduxPlayerObject.ReduxPlayerHandler.playerExists;
import static me.alpha.kitpvp.utils.ColorUtil.colorCode;
import static me.alpha.kitpvp.utils.FancyText.compileListToStringWithTitle;
import static me.alpha.kitpvp.utils.FancyText.hoverText;

public class UberRewards {

    private static Boolean percentChance(double chance) {
        return Math.random() <= chance;
    }

    public static void claimUberReward(Player player){
        Random rand = new Random(); //instance of random class
        int upperbound = 40;
        //generate random values from 0-24
        int int_random = rand.nextInt(upperbound);

            /*
            1x pant part, 5x pant parts, 50k gold, 100k gold,
             250k gold, 1x feather, 2x feather, 3x feather,
              1x dark fragment, 5x dark fragment, 1x sword star,
               5x sword star, prot helmet, prot chest, prot leggings,
                prot boots, sharp sword, first aid egg, bow
             */

        List<String> protection = new ArrayList<>();
        List<String> sharpness = new ArrayList<>();
        protection.add(ChatColor.GRAY + "Protection I");
        protection.add(ChatColor.BLUE + "Unbreakable");

        sharpness.add(ChatColor.GRAY + "Sharpness I");
        sharpness.add(ChatColor.BLUE + "Unbreakable");

        String FeatherLore = compileListToStringWithTitle(String.valueOf(itemManager.feather.getItemMeta().getDisplayName()) + "\n", itemManager.feather.getItemMeta().getLore());
        String FreshRedsLore = compileListToStringWithTitle(String.valueOf(enchants.cactus.getItemMeta().getDisplayName()) + "\n", enchants.cactus.getItemMeta().getLore());
        String VileLore = compileListToStringWithTitle(String.valueOf(enchants.vile.getItemMeta().getDisplayName()) + "\n", enchants.vile.getItemMeta().getLore());
        String BootLore = compileListToStringWithTitle(ChatColor.WHITE + "Diamond Boots"  + "\n", protection);
        String LegLore = compileListToStringWithTitle(ChatColor.WHITE + "Diamond Leggings" + "\n", protection);
        String ChestLore = compileListToStringWithTitle(ChatColor.WHITE + "Diamond Chestplate"  + "\n", protection);
        String HeadLore = compileListToStringWithTitle(ChatColor.WHITE + "Diamond Helmet" + "\n", protection);
        String DiamondSwordLore = compileListToStringWithTitle(ChatColor.WHITE + "Diamond Sword" + "\n", sharpness);
        String fttsLore = compileListToStringWithTitle(String.valueOf(enchants.fresh_bow.getItemMeta().getDisplayName()) + "\n", enchants.fresh_bow.getItemMeta().getLore());
        String MLBLore = compileListToStringWithTitle(String.valueOf(enchants.fresh_bow.getItemMeta().getDisplayName()) + "\n", enchants.fresh_bow.getItemMeta().getLore());
        String GhelmLore = compileListToStringWithTitle(String.valueOf(itemManager.goldHelm.getItemMeta().getDisplayName()) + "\n", itemManager.goldHelm.getItemMeta().getLore());
        String ArchLore = compileListToStringWithTitle(String.valueOf(itemManager.arch.getItemMeta().getDisplayName()) + "\n", itemManager.arch.getItemMeta().getLore());
        String JewelSwordLore = compileListToStringWithTitle(String.valueOf(enchants.jewl_sword.getItemMeta().getDisplayName()) + "\n", enchants.jewl_sword.getItemMeta().getLore());
        String JewelPantLore = compileListToStringWithTitle(String.valueOf(enchants.jewl_pant.getItemMeta().getDisplayName()) + "\n", enchants.jewl_pant.getItemMeta().getLore());
        String GemLore = compileListToStringWithTitle(String.valueOf(enchants.gem.getItemMeta().getDisplayName() + "\n"), enchants.gem.getItemMeta().getLore());

        boolean looping = true;

        while(looping){
            if(percentChance(.10)){
                GoldData.addEconomy(String.valueOf(player.getUniqueId()), 100000);
                hoverText(colorCode("&d&lUBERDROP! <level_username> &7obtained an &dUberdrop&7!", playerExists(player)), ChatColor.GOLD + "Golden Bar\n" + ChatColor.GRAY + "Receive: " + ChatColor.GOLD + "100,000g");
                looping=false;
                player.sendMessage(colorCode("&6&lGOLD REQUIREMENT! &7This gold counts toward your prestige gold requirement."));
                Sounds.BOOSTER_REMIND.play(player);
                ClassInstances.goldRequirementData.addGoldReq(player.getUniqueId().toString(), 100000);
                break;
            }else if(percentChance(.01)){
                StashCore.safeGive(player, enchants.gem);
                hoverText(colorCode("&d&lUBERDROP! <level_username> &7obtained an &dUberdrop&7!", playerExists(player)),  GemLore);
                looping=false;
                break;
            }else if(percentChance(.15)){
                GoldData.addEconomy(String.valueOf(player.getUniqueId()), 250000);

                hoverText(colorCode("&d&lUBERDROP! <level_username> &7obtained an &dUberdrop&7!", playerExists(player)),  ChatColor.GOLD + "Golden Bar\n" + ChatColor.GRAY + "Receive: " + ChatColor.GOLD + "250,000g");
                player.sendMessage(colorCode("&6&lGOLD REQUIREMENT! &7This gold counts toward your prestige gold requirement."));
                Sounds.BOOSTER_REMIND.play(player);
                ClassInstances.goldRequirementData.addGoldReq(player.getUniqueId().toString(), 250000);
                looping=false;
                break;
            }else if(percentChance(.10)){
                StashCore.safeGiveMultiple(player, enchants.cactus, 5);
                hoverText(colorCode("&d&lUBERDROP! <level_username> &7obtained an &dUberdrop&7!", playerExists(player)), FreshRedsLore);
                looping=false;
                break;
            }else if(percentChance(.05)){
                StashCore.safeGiveMultiple(player, enchants.cactus, 16);
                hoverText(colorCode("&d&lUBERDROP! <level_username> &7obtained an &dUberdrop&7!", playerExists(player)), FreshRedsLore);
                looping=false;
                break;
            }else if(percentChance(.03)){
                StashCore.safeGive(player, itemManager.DiamondBoots);
                hoverText(colorCode("&d&lUBERDROP! <level_username> &7obtained an &dUberdrop&7!", playerExists(player)), BootLore);
                looping=false;
                break;
            }else if(percentChance(.03)){
                StashCore.safeGive(player, itemManager.DiamondLeggings);
                hoverText(colorCode("&d&lUBERDROP! <level_username> &7obtained an &dUberdrop&7!", playerExists(player)), LegLore);
                looping=false;
                break;
            }else if(percentChance(.03)){
                StashCore.safeGive(player, itemManager.DiamondChestplate);
                hoverText(colorCode("&d&lUBERDROP! <level_username> &7obtained an &dUberdrop&7!", playerExists(player)), ChestLore);
                looping=false;
                break;
            }else if(percentChance(.03)){
                StashCore.safeGive(player, itemManager.DiamondHelmet);
                hoverText(colorCode("&d&lUBERDROP! <level_username> &7obtained an &dUberdrop&7!", playerExists(player)), HeadLore);
                looping=false;
                break;
            }else if(percentChance(.05)){
                StashCore.safeGive(player, itemManager.feather);
                hoverText(colorCode("&d&lUBERDROP! <level_username> &7obtained an &dUberdrop&7!", playerExists(player)), FeatherLore);
                looping=false;
                break;
            }else if(percentChance(.04)){
                StashCore.safeGiveMultiple(player, itemManager.feather, 2);
                hoverText(colorCode("&d&lUBERDROP! <level_username> &7obtained an &dUberdrop&7!", playerExists(player)), FeatherLore);
                looping=false;
                break;
            }else if(percentChance(.03)){
                StashCore.safeGiveMultiple(player, itemManager.feather, 3);
                hoverText(colorCode("&d&lUBERDROP! <level_username> &7obtained an &dUberdrop&7!", playerExists(player)), FeatherLore);
                looping=false;
                break;
            }else if(percentChance(.05)){
                StashCore.safeGive(player, itemManager.DiamondSword);
                hoverText(colorCode("&d&lUBERDROP! <level_username> &7obtained an &dUberdrop&7!", playerExists(player)), DiamondSwordLore);
                looping=false;
                break;
            }else if(percentChance(.01)){
                StashCore.safeGive(player, enchants.jewl_sword);
                hoverText(colorCode("&d&lUBERDROP! <level_username> &7obtained an &dUberdrop&7!", playerExists(player)),  JewelSwordLore);
                looping=false;
                break;
            }else if(percentChance(.05)){
                StashCore.safeGiveMultiple(player, itemManager.feather, 1);
                hoverText(colorCode("&d&lUBERDROP! <level_username> &7obtained an &dUberdrop&7!", playerExists(player)), FeatherLore);
                looping=false;
                break;
            }else if(percentChance(.03)){
                StashCore.safeGiveMultiple(player, enchants.vile, 16);
                hoverText(colorCode("&d&lUBERDROP! <level_username> &7obtained an &dUberdrop&7!", playerExists(player)),  VileLore);
                looping=false;
                break;
            }else if(percentChance(.05)){
                StashCore.safeGiveMultiple(player, enchants.vile, 8);
                hoverText(colorCode("&d&lUBERDROP! <level_username> &7obtained an &dUberdrop&7!", playerExists(player)),  VileLore);
                looping=false;
                break;
            }else if(percentChance(.02)){
                StashCore.safeGiveMultiple(player, enchants.vile, 32);
                hoverText(colorCode("&d&lUBERDROP! <level_username> &7obtained an &dUberdrop&7!", playerExists(player)),  VileLore);
                looping=false;
                break;
            }else if(percentChance(.05)){
                StashCore.safeGive(player, enchants.jewl_pant);
                hoverText(colorCode("&d&lUBERDROP! <level_username> &7obtained an &dUberdrop&7!", playerExists(player)),  JewelPantLore);
                looping=false;
                break;
            }else if(percentChance(.05)){
                StashCore.safeGive(player, enchants.jewl_pant);
                hoverText(colorCode("&d&lUBERDROP! <level_username> &7obtained an &dUberdrop&7!", playerExists(player)),  JewelPantLore);
                looping=false;
                break;
            }else if(percentChance(.08)){
                StashCore.safeGive(player, enchants.jewl_pant);
                hoverText(colorCode("&d&lUBERDROP! <level_username> &7obtained an &dUberdrop&7!", playerExists(player)),  JewelPantLore);
                looping=false;
                break;
            }else if(percentChance(.08)){
                StashCore.safeGive(player, enchants.jewl_sword);
                hoverText(colorCode("&d&lUBERDROP! <level_username> &7obtained an &dUberdrop&7!", playerExists(player)),  JewelSwordLore);
                looping=false;
                break;
            }
        }

        Sounds.UBER_500.play(player);

    }
}
