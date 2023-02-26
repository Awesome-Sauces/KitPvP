package me.alpha.kitpvp.Bot;

import me.alpha.hunter.main.hunterUtils;
import me.alpha.kitpvp.KitPvP;
import me.alpha.kitpvp.PitRemake.ItemStacks.enchants;
import me.alpha.kitpvp.PitRemake.ItemStacks.itemManager;
import me.alpha.kitpvp.utils.CitizensHelper;
import net.citizensnpcs.api.CitizensAPI;
import net.citizensnpcs.api.ai.TargetType;
import net.citizensnpcs.api.event.DespawnReason;
import net.citizensnpcs.api.npc.NPC;
import net.citizensnpcs.api.trait.trait.Equipment;
import net.citizensnpcs.trait.SkinTrait;
import net.minecraft.server.v1_8_R3.PacketPlayOutAnimation;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftEntity;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import java.util.*;

import static me.alpha.kitpvp.PitRemake.MysticWell.enchanters.FreshPants.percentChance;
import static me.alpha.kitpvp.utils.advancedInventory.ItemMaker;

public class BotPlayer {
    
    public static List<NPC> bots = new ArrayList<>();
    
    public static void registerBot(NPC bot){
        bots.add(bot);
    }
    
    public static void destroyBot(NPC bot){
        bots.remove(bot);
        bot.despawn(DespawnReason.REMOVAL);
        CitizensAPI.getNPCRegistry().deregister(bot);
    }
    
    public static void destroyAllBot(){
        for(NPC bot : bots){
            bots.remove(bot);
            bot.despawn(DespawnReason.REMOVAL);
            CitizensAPI.getNPCRegistry().deregister(bot);
        }
    }
    
    public static ItemStack getRandomHelmet() {
        ItemStack helmet = null;
        if (percentChance(0.05)) {
            helmet = itemManager.IronHelmet;
        } else if (percentChance(0.05)) {
            helmet = ItemMaker(Material.DIAMOND_HELMET, "NULL", "NULL", 1, false);
        }

        return helmet;
    }

    public static ItemStack getRandomChestplate() {
        ItemStack chestplate = itemManager.ChainChestplate;
        if (percentChance(0.1)) {
            chestplate = itemManager.IronChestplate;
        } else if (percentChance(0.1)) {
            chestplate = ItemMaker(Material.DIAMOND_CHESTPLATE, "NULL", "NULL", 1, false);
        }

        return chestplate;
    }

    public static ItemStack getRandomLeggings() {
        ItemStack leggings = itemManager.IronLeggings;
        if (percentChance(0.1)) {
            leggings = enchants.fresh_reds;
        } else if (percentChance(0.1)) {
            leggings = enchants.fresh_blues;
        } else if (percentChance(0.1)) {
            leggings = enchants.fresh_oranges;
        } else if (percentChance(0.1)) {
            leggings = ItemMaker(Material.CHAINMAIL_LEGGINGS, "NULL", "NULL", 1, false);
        } else if (percentChance(0.1)) {
            leggings = ItemMaker(Material.DIAMOND_LEGGINGS, "NULL", "NULL", 1, false);
        }

        return leggings;
    }

    public static ItemStack getRandomBoots() {
        ItemStack boots = itemManager.ChainBoots;
        if (percentChance(0.1)) {
            boots = itemManager.IronBoots;
        } else if (percentChance(0.1)) {
            boots = ItemMaker(Material.DIAMOND_BOOTS, "NULL", "NULL", 1, false);
        }

        return boots;
    }

    public static ItemStack getRandomSword() {
        ItemStack sword = itemManager.IronSword;
        if (percentChance(0.1)) {
            sword = ItemMaker(Material.DIAMOND_SWORD, "NULL", "NULL", 1, false);
        }else if(percentChance(0.05)){
            sword = enchants.fresh_sword;
        }

        return sword;
    }
    
