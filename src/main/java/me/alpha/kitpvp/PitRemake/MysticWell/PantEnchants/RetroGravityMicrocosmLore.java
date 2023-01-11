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
import org.bukkit.Material;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.potion.PotionEffectType;

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

            event.getDefender().getPlayerObject().setHealth(Math.min(event.getDefender().getPlayerObject().getMaxHealth(),
                    event.getDefender().getPlayerObject().getHealth()+(.5+((level-1)*.25))));

            event.addReduxDefenderTrueDamage((.5+((level-1)*.5))*2);

            event.getAttacker().getPlayerObject().sendMessage(ChatColor.translateAlternateColorCodes('&', "&d&lRGM! &7Proc'd against " + RankColor.getNameColor(event.getDefender().getPlayerObject()) + event.getDefender().getPlayerObject().getDisplayName() + "&7!"));
            Sounds.RGM.play(event.getAttacker().getPlayerObject());
            Sounds.RGM.play(event.getDefender().getPlayerObject());
            event.getDefender().getPlayerObject().sendMessage(ChatColor.translateAlternateColorCodes('&', "&d&lRGM! &7Procced against " + RankColor.getNameColor(event.getAttacker().getPlayerObject()) + event.getAttacker().getPlayerObject().getDisplayName() + "&7!"));
        }

    }

    public boolean isCritical(LivingEntity entity){
        return entity.getFallDistance()>0.0F &&
                !entity.isOnGround() &&
                !entity.isInsideVehicle() &&
                !entity.hasPotionEffect(PotionEffectType.BLINDNESS) &&
                entity.getLocation().getBlock().getType() != Material.LADDER &&
                entity.getLocation().getBlock().getType() != Material.VINE;
    }

    public boolean criticalHit(ReduxDamageEvent event){
        Player player = event.getAttacker().getPlayerObject();

        if(isCritical(event.getAttacker().getPlayerObject())){
            if(ClassInstances.RgmHitCounter.containsKey(String.valueOf(player.getUniqueId()))){
                ClassInstances.RgmHitCounter.put(String.valueOf(player.getUniqueId()), ClassInstances.RgmHitCounter.get(String.valueOf(player.getUniqueId())) + 1);
            }else{
                ClassInstances.RgmHitCounter.put(String.valueOf(player.getUniqueId()), 1);
            }

            if(ClassInstances.RgmHitCounter.get(String.valueOf(player.getUniqueId())) >= 4){
                ClassInstances.RgmHitCounter.put(String.valueOf(player.getUniqueId()), 0);
                return true;
            }

            return false;
        }
        return false;
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