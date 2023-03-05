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

    public static void Uber(Player player){
        int streak = ClassInstances.streakData.getStreak(player.getUniqueId());

        if(streak >= 100 && streak % 100 == 0){
            ChatManager.broadcastMessage(ChatColor.translateAlternateColorCodes('&',
                    "&c&lSTREAK! &7of &c"+streak+" &7kills by " + RankColor.getNameColor(player) +
                            ChatColor.stripColor(player.getDisplayName())), player.getWorld());

            NametagEdit.getApi().setNametag(player, colorCode("&d&lUBER "+streak+" ")
                    + RankColor.getNameColor(player), "");
        }

        UberRewardClaimDeath(player);

    }

    public static void UberRewardClaimDeath(Player player){
        if(Objects.equals(ClassInstances.megaStreakData.getMegaStreak(String.valueOf(player.getUniqueId())), "uber")){
            if (ClassInstances.streakData.getStreak(String.valueOf(player.getUniqueId())) >= 500){

                if(ClassInstances.extraHearts.hasValue(player.getUniqueId().toString()) &&
                ClassInstances.extraHearts.getDouble(player.getUniqueId().toString())>0) {
                    player.setMaxHealth(20 + ((Integer) ClassInstances.
                            extraHearts.getValue(player.getUniqueId().toString(), 1) * 2));
                }else player.setMaxHealth(20);

                player.setHealth(player.getMaxHealth());
                player.removePotionEffect(PotionEffectType.SLOW);
                player.removePotionEffect(PotionEffectType.POISON);

                NametagEdit.getApi().setNametag(player, ChatManager.getLevelText(player) +
                        RankColor.getNameColor(player), "");

                ClassInstances.streakData.setStreak(player.getUniqueId().toString(), 0);

                StashCore.safeGive(player, getUberDrop());
                player.teleport(getSpawnLocation(player.getWorld()));
            }
        }

    }

    public static void StreakManager(Player player){
        if (Objects.equals(ClassInstances.megaStreakData.getMegaStreak(String.valueOf(player.getUniqueId())), "beastmode")){
            if(ClassInstances.streakData.getStreak(player.getUniqueId().toString()) == 50){
                NametagEdit.getApi().setNametag(player, colorCode("&a&lBEAST ") + RankColor.getNameColor(player), "");
            }
        }

        if (Objects.equals(ClassInstances.megaStreakData.getMegaStreak(String.valueOf(player.getUniqueId())), "hermit")){
            if(ClassInstances.streakData.getStreak(player.getUniqueId().toString()) == 50){
                NametagEdit.getApi().setNametag(player, colorCode("&9&lHERMIT ") + RankColor.getNameColor(player), "");
            }
        }

        if (Objects.equals(ClassInstances.megaStreakData.getMegaStreak(player.getUniqueId().toString()), "overdrive")){
            if(ClassInstances.streakData.getStreak(player.getUniqueId().toString()) == 50){
                NametagEdit.getApi().setNametag(player, colorCode("&c&lOVER ") + RankColor.getNameColor(player), "");
            }
        }

        if (Objects.equals(ClassInstances.megaStreakData.getMegaStreak(player.getUniqueId().toString()), "moon")){
            if(ClassInstances.streakData.getStreak(player.getUniqueId().toString()) == 100){
                NametagEdit.getApi().setNametag(player, colorCode("&b&lMOON ") + RankColor.getNameColor(player), "");
            }
        }else if (Objects.equals(ClassInstances.megaStreakData.getMegaStreak(player.getUniqueId().toString()), "uber")){
            Uber(player);
        }else if (Objects.equals(ClassInstances.megaStreakData.getMegaStreak(player.getUniqueId().toString()), "highlander")){
            if(ClassInstances.streakData.getStreak(String.valueOf(player.getUniqueId())) == 50){
                NametagEdit.getApi().setNametag(player, colorCode("&6&lHIGH ") + RankColor.getNameColor(player), "");
            }
        }
    }


}
