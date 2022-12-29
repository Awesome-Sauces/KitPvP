package me.alpha.kitpvp.PitRemake.Factions;

import me.alpha.kitpvp.Data.ClassInstances;
import me.alpha.kitpvp.PitRemake.ItemStacks.enchants;
import me.alpha.kitpvp.PitRemake.PitCommands.Stash.StashCore;
import me.alpha.kitpvp.utils.Sounds;
import me.alpha.kitpvp.utils.advancedInventory;
import net.citizensnpcs.api.npc.NPC;
import net.citizensnpcs.trait.LookClose;
import net.citizensnpcs.trait.SkinTrait;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.Collections;

import static me.alpha.kitpvp.utils.ColorUtil.colorCode;
import static me.alpha.kitpvp.utils.advancedInventory.ClayMaker;
import static me.alpha.kitpvp.utils.advancedInventory.ItemMaker;

public class ArchAngelFaction implements Listener {
    public static void editNPC(NPC npc){
        skin(npc);
    }

    public static boolean joinFaction(Player player){
        String uuid = player.getUniqueId().toString();

        if(ClassInstances.factionData.hasValue(uuid) &&
                ClassInstances.factionData.getValue(uuid).toString().equals("angel")) return true;

        if(ClassInstances.factionData.hasValue(uuid) &&
                ClassInstances.factionData.getValue(uuid).toString().equals("demon")) {

            if(ClassInstances.renownData.getRenown(uuid)>=500){
                player.sendMessage(colorCode("&e&lFACTION! &7You switched factions and reset all your points!"));
                Sounds.EXE.play(player);
                ClassInstances.factionData.setValue(uuid, "angel");
                ClassInstances.botKills.setValue(uuid, 0);
                player.sendMessage(colorCode("&b&lPLEDGED! &7You joined the &bAngel &7Faction!"));
                ClassInstances.factionReward.setValue(uuid, "NONE");
                ClassInstances.renownData.setRenown(uuid, ClassInstances.renownData.getRenown(uuid)-500);
            }else{
                player.sendMessage(colorCode("&e&lFACTION! &7You can't afford this!"));
                Sounds.ERROR.play(player);
            }
        }

        if(ClassInstances.factionData.hasValue(uuid) &&
                ClassInstances.factionData.getValue(uuid).toString().equals("NONE")){
            ClassInstances.factionData.setValue(uuid, "angel");
            ClassInstances.botKills.setValue(uuid, 0);
            player.sendMessage(colorCode("&b&lPLEDGED! &7You joined the &bAngel &7Faction!"));
            return true;
        }

        return false;
    }

    public static ItemStack getTierI(Player player, boolean isAngel, int botKills){
        if(isAngel&&botKills>=300) return ClayMaker((short)3,ChatColor.AQUA+"Tier I Perk",colorCode("&8Angel Faction\n\n" +
                "&7Deal &c+1 &7damage vs.\n" +
                "&cDemon &7players.\n\n" +
                "&aUnlocked!"));
        else if(isAngel) return ClayMaker(ChatColor.AQUA+"Tier I Perk",colorCode("&8Angel Faction\n\n" +
                "&7Deal &c+1 &7damage vs.\n" +
                "&cDemon &7players.\n\n" +
                "&bRequires 300 points"));
        else return ClayMaker(ChatColor.AQUA+"Tier I Perk",colorCode("&8Angel Faction\n\n" +
                    "&7Deal &c+1 &7damage vs.\n" +
                    "&cDemon &7players.\n\n" +
                    "&bPledge to unlock!"));
    }

    public static ItemStack getTierII(Player player, boolean isAngel, int botKills){
        if(isAngel&&botKills>=1000) return ClayMaker((short)3,ChatColor.AQUA+"Tier II Perk", colorCode("&8Angel Faction\n\n" +
                "&7Unlock the &bAngel &7spawn\n\n" +
                "&aUnlocked!"));
        else if(isAngel) return ClayMaker(ChatColor.AQUA+"Tier II Perk", colorCode("&8Angel Faction\n\n" +
                "&7Unlock the &bAngel &7spawn\n\n" +
                "&bRequires 1,000 points"));
        else return ClayMaker(ChatColor.AQUA+"Tier II Perk", colorCode("&8Angel Faction\n\n" +
                    "&7Unlock the &bAngel &7spawn\n\n" +
                    "&bPledge to unlock!"));
    }

