package me.alpha.kitpvp.utils;

import me.alpha.kitpvp.Commands.KitPvpCommand;
import me.alpha.kitpvp.KitPvP;

public class CommandRegistrar {
    public static void registerCommands(){

        KitPvpCommand kitPvpCommand = new KitPvpCommand();

        KitPvP.INSTANCE.getCommand("kitpvp").setExecutor(kitPvpCommand);
    }
}
