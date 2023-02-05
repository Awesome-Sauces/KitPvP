package me.alpha.kitpvp.PitRemake.Perks.gui;

import me.alpha.kitpvp.CustomEvents.ReduxInventoryEvent;
import me.alpha.kitpvp.Data.ClassInstances;
import me.alpha.kitpvp.Data.GoldData;
import me.alpha.kitpvp.PitRemake.ItemStacks.enchants;
import me.alpha.kitpvp.PitRemake.PitCommands.Stash.StashCore;
import me.alpha.kitpvp.utils.Sounds;
import me.alpha.kitpvp.utils.advancedInventory;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import static me.alpha.kitpvp.utils.ColorUtil.colorCode;
import static me.alpha.kitpvp.utils.advancedInventory.ClayMaker;
import static me.alpha.kitpvp.utils.advancedInventory.ItemMaker;

public class KillStreakPerkGUI implements Listener {
    @EventHandler
    public void clickInventory(ReduxInventoryEvent event) {
        if(!event.getInventory().getTitle().contains("Choose a Killstreak #")) {return;}
        Player player = event.getPlayer();
        String uuid = player.getUniqueId().toString();

        int menu = Integer.parseInt(ChatColor.stripColor(event.getInventory().getTitle().replaceAll("Choose a Killstreak #", "")));

        if(event.getItemName().contains("Go Back")) {
            player.openInventory(PermanentUpgrades.getKillstreakUpgrades(player));
            Sounds.BUTTON.play(player);
            return;
        }

        if(event.getItemName().isEmpty()) return;

        if(event.getItemName().contains("No killstreak")) {
            Sounds.RENOWN_SHOP_PURCHASE.play(player);
            if(menu==1) ClassInstances.killStreakPerkOne.setPerk(uuid, "NONE");
            if(menu==2) ClassInstances.killStreakPerkTwo.setPerk(uuid, "NONE");
            player.openInventory(PermanentUpgrades.getKillstreakUpgrades(player));
            return;
        }

        boolean affordable = event.getItemName().contains(colorCode("&e")) &&
                !event.getItemName().contains(colorCode("&c")) && !event.getItemName().contains("Kills");
        boolean alreadyOn = event.getItemName().contains(colorCode("&a"));

        if (!alreadyOn && affordable) {
            Sounds.RENOWN_SHOP_PURCHASE.play(player);
            if(menu==1) ClassInstances.killStreakPerkOne.setPerk(uuid, getRefID(ChatColor.stripColor(event.getItemName())));
            if(menu==2) ClassInstances.killStreakPerkTwo.setPerk(uuid, getRefID(ChatColor.stripColor(event.getItemName())));
            player.openInventory(PermanentUpgrades.getKillstreakUpgrades(player));
        }else if (!alreadyOn && !affordable) {
            Sounds.NO.play(player);
            player.sendMessage(colorCode("&cYou don't have enough gold to afford this!"));
        }else if (alreadyOn) {
            player.sendMessage(colorCode("&aYou already have this equipped!"));
            Sounds.ERROR.play(player);
        }

    }

    public static Inventory openInventory(Player player, int number){
        String uuid = String.valueOf(player.getUniqueId());
        Inventory gui = advancedInventory.inv(player, 54, "Choose a Killstreak #"+number);

        advancedInventory.addInv(gui, getKills(uuid,3), 2,1,false);
        advancedInventory.addInv(gui, getKills(uuid,5), 2,2,false);
        advancedInventory.addInv(gui, getKills(uuid,7), 2,3,false);
        advancedInventory.addInv(gui, getKills(uuid,10), 2,4,false);
        advancedInventory.addInv(gui, getKills(uuid,25), 2,5,false);

        advancedInventory.addInv(gui, getGoBackItem(), 5,6,false);
        advancedInventory.addInv(gui, getNoKillStreak(), 6,6,false);

        advancedInventory.addInv(gui, getSecondGapple(player), 3,1,false);
        advancedInventory.addInv(gui, getExplicious(player), 4,1,false);
        advancedInventory.addInv(gui, getRandR(player), 5,1,false);
        advancedInventory.addInv(gui, getKhante(player), 6,1,false);
        advancedInventory.addInv(gui, getLeech(player), 7,1,false);

        advancedInventory.addInv(gui, getToughSkin(player), 3,2,false);
        advancedInventory.addInv(gui, getFightOrFlight(player), 4,2,false);
        advancedInventory.addInv(gui, getPungent(player), 5,2,false);
        advancedInventory.addInv(gui, getHeroHaste(player), 6,2,false);
        advancedInventory.addInv(gui, getRush(player), 7,2,false);

        advancedInventory.addInv(gui, getFeast(player), 3,3,false);
        advancedInventory.addInv(gui, getCounterStrike(player), 4,3,false);
        advancedInventory.addInv(gui, getGoldNanoFactory(player), 5,3,false);
        advancedInventory.addInv(gui, getTacticalRetreat(player), 6,3,false);
        advancedInventory.addInv(gui, getGlassPickaxe(player), 7,3,false);
        advancedInventory.addInv(gui, getAssuredStrike(player), 8,3,false);

        advancedInventory.addInv(gui, getAuraOfProtection(player), 3,4,false);
        advancedInventory.addInv(gui, getIceCube(player), 4,4,false);
        advancedInventory.addInv(gui, getSuperStreaker(player), 5,4,false);

        advancedInventory.addInv(gui, getMonster(player), 3,5,false);
        advancedInventory.addInv(gui, getSpongeSteve(player), 4,5,false);

        player.openInventory(gui);

        return gui;
    }

    public static String getRefID(String string){
        switch (string){
            case "Second Gapple":
                return "gapple";
            case "Explicious":
                return "exp";
            case "R&R":
                return "rr";
            case "Khante":
                return "khante";
            case "leech":
                return "leech";
            case "Tough Skin":
                return "toughSkin";
            case "Fight or Flight":
                return "fof";
            case "Pungent":
                return "pungent";
            case "Hero's Haste":
                return "heroHaste";
            case "Rush":
                return "rush";
            case "Feast":
                return "feast";
            case "Counter-Strike":
                return "csgo";
            case "Gold Nano-factory":
                return "nanoFactory";
            case "Tactical Retreat":
                return "tactRetreat";
            case "Glass Pickaxe":
                return "pickaxe";
            case "Assured Strike":
                return "strike";
            case "Aura of Protection":
                return "aura";
            case "Ice Cube":
                return "iceCube";
            case "Super Streaker":
                return "superStreaker";
            case "Monster":
                return "monster";
            case "Spongesteve":
                return "steve";
            default:
                return "NONE";
        }
    }

