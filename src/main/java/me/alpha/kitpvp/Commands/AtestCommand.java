package me.alpha.kitpvp.Commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class AtestCommand  implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args) {
        if(!(sender instanceof Player)) {
            System.out.println("ERROR: Cannot use /atest command as console!");
            return true;
        }

        Player player = (Player) sender;

        if(cmd.getName().equalsIgnoreCase("atest")){
            return true;
        }

        return true;
    }
}