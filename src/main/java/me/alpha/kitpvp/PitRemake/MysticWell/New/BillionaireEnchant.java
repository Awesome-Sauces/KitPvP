package me.alpha.kitpvp.PitRemake.MysticWell.New;

import me.alpha.kitpvp.CustomEvents.ArmorEvents.ArmorEquipEvent;
import me.alpha.kitpvp.CustomEvents.ReduxBowEvent;
import me.alpha.kitpvp.CustomEvents.ReduxDamageEvent;
import me.alpha.kitpvp.CustomEvents.ReduxDeathEvent;
import org.bukkit.Bukkit;

public class BillionaireEnchant extends MysticEnchant{

    public BillionaireEnchant () {
        this.setRefID("billionaire");
        this.setTitle("Billionaire");
        this.setLore("&7Hits with this sword deal &c[(1.33 + .34 * (@lvl@-1))-.01]x &cdamage &7but cost &6[@lvl@*150]g");
        this.setMysticType(MysticType.SWORD);
        this.setEnchantGeneral(EnchantGeneral.RARE);
        this.addEventListener(RegisterEvent.REDUX_DAMAGE_EVENT);
    }

    @Override
    public void DamageEvent(ReduxDamageEvent event) {
        Bukkit.broadcastMessage(this.getRefID());
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
}