    public static String getRandomName() {
        String bot = "&7[9&7] MrShabadoo30000\n&f[7&f]&7 Axe2Grind\n&7[&912&7] EpicCat345\n&7[&325&7] ItzWiqu\n&7[&327&7]&b Enen07\n&7[&235&7]&b Knack7596\n&7[&e56&7]&b StarFallAva\n&6[&e54&6]&7 Trintynt\n&7[&e53&7]&a SilencedVoice90\n&7[&e55&7]&a BeKinderToMe\n&7[&e51&7]&a aBruno\n&7[&6&l67&7] Skadey\n&7[&6&l63&7] matscoboy\n&7[&6&l65&7] TW9User\n&6[&c&l77&6]&7 Latshi\n&6[&c&l75&6]&7 BulkStraw\n&6[&c&l78&6]&7 3cpscombo\n&7[&c&l79&7] Keruto_\n&7[&c&l73&7]&a StarFallAva\n&7[&c&l70&7]&a SilencedVoice90\n&7[&c&l71&7]&a Ferd69\n&7[&4&l81&7]&a JustSomeLoaf\n&7[&4&l83&7] ItsZERD\n&7[&4&l89&7]&6 Hurricane13579\n&7[&4&l80&7] HasjEnjoyer\n&7[&5&l99&7]&6 drenjamin\n&7[&5&l97&7] PapaBold\n&7[&5&l90&7] Chobblesome\n&7[&5&l91&7]&b ICEASM\n&7[&5&l93&7]&b bizbirikos21\n&7[&d&l107&7]&b Pit_Hy\n&7[&d&l106&7] Psui666\n&7[&d&l105&7] Hangry1221\n&7[&d&l102&7]&6 carriedbyluck\n&7[&d&l100&7]&6 ASAjagt\n&7[&f&l117&7]&6 pingpng\n&7[&f&l115&7]&6 CommentFirst\n&7[&f&l113&7] CatboyMaid4Hire\n&7[&f&l110&7] Ann3frankisdank\n&7[&b&l120&7]&b JazzyWazzy_\n&f[&b&l120&f]&b imharrysmh\n&7[&b&l120&7]&b WhyPit\n&7[&322&7] penelope7\n&7[&329&7] Grizloy\n&7[1&7] HxPulse\n&7[9&7] Sakr_";
        List<String> bots = Arrays.asList(bot.split("\n"));
        Collections.shuffle(bots);
        return ChatColor.translateAlternateColorCodes('&', (String)bots.get(5));
    }

    public static String getNormalRandomName() {
        String bot = "&7MrShabadoo30000\n&7Axe2Grind\n&7EpicCat345\n&7ItzWiqu\n&7Enen07\n&7Knack7596\n&7StarFallAva\n&7Trintynt\n&7SilencedVoice90\n&7BeKinderToMe\n&7aBruno\n&7Skadey\n&7matscoboy\n&7TW9User\n&7Latshi\n&7BulkStraw\n&73cpscombo\n&7Keruto_\n&7StarFallAva\n&7SilencedVoice90\n&7Ferd69\n&7JustSomeLoaf\n&7ItsZERD\n&7Hurricane13579\n&7HasjEnjoyer\n&7drenjamin\n&7PapaBold\n&7Chobblesome\n&7ICEASM\n&7bizbirikos21\n&7Pit_Hy\n&7Psui666\n&7Hangry1221\n&7carriedbyluck\n&7ASAjagt\n&7pingpng\n&7CommentFirst\n&7CatboyMaid4Hire\n&7Ann3frankisdank\n&7JazzyWazzy_\n&7imharrysmh\n&7WhyPit\n&7penelope7\n&7Grizloy\n&7HxPulse\n&7Sakr_";
        List<String> bots = Arrays.asList(bot.split("\n"));
        Collections.shuffle(bots);
        return ChatColor.translateAlternateColorCodes('&', (String)bots.get(5));
    }

    public static void createBot(Player player){
        BotPlayer bot = new BotPlayer(player,
                getRandomName(),
                0.25,
                0,
                5,
                getRandomHelmet(),
                getRandomChestplate(),
                getRandomLeggings(),
                getRandomBoots(),
                getRandomSword());
        bot.getBot();
        bot.spawnBot(player.getLocation());
        bot.run();
    }

    public static void createBot(Location locations){
        BotPlayer bot = new BotPlayer(null,
                getRandomName(),
                0.25,
                0,
                5,
                getRandomHelmet(),
                getRandomChestplate(),
                getRandomLeggings(),
                getRandomBoots(),
                getRandomSword());
        bot.getBot();
        bot.spawnBot(locations);
        bot.run();
    }

    private NPC bot;
    private Player owner;
    private BotAction action;
    private Location pauseLocation;
    private String name;
    private double speed;
    private int jumpTime;
    private int time;
    private int damage;
    private ItemStack helmet;
    private ItemStack chestplate;
    private ItemStack leggings;
    private ItemStack boots;
    private ItemStack sword;

    public BotPlayer() {
        this.action = BotAction.FROZEN;
        this.name = getRandomName();
        this.speed = 2.0;
        this.jumpTime = 7;
        this.time = 60;
        this.damage = 7;
        this.helmet = null;
        this.chestplate = getRandomChestplate();
        this.leggings = getRandomLeggings();
        this.boots = getRandomBoots();
        this.sword = getRandomSword();
    }

