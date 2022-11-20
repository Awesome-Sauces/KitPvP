package me.alpha.kitpvp;

import me.alpha.kitpvp.utils.CommandRegistrar;
import org.bukkit.plugin.java.JavaPlugin;

public class KitPvP extends JavaPlugin {

    public static KitPvP INSTANCE;
    @Override
    public void onEnable() {

        // Register Commands
        CommandRegistrar.registerCommands();

    }

    @Override
    public void onDisable() {

    }


}
