package me.alpha.kitpvp.PitRemake.Startup;

import com.gmail.filoghost.holographicdisplays.api.Hologram;
import com.gmail.filoghost.holographicdisplays.api.HologramsAPI;
import me.alpha.kitpvp.KitPvP;
import me.alpha.kitpvp.PitRemake.Factions.ArchAngelFaction;
import me.alpha.kitpvp.PitRemake.Factions.ArmageddonFaction;
import me.alpha.kitpvp.PitRemake.Factions.KingFaction;
import me.alpha.kitpvp.PitRemake.Locations;
import me.alpha.kitpvp.PitRemake.MapType;
import net.citizensnpcs.api.CitizensAPI;
import net.citizensnpcs.api.event.DespawnReason;
import net.citizensnpcs.api.npc.NPC;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.EntityType;
import org.bukkit.event.player.PlayerTeleportEvent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static me.alpha.kitpvp.utils.ColorUtil.colorCode;

public class CreateVillagers {

    static String lobbies = "world>lobby>lobby2";

    public static List<World> getWorlds(){
        List<World> worlds = new ArrayList<>();

        for (String world : lobbies.split(">")){
            worlds.add(Bukkit.getWorld(world));
        }

        return worlds;
    }

    static HashMap<NPC, NPCType> npcs = new HashMap<>();
    static List<Hologram> holograms = new ArrayList<>();

    public static void registerNPC(NPC npc, NPCType type){
        if(npcs.containsKey(npc)) return;
        else npcs.put(npc, type);
    }

    public static NPCType getNPCType(NPC npc){
        return npcs.getOrDefault(npc, NPCType.NONE);
    }

    public static void registerHologram(Hologram hologram){
        if(holograms.contains(hologram)) return;
        else holograms.add(hologram);
    }

