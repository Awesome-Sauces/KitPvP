package me.alpha.kitpvp.events;

import com.nametagedit.plugin.NametagEdit;
import de.tr7zw.nbtapi.NBTItem;
import me.alpha.kitpvp.ChatManager.ChatManager;
import me.alpha.kitpvp.ChatManager.LevelColor;
import me.alpha.kitpvp.ChatManager.PrestigeBracketColors;
import me.alpha.kitpvp.ChatManager.RankColor;
import me.alpha.kitpvp.CustomEvents.ReduxBowEvent;
import me.alpha.kitpvp.CustomEvents.ReduxDamageEvent;
import me.alpha.kitpvp.CustomEvents.ReduxInventoryEvent;
import me.alpha.kitpvp.CustomEvents.ReduxSpawnEvent;
import me.alpha.kitpvp.Data.ClassInstances;
import me.alpha.kitpvp.KitPvP;
import me.alpha.kitpvp.Objects.ReduxPlayerObject.ReduxPlayer;
import me.alpha.kitpvp.PitRemake.Bounties.Bounty;
import me.alpha.kitpvp.PitRemake.Factions.ArchAngelFaction;
import me.alpha.kitpvp.PitRemake.Factions.ArmageddonFaction;
import me.alpha.kitpvp.PitRemake.Factions.KingFaction;
import me.alpha.kitpvp.PitRemake.Fishing.FishingCore;
import me.alpha.kitpvp.PitRemake.ItemStacks.enchants;
import me.alpha.kitpvp.PitRemake.ItemStacks.itemManager;
import me.alpha.kitpvp.PitRemake.Locations;
import me.alpha.kitpvp.PitRemake.MysticWell.MysticWellGUI;
import me.alpha.kitpvp.PitRemake.Perks.gui.PermanentUpgrades;
import me.alpha.kitpvp.PitRemake.PitBlob.PitBlobMap;
import me.alpha.kitpvp.PitRemake.PitCommands.Stash.StashCore;
import me.alpha.kitpvp.PitRemake.PitMenus.CactusMenu;
import me.alpha.kitpvp.PitRemake.PitMenus.NonPermanentItems;
import me.alpha.kitpvp.PitRemake.QuestMaster.questInventoryManager;
import me.alpha.kitpvp.PitRemake.RenownShop.RenownItems;
import me.alpha.kitpvp.PitRemake.RenownShop.RenownStorage;
import me.alpha.kitpvp.PitRemake.Startup.CreateVillagers;
import me.alpha.kitpvp.utils.CitizensHelper;
import me.alpha.kitpvp.utils.ColorUtil;
import me.alpha.kitpvp.utils.PacketTitles.PacketTitle;
import me.alpha.kitpvp.utils.Sounds;
import me.alpha.kitpvp.utils.advancedInventory;
import net.citizensnpcs.api.CitizensAPI;
import net.citizensnpcs.api.event.NPCCollisionEvent;
import net.citizensnpcs.api.event.NPCDamageByEntityEvent;
import net.citizensnpcs.api.event.NPCDamageEntityEvent;
import net.citizensnpcs.api.npc.NPC;
import net.minecraft.server.v1_8_R3.IChatBaseComponent;
import net.minecraft.server.v1_8_R3.PacketPlayOutTitle;
import org.bukkit.*;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.*;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.player.*;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import java.util.Objects;
import java.util.Random;
import java.util.UUID;

import static me.alpha.kitpvp.Data.XpData.GetCurrentLevel;
import static me.alpha.kitpvp.Objects.ReduxPlayerObject.ReduxPlayerHandler.playerExists;
import static me.alpha.kitpvp.PitRemake.DeathHandler.DeathHandler.KillMan;
import static me.alpha.kitpvp.PitRemake.Gems.gemMain.makeGemGUI;
import static me.alpha.kitpvp.PitRemake.InventoryManager.NonPermanentItems.ClearAndCheck;
import static me.alpha.kitpvp.PitRemake.Locations.changeCakeLocation;
import static me.alpha.kitpvp.PitRemake.Locations.getSpawnProtection;
import static me.alpha.kitpvp.PitRemake.MysticWell.enchanters.FreshPants.percentChance;
import static me.alpha.kitpvp.PitRemake.MysticWell.loreChecker.CheckEnchantOnPant;
import static me.alpha.kitpvp.PitRemake.PitBlob.PitBlobMap.*;
import static me.alpha.kitpvp.PitRemake.PitMenus.PrestigeMenu.PrestigeMenu;
import static me.alpha.kitpvp.PitRemake.QuestMaster.questMenu.makeMainMenu;
import static me.alpha.kitpvp.PitRemake.RenownShop.RenownStorage.getUberDrop;
import static me.alpha.kitpvp.PitRemake.Scoreboard.ScoreboardCore.boardMap;
import static me.alpha.kitpvp.PitRemake.Scoreboard.ScoreboardCore.updateBoard;
import static me.alpha.kitpvp.PitRemake.StreakManager.UberRewards.claimUberReward;
import static me.alpha.kitpvp.events.InventoryClickEvents.NonPermItems;
import static me.alpha.kitpvp.events.InventoryClickEvents.PrestigeItems;
import static me.alpha.kitpvp.utils.CitizensHelper.getNPC;
import static me.alpha.kitpvp.utils.CitizensHelper.isNPC;
import static me.alpha.kitpvp.utils.ColorUtil.colorCode;

public class GeneralEvents implements Listener {
    @EventHandler (priority = EventPriority.HIGHEST)
    public void DropItemEvent(PlayerDropItemEvent event){
        if(event==null) return;

        UUID uuid = event.getPlayer().getUniqueId();

        if(ClassInstances.LobbyTransfer.containsKey(uuid) &&
        ClassInstances.LobbyTransfer.get(uuid)){
            event.setCancelled(true);
            return;
        }
    }

    @EventHandler (priority = EventPriority.HIGH)
    public void runReduxInventoryEvent(InventoryClickEvent event){
        ReduxInventoryEvent mainEvent = new ReduxInventoryEvent(event);
        if(!mainEvent.isCancelled()) Bukkit.getPluginManager().callEvent(mainEvent);

        mainEvent.run();

    }

    @EventHandler
    public void CloseInv(InventoryCloseEvent event){
        if(event.getInventory()==null && event.getInventory().getTitle()==null) return;
        Player player = (Player) event.getPlayer();

        if(event.getInventory().getTitle().equals("Mystic Well")){
            ItemStack items = event.getInventory().getItem(20);
            if(items!=null) StashCore.safeGive(player, items);
        }

    }

    @EventHandler
    public void onPlayerWalk(PlayerMoveEvent event) {
        Player player = event.getPlayer();
        int x = player.getLocation().getBlockX();
        int y = player.getLocation().getBlockY();
        int z = player.getLocation().getBlockZ();
        Material block = player.getWorld().getBlockAt(x, y-1, z).getType();
        if (block == Material.SLIME_BLOCK) {
            player.setVelocity(player.getLocation().getDirection().multiply(3).setY(1));
        }
    }

