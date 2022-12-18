package me.alpha.kitpvp.PitRemake.Perks;


import me.alpha.kitpvp.CustomEvents.ReduxDamageEvent;
import me.alpha.kitpvp.CustomEvents.ReduxDeathEvent;
import me.alpha.kitpvp.Data.ClassInstances;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import static me.alpha.kitpvp.utils.CitizensHelper.isNPC;

public class PerkHandler implements Listener {

    @EventHandler
    public void PlayerDamageEvent(ReduxDamageEvent event){
        if(isNPC(event.getAttacker().getPlayerObject())) return;

        ClassInstances.strengthChaining.getPerkExecute().run(event);
        ClassInstances.goldenHeads.getPerkExecute().run(event);
        ClassInstances.dirty.getPerkExecute().run(event);
        ClassInstances.streaker.getPerkExecute().run(event);
        ClassInstances.vampire.getPerkExecute().run(event);
        ClassInstances.assistantStreaker.getPerkExecute().run(event);
        ClassInstances.gladiator.getPerkExecute().run(event);
    }

    @EventHandler
    public void PlayerDeathEvent(ReduxDeathEvent event){
        if(isNPC(event.getAttacker().getPlayerObject())) return;

        ClassInstances.strengthChaining.getPerkExecute().run(event);
        ClassInstances.goldenHeads.getPerkExecute().run(event);
        ClassInstances.dirty.getPerkExecute().run(event);
        ClassInstances.streaker.getPerkExecute().run(event);
        ClassInstances.vampire.getPerkExecute().run(event);
        ClassInstances.assistantStreaker.getPerkExecute().run(event);
        ClassInstances.gladiator.getPerkExecute().run(event);

    }

}
