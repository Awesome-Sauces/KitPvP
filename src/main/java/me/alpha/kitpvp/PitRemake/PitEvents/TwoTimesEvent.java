package me.alpha.kitpvp.PitRemake.PitEvents;

import com.gmail.filoghost.holographicdisplays.api.Hologram;
import com.gmail.filoghost.holographicdisplays.api.HologramsAPI;
import me.alpha.kitpvp.KitPvP;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.inventivetalent.bossbar.BossBar;
import org.inventivetalent.bossbar.BossBarAPI;

import static me.alpha.kitpvp.PitRemake.Locations.getEventNotifyLocation;
import static me.alpha.kitpvp.utils.ColorUtil.colorCode;

public class TwoTimesEvent {

    private static Hologram hologram = HologramsAPI.createHologram(KitPvP.INSTANCE, getEventNotifyLocation(Bukkit.getWorld("world")));
    private static Hologram hologram2 = HologramsAPI.createHologram(KitPvP.INSTANCE, getEventNotifyLocation(Bukkit.getWorld("lobby")));
    private static Hologram hologram3 = HologramsAPI.createHologram(KitPvP.INSTANCE, getEventNotifyLocation(Bukkit.getWorld("lobby2")));
    public static int twoTimesEvent = 1;

    public static void twoTimesEvent(){
        /*for (Player player : Bukkit.getOnlinePlayers()){
            barUtil.setBar(player,  "§d§lMINOR EVENT!" + ChatColor.YELLOW + " 2x " + ChatColor.AQUA + "XP" + ChatColor.LIGHT_PURPLE + "/" + ChatColor.GOLD + "gold" + ChatColor.YELLOW + "!", 100);
        }

         */

        for(Player player : Bukkit.getOnlinePlayers()){
            BossBar bossBar = BossBarAPI.addBar(player,
                    new TextComponent(colorCode("&d&lMINOR EVENT! &e2x in &e&lPit Area")),
                    BossBarAPI.Color.GREEN,
                    BossBarAPI.Style.NOTCHED_6,
                    1.0f);
        }

        Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', "&d&lMINOR EVENT! &e2x in &e&lPit Area"));

        if(hologram.isDeleted()){
            refreshHoloGram();
        }

        hologram.clearLines();

        hologram.appendTextLine(ChatColor.translateAlternateColorCodes('&', "&d&lMINOR EVENT!"));
        hologram.appendTextLine(ChatColor.translateAlternateColorCodes('&', "&e2x &bXP &d/&6gold&e!"));
        hologram.appendTextLine(ChatColor.translateAlternateColorCodes('&', "&ein Pit Area"));

        if(hologram2.isDeleted()){
            refreshHoloGram();
        }

        hologram2.clearLines();

        hologram2.appendTextLine(ChatColor.translateAlternateColorCodes('&', "&d&lMINOR EVENT!"));
        hologram2.appendTextLine(ChatColor.translateAlternateColorCodes('&', "&e2x &bXP &d/&6gold&e!"));
        hologram2.appendTextLine(ChatColor.translateAlternateColorCodes('&', "&ein Pit Area"));

        if(hologram3.isDeleted()){
            refreshHoloGram();
        }

        hologram3.clearLines();

        hologram3.appendTextLine(ChatColor.translateAlternateColorCodes('&', "&d&lMINOR EVENT!"));
        hologram3.appendTextLine(ChatColor.translateAlternateColorCodes('&', "&e2x &bXP &d/&6gold&e!"));
        hologram3.appendTextLine(ChatColor.translateAlternateColorCodes('&', "&ein Pit Area"));

        twoTimesEvent = 2;

    }

    public static void handleTwoEvent(){
        if(!hologram.isDeleted() && !hologram2.isDeleted() && !hologram3.isDeleted()){
            endTwoTimes();
        }else{
            twoTimesEvent();
        }
    }

    public static void endTwoTimes(){
        /*for (Player player: Bukkit.getOnlinePlayers()){
            barUtil.removeBar(player);
        }

         */

        for(Player player : Bukkit.getOnlinePlayers()){
            BossBarAPI.removeAllBars(player);
        }

        Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', "&d&lMINOR EVENT! &e2x in &e&lPit Area &c&lENDED"));
        hologram.delete();
        hologram2.delete();
        hologram3.delete();

        twoTimesEvent = 1;

    }

    public static void refreshHoloGram(){
        hologram = HologramsAPI.createHologram(KitPvP.INSTANCE, getEventNotifyLocation(Bukkit.getWorld("world")));
        hologram2 = HologramsAPI.createHologram(KitPvP.INSTANCE, getEventNotifyLocation(Bukkit.getWorld("lobby")));
        hologram3 = HologramsAPI.createHologram(KitPvP.INSTANCE, getEventNotifyLocation(Bukkit.getWorld("lobby2")));
    }
}