    public static ItemStack getTierIII(Player player, boolean isAngel, int botKills) {
        if(isAngel&&botKills>=2500) return ClayMaker((short)3,ChatColor.AQUA+"Tier III Perk", colorCode("&8Angel Faction\n\n" +
                "&7Diamond items cost &b1/3\n" +
                "&7of the price.\n\n" +
                "&aUnlocked!"));
        else if(isAngel) return ClayMaker(ChatColor.AQUA+"Tier III Perk", colorCode("&8Angel Faction\n\n" +
                "&7Diamond items cost &b1/3\n" +
                "&7of the price.\n\n" +
                "&bRequires 2,500 points"));
        else return ClayMaker(ChatColor.AQUA+"Tier III Perk", colorCode("&8Angel Faction\n\n" +
                    "&7Diamond items cost &b1/3\n" +
                    "&7of the price.\n\n" +
                    "&bPledge to unlock!"));
    }

    public static ItemStack getTierIV(Player player, boolean isAngel, int botKills){
        if(isAngel&&botKills>=7000) return ClayMaker((short)3,ChatColor.AQUA+"Tier IV Perk", colorCode("&8Angel Faction\n\n" +
                "&7Deal &c+0.5 &7damage vs.\n" +
                "&7players wearing leather\n&7armor.\n\n" +
                "&aUnlocked!"));
        else if(isAngel) return ClayMaker(ChatColor.AQUA+"Tier IV Perk", colorCode("&8Angel Faction\n\n" +
                "&7Deal &c+0.5 &7damage vs.\n" +
                "&7players wearing leather\n&7armor.\n\n" +
                "&bRequires 7,000 points"));
        else return ClayMaker(ChatColor.AQUA+"Tier IV Perk", colorCode("&8Angel Faction\n\n" +
                    "&7Deal &c+0.5 &7damage vs.\n" +
                    "&7players wearing leather\n&7armor.\n\n" +
                    "&bPledge to unlock!"));
    }

    public static ItemStack getTierV(Player player, boolean isAngel, int botKills){
        if(isAngel&&botKills>=15000) return ClayMaker((short)3,ChatColor.AQUA+"Tier V Perk", colorCode("&8Angel Faction\n\n" +
                "&7Accumulate &6+50% &7gold on\n" +
                "&7your bounties.\n\n" +
                "&7Also, earn &e+1 Renown\n" +
                "&7when earning Renown from\n" +
                "&7events.\n\n" +
                "&aUnlocked!"));
        else if(isAngel) return ClayMaker(ChatColor.AQUA+"Tier V Perk", colorCode("&8Angel Faction\n\n" +
                "&7Accumulate &6+50% &7gold on\n" +
                "&7your bounties.\n\n" +
                "&7Also, earn &e+1 Renown\n" +
                "&7when earning Renown from\n" +
                "&7events.\n\n" +
                "&bRequires 15,000 points"));
        else return ClayMaker(ChatColor.AQUA+"Tier V Perk", colorCode("&8Angel Faction\n\n" +
                    "&7Accumulate &6+50% &7gold on\n" +
                    "&7your bounties.\n\n" +
                    "&7Also, earn &e+1 Renown\n" +
                    "&7when earning Renown from\n" +
                    "&7events.\n\n" +
                    "&bPledge to unlock!"));
    }

    public static ItemStack getTierVI(Player player, boolean isAngel, int botKills, String reward){
        if(reward.equals("unclaimed")) return ItemMaker(Material.GOLD_BLOCK,ChatColor.AQUA+"Tier VI Perk", colorCode("&8Angel Faction\n\n" +
                    "&7Earn &bArchangel Chestplate\n&8Diamond Chestplate with lives\n\n" +
                    "&eClick to claim!"),1,true);

        if(isAngel&&botKills>=40000) return ClayMaker((short)3,ChatColor.AQUA+"Tier VI Perk", colorCode("&8Angel Faction\n\n" +
                "&7Earn &bArchangel Chestplate\n&8Diamond Chestplate with lives\n\n" +
                "&aUnlocked!"));
        else if(isAngel) return ClayMaker(ChatColor.AQUA+"Tier VI Perk", colorCode("&8Angel Faction\n\n" +
                "&7Earn &bArchangel Chestplate\n&8Diamond Chestplate with lives\n\n" +
                "&bRequires 40,000 points"));
        else return ClayMaker(ChatColor.AQUA+"Tier VI Perk", colorCode("&8Angel Faction\n\n" +
                    "&7Earn &bArchangel Chestplate\n&8Diamond Chestplate with lives\n\n" +
                    "&bPledge to unlock!"));
    }

