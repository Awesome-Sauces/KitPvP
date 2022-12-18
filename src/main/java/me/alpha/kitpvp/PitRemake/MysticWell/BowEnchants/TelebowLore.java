package me.alpha.kitpvp.PitRemake.MysticWell.BowEnchants;

import de.tr7zw.nbtapi.NBTItem;
import me.alpha.kitpvp.CustomEvents.ReduxBowEvent;
import me.alpha.kitpvp.CustomEvents.ReduxDamageEvent;
import me.alpha.kitpvp.KitPvP;
import me.alpha.kitpvp.Objects.ReduxPlayerObject.ReduxPlayer;
import me.alpha.kitpvp.PitRemake.MysticWell.EnchantRarity;
import me.alpha.kitpvp.PitRemake.MysticWell.PitEnchant;
import me.alpha.kitpvp.utils.CitizensHelper;
import me.alpha.kitpvp.utils.Sounds;
import org.bukkit.Bukkit;
import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.scheduler.BukkitRunnable;

import static me.alpha.kitpvp.Objects.ReduxPlayerObject.ReduxPlayerHandler.playerExists;
import static me.alpha.kitpvp.utils.IntegerHelper.integerToRoman;

public class TelebowLore extends PitEnchant {

    @Override
    public void run(ReduxDamageEvent event) {
    }

    public void bow(ReduxBowEvent event, int level){

    }

    public void run(EntityShootBowEvent event){

        if(!(event.getEntity()instanceof Player)) return;

        Arrow arrow = (Arrow) event.getProjectile();
        Player shooter = (Player) event.getEntity();
        ReduxPlayer player = playerExists(shooter);

        if (!CitizensHelper.isNPC(player.getPlayerObject()) &&
                player.getPlayerObject().getItemInHand()!=null &&
                player.getPlayerObject().getItemInHand().getType()!= Material.AIR){
            NBTItem item = new NBTItem(player.getPlayerObject().getItemInHand());

            if(!item.hasKey("telebow")) return;

            int level = item.getInteger("telebow");

            if(shooter.isSneaking()) {
                new BukkitRunnable() {
                    @Override
                    public void run() {
                        if(arrow==null||arrow.isOnGround()||arrow.isDead()) this.cancel();
                        else for(int j = 0; j < 10; j++) arrow.getWorld().playEffect(arrow.getLocation(), Effect.POTION_SWIRL, 0, 30);
                    }
                }.runTaskTimer(KitPvP.INSTANCE, 0L, 1L);
            }

        }
    }

    public void runIt(ProjectileHitEvent event){

        if(!(event.getEntity().getShooter()instanceof Player)) return;

        Arrow arrow = (Arrow) event.getEntity();
        Player shooter = (Player) event.getEntity().getShooter();
        ReduxPlayer player = playerExists(shooter);

        if (!CitizensHelper.isNPC(shooter) &&
                shooter.getItemInHand()!=null &&
                shooter.getItemInHand().getType()!= Material.AIR){
            NBTItem item = new NBTItem(shooter.getItemInHand());

            if(!item.hasKey("telebow")) return;

            if(!shooter.isSneaking()) return;

            if(!getCooldown(playerExists(shooter))){
                Sounds.NO.play(shooter);
                return;
            }

            Location teleportLoc = arrow.getLocation().clone();
            teleportLoc.setYaw(-arrow.getLocation().getYaw());
            teleportLoc.setPitch(-arrow.getLocation().getPitch());

            player.getPlayerObject().teleport(teleportLoc);
        }
    }

    private boolean getCooldown(ReduxPlayer owner){
        if (owner.getTelebowCD()){
            owner.setTelebowCD();
            new BukkitRunnable() {
                @Override
                public void run() {
                    owner.setTelebowCD();
                }
            }.runTaskLater(KitPvP.INSTANCE, 600L);
            return true;
        }

        return false;
    }

    @Override
    public void init() {
        rarity = EnchantRarity.RARE;
    }

    @Override
    public String title(int level) {
        String tier = "";
        if (level > 1){tier += " " + integerToRoman(level);}

        return "&dRARE! &9Telebow" + tier;
    }

    @Override
    public String lore(int level) {
        String tier = "";
        if (level > 1){tier += " " + integerToRoman(level);}

        String multiplier = String.valueOf(90/level);

        String lore = "&dRARE! &9Telebow" + tier + "\n" +
                "&7Shoot a teleportation\n" +
                "&7arrow ("+multiplier+"s cooldown)" + "\n&7";

        return colorCode(lore);
    }
}
