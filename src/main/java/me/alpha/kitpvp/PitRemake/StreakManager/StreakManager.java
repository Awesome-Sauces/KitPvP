package me.alpha.kitpvp.PitRemake.StreakManager;

import com.nametagedit.plugin.NametagEdit;
import de.tr7zw.nbtapi.NBTItem;
import me.alpha.kitpvp.ChatManager.ChatManager;
import me.alpha.kitpvp.ChatManager.RankColor;
import me.alpha.kitpvp.Data.ClassInstances;
import me.alpha.kitpvp.PitRemake.PitCommands.Stash.StashCore;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffectType;

import java.util.Objects;

import static me.alpha.kitpvp.PitRemake.Locations.getSpawnLocation;
import static me.alpha.kitpvp.PitRemake.RenownShop.RenownStorage.getUberDrop;
import static me.alpha.kitpvp.utils.ColorUtil.colorCode;
import static me.alpha.kitpvp.utils.advancedInventory.ItemMaker;

public class StreakManager {
    // Set Material to BARRIER when running unless not using custom item
    public static void GiveUberItems(Player player, ItemStack item, int amount, boolean customItem, Material material){
        if (customItem){
            StashCore.safeGiveMultiple(player, item, amount);
        }else{
            StashCore.safeGiveMultiple(player, new ItemStack(material), amount);
        }

    }

    public static void Uber(Player player){
        if (ClassInstances.streakData.getStreak(String.valueOf(player.getUniqueId())) == 100){
            ChatManager.broadcastMessage(ChatColor.translateAlternateColorCodes('&',
                    "&c&lSTREAK! &7of &c100 &7kills by " + RankColor.getNameColor(player) + ChatColor.stripColor(player.getDisplayName())), player.getWorld());
        }else if (ClassInstances.streakData.getStreak(String.valueOf(player.getUniqueId())) == 200){
            NametagEdit.getApi().setNametag(player, colorCode("&d&lUBER 200 ") + RankColor.getNameColor(player), "");
            ChatManager.broadcastMessage(ChatColor.translateAlternateColorCodes('&',
                    "&c&lSTREAK! &7of &c200 &7kills by " + RankColor.getNameColor(player) + ChatColor.stripColor(player.getDisplayName())), player.getWorld());
        }else if (ClassInstances.streakData.getStreak(String.valueOf(player.getUniqueId())) == 300) {
            NametagEdit.getApi().setNametag(player, colorCode("&d&lUBER 300 ") + RankColor.getNameColor(player), "");
            ChatManager.broadcastMessage(ChatColor.translateAlternateColorCodes('&',
                    "&c&lSTREAK! &7of &c300 &7kills by " + RankColor.getNameColor(player) + ChatColor.stripColor(player.getDisplayName())), player.getWorld());
        }else if (ClassInstances.streakData.getStreak(String.valueOf(player.getUniqueId())) == 400) {
            NametagEdit.getApi().setNametag(player, colorCode("&d&lUBER 400 ") + RankColor.getNameColor(player), "");
            ChatManager.broadcastMessage(ChatColor.translateAlternateColorCodes('&',
                    "&c&lSTREAK! &7of &c400 &7kills by " + RankColor.getNameColor(player) + ChatColor.stripColor(player.getDisplayName())), player.getWorld());
        }

        UberRewardClaimDeath(player);

        // Player Reaches Uber 400

    }

    public static void UberRewardClaimDeath(Player player){
        if(Objects.equals(ClassInstances.megaStreakData.getMegaStreak(String.valueOf(player.getUniqueId())), "uber")){
            if (ClassInstances.streakData.getStreak(String.valueOf(player.getUniqueId())) >= 700){
                player.setMaxHealth(20);
                player.setHealth(player.getMaxHealth());
                player.removePotionEffect(PotionEffectType.SLOW);
                player.removePotionEffect(PotionEffectType.POISON);
                NametagEdit.getApi().setNametag(player, ChatManager.getLevelText(player) + RankColor.getNameColor(player), "");
                ClassInstances.streakData.setStreak(String.valueOf(player.getUniqueId()), 0);
                StashCore.safeGive(player, getUberDrop());
                Location loc = getSpawnLocation(player.getWorld());
                player.teleport(loc);
            }
        }

    }

    public static void StreakManager(Player player){
        if (Objects.equals(ClassInstances.megaStreakData.getMegaStreak(String.valueOf(player.getUniqueId())), "beastmode")){
            if(ClassInstances.streakData.getStreak(String.valueOf(player.getUniqueId())) > 49 && ClassInstances.streakData.getStreak(String.valueOf(player.getUniqueId())) < 51){
                NametagEdit.getApi().setNametag(player, colorCode("&a&lBEAST ") + RankColor.getNameColor(player), "");
            }
        }

        if (Objects.equals(ClassInstances.megaStreakData.getMegaStreak(String.valueOf(player.getUniqueId())), "hermit")){
            if(ClassInstances.streakData.getStreak(String.valueOf(player.getUniqueId())) > 49 && ClassInstances.streakData.getStreak(String.valueOf(player.getUniqueId())) < 51){
                NametagEdit.getApi().setNametag(player, colorCode("&9&lHERMIT ") + RankColor.getNameColor(player), "");
            }
        }

        if (Objects.equals(ClassInstances.megaStreakData.getMegaStreak(String.valueOf(player.getUniqueId())), "overdrive")){
            NametagEdit.getApi().setNametag(player, colorCode("&c&lOVER ") + RankColor.getNameColor(player), "");
        }

        if (Objects.equals(ClassInstances.megaStreakData.getMegaStreak(String.valueOf(player.getUniqueId())), "moon")){
            if(ClassInstances.streakData.getStreak(String.valueOf(player.getUniqueId())) > 99 && ClassInstances.streakData.getStreak(String.valueOf(player.getUniqueId())) < 101){

                NametagEdit.getApi().setNametag(player, colorCode("&b&lMOON ") + RankColor.getNameColor(player), "");

            }
        }else if (Objects.equals(ClassInstances.megaStreakData.getMegaStreak(String.valueOf(player.getUniqueId())), "uber")){
            Uber(player);
        }else if (Objects.equals(ClassInstances.megaStreakData.getMegaStreak(String.valueOf(player.getUniqueId())), "highlander")){
            if(ClassInstances.streakData.getStreak(String.valueOf(player.getUniqueId())) > 49 &&
                    ClassInstances.streakData.getStreak(String.valueOf(player.getUniqueId())) < 51){

                NametagEdit.getApi().setNametag(player, colorCode("&6&lHIGH ") + RankColor.getNameColor(player), "");
            }
        }
    }


}
