package me.alpha.kitpvp.utils;

import net.citizensnpcs.api.CitizensAPI;
import net.citizensnpcs.api.npc.NPC;
import org.bukkit.entity.Player;

public class CitizensHelper {
    public static boolean isNPC(Player player){
        return CitizensAPI.getNPCRegistry().isNPC(player);
    }

    public static NPC getNPC(Player player){
        return CitizensAPI.getNPCRegistry().getNPC(player);
    }
}
