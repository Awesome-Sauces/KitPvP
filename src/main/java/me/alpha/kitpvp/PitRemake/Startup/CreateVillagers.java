package me.alpha.kitpvp.PitRemake.Startup;

import com.gmail.filoghost.holographicdisplays.api.Hologram;
import com.gmail.filoghost.holographicdisplays.api.HologramsAPI;
import me.alpha.kitpvp.KitPvP;
import me.alpha.kitpvp.PitRemake.Factions.ArchAngelFaction;
import me.alpha.kitpvp.PitRemake.Factions.ArmageddonFaction;
import me.alpha.kitpvp.PitRemake.Factions.KingFaction;
import me.alpha.kitpvp.PitRemake.Locations;
import net.citizensnpcs.api.CitizensAPI;
import net.citizensnpcs.api.npc.NPC;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.EntityType;
import org.bukkit.event.player.PlayerTeleportEvent;

import static me.alpha.kitpvp.utils.ColorUtil.colorCode;

public class CreateVillagers {

    public static NPC perm_upgrades_npc;
    public static NPC non_perm_upgrades_npc;

    public static NPC leaderboard_npc;
    public static NPC quest_npc;

    public static NPC prestige_npc;

    private static Hologram perm_upgrades_hologram;
    private static Hologram non_perm_upgrades_hologram;

    private static Hologram leaderboard_hologram;
    private static Hologram quest_hologram;

    private static Hologram prestige_hologram;

    private static Hologram ender_chest;
    private static Hologram ender_chest_lore;

    private static Hologram mystic_well;
    private static Hologram mystic_well_lore;

    public static NPC lobby_perm_upgrades_npc;
    public static NPC lobby_non_perm_upgrades_npc;

    public static NPC lobby_leaderboard_npc;
    public static NPC lobby_quest_npc;

    public static NPC lobby_prestige_npc;

    private static Hologram lobby_perm_upgrades_hologram;
    private static Hologram lobby_non_perm_upgrades_hologram;

    private static Hologram lobby_leaderboard_hologram;
    private static Hologram lobby_quest_hologram;

    private static Hologram lobby_prestige_hologram;

    private static Hologram lobby_ender_chest;
    private static Hologram lobby_ender_chest_lore;

    private static Hologram lobby_mystic_well;
    private static Hologram lobby_mystic_well_lore;

    public static NPC lobby2_perm_upgrades_npc;
    public static NPC lobby2_non_perm_upgrades_npc;

    public static NPC lobby2_leaderboard_npc;
    public static NPC lobby2_quest_npc;

    public static NPC lobby2_prestige_npc;

    public static NPC armageddon_npc;
    public static NPC lobby_armageddon_npc;
    public static NPC lobby2_armageddon_npc;

    public static NPC archAngel_npc;
    public static NPC lobby_archAngel_npc;
    public static NPC lobby2_archAngel_npc;

    public static NPC king_npc;
    public static NPC lobby_king_npc;
    public static NPC lobby2_king_npc;

    private static Hologram lobby2_perm_upgrades_hologram;
    private static Hologram lobby2_non_perm_upgrades_hologram;

    private static Hologram lobby2_leaderboard_hologram;
    private static Hologram lobby2_quest_hologram;

    private static Hologram lobby2_prestige_hologram;

    private static Hologram lobby2_ender_chest;
    private static Hologram lobby2_ender_chest_lore;

    private static Hologram lobby2_mystic_well;
    private static Hologram lobby2_mystic_well_lore;

    private static Hologram BetterPit;
    private static Hologram LobbyBetterPit;
    private static Hologram Lobby2BetterPit;

    private static Hologram JumpBetterPit;
    private static Hologram LobbyJumpBetterPit;
    private static Hologram Lobby2JumpBetterPit;