    public BotPlayer(String name, int speed, int jumpTime, int time, int damage) {
        this.action = BotAction.FROZEN;
        this.name = name;
        this.speed = (double)speed;
        this.jumpTime = jumpTime;
        this.time = time;
        this.damage = damage;
        this.helmet = null;
        this.chestplate = getRandomChestplate();
        this.leggings = getRandomLeggings();
        this.boots = getRandomBoots();
        this.sword = getRandomSword();
    }

    public BotPlayer(Player owner, String name, double speed, int time, int damage, ItemStack helmet, ItemStack chestplate, ItemStack leggings, ItemStack boots, ItemStack sword) {
        this.action = BotAction.FROZEN;
        this.owner = owner;
        this.name = name;
        this.speed = speed;
        this.jumpTime = 7;
        this.time = time;
        this.damage = damage;
        this.helmet = helmet;
        this.chestplate = chestplate;
        this.leggings = leggings;
        this.boots = boots;
        this.sword = sword;
    }

    public NPC getBot() {
        if (bot != null) {
            return bot;
        } else {
            NPC npc = CitizensAPI.getNPCRegistry().createNPC(EntityType.PLAYER, this.name);
            SkinTrait skinTrait = (SkinTrait)npc.getTrait(SkinTrait.class);
            skinTrait.setSkinName(ChatColor.stripColor(getNormalRandomName()));
            npc.setName(ChatColor.translateAlternateColorCodes('&', this.name));
            ((Equipment)npc.getOrAddTrait(Equipment.class)).set(Equipment.EquipmentSlot.BOOTS, this.boots);
            ((Equipment)npc.getOrAddTrait(Equipment.class)).set(Equipment.EquipmentSlot.LEGGINGS, this.leggings);
            ((Equipment)npc.getOrAddTrait(Equipment.class)).set(Equipment.EquipmentSlot.CHESTPLATE, this.chestplate);
            ((Equipment)npc.getOrAddTrait(Equipment.class)).set(Equipment.EquipmentSlot.HAND, this.sword);
            if (this.helmet != null) {
                ((Equipment)npc.getOrAddTrait(Equipment.class)).set(Equipment.EquipmentSlot.HELMET, this.helmet);
            }

            npc.setBukkitEntityType(EntityType.PLAYER);
            npc.setProtected(false);
            npc.getNavigator().getDefaultParameters().attackDelayTicks(1).pathDistanceMargin(500.0).attackRange(30.0).speedModifier((float)Math.max(2.0, this.speed));
            //npc.getOrAddTrait(hunterTrait.class);
            bot = npc;
            return npc;
        }
    }

    public void spawnBot(Location location) {
        registerBot(bot);
        if (!bot.isSpawned()) {
            bot.spawn(location);
        }

        bot.teleport(location, PlayerTeleportEvent.TeleportCause.PLUGIN);
    }

    public Entity getClosestBot(Location location){
        NPC closest = null;

        for(NPC npc : bots){
            if(closest == null) closest = npc;

            if(!npc.getEntity().isOnGround()) continue;

            if(closest.getEntity()!=null&& npc.getEntity()!=null &&
            closest.getEntity().getLocation().distance(location)>npc.getEntity().getLocation().distance(location)) closest = npc;
        }

        Player closePlayer = null;

        for(Player player : Bukkit.getOnlinePlayers()){
            if(closePlayer == null) closePlayer = player;

            if(!player.isOnGround()) continue;

            if(closePlayer!=null&& player!=null &&
                    closest.getEntity().getLocation().distance(location)>player.getLocation().distance(location)) closePlayer = player;
        }

        if(closest.getEntity().getLocation().distance(location) >
        closePlayer.getLocation().distance(location)) return closePlayer;
        return closest.getEntity();
    }

