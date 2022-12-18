package me.alpha.kitpvp.utils.PacketTitles;

import me.alpha.kitpvp.ChatManager.RankColor;
import me.alpha.kitpvp.utils.CitizensHelper;
import net.minecraft.server.v1_8_R3.IChatBaseComponent;
import net.minecraft.server.v1_8_R3.PacketPlayOutChat;
import net.minecraft.server.v1_8_R3.PacketPlayOutTitle;
import org.bukkit.ChatColor;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

import java.text.DecimalFormat;

import static me.alpha.kitpvp.utils.ColorUtil.colorCode;

public class PacketTitle {
    public static void sendTitle(Player player, String string){
        PacketPlayOutTitle title = new PacketPlayOutTitle(PacketPlayOutTitle.EnumTitleAction.TITLE,
                IChatBaseComponent.ChatSerializer.a("{\"text\":\""+string+"\"}"),100,20,20);
        ((CraftPlayer) player).getHandle().playerConnection.sendPacket(title);
    }

    public static void sendActionBar(Player player, String string){
        PacketPlayOutChat packet = new PacketPlayOutChat(IChatBaseComponent.ChatSerializer.a("{\"text\":\""+colorCode(string)+"\"}"), (byte) 2);
        ((CraftPlayer) player).getHandle().playerConnection.sendPacket(packet);
    }

    public static void sendHealthBar(EntityDamageByEntityEvent event){

        Player player = (Player) event.getDamager();

        if(CitizensHelper.isNPC(player)) return;

        Player defender = (Player) event.getEntity();
        String colorPlayer = RankColor.getNameColor(defender);
        StringBuilder health = new StringBuilder();
        health.append(" ");

        double defenderHealth = (double) Math.round(defender.getHealth());
        int red_heart = Math.min((int) event.getFinalDamage()/2, 10);
        int dark_red_heart = Math.min((int) (defenderHealth - event.getFinalDamage())  / 2, 10);
        int gray_heart = Math.min((int) (((defenderHealth - defender.getMaxHealth())) - dark_red_heart) / 2, 10);

        if (gray_heart == 0){
            gray_heart = (int) Math.abs((int) defender.getMaxHealth() - defenderHealth);
        }
        for (int i = 0; i < Math.abs(dark_red_heart); i++) {
            health.append(ChatColor.translateAlternateColorCodes('&', "&4\u2764"));
        }

        for (int i = 0; i < Math.abs(red_heart); i++) {
            health.append(ChatColor.translateAlternateColorCodes('&', "&c\u2764"));
        }

        for (int i = 0; i < Math.abs(gray_heart); i++) {
            health.append(ChatColor.translateAlternateColorCodes('&', "&0\u2764"));
        }

        DecimalFormat df = new DecimalFormat("#.000");
        float number = Float.parseFloat(df.format(event.getFinalDamage()));

        if(CitizensHelper.isNPC(defender)){
            sendActionBar(player, colorPlayer + CitizensHelper.getNPC(defender).getName() + health + " &c" + number + "HP");
        }else{
            sendActionBar(player, colorPlayer + defender.getDisplayName() + health + " &c" + number + "HP");
        }
    }

    public static void sendKillBar(Player player, Player defender){
        String health = colorCode(" &a&lKILL!");
        String colorPlayer = RankColor.getNameColor(defender);

        if(CitizensHelper.isNPC(defender)){
            sendActionBar(player, colorPlayer + CitizensHelper.getNPC(defender).getName() + health);
        }else{
            sendActionBar(player, colorPlayer + defender.getDisplayName() + health);
        }
    }
}
