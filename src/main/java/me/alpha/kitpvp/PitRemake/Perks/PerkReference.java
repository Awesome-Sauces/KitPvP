package me.alpha.kitpvp.PitRemake.Perks;


import me.alpha.kitpvp.Data.ClassInstances;
import org.bukkit.ChatColor;

public class PerkReference {
    public static String getPerkLore(String refID){

        if(refID.equals(ClassInstances.goldenHeads.getRefID())){
            return ClassInstances.goldenHeads.getLore();
        }else if(refID.equals(ClassInstances.vampire.getRefID())){
            return ClassInstances.vampire.getLore();
        }else if(refID.equals(ClassInstances.streaker.getRefID())){
            return ClassInstances.streaker.getLore();
        }else if(refID.equals(ClassInstances.strengthChaining.getRefID())){
            return ClassInstances.strengthChaining.getLore();
        }else if(refID.equals(ClassInstances.gladiator.getRefID())){
            return ClassInstances.gladiator.getLore();
        }else if(refID.equals(ClassInstances.dirty.getRefID())){
            return ClassInstances.dirty.getLore();
        }else{
            return ChatColor.GRAY + "Select a perk to fill this slot.";
        }

        /*
        else if(refID.equals(ClassInstances..getRefID())){
            return ClassInstances..getLore();
        }
         */
    }
}