    public static void loadNPC(){

        perm_upgrades_npc = CitizensAPI.getNPCRegistry().createNPC(EntityType.PLAYER,  ChatColor.GRAY +  "Permanent");
        perm_upgrades_npc.setBukkitEntityType(EntityType.VILLAGER);
        perm_upgrades_npc.setProtected(true);

        lobby_perm_upgrades_npc = CitizensAPI.getNPCRegistry().createNPC(EntityType.PLAYER, ChatColor.GRAY + "Permanent");
        lobby_perm_upgrades_npc.setBukkitEntityType(EntityType.VILLAGER);
        lobby_perm_upgrades_npc.setProtected(true);

        lobby2_perm_upgrades_npc = CitizensAPI.getNPCRegistry().createNPC(EntityType.PLAYER, ChatColor.GRAY + "Permanent");
        lobby2_perm_upgrades_npc.setBukkitEntityType(EntityType.VILLAGER);
        lobby2_perm_upgrades_npc.setProtected(true);

        non_perm_upgrades_npc = CitizensAPI.getNPCRegistry().createNPC(EntityType.PLAYER, ChatColor.GRAY + "Non-permanent");
        non_perm_upgrades_npc.setBukkitEntityType(EntityType.VILLAGER);
        non_perm_upgrades_npc.setProtected(true);

        lobby_non_perm_upgrades_npc = CitizensAPI.getNPCRegistry().createNPC(EntityType.PLAYER, ChatColor.GRAY + "Non-permanent");
        lobby_non_perm_upgrades_npc.setBukkitEntityType(EntityType.VILLAGER);
        lobby_non_perm_upgrades_npc.setProtected(true);

        lobby2_non_perm_upgrades_npc = CitizensAPI.getNPCRegistry().createNPC(EntityType.PLAYER, ChatColor.GRAY + "Non-permanent");
        lobby2_non_perm_upgrades_npc.setBukkitEntityType(EntityType.VILLAGER);
        lobby2_non_perm_upgrades_npc.setProtected(true);

        leaderboard_npc = CitizensAPI.getNPCRegistry().createNPC(EntityType.PLAYER, ChatColor.GRAY + "View your stats");
        leaderboard_npc.setBukkitEntityType(EntityType.VILLAGER);
        leaderboard_npc.setProtected(true);

        lobby_leaderboard_npc = CitizensAPI.getNPCRegistry().createNPC(EntityType.PLAYER, ChatColor.GRAY + "View your stats");
        lobby_leaderboard_npc.setBukkitEntityType(EntityType.VILLAGER);
        lobby_leaderboard_npc.setProtected(true);

        lobby2_leaderboard_npc = CitizensAPI.getNPCRegistry().createNPC(EntityType.PLAYER, ChatColor.GRAY + "View your stats");
        lobby2_leaderboard_npc.setBukkitEntityType(EntityType.VILLAGER);
        lobby2_leaderboard_npc.setProtected(true);

        quest_npc = CitizensAPI.getNPCRegistry().createNPC(EntityType.PLAYER, ChatColor.GRAY + "Quests & Contracts");
        quest_npc.setBukkitEntityType(EntityType.VILLAGER);
        quest_npc.setProtected(true);

        lobby_quest_npc = CitizensAPI.getNPCRegistry().createNPC(EntityType.PLAYER, ChatColor.GRAY + "Quests & Contracts");
        lobby_quest_npc.setBukkitEntityType(EntityType.VILLAGER);
        lobby_quest_npc.setProtected(true);

        lobby2_quest_npc = CitizensAPI.getNPCRegistry().createNPC(EntityType.PLAYER, ChatColor.GRAY + "Quests & Contracts");
        lobby2_quest_npc.setBukkitEntityType(EntityType.VILLAGER);
        lobby2_quest_npc.setProtected(true);

        prestige_npc = CitizensAPI.getNPCRegistry().createNPC(EntityType.PLAYER, ChatColor.GRAY + "Resets & Renown");
        prestige_npc.setBukkitEntityType(EntityType.VILLAGER);
        prestige_npc.setProtected(true);

        lobby_prestige_npc = CitizensAPI.getNPCRegistry().createNPC(EntityType.PLAYER, ChatColor.GRAY + "Resets & Renown");
        lobby_prestige_npc.setBukkitEntityType(EntityType.VILLAGER);
        lobby_prestige_npc.setProtected(true);

        lobby2_prestige_npc = CitizensAPI.getNPCRegistry().createNPC(EntityType.PLAYER, ChatColor.GRAY + "Resets & Renown");
        lobby2_prestige_npc.setBukkitEntityType(EntityType.VILLAGER);
        lobby2_prestige_npc.setProtected(true);

        king_npc = CitizensAPI.getNPCRegistry().createNPC(EntityType.PLAYER, colorCode("&6&lTHE KING"));
        king_npc.setBukkitEntityType(EntityType.PLAYER);
        king_npc.setProtected(true);
        KingFaction.editNPC(king_npc);

        lobby_king_npc = CitizensAPI.getNPCRegistry().createNPC(EntityType.PLAYER, colorCode("&6&lTHE KING"));
        lobby_king_npc.setBukkitEntityType(EntityType.PLAYER);
        lobby_king_npc.setProtected(true);
        KingFaction.editNPC(lobby_king_npc);

        lobby2_king_npc = CitizensAPI.getNPCRegistry().createNPC(EntityType.PLAYER, colorCode("&6&lTHE KING"));
        lobby2_king_npc.setBukkitEntityType(EntityType.PLAYER);
        lobby2_king_npc.setProtected(true);
        KingFaction.editNPC(lobby2_king_npc);

        archAngel_npc = CitizensAPI.getNPCRegistry().createNPC(EntityType.PLAYER, colorCode("&f&lANGEL"));
        archAngel_npc.setBukkitEntityType(EntityType.PLAYER);
        archAngel_npc.setProtected(true);
        ArchAngelFaction.editNPC(archAngel_npc);

        lobby_archAngel_npc = CitizensAPI.getNPCRegistry().createNPC(EntityType.PLAYER, colorCode("&f&lANGEL"));
        lobby_archAngel_npc.setProtected(true);
        lobby_archAngel_npc.setBukkitEntityType(EntityType.PLAYER);
        ArchAngelFaction.editNPC(lobby_archAngel_npc);

        lobby2_archAngel_npc = CitizensAPI.getNPCRegistry().createNPC(EntityType.PLAYER, colorCode("&f&lANGEL"));
        lobby2_archAngel_npc.setBukkitEntityType(EntityType.PLAYER);
        lobby2_archAngel_npc.setProtected(true);
        ArchAngelFaction.editNPC(lobby2_archAngel_npc);

        armageddon_npc = CitizensAPI.getNPCRegistry().createNPC(EntityType.PLAYER, colorCode("&c&lDEMON"));
        armageddon_npc.setBukkitEntityType(EntityType.PLAYER);
        armageddon_npc.setProtected(true);
        ArmageddonFaction.editNPC(armageddon_npc);

        lobby_armageddon_npc = CitizensAPI.getNPCRegistry().createNPC(EntityType.PLAYER, colorCode("&c&lDEMON"));
        lobby_armageddon_npc.setProtected(true);
        lobby_armageddon_npc.setBukkitEntityType(EntityType.PLAYER);
        ArmageddonFaction.editNPC(lobby_armageddon_npc);

        lobby2_armageddon_npc = CitizensAPI.getNPCRegistry().createNPC(EntityType.PLAYER, colorCode("&c&lDEMON"));
        lobby2_armageddon_npc.setBukkitEntityType(EntityType.PLAYER);
        lobby2_armageddon_npc.setProtected(true);
        ArmageddonFaction.editNPC(lobby2_armageddon_npc);


        moveNPC();
        makeHolograms();
    }

