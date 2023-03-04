package me.alpha.kitpvp.PitRemake.MysticWell.New;

import com.google.common.collect.ImmutableList;
import me.alpha.kitpvp.CustomEvents.ArmorEvents.ArmorEquipEvent;
import me.alpha.kitpvp.CustomEvents.ReduxBowEvent;
import me.alpha.kitpvp.CustomEvents.ReduxDamageEvent;
import me.alpha.kitpvp.CustomEvents.ReduxDeathEvent;
import org.bukkit.Bukkit;

public class SweatyEnchant extends MysticEnchant{

    public SweatyEnchant () {
        this.setRefID("sweaty");
        this.setTitle("Sweaty");
        this.setLore("&7Increase the streak XP bonus by &b[@lvl@*20]%&7 +[@lvl@*50] max &bXP &7on kill.");
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
}