    public static int getKillsFromRefID(String string){
        switch (string){
            // 3 Kills
            case "gapple":
                return 3;
            case "exp":
                return 3;
            case "rr":
                return 3;
            case "khante":
                return 3;
            case "leech":
                return 3;
                // 5 Kills
            case "toughSkin":
                return 5;
            case "fof":
                return 5;
            case "pungent":
                return 5;
            case "heroHaste":
                return 5;
            case "rush":
                return 5;
                // 7 Kills
            case "feast":
                return 7;
            case "csgo":
                return 7;
            case "nanoFactory":
                return 7;
            case "tactRetreat":
                return 7;
            case "pickaxe":
                return 7;
            case "strike":
                return 7;
                // 10 Kills
            case "aura":
                return 10;
            case "iceCube":
                return 10;
            case "superStreaker":
                return 10;
                // 25 Kills
            case "monster":
                return 25;
            case "steve":
                return 25;
            default:
                return 0;
        }
    }

    public static Material getMaterialFromRefID(String string){
        switch (string){
            // 3 Kills
            case "gapple":
                return Material.GOLDEN_APPLE;
            case "exp":
                return Material.EXP_BOTTLE;
            case "rr":
                return Material.GOLDEN_CARROT;
            case "khante":
                return Material.GOLD_HELMET;
            case "leech":
                return Material.FERMENTED_SPIDER_EYE;
            // 5 Kills
            case "toughSkin":
                return Material.LEATHER_CHESTPLATE;
            case "fof":
                return Material.FIREBALL;
            case "pungent":
                return Material.FERMENTED_SPIDER_EYE;
            case "heroHaste":
                return Material.ENCHANTED_BOOK;
            case "rush":
                return Material.SUGAR;
            // 7 Kills
            case "feast":
                return Material.MUTTON;
            case "csgo":
                return Material.IRON_BARDING;
            case "nanoFactory":
                return Material.GOLD_NUGGET;
            case "tactRetreat":
                return Material.DOUBLE_PLANT;
            case "pickaxe":
                return Material.DIAMOND_PICKAXE;
            case "strike":
                return Material.IRON_SWORD;
            // 10 Kills
            case "aura":
                return Material.SLIME_BALL;
            case "iceCube":
                return Material.PACKED_ICE;
            case "superStreaker":
                return Material.WHEAT;
            // 25 Kills
            case "monster":
                return Material.APPLE;
            case "steve":
                return Material.SPONGE;
            default:
                return Material.GOLD_BLOCK;
        }
    }