    public static void unloadNPC(){
        deleteNPC();
        deleteHolograms();
    }

    private static void moveNPC(){
        if (!perm_upgrades_npc.isSpawned()) {
            perm_upgrades_npc.spawn(Locations.perm_upgrades_loc);
        }

        if (!non_perm_upgrades_npc.isSpawned()) {
            non_perm_upgrades_npc.spawn(Locations.non_perm_upgrades_loc);
        }

        if (!leaderboard_npc.isSpawned()) {
            leaderboard_npc.spawn(Locations.leaderboard_npc_loc);
        }

        if (!quest_npc.isSpawned()) {
            quest_npc.spawn(Locations.quest_npc_loc);
        }

        if (!prestige_npc.isSpawned()) {
            prestige_npc.spawn(Locations.prestige_npc_loc);
        }

        if (!lobby_perm_upgrades_npc.isSpawned()) {
            lobby_perm_upgrades_npc.spawn(Locations.lobby_perm_upgrades_loc);
        }

        if (!lobby_non_perm_upgrades_npc.isSpawned()) {
            lobby_non_perm_upgrades_npc.spawn(Locations.lobby_non_perm_upgrades_loc);
        }

        if (!lobby_leaderboard_npc.isSpawned()) {
            lobby_leaderboard_npc.spawn(Locations.lobby_leaderboard_npc_loc);
        }

        if (!lobby_quest_npc.isSpawned()) {
            lobby_quest_npc.spawn(Locations.lobby_quest_npc_loc);
        }

        if (!lobby_prestige_npc.isSpawned()) {
            lobby_prestige_npc.spawn(Locations.lobby_prestige_npc_loc);
        }

        if (!lobby2_perm_upgrades_npc.isSpawned()) {
            lobby2_perm_upgrades_npc.spawn(Locations.lobby2_perm_upgrades_loc);
        }

        if (!lobby2_non_perm_upgrades_npc.isSpawned()) {
            lobby2_non_perm_upgrades_npc.spawn(Locations.lobby2_non_perm_upgrades_loc);
        }

        if (!lobby2_leaderboard_npc.isSpawned()) {
            lobby2_leaderboard_npc.spawn(Locations.lobby2_leaderboard_npc_loc);
        }

        if (!lobby2_quest_npc.isSpawned()) {
            lobby2_quest_npc.spawn(Locations.lobby2_quest_npc_loc);
        }

        if (!lobby2_prestige_npc.isSpawned()) {
            lobby2_prestige_npc.spawn(Locations.lobby2_prestige_npc_loc);
        }

        if(!king_npc.isSpawned()){
            king_npc.spawn(Locations.getKingsQuestLocation(Bukkit.getWorld("world")));
        }

        if(!lobby_king_npc.isSpawned()){
            lobby_king_npc.spawn(Locations.getKingsQuestLocation(Bukkit.getWorld("lobby")));
        }

        if(!lobby2_king_npc.isSpawned()){
            lobby2_king_npc.spawn(Locations.getKingsQuestLocation(Bukkit.getWorld("lobby2")));
        }

        if(!armageddon_npc.isSpawned()){
            armageddon_npc.spawn(Locations.getArmageddonLocation(Bukkit.getWorld("world")));
        }

        if(!lobby_armageddon_npc.isSpawned()){
            lobby_armageddon_npc.spawn(Locations.getArmageddonLocation(Bukkit.getWorld("lobby")));
        }

        if(!lobby2_armageddon_npc.isSpawned()){
            lobby2_armageddon_npc.spawn(Locations.getArmageddonLocation(Bukkit.getWorld("lobby2")));
        }

        if(!archAngel_npc.isSpawned()){
            archAngel_npc.spawn(Locations.getArchAngelLocation(Bukkit.getWorld("world")));
        }

        if(!lobby_archAngel_npc.isSpawned()){
            lobby_archAngel_npc.spawn(Locations.getArchAngelLocation(Bukkit.getWorld("lobby")));
        }

        if(!lobby2_archAngel_npc.isSpawned()){
            lobby2_archAngel_npc.spawn(Locations.getArchAngelLocation(Bukkit.getWorld("lobby2")));
        }

        perm_upgrades_npc.teleport(Locations.perm_upgrades_loc, PlayerTeleportEvent.TeleportCause.PLUGIN);
        non_perm_upgrades_npc.teleport(Locations.non_perm_upgrades_loc, PlayerTeleportEvent.TeleportCause.PLUGIN);
        leaderboard_npc.teleport(Locations.leaderboard_npc_loc, PlayerTeleportEvent.TeleportCause.PLUGIN);
        quest_npc.teleport(Locations.quest_npc_loc, PlayerTeleportEvent.TeleportCause.PLUGIN);
        prestige_npc.teleport(Locations.prestige_npc_loc, PlayerTeleportEvent.TeleportCause.PLUGIN);

        lobby_perm_upgrades_npc.teleport(Locations.lobby_perm_upgrades_loc, PlayerTeleportEvent.TeleportCause.PLUGIN);
        lobby_non_perm_upgrades_npc.teleport(Locations.lobby_non_perm_upgrades_loc, PlayerTeleportEvent.TeleportCause.PLUGIN);
        lobby_leaderboard_npc.teleport(Locations.lobby_leaderboard_npc_loc, PlayerTeleportEvent.TeleportCause.PLUGIN);
        lobby_quest_npc.teleport(Locations.lobby_quest_npc_loc, PlayerTeleportEvent.TeleportCause.PLUGIN);
        lobby_prestige_npc.teleport(Locations.lobby_prestige_npc_loc, PlayerTeleportEvent.TeleportCause.PLUGIN);

        lobby2_perm_upgrades_npc.teleport(Locations.lobby2_perm_upgrades_loc, PlayerTeleportEvent.TeleportCause.PLUGIN);
        lobby2_non_perm_upgrades_npc.teleport(Locations.lobby2_non_perm_upgrades_loc, PlayerTeleportEvent.TeleportCause.PLUGIN);
        lobby2_leaderboard_npc.teleport(Locations.lobby2_leaderboard_npc_loc, PlayerTeleportEvent.TeleportCause.PLUGIN);
        lobby2_quest_npc.teleport(Locations.lobby2_quest_npc_loc, PlayerTeleportEvent.TeleportCause.PLUGIN);
        lobby2_prestige_npc.teleport(Locations.lobby2_prestige_npc_loc, PlayerTeleportEvent.TeleportCause.PLUGIN);

        Location temp_quest = Locations.quest_npc_loc;
        Location temp_leaderboard = Locations.leaderboard_npc_loc;
        Location temp_prestige = Locations.prestige_npc_loc;
        Location temp_perm_upgrades = Locations.perm_upgrades_loc;
        Location temp_non_perm_upgrades = Locations.non_perm_upgrades_loc;

        /*
        quest_npc.faceLocation(temp_quest.add(-1,0,0));
        leaderboard_npc.faceLocation(temp_leaderboard.add(-1,0,0));
        prestige_npc.faceLocation(temp_prestige.add(0,0,1));
        perm_upgrades_npc.faceLocation(temp_perm_upgrades.add(0,0,-1));
        non_perm_upgrades_npc.faceLocation(temp_non_perm_upgrades.add(0,0,-1));

         */

        Location lobby_temp_quest = Locations.lobby_quest_npc_loc;
        Location lobby_temp_leaderboard = Locations.lobby_leaderboard_npc_loc;
        Location lobby_temp_prestige = Locations.lobby_prestige_npc_loc;
        Location lobby_temp_perm_upgrades = Locations.lobby_perm_upgrades_loc;
        Location lobby_temp_non_perm_upgrades = Locations.lobby_non_perm_upgrades_loc;

        /*
        lobby_quest_npc.faceLocation(lobby_temp_quest.add(-1,0,0));
        lobby_leaderboard_npc.faceLocation(lobby_temp_leaderboard.add(-1,0,0));
        lobby_prestige_npc.faceLocation(lobby_temp_prestige.add(0,0,1));
        lobby_perm_upgrades_npc.faceLocation(lobby_temp_perm_upgrades.add(0,0,-1));
        lobby_non_perm_upgrades_npc.faceLocation(lobby_temp_non_perm_upgrades.add(0,0,-1));

         */

        Location lobby2_temp_quest = Locations.lobby2_quest_npc_loc;
        Location lobby2_temp_leaderboard = Locations.lobby2_leaderboard_npc_loc;
        Location lobby2_temp_prestige = Locations.lobby2_prestige_npc_loc;
        Location lobby2_temp_perm_upgrades = Locations.lobby2_perm_upgrades_loc;
        Location lobby2_temp_non_perm_upgrades = Locations.lobby2_non_perm_upgrades_loc;

        /*
        lobby2_quest_npc.faceLocation(lobby2_temp_quest.add(-1,0,0));
        lobby2_leaderboard_npc.faceLocation(lobby2_temp_leaderboard.add(-1,0,0));
        lobby2_prestige_npc.faceLocation(lobby2_temp_prestige.add(0,0,1));
        lobby2_perm_upgrades_npc.faceLocation(lobby2_temp_perm_upgrades.add(0,0,-1));
        lobby2_non_perm_upgrades_npc.faceLocation(lobby2_temp_non_perm_upgrades.add(0,0,-1));

         */
    }

