package me.alpha.kitpvp.PitRemake.MysticWell.BowEnchants;

import de.tr7zw.nbtapi.NBTItem;
import me.alpha.kitpvp.CustomEvents.ReduxBowEvent;
import me.alpha.kitpvp.CustomEvents.ReduxDamageEvent;
import me.alpha.kitpvp.KitPvP;
import me.alpha.kitpvp.Objects.ReduxPlayerObject.ReduxPlayer;
import me.alpha.kitpvp.PitRemake.MysticWell.EnchantRarity;
import me.alpha.kitpvp.PitRemake.MysticWell.PitEnchant;
import me.alpha.kitpvp.utils.CitizensHelper;
import org.bukkit.Material;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import static me.alpha.kitpvp.Objects.ReduxPlayerObject.ReduxPlayerHandler.playerExists;
import static me.alpha.kitpvp.utils.IntegerHelper.integerToRoman;

public class MegaLongBowLore extends PitEnchant {

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

            if(!item.hasKey("megalongbow")) return;

            int level = item.getInteger("megalongbow");

            if (player.getMlbCD()){
                player.setMlbCD();

                arrow.setCritical(true);
                arrow.setVelocity(shooter.getLocation().getDirection().multiply(2.90));
                arrow.setShooter(shooter);

                player.addPotionEffect(PotionEffectType.JUMP, 2,level+1);


                new BukkitRunnable() {
                    @Override
                    public void run() {
                        player.setMlbCD();
                    }
                }.runTaskLater(KitPvP.INSTANCE, 20L);
            }

        }
    }

    @Override
    public void init() {
        rarity = EnchantRarity.RARE;
    }

    @Override
    public String title(int level) {
        String tier = "";
        if (level > 1){tier += " " + integerToRoman(level);}

        return "&dRARE! &9Mega Longbow" + tier;
    }

    @Override
    public String lore(int level) {
        String tier = "";
        if (level > 1){tier += " " + integerToRoman(level);}

        String multiplier = String.valueOf(1+(Math.round(level*.5)));
        String jump = String.valueOf(integerToRoman(level+1));

        String lore = "&dRARE! &9Mega Longbow" + tier + "\n" +
                "&7One shot per second, this bow is\n" +
                "&7automatically fully drawn and\n" +
                "&7grants &aJump Boost "+jump+" &7("+multiplier+"s)" + "\n&7";

        return colorCode(lore);
    }
}
