package me.alpha.kitpvp.PitRemake.PitCommands;

import com.nametagedit.plugin.NametagEdit;
import de.tr7zw.nbtapi.NBTCompound;
import de.tr7zw.nbtapi.NBTItem;
import me.alpha.kitpvp.ChatManager.ChatManager;
import me.alpha.kitpvp.ChatManager.RankColor;
import me.alpha.kitpvp.Data.ClassInstances;
import me.alpha.kitpvp.DataSave.Converter64;
import me.alpha.kitpvp.DataSave.PlayerData;
import me.alpha.kitpvp.KitPvP;
import me.alpha.kitpvp.Objects.Mobs.CustomZombie;
import me.alpha.kitpvp.PitRemake.Boosters.Booster;
import me.alpha.kitpvp.PitRemake.InventoryRefresher.RefreshCore;
import me.alpha.kitpvp.PitRemake.ItemStacks.enchants;
import me.alpha.kitpvp.PitRemake.ItemStacks.itemManager;
import me.alpha.kitpvp.PitRemake.MysticWell.MysticWellGUI;
import me.alpha.kitpvp.PitRemake.MysticWell.enchanters.FreshPants;
import me.alpha.kitpvp.PitRemake.MysticWell.enchanters.MysticBow;
import me.alpha.kitpvp.PitRemake.MysticWell.enchanters.MysticSword;
import me.alpha.kitpvp.PitRemake.PitCommands.Crates.crate;
import me.alpha.kitpvp.PitRemake.PitCommands.Repairs.menu;
import me.alpha.kitpvp.PitRemake.PitCommands.Stash.StashCore;
import me.alpha.kitpvp.PitRemake.PitCommands.Stash.StashData;
import me.alpha.kitpvp.PitRemake.PitCommands.View.ViewCore;
import me.alpha.kitpvp.PitRemake.Scoreboard.ScoreboardCore;
import me.alpha.kitpvp.utils.ColorUtil;
import me.alpha.kitpvp.utils.Sounds;
import net.citizensnpcs.api.CitizensAPI;
import net.citizensnpcs.api.npc.NPC;
import org.bukkit.*;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.v1_8_R3.CraftWorld;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.*;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.EulerAngle;
import org.bukkit.util.Vector;

import java.io.IOException;
import java.util.*;

import static me.alpha.kitpvp.Data.ClassInstances.CombatTag;
import static me.alpha.kitpvp.Data.GoldData.*;
import static me.alpha.kitpvp.Data.XpData.GetCurrentLevel;
import static me.alpha.kitpvp.Objects.ReduxPlayerObject.ReduxPlayerHandler.playerExists;
import static me.alpha.kitpvp.PitRemake.DeathHandler.DeathHandler.KillMan;
import static me.alpha.kitpvp.PitRemake.InventoryRefresher.RefreshCore.refreshInventory;
import static me.alpha.kitpvp.PitRemake.ItemStacks.tebexItems.giveDyes;
import static me.alpha.kitpvp.PitRemake.Leaderboards.Leaderboard.RefreshBoard;
import static me.alpha.kitpvp.PitRemake.Leaderboards.Leaderboard.TopPlayers;
import static me.alpha.kitpvp.PitRemake.Locations.getSpawnLocation;
import static me.alpha.kitpvp.PitRemake.PitBlob.PitBlobMap.deleteBlob;
import static me.alpha.kitpvp.PitRemake.PitCommands.TebexSystem.TebexSystem.ColorfulBoxers;
import static me.alpha.kitpvp.PitRemake.PitCommands.TebexSystem.TebexSystem.onRankBuy;
import static me.alpha.kitpvp.PitRemake.PitEvents.TwoTimesEvent.twoTimesEvent;
import static me.alpha.kitpvp.PitRemake.PitMenus.PrestigeMenu.PrestigeMenu;
import static me.alpha.kitpvp.PitRemake.RenownShop.CookieMonster.MonsterHandler.*;
import static me.alpha.kitpvp.PitRemake.Scoreboard.ScoreboardCore.boardMap;
import static me.alpha.kitpvp.PitRemake.Scoreboard.ScoreboardCore.updateBoard;
import static me.alpha.kitpvp.utils.ColorUtil.colorCode;
import static me.alpha.kitpvp.utils.FancyText.compileListToString;
import static me.alpha.kitpvp.utils.FancyText.hoverText;
import static me.alpha.kitpvp.utils.advancedInventory.HeadMaker;

public class PitCommands implements CommandExecutor {
    public static HashMap<String, Boolean> KillMessages = new HashMap<>();

    public static HashMap<String, String> PlayerDataSave = new HashMap<>();

    public static Map<String, Long> freshPantsCD = new HashMap<String, Long>();

    public static Map<String, Long> ShowCD = new HashMap<String, Long>();

