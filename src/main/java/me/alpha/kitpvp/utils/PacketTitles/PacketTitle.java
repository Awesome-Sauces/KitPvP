package me.alpha.kitpvp.utils.PacketTitles;

import me.alpha.hunter.api.HunterAPI;
import me.alpha.kitpvp.ChatManager.RankColor;
import me.alpha.kitpvp.CustomEvents.ReduxDamageEvent;
import me.alpha.kitpvp.utils.CitizensHelper;
import net.minecraft.server.v1_8_R3.EntityPlayer;
import net.minecraft.server.v1_8_R3.IChatBaseComponent;
import net.minecraft.server.v1_8_R3.PacketPlayOutChat;
import net.minecraft.server.v1_8_R3.PacketPlayOutTitle;
import org.bukkit.ChatColor;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

import java.text.DecimalFormat;
import java.util.Objects;

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

    public static void onAttackHealthBar(ReduxDamageEvent event) {
        if(CitizensHelper.isNPC(event.getAttacker().getPlayerObject())) return;

        Player attacker = event.getAttacker().getPlayerObject();
        Player defender = event.getDefender().getPlayerObject();

//        double maxHealth = defender.getMaxHealth() / 2;
//        double currentHealth = defender.getHealth() / 2;
//        double damageTaken = attackEvent.event.getFinalDamage() / 2;
//
//
//        Bukkit.broadcastMessage(String.valueOf("Max Health: " + maxHealth));
//        Bukkit.broadcastMessage(String.valueOf("Current Health: " + currentHealth));
//        Bukkit.broadcastMessage(String.valueOf("Damage Taken: " + damageTaken));
//
//        StringBuilder output = new StringBuilder();
//
//
//
//        for (int i = 0; i < Math.floor(currentHealth - damageTaken); i++) {
//            output.append(ChatColor.DARK_RED).append("\u2764");
//        }
//
//        for (int i = 0; i < Math.ceil(damageTaken); i++) {
//            output.append(ChatColor.RED).append("\u2764");
//        }
//
//        for (int i = 0; i < maxHealth - (Math.floor(currentHealth - damageTaken) + Math.ceil(damageTaken)); i++) {
//            output.append(ChatColor.BLACK).append("\u2764");
//        }
//
//        Misc.sendActionBar(attacker, output.toString());

        EntityPlayer entityPlayer = null;
        if(!CitizensHelper.isNPC(defender)) entityPlayer = ((CraftPlayer) defender).getHandle();

        int roundedDamageTaken = ((int) event.getBukkitEvent().getFinalDamage()) / getNum(defender);

        int originalHealth = ((int) defender.getHealth()) / getNum(defender);
        int maxHealth = ((int) defender.getMaxHealth()) / getNum(defender);

        int result = Math.max(originalHealth - roundedDamageTaken, 0);

        if((defender.getHealth() - event.getBukkitEvent().getFinalDamage()) % 2 < 1 && event.getBukkitEvent().getFinalDamage() > 1)
            roundedDamageTaken++;

        if(result == 0) {
            roundedDamageTaken = 0;

            for(int i = 0; i < originalHealth; i++) {
                roundedDamageTaken++;
            }
        }


        StringBuilder output = new StringBuilder();

        if(CitizensHelper.isNPC(defender)){
            output.append(CitizensHelper.getNPC(defender).getName()).append(" ");
        }else{
            String colorPlayer = RankColor.getNameColor(defender);
            output.append(colorPlayer).append(defender.getDisplayName()).append(" ");
        }

        for(int i = 0; i < Math.max(originalHealth - roundedDamageTaken, 0); i++) {
            output.append(ChatColor.DARK_RED).append("\u2764");
        }

        if(!CitizensHelper.isNPC(defender)) {
            for(int i = 0; i < roundedDamageTaken - (int) Objects.requireNonNull(entityPlayer).getAbsorptionHearts() / getNum(defender); i++) {
                output.append(ChatColor.RED).append("\u2764");
            }
        } else {
            for(int i = 0; i < roundedDamageTaken; i++) {
                output.append(ChatColor.RED).append("\u2764");
            }
        }


        for(int i = originalHealth; i < maxHealth; i++) {
            output.append(ChatColor.BLACK).append("\u2764");
        }

        if(!CitizensHelper.isNPC(defender)) {
            for(int i = 0; i < (int) Objects.requireNonNull(entityPlayer).getAbsorptionHearts() / getNum(defender); i++) {
                output.append(ChatColor.YELLOW).append("\u2764");
            }
        }

        DecimalFormat df = new DecimalFormat("#.000");
        float number = Math.abs(Float.parseFloat(df.format(event.getBukkitEvent().getFinalDamage())));
        output.append(" ").append(ChatColor.RED).append(number).append("HP");

        sendActionBar(attacker, output.toString());
    }

    public static int getNum(LivingEntity entity) {
        return Math.max(1, (int) (2 * (entity.getMaxHealth() / 20)));
    }
}
