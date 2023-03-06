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

public class MegalongBowEnchant extends MysticEnchant {

    public MegalongBowEnchant () {
        this.setRefID("megalongbow");
        this.setTitle("Mega Longbow");
        this.setLore("&7One shot per second, this bow is " +
                "&7automatically fully drawn and\n" +
                "&7grants &aJump Boost roman([@lvl@+1]) &7(2s)");
        this.setMysticType(MysticType.BOW);
        this.setEnchantGeneral(EnchantGeneral.RARE);
        this.addEventListener(RegisterEvent.ARROW_SHOOT_EVENT);
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
