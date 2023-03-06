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

public class JumpSpammerEnchant extends MysticEnchant {

    public JumpSpammerEnchant () {
        this.setRefID("jumpSpammer");
        this.setTitle("Jumpspammer");
        this.setLore("&7While midair, your arrows deal\n" +
                "&c+[@lvl@*8]% &7damage. While midair, " +
                "&7receive &9-[@lvl@*7]% &7damage from melee " +
                "&7and ranged attacks");
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