    private static void makeHolograms(){
        perm_upgrades_hologram = HologramsAPI.createHologram(KitPvP.INSTANCE, Locations.perm_upgrades_loc.add(0,2.75,0));
        perm_upgrades_hologram.appendTextLine( ChatColor.translateAlternateColorCodes('&', "&a&lUPGRADES"));
        non_perm_upgrades_hologram = HologramsAPI.createHologram(KitPvP.INSTANCE, Locations.non_perm_upgrades_loc.add(0,2.75,0));
        non_perm_upgrades_hologram.appendTextLine( ChatColor.translateAlternateColorCodes('&', "&6&lITEMS"));
        leaderboard_hologram = HologramsAPI.createHologram(KitPvP.INSTANCE, Locations.leaderboard_npc_loc.add(0,2.75,0));
        leaderboard_hologram.appendTextLine( ChatColor.translateAlternateColorCodes('&', "&3&lSTATS"));
        quest_hologram = HologramsAPI.createHologram(KitPvP.INSTANCE, Locations.quest_npc_loc.add(0,2.75,0));
        quest_hologram.appendTextLine( ChatColor.translateAlternateColorCodes('&', "&b&lQUEST MASTER"));
        prestige_hologram = HologramsAPI.createHologram(KitPvP.INSTANCE, Locations.prestige_npc_loc.add(0,2.75,0));
        prestige_hologram.appendTextLine( ChatColor.translateAlternateColorCodes('&', "&e&lPRESTIGE"));

        ender_chest = HologramsAPI.createHologram(KitPvP.INSTANCE, Locations.getEnderChestLocation(Bukkit.getWorld("world")).add(0,-.75,0));
        ender_chest.appendTextLine( ChatColor.translateAlternateColorCodes('&', "&5&lENDER CHEST"));

        ender_chest_lore = HologramsAPI.createHologram(KitPvP.INSTANCE, Locations.getEnderChestLocation(Bukkit.getWorld("world")).add(0,-1,0));
        ender_chest_lore.appendTextLine( ChatColor.translateAlternateColorCodes('&', "&7Store items forever"));

        mystic_well = HologramsAPI.createHologram(KitPvP.INSTANCE, Locations.getMysticWellLocation(Bukkit.getWorld("world")).add(0,-.75,0));
        mystic_well.appendTextLine( ChatColor.translateAlternateColorCodes('&', "&d&lMYSTIC WELL"));

        mystic_well_lore = HologramsAPI.createHologram(KitPvP.INSTANCE, Locations.getMysticWellLocation(Bukkit.getWorld("world")).add(0,-1,0));
        mystic_well_lore.appendTextLine( ChatColor.translateAlternateColorCodes('&', "&7item enchants"));

        lobby_perm_upgrades_hologram = HologramsAPI.createHologram(KitPvP.INSTANCE, Locations.lobby_perm_upgrades_loc.add(0,2.75,0));
        lobby_perm_upgrades_hologram.appendTextLine( ChatColor.translateAlternateColorCodes('&', "&a&lUPGRADES"));
        lobby_non_perm_upgrades_hologram = HologramsAPI.createHologram(KitPvP.INSTANCE, Locations.lobby_non_perm_upgrades_loc.add(0,2.75,0));
        lobby_non_perm_upgrades_hologram.appendTextLine( ChatColor.translateAlternateColorCodes('&', "&6&lITEMS"));
        lobby_leaderboard_hologram = HologramsAPI.createHologram(KitPvP.INSTANCE, Locations.lobby_leaderboard_npc_loc.add(0,2.75,0));
        lobby_leaderboard_hologram.appendTextLine( ChatColor.translateAlternateColorCodes('&', "&3&lSTATS"));
        lobby_quest_hologram = HologramsAPI.createHologram(KitPvP.INSTANCE, Locations.lobby_quest_npc_loc.add(0,2.75,0));
        lobby_quest_hologram.appendTextLine( ChatColor.translateAlternateColorCodes('&', "&b&lQUEST MASTER"));
        lobby_prestige_hologram = HologramsAPI.createHologram(KitPvP.INSTANCE, Locations.lobby_prestige_npc_loc.add(0,2.75,0));
        lobby_prestige_hologram.appendTextLine( ChatColor.translateAlternateColorCodes('&', "&e&lPRESTIGE"));

        lobby_ender_chest = HologramsAPI.createHologram(KitPvP.INSTANCE, Locations.getEnderChestLocation(Bukkit.getWorld("lobby")).add(0,-.75,0));
        lobby_ender_chest.appendTextLine( ChatColor.translateAlternateColorCodes('&', "&5&lENDER CHEST"));

        lobby_ender_chest_lore = HologramsAPI.createHologram(KitPvP.INSTANCE, Locations.getEnderChestLocation(Bukkit.getWorld("lobby")).add(0,-1,0));
        lobby_ender_chest_lore.appendTextLine( ChatColor.translateAlternateColorCodes('&', "&7Store items forever"));

        lobby_mystic_well = HologramsAPI.createHologram(KitPvP.INSTANCE, Locations.getMysticWellLocation(Bukkit.getWorld("lobby")).add(0,-.75,0));
        lobby_mystic_well.appendTextLine( ChatColor.translateAlternateColorCodes('&', "&d&lMYSTIC WELL"));

        lobby_mystic_well_lore = HologramsAPI.createHologram(KitPvP.INSTANCE, Locations.getMysticWellLocation(Bukkit.getWorld("lobby")).add(0,-1,0));
        lobby_mystic_well_lore.appendTextLine( ChatColor.translateAlternateColorCodes('&', "&7item enchants"));


        lobby2_perm_upgrades_hologram = HologramsAPI.createHologram(KitPvP.INSTANCE, Locations.lobby2_perm_upgrades_loc.add(0,2.75,0));
        lobby2_perm_upgrades_hologram.appendTextLine( ChatColor.translateAlternateColorCodes('&', "&a&lUPGRADES"));
        lobby2_non_perm_upgrades_hologram = HologramsAPI.createHologram(KitPvP.INSTANCE, Locations.lobby2_non_perm_upgrades_loc.add(0,2.75,0));
        lobby2_non_perm_upgrades_hologram.appendTextLine( ChatColor.translateAlternateColorCodes('&', "&6&lITEMS"));
        lobby2_leaderboard_hologram = HologramsAPI.createHologram(KitPvP.INSTANCE, Locations.lobby2_leaderboard_npc_loc.add(0,2.75,0));
        lobby2_leaderboard_hologram.appendTextLine( ChatColor.translateAlternateColorCodes('&', "&3&lSTATS"));
        lobby2_quest_hologram = HologramsAPI.createHologram(KitPvP.INSTANCE, Locations.lobby2_quest_npc_loc.add(0,2.75,0));
        lobby2_quest_hologram.appendTextLine( ChatColor.translateAlternateColorCodes('&', "&b&lQUEST MASTER"));
        lobby2_prestige_hologram = HologramsAPI.createHologram(KitPvP.INSTANCE, Locations.lobby2_prestige_npc_loc.add(0,2.75,0));
        lobby2_prestige_hologram.appendTextLine( ChatColor.translateAlternateColorCodes('&', "&e&lPRESTIGE"));

        lobby2_ender_chest = HologramsAPI.createHologram(KitPvP.INSTANCE, Locations.getEnderChestLocation(Bukkit.getWorld("lobby2")).add(0,-.75,0));
        lobby2_ender_chest.appendTextLine( ChatColor.translateAlternateColorCodes('&', "&5&lENDER CHEST"));

        lobby2_ender_chest_lore = HologramsAPI.createHologram(KitPvP.INSTANCE, Locations.getEnderChestLocation(Bukkit.getWorld("lobby2")).add(0,-1,0));
        lobby2_ender_chest_lore.appendTextLine( ChatColor.translateAlternateColorCodes('&', "&7Store items forever"));

        lobby2_mystic_well = HologramsAPI.createHologram(KitPvP.INSTANCE, Locations.getMysticWellLocation(Bukkit.getWorld("lobby2")).add(0,-.75,0));
        lobby2_mystic_well.appendTextLine( ChatColor.translateAlternateColorCodes('&', "&d&lMYSTIC WELL"));

        lobby2_mystic_well_lore = HologramsAPI.createHologram(KitPvP.INSTANCE, Locations.getMysticWellLocation(Bukkit.getWorld("lobby2")).add(0,-1,0));
        lobby2_mystic_well_lore.appendTextLine( ChatColor.translateAlternateColorCodes('&', "&7item enchants"));

        BetterPit = HologramsAPI.createHologram(KitPvP.INSTANCE, Locations.getBetterPitLocation(Bukkit.getWorld("world")));
        BetterPit.appendTextLine( ChatColor.translateAlternateColorCodes('&', "&e&lUNLOCKED FEATURES"));
        BetterPit.appendTextLine( ChatColor.translateAlternateColorCodes('&', ""));
        BetterPit.appendTextLine( ChatColor.translateAlternateColorCodes('&', "&7[1] &b/respawn"));
        BetterPit.appendTextLine( ChatColor.translateAlternateColorCodes('&', "&7[5] &b/play pit"));
        BetterPit.appendTextLine( ChatColor.translateAlternateColorCodes('&', "&7[&910&7] &bUpgrades"));
        BetterPit.appendTextLine( ChatColor.translateAlternateColorCodes('&', "&7[&915&7] &bEnder chest"));
        BetterPit.appendTextLine( ChatColor.translateAlternateColorCodes('&', "&7[&230&7] &bContracts"));
        BetterPit.appendTextLine( ChatColor.translateAlternateColorCodes('&', "&7[&e50&7] &bStats"));
        BetterPit.appendTextLine( ChatColor.translateAlternateColorCodes('&', "&7[&660&7] &b/trade"));
        BetterPit.appendTextLine( ChatColor.translateAlternateColorCodes('&', "&7[&c70&7] &b/view"));
        BetterPit.appendTextLine( ChatColor.translateAlternateColorCodes('&', "&7[&b120&7] &bPrestige"));
        BetterPit.appendTextLine( ChatColor.translateAlternateColorCodes('&', "&7Gain levels to unlock more"));


        LobbyBetterPit = HologramsAPI.createHologram(KitPvP.INSTANCE, Locations.getBetterPitLocation(Bukkit.getWorld("lobby")));
        LobbyBetterPit.appendTextLine( ChatColor.translateAlternateColorCodes('&', "&e&lUNLOCKED FEATURES"));
        LobbyBetterPit.appendTextLine( ChatColor.translateAlternateColorCodes('&', ""));
        LobbyBetterPit.appendTextLine( ChatColor.translateAlternateColorCodes('&', "&7[1] &b/respawn"));
        LobbyBetterPit.appendTextLine( ChatColor.translateAlternateColorCodes('&', "&7[5] &b/play pit"));
        LobbyBetterPit.appendTextLine( ChatColor.translateAlternateColorCodes('&', "&7[&910&7] &bUpgrades"));
        LobbyBetterPit.appendTextLine( ChatColor.translateAlternateColorCodes('&', "&7[&915&7] &bEnder chest"));
        LobbyBetterPit.appendTextLine( ChatColor.translateAlternateColorCodes('&', "&7[&230&7] &bContracts"));
        LobbyBetterPit.appendTextLine( ChatColor.translateAlternateColorCodes('&', "&7[&e50&7] &bStats"));
        LobbyBetterPit.appendTextLine( ChatColor.translateAlternateColorCodes('&', "&7[&660&7] &b/trade"));
        LobbyBetterPit.appendTextLine( ChatColor.translateAlternateColorCodes('&', "&7[&c70&7] &b/view"));
        LobbyBetterPit.appendTextLine( ChatColor.translateAlternateColorCodes('&', "&7[&b120&7] &bPrestige"));
        LobbyBetterPit.appendTextLine( ChatColor.translateAlternateColorCodes('&', "&7Gain levels to unlock more"));



        Lobby2BetterPit = HologramsAPI.createHologram(KitPvP.INSTANCE, Locations.getBetterPitLocation(Bukkit.getWorld("lobby2")));
        Lobby2BetterPit.appendTextLine( ChatColor.translateAlternateColorCodes('&', "&e&lUNLOCKED FEATURES"));
        Lobby2BetterPit.appendTextLine( ChatColor.translateAlternateColorCodes('&', ""));
        Lobby2BetterPit.appendTextLine( ChatColor.translateAlternateColorCodes('&', "&7[1] &b/respawn"));
        Lobby2BetterPit.appendTextLine( ChatColor.translateAlternateColorCodes('&', "&7[5] &b/play pit"));
        Lobby2BetterPit.appendTextLine( ChatColor.translateAlternateColorCodes('&', "&7[&910&7] &bUpgrades"));
        Lobby2BetterPit.appendTextLine( ChatColor.translateAlternateColorCodes('&', "&7[&915&7] &bEnder chest"));
        Lobby2BetterPit.appendTextLine( ChatColor.translateAlternateColorCodes('&', "&7[&230&7] &bContracts"));
        Lobby2BetterPit.appendTextLine( ChatColor.translateAlternateColorCodes('&', "&7[&e50&7] &bStats"));
        Lobby2BetterPit.appendTextLine( ChatColor.translateAlternateColorCodes('&', "&7[&660&7] &b/trade"));
        Lobby2BetterPit.appendTextLine( ChatColor.translateAlternateColorCodes('&', "&7[&c70&7] &b/view"));
        Lobby2BetterPit.appendTextLine( ChatColor.translateAlternateColorCodes('&', "&7[&b120&7] &bPrestige"));
        Lobby2BetterPit.appendTextLine( ChatColor.translateAlternateColorCodes('&', "&7Gain levels to unlock more"));

        JumpBetterPit = HologramsAPI.createHologram(KitPvP.INSTANCE, Locations.getPlayPitLocation(Bukkit.getWorld("world")));
        JumpBetterPit.appendTextLine( ChatColor.translateAlternateColorCodes('&', "&eThe Better Pit"));
        JumpBetterPit.appendTextLine( ChatColor.translateAlternateColorCodes('&', "&a&lJUMP! &c&lFIGHT!"));

        LobbyJumpBetterPit = HologramsAPI.createHologram(KitPvP.INSTANCE, Locations.getPlayPitLocation(Bukkit.getWorld("world")));
        LobbyJumpBetterPit.appendTextLine( ChatColor.translateAlternateColorCodes('&', "&eThe Better Pit"));
        LobbyJumpBetterPit.appendTextLine( ChatColor.translateAlternateColorCodes('&', "&a&lJUMP! &c&lFIGHT!"));

        Lobby2JumpBetterPit = HologramsAPI.createHologram(KitPvP.INSTANCE, Locations.getPlayPitLocation(Bukkit.getWorld("world")));
        Lobby2JumpBetterPit.appendTextLine( ChatColor.translateAlternateColorCodes('&', "&eThe Better Pit"));
        Lobby2JumpBetterPit.appendTextLine( ChatColor.translateAlternateColorCodes('&', "&a&lJUMP! &c&lFIGHT!"));


    }

