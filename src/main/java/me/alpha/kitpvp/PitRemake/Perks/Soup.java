package me.alpha.kitpvp.PitRemake.Perks;

import me.alpha.kitpvp.CustomEvents.ReduxDamageEvent;
import me.alpha.kitpvp.CustomEvents.ReduxDeathEvent;
import me.alpha.kitpvp.Data.ClassInstances;
import me.alpha.kitpvp.Objects.ReduxPlayerObject.ReduxPlayer;
import me.alpha.kitpvp.PitRemake.ItemStacks.enchants;
import org.bukkit.Material;

import static me.alpha.kitpvp.utils.ColorUtil.colorCode;

public class Soup extends PitPerk {

    public Soup(){
        this.setRefID("soup");
        this.setMaterial(Material.MUSHROOM_SOUP);
        this.setName(colorCode("Soup"));
        this.setLore(colorCode("&7Golden apples you earn turn into\n" +
                "&aTasty Soup&7. You also earn\n" +
                "&7soup on assists.\n\n" +
                "&aTasty Soup\n" +
                "&9Speed I (0:07)\n" +
                "&a1.5\u2764 Heal &7+ &61\u2764 Absorption\n" +
                "&cNext melee hit +15% damage"));
        this.setCost(8000);
        this.setLevel(25);
    }

    @Override
    public PerkExecute getPerkExecute() {
        return new PerkExecute(){
            @Override
            public void run(ReduxDamageEvent event){

            }

            @Override
            public void run(ReduxDeathEvent event){
                ReduxPlayer player = event.getAttacker();

                if(player.getPerks().contains(ClassInstances.soup.getRefID())){
                    if(player.getPlayerObject().getInventory().contains(enchants.soup.getType(), 2)) return;
                    player.getPlayerObject().getInventory().addItem(enchants.soup);
                }

            }
        };
    }


}