    @EventHandler
    public static void PitBlobUnload(PlayerQuitEvent event){
        if(ClassInstances.CombatTag.containsKey(event.getPlayer().getUniqueId().toString()) &&
                ClassInstances.CombatTag.get(event.getPlayer().getUniqueId().toString()) > System.currentTimeMillis()){
            ClearAndCheck(event.getPlayer());
        }
        deleteBlob(event.getPlayer());
    }

    @EventHandler
    public void SpawnProtectionNPC(NPCDamageEntityEvent event){
        if(event.getNPC().getEntity() != null)
            if(event.getNPC().getEntity().getLocation().getY() >= getSpawnProtection(event.getNPC().getEntity().getWorld()))
                event.setCancelled(true);
    }

    @EventHandler
    public void SpawnProtectionNPCtoNPC(NPCDamageByEntityEvent event){
        if(event.getNPC().getEntity() != null)
            if(event.getNPC().getEntity().getLocation().getY() >= getSpawnProtection(event.getNPC().getEntity().getWorld()))
                event.setCancelled(true);
    }

    @EventHandler
    public void BlobDamager(NPCCollisionEvent event){
        Entity slime = event.getCollidedWith();

        if(slime.getType().equals(EntityType.SLIME)){

            Player player = getPlayerFromBlob((Slime) slime);
            if(player!=null){
                if(player.getLocation().getY() >= getSpawnProtection(player.getWorld())) deleteBlob(player);
                if(player.getLocation().distance(slime.getLocation()) >= 18) deleteBlob(player);

                if(player.getInventory().getLeggings() != null &&
                        player.getInventory().getLeggings().getItemMeta() != null &&
                        player.getInventory().getLeggings().getItemMeta().getLore() != null){
                    if(!CheckEnchantOnPant(player.getInventory().getLeggings().getItemMeta().getLore()).contains("blobIII") &&
                            !CheckEnchantOnPant(player.getInventory().getLeggings().getItemMeta().getLore()).contains("blobII") &&
                            !CheckEnchantOnPant(player.getInventory().getLeggings().getItemMeta().getLore()).contains("blobI")){
                        deleteBlob(player);
                    }
                }

                ((Player) event.getNPC().getEntity()).damage(7, player);
            }

        }
    }

    @EventHandler(priority =  EventPriority.HIGHEST)
    public void HandleBowEvent(EntityShootBowEvent event){
        if(event.getEntity() instanceof Player){
            ReduxPlayer attacker = playerExists((Player) event.getEntity());

            boolean somberAttacker = false;

            NBTItem attackerPants = null;

            if(!CitizensHelper.isNPC(attacker) && attacker.getLeggings() !=null){
                attackerPants = new NBTItem(attacker.getLeggings());
            }

            if(attackerPants != null && attackerPants.hasKey("somber")) {
                somberAttacker = true;
            }

            if(!somberAttacker &&
                    !attacker.getPlayerObject().hasPotionEffect(PotionEffectType.POISON)){
                ClassInstances.volleyLore.run(event);
                ClassInstances.megaLongBowLore.run(event);
                ClassInstances.telebowLore.run(event);
            }
        }
    }

    @EventHandler(priority =  EventPriority.HIGHEST)
    public void HandleBowEvents(ProjectileHitEvent event){
        if(event.getEntity().getShooter() instanceof Player){
            if(!(event.getEntity() instanceof Arrow)) return;
            Bukkit.getScheduler().scheduleSyncDelayedTask(KitPvP.INSTANCE, new Runnable() {
                @Override
                public void run() {
                    event.getEntity().remove();
                }
            }, 300L);



            ReduxPlayer attacker = playerExists((Player) event.getEntity().getShooter());

            boolean somberAttacker = false;

            NBTItem attackerPants = null;

            if(!CitizensHelper.isNPC(attacker) && attacker.getLeggings() !=null){
                attackerPants = new NBTItem(attacker.getLeggings());
            }

            if(attackerPants != null && attackerPants.hasKey("somber")) {
                somberAttacker = true;
            }

            if(!somberAttacker &&
                    !attacker.getPlayerObject().hasPotionEffect(PotionEffectType.POISON)){
                ClassInstances.telebowLore.runIt(event);
            }
        }
    }

    private boolean fishingCooldown(ReduxPlayer owner){
        if (owner.getGambleCD()){
            owner.setGambleCD();
            new BukkitRunnable() {
                @Override
                public void run() {
                    owner.setGambleCD();
                }
            }.runTaskLater(KitPvP.INSTANCE, 7L);
            return true;
        }

        return false;
    }