    private static void deleteHolograms(){
        perm_upgrades_hologram.delete();
        non_perm_upgrades_hologram.delete();
        leaderboard_hologram.delete();
        quest_hologram.delete();
        prestige_hologram.delete();
        ender_chest.delete();
        ender_chest_lore.delete();
        mystic_well.delete();
        mystic_well_lore.delete();

        lobby_perm_upgrades_hologram.delete();
        lobby_non_perm_upgrades_hologram.delete();
        lobby_leaderboard_hologram.delete();
        lobby_quest_hologram.delete();
        lobby_prestige_hologram.delete();
        lobby_ender_chest.delete();
        lobby_ender_chest_lore.delete();
        lobby_mystic_well.delete();
        lobby_mystic_well_lore.delete();

        lobby2_perm_upgrades_hologram.delete();
        lobby2_non_perm_upgrades_hologram.delete();
        lobby2_leaderboard_hologram.delete();
        lobby2_quest_hologram.delete();
        lobby2_prestige_hologram.delete();
        lobby2_ender_chest.delete();
        lobby2_ender_chest_lore.delete();
        lobby2_mystic_well.delete();
        lobby2_mystic_well_lore.delete();

        BetterPit.delete();
        LobbyBetterPit.delete();
        Lobby2BetterPit.delete();

        JumpBetterPit.delete();
        LobbyJumpBetterPit.delete();
        Lobby2JumpBetterPit.delete();
    }

