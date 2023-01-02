package me.alpha.kitpvp.PitRemake.Perks.items;

import de.tr7zw.nbtapi.NBTItem;
import me.alpha.kitpvp.KitPvP;
import me.alpha.kitpvp.Objects.ReduxPlayerObject.ReduxPlayer;
import me.alpha.kitpvp.PitRemake.ItemStacks.enchants;
import me.alpha.kitpvp.PitRemake.PitCommands.Stash.StashCore;
import net.minecraft.server.v1_8_R3.EntityHuman;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import static me.alpha.kitpvp.Objects.ReduxPlayerObject.ReduxPlayerHandler.playerExists;

public class SoupItem implements Listener {

    public void addEffects(Player player) {
        player.removePotionEffect(PotionEffectType.SPEED);
        player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 7*20, 0, true, true));

        player.removePotionEffect(PotionEffectType.INCREASE_DAMAGE);
        player.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 4*20, 0, true, true));
    }

    public void addHearts(Player player){

        CraftPlayer CraftPlayer = (CraftPlayer) player; //CraftBukkit
        EntityHuman HumanPlayer = CraftPlayer.getHandle(); //NMS

        double abs = HumanPlayer.getAbsorptionHearts();

        HumanPlayer.setAbsorptionHearts(Math.min((float) abs+2, 12));

        player.setHealth(Math.min(player.getMaxHealth(), player.getHealth()+3));

    }

    @EventHandler
    public void onConsume(PlayerInteractEvent event) {
        if(event.getItem()!=null &&
                !event.getItem().getType().equals(Material.AIR) &&
                event.getItem().getType().equals(Material.MUSHROOM_SOUP)){
            Player player = event.getPlayer();
            ReduxPlayer reduxPlayer = playerExists(player);

            if(event.getAction()==Action.RIGHT_CLICK_AIR) return;

                if (reduxPlayer.getSoupCD()){
                    reduxPlayer.setSoupCD();


                    player.getInventory().remove(enchants.soup);

                    addEffects(player);
                    addHearts(player);
                    new BukkitRunnable() {
                        @Override
                        public void run() {
                            reduxPlayer.setSoupCD();
                        }
                    }.runTaskLater(KitPvP.INSTANCE, 10L);
                }

            }

        }
    }

