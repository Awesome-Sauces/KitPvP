package me.alpha.kitpvp.PitRemake.MysticWell.PantEnchants;

import de.tr7zw.nbtapi.NBTItem;
import me.alpha.kitpvp.CustomEvents.ReduxDamageEvent;
import me.alpha.kitpvp.Data.ClassInstances;
import me.alpha.kitpvp.PitRemake.MysticWell.EnchantRarity;
import me.alpha.kitpvp.PitRemake.MysticWell.PitEnchant;
import me.alpha.kitpvp.utils.CitizensHelper;
import me.alpha.kitpvp.utils.ColorUtil;
import me.alpha.kitpvp.utils.Sounds;
import org.bukkit.potion.PotionEffectType;

import static me.alpha.kitpvp.utils.IntegerHelper.integerToRoman;

public class VenomLore extends PitEnchant {

    @Override
    public void run(ReduxDamageEvent event) {
        if (!CitizensHelper.isNPC(event.getAttacker().getPlayerObject()) &&
                event.getAttacker().getPlayerObject().getInventory().getLeggings()!=null){
            NBTItem item = new NBTItem(event.getAttacker().getPlayerObject().getInventory().getLeggings());

            if(!item.hasKey("venom")) return;


        }else if(!CitizensHelper.isNPC(event.getAttacker().getPlayerObject()) &&
                event.getAttacker().getPlayerObject().getInventory().getLeggings()==null) return;
        else if(CitizensHelper.isNPC(event.getAttacker().getPlayerObject())) return;

        NBTItem item = new NBTItem(event.getAttacker().getPlayerObject().getInventory().getLeggings());
        int level = item.getInteger("venom");

        addHit(event.getAttacker().getPlayerUUID());

        if(ClassInstances.VenomHitCounter.containsKey(event.getAttacker().getPlayerUUID())){
            if(ClassInstances.VenomHitCounter.get(event.getAttacker().getPlayerUUID())>=3){
                if(!CitizensHelper.isNPC(event.getDefender())) event.getDefender().addPotionEffect(PotionEffectType.POISON, 12, 1);
                event.getAttacker().addPotionEffect(PotionEffectType.POISON, 12, 1);
                ClassInstances.VenomHitCounter.put(event.getAttacker().getPlayerUUID(), 0);

                //event.getAttacker().getPlayerObject().sendMessage("&a&lPOISONED! &7Your mystic enchants are ineffective!");
                event.getDefender().getPlayerObject().sendMessage(ColorUtil.colorCode("&a&lPOISONED! &7Your mystic enchants are ineffective!"));

                Sounds.VENOM.play(event.getAttacker().getPlayerObject());
                Sounds.VENOM.play(event.getDefender().getPlayerObject());
            }
        }

    }

    public void addHit(String uuid){
        if(ClassInstances.VenomHitCounter.containsKey(uuid)){
            ClassInstances.VenomHitCounter.put(uuid, ClassInstances.VenomHitCounter.get(uuid)+1);
        }else{
            ClassInstances.VenomHitCounter.put(uuid, 1);
        }
    }

    @Override
    public void init() {
        rarity = EnchantRarity.NORMAL;
    }

    @Override
    public String title(int level) {
        String tier = "";
        if (level > 1){tier += " " + integerToRoman(level);}

        return "&9Somber" + tier;
    }

    @Override
    public String lore(int level) {

        String lore = "&dRARE! &9Combo: Venom" + "\n" +
                "&7Every &ethird &7strike &apoisons\n" +
                "&7enemies, temporarily applying\n" +
                "&7Somber for &512 seconds.\n" +
                "&7Also &apoisons &7yourself!"
                + "\n&7";

        return colorCode(lore);
    }
}