    @EventHandler
    public void HandleFishEvent(PlayerFishEvent event){
        if(event.getState().equals(PlayerFishEvent.State.CAUGHT_FISH)){
            Player player = event.getPlayer();
            NBTItem nbtItem = new NBTItem(event.getPlayer().getItemInHand());

            if(!fishingCooldown(playerExists(player))) return;

            event.setExpToDrop(0);
            event.setCancelled(true);

            if(percentChance(.0001)){
                player.sendMessage(colorCode("&a&lCATCH! &7fished &b+1,000,000 XP"));
                Sounds.PRESTIGE.play(player);
                playerExists(player).addPlayerEXP(1000000);
                return;
            }else if(percentChance(.0001)){
                player.sendMessage(colorCode("&a&lCATCH! &7fished &6+1,000,000g"));
                Sounds.PRESTIGE.play(player);
                playerExists(player).addPlayerGold(1000000);
                ClassInstances.goldRequirementData.addGoldReq(player.getUniqueId().toString(), 1000000);
                return;
            }

            if(percentChance(.001)){
                player.sendMessage(colorCode("&a&lCATCH! &7fished " + enchants.cactus.getItemMeta().getDisplayName()));

                StashCore.safeGiveMultiple(player, enchants.cactus, 8);
                Sounds.JUGGERNAUT_EXPLOSION.play(player);
                return;
            }else if(percentChance(.05)){
                player.sendMessage(colorCode("&a&lCATCH! &7fished " + enchants.fresh_sword.getItemMeta().getDisplayName()));
                Sounds.MYSTIC_DROP_1.play(player);
                Sounds.MYSTIC_DROP_2.play(player);
                Sounds.MYSTIC_DROP_2.play(player);
                StashCore.safeGiveMultiple(player, enchants.fresh_sword, 1);
                return;
            } else if(percentChance(.0005)){
                player.sendMessage(colorCode("&a&lCATCH! &7fished " + itemManager.feather.getItemMeta().getDisplayName()));
                Sounds.JUGGERNAUT_EXPLOSION.play(player);
                StashCore.safeGiveMultiple(player, itemManager.feather, 1);
                return;
            }

            if(percentChance(.0001)){
                player.sendMessage(colorCode("&a&lCATCH! &7fished " + enchants.big_rod.getItemMeta().getDisplayName()));
                Sounds.PRESTIGE.play(player);
                StashCore.safeGiveMultiple(player, enchants.big_rod, 1);
                return;
            }

            if(nbtItem.hasKey("big_rod")){
                player.sendMessage(ColorUtil.colorCode("&a&lCATCH! &7fished &b+250XP &6+78g"));
                playerExists(player).addPlayerEXP(250);
                playerExists(player).addPlayerGold(78);
                ClassInstances.goldRequirementData.addGoldReq(player.getUniqueId().toString(), 78);
            }else {
                player.sendMessage(ColorUtil.colorCode("&a&lCATCH! &7fished &b+104XP &6+53g"));
                playerExists(player).addPlayerEXP(104);
                playerExists(player).addPlayerGold(53);
                ClassInstances.goldRequirementData.addGoldReq(player.getUniqueId().toString(), 53);
            }

            Sounds.SUCCESS.play(player);

        }
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public static void HandleMegaStreakDamage(ReduxDamageEvent event){
        // Mega Streak Calculations
        if(!isNPC(event.getDefender().getPlayerObject())){
            String streak = ClassInstances.megaStreakData.getMegaStreak(event.getDefender().getPlayerUUID());

            if(streak.equals("beastmode") && ClassInstances.streakData.getStreak(event.getDefender().getPlayerUUID()) >= 50){
                if((ClassInstances.streakData.getStreak(event.getDefender().getPlayerUUID())-50)<=0) return;
                int counter = (int) Math.round((double)(ClassInstances.streakData.getStreak(event.getDefender().getPlayerUUID())-50)/5);

                event.addBaseDamage(counter*.15);
            }else if(streak.equals("overdrive") && ClassInstances.streakData.getStreak(event.getDefender().getPlayerUUID()) >= 50){
                if((ClassInstances.streakData.getStreak(event.getDefender().getPlayerUUID())-50)<=0) return;
                int counter = (int) Math.round((double)(ClassInstances.streakData.getStreak(event.getDefender().getPlayerUUID())-50)/5);

                event.getDefender().addPotionEffect(PotionEffectType.SPEED, 32000, 1);

                event.addReduxAttackerTrueDamage(counter*.1);
            }else if(streak.equals("hermit") && ClassInstances.streakData.getStreak(event.getDefender().getPlayerUUID()) >= 50){
                if((ClassInstances.streakData.getStreak(event.getDefender().getPlayerUUID())-50)<=0) return;
                int counter = (int) Math.round((double)(ClassInstances.streakData.getStreak(event.getDefender().getPlayerUUID())-50));

                event.addReduxDamageMultiplier(counter*.3);
            }else if(streak.equals("highlander") && ClassInstances.streakData.getStreak(event.getDefender().getPlayerUUID()) >= 50){
                if((ClassInstances.streakData.getStreak(event.getDefender().getPlayerUUID())-50)<=0) return;
                int counter = (int) Math.round((double)(ClassInstances.streakData.getStreak(event.getDefender().getPlayerUUID())-50)/15);

                event.getDefender().addPotionEffect(PotionEffectType.SPEED, 32000, 1);

                event.addBaseDamage(counter*.20);
            }else if(streak.equals("uber") && ClassInstances.streakData.getStreak(event.getDefender().getPlayerUUID()) >= 100){
                int counter = (int) Math.round((double)(ClassInstances.streakData.getStreak(event.getDefender().getPlayerUUID()))/100);

                event.addBaseDamage(counter*.50);
            }else if(streak.equals("moon") && ClassInstances.streakData.getStreak(event.getDefender().getPlayerUUID()) >= 100){
                if((ClassInstances.streakData.getStreak(event.getDefender().getPlayerUUID())-100)<=0) return;
                int counter = (int) Math.round((double)(ClassInstances.streakData.getStreak(event.getDefender().getPlayerUUID())-100)/20);

                event.addBaseDamage(event.getReduxDamage()*(counter*.10));

                if((ClassInstances.streakData.getStreak(event.getDefender().getPlayerUUID())-200)<=0) return;
                counter = (int) Math.round((double)(ClassInstances.streakData.getStreak(event.getDefender().getPlayerUUID())-200)/20);

                event.addReduxAttackerTrueDamage(counter*.1);
            }
        }

        // Mega Streak Calculations
        if(!isNPC(event.getAttacker().getPlayerObject())){
            String streak = ClassInstances.megaStreakData.getMegaStreak(event.getAttacker().getPlayerUUID());

            if(streak.equals("beastmode") && ClassInstances.streakData.getStreak(event.getAttacker().getPlayerUUID()) >= 50){
                event.addBaseDamage(event.getReduxDamage()*.25);
            }else if(streak.equals("highlander") && ClassInstances.streakData.getStreak(event.getAttacker().getPlayerUUID()) >= 50){
                if(!isNPC(event.getDefender().getPlayerObject()) &&
                        Bounty.BountiesMap.containsKey(event.getDefender().getPlayerUUID()) &&
                        Bounty.BountiesMap.get(event.getDefender().getPlayerUUID()) >= 0){
                    event.addReduxDamageMultiplier(33);
                }
            }else if(streak.equals("uber") && ClassInstances.streakData.getStreak(event.getAttacker().getPlayerUUID()) >= 100){
                if(isNPC(event.getDefender().getPlayerObject())){
                    event.subtractReduxDamageMultiplier(100);
                }
            }

            if(streak.equals("uber") && ClassInstances.streakData.getStreak(event.getAttacker().getPlayerUUID()) >= 500){
                new TrueDamageHandler(event.getDefender(), event.getAttacker(), event.getReduxDamage()/2, 0).run();
            }
        }
    }

    @EventHandler
    public static void MainDamageEvent(EntityDamageByEntityEvent event){

        if(event.getEntity() instanceof Player){
            if(CitizensHelper.isNPC((Player) event.getEntity())){
                NPC npc = CitizensHelper.getNPC((Player) event.getEntity());

                if(npc.isProtected()){
                    event.setCancelled(true);
                    return;
                }

            }
        }

        if(event.getDamager().getLocation().getY()>= Locations.getSpawnProtection(event.getDamager().getWorld()) ||
                event.getEntity().getLocation().getY()>=Locations.getSpawnProtection(event.getDamager().getWorld())) {
            event.setCancelled(true);
            return;
        }

        if (event.getEntity().getType().equals(EntityType.VILLAGER)){
            event.setCancelled(true);
            return;
        }

        if(event.getDamager().getType().equals(EntityType.SLIME)){
            event.setCancelled(true);
            return;
        }

        if(event.getEntity().getType().equals(EntityType.SLIME)){
            event.setCancelled(true);

            Player player = getPlayerFromBlob((Slime) event.getEntity());

            if(player != null){
                if(player.getLocation().distance(event.getEntity().getLocation()) >= 20) deleteBlob(player);

                PitBlobMap.removeBlobHealth(player);

                if(PitBlobMap.getBlobHealth(player) <= 1) deleteBlob(player);

            }

            return;
        }

        /*
        if(event.getDamager().getType().equals(EntityType.SLIME)){

            Bukkit.broadcastMessage("SLIME!");
            Player player = getPlayerFromBlob((Slime) event.getDamager());

            if(event.getEntity().getType().equals(EntityType.PLAYER)){
                ((Player) event.getEntity()).damage(5, player);

            }

            event.setCancelled(true);
            return;
        }


         */
        Player defender = null;

        if(CitizensAPI.getNPCRegistry().isNPC(event.getDamager())){
            if(event.getDamager().getLocation().getY() >= getSpawnProtection(event.getDamager().getWorld())){
                event.setCancelled(true);
                return;

            }

        }
        if(!(event.getEntity() instanceof Player)) {return;}
        if((event.getDamager() instanceof FishHook)) {
            event.setCancelled(true);
            FishHook arrow = (FishHook) event.getDamager();
            Player player = (Player) arrow.getShooter();

            event.setDamage(0);

            return;

        }

        if((event.getDamager() instanceof Arrow)) {

            Arrow arrow = (Arrow) event.getDamager();
            Player player = (Player) arrow.getShooter();

            if(player.getLocation().getY()>=Locations.getSpawnProtection(player.getWorld())) {
                event.setCancelled(true);
                return;
            }

            Bukkit.getScheduler().scheduleSyncDelayedTask(KitPvP.INSTANCE, new Runnable() {
                @Override
                public void run() {
                    arrow.remove();
                }
            }, 300L);

            ReduxBowEvent me = new ReduxBowEvent(playerExists(player), playerExists((Player) event.getEntity()), event.getDamage(), event);
            Bukkit.getPluginManager().callEvent(me);

            if(!me.isCancelled()){
                event.setDamage((me.getReduxDamage())*.45);

                ClassInstances.CombatTag.put(String.valueOf(player.getUniqueId()), System.currentTimeMillis() + (15 * 1000));
                ClassInstances.CombatTag.put(String.valueOf(event.getEntity().getUniqueId()), System.currentTimeMillis() + (15 * 1000));

                if(isNPC(defender)){
                    if(((LivingEntity) event.getEntity()).getHealth() - event.getFinalDamage() <= 0){
                        event.setCancelled(true);
                        ((LivingEntity) event.getEntity()).setHealth(((LivingEntity) event.getEntity()).getMaxHealth());
                        KillMan(player, (Player) event.getEntity());
                        return;
                    }
                }else{
                    if(((LivingEntity) event.getEntity()).getHealth() - event.getFinalDamage() <= 0){
                        event.setCancelled(true);
                        ((LivingEntity) event.getEntity()).setHealth(((LivingEntity) event.getEntity()).getMaxHealth());
                        KillMan(player, (Player) event.getEntity());
                        return;
                    }
                }

                PacketTitle.onAttackHealthBar(me);
            }




            return;

        }else if((event.getDamager() instanceof Player)){
            defender = (Player) event.getEntity();
        }

        Player attacker = (Player) event.getDamager();

        if(attacker.getGameMode().equals(GameMode.SURVIVAL)){
            attacker.setAllowFlight(false);
        }


        if(event.getDamager().getLocation().getY() <= getSpawnProtection(event.getDamager().getWorld())){
            assert defender != null;
            defender.setAllowFlight(false);
            ClassInstances.CombatTag.put(String.valueOf(event.getDamager().getUniqueId()), System.currentTimeMillis() + (15 * 1000));
            ClassInstances.CombatTag.put(String.valueOf(event.getEntity().getUniqueId()), System.currentTimeMillis() + (15 * 1000));

            if(((Player) event.getDamager()).getItemInHand()!=null &&
                    ((Player) event.getDamager()).getItemInHand().getType().equals(Material.GOLD_SWORD) &&
                    ((Player) event.getDamager()).getItemInHand().hasItemMeta() &&
                    ((Player) event.getDamager()).getItemInHand().getEnchantments().containsKey(Enchantment.DAMAGE_ALL) &&
                    CitizensHelper.isNPC(event.getEntity())
            ){
                event.setDamage(Math.max(0,event.getDamage())-1.5);
            }else if(((Player) event.getDamager()).getItemInHand()!=null &&
                    ((Player) event.getDamager()).getItemInHand().getType().equals(Material.GOLD_SWORD) &&
                    ((Player) event.getDamager()).getItemInHand().hasItemMeta() &&
                    ((Player) event.getDamager()).getItemInHand().getEnchantments().containsKey(Enchantment.DAMAGE_ALL)){
                event.setDamage(Math.max(0,event.getDamage())-1.5);
            }


            if(CitizensHelper.isNPC(defender) && !defender.isOnGround()){

                Player tempDefender = defender;
                if(percentChance(.25)) Bukkit.getScheduler().scheduleSyncDelayedTask(KitPvP.INSTANCE, new Runnable() {
                    @Override
                    public void run() {
                        tempDefender.setVelocity(new Vector());
                    }
                }, 1L);
            }

            ReduxDamageEvent mainEvent = new ReduxDamageEvent(playerExists(attacker), playerExists(defender), event.getDamage(), event);
            Bukkit.getPluginManager().callEvent(mainEvent);
            if (!mainEvent.isCancelled()) {


                mainEvent.run();

                boolean attackerTrueDamage = new TrueDamageHandler(playerExists(attacker), playerExists(defender), mainEvent.getReduxAttackerTrueDamage(), event.getFinalDamage()).run();
                boolean defenderTrueDamage = new TrueDamageHandler(playerExists(attacker), playerExists(defender), mainEvent.getReduxDefenderTrueDamage(), 0).run();


                if(attackerTrueDamage){
                    mainEvent.setCancelled(true);
                    event.setCancelled(true);
                    return;
                }

                event.setDamage(mainEvent.getReduxDamage());

                if(isNPC(defender)){
                    if(((LivingEntity) event.getEntity()).getHealth() - event.getFinalDamage() <= 3){
                        event.setCancelled(true);
                        ((LivingEntity) event.getEntity()).setHealth(((LivingEntity) event.getEntity()).getMaxHealth());
                        KillMan((Player) event.getDamager(), (Player) event.getEntity());
                        return;
                    }
                }else{
                    if(((LivingEntity) event.getEntity()).getHealth() - event.getFinalDamage() <= 1){
                        event.setCancelled(true);
                        ((LivingEntity) event.getEntity()).setHealth(((LivingEntity) event.getEntity()).getMaxHealth());
                        KillMan((Player) event.getDamager(), (Player) event.getEntity());
                        return;
                    }
                }

            }else{
                event.setCancelled(true);
            }
        }else{
            event.setCancelled(true);
        }
    }

    @EventHandler
    public static void Death(PlayerDeathEvent event){

        if(event.getEntity() == null) {return;}
        if(isNPC(event.getEntity())){
            NPC npc = getNPC(event.getEntity());
            if(npc != null){
                Player player = (Player) npc.getEntity();
                player.setHealth(player.getMaxHealth());
                player.setMaxHealth(20);
            }

            return;
        }
        Player player = event.getEntity();
        player.setHealth(player.getMaxHealth());
        player.setMaxHealth(20);
        try{
            playerExists(player).resetEscape();
            KillMan(player.getKiller(), player);
        } catch (Exception e) {

        }

        NametagEdit.getApi().setNametag(player, ChatManager.getLevelText(player) + RankColor.getNameColor(player), "");

        PacketPlayOutTitle title = new PacketPlayOutTitle(PacketPlayOutTitle.EnumTitleAction.TITLE,
                IChatBaseComponent.ChatSerializer.a("{\"text\":\"YOU DIED\",\"color\":\"red\"}"),100,20,20);
        ((CraftPlayer) player).getHandle().playerConnection.sendPacket(title);
    }

    @EventHandler
    public static void PrestigeLimit(PlayerLevelChangeEvent event){
        Player player = event.getPlayer();
        if (player.getLevel() > 120){
            player.setLevel(120);
        }else if(player.getLevel() == 0){
            return;
        }else{
            player.playSound(player.getLocation(), Sound.LEVEL_UP, 1F, 1F);
            String pb = PrestigeBracketColors.getBracketColor(player);
            int[] randomDUDE = GetCurrentLevel(String.valueOf(player.getUniqueId()), ClassInstances.xpData.getXp(String.valueOf(player.getUniqueId())), ClassInstances.prestigeData.getPrestige(String.valueOf(player.getUniqueId())), player);
            PacketPlayOutTitle title = new PacketPlayOutTitle(PacketPlayOutTitle.EnumTitleAction.TITLE,
                    IChatBaseComponent.ChatSerializer.a("{\"text\":\"LEVEL UP!\",\"bold\":true,\"color\":\"aqua\"}"),100,20,20);
            ((CraftPlayer) player).getHandle().playerConnection.sendPacket(title);
            PacketPlayOutTitle sub_title = new PacketPlayOutTitle(PacketPlayOutTitle.EnumTitleAction.SUBTITLE, IChatBaseComponent.ChatSerializer.a("{\"text\":\" " + pb + "[" + LevelColor.getLevelColor(randomDUDE[1] - 1) + (randomDUDE[1] -1)  + pb + "]" +  ChatColor.GRAY + " \u279F "  + pb + "[" + LevelColor.getLevelColor(randomDUDE[1]) + (randomDUDE[1])  + pb + "]" +"\"}"),100,20,20);
            ((CraftPlayer) player).getHandle().playerConnection.sendPacket(sub_title);
        }

    }

    @EventHandler
    public static void onRightClick(PlayerInteractEvent event) {
        Player player = event.getPlayer();

        if(event.getPlayer().getItemInHand()!=null &&
        !event.getPlayer().isOp() &&
        event.getPlayer().getItemInHand().getType().equals(Material.MONSTER_EGG)){
            event.setCancelled(true);
        }

        if(event.getPlayer().getItemInHand()!=null &&
                !event.getPlayer().isOp() &&
                event.getPlayer().getItemInHand().getType().equals(Material.MONSTER_EGGS)){
            event.setCancelled(true);
        }

        if(event.getClickedBlock()!=null){
            if(event.getClickedBlock().getType().equals(Material.ENDER_CHEST)){
                return;
            }
        }

        if(event.getItem() != null && player.getItemInHand().getType().equals(Material.CACTUS)){
            player.openInventory(CactusMenu.inventoryConstructor(player));
            return;
        }

        if(event.getItem() != null && event.getItem().getType().equals(Material.EMERALD)){

            NBTItem nbtItem = new NBTItem(event.getItem());

            if(!nbtItem.hasKey("gem")) return;
            //player.sendMessage(colorCode("&c&lERROR! &7That item is temporarily disabled!"));
            //Sounds.ERROR.play(player);
            Sounds.MYSTIC_WELL_OPEN_1.play(player);
            player.openInventory(makeGemGUI(player));
            return;
        }

        if(event.getPlayer().getItemInHand()!=null&&
                (event.getPlayer().getItemInHand().equals(enchants.firstaidempty)||
        event.getPlayer().getItemInHand().equals(enchants.firstaidfull))){

            if(event.getPlayer().getItemInHand() != null &&
                    event.getPlayer().getItemInHand().equals(enchants.firstaidempty)){
                event.setCancelled(true);
                Sounds.NO.play(player);
                return;
            }

            if(event.getPlayer().getItemInHand() != null && event.getPlayer().getItemInHand().getItemMeta() != null &&
                    event.getPlayer().getItemInHand().getItemMeta().getLore() != null &&
                    event.getPlayer().getItemInHand().getItemMeta().getLore().equals(enchants.firstaidfull.getItemMeta().getLore())){
                event.setCancelled(true);
                player.getInventory().remove(enchants.firstaidfull);
                player.setItemInHand(enchants.firstaidempty);
                player.setHealth(Math.min(player.getMaxHealth(), player.getHealth()+5));
                Sounds.FIRST_AID.play(player);

                Bukkit.getScheduler().scheduleSyncDelayedTask(KitPvP.INSTANCE, new Runnable() {
                    @Override
                    public void run() {
                        StashCore.safeRemove(player, enchants.firstaidempty);
                        player.getInventory().addItem(enchants.firstaidfull);
                    }
                }, 5 * 20);

                return;
            }

        }

        if(event.getAction().equals(Action.RIGHT_CLICK_BLOCK) ||
                event.getAction().equals(Action.RIGHT_CLICK_AIR)){

            if (event.getPlayer().getItemInHand() != null &&
                    event.getPlayer().getItemInHand().equals(enchants.fullPantPB) &&
                    event.getPlayer().getInventory().containsAtLeast(enchants.fullPantPB, 1)){
                for (int i = 0; i < 10; i++) {
                    StashCore.safeGive(player, enchants.fresh_reds);
                }

                Sounds.ARMOR_SWAP.play(player);

                event.getPlayer().getInventory().removeItem(enchants.fullPantPB);
            }else if (event.getPlayer().getItemInHand() != null &&
                    event.getPlayer().getItemInHand().equals(enchants.fullSwordPB) &&
                    event.getPlayer().getInventory().containsAtLeast(enchants.fullSwordPB, 1)){
                for (int i = 0; i < 10; i++) {
                    StashCore.safeGive(player, enchants.fresh_sword);
                }

                Sounds.ARMOR_SWAP.play(player);

                event.getPlayer().getInventory().removeItem(enchants.fullSwordPB);
            }

            if(event.getPlayer().getItemInHand() != null &&
                    event.getPlayer().getItemInHand().getItemMeta() != null &&
                    event.getPlayer().getItemInHand().equals(enchants.swordPB) &&
                    event.getPlayer().getInventory().containsAtLeast(enchants.fresh_sword, 10)
            ){

                event.getPlayer().getInventory().removeItem(enchants.swordPB);

                for (int i = 0; i < 10; i++) {
                    event.getPlayer().getInventory().removeItem(enchants.fresh_sword);
                }

                event.getPlayer().getInventory().addItem(enchants.fullSwordPB);

                Sounds.ARMOR_SWAP.play(player);

                return;

            }else if(event.getPlayer().getItemInHand() != null &&
                    event.getPlayer().getItemInHand().getItemMeta() != null &&
                    event.getPlayer().getItemInHand().equals(enchants.pantsPB)){

                if(event.getPlayer().getInventory().containsAtLeast(enchants.fresh_reds, 10)){
                    for (int i = 0; i < 10; i++) {
                        event.getPlayer().getInventory().removeItem(enchants.fresh_reds);
                    }

                    Sounds.ARMOR_SWAP.play(player);
                    event.getPlayer().getInventory().addItem(enchants.fullPantPB);
                    event.getPlayer().getInventory().removeItem(enchants.pantsPB);
                    return;
                }else if(event.getPlayer().getInventory().containsAtLeast(enchants.fresh_blues, 10)){
                    for (int i = 0; i < 10; i++) {
                        event.getPlayer().getInventory().removeItem(enchants.fresh_blues);
                    }

                    Sounds.ARMOR_SWAP.play(player);
                    event.getPlayer().getInventory().addItem(enchants.fullPantPB);
                    event.getPlayer().getInventory().removeItem(enchants.pantsPB);
                    return;
                }else if(event.getPlayer().getInventory().containsAtLeast(enchants.fresh_yellows, 10)){
                    for (int i = 0; i < 10; i++) {
                        event.getPlayer().getInventory().removeItem(enchants.fresh_yellows);
                    }

                    Sounds.ARMOR_SWAP.play(player);
                    event.getPlayer().getInventory().addItem(enchants.fullPantPB);
                    event.getPlayer().getInventory().removeItem(enchants.pantsPB);
                    return;
                }else if(event.getPlayer().getInventory().containsAtLeast(enchants.fresh_greens, 10)){
                    for (int i = 0; i < 10; i++) {
                        event.getPlayer().getInventory().removeItem(enchants.fresh_greens);
                    }

                    Sounds.ARMOR_SWAP.play(player);
                    event.getPlayer().getInventory().addItem(enchants.fullPantPB);
                    event.getPlayer().getInventory().removeItem(enchants.pantsPB);
                    return;
                }else if(event.getPlayer().getInventory().containsAtLeast(enchants.fresh_oranges, 10)){
                    for (int i = 0; i < 10; i++) {
                        event.getPlayer().getInventory().removeItem(enchants.fresh_oranges);
                    }

                    Sounds.ARMOR_SWAP.play(player);
                    event.getPlayer().getInventory().addItem(enchants.fullPantPB);
                    event.getPlayer().getInventory().removeItem(enchants.pantsPB);
                    return;
                }else{

                    int pant_amount = 0;

                    for (ItemStack item : event.getPlayer().getInventory()){
                        if (item!=null&&item.getItemMeta()!=null&& item.getItemMeta().getDisplayName() != null &&item.getItemMeta().getDisplayName().contains("Fresh")){
                            pant_amount+=1;
                        }
                    }

                    if(pant_amount < 10) return;

                    int removed = 0;

                    if(pant_amount>=10){
                        for(ItemStack item : event.getPlayer().getInventory()){
                            if(removed>=10){
                                break;
                            }

                            if(item!=null&&item.getItemMeta()!=null&& item.getItemMeta().getDisplayName() != null &&item.getItemMeta().getDisplayName().contains("Fresh")){
                                event.getPlayer().getInventory().removeItem(item);
                                removed+=1;
                            }
                        }
                    }

                    Sounds.ARMOR_SWAP.play(player);
                    //Sounds.FIRST_AID.play(player);
                    event.getPlayer().getInventory().addItem(enchants.fullPantPB);
                    event.getPlayer().getInventory().removeItem(enchants.pantsPB);
                    return;
                }

            }
        }

        if (event.getAction() == Action.LEFT_CLICK_AIR) {
            if (event.getItem() != null) {
                 if(event.getItem().getType().equals(Material.ENDER_CHEST)){
                     NBTItem item = new NBTItem(event.getItem());

                     if(item.hasKey("uber") &&
                     event.getItem().getItemMeta().getDisplayName().equals(getUberDrop().getItemMeta().getDisplayName()) &&
                     player.getInventory().containsAtLeast(getUberDrop(), 1)){
                         event.getPlayer().getInventory().removeItem(getUberDrop());
                         claimUberReward(event.getPlayer());
                     }
                }
            }
        }
    }

    @EventHandler
    public void MysticWellEnchant(PlayerInteractEvent event){
        try{

            Material block = event.getClickedBlock().getType();

            if(event.getClickedBlock().getType().equals(Material.ENDER_CHEST)){
                return;
            }

            if(block == Material.ENCHANTMENT_TABLE){
                event.setCancelled(true);
                Sounds.BOOSTER_REMIND.play(event.getPlayer());
                MysticWellGUI.openMysticWell(event.getPlayer());


                event.getPlayer().getOpenInventory().getTopInventory().setItem(10, advancedInventory.yGlass(true, 0, false));

                new BukkitRunnable(){
                    @Override
                    public void run(){
                        Player player = event.getPlayer();

                        if(player.getOpenInventory().getTopInventory()!=null &&
                        player.getOpenInventory().getTopInventory().getTitle()!=null &&
                        !player.getOpenInventory().getTopInventory().getTitle().contains("Mystic Well")) this.cancel();

                        ItemStack mystic = event.getPlayer().getOpenInventory().getTopInventory().getItem(20);

                        boolean empty = mystic == null || mystic.getType().equals(Material.AIR);

                        int tier = 0;
                        boolean dark = false;

                        if(!empty){
                            NBTItem nbtItem = new NBTItem(mystic);

                            if(nbtItem.hasKey("mysticTier")) tier=nbtItem.getInteger("mysticTier");
                            if(nbtItem.hasKey("darkPant")) dark = true;
                        }

                        boolean found = false;

                        Inventory inventory = player.getOpenInventory().getTopInventory();

                        int position = 10;


                            if(inventory.getItem(position)!=null &&
                                    !inventory.getItem(position).
                                            getItemMeta().getDisplayName().
                                            contains(ChatColor.GRAY+"Click an item in your inventory!") &&
                                    !inventory.getItem(position).
                                            getItemMeta().getDisplayName().
                                            contains(ChatColor.GRAY + "Item in well!")){
                                found=true;
                            }else if(!inventory.getItem(11).
                                    getItemMeta().getDisplayName().
                                    contains(ChatColor.GRAY+"Click an item in your inventory!") &&
                                    !inventory.getItem(11).
                                            getItemMeta().getDisplayName().
                                            contains(ChatColor.GRAY + "Item in well!")){
                                found=true;
                                position=11;
                            }else if(!inventory.getItem(12).
                                    getItemMeta().getDisplayName().
                                    contains(ChatColor.GRAY+"Click an item in your inventory!") &&
                                    !inventory.getItem(12).
                                            getItemMeta().getDisplayName().
                                            contains(ChatColor.GRAY + "Item in well!")){
                                found=true;
                                position=12;
                            }else if(!inventory.getItem(19).
                                    getItemMeta().getDisplayName().
                                    contains(ChatColor.GRAY+"Click an item in your inventory!") &&
                                    !inventory.getItem(19).
                                            getItemMeta().getDisplayName().
                                            contains(ChatColor.GRAY + "Item in well!")){
                                found=true;
                                position=19;
                            }else if(!inventory.getItem(21).
                                    getItemMeta().getDisplayName().
                                    contains(ChatColor.GRAY+"Click an item in your inventory!") &&
                                    !inventory.getItem(21).
                                            getItemMeta().getDisplayName().
                                            contains(ChatColor.GRAY + "Item in well!")){
                                found=true;
                                position=21;
                            }else if(!inventory.getItem(28).
                                    getItemMeta().getDisplayName().
                                    contains(ChatColor.GRAY+"Click an item in your inventory!") &&
                                    !inventory.getItem(28).
                                            getItemMeta().getDisplayName().
                                            contains(ChatColor.GRAY + "Item in well!")){
                                found=true;
                                position=28;
                            }else if(!inventory.getItem(29).
                                    getItemMeta().getDisplayName().
                                    contains(ChatColor.GRAY+"Click an item in your inventory!") &&
                                    !inventory.getItem(29).
                                            getItemMeta().getDisplayName().
                                            contains(ChatColor.GRAY + "Item in well!")){
                                found=true;
                                position=29;
                            }else if(!inventory.getItem(30).
                                    getItemMeta().getDisplayName().
                                    contains(ChatColor.GRAY+"Click an item in your inventory!") &&
                                    !inventory.getItem(30).
                                            getItemMeta().getDisplayName().
                                            contains(ChatColor.GRAY + "Item in well!")){
                                found=true;
                                position=30;
                            }

                        inventory.setItem(position, advancedInventory.dGlass(empty));

                        switch (position){
                            case 10:
                                position=11;
                                break;
                            case 11:
                                position=12;
                                break;
                            case 12:
                                position=21;
                                break;
                            case 19:
                                position=10;
                                break;
                            case 21:
                                position=30;
                                break;
                            case 28:
                                position=19;
                                break;
                            case 29:
                                position=28;
                                break;
                            default:
                                position=29;
                                break;
                        }


                        inventory.setItem(position, advancedInventory.yGlass(empty, tier, dark));

                    }
                }.runTaskTimer(KitPvP.INSTANCE,  3L, 3L);

            }else if(block == Material.CAKE_BLOCK){
                if(event.getPlayer().getInventory().getItemInHand().equals(RenownStorage.getStickQuest())){
                    event.getPlayer().getInventory().addItem(new ItemStack(Material.CAKE));
                    changeCakeLocation();
                    Random rand = new Random(); //instance of random class
                    int upperbound = 10;
                    int int_random = rand.nextInt(upperbound);
                    switch (int_random){
                        case 1:
                        case 2:
                            event.getPlayer().getInventory().addItem(RenownStorage.getToken());
                            event.getPlayer().getInventory().addItem(RenownStorage.getToken());
                            event.getPlayer().getInventory().addItem(RenownStorage.getToken());
                            event.getPlayer().getInventory().addItem(RenownStorage.getToken());
                            event.getPlayer().getInventory().addItem(RenownStorage.getToken());
                            event.getPlayer().getInventory().addItem(RenownStorage.getToken());
                            event.getPlayer().getInventory().addItem(RenownStorage.getToken());
                            event.getPlayer().getInventory().addItem(RenownStorage.getToken());
                            event.getPlayer().getInventory().addItem(RenownStorage.getToken());
                            event.getPlayer().getInventory().addItem(RenownStorage.getToken());
                            event.getPlayer().getInventory().addItem(RenownStorage.getToken());
                            event.getPlayer().getInventory().addItem(RenownStorage.getToken());
                            event.getPlayer().getInventory().addItem(RenownStorage.getToken());
                            event.getPlayer().getInventory().addItem(RenownStorage.getToken());
                            event.getPlayer().getInventory().addItem(RenownStorage.getToken());
                            event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.ANVIL_LAND, 1, 1);
                            event.getPlayer().sendMessage(ChatColor.translateAlternateColorCodes('&', "&a&lWOW! &7you found some &eRenown Tokens&7!"));
                            break;
                    }
                }else{
                    event.getPlayer().sendMessage(ChatColor.RED + "You need the right tool for this!");
                }

            }
        }catch (Exception e){

        }

    }

