package me.alpha.kitpvp.utils;

import me.alpha.kitpvp.ChatManager.ChatManager;
import me.alpha.kitpvp.Commands.KitPvpCommand;
import me.alpha.kitpvp.KitPvP;
import me.alpha.kitpvp.PitRemake.Scoreboard.ScoreboardCore;
import me.alpha.kitpvp.events.MainDamageEvent;

public class EventRegistrar {
    public static void registerEvents(){

        KitPvP.INSTANCE.getServer().getPluginManager().registerEvents(new ChatManager(), KitPvP.INSTANCE);
        KitPvP.INSTANCE.getServer().getPluginManager().registerEvents(new ScoreboardCore(), KitPvP.INSTANCE);
        KitPvP.INSTANCE.getServer().getPluginManager().registerEvents(new MainDamageEvent(), KitPvP.INSTANCE);

    }
}