    public void run() {
        if (this.time > 0) {

            Bukkit.getScheduler().scheduleSyncDelayedTask(KitPvP.INSTANCE, new Runnable() {
                public void run() {
                    if (bot.isSpawned()) {
                        if (owner != null) {
                            destroyBot(bot);
                        }

                        bot.despawn();
                        bot.destroy();
                        CitizensAPI.getNPCRegistry().deregister(bot);
                    }

                }
            }, (long)this.time * 20L);

        }

        (new HunterRunnable() {
            public void code() {
                if (!bot.isSpawned()) {
                    this.cancel();
                }

                if (bot.getEntity() != null && bot.getEntity().isOnGround()) {
                    (new BukkitRunnable() {
                        public void run() {
                            ((Player) bot.getEntity()).setVelocity(new Vector(0.0, 0.36, 0.0));
                        }
                    }).runTaskLater(KitPvP.INSTANCE, 3L);
                }

            }
        }).execute(this.jumpTime);

        (new HunterRunnable() {
            public void code() {
                if (!bot.isSpawned()) {
                    this.cancel();
                }

                if (bot.getNavigator().isNavigating()) {
                    bot.faceLocation(bot.getNavigator().getTargetAsLocation());
                }

            }
        }).execute(1);

        (new HunterRunnable() {
            public void code() {
                if (!bot.isSpawned()) {
                    this.cancel();
                }

                List<Player> nearby = hunterUtils.gearNearby(bot.getEntity(), 25.0);
                if (!bot.getNavigator().isNavigating() && bot.getEntity() != null && bot.getEntity().isOnGround()) {
                    Player lastPlayer = null;

                    for (Player players : nearby) {
                        if (lastPlayer == null) {
                            lastPlayer = players;
                        } else {
                            double distance1 = bot.getEntity().getLocation().distance(lastPlayer.getLocation());
                            double distance2 = bot.getEntity().getLocation().distance(players.getLocation());
                            if (!(distance2 > distance1)) {
                                if (distance2 < distance1) {
                                    lastPlayer = players;
                                } else {
                                    lastPlayer = players;
                                }
                            }
                        }
                    }

                    bot.getNavigator().setTarget(lastPlayer, false);
                }

                if (!bot.getNavigator().isNavigating()) {
                    setAction(BotAction.FROZEN);
                }

            }
        }).execute(1);


        (new HunterRunnable() {
            public void code() {
                if (!bot.isSpawned()) {
                    this.cancel();
                }

                if (bot.getEntity() != null) {
                    List<Player> nearby = hunterUtils.gearNearby(bot.getEntity(), 40.0);

                    for (Player players : nearby) {
                        if (!CitizensAPI.getNPCRegistry().isNPC(players)) {
                            PacketPlayOutAnimation animationPacket = new PacketPlayOutAnimation(((CraftEntity) bot.getEntity()).getHandle(), 0);
                            ((CraftPlayer) players).getHandle().playerConnection.sendPacket(animationPacket);
                        }
                    }

                    if (bot.getNavigator().isNavigating() && bot.getNavigator().getTargetType().equals(TargetType.ENTITY)) {
                        Entity player = bot.getNavigator().getEntityTarget().getTarget();
                        if (bot.getEntity() != null && player.getType().equals(EntityType.PLAYER) &&
                                player.getLocation().distance(bot.getEntity().getLocation()) <= 3.0 &&
                                player.isOnGround() &&
                                CitizensHelper.isNPC(player)) {
                            ((Player)player).damage((double) damage, bot.getEntity());

                        } else if (bot.getEntity() != null &&
                                player.getType().equals(EntityType.PLAYER) &&
                                player.getLocation().distance(bot.getEntity().getLocation()) <= 2.5) {
                            ((Player)player).damage((double) damage, bot.getEntity());
                        }

                        if (player.getLocation().distance(bot.getEntity().getLocation()) > 40.0 && nearby.isEmpty()) {
                            bot.getNavigator().cancelNavigation();
                            bot.getNavigator().setTarget(getClosestBot(bot.getEntity().getLocation()), false);
                        }
                    }

                }
            }
        }).execute(5);
    }

    public BotAction getAction() {
        return this.action;
    }

    public void setAction(BotAction action) {
        this.action = action;
    }

    public ItemStack getHelmet() {
        return this.helmet;
    }

    public void setHelmet(ItemStack helmet) {
        this.helmet = helmet;
    }

    public ItemStack getChestplate() {
        return this.chestplate;
    }

    public void setChestplate(ItemStack chestplate) {
        this.chestplate = chestplate;
    }

    public ItemStack getLeggings() {
        return this.leggings;
    }

    public void setLeggings(ItemStack leggings) {
        this.leggings = leggings;
    }

    public ItemStack getBoots() {
        return this.boots;
    }

    public void setBoots(ItemStack boots) {
        this.boots = boots;
    }

    public ItemStack getSword() {
        return this.sword;
    }

    public void setSword(ItemStack sword) {
        this.sword = sword;
    }

    public int getDamage() {
        return this.damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public int getTime() {
        return this.time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public int getJumpTime() {
        return this.jumpTime;
    }

    public void setJumpTime(int jumpTime) {
        this.jumpTime = jumpTime;
    }

    public double getSpeed() {
        return this.speed;
    }

    public void setSpeed(int speed) {
        this.speed = (double)speed;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