    public static String getLoreFromRefID(String string){
        switch (string){
            // 3 Kills
            case "gapple":
                return colorCode("&7Selected: &aSecond Gapple\n\n"+ "&7Every: &c3 kills\n\n" +
                        "&7Gain &b+5XP&7, &6+5g &7and an\n" +
                        "&7extra golden apple.\n\n" +
                        "&eClick to switch streak!");
            case "exp":
                return colorCode("&7Selected: &aExplicious\n\n"+ "&7Every: &c3 kills\n\n" +
                        "&7Gain &b+12 XP\n\n"+
                        "&eClick to switch streak!");
            case "rr":
                return colorCode("&7Selected: &aR&&R\n\n"+ "&7Every: &c3 kills\n\n" +
                        "&7Gain &9Resistance I &7and\n" +
                        "&cRegen II &7for 3s.\n\n"+
                        "&eClick to switch streak!");
            case "khante":
                return colorCode("&7Selected: &aKhante\n\n"+"&7Every: &c3 kills\n\n" +
                        "&7Earn &6+8g&7.\n\n" +
                        "&7Stack &c+4% damage &7vs bountied players\n" +
                        "&7up to &c+40%&7.\n\n" +
                        "&eClick to switch streak!");
            case "leech":
                return colorCode("&7Selected: &aLeech\n\n"+ "&7Every: &c3 kills\n\n" +
                        "&7Next hit heals for &c0.5\u2764 &7+\n" +
                        "&c20% &7of its damage.\n\n"+
                        "&eClick to switch streak!");
            // 5 Kills
            case "toughSkin":
                return colorCode("&7Selected: &aTough Skin\n\n"+"&7Every: &c5 kills\n\n" +
                        "&7Receive &9-3% &7damage.\n" +
                        "&7Stacks up to &9-24%&7.\n\n" +
                        "&eClick to switch streak!");
            case "fof":
                return colorCode("&7Selected: &aFight or Flight\n\n"+ "&7Every: &c5 kills\n\n" +
                        "&7If below half &c\u2764\n" +
                        "&7Gain &eSpeed I &7and &9Resistance I &7for 7 seconds.\n\n" +
                        "&7Otherwise:\n" +
                        "&7Deal &c+20% damage for 7 seconds.\n\n"+
                        "&eClick to switch streak!");
            case "pungent":
                return colorCode("&7Selected: &aPungent\n\n"+ "&7Every: &c5 kills\n\n" +
                        "&7Obtain a &cSmelly Bomb&7:\n" +
                        "&7Applies &9Slowness I &7to players\n" +
                        "&7within 3 blocks for 5 seconds.\n\n"+
                        "&eClick to switch streak!");
            case "heroHaste":
                return colorCode("&7Selected: &aHero's Haste\n\n"+ "&7Every: &c5 kills\n\n" +
                        "&7Gain &eSpeed II &7for 5 seconds.\n\n"+
                        "&eClick to switch streak!");
            case "rush":
                return colorCode("&7Selected: &aRush\n\n"+"&7Every: &c5 kills\n\n" +
                        "&7Gain &e1.5% Speed\n" +
                        "&7Max. &e+15% Speed.\n\n" +
                        "&eClick to switch streak!");
            // 7 Kills
            case "feast":
                return colorCode("&7Selected: &aFeast\n\n"+ "&7Every: &c7 kills\n\n" +
                        "&7Obtain a &6AAA-Rated Steak:\n" +
                        "&7- &c+20% damage\n" +
                        "&7- &eSpeed I\n" +
                        "&7- &9Resistance I\n" +
                        "&7Insta-eat (0:10)\n\n"+
                        "&eClick to switch streak!");
            case "csgo":
                return colorCode("&7Selected: &aCounter-Strike\n\n"+ "&7Every: &c7 kills\n\n" +
                        "&7Deal &c+15% damage &7and block\n" +
                        "&91\u2764 &7per hit for 8s\n\n"+
                        "&eClick to switch streak!");
            case "nanoFactory":
                return colorCode("&7Selected: &aGold Nano-factory\n\n"+ "&7Every: &c7 kills\n\n" +
                        "&7Obtain a molecular assembler:\n" +
                        "&7Spawns &67 gold ingots.\n" +
                        "&7Grants &cRegen IV &7for 2 seconds.\n\n"+
                        "&eClick to switch streak!");
            case "tactRetreat":
                return colorCode("&7Selected: &aTactical Retreat\n\n"+ "&7Every: &c7 kills\n\n" +
                        "&7Gain &cRegeneration IV &7and\n" +
                        "&cWeakness IV &7for 5 seconds.\n\n"+
                        "&eClick to switch streak!");
            case "pickaxe":
                return colorCode("&7Selected: &aGlass Pickaxe\n\n"+ "&7Every: &c7 kills\n\n" +
                        "&7Get a single-use weapon with\n" +
                        "&9+8.5 Damage &7and &c+0.5\u2764\n" +
                        "&7true damage.\n\n"+
                        "&eClick to switch streak!");
            case "strike":
                return colorCode("&7Selected: &aAssured Strike\n\n"+"&7Every: &c7 kills\n\n" +
                        "&7Your next melee hit deals &c+35%\n" +
                        "&cdamage &7and grants &eSpeed I\n" +
                        "&7for 20 seconds.\n\n" +
                        "&eClick to switch streak!");
            // 10 Kills
            case "aura":
                return colorCode("&7Selected: &aAura of Protection\n\n"+ "&7Every: &c10 kills\n\n" +
                        "&7Gain an &aAura of Protection &7spell item.\n\n" +
                        "&aAura of Protection\n" +
                        "&9Ressistance II &7(0:04)\n" +
                        "&eTrue Damage &7immunity (0:15)\n\n"+
                        "&eClick to switch streak!");
            case "iceCube":
                return colorCode("&7Selected: &aIce Cube\n\n"+ "&7Every: &c10 kills\n\n" +
                        "&7Get an &bIce Cube &7item.\n\n" +
                        "&bIce Cube\n" +
                        "&7Single-Use on melee strike.\n" +
                        "&7Deals &c1\u2764 &7true damage to victim.\n" +
                        "&7Gain &b40 XP&7.\n" +
                        "&7Attacks slow enemies for 10 seconds.\n\n"+
                        "&eClick to switch streak!");
            case "superStreaker":
                return colorCode("&7Selected: &aSuper Streaker\n\n"+"&7Every: &c10 kills\n\n" +
                        "&7Add &b50 base XP &7to the kill or\n" +
                        "&7assist that activated this\n" +
                        "&7streak. Stack a buff of &b+5%\n" +
                        "&bXP &7and &b+5% max XP &7from kills.\n" +
                        "&7Maximum of &b+50%&7.\n\n" +
                        "&eClick to switch streak!");
            // 25 Kills
            case "monster":
                return colorCode("&7Selected: &aMonster\n\n"+ "&7Every: &c25 kills\n\n" +
                        "&7Gain an extra max &c\u2764 &7(max 2).\n\n"+
                        "&eClick to switch streak!");
            case "steve":
                return colorCode("&7Selected: &aSpongesteve\n\n"+ "&7Every: &c25 kills\n\n" +
                        "&7Gain &615\u2764 Absorption&7.\n\n"+
                        "&eClick to switch streak!");
            default:
                return ChatColor.GRAY + "Select a killstreak for this\n" + ChatColor.GRAY + "slot.\n\n" +ChatColor.YELLOW + "Click to choose perk!";
        }
    }

    public static ItemStack getGoBackItem(){
        return ItemMaker(Material.ARROW, ChatColor.GREEN + "Go Back",
                ChatColor.GRAY+"To Killstreaks",1, true);
    }

    public static ItemStack getKills(String uuid, int amount){
        return ItemMaker(Material.ITEM_FRAME,
                ChatColor.RED+(amount + " Kills"),"", getKillsFromRefID(uuid)==amount);
    }

    public static ItemStack getNoKillStreak(){
        return ItemMaker(Material.GOLD_BLOCK,
                ChatColor.RED+"No killstreak",colorCode("&7Wanna free up this slot for some\n" +
                        "&7reason?\n\n" +
                        "&eClick to remove killstreak!"), false);
    }

    // 3 Killstreak
    public static ItemStack getSecondGapple(Player player){
        String title = "Second Gapple";

        String refID = "gapple";

        Material material = Material.GOLDEN_APPLE;

        boolean megastreak = true;

        String lore = colorCode("&7Every: &c3 kills\n\n" +
                "&7Gain &b+5XP&7, &6+5g &7and an\n" +
                "&7extra golden apple.\n\n");

        if((ClassInstances.killStreakPerkOne.getPerk(player.getUniqueId().toString()).equals(refID) ||
                ClassInstances.killStreakPerkTwo.getPerk(player.getUniqueId().toString()).equals(refID)) &&
                megastreak){
            return ItemMaker(material, ChatColor.GREEN + title, lore + ChatColor.GREEN + "Already selected!");
        }else if(megastreak && GoldData.getEconomy(player.getUniqueId().toString())>=5000){
            return ItemMaker(material, ChatColor.YELLOW + title, lore + colorCode("&7Cost: &65,000g\n" +
                    "&eClick to purchase!"));
        }else if(megastreak && GoldData.getEconomy(player.getUniqueId().toString()) <= 5000){
            return ItemMaker(material, ChatColor.RED + title, lore + colorCode("&7Cost: &65,000g\n" +
                    "&cNot enough gold!"));
        }else{
            return ItemMaker(Material.BEDROCK, ChatColor.RED + title, lore + colorCode("&7Cost: &65,000g\n" +
                    "&cUnlocked in Renown Shop!"));
        }
    }

