package me.alpha.kitpvp.Commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class KitPvpCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args) {
        if(!(sender instanceof Player)) {
            System.out.println("ERROR: Cannot use /kitpvp command as console!");
            return true;
        }

        Player player = (Player) sender;

        if(cmd.getName().equalsIgnoreCase("kitpvp")){
            return true;
        }

        return true;
    }
}
