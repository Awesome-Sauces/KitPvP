package me.alpha.kitpvp.utils;

import me.alpha.kitpvp.Objects.KitPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.HashMap;
import java.util.Map;

public class KitPlayerLoader implements Listener {
    private static final Map<Player, KitPlayer> KitPlayerStore = new HashMap<Player, KitPlayer>();

    public static KitPlayer getKitPlayer(Player player){

        if (!KitPlayerStore.containsKey(player)) KitPlayerStore.put(player, new KitPlayer());

        return KitPlayerStore.get(player);
    }

    @EventHandler
    public static void handleOfflinePlayers(PlayerQuitEvent event){
        KitPlayerStore.remove(event.getPlayer());
    }
}
