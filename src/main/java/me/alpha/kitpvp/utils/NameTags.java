package me.alpha.kitpvp.utils;

import com.nametagedit.plugin.NametagEdit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class NameTags {
    public static void setNameTagSuffix(Player player, String string){
        NametagEdit.getApi().setSuffix(player,
                string);
    }

    public static void setNameTag(Player player, String string){
        NametagEdit.getApi().setNametag(player,
                string, "");
    }
}
