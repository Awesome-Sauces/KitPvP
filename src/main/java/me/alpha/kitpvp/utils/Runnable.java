package me.alpha.kitpvp.utils;

import me.alpha.kitpvp.KitPvP;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

public class Runnable  {

    boolean isCanceled = false;
    public Runnable(){

    }

    public void code(){

    }

    public void cancel(){
        this.isCanceled = true;
    }

    public BukkitTask execute(int delay){
        // Runnable for Jumping
        return new BukkitRunnable() {
            @Override
            public void run(){
                if(isCanceled) this.cancel();
                code();
            }
        }.runTaskTimer(KitPvP.INSTANCE, delay, delay);
    }
}