    public static void loadNPC(){
        for(World world : getWorlds()) {
            NPC perm_upgrades_npc = CitizensAPI.getNPCRegistry().createNPC(EntityType.PLAYER, ChatColor.GRAY + "Permanent");
            perm_upgrades_npc.setBukkitEntityType(EntityType.VILLAGER);
            perm_upgrades_npc.setProtected(true);

            NPC non_perm_upgrades_npc = CitizensAPI.getNPCRegistry().createNPC(EntityType.PLAYER, ChatColor.GRAY + "Non-permanent");
            non_perm_upgrades_npc.setBukkitEntityType(EntityType.VILLAGER);
            non_perm_upgrades_npc.setProtected(true);

            NPC leaderboard_npc = CitizensAPI.getNPCRegistry().createNPC(EntityType.PLAYER, ChatColor.GRAY + "View your stats");
            leaderboard_npc.setBukkitEntityType(EntityType.VILLAGER);
            leaderboard_npc.setProtected(true);

            NPC quest_npc = CitizensAPI.getNPCRegistry().createNPC(EntityType.PLAYER, ChatColor.GRAY + "Quests & Contracts");
            quest_npc.setBukkitEntityType(EntityType.VILLAGER);
            quest_npc.setProtected(true);

            NPC prestige_npc = CitizensAPI.getNPCRegistry().createNPC(EntityType.PLAYER, ChatColor.GRAY + "Resets & Renown");
            prestige_npc.setBukkitEntityType(EntityType.VILLAGER);
            prestige_npc.setProtected(true);

            NPC king_npc = CitizensAPI.getNPCRegistry().createNPC(EntityType.PLAYER, colorCode("&6&lTHE KING"));
            king_npc.setBukkitEntityType(EntityType.PLAYER);
            king_npc.setProtected(true);
            KingFaction.editNPC(king_npc);

            NPC archAngel_npc = CitizensAPI.getNPCRegistry().createNPC(EntityType.PLAYER, colorCode("&f&lANGEL"));
            archAngel_npc.setBukkitEntityType(EntityType.PLAYER);
            archAngel_npc.setProtected(true);
            ArchAngelFaction.editNPC(archAngel_npc);

            NPC armageddon_npc = CitizensAPI.getNPCRegistry().createNPC(EntityType.PLAYER, colorCode("&c&lDEMON"));
            armageddon_npc.setBukkitEntityType(EntityType.PLAYER);
            armageddon_npc.setProtected(true);
            ArmageddonFaction.editNPC(armageddon_npc);

            if (!perm_upgrades_npc.isSpawned()) {
                perm_upgrades_npc.spawn(MapType.getMapType(world).getPermNPC(world));
            }

            if (!non_perm_upgrades_npc.isSpawned()) {
                non_perm_upgrades_npc.spawn(MapType.getMapType(world).getNonPermNPC(world));
            }

            if (!leaderboard_npc.isSpawned()) {
                leaderboard_npc.spawn(MapType.getMapType(world).getStatsNPC(world));
            }

            if (!quest_npc.isSpawned()) {
                quest_npc.spawn(MapType.getMapType(world).getQuestNPC(world));
            }

            if (!prestige_npc.isSpawned()) {
                prestige_npc.spawn(MapType.getMapType(world).getPrestigeNPC(world));
            }

            if(!archAngel_npc.isSpawned()){
                archAngel_npc.spawn(Locations.getArchAngelLocation(world));
            }

            if(!armageddon_npc.isSpawned()){
                armageddon_npc.spawn(Locations.getArmageddonLocation(world));
            }

            if(!king_npc.isSpawned()){
                king_npc.spawn(Locations.getKingsQuestLocation(world));
            }

            perm_upgrades_npc.teleport(MapType.getMapType(world).getPermNPC(world), PlayerTeleportEvent.TeleportCause.PLUGIN);
            non_perm_upgrades_npc.teleport(MapType.getMapType(world).getNonPermNPC(world), PlayerTeleportEvent.TeleportCause.PLUGIN);
            leaderboard_npc.teleport(MapType.getMapType(world).getStatsNPC(world), PlayerTeleportEvent.TeleportCause.PLUGIN);
            quest_npc.teleport(MapType.getMapType(world).getQuestNPC(world), PlayerTeleportEvent.TeleportCause.PLUGIN);
            prestige_npc.teleport(MapType.getMapType(world).getPrestigeNPC(world), PlayerTeleportEvent.TeleportCause.PLUGIN);

            armageddon_npc.teleport(MapType.getMapType(world).getBadNPC(world), PlayerTeleportEvent.TeleportCause.PLUGIN);
            archAngel_npc.teleport(MapType.getMapType(world).getGoodNPC(world), PlayerTeleportEvent.TeleportCause.PLUGIN);
            king_npc.teleport(MapType.getMapType(world).getKingNPC(world), PlayerTeleportEvent.TeleportCause.PLUGIN);

            registerNPC(perm_upgrades_npc, NPCType.PERM);
            registerNPC(non_perm_upgrades_npc, NPCType.NO_PERM);
            registerNPC(leaderboard_npc, NPCType.STATS);
            registerNPC(quest_npc, NPCType.QUEST);
            registerNPC(prestige_npc, NPCType.PRESTIGE);
            registerNPC(archAngel_npc, NPCType.ANGEL);
            registerNPC(armageddon_npc, NPCType.ARMAGEDDON);
            registerNPC(king_npc, NPCType.KING);

        }

        makeHolograms();
    }

    public static void unloadNPC(){
        deleteNPC();
        deleteHolograms();
    }

