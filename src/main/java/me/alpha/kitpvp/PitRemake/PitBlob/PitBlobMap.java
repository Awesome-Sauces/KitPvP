package me.alpha.kitpvp.PitRemake.PitBlob;

import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Slime;
import org.bukkit.scheduler.BukkitTask;

import java.util.HashMap;

public class PitBlobMap {

    public static HashMap<Player, Slime> blob = new HashMap<>();
    public static HashMap<Player, Integer> blobStreak = new HashMap<>();
    private static HashMap<Player, Integer> blobHealth = new HashMap<>();
    public static HashMap<Player, BukkitTask> runnable = new HashMap<>();


    public static void addBlob(Player player, Slime slime){
        blob.put(player, slime);

        if(!blob.containsKey(player) && !blobStreak.containsKey(player)) return;
    }

    public static Player getPlayerFromBlob(Slime slime){
        for(Player player : blob.keySet()) if(blob.get(player).equals(slime)) return player;

        return null;
    }

    public static void addBlobStreak(Player player){
        if(!blobStreak.containsKey(player)){
            blobStreak.put(player, 1);
        }else{
            blobStreak.put(player, blobStreak.get(player) + 1);
        }
    }

    public static int getBlobHealth(Player player){
        if(blobHealth.containsKey(player)) return blobHealth.get(player);
        return 0;
    }

    public static void addBlobHealth(Player player){
        if(!blobHealth.containsKey(player)){
            blobHealth.put(player, 20);
        }else{
            blobHealth.put(player, Math.min(20, blobHealth.get(player) + 1));
        }
    }

    public static void removeBlobHealth(Player player){
        if(blobHealth.containsKey(player)) blobHealth.put(player, blobHealth.get(player) - 1);
    }

    public static void resizeBlob(Player player){
        if(blob.containsKey(player)){
            int blobSize = blob.get(player).getSize();

            if(blobSize>=5) return;

            if(blobStreak.containsKey(player)){
                int streak = blobStreak.get(player);
                if((streak % 5) == 0) blob.get(player).setSize(blobSize+1);
            }

        }
    }

    public static void deleteBlob(Player player){
        if(!blob.containsKey(player) && !blobStreak.containsKey(player)) return;

        Slime slime = blob.get(player);

        //if (Bukkit.getScheduler().isCurrentlyRunning(runnable.get(player).getTaskId())) runnable.get(player).cancel();
        runnable.remove(player);

        blob.remove(player);
        blobStreak.remove(player);

        slime.remove();



    }

    public static void blobTick(Player player){
        if(!blob.containsKey(player)) createBlob(player);

        addBlobStreak(player);
        addBlobHealth(player);

        resizeBlob(player);

    }

    private static void createBlob(Player player){
        Slime slime = (Slime) player.getLocation().getWorld().spawnEntity(player.getLocation(), EntityType.SLIME);
        slime.setRemoveWhenFarAway(false);
        slime.setSize(1);


        addBlob(player, slime);
        addBlobHealth(player);
        addBlobStreak(player);
        resizeBlob(player);

        /*
        BukkitTask task = new BukkitRunnable() {
            @Override
            public void run() {

                if(player.getLocation().getY() >= getSpawnProtection()) deleteBlob(player);

                for(Entity entity : slime.getNearbyEntities(1, 1, 1)){
                    LivingEntity livingEntity = (LivingEntity) entity;


                    if(livingEntity instanceof Player && isNPC((Player) livingEntity)){
                        Player living = (Player) livingEntity;

                        getNPC(living);

                        //getNPC(living).teleport(getBotSpawnLocation(), PlayerTeleportEvent.TeleportCause.PLUGIN);

                        living.setHealth(living.getMaxHealth());

                        KillMan(player, living);

                    }

                }

            }
        }.runTaskTimer(economy.getPlugin(), 50, 50);

        runnable.put(player, task);
        */


    }

}
