package me.alpha.kitpvp.CustomEvents;

import com.nametagedit.plugin.NametagEdit;
import me.alpha.kitpvp.ChatManager.ChatManager;
import me.alpha.kitpvp.ChatManager.RankColor;
import me.alpha.kitpvp.Data.ClassInstances;
import me.alpha.kitpvp.Objects.ReduxPlayerObject.ReduxPlayer;
import me.alpha.kitpvp.PitRemake.Perks.FishingRod;
import me.alpha.kitpvp.PitRemake.Scoreboard.ScoreboardCore;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.potion.PotionEffectType;

import java.util.List;

import static me.alpha.kitpvp.Data.ClassInstances.CombatTag;
import static me.alpha.kitpvp.Objects.ReduxPlayerObject.ReduxPlayerHandler.playerExists;
import static me.alpha.kitpvp.PitRemake.InventoryRefresher.RefreshCore.refreshInventory;
import static me.alpha.kitpvp.PitRemake.Locations.getSpawnLocation;
import static me.alpha.kitpvp.PitRemake.MysticWell.loreChecker.CheckEnchantOnBow;
import static me.alpha.kitpvp.PitRemake.PitBlob.PitBlobMap.deleteBlob;
import static me.alpha.kitpvp.utils.ColorUtil.colorCode;

public class ReduxSpawnEvent extends Event implements Cancellable {
    private static final HandlerList HANDLERS = new HandlerList();
    private final ReduxPlayer player;
    private boolean combatTag;
    private int timeLeft;
    private boolean isCancelled;

    public static HandlerList getHandlerList() {
        return HANDLERS;
    }

    public ReduxSpawnEvent(ReduxPlayer player) {
        this.player = player;
        this.isCancelled = false;
        this.timeLeft = 0;
        this.combatTag = false;

        if (CombatTag.containsKey(player.getPlayerUUID()) &&
                CombatTag.get(player.getPlayerUUID()) > System.currentTimeMillis()){
            // They still have time left on combat
            this.timeLeft = (int) ((CombatTag.get(player.getPlayerUUID()) - System.currentTimeMillis()) / 1000);
            this.combatTag = true;
        }else {
            CombatTag.put(player.getPlayerUUID(), 0L);
            this.timeLeft = (int) ((CombatTag.get(player.getPlayerUUID()) - System.currentTimeMillis()) / 1000);
            this.combatTag = false;
        }
    }

    public void onSpawn(){
        if(isCancelled) return;
        if(this.combatTag) {
            player.getPlayerObject().sendMessage(getCombatTagMessage());
            return;
        }

        ClassInstances.CombatTag.put(player.getPlayerUUID(), System.currentTimeMillis());
        ClassInstances.streakData.setStreak(player.getPlayerUUID(), 0);

        NametagEdit.getApi().setNametag(player.getPlayerObject(),
                ChatManager.getLevelText(player.getPlayerObject()) +
                        RankColor.getNameColor(player.getPlayerObject()), "");

        ScoreboardCore.CreateScore(player.getPlayerObject());

        FishingRod.getRod(player.getPlayerObject());FishingRod.getRod(player.getPlayerObject());

        if(ClassInstances.extraHearts.hasValue(player.getPlayerUUID())) player.getPlayerObject().
                    setMaxHealth(20+((Integer)ClassInstances.extraHearts.getValue(player.getPlayerUUID(), 1)*2));


        refreshInventory(player.getPlayerObject());
        deleteBlob(player.getPlayerObject());


        player.setMoonXP(0);
        player.removePotionEffect(PotionEffectType.WEAKNESS);
        player.getPlayerObject().teleport(getSpawnLocation(player.getPlayerObject().getWorld()));
    }

    @Override
    public HandlerList getHandlers() {
        return HANDLERS;
    }

    @Override
    public boolean isCancelled() {
        return this.isCancelled;
    }

    @Override
    public void setCancelled(boolean isCancelled) {
        this.isCancelled = isCancelled;
    }

    public ReduxPlayer getPlayer() {
        return this.player;
    }
    public boolean getCombatTag() {
        return this.combatTag;
    }
    public int getTimeLeft() {
        return this.timeLeft;
    }
    public String getCombatTagMessage(){return colorCode("&c&lHOLD UP! &7Can't /respawn while fighting (&c" + getTimeLeft() + "s &7left)");}

}