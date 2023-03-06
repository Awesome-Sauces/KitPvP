package me.alpha.kitpvp.PitRemake.MysticWell.New.SwordEnchants;

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

public class GambleEnchant extends MysticEnchant {

    public GambleEnchant () {
        this.setRefID("gamble");
        this.setTitle("Gamble");
        this.setLore("&d50% chance &7to deal @lvl@\u2764 true" +
                " &7damage to whoever you hit, or to yourself");
        this.setMysticType(MysticType.SWORD);
        this.setEnchantGeneral(EnchantGeneral.RARE);
        this.addEventListener(RegisterEvent.REDUX_DAMAGE_EVENT);
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
