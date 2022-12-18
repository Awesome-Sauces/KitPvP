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
import org.bukkit.Material;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import static me.alpha.kitpvp.Objects.ReduxPlayerObject.ReduxPlayerHandler.playerExists;
import static me.alpha.kitpvp.utils.IntegerHelper.integerToRoman;

public class VolleyLore extends PitEnchant {

    @Override
    public void run(ReduxDamageEvent event) {
    }

    public void bow(ReduxBowEvent event){

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

            if(!item.hasKey("volley")) return;

            int level = item.getInteger("volley");

            new BukkitRunnable(){
                @Override
                public void run(){

                    if(player.shotCount>=level+2) {
                        player.shotCount=0;
                        this.cancel();
                    }

                    if(player.getVolleyCD()){
                        shootArrow(shooter,arrow);
                        player.setVolleyCD();
                        player.shotCount++;
                        new BukkitRunnable(){
                            @Override
                            public void run(){
                                player.setVolleyCD();
                            }
                        }.runTaskLater(KitPvP.INSTANCE,  1L);

                    }

                }
            }.runTaskTimer(KitPvP.INSTANCE,  1L, 1L);

        }
    }

    public void shootArrow(Player player, Arrow arrow){

        final double arrowVelo = arrow.getVelocity().length();

        Arrow volleyArrow = player.launchProjectile(Arrow.class);

        if(arrow.isCritical()) volleyArrow.setCritical(true);

        volleyArrow.setVelocity(player.getEyeLocation().getDirection().normalize().multiply(arrowVelo));

        Sounds.VOLLEY.play(player);
    }

    @Override
    public void init() {
        rarity = EnchantRarity.RARE;
    }

    @Override
    public String title(int level) {
        String tier = "";
        if (level > 1){tier += " " + integerToRoman(level);}

        return "&dRARE! &9Volley" + tier;
    }

    @Override
    public String lore(int level) {
        String tier = "";
        if (level > 1){tier += " " + integerToRoman(level);}

        String multiplier = String.valueOf(level+2);

        String lore = "&dRARE! &9Volley" + tier + "\n" +
                "&7Shoot &f"+multiplier+" arrows &7at once" + "\n&7";

        return colorCode(lore);
    }
}