    @EventHandler (priority = EventPriority.HIGH)
    public void entityDamageEvent(EntityDamageEvent event) {

        if(event.getCause()== EntityDamageEvent.DamageCause.VOID){
            event.getEntity().teleport(Locations.getBotSpawnLocation(event.getEntity().getWorld()), PlayerTeleportEvent.TeleportCause.PLUGIN);
            if(event.getEntity().getType().equals(EntityType.PLAYER) &&
                    !isNPC((Player) event.getEntity())){
                Player player = (Player)event.getEntity();
                player.sendMessage(colorCode("&c&lOOPS! &7you fell out of the void!"));
                Sounds.ERROR.play(player);
            }
            event.setCancelled(true);
        }

        if(event.getCause()== EntityDamageEvent.DamageCause.FIRE){
            event.setCancelled(true);
        }

        if(event.getCause()== EntityDamageEvent.DamageCause.FIRE_TICK){
            event.setCancelled(true);
        }


        if(event.getCause()== EntityDamageEvent.DamageCause.LAVA){
            event.setCancelled(true);
        }

        if(event.getEntity().getType().equals(EntityType.SLIME)) event.setCancelled(true);

        if (event.getCause() == EntityDamageEvent.DamageCause.FALL) {
            event.setCancelled(true);
        }

        if (event.getCause() == EntityDamageEvent.DamageCause.LIGHTNING) {
            event.setCancelled(true);
        }


    }

