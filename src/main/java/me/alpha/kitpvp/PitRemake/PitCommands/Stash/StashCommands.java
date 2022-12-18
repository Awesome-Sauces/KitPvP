package me.alpha.kitpvp.PitRemake.PitCommands.Stash;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class StashCommands implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args) {
        if(cmd.getName().equalsIgnoreCase("stash") &&
        sender instanceof Player){
            Player player = (Player) sender;

            StashCore.claimStash(player);
            return true;
        }

        return true;
    }
}