    private static void makeHolograms(){
        for (World world : getWorlds()) {
            Hologram perm_upgrades_hologram = HologramsAPI.createHologram(KitPvP.INSTANCE, MapType.getMapType(world).getPermNPC(world).add(0, 2.75, 0));
            perm_upgrades_hologram.appendTextLine(ChatColor.translateAlternateColorCodes('&', "&a&lUPGRADES"));

            Hologram non_perm_upgrades_hologram = HologramsAPI.createHologram(KitPvP.INSTANCE, MapType.getMapType(world).getNonPermNPC(world).add(0, 2.75, 0));
            non_perm_upgrades_hologram.appendTextLine(ChatColor.translateAlternateColorCodes('&', "&6&lITEMS"));

            Hologram leaderboard_hologram = HologramsAPI.createHologram(KitPvP.INSTANCE, MapType.getMapType(world).getStatsNPC(world).add(0, 2.75, 0));
            leaderboard_hologram.appendTextLine(ChatColor.translateAlternateColorCodes('&', "&3&lSTATS"));

            Hologram quest_hologram = HologramsAPI.createHologram(KitPvP.INSTANCE, MapType.getMapType(world).getQuestNPC(world).add(0, 2.75, 0));
            quest_hologram.appendTextLine(ChatColor.translateAlternateColorCodes('&', "&b&lQUEST MASTER"));

            Hologram prestige_hologram = HologramsAPI.createHologram(KitPvP.INSTANCE, MapType.getMapType(world).getPrestigeNPC(world).add(0, 2.75, 0));
            prestige_hologram.appendTextLine(ChatColor.translateAlternateColorCodes('&', "&e&lPRESTIGE"));

            Hologram ender_chest = HologramsAPI.createHologram(KitPvP.INSTANCE, Locations.getEnderChestLocation(world).add(0, -.75, 0));
            ender_chest.appendTextLine(ChatColor.translateAlternateColorCodes('&', "&5&lENDER CHEST"));

            Hologram ender_chest_lore = HologramsAPI.createHologram(KitPvP.INSTANCE, Locations.getEnderChestLocation(world).add(0, -1, 0));
            ender_chest_lore.appendTextLine(ChatColor.translateAlternateColorCodes('&', "&7Store items forever"));

            Hologram mystic_well = HologramsAPI.createHologram(KitPvP.INSTANCE, Locations.getMysticWellLocation(world).add(0, -.75, 0));
            mystic_well.appendTextLine(ChatColor.translateAlternateColorCodes('&', "&d&lMYSTIC WELL"));

            Hologram mystic_well_lore = HologramsAPI.createHologram(KitPvP.INSTANCE, Locations.getMysticWellLocation(world).add(0, -1, 0));
            mystic_well_lore.appendTextLine(ChatColor.translateAlternateColorCodes('&', "&7item enchants"));

            Hologram BetterPit = HologramsAPI.createHologram(KitPvP.INSTANCE, Locations.getBetterPitLocation(world));
            BetterPit.appendTextLine(ChatColor.translateAlternateColorCodes('&', "&e&lUNLOCKED FEATURES"));
            BetterPit.appendTextLine(ChatColor.translateAlternateColorCodes('&', ""));
            BetterPit.appendTextLine(ChatColor.translateAlternateColorCodes('&', "&7[1] &b/respawn"));
            BetterPit.appendTextLine(ChatColor.translateAlternateColorCodes('&', "&7[5] &b/play pit"));
            BetterPit.appendTextLine(ChatColor.translateAlternateColorCodes('&', "&7[&910&7] &bUpgrades"));
            BetterPit.appendTextLine(ChatColor.translateAlternateColorCodes('&', "&7[&915&7] &bEnder chest"));
            BetterPit.appendTextLine(ChatColor.translateAlternateColorCodes('&', "&7[&230&7] &bContracts"));
            BetterPit.appendTextLine(ChatColor.translateAlternateColorCodes('&', "&7[&e50&7] &bStats"));
            BetterPit.appendTextLine(ChatColor.translateAlternateColorCodes('&', "&7[&660&7] &b/trade"));
            BetterPit.appendTextLine(ChatColor.translateAlternateColorCodes('&', "&7[&c70&7] &b/view"));
            BetterPit.appendTextLine(ChatColor.translateAlternateColorCodes('&', "&7[&b120&7] &bPrestige"));
            BetterPit.appendTextLine(ChatColor.translateAlternateColorCodes('&', "&7Gain levels to unlock more"));

            Hologram JumpBetterPit = HologramsAPI.createHologram(KitPvP.INSTANCE, Locations.getPlayPitLocation(world));
            JumpBetterPit.appendTextLine(ChatColor.translateAlternateColorCodes('&', "&eThe Better Pit"));
            JumpBetterPit.appendTextLine(ChatColor.translateAlternateColorCodes('&', "&a&lJUMP! &c&lFIGHT!"));

            registerHologram(perm_upgrades_hologram);
            registerHologram(non_perm_upgrades_hologram);
            registerHologram(leaderboard_hologram);
            registerHologram(quest_hologram);
            registerHologram(prestige_hologram);
            registerHologram(ender_chest);
            registerHologram(ender_chest_lore);
            registerHologram(mystic_well);
            registerHologram(mystic_well_lore);
            registerHologram(BetterPit);
            registerHologram(JumpBetterPit);
        }

    }

    private static void deleteHolograms(){
        for (Hologram hologram : holograms){
            if(hologram.isDeleted()) continue;
            hologram.delete();
        }
    }

    private static void deleteNPC(){
        for(NPC npc : npcs.keySet()){
            npc.despawn(DespawnReason.REMOVAL);
            CitizensAPI.getNPCRegistry().deregister(npc);
        }
    }

}