    @EventHandler (priority = EventPriority.HIGH)
    public void onFoodLevelChange (FoodLevelChangeEvent event) {
        if (event.getEntityType () != EntityType.PLAYER) return;
        event.setCancelled (true);
    }

    @EventHandler
    public void venomHandler(EntityDamageEvent event){
        if(event.getEntity() instanceof Player){
            if(event.getCause().equals(EntityDamageEvent.DamageCause.POISON)) event.setCancelled(true);
        }
    }

    @EventHandler (priority = EventPriority.HIGH)
    public void clickEvent(ReduxInventoryEvent event) {
        Player player = event.getPlayer();

        try {
            if (!event.isInventory("Non-permanent items")) {
                event.setCancelled(true);
                NonPermItems(event);
            } else if (!event.isInventory("Prestige & Renown")) {
                PrestigeItems(event);
        } else if(!event.isInventory("Quests & Contracts")){
                //questInventoryManager.main(event);
            }else if(player.getOpenInventory().getTitle().equals("Mystic Well")){
                InventoryClickEvents.main(event);
            }
        }catch (Exception e){

        }
    }


    @EventHandler
    public static void NpcShop(PlayerInteractEntityEvent event){

        if (event.getRightClicked().getType() != EntityType.VILLAGER &&
                event.getPlayer().getType() != EntityType.PLAYER){
            event.setCancelled(true);
            return;
        }



        Player player = (Player) event.getPlayer();
        NPC npc = CitizensAPI.getNPCRegistry().getNPC(event.getRightClicked());

        if (Objects.equals(npc, CreateVillagers.non_perm_upgrades_npc) ||
                Objects.equals(npc, CreateVillagers.lobby_non_perm_upgrades_npc) ||
                Objects.equals(npc, CreateVillagers.lobby2_non_perm_upgrades_npc)){
            new NonPermanentItems(player);
        }else if (Objects.equals(npc, CreateVillagers.prestige_npc) ||
                Objects.equals(npc, CreateVillagers.lobby_prestige_npc) ||
                Objects.equals(npc, CreateVillagers.lobby2_prestige_npc)){
            PrestigeMenu(player);
        }else if (Objects.equals(npc, CreateVillagers.perm_upgrades_npc) ||
                Objects.equals(npc, CreateVillagers.lobby_perm_upgrades_npc) ||
                Objects.equals(npc, CreateVillagers.lobby2_perm_upgrades_npc)){
            player.openInventory(PermanentUpgrades.getPermanentUpgrades(player));
        }else if (Objects.equals(npc, CreateVillagers.king_npc) ||
                Objects.equals(npc, CreateVillagers.lobby_king_npc) ||
                Objects.equals(npc, CreateVillagers.lobby2_king_npc)){
            KingFaction.openInventory(player);
        }else if (Objects.equals(npc, CreateVillagers.archAngel_npc) ||
                Objects.equals(npc, CreateVillagers.lobby_archAngel_npc) ||
                Objects.equals(npc, CreateVillagers.lobby2_archAngel_npc)){
            ArchAngelFaction.openInventory(player);
        }else if (Objects.equals(npc, CreateVillagers.armageddon_npc) ||
                Objects.equals(npc, CreateVillagers.lobby_armageddon_npc) ||
                Objects.equals(npc, CreateVillagers.lobby2_armageddon_npc)){
            ArmageddonFaction.openInventory(player);
        }else if(Objects.equals(npc, CreateVillagers.quest_npc) ||
                Objects.equals(npc, CreateVillagers.lobby_quest_npc) ||
                Objects.equals(npc, CreateVillagers.lobby2_quest_npc)){
            player.sendMessage(ColorUtil.colorCode("&c&lERROR! &7This NPC is currently disabled!"));
            Sounds.NO.play(player);
            //if(ClassInstances.prestigeData.getPrestige(String.valueOf(player.getUniqueId())) >= 15){
              //  player.openInventory(makeMainMenu(player));
            //}else{
              //  player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&bQuest Master&8 >> &7Hey you need at least prestige &eXV&7 to talk to me!"));
            //}

            //Perks(player);
        }else if(npc != null && npc.getName() != null && npc.getName().contains("Merchant")){
            player.sendMessage(ChatColor.LIGHT_PURPLE + "Hey welcome to Better Pit!");
            player.sendMessage(ChatColor.GRAY + "Check out the store at: " + ChatColor.AQUA + "https://betterpit.tebex.io/");
            player.sendMessage(ChatColor.AQUA + "Join the discord at: " + ChatColor.DARK_AQUA + "https://discord.gg/jeGddMuH");
            //Perks(player);
        }
    }


