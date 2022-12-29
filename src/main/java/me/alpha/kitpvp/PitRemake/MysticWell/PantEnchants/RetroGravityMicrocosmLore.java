package me.alpha.kitpvp.PitRemake.MysticWell.PantEnchants;

import de.tr7zw.nbtapi.NBTItem;
import me.alpha.kitpvp.ChatManager.RankColor;
import me.alpha.kitpvp.CustomEvents.ReduxDamageEvent;
import me.alpha.kitpvp.Data.ClassInstances;
import me.alpha.kitpvp.PitRemake.MysticWell.EnchantRarity;
import me.alpha.kitpvp.PitRemake.MysticWell.PitEnchant;
import me.alpha.kitpvp.utils.CitizensHelper;
import me.alpha.kitpvp.utils.Sounds;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;

import static me.alpha.kitpvp.utils.IntegerHelper.integerToRoman;

public class RetroGravityMicrocosmLore extends PitEnchant {

    @Override
    public void run(ReduxDamageEvent event) {

        if (!CitizensHelper.isNPC(event.getDefender().getPlayerObject()) &&
                event.getDefender().getPlayerObject().getInventory().getLeggings()!=null){
            NBTItem item = new NBTItem(event.getDefender().getPlayerObject().getInventory().getLeggings());

            if(!item.hasKey("retro-gravitymicrocosm")) return;


        }else if(!CitizensHelper.isNPC(event.getDefender().getPlayerObject()) &&
                event.getDefender().getPlayerObject().getInventory().getLeggings()==null) return;
        else if(CitizensHelper.isNPC(event.getDefender().getPlayerObject())) return;

        NBTItem item = new NBTItem(event.getDefender().getPlayerObject().getInventory().getLeggings());
        int level = item.getInteger("retro-gravitymicrocosm");

        if(criticalHit(event)){
            runIt(event.getAttacker().getPlayerObject(), event.getDefender().getPlayerObject(), .5+((level-1)*.25), .5+((level-1)*.5));
        }

    }

    public boolean criticalHit(ReduxDamageEvent event){
        Player player = event.getDefender().getPlayerObject();

        if(!event.getAttacker().getPlayerObject().isOnGround()){
            if(ClassInstances.RgmHitCounter.containsKey(String.valueOf(player.getUniqueId()))){
                ClassInstances.RgmHitCounter.put(String.valueOf(player.getUniqueId()), ClassInstances.RgmHitCounter.get(String.valueOf(player.getUniqueId())) + 1);
            }else{
                ClassInstances.RgmHitCounter.put(String.valueOf(player.getUniqueId()), 1);
            }

            return ClassInstances.RgmHitCounter.get(String.valueOf(player.getUniqueId())) >= 3;
        }
        return !event.getAttacker().getPlayerObject().isOnGround();
    }

    public void runIt(Player attacker, Player defender, double health, double trueDmg){
        ClassInstances.RgmHitCounter.put(String.valueOf(defender.getUniqueId()), 0);
        attacker.sendMessage(ChatColor.translateAlternateColorCodes('&', "&d&lRGM! &7Proc'd against " + RankColor.getNameColor(defender) + defender.getDisplayName() + "&7!"));
        Sounds.RGM.play(attacker.getLocation());
        Sounds.RGM.play(defender.getLocation());
        defender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&d&lRGM! &7Procced against " + RankColor.getNameColor(attacker) + attacker.getDisplayName() + "&7!"));
        defender.setHealth(Math.min(defender.getHealth() + health, defender.getMaxHealth()));

        EntityDamageByEntityEvent events = new EntityDamageByEntityEvent(defender, attacker,
                EntityDamageEvent.DamageCause.MAGIC, trueDmg);
        Bukkit.getServer().getPluginManager().callEvent(events);
        if(!events.isCancelled()) {
            attacker.setHealth(Math.max(attacker.getHealth() - trueDmg, 0));
        }
    }

    @Override
    public void init() {
        rarity = EnchantRarity.RARE;
    }

    @Override
    public String title(int level) {
        String tier = "";
        if (level > 1){tier += " " + integerToRoman(level);}

        return "&dRARE! &9Retro-Gravity Microcosm" + tier;
    }

    @Override
    public String lore(int level) {
        String tier = "";
        if (level > 1){tier += " " + integerToRoman(level);}

        String multiplier = String.valueOf(.5+((level-1)*.25));

        String take = String.valueOf(.5+((level-1)*.5));

        String lore = "&dRARE! &9Retro-Gravity Microcosm" + tier + "\n" +
                "&7When a player hits you from\n" +
                "&7above ground &e3 times &7in a row:\n" +
                "&7You heal &c"+multiplier+"\u2764\n" +
                "&7They take &c"+take+"\u2764 &7true damage" + "\n&7";

        return colorCode(lore);
    }
}