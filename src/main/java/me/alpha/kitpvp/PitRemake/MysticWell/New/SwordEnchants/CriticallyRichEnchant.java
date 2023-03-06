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

public class CriticallyRichEnchant extends MysticEnchant {
    public CriticallyRichEnchant() {
        this.setRefID("criticallyrich");
        this.setTitle("Critically Rich");
        this.setLore("&7Earn &6+" + "[@lvl@*4]" + "g &7every &7critical hit");
        this.setMysticType(MysticType.SWORD);
        this.setEnchantGeneral(EnchantGeneral.COMMON);
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
