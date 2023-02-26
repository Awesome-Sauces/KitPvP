package me.alpha.kitpvp.Bot;

import me.alpha.kitpvp.KitPvP;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

public class HunterRunnable {
    boolean isCanceled = false;

    public HunterRunnable() {
    }

    public void code() {
    }

    public void cancel() {
        this.isCanceled = true;
    }

    public BukkitTask execute(int delay) {
        return (new BukkitRunnable() {
            public void run() {
                if (isCanceled) {
                    this.cancel();
                }

                code();
            }
        }).runTaskTimer(KitPvP.INSTANCE, (long)delay, (long)delay);
    }
}
