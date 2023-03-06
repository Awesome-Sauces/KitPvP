package me.alpha.kitpvp.PitRemake.MysticWell.New.BowEnchants;

import me.alpha.kitpvp.CustomEvents.ArmorEvents.ArmorEquipEvent;
import me.alpha.kitpvp.CustomEvents.ReduxBowEvent;
import me.alpha.kitpvp.CustomEvents.ReduxDamageEvent;
import me.alpha.kitpvp.CustomEvents.ReduxDeathEvent;
import me.alpha.kitpvp.PitRemake.MysticWell.New.EnchantGeneral;
import me.alpha.kitpvp.PitRemake.MysticWell.New.MysticEnchant;
import me.alpha.kitpvp.PitRemake.MysticWell.New.MysticType;
import me.alpha.kitpvp.PitRemake.MysticWell.New.RegisterEvent;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.event.entity.ProjectileHitEvent;

public class SprintDrainEnchant extends MysticEnchant {

    public SprintDrainEnchant () {
        this.setRefID("sprintdrain");
        this.setTitle("Sprint Drain");
        this.setLore("&7Arrow shots grant you &eSpeed &eII " +
                "&7([(@lvl@*2)+1]s) and apply &9Slowness &9I " +
                "&7([@lvl@]s)");
        this.setMysticType(MysticType.BOW);
        this.setEnchantGeneral(EnchantGeneral.UNCOMMON);
        this.addEventListener(RegisterEvent.REDUX_BOW_EVENT);
    }

    @Override
    public void DamageEvent(ReduxDamageEvent event) {

    }

    @Override
    public void DeathEvent(ReduxDeathEvent event) {

    }

    @Override
    public void BowEvent(ReduxBowEvent event) {

    }

    @Override
    public void ArmorEvent(ArmorEquipEvent event) {

    }

    @Override
    public void ShootEvent(EntityShootBowEvent event) {

    }

    @Override
    public void ArrowHitEvent(ProjectileHitEvent event) {

    }
}
