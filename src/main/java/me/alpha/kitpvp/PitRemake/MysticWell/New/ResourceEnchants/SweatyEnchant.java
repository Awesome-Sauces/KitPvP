package me.alpha.kitpvp.PitRemake.MysticWell.New.ResourceEnchants;

import com.google.common.collect.ImmutableList;
import me.alpha.kitpvp.CustomEvents.ArmorEvents.ArmorEquipEvent;
import me.alpha.kitpvp.CustomEvents.ReduxBowEvent;
import me.alpha.kitpvp.CustomEvents.ReduxDamageEvent;
import me.alpha.kitpvp.CustomEvents.ReduxDeathEvent;
import me.alpha.kitpvp.PitRemake.MysticWell.New.EnchantGeneral;
import me.alpha.kitpvp.PitRemake.MysticWell.New.MysticEnchant;
import me.alpha.kitpvp.PitRemake.MysticWell.New.MysticType;
import me.alpha.kitpvp.PitRemake.MysticWell.New.RegisterEvent;
import org.bukkit.Bukkit;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.event.entity.ProjectileHitEvent;

public class SweatyEnchant extends MysticEnchant {

    public SweatyEnchant () {
        this.setRefID("sweaty");
        this.setTitle("Sweaty");
        this.setLore("&7Earn &b+[@lvl@*20]% XP &7from streak XP\n" +
                "&7bonus and &b+[@lvl@*50] max XP &7on kill");
        this.setMysticType(MysticType.ALL);
        this.setEnchantGeneral(EnchantGeneral.UNCOMMON);
        this.addEventListener(RegisterEvent.REDUX_DEATH_EVENT);
    }

    @Override
    public void DamageEvent(ReduxDamageEvent event) {

    }

    @Override
    public void DeathEvent(ReduxDeathEvent event) {
        Bukkit.broadcastMessage(this.getRefID());
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