    public static ItemStack getExplicious(Player player){
        String title = "Explicious";

        boolean megastreak = true;

        String refID = "exp";

        Material material = Material.EXP_BOTTLE;

        String lore = colorCode("&7Every: &c3 kills\n\n" +
                "&7Gain &b+12 XP\n\n");

        if((ClassInstances.killStreakPerkOne.getPerk(player.getUniqueId().toString()).equals(refID) ||
                ClassInstances.killStreakPerkTwo.getPerk(player.getUniqueId().toString()).equals(refID)) &&
                megastreak){
            return ItemMaker(material, ChatColor.GREEN + title, lore + ChatColor.GREEN + "Already selected!");
        }else if(megastreak && GoldData.getEconomy(player.getUniqueId().toString())>=5000){
            return ItemMaker(material, ChatColor.YELLOW + title, lore + colorCode("&7Cost: &65,000g\n" +
                    "&eClick to purchase!"));
        }else if(megastreak && GoldData.getEconomy(player.getUniqueId().toString()) <= 5000){
            return ItemMaker(material, ChatColor.RED + title, lore + colorCode("&7Cost: &65,000g\n" +
                    "&cNot enough gold!"));
        }else{
            return ItemMaker(Material.BEDROCK, ChatColor.RED + title, lore + colorCode("&7Cost: &65,000g\n" +
                    "&cUnlocked in Renown Shop!"));
        }
    }

    public static ItemStack getRandR(Player player){
        String title = "R&R";

        boolean megastreak = ClassInstances.beastmodeStreak.
                getInt(player.getUniqueId().toString(), 0)>=1;

        String refID = "rr";

        Material material = Material.GOLDEN_CARROT;

        String lore = colorCode("&7Every: &c3 kills\n\n" +
                "&7Gain &9Resistance I &7and\n" +
                "&cRegen II &7for 3s.\n\n");

        if((ClassInstances.killStreakPerkOne.getPerk(player.getUniqueId().toString()).equals(refID) ||
                ClassInstances.killStreakPerkTwo.getPerk(player.getUniqueId().toString()).equals(refID)) &&
                megastreak){
            return ItemMaker(material, ChatColor.GREEN + title, lore + ChatColor.GREEN + "Already selected!");
        }else if(megastreak && GoldData.getEconomy(player.getUniqueId().toString())>=5000){
            return ItemMaker(material, ChatColor.YELLOW + title, lore + colorCode("&7Cost: &65,000g\n" +
                    "&eClick to purchase!"));
        }else if(megastreak && GoldData.getEconomy(player.getUniqueId().toString()) <= 5000){
            return ItemMaker(material, ChatColor.RED + title, lore + colorCode("&7Cost: &65,000g\n" +
                    "&cNot enough gold!"));
        }else{
            return ItemMaker(Material.BEDROCK, ChatColor.RED + title, lore + colorCode("&7Cost: &65,000g\n" +
                    "&cUnlocked in Renown Shop!"));
        }
    }

    public static ItemStack getKhante(Player player){
        String title = "Khante";

        String refID = "khante";

        boolean megastreak = ClassInstances.highlanderStreak.
                getInt(player.getUniqueId().toString(), 0)>=1;

        Material material = Material.GOLD_HELMET;

        String lore = colorCode("&7Every: &c3 kills\n\n" +
                "&7Earn &6+8g&7.\n\n" +
                "&7Stack &c+4% damage &7vs bountied players\n" +
                "&7up to &c+40%&7.\n\n");

        if((ClassInstances.killStreakPerkOne.getPerk(player.getUniqueId().toString()).equals(refID) ||
                ClassInstances.killStreakPerkTwo.getPerk(player.getUniqueId().toString()).equals(refID)) &&
                megastreak){
            return ItemMaker(material, ChatColor.GREEN + title, lore + ChatColor.GREEN + "Already selected!");
        }else if(megastreak && GoldData.getEconomy(player.getUniqueId().toString())>=5000){
            return ItemMaker(material, ChatColor.YELLOW + title, lore + colorCode("&7Cost: &65,000g\n" +
                    "&eClick to purchase!"));
        }else if(megastreak && GoldData.getEconomy(player.getUniqueId().toString()) <= 5000){
            return ItemMaker(material, ChatColor.RED + title, lore + colorCode("&7Cost: &65,000g\n" +
                    "&cNot enough gold!"));
        }else{
            return ItemMaker(Material.BEDROCK, ChatColor.RED + title, lore + colorCode("&7Cost: &65,000g\n" +
                    "&cUnlocked in Renown Shop!"));
        }
    }

    public static ItemStack getLeech(Player player){
        String title = "Leech";

        String refID = "leech";

        boolean megastreak = ClassInstances.magnumOpus.
                getInt(player.getUniqueId().toString(), 0)>=1;

        Material material = Material.FERMENTED_SPIDER_EYE;

        String lore = colorCode("&7Every: &c3 kills\n\n" +
                "&7Next hit heals for &c0.5\u2764 &7+\n" +
                "&c20% &7of its damage.\n\n");

        if((ClassInstances.killStreakPerkOne.getPerk(player.getUniqueId().toString()).equals(refID) ||
                ClassInstances.killStreakPerkTwo.getPerk(player.getUniqueId().toString()).equals(refID)) &&
                megastreak){
            return ItemMaker(material, ChatColor.GREEN + title, lore + ChatColor.GREEN + "Already selected!");
        }else if(megastreak && GoldData.getEconomy(player.getUniqueId().toString())>=5000){
            return ItemMaker(material, ChatColor.YELLOW + title, lore + colorCode("&7Cost: &65,000g\n" +
                    "&eClick to purchase!"));
        }else if(megastreak && GoldData.getEconomy(player.getUniqueId().toString()) <= 5000){
            return ItemMaker(material, ChatColor.RED + title, lore + colorCode("&7Cost: &65,000g\n" +
                    "&cNot enough gold!"));
        }else{
            return ItemMaker(Material.BEDROCK, ChatColor.RED + title, lore + colorCode("&7Cost: &65,000g\n" +
                    "&cUnlocked in Renown Shop!"));
        }
    }

    // 5 Killstreak

