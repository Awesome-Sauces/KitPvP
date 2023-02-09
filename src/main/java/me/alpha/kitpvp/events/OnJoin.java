package me.alpha.kitpvp.events;

import com.nametagedit.plugin.NametagEdit;
import me.alpha.kitpvp.ChatManager.ChatManager;
import me.alpha.kitpvp.ChatManager.RankColor;
import me.alpha.kitpvp.Data.ClassInstances;
import me.alpha.kitpvp.DataSave.DatabaseConnector;
import me.alpha.kitpvp.KitPvP;
import me.alpha.kitpvp.PitRemake.InventoryManager.NonPermanentItems;
import me.alpha.kitpvp.PitRemake.InventoryRefresher.RefreshCore;
import me.alpha.kitpvp.PitRemake.ItemStacks.itemManager;
import me.alpha.kitpvp.PitRemake.Scoreboard.ScoreboardCore;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffectType;

import static me.alpha.kitpvp.utils.CitizensHelper.isNPC;
import static me.alpha.kitpvp.utils.ColorUtil.colorCode;

public class OnJoin implements Listener {
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        ClassInstances.LobbyTransfer.put(player.getUniqueId(), true);
        Bukkit.getScheduler().scheduleSyncDelayedTask(KitPvP.INSTANCE, new Runnable() {
            @Override
            public void run() {
                ClassInstances.LobbyTransfer.put(player.getUniqueId(), false);
            }
        }, 20L);
        GiveChain(player);
        // Make a new component (Bungee API).
        TextComponent component = new TextComponent(TextComponent.fromLegacyText(colorCode("&e&lPIT! &fLatest update: &ev1.5.7 &bBug Patch! &7[&e&lCLICK&7]")));
        // Add a click event to the component.
        component.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/patchnotes"));

        // Send it!
        player.spigot().sendMessage(component);
        player.playSound(player.getLocation(), Sound.LEVEL_UP, 1.0F, 1.0F);
        ClassInstances.streakData.setStreak(String.valueOf(player.getUniqueId()), 0);
        ScoreboardCore.CreateScore(player);
        if(!isNPC(player)){
            //NametagEdit.getApi().setNametag(player, "§b§lMOON " + rank.getNameColor(player), "");
            NametagEdit.getApi().setNametag(player, ChatManager.getLevelText(player) + RankColor.getNameColor(player), "");
            //NametagEdit.getApi().setNametag(player, ChatEventApiGetLevelColor(player.getDisplayName(), String.valueOf(player.getUniqueId()))+ rank.getNameColor(player), "");
        }

        RefreshCore.refreshInventory(player);
    }

    public void GiveChain(Player player){
        Inventory inv = player.getInventory();

        try{
            if(CheckChainBoots(player)){
                player.getInventory().setBoots(itemManager.IronBoots);
            }

            if(CheckChainChestplate(player)){
                player.getInventory().setChestplate(itemManager.IronChestplate);
            }


            if(CheckIronLeggings(player)){
                player.getInventory().setLeggings(itemManager.IronLeggings);
            }

            if(CheckIronHelmet(player)){
                player.getInventory().setHelmet(itemManager.IronHelmet);
            }

            if (!player.getInventory().containsAtLeast(itemManager.Bow, 1)) {
                player.getInventory().addItem(itemManager.Bow);
            }

            if (!player.getInventory().containsAtLeast(itemManager.IronSword, 1)) {
                player.getInventory().addItem(itemManager.IronSword);
            }

            if (!player.getInventory().containsAtLeast(new ItemStack(Material.ARROW), 1)) {
                for(int i = 0; i < 32; i++){
                    player.getInventory().addItem(new ItemStack(Material.ARROW));
                }
            }
        }catch (Exception e){

        }

    }

    public boolean CheckIronHelmet(Player player){
        try{
            if(player.getInventory().getHelmet() == null){
                return true;
            }else if (player.getInventory().containsAtLeast(itemManager.DiamondHelmet, 1)) {
                return false;
            }

            return false;

        }catch (Exception e){
            return false;
        }
    }

    public boolean CheckChainBoots(Player player){
        try{
            if(player.getInventory().getBoots() == null){
                return true;
            }else if (player.getInventory().containsAtLeast(itemManager.DiamondBoots, 1)) {
                return false;
            }

            return false;

        }catch (Exception e){
            return false;
        }
    }

    public boolean CheckChainChestplate(Player player){
        try{
            if(player.getInventory().getChestplate() == null){
                return true;
            }else if (player.getInventory().containsAtLeast(itemManager.DiamondChestplate, 1)) {
                return false;
            }

            return false;

        }catch (Exception e){
            return false;
        }
    }

    public boolean CheckIronLeggings(Player player){
        try{
            if(player.getInventory().getLeggings() == null){
                return true;
            }else if (player.getInventory().containsAtLeast(itemManager.DiamondLeggings, 1)) {
                return false;
            }

            return false;

        }catch (Exception e){
            return false;
        }
    }
}
