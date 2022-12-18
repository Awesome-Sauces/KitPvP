package me.alpha.kitpvp.PitRemake.Perks;


import me.alpha.kitpvp.CustomEvents.ReduxDamageEvent;
import me.alpha.kitpvp.CustomEvents.ReduxDeathEvent;
import me.alpha.kitpvp.Data.ClassInstances;
import me.alpha.kitpvp.Objects.ReduxPlayerObject.ReduxPlayer;
import org.bukkit.Material;
import static me.alpha.kitpvp.utils.ColorUtil.colorCode;

public class AssistantStreaker extends PitPerk {

    public AssistantStreaker(){
        this.setRefID("assistant");
        this.setMaterial(Material.SPRUCE_FENCE);
        this.setName(colorCode("Assistant Streaker"));
        this.setLore(colorCode("&7Assists count their\n" +
                "&aparticipation &7towards\n" +
                "&7killstreaks.\n\n" +
                "&7Earn &6+2g &7and &b+15% XP\n" +
                "&7from kills and assists.\n\n" +
                "&7Gain &e+1 &7streak every &c4th\n" +
                "&ckill&7."));
        this.setCost(8000);
        this.setLevel(25);
    }

    @Override
    public PerkExecute getPerkExecute() {
        return new PerkExecute(){
            @Override
            public void run(ReduxDamageEvent event){}

            @Override
            public void run(ReduxDeathEvent event){
                ReduxPlayer player = event.getAttacker();
                ReduxPlayer defender = event.getDefender();

                if(player.getPerks().contains(ClassInstances.assistantStreaker.getRefID())){
                    event.addGold(2);
                    event.addXp(event.getXp()*.15);
                }

                if(player.getPerks().contains(ClassInstances.assistantStreaker.getRefID())) if(player.tickAssistantStreaker()>=4){
                    player.resetAssistantStreaker();
                    ClassInstances.streakData.addStreak(player.getPlayerUUID(),1);
                }
            }
        };
    }


}