    public static ItemStack getToughSkin(Player player){
        String title = "Tough Skin";

        String refID = "toughSkin";

        boolean megastreak = ClassInstances.beastmodeStreak.
                getInt(player.getUniqueId().toString(), 0)>=1;

        Material material = Material.LEATHER_CHESTPLATE;

        String lore = colorCode("&7Every: &c5 kills\n\n" +
                "&7Receive &9-3% &7damage.\n" +
                "&7Stacks up to &9-24%&7.\n\n");

        if((ClassInstances.killStreakPerkOne.getPerk(player.getUniqueId().toString()).equals(refID) ||
                ClassInstances.killStreakPerkTwo.getPerk(player.getUniqueId().toString()).equals(refID)) &&
                megastreak){
            return ItemMaker(material, ChatColor.GREEN + title, lore + ChatColor.GREEN + "Already selected!");
        }else if(megastreak && GoldData.getEconomy(player.getUniqueId().toString())>=10000){
            return ItemMaker(material, ChatColor.YELLOW + title, lore + colorCode("&7Cost: &610,000g\n" +
                    "&eClick to purchase!"));
        }else if(megastreak && GoldData.getEconomy(player.getUniqueId().toString()) <= 10000){
            return ItemMaker(material, ChatColor.RED + title, lore + colorCode("&7Cost: &610,000g\n" +
                    "&cNot enough gold!"));
        }else{
            return ItemMaker(Material.BEDROCK, ChatColor.RED + title, lore + colorCode("&7Cost: &610,000g\n" +
                    "&cUnlocked in Renown Shop!"));
        }
    }

    public static ItemStack getFightOrFlight(Player player){
        String title = "Fight or Flight";

        String refID = "fof";

        boolean megastreak = true;

        Material material = Material.FIREBALL;

        String lore = colorCode("&7Every: &c5 kills\n\n" +
                "&7If below half &c\u2764\n" +
                "&7Gain &eSpeed I &7and &9Resistance I &7for 7 seconds.\n\n" +
                "&7Otherwise:\n" +
                "&7Deal &c+20% damage for 7 seconds.\n\n");

        if((ClassInstances.killStreakPerkOne.getPerk(player.getUniqueId().toString()).equals(refID) ||
                ClassInstances.killStreakPerkTwo.getPerk(player.getUniqueId().toString()).equals(refID)) &&
                megastreak){
            return ItemMaker(material, ChatColor.GREEN + title, lore + ChatColor.GREEN + "Already selected!");
        }else if(megastreak && GoldData.getEconomy(player.getUniqueId().toString())>=10000){
            return ItemMaker(material, ChatColor.YELLOW + title, lore + colorCode("&7Cost: &610,000g\n" +
                    "&eClick to purchase!"));
        }else if(megastreak && GoldData.getEconomy(player.getUniqueId().toString()) <= 10000){
            return ItemMaker(material, ChatColor.RED + title, lore + colorCode("&7Cost: &610,000g\n" +
                    "&cNot enough gold!"));
        }else{
            return ItemMaker(Material.BEDROCK, ChatColor.RED + title, lore + colorCode("&7Cost: &610,000g\n" +
                    "&cUnlocked in Renown Shop!"));
        }
    }

    public static ItemStack getPungent(Player player){
        String title = "Pungent";

        String refID = "pungent";

        boolean megastreak = ClassInstances.hermitStreak.
                getInt(player.getUniqueId().toString(), 0)>=1;

        Material material = Material.FERMENTED_SPIDER_EYE;

        String lore = colorCode("&7Every: &c5 kills\n\n" +
                "&7Obtain a &cSmelly Bomb&7:\n" +
                "&7Applies &9Slowness I &7to players\n" +
                "&7within 3 blocks for 5 seconds.\n\n");

        if((ClassInstances.killStreakPerkOne.getPerk(player.getUniqueId().toString()).equals(refID) ||
                ClassInstances.killStreakPerkTwo.getPerk(player.getUniqueId().toString()).equals(refID)) &&
                megastreak){
            return ItemMaker(material, ChatColor.GREEN + title, lore + ChatColor.GREEN + "Already selected!");
        }else if(megastreak && GoldData.getEconomy(player.getUniqueId().toString())>=10000){
            return ItemMaker(material, ChatColor.YELLOW + title, lore + colorCode("&7Cost: &610,000g\n" +
                    "&eClick to purchase!"));
        }else if(megastreak && GoldData.getEconomy(player.getUniqueId().toString()) <= 10000){
            return ItemMaker(material, ChatColor.RED + title, lore + colorCode("&7Cost: &610,000g\n" +
                    "&cNot enough gold!"));
        }else{
            return ItemMaker(Material.BEDROCK, ChatColor.RED + title, lore + colorCode("&7Cost: &610,000g\n" +
                    "&cUnlocked in Renown Shop!"));
        }
    }

    public static ItemStack getHeroHaste(Player player){
        String title = "Hero's Haste";

        String refID = "heroHaste";

        boolean megastreak = true;

        Material material = Material.ENCHANTED_BOOK;

        String lore = colorCode("&7Every: &c5 kills\n\n" +
                "&7Gain &eSpeed II &7for 5 seconds.\n\n");

        if((ClassInstances.killStreakPerkOne.getPerk(player.getUniqueId().toString()).equals(refID) ||
                ClassInstances.killStreakPerkTwo.getPerk(player.getUniqueId().toString()).equals(refID)) &&
                megastreak){
            return ItemMaker(material, ChatColor.GREEN + title, lore + ChatColor.GREEN + "Already selected!");
        }else if(megastreak && GoldData.getEconomy(player.getUniqueId().toString())>=10000){
            return ItemMaker(material, ChatColor.YELLOW + title, lore + colorCode("&7Cost: &610,000g\n" +
                    "&eClick to purchase!"));
        }else if(megastreak && GoldData.getEconomy(player.getUniqueId().toString()) <= 10000){
            return ItemMaker(material, ChatColor.RED + title, lore + colorCode("&7Cost: &610,000g\n" +
                    "&cNot enough gold!"));
        }else{
            return ItemMaker(Material.BEDROCK, ChatColor.RED + title, lore + colorCode("&7Cost: &610,000g\n" +
                    "&cUnlocked in Renown Shop!"));
        }
    }