    public static ItemStack getTierVII(Player player, boolean isAngel, int botKills){
        if(isAngel&&botKills>=70000) return ClayMaker((short) 3,ChatColor.AQUA+"Tier VII Perk", colorCode("&8Angel Faction\n\n" +
                "&7Permanently gain &b+1% XP &7from kills\n" +
                "&8Can be claimed up to 15 times\n\n" +
                "&aUnlocked!"));
        else if(isAngel) return ClayMaker(ChatColor.AQUA+"Tier VII Perk", colorCode("&8Angel Faction\n\n" +
                "&7Permanently gain &b+1% XP &7from kills\n" +
                "&8Can be claimed up to 15 times\n\n" +
                "&bRequires 70,000 points"));
        else return ClayMaker(ChatColor.AQUA+"Tier VII Perk", colorCode("&8Angel Faction\n\n" +
                "&7Permanently gain &b+1% XP &7from kills\n" +
                "&8Can be claimed up to 15 times\n\n" +
                "&bPledge to unlock!"));
    }

    public static ItemStack getJoinButton(Player player, boolean isAngel){
        if(isAngel) return ItemMaker(Material.WOOL, ChatColor.AQUA+"Allegiance Pledged",
                colorCode("&8Angel\n\n" +
                        "&7You pleged allegiance to\n" +
                        "&7this faction.\n\n" +
                        "&7You have: &b"+ClassInstances.botKills.getValue(player.getUniqueId().toString(), 1).toString()+" points\n\n" +
                        "&b1 bot kill = 1 point"), 1, true);
        else if(ClassInstances.factionData.getValue(player.getUniqueId().toString(), "NONE").equals("demon")) return ItemMaker(Material.WOOL, ChatColor.AQUA+"Allegiance Pledged",
                colorCode("&8Angel\n\n" +
                        "&7Join this faction, earn\n" +
                        "&7points from kills and earn\n" +
                        "&7exclusive perks.\n\n" +
                        "&7Cost: &e500 Renown\n" +
                        "&7You have: &e"+ ClassInstances.renownData.getRenown(player.getUniqueId().toString())+" Renown\n\n" +
                        "&eClick to join!"), 1, true);
        else return ItemMaker(Material.WOOL, ChatColor.AQUA+"Allegiance Pledged",
                colorCode("&8Angel\n\n" +
                        "&7Join this faction, earn\n" +
                        "&7points from kills and earn\n" +
                        "&7exclusive perks.\n\n" +
                        "&eClick to join!"), 1, true);
    }

    public static ItemStack getRestartButton(Player player, boolean isAngel, int botKills){
        if(isAngel && botKills>=70000) return ItemMaker(Material.SEA_LANTERN, ChatColor.GREEN+"Restart Progress",
                    colorCode("&7Click this button to\n" +
                            "&7restart all progress and\n" +
                            "&7gain all rewards again.\n" +
                            "&8Allows you to reclaim all rewards\n\n" +
                            "&7Cost: &e250 Renown\n" +
                            "&7You have: &e"+ ClassInstances.renownData.getRenown(player.getUniqueId().toString())+" Renown\n\n" +
                            "&eClick to restart!"), 1, true);
        else return ItemMaker(Material.SEA_LANTERN, ChatColor.GREEN+"Restart Progress",
                colorCode("&7Click this button to\n" +
                        "&7gain all rewards again.\n" +
                        "&8Allows you to reclaim all rewards\n\n" +
                        "&cReach max tier first!"), 1, true);
    }

