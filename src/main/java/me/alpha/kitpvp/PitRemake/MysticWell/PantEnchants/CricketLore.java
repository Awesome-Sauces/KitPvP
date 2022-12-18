package me.alpha.kitpvp.PitRemake.MysticWell.PantEnchants;

import de.tr7zw.nbtapi.NBTItem;
import me.alpha.kitpvp.CustomEvents.ArmorEvents.ArmorEquipEvent;
import me.alpha.kitpvp.CustomEvents.ReduxDamageEvent;
import me.alpha.kitpvp.Objects.ReduxPlayerObject.ReduxPlayer;
import me.alpha.kitpvp.PitRemake.MysticWell.EnchantRarity;
import me.alpha.kitpvp.PitRemake.MysticWell.PitEnchant;
import me.alpha.kitpvp.PitRemake.MysticWell.loreChecker;
import me.alpha.kitpvp.utils.CitizensHelper;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffectType;

import static me.alpha.kitpvp.Objects.ReduxPlayerObject.ReduxPlayerHandler.playerExists;
import static me.alpha.kitpvp.PitRemake.MysticWell.loreChecker.CheckEnchantOnPant;
import static me.alpha.kitpvp.utils.CitizensHelper.isNPC;
import static me.alpha.kitpvp.utils.IntegerHelper.integerToRoman;

public class CricketLore extends PitEnchant {
    @Override
    public void run(ReduxDamageEvent event) {
        if (!CitizensHelper.isNPC(event.getDefenders().getPlayerObject()) &&
                event.getDefenders().getPlayerObject().getInventory().getLeggings()!=null){
            NBTItem item = new NBTItem(event.getDefenders().getPlayerObject().getInventory().getLeggings());

            if(!item.hasKey("cricket")) return;


        }else if(!CitizensHelper.isNPC(event.getDefenders().getPlayerObject()) &&
                event.getDefenders().getPlayerObject().getInventory().getLeggings()==null) return;
        else if(CitizensHelper.isNPC(event.getDefenders().getPlayerObject())) return;

        NBTItem item = new NBTItem(event.getDefenders().getPlayerObject().getInventory().getLeggings());
        int level = item.getInteger("cricket");

        if(event.getAttacker().getPlayerObject().getWorld().getBlockAt(event.getAttacker().getPlayerObject().getLocation().add(0,-1,0)).getType().equals(Material.GRASS) ||
                event.getDefenders().getPlayerObject().getWorld().getBlockAt(event.getDefenders().getPlayerObject().getLocation().add(0,-1,0)).getType().equals(Material.GRASS)){
            double damage = (level*5);
            event.subtractReduxDamageMultiplier(damage);


            event.getDefenders().addPotionEffect(PotionEffectType.REGENERATION, 32000, 1);
        }else{
            event.getDefenders().removePotionEffect(PotionEffectType.REGENERATION);
        }
    }

    public void run(ArmorEquipEvent event){
        if(isNPC(event.getPlayer())) return;
        if(event.getNewArmorPiece() == null) {
            playerExists(event.getPlayer()).removePotionEffect(PotionEffectType.REGENERATION);
            return;
        }
        if(event.getOldArmorPiece() != null &&
                event.getOldArmorPiece().getType().equals(Material.LEATHER_LEGGINGS) &&
        !isNPC(event.getPlayer())){
            ItemStack leggings = event.getOldArmorPiece();

            boolean CRICKET = false;

            if(leggings.getItemMeta() != null &&
            leggings.getItemMeta().getLore() != null){
                for(String enchant : CheckEnchantOnPant(leggings.getItemMeta().getLore())) {
                    if (enchant.contains("cricket")) {
                        CRICKET = true;
                        break;
                    }
                }
            }

            if(CRICKET){
                playerExists(event.getPlayer()).removePotionEffect(PotionEffectType.REGENERATION);
            }

            return;
        }
        if(!event.getNewArmorPiece().getType().equals(Material.LEATHER_LEGGINGS)) return;

        ReduxPlayer player = playerExists(event.getPlayer());
        ItemStack leggings = event.getNewArmorPiece();

        boolean CRICKET = false;

        if(leggings != null &&
                leggings.getItemMeta() != null &&
                leggings.getItemMeta().getLore() != null){
            for(String enchant : CheckEnchantOnPant(leggings.getItemMeta().getLore())) {
                if (enchant.contains("cricket")) {
                    CRICKET = true;
                    break;
                }
            }
        }

        if(CRICKET &&
                player.getPlayerObject().getWorld().getBlockAt(player.getPlayerObject().getLocation().add(0,-1,0)).getType().equals(Material.GRASS)){
            player.addPotionEffect(PotionEffectType.REGENERATION, 32000, 1);
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

        return "&9Cricket" + tier;
    }

    // T1 = 5 T2 = 7 T3 = 15

    @Override
    public String lore(int level) {
        String tier = "";
        if (level > 1){tier += " " + integerToRoman(level);}

        String multiplier = String.valueOf(level*5);

        String lore = "&9Cricket" + tier + "\n" +
                "&7Receive &9-"+multiplier+"% &7damage when you or\n" +
                "&7your victims are standing on grass\n" +
                "&aPerma &cRegen I &aon grass!" + "\n&7";

        return colorCode(lore);
    }
}