    List<Integer> runnables = new ArrayList<>();

    public static int booster = 1;

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args) {
        if(!(sender instanceof Player)) {

            if(cmd.getName().equalsIgnoreCase("crategive")){
                crate Crate = new crate(args[0], args[1]);

                Crate.broadcastMessage();
            }

            if(cmd.getName().equalsIgnoreCase("booster")){
                Booster.purchaseBoosterCommand(args[0],String.valueOf(Bukkit.getPlayer(args[1]).getUniqueId()), Integer.parseInt(args[2]));

                Bukkit.broadcastMessage(colorCode("&e&lCONGRATS! &7"+args[1]+" just purchased &e&l"+args[0]+" &7booster!"));

                Sounds.PRESTIGE.play(Bukkit.getPlayer(args[1]));

                return true;
            }

            if (cmd.getName().equalsIgnoreCase("makemonersrankers")) {
                onRankBuy(args[0], args[1]);
                return true;
            }

            if (cmd.getName().equalsIgnoreCase("purchaseDyes")) {
                ColorfulBoxers(args[0]);
                return true;
            }
            return true;
        }
        Player player = (Player) sender;
        // /heal

        
        if (cmd.getName().equalsIgnoreCase("activateBooster")) {
            if(player.hasPermission("booster")){

                if(args.length < 1){
                    player.sendMessage(ChatColor.RED + "Please put a time for the booster to last for example: /activateBooster 5/10/15/20/25/30/35/40/45/50");
                    return true;
                }

                if(booster > 1){
                    Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', "&e&lBOOSTER &c&lENDED!"));

                    for (Integer task : runnables) {
                        runnables.remove(task);
                    }

                    booster = 1;
                    return true;
                }

                int time;
                try{
                    time = Integer.parseInt(args[0]);
                } catch (NumberFormatException e) {
                    player.sendMessage(ChatColor.RED + "Please use a number!");
                    return true;
                }

                Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', "&e&lBOOSTER!!! &7There is currently an active booster for &e&l" + time + "m&7."));
                booster += 2;
                int runnable = Bukkit.getScheduler().scheduleSyncDelayedTask(KitPvP.INSTANCE, new Runnable() {
                    @Override
                    public void run() {
                        for (Integer task : runnables) runnables.remove(task);
                        Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', "&e&lBOOSTER &c&lENDED!"));
                        booster = 1;
                    }
                }, (time * 60L) * 20);

                runnables.add(runnable);

                return true;
            }
            return true;
        }

        if(cmd.getName().equalsIgnoreCase("patchnotes")){

            player.sendMessage(colorCode("&eBetter Pit Update - v1.5.2\n" +
                    "&7- &a(+) &7Reworked the whole damage system\n" +
                    "&7- &a(+) &7Recoded the whole Enchanting system\n" +
                    "&7- &a(+) &7Recoded the whole source\n\n" +
                    "&eJoin the discord: &bdiscord.gg/scaTgKQq"));

            return true;
        }

        if(cmd.getName().equalsIgnoreCase("cookiemonster") &&
                player.isOp()){

            createMonsterBoss(player);
            player.sendMessage(colorCode("&c&lWOAH! &7a wild &bCookie Monster &7has appeared!"));
            Sounds.PRESTIGE.play(player);

            return true;
        }

        if(cmd.getName().equalsIgnoreCase("atest") &&
        player.isOp()){

            if(args.length>=1 && args[0].equalsIgnoreCase("save")){
                PlayerData playerData = new PlayerData(player.getUniqueId().toString());

                playerData.saveData(player);

                PlayerDataSave.put(player.getUniqueId().toString(), Converter64.playerDataTo64(playerData));

                player.sendMessage(ColorUtil.colorCode("&aSuccessfully saved and serialized player data"));
                Sounds.SUCCESS.play(player);
            }

            if(args.length>=1 && args[0].equalsIgnoreCase("load")){
                if(PlayerDataSave.containsKey(player.getUniqueId().toString())){
                    try {
                        Converter64.playerDataFrom64(PlayerDataSave.get(player.getUniqueId().toString())).loadData(player);

                        player.sendMessage(ColorUtil.colorCode("&aSuccessfully deserialized and loaded player data"));
                        Sounds.SUCCESS.play(player);
                    } catch (IOException e) {
                        player.sendMessage(ColorUtil.colorCode("&cFailed to deserialize player data"));
                        Sounds.ERROR.play(player);
                        throw new RuntimeException(e);
                    }
                }
            }



            //Zombie stand = (Zombie) player.getWorld().spawnEntity(player.getLocation(), EntityType.ZOMBIE);
            /*

            CustomZombie stand = new CustomZombie(((CraftWorld)player.getWorld()).getHandle());

            stand.setPosition(player.getLocation().getX(),player.getLocation().getY(),player.getLocation().getZ());

            NPC npc = CitizensAPI.getNPCRegistry().createNPC(EntityType.PLAYER, ChatColor.GRAY + "ZOMBIE-AI");

            npc.getNavigator().getDefaultParameters()
                    .attackRange(30)
                    .speedModifier(2);

            npc.spawn(player.getLocation());

             */
/*
            new BukkitRunnable(){
                @Override
                public void run(){
                    if(stand==null || !stand.isValid() || stand.isDead()) this.cancel();
                    if(stand.getTarget()!=null) return;

                    List<Entity> entities = getNearbyEntity(stand, 10, 10, 10);

                    if(entities!=null && !entities.isEmpty()) stand.setTarget((LivingEntity) entities.get(0));

                }
            }.runTaskTimer(KitPvP.INSTANCE,  5L, 5L);

 */
            /*

            new BukkitRunnable(){
                @Override
                public void run(){
                    if(npc==null||!npc.isSpawned() || !stand.getBukkitEntity().isValid() || stand.getBukkitEntity().isDead()){
                        this.cancel();
                    }else{
                        npc.teleport(stand.getBukkitEntity().getLocation(), PlayerTeleportEvent.TeleportCause.PLUGIN);
                    }

                }
            }.runTaskTimer(KitPvP.INSTANCE,  1L, 1L);

            npc.getNavigator().setTarget(stand.getBukkitEntity(), false);

            /*
            new BukkitRunnable(){
                @Override
                public void run(){
                    if(npc==null || !npc.isSpawned()) this.cancel();
                    if(npc.getNavigator().isNavigating()) return;

                    npc.getNavigator().setTarget(stand, false);

                }
            }.runTaskTimer(KitPvP.INSTANCE,  5L, 5L);


            /*
            if(args[1].contains("remove")){
                ClassInstances.petData.setPetData(player.getUniqueId().toString(), "none", 1, 1, ClassInstances.xpDragon.getXpPerLevel());
            }else if(args[1].contains("xp")){
                ClassInstances.petData.setPetData(player.getUniqueId().toString(), ClassInstances.xpDragon.getRefID(), Integer.parseInt(args[0]), Integer.parseInt(args[2]), ClassInstances.xpDragon.getXpPerLevel());
                //ClassInstances.petData.getLevelFromXP(player.getUniqueId().toString(), ClassInstances.xpDragon.getXpPerLevel());
            }else{
                //ClassInstances.petData.setPetData(player.getUniqueId().toString(), ClassInstances.xpDragon.getRefID(), Integer.parseInt(args[0]), 10);
                ClassInstances.xpDragon.spawnPet(player);
                StashCore.safeGive(player,ClassInstances.xpDragon.getPetItem(player));
            }


            Bukkit.broadcastMessage(ClassInstances.petData.getPetData(player.getUniqueId().toString()));

             */

            //player.setItemInHand(MysticSword.enchantMystic(player, player.getItemInHand(), 3));

            /*

            ItemStack item = player.getItemInHand();
            NBTItem nbtItem = new NBTItem(item);

            nbtItem.addCompound("enchants");

            NBTCompound nbtCompound = nbtItem.getOrCreateCompound("enchants");

            nbtCompound.setInteger("billionaire", 300);
            nbtCompound.setInteger("lifesteal", 3);
            nbtCompound.setInteger("shark", 2);

            for(String key : nbtCompound.getKeys()){
                Bukkit.broadcastMessage(key+":"+String.valueOf(nbtCompound.getInteger(key)));
            }

            ItemMeta itemMeta = nbtItem.getItem().getItemMeta();
            List<String> lore = nbtItem.getItem().getItemMeta().getLore();

            for (String key : nbtItem.getCompound("enchants").getKeys()){
                int level = nbtCompound.getInteger(key);

                Bukkit.broadcastMessage(key);

                lore.addAll(Arrays.asList(MysticSword.enchantTier(key, level).split("\n")));
                Bukkit.broadcastMessage(MysticSword.enchantTier(key, level));
            }

            itemMeta.setLore(lore);

            nbtItem.getItem().setItemMeta(itemMeta);

            nbtItem.mergeCompound(nbtCompound);

            item = nbtItem.getItem();

            player.setItemInHand(item);

            /*
            StashData.addStashData(player.getUniqueId(), enchants.fresh_blues);

            StashCore.reminderMessage(player);
            ClassInstances.botKills.setValue(player.getUniqueId().toString(), Integer.parseInt(args[0]));
            player.sendMessage(ClassInstances.botKills.getValue(player.getUniqueId().toString()).toString());

            Sounds.MYSTIC_DROP_1.play(player);

             */

            /*
            playerExists(player).addPerks(args[0]);
            player.sendMessage(colorCode("&c" + playerExists(player).getPerks()));

             */
            //ClassInstances.kingOfTheLadder.getEventExecute().runnable();

            //event.handleTwoEvent();

            //setXp(player.getUniqueId().toString(),getLevelXP(player, Integer.parseInt(args[0]), getPrestige(player.getUniqueId().toString())));

            return true;
        }

        if(cmd.getName().equalsIgnoreCase("repairs")){

            player.openInventory(menu.confirmationGui(player));
            return true;
            /*
            if(player.getInventory().containsAtLeast(enchants.vile, 1)){
                try{
                    ItemMeta meta = player.getInventory().getItemInHand().getItemMeta();

                    String lives = meta.getLore().get(0);

                    if(ChatColor.stripColor(lives).contains("Lives: 20/20")){
                        player.sendMessage(Chat Color.RED + "This mystic has the max lives!");
                        return true;
                    }



                    meta.setLore(MysticRepairs(meta.getLore()));

                    player.getInventory().getItemInHand().setItemMeta(meta);
                    player.getInventory().removeItem(enchants.vile);
                    player.sendMessage(ChatColor.GREEN + "+1" + ChatColor.DARK_GRAY + " Mystic Life");
                    return true;
                } catch (Exception e) {
                    player.sendMessage(ChatColor.RED + "Please hold a mystic!");
                    return true;
                }
            }else{
                player.sendMessage(ChatColor.RED + "You don't have any vile!");
                return true;
            }

             */
        }

        if(cmd.getName().equalsIgnoreCase("damage") && player.isOp()){
            Player defender = Bukkit.getPlayer(args[1]);
            Player attacker = player;

            defender.damage(Integer.parseInt(args[0]), attacker);
        }

        if(cmd.getName().equalsIgnoreCase("veloCheck")){
            if(player.hasPermission("admin.velo")){
                if(args.length < 1){
                    player.sendMessage(ChatColor.RED + "Please use this format: /veloCheck");
                }else{
                    Player checkedPlayer = null;
                    boolean success = false;
                    try{
                        checkedPlayer = Bukkit.getPlayer(args[0]);
                        success = true;
                    }catch (Exception e){
                        player.sendMessage(ChatColor.RED + "An error occured, maybe that player is offline!");
                    }

                    if(success){
                        Location playerLocation = checkedPlayer.getLocation();
                        checkedPlayer.setVelocity(checkedPlayer.getVelocity().add(new Vector(0, 100, 0)));
                        checkedPlayer.teleport(playerLocation, PlayerTeleportEvent.TeleportCause.PLUGIN);
                    }
                }
            }
        }

        if(cmd.getName().equalsIgnoreCase("refresh")){
            refreshInventory(player);
            return true;
        }

        if(cmd.getName().equalsIgnoreCase("enchantPant")){
            if(!player.hasPermission("pantEnchant")) return true;
            if(args.length < 2){
                player.sendMessage(ChatColor.RED + "Please use this format: /enchantPant <enchant> <tier>");
                return true;
            }

            if(player.getInventory().getItemInHand() != null){

                ItemStack item = player.getItemInHand();
                NBTItem nbtItem = new NBTItem(item);

                nbtItem.addCompound("enchants");

                NBTCompound nbtCompound = nbtItem.getOrCreateCompound("enchants");

                nbtCompound.setInteger(args[0], Integer.parseInt(args[1]));

                ItemMeta itemMeta = nbtItem.getItem().getItemMeta();
                List<String> lore = new ArrayList<>();

                lore.add(ChatColor.translateAlternateColorCodes('&', "&7Lives: &a5&7/5"));
                lore.add("   ");

                for (String key : nbtItem.getCompound("enchants").getKeys()){
                    int level = nbtCompound.getInteger(key);

                    if(player.getItemInHand().getType().equals(Material.LEATHER_LEGGINGS)){
                        lore.addAll(Arrays.asList(FreshPants.enchantTier(key, level).split("\n")));
                    }else if(player.getItemInHand().getType().equals(Material.GOLD_SWORD)){
                        lore.addAll(Arrays.asList(MysticSword.enchantTier(key, level).split("\n")));
                    }else if(player.getItemInHand().getType().equals(Material.BOW)){
                        lore.addAll(Arrays.asList(MysticBow.enchantTier(key, level).split("\n")));
                    }
                }


                if(player.getItemInHand().getType().equals(Material.LEATHER_LEGGINGS)){
                    itemMeta.setDisplayName(colorCode("&cTier III Pants"));
                    lore.add(ChatColor.RED + "As strong as iron");
                }else if(player.getItemInHand().getType().equals(Material.GOLD_SWORD)){
                    itemMeta.setDisplayName(colorCode("&cTier III Sword"));
                    lore.add(ChatColor.BLUE + "+6.5 Attack Damage");
                }else if(player.getItemInHand().getType().equals(Material.BOW)){
                    lore.add(ChatColor.BLUE + "+6.5 Attack Damage");
                }

                itemMeta.setLore(lore);

                nbtItem.getItem().setItemMeta(itemMeta);

                nbtItem.mergeCompound(nbtCompound);

                item = nbtItem.getItem();

                player.setItemInHand(item);

            }

            return true;
        }

        if (cmd.getName().equalsIgnoreCase("play")){
            if(args[0].equalsIgnoreCase("pit")){

                if (CombatTag.containsKey(String.valueOf(player.getUniqueId()))){
                    // player is inside mute map
                    if (CombatTag.get(String.valueOf(player.getUniqueId())) > System.currentTimeMillis()){
                        // They still have time left on mute
                        long timeleft = (CombatTag.get(String.valueOf(player.getUniqueId())) - System.currentTimeMillis()) / 1000;
                        player.sendMessage(colorCode("&c&lHOLD UP! &7Can't /play pit while fighting (&c" + timeleft + "s &7left)"));
                        return true;
                    }else{
                        player.removePotionEffect(PotionEffectType.WEAKNESS);
                        ClassInstances.streakData.setStreak(String.valueOf(player.getUniqueId()), 0);

                        Location loc = getSpawnLocation(player.getWorld());

                        if(player.getWorld().getName().equals("world")){
                            loc = getSpawnLocation(Bukkit.getWorld("lobby"));
                            player.sendMessage(colorCode("&b&lTELEPORTING &7to lobby 2"));
                        }else if(player.getWorld().getName().equals("lobby")){
                            loc = getSpawnLocation(Bukkit.getWorld("lobby2"));
                            player.sendMessage(colorCode("&b&lTELEPORTING &7to lobby 3"));
                        }else{
                            loc = getSpawnLocation(Bukkit.getWorld("world"));
                            player.sendMessage(colorCode("&b&lTELEPORTING &7to lobby 1"));
                        }

                        deleteBlob(player);
                        playerExists(player).setMoonXP(0);
                        refreshInventory(player);

                        player.teleport(loc);
                        ScoreboardCore.CreateScore(player);
                        return true;
                    }

                }else{

                    refreshInventory(player);
                    deleteBlob(player);
                    playerExists(player).setMoonXP(0);
                    player.removePotionEffect(PotionEffectType.WEAKNESS);
                    ClassInstances.streakData.setStreak(String.valueOf(player.getUniqueId()), 0);
                    Location loc = getSpawnLocation(player.getWorld());

                    if(player.getWorld().getName().equals("world")){
                        loc = getSpawnLocation(Bukkit.getWorld("lobby"));
                        player.sendMessage(colorCode("&b&lTELEPORTING &7to lobby 2"));
                    }else if(player.getWorld().getName().equals("lobby")){
                        loc = getSpawnLocation(Bukkit.getWorld("lobby2"));
                        player.sendMessage(colorCode("&b&lTELEPORTING &7to lobby 3"));
                    }else{
                        loc = getSpawnLocation(Bukkit.getWorld("world"));
                        player.sendMessage(colorCode("&b&lTELEPORTING &7to lobby 1"));
                    }
                    player.teleport(loc);
                    ScoreboardCore.CreateScore(player);
                }
                return true;
            }
        }

        if(cmd.getName().equalsIgnoreCase("hub")){

            if (CombatTag.containsKey(String.valueOf(player.getUniqueId()))){
                // player is inside mute map
                if (CombatTag.get(String.valueOf(player.getUniqueId())) > System.currentTimeMillis()){
                    // They still have time left on mute
                    long timeleft = (CombatTag.get(String.valueOf(player.getUniqueId())) - System.currentTimeMillis()) / 1000;
                    player.sendMessage(colorCode("&c&lHOLD UP! &7Can't /hub while fighting (&c" + timeleft + "s &7left)"));
                    return true;
                }else{
                    deleteBlob(player);
                    refreshInventory(player);
                    playerExists(player).setMoonXP(0);
                    player.removePotionEffect(PotionEffectType.WEAKNESS);
                    ClassInstances.streakData.setStreak(String.valueOf(player.getUniqueId()), 0);

                    Location loc = getSpawnLocation(player.getWorld());
                    player.teleport(loc);
                    ScoreboardCore.CreateScore(player);
                    return true;
                }

            }else{
                refreshInventory(player);
                deleteBlob(player);
                playerExists(player).setMoonXP(0);
                player.removePotionEffect(PotionEffectType.WEAKNESS);
                ClassInstances.streakData.setStreak(String.valueOf(player.getUniqueId()), 0);
                Location loc = getSpawnLocation(player.getWorld());
                player.teleport(loc);
                ScoreboardCore.CreateScore(player);
            }
            return true;
        }

        if(cmd.getName().equalsIgnoreCase("KillMessageToggle")) {

            if(player.hasPermission("VIP") ||
                    player.hasPermission("VIP+") ||
                    player.hasPermission("MVP") ||
                    player.hasPermission("MVP+") ||
                    player.hasPermission("MVP++")){

                if(!KillMessages.containsKey(String.valueOf(player.getUniqueId()))){
                    KillMessages.put(String.valueOf(player.getUniqueId()), false);
                    player.sendMessage(ChatColor.RED + "Death messages off!");
                }else{
                    if(KillMessages.get(String.valueOf(player.getUniqueId())).equals(true)){
                        KillMessages.put(String.valueOf(player.getUniqueId()), false);
                        player.sendMessage(ChatColor.RED + "Death messages off!");
                    }else{
                        KillMessages.put(String.valueOf(player.getUniqueId()), true);
                        player.sendMessage(ChatColor.GREEN + "Death messages on!");
                    }
                }


                return true;
            }


            player.sendMessage(ChatColor.translateAlternateColorCodes('&',
                    "&cYou need VIP or higher to use that command!"));
            return true;
        }

        if(cmd.getName().equalsIgnoreCase("mkBoard") &&
        player.isOp()){TopPlayers();}

        if(cmd.getName().equalsIgnoreCase("rBoard") &&
        player.isOp()){RefreshBoard();}

        if(cmd.getName().equalsIgnoreCase("all")){
            if(player.isOp()){
                Inventory inv = player.getInventory();
                inv.addItem(enchants.fresh_bow);
                inv.addItem(enchants.malding_boots);
                inv.addItem(enchants.malding_chestplate);
                inv.addItem(enchants.malding_pants);
                inv.addItem(enchants.lores);
                inv.addItem(enchants.vile);
                inv.addItem(enchants.archAngel);
                inv.addItem(enchants.fresh_sword);
                inv.addItem(itemManager.feather);
                inv.addItem(enchants.jewl_pant);
                inv.addItem(enchants.jewl_sword);

                inv.addItem(itemManager.DiamondSword);
                inv.addItem(itemManager.DiamondHelmet);
                inv.addItem(itemManager.DiamondChestplate);
                inv.addItem(itemManager.DiamondLeggings);
                inv.addItem(itemManager.DiamondBoots);

                inv.addItem(enchants.fresh_reds);
                inv.addItem(itemManager.megalongbow);
                inv.addItem(enchants.kingsHelmet);
                inv.addItem(enchants.arma);
                inv.addItem(itemManager.ftts);
                giveDyes(player.getDisplayName());
            }

            return true;
        }

        if(cmd.getName().equalsIgnoreCase("view")){
            if(args.length < 1) {
                player.sendMessage(colorCode("&cMissing arguments! Usage: /view <username>"));
                return true;
            }

            Player tempPlayer = Bukkit.getPlayer(args[0]);
            OfflinePlayer offline;

            if(tempPlayer==null){
                offline = Bukkit.getOfflinePlayer(Bukkit.getOfflinePlayer(args[0]).getUniqueId());
                tempPlayer = ViewCore.loadPlayer(offline);
                if(tempPlayer==null){
                    player.sendMessage(colorCode("&cPlayer doesn't exist"));
                    return true;
                }
                player.openInventory(ViewCore.getViewInventory(player, tempPlayer, args[0], false));
                return true;
            }

            player.openInventory(ViewCore.getViewInventory(player, tempPlayer, args[0], true));
            return true;
        }

        if(cmd.getName().equalsIgnoreCase("well")) {
            if(player.isOp()){
                Sounds.BOOSTER_REMIND.play(player);
                MysticWellGUI.openMysticWell(player);
                return true;
            }

            if(player.hasPermission("VIP") ||
                    player.hasPermission("VIP+") ||
                    player.hasPermission("MVP") ||
                    player.hasPermission("MVP+") ||
                    player.hasPermission("MVP++")){

                Sounds.BOOSTER_REMIND.play(player);
                MysticWellGUI.openMysticWell(player);
                return true;

            }

            player.sendMessage(ChatColor.translateAlternateColorCodes('&',
                    "&cYou need VIP or higher to use that command!"));
            return true;

        }

        if(cmd.getName().equalsIgnoreCase("booster")){
            if(player.isOp() && args.length >= 2){
                Booster.purchaseBoosterCommand(args[0],args[1],Integer.parseInt(args[2]));
                return true;
            }

            player.openInventory(Booster.getBoosterGUI(player));

            return true;
        }

        if(cmd.getName().equalsIgnoreCase("prestige")){
            if(
                    player.hasPermission("VIP+") ||
                    player.hasPermission("MVP") ||
                    player.hasPermission("MVP+") ||
                    player.hasPermission("MVP++")){
                PrestigeMenu(player);
                return true;
            }

            player.sendMessage(ChatColor.translateAlternateColorCodes('&',
                    "&cYou need VIP+ or higher to use that command!"));
            return true;

        }

        if(cmd.getName().equalsIgnoreCase("prestiges") &&
        player.isOp()){
            if(args.length != 3){
                player.sendMessage(ChatColor.RED + "Hey! You cannot use that command like that!");
                player.sendMessage(ChatColor.GREEN + "Usage: /prestiges <set> <amount> <uuid>");
                return true;
            }

            if (args[0].equalsIgnoreCase("set")){
                double amount = Double.parseDouble(args[1]);
                ClassInstances.prestigeData.setPrestige(args[2], (int) amount);
                player.sendMessage(ChatColor.GREEN + "Prestige succesfully set!");
                return true;
            }
        }

        if(cmd.getName().equalsIgnoreCase("renown") &&
        player.isOp()){
            if(args.length != 3){
                player.sendMessage(ChatColor.RED + "Hey! You cannot use that command like that!");
                player.sendMessage(ChatColor.GREEN + "Usage: /renown <set> <amount> <uuid>");
                return true;
            }

            if (args[0].equalsIgnoreCase("set")){
                double amount = Double.parseDouble(args[1]);
                ClassInstances.renownData.setRenown(args[2], (int) amount);
                player.sendMessage(ChatColor.GREEN + "Renown succesfully set!");
                return true;
            }
        }


        if(cmd.getName().equalsIgnoreCase("gold") &&
        player.isOp()){
            if(args.length != 2){
                player.sendMessage(ChatColor.RED + "Hey! You cannot use that command like that!");
                player.sendMessage(ChatColor.GREEN + "Usage: /gold <set> <amount>");
                return true;
            }

            if (args[0].equalsIgnoreCase("set")){
                double amount = Double.parseDouble(args[1]);
                hasEconomy(String.valueOf(player.getUniqueId()));
                setEconomy(String.valueOf(player.getUniqueId()), (int) amount);
                ScoreboardCore.CreateScore(player);
                return true;
            }
        }

        if (cmd.getName().equalsIgnoreCase("pants")){
            if(player.hasPermission("opP2W")){
                if (freshPantsCD.containsKey(String.valueOf(player.getUniqueId()))){
                    // player is inside mute map
                    if (freshPantsCD.get(String.valueOf(player.getUniqueId())) > System.currentTimeMillis()){
                        // They still have time left on mute
                        long timeleft = (freshPantsCD.get(String.valueOf(player.getUniqueId())) - System.currentTimeMillis()) / 1000;
                        player.sendMessage(ChatColor.RED + "Please wait a little before doing that!");
                    }else{
                        player.getInventory().addItem(enchants.cactus);
                        player.getInventory().addItem(enchants.fresh_bow);
                        player.getInventory().addItem(enchants.fresh_reds);
                        player.getInventory().addItem(enchants.fresh_sword);
                        player.getInventory().addItem(enchants.jewl_sword);
                        player.getInventory().addItem(enchants.jewl_pant);
                        player.getInventory().addItem(enchants.fresh_dark);
                        freshPantsCD.put(String.valueOf(player.getUniqueId()), System.currentTimeMillis() + (5 * 1000));
                    }

                }else{
                    player.getInventory().addItem(enchants.fresh_dark);
                    player.getInventory().addItem(enchants.cactus);
                    player.getInventory().addItem(enchants.fresh_bow);
                    player.getInventory().addItem(enchants.fresh_reds);
                    player.getInventory().addItem(enchants.fresh_sword);
                    player.getInventory().addItem(enchants.jewl_sword);
                    player.getInventory().addItem(enchants.jewl_pant);
                    freshPantsCD.put(String.valueOf(player.getUniqueId()), System.currentTimeMillis() + (5 * 1000));
                }
                return true;
            }else{
                player.sendMessage(ChatColor.RED + "You don't have permission to use this!");
                return true;
            }
        }

        if (cmd.getName().equalsIgnoreCase("show")){
            if(player.isOp()){
                try {
                    hoverText(ChatColor.translateAlternateColorCodes('&', "&a&lSHOWOFF! " +  RankColor.getNameColor(player) + player.getDisplayName() + ChatColor.GRAY + " has: " + player.getItemInHand().getItemMeta().getDisplayName()), compileListToString(player.getItemInHand().getItemMeta().getLore(), player.getItemInHand().getItemMeta().getDisplayName(), true));
                }catch (Exception ignored){}
                return true;
            }
            if(player.hasPermission("VIP") ||
                    player.hasPermission("VIP+") ||
                    player.hasPermission("MVP") ||
                    player.hasPermission("MVP+") ||
                    player.hasPermission("MVP++")){



                if (ShowCD.containsKey(String.valueOf(player.getUniqueId()))){
                    // player is inside mute map
                    if (ShowCD.get(String.valueOf(player.getUniqueId())) > System.currentTimeMillis()){
                        // They still have time left on mute
                        long timeleft = (ShowCD.get(String.valueOf(player.getUniqueId())) - System.currentTimeMillis()) / 1000;
                        player.sendMessage(ChatColor.RED + "Please wait a little before doing that!");
                    }else{
                        try{
                            hoverText(ChatColor.translateAlternateColorCodes('&', "&a&lSHOWOFF! " +  RankColor.getNameColor(player) + player.getDisplayName() + ChatColor.GRAY + " has: " + player.getItemInHand().getItemMeta().getDisplayName()), compileListToString(player.getItemInHand().getItemMeta().getLore(), player.getItemInHand().getItemMeta().getDisplayName(), true));
                        }catch (Exception e){
                            player.sendMessage(ChatColor.RED + "Please hold a mystic item!");
                        }
                        ShowCD.put(String.valueOf(player.getUniqueId()), System.currentTimeMillis() + (10 * 1000));
                    }

                }else{
                    try{
                        hoverText(ChatColor.translateAlternateColorCodes('&', "&a&lSHOWOFF! " +  RankColor.getNameColor(player) + player.getDisplayName() + ChatColor.GRAY + " has: " + player.getItemInHand().getItemMeta().getDisplayName()), compileListToString(player.getItemInHand().getItemMeta().getLore(), player.getItemInHand().getItemMeta().getDisplayName(), true));
                    }catch (Exception e){
                        player.sendMessage(ChatColor.RED + "Please hold a mystic item!");
                    }

                    ShowCD.put(String.valueOf(player.getUniqueId()), System.currentTimeMillis() + (10 * 1000));
                }
                return true;
            }


            player.sendMessage(ChatColor.translateAlternateColorCodes('&',
                    "&cYou need VIP or higher to use that command!"));
            return true;
        }


        if (cmd.getName().equalsIgnoreCase("balance")) {
            hasEconomy(String.valueOf(player.getUniqueId()));
            int balance = getEconomy(String.valueOf(player.getUniqueId()));
            player.sendMessage(ChatColor.AQUA + colorCode("&lBALANCE: ") + ChatColor.RESET + ChatColor.GOLD + balance + " gold");
            return true;
        }

        if (cmd.getName().equalsIgnoreCase("spawn")) {

            playerExists(player).setMoonXP(0);
            deleteBlob(player);
            //deleteHologramStreak(player);

            if (CombatTag.containsKey(String.valueOf(player.getUniqueId()))){
                // player is inside mute map
                if (CombatTag.get(String.valueOf(player.getUniqueId())) > System.currentTimeMillis()){
                    // They still have time left on mute
                    long timeleft = (CombatTag.get(String.valueOf(player.getUniqueId())) - System.currentTimeMillis()) / 1000;
                    player.sendMessage(colorCode("&c&lHOLD UP! &7Can't /respawn while fighting (&c" + timeleft + "s &7left)"));
                    return true;
                }else{
                    playerExists(player).setMoonXP(0);
                    player.removePotionEffect(PotionEffectType.WEAKNESS);
                    refreshInventory(player);
                    ClassInstances.streakData.setStreak(String.valueOf(player.getUniqueId()), 0);
                    Location loc = getSpawnLocation(player.getWorld());
                    player.teleport(loc);
                    NametagEdit.getApi().setNametag(player, ChatManager.getLevelText(player) + RankColor.getNameColor(player), "");

                    ScoreboardCore.CreateScore(player);
                    return true;
                }

            }else{
                playerExists(player).setMoonXP(0);
                player.removePotionEffect(PotionEffectType.WEAKNESS);
                ClassInstances.streakData.setStreak(String.valueOf(player.getUniqueId()), 0);
                NametagEdit.getApi().setNametag(player, ChatManager.getLevelText(player)+ RankColor.getNameColor(player), "");
                Location loc = getSpawnLocation(player.getWorld());
                refreshInventory(player);
                player.teleport(loc);
                ScoreboardCore.CreateScore(player);
            }
            return true;
        }

        if (cmd.getName().equalsIgnoreCase("oof")) {
            if(player.getKiller() != null){
                KillMan(player.getKiller(), player);
                refreshInventory(player);
            }else{
                player.sendMessage(colorCode("&c&lOOF! &7seems like no one has hit you in a while!"));
                Sounds.NO.play(player);
            }
            return true;
        }

        else if (cmd.getName().equalsIgnoreCase("feed")) {
            player.setFoodLevel((20));
            player.sendMessage(ChatColor.GOLD + "You were Fed!");
        }

        if (cmd.getName().equalsIgnoreCase("kit")){
            player.sendMessage(ChatColor.RED + "This is currently disabled!");
            //player.getInventory().addItem(itemManager.ChainBoots, itemManager.ChainChestplate, itemManager.IronLeggings, itemManager.IronSword);
        }
        return false;
    }}