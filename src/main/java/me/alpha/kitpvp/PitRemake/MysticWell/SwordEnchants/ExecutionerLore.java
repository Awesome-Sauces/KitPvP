package me.alpha.kitpvp.PitRemake.MysticWell.SwordEnchants;

import de.tr7zw.nbtapi.NBTItem;
import me.alpha.kitpvp.CustomEvents.ReduxDamageEvent;
import me.alpha.kitpvp.KitPvP;
import me.alpha.kitpvp.PitRemake.MysticWell.EnchantRarity;
import me.alpha.kitpvp.PitRemake.MysticWell.PitEnchant;
import me.alpha.kitpvp.PitRemake.PitCommands.Stash.StashCore;
import me.alpha.kitpvp.utils.CitizensHelper;
import me.alpha.kitpvp.utils.ColorUtil;
import me.alpha.kitpvp.utils.Sounds;
import org.bukkit.Bukkit;
import org.bukkit.Effect;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import java.util.UUID;

import static me.alpha.kitpvp.PitRemake.DeathHandler.DeathHandler.KillMan;
import static me.alpha.kitpvp.PitRemake.Scoreboard.ScoreboardCore.boardMap;
import static me.alpha.kitpvp.PitRemake.Scoreboard.ScoreboardCore.updateBoard;
import static me.alpha.kitpvp.utils.IntegerHelper.integerToRoman;


public class ExecutionerLore extends PitEnchant {

    @Override
    public void run(ReduxDamageEvent event) {

        if (!CitizensHelper.isNPC(event.getAttacker().getPlayerObject()) &&
                event.getAttacker().getPlayerObject().getItemInHand()!=null){
            NBTItem item = new NBTItem(event.getAttacker().getPlayerObject().getItemInHand());

            if(!item.hasKey("executioner")) return;


        }else if(!CitizensHelper.isNPC(event.getAttacker().getPlayerObject()) &&
                event.getAttacker().getPlayerObject().getItemInHand()!=null) return;
        else if(CitizensHelper.isNPC(event.getAttacker().getPlayerObject())) return;

        NBTItem item = new NBTItem(event.getAttacker().getPlayerObject().getItemInHand());

        double tier = item.getInteger("executioner");

        double damage = (tier*2);

        if(CitizensHelper.isNPC(event.getDefender())){
            damage+=2;
        }

        if(event.getDefender().getPlayerObject().getHealth() - (tier*2) <= 0){
            Sounds.EXE.play(event.getAttacker().getPlayerObject());
        }

        if(event.getDefender().getPlayerObject().getHealth() - damage <= tier){
            Sounds.EXE.play(event.getAttacker().getPlayerObject());
            event.getDefender().getPlayerObject().getWorld().playEffect(event.getDefender().getPlayerObject().getLocation(), Effect.STEP_SOUND, Material.REDSTONE_BLOCK);

            KillMan(event.getAttacker().getPlayerObject(), event.getDefender().getPlayerObject());

            //KillMan(event.getAttacker().getPlayerObject(), event.getDefender().getPlayerObject());

            event.setCancelled(true);
            event.getBukkitEvent().setCancelled(true);
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

        return "&dRARE! &9Executioner" + tier;
    }

    @Override
    public String lore(int level) {
        String tier = "";
        if (level > 1){tier += " " + integerToRoman(level);}

        String multiplier = String.valueOf(level);

        String lore = "&dRARE! &9Executioner" + tier + "\n" +
                "&7Hitting an enemy to below &c" + multiplier + "\u2764" +
                "\n&7instantly kills them" + "\n&7";

        return colorCode(lore);
    }
}
