package me.alpha.kitpvp.PitRemake.Bounties;

import com.nametagedit.plugin.NametagEdit;
import me.alpha.kitpvp.ChatManager.ChatManager;
import me.alpha.kitpvp.ChatManager.RankColor;
import me.alpha.kitpvp.Data.ClassInstances;
import me.alpha.kitpvp.Data.GoldData;
import me.alpha.kitpvp.utils.CitizensHelper;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.HashMap;

public class Bounty {

    public static HashMap<String, Integer> BountiesMap = new HashMap<>();

    public static void BountyManager(Player player){
        String uuid = String.valueOf(player.getUniqueId());

        if(ClassInstances.streakData.getStreak(uuid) % 30 == 0){

            if(BountiesMap.containsKey(uuid)){
                if(BountiesMap.get(uuid) >= 20000){
                    BountiesMap.put(uuid, BountiesMap.get(uuid));
                }else{
                    ChatManager.broadcastMessage(ChatColor.translateAlternateColorCodes('&',"&6&lBOUNTY! &7bump &6&l1000g &7on " + RankColor.getNameColor(player) + player.getDisplayName() + "&7 for high streak"), player.getWorld());
                    BountiesMap.put(uuid, BountiesMap.get(uuid) + 1000);
                }
            }else{
                ChatManager.broadcastMessage(ChatColor.translateAlternateColorCodes('&',"&6&lBOUNTY! &7bump &6&l1000g &7on " + RankColor.getNameColor(player) + player.getDisplayName() + "&7 for high streak"), player.getWorld());
                BountiesMap.put(uuid, 1000);
            }

            NametagEdit.getApi().setSuffix(player, ChatColor.translateAlternateColorCodes('&', " &6&l" + BountiesMap.get(uuid) + "&6&lg "));
        }
    }

    public static void BountyClaimed(Player bountied, Player claimer){
        String uuid = String.valueOf(bountied.getUniqueId());

        if(CitizensHelper.isNPC(claimer)){
            if(BountiesMap.containsKey(uuid)){
                if(BountiesMap.get(uuid) > 0){
                    NametagEdit.getApi().setSuffix(bountied, "");
                    if(CitizensHelper.isNPC(claimer)){
                        ChatManager.broadcastMessage(ChatColor.translateAlternateColorCodes('&',"&6&lBOUNTY CLAIMED! " + "&7" + RankColor.getNameColor(claimer) + CitizensHelper.getNPC(claimer).getFullName() + "&7 killed " + RankColor.getNameColor(bountied) + bountied.getDisplayName() + "&7 for " + "&6&l" + BountiesMap.get(uuid) + "&6&lg"), bountied.getWorld());
                    }else{
                        ChatManager.broadcastMessage(ChatColor.translateAlternateColorCodes('&',"&6&lBOUNTY CLAIMED! " + "&7" + RankColor.getNameColor(claimer) + claimer.getDisplayName() + "&7 killed " + RankColor.getNameColor(bountied) + bountied.getDisplayName() + "&7 for " + "&6&l" + BountiesMap.get(uuid) + "&6&lg"), bountied.getWorld());
                    }
                    BountiesMap.put(uuid, 0);
                    return;
                }
            }

        }

        if(BountiesMap.containsKey(uuid)){
            if(BountiesMap.get(uuid) > 0){
                GoldData.addEconomy(String.valueOf(claimer.getUniqueId()), BountiesMap.get(uuid));
                NametagEdit.getApi().setSuffix(bountied, "");
                ChatManager.broadcastMessage(ChatColor.translateAlternateColorCodes('&',"&6&lBOUNTY CLAIMED! " + "&7" + RankColor.getNameColor(claimer) + claimer.getDisplayName() + "&7 killed " + RankColor.getNameColor(bountied) + bountied.getDisplayName() + "&7 for " + "&6&l" + BountiesMap.get(uuid) + "&6&lg"), bountied.getWorld());
                BountiesMap.put(uuid, 0);
                return;
            }
        }

    }
}