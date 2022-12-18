package me.alpha.kitpvp.PitRemake.Perks;

import me.alpha.kitpvp.CustomEvents.ReduxDamageEvent;
import me.alpha.kitpvp.CustomEvents.ReduxDeathEvent;
import me.alpha.kitpvp.Data.ClassInstances;
import me.alpha.kitpvp.Objects.ReduxPlayerObject.ReduxPlayer;
import me.alpha.kitpvp.PitRemake.ItemStacks.enchants;
import org.bukkit.Material;

import static me.alpha.kitpvp.utils.ColorUtil.colorCode;

public class GoldenHeads extends PitPerk {

    public GoldenHeads(){
        this.setRefID("goldenhead");
        this.setMaterial(Material.SKULL_ITEM);
        this.setName(colorCode("Golden Heads"));
        this.setLore(colorCode("&7Golden apples you earn turn into\n" +
                "&6Golden Heads&7."));
        this.setCost(1000);
        this.setLevel(5);
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

                if(player.getPerks().contains(ClassInstances.goldenHeads.getRefID())){
                    if(player.getPlayerObject().getInventory().contains(enchants.goldenhead.getType(), 2)) return;
                    player.getPlayerObject().getInventory().addItem(enchants.goldenhead);
                }

            }
        };
    }


}

