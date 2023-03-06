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

public class ArrowArmoryEnchant extends MysticEnchant {

    public ArrowArmoryEnchant () {
        this.setRefID("arrowArmory");
        this.setTitle("Arrow Armory");
        this.setLore("&7Deal &c+[@lvl@*20]% &7damage but uses &f8 " +
                "&farrows &7per shot, if available");
        this.setMysticType(MysticType.BOW);
        this.setEnchantGeneral(EnchantGeneral.COMMON);
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