    @EventHandler
    public void cactus(InventoryClickEvent event){
        if(event != null && event.getClickedInventory() != null && event.getClickedInventory().getTitle() != null &&event.getClickedInventory().getTitle() != null && event.getClickedInventory().getTitle().equals(ChatColor.GRAY + "Philosopher's Cactus")){
            event.setCancelled(true);
            Player player = (Player) event.getWhoClicked();
            Inventory inventory = player.getInventory();

            if(event.getWhoClicked().getInventory().containsAtLeast(enchants.cactus, 1) && event.getCurrentItem().equals(enchants.fresh_reds)){
                player.closeInventory();
                event.getWhoClicked().getInventory().removeItem(enchants.cactus);
                StashCore.safeGive(player, enchants.fresh_reds);
            }else if(event.getWhoClicked().getInventory().containsAtLeast(enchants.cactus, 1) && event.getCurrentItem().equals(enchants.fresh_blues)){
                player.closeInventory();
                event.getWhoClicked().getInventory().removeItem(enchants.cactus);
                StashCore.safeGive(player, enchants.fresh_blues);
            }else if(event.getWhoClicked().getInventory().containsAtLeast(enchants.cactus, 1) && event.getCurrentItem().equals(enchants.fresh_greens)){
                player.closeInventory();
                event.getWhoClicked().getInventory().removeItem(enchants.cactus);
                StashCore.safeGive(player, enchants.fresh_greens);
            }else if(event.getWhoClicked().getInventory().containsAtLeast(enchants.cactus, 1) && event.getCurrentItem().equals(enchants.fresh_yellows)){
                player.closeInventory();
                StashCore.safeRemove(player, enchants.cactus);
                StashCore.safeGive(player, enchants.fresh_yellows);
            }else if(event.getWhoClicked().getInventory().containsAtLeast(enchants.cactus, 1) && event.getCurrentItem().equals(enchants.fresh_oranges)){
                player.closeInventory();
                event.getWhoClicked().getInventory().removeItem(enchants.cactus);
                StashCore.safeGive(player, enchants.fresh_oranges);
            }

            Sounds.FIRST_AID.play(player);

        }
    }


}
