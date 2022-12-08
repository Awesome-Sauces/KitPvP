package me.alpha.kitpvp;

import me.alpha.kitpvp.Data.XpData;
import me.alpha.kitpvp.events.MainDamageEvent;
import me.alpha.kitpvp.utils.CommandRegistrar;
import me.alpha.kitpvp.utils.EventRegistrar;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.Map;

public class KitPvP extends JavaPlugin {

    public static Map<String, Long> combatTag = new HashMap<String, Long>();

    public static KitPvP INSTANCE;
    @Override
    public void onEnable() {

        // Register Commands
        CommandRegistrar.registerCommands();

        // Register Events
        EventRegistrar.registerEvents();

        // XP Amounts
        XpData.XpLevelCalculation();

    }

    @Override
    public void onDisable() {

    }


}
