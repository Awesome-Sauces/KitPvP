package me.alpha.kitpvp.PitRemake.MysticWell.New;

import me.alpha.kitpvp.CustomEvents.ArmorEvents.ArmorEquipEvent;
import me.alpha.kitpvp.CustomEvents.ReduxBowEvent;
import me.alpha.kitpvp.CustomEvents.ReduxDamageEvent;
import me.alpha.kitpvp.CustomEvents.ReduxDeathEvent;
import org.bukkit.Bukkit;

public class XpBoostEnchant extends MysticEnchant{

    public XpBoostEnchant () {
        this.setRefID("xpBoost");
        this.setTitle("Xp Boost");
        this.setLore("&7Earn &b+[@lvl@*10]% XP &7on kill");
        this.setMysticType(MysticType.ALL);
        this.setEnchantGeneral(EnchantGeneral.COMMON);
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