    public static ItemStack getRush(Player player){
        String title = "Rush";

        String refID = "rush";

        boolean megastreak = ClassInstances.highlanderStreak.
                getInt(player.getUniqueId().toString(), 0)>=1;

        Material material = Material.SUGAR;

        String lore = colorCode("&7Every: &c5 kills\n\n" +
                "&7Gain &e1.5% Speed\n" +
                "&7Max. &e+15% Speed.\n\n");

        if((ClassInstances.killStreakPerkOne.getPerk(player.getUniqueId().toString()).equals(refID) ||
                ClassInstances.killStreakPerkTwo.getPerk(player.getUniqueId().toString()).equals(refID)) &&
                megastreak){
            return ItemMaker(material, ChatColor.GREEN + title, lore + ChatColor.GREEN + "Already selected!");
        }else if(megastreak && GoldData.getEconomy(player.getUniqueId().toString())>=10000){
            return ItemMaker(material, ChatColor.YELLOW + title, lore + colorCode("&7Cost: &610,000g\n" +
                    "&eClick to purchase!"));
        }else if(megastreak && GoldData.getEconomy(player.getUniqueId().toString()) <= 10000){
            return ItemMaker(material, ChatColor.RED + title, lore + colorCode("&7Cost: &610,000g\n" +
                    "&cNot enough gold!"));
        }else{
            return ItemMaker(Material.BEDROCK, ChatColor.RED + title, lore + colorCode("&7Cost: &610,000g\n" +
                    "&cUnlocked in Renown Shop!"));
        }
    }

    // 7 Killstreak

    public static ItemStack getFeast(Player player){
        String title = "Feast";

        String refID = "feast";

        boolean megastreak = true;

        Material material = Material.MUTTON;

        String lore = colorCode("&7Every: &c7 kills\n\n" +
                "&7Obtain a &6AAA-Rated Steak:\n" +
                "&7- &c+20% damage\n" +
                "&7- &eSpeed I\n" +
                "&7- &9Resistance I\n" +
                "&7Insta-eat (0:10)\n\n");

        if((ClassInstances.killStreakPerkOne.getPerk(player.getUniqueId().toString()).equals(refID) ||
                ClassInstances.killStreakPerkTwo.getPerk(player.getUniqueId().toString()).equals(refID)) &&
                megastreak){
            return ItemMaker(material, ChatColor.GREEN + title, lore + ChatColor.GREEN + "Already selected!");
        }else if(megastreak && GoldData.getEconomy(player.getUniqueId().toString())>=15000){
            return ItemMaker(material, ChatColor.YELLOW + title, lore + colorCode("&7Cost: &615,000g\n" +
                    "&eClick to purchase!"));
        }else if(megastreak && GoldData.getEconomy(player.getUniqueId().toString()) <= 15000){
            return ItemMaker(material, ChatColor.RED + title, lore + colorCode("&7Cost: &615,000g\n" +
                    "&cNot enough gold!"));
        }else{
            return ItemMaker(Material.BEDROCK, ChatColor.RED + title, lore + colorCode("&7Cost: &615,000g\n" +
                    "&cUnlocked in Renown Shop!"));
        }
    }

    public static ItemStack getCounterStrike(Player player){
        String title = "Counter-Strike";

        String refID = "csgo";

        boolean megastreak = true;

        Material material = Material.IRON_BARDING;

        String lore = colorCode("&7Every: &c7 kills\n\n" +
                "&7Deal &c+15% damage &7and block\n" +
                "&91\u2764 &7per hit for 8s\n\n");

        if((ClassInstances.killStreakPerkOne.getPerk(player.getUniqueId().toString()).equals(refID) ||
                ClassInstances.killStreakPerkTwo.getPerk(player.getUniqueId().toString()).equals(refID)) &&
                megastreak){
            return ItemMaker(material, ChatColor.GREEN + title, lore + ChatColor.GREEN + "Already selected!");
        }else if(megastreak && GoldData.getEconomy(player.getUniqueId().toString())>=15000){
            return ItemMaker(material, ChatColor.YELLOW + title, lore + colorCode("&7Cost: &615,000g\n" +
                    "&eClick to purchase!"));
        }else if(megastreak && GoldData.getEconomy(player.getUniqueId().toString()) <= 15000){
            return ItemMaker(material, ChatColor.RED + title, lore + colorCode("&7Cost: &615,000g\n" +
                    "&cNot enough gold!"));
        }else{
            return ItemMaker(Material.BEDROCK, ChatColor.RED + title, lore + colorCode("&7Cost: &615,000g\n" +
                    "&cUnlocked in Renown Shop!"));
        }
    }

    public static ItemStack getGoldNanoFactory(Player player){
        String title = "Gold Nano-factory";

        String refID = "nanoFactory";

        boolean megastreak = ClassInstances.highlanderStreak.
                getInt(player.getUniqueId().toString(), 0)>=1;

        Material material = Material.GOLD_NUGGET;

        String lore = colorCode("&7Every: &c7 kills\n\n" +
                "&7Obtain a molecular assembler:\n" +
                "&7Spawns &67 gold ingots.\n" +
                "&7Grants &cRegen IV &7for 2 seconds.\n\n");

        if((ClassInstances.killStreakPerkOne.getPerk(player.getUniqueId().toString()).equals(refID) ||
                ClassInstances.killStreakPerkTwo.getPerk(player.getUniqueId().toString()).equals(refID)) &&
                megastreak){
            return ItemMaker(material, ChatColor.GREEN + title, lore + ChatColor.GREEN + "Already selected!");
        }else if(megastreak && GoldData.getEconomy(player.getUniqueId().toString())>=15000){
            return ItemMaker(material, ChatColor.YELLOW + title, lore + colorCode("&7Cost: &615,000g\n" +
                    "&eClick to purchase!"));
        }else if(megastreak && GoldData.getEconomy(player.getUniqueId().toString()) <= 15000){
            return ItemMaker(material, ChatColor.RED + title, lore + colorCode("&7Cost: &615,000g\n" +
                    "&cNot enough gold!"));
        }else{
            return ItemMaker(Material.BEDROCK, ChatColor.RED + title, lore + colorCode("&7Cost: &615,000g\n" +
                    "&cUnlocked in Renown Shop!"));
        }
    }