    @EventHandler
    public void clickInventory(InventoryClickEvent event) {
        if (event != null && event.getClickedInventory() != null &&
                event.getClickedInventory().getTitle() != null &&
                event.getClickedInventory().getTitle().equals(ChatColor.GRAY + "Angel")) {
            event.setCancelled(true);

            Player player = (Player) event.getWhoClicked();
            String uuid = player.getUniqueId().toString();

            ItemStack clicked = event.getCurrentItem();

            if(clicked==null || clicked.getItemMeta()==null && clicked.getItemMeta().getDisplayName()==null) return;


            String displayName = clicked.getItemMeta().getDisplayName();

            if(displayName.equals(ChatColor.AQUA+"Allegiance Pledged")) {
                joinFaction(player);
                openInventory(player);
                return;
            }

            if(clicked.getType().equals(Material.SEA_LANTERN)){
                if(((int)ClassInstances.botKills.getValue(uuid, 1))>=70000 &&
                        ClassInstances.renownData.getRenown(uuid)>=250
                ){
                    ClassInstances.factionReward.setValue(uuid, "NONE");
                    ClassInstances.botKills.setValue(uuid, 0);
                    player.sendMessage(colorCode("&e&lFACTION! &7Successfully reset all faction points!"));
                    Sounds.EXE.play(player);
                    openInventory(player);
                }
            }

            if(clicked.getType().equals(Material.GOLD_BLOCK)){
                ClassInstances.factionReward.setValue(player.getUniqueId().toString(), "claimed");
                StashCore.safeGive(player, enchants.archAngel);
                openInventory(player);
            }

        }
    }

    public static Inventory openInventory(Player player){
        String uuid = String.valueOf(player.getUniqueId());
        Inventory gui = advancedInventory.inv(player, 45, ChatColor.GRAY + "Angel");
        ItemStack base_glass = advancedInventory.cGlass();

        for (int i = 0; i < 10; i++) {
            advancedInventory.addInv(gui, base_glass, i, 1, false);
            advancedInventory.addInv(gui, base_glass, i, 2, false);
            advancedInventory.addInv(gui, base_glass, i, 3, false);
            advancedInventory.addInv(gui, base_glass, i, 4, false);
            advancedInventory.addInv(gui, base_glass, i, 5, false);
        }

        boolean isAngel = ClassInstances.factionData.getValue(player.getUniqueId().toString(), "NONE").toString().equals("angel");
        int botKills = (int) ClassInstances.botKills.getValue(player.getUniqueId().toString(), 1);
        String reward = ClassInstances.factionReward.getValue(player.getUniqueId().toString(), "NONE").toString();

        advancedInventory.addInv(gui, getTierI(player, isAngel, botKills), 2,2,false);
        advancedInventory.addInv(gui, getTierII(player, isAngel, botKills), 3,2,false);
        advancedInventory.addInv(gui, getTierIII(player, isAngel, botKills), 4,2,false);
        advancedInventory.addInv(gui, getTierIV(player, isAngel, botKills), 5,2,false);
        advancedInventory.addInv(gui, getTierV(player, isAngel, botKills), 6,2,false);
        advancedInventory.addInv(gui, getTierVI(player, isAngel, botKills, reward), 7,2,false);
        advancedInventory.addInv(gui, getTierVII(player, isAngel, botKills), 8,2,false);

        advancedInventory.addInv(gui, getJoinButton(player, isAngel), 3,4,false);
        advancedInventory.addInv(gui, getRestartButton(player, isAngel, botKills), 7,4,false);

        player.openInventory(gui);

        return gui;
    }

    private static void skin(NPC npc) {
        SkinTrait skinTrait = npc.getTrait(SkinTrait.class);
        skinTrait.setSkinName("x_Auriel_x");

        LookClose lookClose = npc.getTrait(LookClose.class);
        lookClose.lookClose(true);
    }

    /*
    public Iterable<Player> getNearbyPlayers(Location base, double dist) {
        PhTreeF<Player> tree = (PhTreeF)this.worlds.get(base.getWorld().getUID());
        return (Iterable)(tree == null ? Collections.emptyList() : () -> {
            return tree.rangeQuery(dist, new double[]{base.getX(), base.getY(), base.getZ()});
        });
    }

     */

}