    private static void deleteNPC(){
        perm_upgrades_npc.despawn();
        CitizensAPI.getNPCRegistry().deregister(perm_upgrades_npc);

        non_perm_upgrades_npc.despawn();
        CitizensAPI.getNPCRegistry().deregister(non_perm_upgrades_npc);

        leaderboard_npc.despawn();
        CitizensAPI.getNPCRegistry().deregister(leaderboard_npc);

        quest_npc.despawn();
        CitizensAPI.getNPCRegistry().deregister(quest_npc);

        prestige_npc.despawn();
        CitizensAPI.getNPCRegistry().deregister(prestige_npc);

        lobby_perm_upgrades_npc.despawn();
        CitizensAPI.getNPCRegistry().deregister(lobby_perm_upgrades_npc);

        lobby_non_perm_upgrades_npc.despawn();
        CitizensAPI.getNPCRegistry().deregister(lobby_non_perm_upgrades_npc);

        lobby_leaderboard_npc.despawn();
        CitizensAPI.getNPCRegistry().deregister(lobby_leaderboard_npc);

        lobby_quest_npc.despawn();
        CitizensAPI.getNPCRegistry().deregister(lobby_quest_npc);

        lobby_prestige_npc.despawn();
        CitizensAPI.getNPCRegistry().deregister(lobby_prestige_npc);

        lobby2_perm_upgrades_npc.despawn();
        CitizensAPI.getNPCRegistry().deregister(lobby2_perm_upgrades_npc);

        lobby2_non_perm_upgrades_npc.despawn();
        CitizensAPI.getNPCRegistry().deregister(lobby2_non_perm_upgrades_npc);

        lobby2_leaderboard_npc.despawn();
        CitizensAPI.getNPCRegistry().deregister(lobby2_leaderboard_npc);

        lobby2_quest_npc.despawn();
        CitizensAPI.getNPCRegistry().deregister(lobby2_quest_npc);

        lobby2_prestige_npc.despawn();
        CitizensAPI.getNPCRegistry().deregister(lobby2_prestige_npc);

        king_npc.despawn();
        CitizensAPI.getNPCRegistry().deregister(king_npc);

        lobby_king_npc.despawn();
        CitizensAPI.getNPCRegistry().deregister(lobby_king_npc);

        lobby_king_npc.despawn();
        CitizensAPI.getNPCRegistry().deregister(lobby_king_npc);

        archAngel_npc.despawn();
        CitizensAPI.getNPCRegistry().deregister(archAngel_npc);

        lobby_archAngel_npc.despawn();
        CitizensAPI.getNPCRegistry().deregister(lobby_archAngel_npc);

        lobby2_archAngel_npc.despawn();
        CitizensAPI.getNPCRegistry().deregister(lobby2_archAngel_npc);

        armageddon_npc.despawn();
        CitizensAPI.getNPCRegistry().deregister(armageddon_npc);

        lobby_armageddon_npc.despawn();
        CitizensAPI.getNPCRegistry().deregister(lobby_armageddon_npc);

        lobby2_armageddon_npc.despawn();
        CitizensAPI.getNPCRegistry().deregister(lobby2_armageddon_npc);
    }

}