    public static ItemStack getTacticalRetreat(Player player){
        String title = "Tactical Retreat";

        String refID = "tactRetreat";

        boolean megastreak = ClassInstances.beastmodeStreak.
                getInt(player.getUniqueId().toString(), 0)>=1;

        Material material = Material.DOUBLE_PLANT;

        String lore = colorCode("&7Every: &c7 kills\n\n" +
                "&7Gain &cRegeneration IV &7and\n" +
                "&cWeakness IV &7for 5 seconds.\n\n");

        if((ClassInstances.killStreakPerkOne.getPerk(player.getUniqueId().toString()).equals(refID) ||
                ClassInstances.killStreakPerkTwo.getPerk(player.getUniqueId().toString()).equals(refID)) &&
                megastreak){
            return ItemMaker(material, ChatColor.GREEN + title, lore + ChatColor.GREEN + "Already selected!");
        }else if(megastreak && GoldData.getEconomy(player.getUniqueId().toString())>=15000){
            return ItemMaker(material, ChatColor.YELLOW + title, lore + colorCode("&7Cost: &615,000g\n" +
                    "&eClick to purchase!"));
        }else if(megastreak && GoldData.getEconomy(player.getUniqueId().toString()) <= 15000){
            return ItemMaker(material, ChatColor.RED + title, lore + colorCode("&7Cost: &615,000g\n" +
                    "&cNot enough gold!"));
        }else{
            return ItemMaker(Material.BEDROCK, ChatColor.RED + title, lore + colorCode("&7Cost: &615,000g\n" +
                    "&cUnlocked in Renown Shop!"));
        }
    }

    public static ItemStack getGlassPickaxe(Player player){
        String title = "Glass Pickaxe";

        String refID = "pickaxe";

        boolean megastreak = ClassInstances.hermitStreak.
                getInt(player.getUniqueId().toString(), 0)>=1;

        Material material = Material.DIAMOND_PICKAXE;

        String lore = colorCode("&7Every: &c7 kills\n\n" +
                "&7Get a single-use weapon with\n" +
                "&9+8.5 Damage &7and &c+0.5\u2764\n" +
                "&7true damage.\n\n");

        if((ClassInstances.killStreakPerkOne.getPerk(player.getUniqueId().toString()).equals(refID) ||
                ClassInstances.killStreakPerkTwo.getPerk(player.getUniqueId().toString()).equals(refID)) &&
                megastreak){
            return ItemMaker(material, ChatColor.GREEN + title, lore + ChatColor.GREEN + "Already selected!");
        }else if(megastreak && GoldData.getEconomy(player.getUniqueId().toString())>=15000){
            return ItemMaker(material, ChatColor.YELLOW + title, lore + colorCode("&7Cost: &615,000g\n" +
                    "&eClick to purchase!"));
        }else if(megastreak && GoldData.getEconomy(player.getUniqueId().toString()) <= 15000){
            return ItemMaker(material, ChatColor.RED + title, lore + colorCode("&7Cost: &615,000g\n" +
                    "&cNot enough gold!"));
        }else{
            return ItemMaker(Material.BEDROCK, ChatColor.RED + title, lore + colorCode("&7Cost: &615,000g\n" +
                    "&cUnlocked in Renown Shop!"));
        }
    }

    public static ItemStack getAssuredStrike(Player player){
        String title = "Assured Strike";

        String refID = "strike";

        boolean megastreak = ClassInstances.magnumOpus.
                getInt(player.getUniqueId().toString(), 0)>=1;

        Material material = Material.IRON_SWORD;

        String lore = colorCode("&7Every: &c7 kills\n\n" +
                "&7Your next melee hit deals &c+35%\n" +
                "&cdamage &7and grants &eSpeed I\n" +
                "&7for 20 seconds.\n\n");

        if((ClassInstances.killStreakPerkOne.getPerk(player.getUniqueId().toString()).equals(refID) ||
                ClassInstances.killStreakPerkTwo.getPerk(player.getUniqueId().toString()).equals(refID)) &&
                megastreak){
            return ItemMaker(material, ChatColor.GREEN + title, lore + ChatColor.GREEN + "Already selected!");
        }else if(megastreak && GoldData.getEconomy(player.getUniqueId().toString())>=15000){
            return ItemMaker(material, ChatColor.YELLOW + title, lore + colorCode("&7Cost: &615,000g\n" +
                    "&eClick to purchase!"));
        }else if(megastreak && GoldData.getEconomy(player.getUniqueId().toString()) <= 15000){
            return ItemMaker(material, ChatColor.RED + title, lore + colorCode("&7Cost: &615,000g\n" +
                    "&cNot enough gold!"));
        }else{
            return ItemMaker(Material.BEDROCK, ChatColor.RED + title, lore + colorCode("&7Cost: &615,000g\n" +
                    "&cUnlocked in Renown Shop!"));
        }
    }

    // 10 Killstreak

    public static ItemStack getAuraOfProtection(Player player){
        String title = "Aura of Protection";

        String refID = "aura";

        boolean megastreak = ClassInstances.hermitStreak.
                getInt(player.getUniqueId().toString(), 0)>=1;

        Material material = Material.SLIME_BALL;

        String lore = colorCode("&7Every: &c10 kills\n\n" +
                "&7Gain an &aAura of Protection &7spell item.\n\n" +
                "&aAura of Protection\n" +
                "&9Ressistance II &7(0:04)\n" +
                "&eTrue Damage &7immunity (0:15)\n\n");

        if((ClassInstances.killStreakPerkOne.getPerk(player.getUniqueId().toString()).equals(refID) ||
                ClassInstances.killStreakPerkTwo.getPerk(player.getUniqueId().toString()).equals(refID)) &&
                megastreak){
            return ItemMaker(material, ChatColor.GREEN + title, lore + ChatColor.GREEN + "Already selected!");
        }else if(megastreak && GoldData.getEconomy(player.getUniqueId().toString())>=20000){
            return ItemMaker(material, ChatColor.YELLOW + title, lore + colorCode("&7Cost: &620,000g\n" +
                    "&eClick to purchase!"));
        }else if(megastreak && GoldData.getEconomy(player.getUniqueId().toString()) <= 20000){
            return ItemMaker(material, ChatColor.RED + title, lore + colorCode("&7Cost: &620,000g\n" +
                    "&cNot enough gold!"));
        }else{
            return ItemMaker(Material.BEDROCK, ChatColor.RED + title, lore + colorCode("&7Cost: &620,000g\n" +
                    "&cUnlocked in Renown Shop!"));
        }
    }

