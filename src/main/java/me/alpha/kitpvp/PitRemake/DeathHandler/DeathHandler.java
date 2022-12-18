package me.alpha.kitpvp.PitRemake.DeathHandler;

import me.alpha.kitpvp.CustomEvents.ReduxDeathEvent;
import me.alpha.kitpvp.PitRemake.RenownShop.CookieMonster.MonsterHandler;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import static me.alpha.kitpvp.Objects.ReduxPlayerObject.ReduxPlayerHandler.playerExists;
import static me.alpha.kitpvp.utils.CitizensHelper.getNPC;
import static me.alpha.kitpvp.utils.PacketTitles.PacketTitle.sendKillBar;

public class DeathHandler {
    public static void KillMan(Player attacker, Player defender){

        MonsterHandler.handleMonsterDeath(attacker, getNPC(defender));

        sendKillBar(attacker, defender);
        //processKill(playerExists(attacker), playerExists(defender));
        ReduxDeathEvent mainEvent = new ReduxDeathEvent(playerExists(attacker), playerExists(defender));
        Bukkit.getPluginManager().callEvent(mainEvent);
        if (!mainEvent.isCancelled()) {
            mainEvent.run();
            mainEvent.setCancelled(true);
        }
    }
}
