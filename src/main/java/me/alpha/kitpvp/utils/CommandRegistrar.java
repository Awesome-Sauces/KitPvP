package me.alpha.kitpvp.utils;

import me.alpha.kitpvp.Commands.KitPvpCommand;
import me.alpha.kitpvp.KitPvP;
import me.alpha.kitpvp.PitRemake.PitCommands.PitCommands;
import me.alpha.kitpvp.PitRemake.PitCommands.Stash.StashCommands;

public class CommandRegistrar {
    public static void registerCommands(){

        KitPvpCommand kitPvpCommand = new KitPvpCommand();
        PitCommands pitCommands = new PitCommands();
        StashCommands stashCommands = new StashCommands();

        KitPvP.INSTANCE.getCommand("kitpvp").setExecutor(kitPvpCommand);


        KitPvP.INSTANCE.getCommand("stash").setExecutor(stashCommands);

        KitPvP.INSTANCE.getCommand("refresh").setExecutor(pitCommands);
        KitPvP.INSTANCE.getCommand("cookiemonster").setExecutor(pitCommands);
        KitPvP.INSTANCE.getCommand("crategive").setExecutor(pitCommands);
        KitPvP.INSTANCE.getCommand("enchantPant").setExecutor(pitCommands);
        KitPvP.INSTANCE.getCommand("activateBooster").setExecutor(pitCommands);
        KitPvP.INSTANCE.getCommand("purchaseDyes").setExecutor(pitCommands);
        KitPvP.INSTANCE.getCommand("show").setExecutor(pitCommands);
        KitPvP.INSTANCE.getCommand("repairs").setExecutor(pitCommands);
        KitPvP.INSTANCE.getCommand("hub").setExecutor(pitCommands);
        KitPvP.INSTANCE.getCommand("veloCheck").setExecutor(pitCommands);
        KitPvP.INSTANCE.getCommand("play").setExecutor(pitCommands);
        KitPvP.INSTANCE.getCommand("oof").setExecutor(pitCommands);
        KitPvP.INSTANCE.getCommand("mkBoard").setExecutor(pitCommands);
        KitPvP.INSTANCE.getCommand("makeMonersRankers").setExecutor(pitCommands);
        KitPvP.INSTANCE.getCommand("rBoard").setExecutor(pitCommands);
        KitPvP.INSTANCE.getCommand("spawn").setExecutor(pitCommands);
        KitPvP.INSTANCE.getCommand("spawn").setExecutor(pitCommands);
        KitPvP.INSTANCE.getCommand("all").setExecutor(pitCommands);
        KitPvP.INSTANCE.getCommand("feed").setExecutor(pitCommands);
        KitPvP.INSTANCE.getCommand("shop").setExecutor(pitCommands);
        KitPvP.INSTANCE.getCommand("kit").setExecutor(pitCommands);
        KitPvP.INSTANCE.getCommand("streak").setExecutor(pitCommands);
        KitPvP.INSTANCE.getCommand("balance").setExecutor(pitCommands);
        KitPvP.INSTANCE.getCommand("KillMessageToggle").setExecutor(pitCommands);
        KitPvP.INSTANCE.getCommand("prestige").setExecutor(pitCommands);
        KitPvP.INSTANCE.getCommand("prestiges").setExecutor(pitCommands);
        KitPvP.INSTANCE.getCommand("mega").setExecutor(pitCommands);
        KitPvP.INSTANCE.getCommand("gold").setExecutor(pitCommands);
        KitPvP.INSTANCE.getCommand("checkPants").setExecutor(pitCommands);
        KitPvP.INSTANCE.getCommand("well").setExecutor(pitCommands);
        KitPvP.INSTANCE.getCommand("getXp").setExecutor(pitCommands);
        KitPvP.INSTANCE.getCommand("pants").setExecutor(pitCommands);
        KitPvP.INSTANCE.getCommand("malding").setExecutor(pitCommands);
        KitPvP.INSTANCE.getCommand("damage").setExecutor(pitCommands);
        KitPvP.INSTANCE.getCommand("renown").setExecutor(pitCommands);
        KitPvP.INSTANCE.getCommand("booster").setExecutor(pitCommands);
        KitPvP.INSTANCE.getCommand("atest").setExecutor(pitCommands);
        KitPvP.INSTANCE.getCommand("view").setExecutor(pitCommands);
        KitPvP.INSTANCE.getCommand("patchnotes").setExecutor(pitCommands);


    }
}