    public static ItemStack getIceCube(Player player){
        String title = "Ice Cube";

        String refID = "iceCube";

        boolean megastreak = ClassInstances.hermitStreak.
                getInt(player.getUniqueId().toString(), 0)>=1;

        Material material = Material.PACKED_ICE;

        String lore = colorCode("&7Every: &c10 kills\n\n" +
                "&7Get an &bIce Cube &7item.\n\n" +
                "&bIce Cube\n" +
                "&7Single-Use on melee strike.\n" +
                "&7Deals &c1\u2764 &7true damage to victim.\n" +
                "&7Gain &b40 XP&7.\n" +
                "&7Attacks slow enemies for 10 seconds.\n\n");

        if((ClassInstances.killStreakPerkOne.getPerk(player.getUniqueId().toString()).equals(refID) ||
                ClassInstances.killStreakPerkTwo.getPerk(player.getUniqueId().toString()).equals(refID)) &&
                megastreak){
            return ItemMaker(material, ChatColor.GREEN + title, lore + ChatColor.GREEN + "Already selected!");
        }else if(megastreak && GoldData.getEconomy(player.getUniqueId().toString())>=20000){
            return ItemMaker(material, ChatColor.YELLOW + title, lore + colorCode("&7Cost: &620,000g\n" +
                    "&eClick to purchase!"));
        }else if(megastreak && GoldData.getEconomy(player.getUniqueId().toString()) <= 20000){
            return ItemMaker(material, ChatColor.RED + title, lore + colorCode("&7Cost: &620,000g\n" +
                    "&cNot enough gold!"));
        }else{
            return ItemMaker(Material.BEDROCK, ChatColor.RED + title, lore + colorCode("&7Cost: &620,000g\n" +
                    "&cUnlocked in Renown Shop!"));
        }
    }

    public static ItemStack getSuperStreaker(Player player){
        String title = "Super Streaker";

        String refID = "superStreaker";

        boolean megastreak = ClassInstances.moonStreak.
                getInt(player.getUniqueId().toString(), 0)>=1;

        Material material = Material.WHEAT;

        String lore = colorCode("&7Every: &c10 kills\n\n" +
                "&7Add &b50 base XP &7to the kill or\n" +
                "&7assist that activated this\n" +
                "&7streak. Stack a buff of &b+5%\n" +
                "&bXP &7and &b+5% max XP &7from kills.\n" +
                "&7Maximum of &b+50%&7.\n\n");

        if((ClassInstances.killStreakPerkOne.getPerk(player.getUniqueId().toString()).equals(refID) ||
                ClassInstances.killStreakPerkTwo.getPerk(player.getUniqueId().toString()).equals(refID)) &&
                megastreak){
            return ItemMaker(material, ChatColor.GREEN + title, lore + ChatColor.GREEN + "Already selected!");
        }else if(megastreak && GoldData.getEconomy(player.getUniqueId().toString())>=25000){
            return ItemMaker(material, ChatColor.YELLOW + title, lore + colorCode("&7Cost: &625,000g\n" +
                    "&eClick to purchase!"));
        }else if(megastreak && GoldData.getEconomy(player.getUniqueId().toString()) <= 25000){
            return ItemMaker(material, ChatColor.RED + title, lore + colorCode("&7Cost: &625,000g\n" +
                    "&cNot enough gold!"));
        }else{
            return ItemMaker(Material.BEDROCK, ChatColor.RED + title, lore + colorCode("&7Cost: &625,000g\n" +
                    "&cUnlocked in Renown Shop!"));
        }
    }

    // 25 Killstreak

    public static ItemStack getMonster(Player player){
        String title = "Monster";

        String refID = "monster";

        boolean megastreak = ClassInstances.beastmodeStreak.
                getInt(player.getUniqueId().toString(), 0)>=1;

        Material material = Material.APPLE;

        String lore = colorCode("&7Every: &c25 kills\n\n" +
                "&7Gain an extra max &c\u2764 &7(max 2).\n\n");

        if((ClassInstances.killStreakPerkOne.getPerk(player.getUniqueId().toString()).equals(refID) ||
                ClassInstances.killStreakPerkTwo.getPerk(player.getUniqueId().toString()).equals(refID)) &&
                megastreak){
            return ItemMaker(material, ChatColor.GREEN + title, lore + ChatColor.GREEN + "Already selected!");
        }else if(megastreak && GoldData.getEconomy(player.getUniqueId().toString())>=5000){
            return ItemMaker(material, ChatColor.YELLOW + title, lore + colorCode("&7Cost: &65,000g\n" +
                    "&eClick to purchase!"));
        }else if(megastreak && GoldData.getEconomy(player.getUniqueId().toString()) <= 5000){
            return ItemMaker(material, ChatColor.RED + title, lore + colorCode("&7Cost: &65,000g\n" +
                    "&cNot enough gold!"));
        }else{
            return ItemMaker(Material.BEDROCK, ChatColor.RED + title, lore + colorCode("&7Cost: &65,000g\n" +
                    "&cUnlocked in Renown Shop!"));
        }
    }

    public static ItemStack getSpongeSteve(Player player){
        String title = "Spongesteve";

        String refID = "steve";

        boolean megastreak = true;

        Material material = Material.SPONGE;

        String lore = colorCode("&7Every: &c25 kills\n\n" +
                "&7Gain &615\u2764 Absorption&7.\n\n");

        if((ClassInstances.killStreakPerkOne.getPerk(player.getUniqueId().toString()).equals(refID) ||
                ClassInstances.killStreakPerkTwo.getPerk(player.getUniqueId().toString()).equals(refID)) &&
                megastreak){
            return ItemMaker(material, ChatColor.GREEN + title, lore + ChatColor.GREEN + "Already selected!");
        }else if(megastreak && GoldData.getEconomy(player.getUniqueId().toString())>=5000){
            return ItemMaker(material, ChatColor.YELLOW + title, lore + colorCode("&7Cost: &65,000g\n" +
                    "&eClick to purchase!"));
        }else if(megastreak && GoldData.getEconomy(player.getUniqueId().toString()) <= 5000){
            return ItemMaker(material, ChatColor.RED + title, lore + colorCode("&7Cost: &65,000g\n" +
                    "&cNot enough gold!"));
        }else{
            return ItemMaker(Material.BEDROCK, ChatColor.RED + title, lore + colorCode("&7Cost: &65,000g\n" +
                    "&cUnlocked in Renown Shop!"));
        }
    }

}
