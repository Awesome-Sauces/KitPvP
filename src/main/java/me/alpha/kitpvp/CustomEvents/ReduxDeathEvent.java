package me.alpha.kitpvp.CustomEvents;

import com.nametagedit.plugin.NametagEdit;
import me.alpha.hunter.api.HunterAPI;
import me.alpha.kitpvp.ChatManager.ChatManager;
import me.alpha.kitpvp.ChatManager.RankColor;
import me.alpha.kitpvp.Data.ClassInstances;
import me.alpha.kitpvp.Data.GoldData;
import me.alpha.kitpvp.KitPvP;
import me.alpha.kitpvp.Objects.ReduxPlayerObject.ReduxPlayer;
import me.alpha.kitpvp.PitRemake.Boosters.Booster;
import me.alpha.kitpvp.PitRemake.Bounties.Bounty;
import me.alpha.kitpvp.PitRemake.ItemStacks.enchants;
import me.alpha.kitpvp.PitRemake.MysticWell.GlobalEnchants.*;
import me.alpha.kitpvp.PitRemake.PitBlob.PitBlobMap;
import me.alpha.kitpvp.PitRemake.Scoreboard.ScoreboardCore;
import me.alpha.kitpvp.PitRemake.PitCommands.Stash.StashCore;
import me.alpha.kitpvp.utils.ColorUtil;
import me.alpha.kitpvp.utils.Sounds;
import net.citizensnpcs.api.npc.NPC;
import net.minecraft.server.v1_8_R3.EntityHuman;
import net.minecraft.server.v1_8_R3.IChatBaseComponent;
import net.minecraft.server.v1_8_R3.PacketPlayOutTitle;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Effect;
import org.bukkit.Sound;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import java.text.DecimalFormat;
import java.util.List;

import static me.alpha.kitpvp.Data.ClassInstances.KillMessages;
import static me.alpha.kitpvp.PitRemake.DeathHandler.DeathHandler.KillMan;
import static me.alpha.kitpvp.PitRemake.InventoryManager.NonPermanentItems.ClearAndCheck;
import static me.alpha.kitpvp.PitRemake.InventoryManager.NonPermanentItems.ClearRegular;
import static me.alpha.kitpvp.PitRemake.Jewels.Jewels.PlayerFinishedJewl;
import static me.alpha.kitpvp.PitRemake.Locations.getBotSpawnLocation;
import static me.alpha.kitpvp.PitRemake.Locations.getSpawnLocation;
import static me.alpha.kitpvp.PitRemake.PitBlob.PitBlobMap.deleteBlob;
import static me.alpha.kitpvp.PitRemake.PitEvents.TwoTimesEvent.twoTimesEvent;
import static me.alpha.kitpvp.PitRemake.StreakManager.StreakManager.StreakManager;
import static me.alpha.kitpvp.PitRemake.StreakManager.StreakManager.UberRewardClaimDeath;
import static me.alpha.kitpvp.utils.CitizensHelper.getNPC;
import static me.alpha.kitpvp.utils.CitizensHelper.isNPC;
import static me.alpha.kitpvp.utils.ColorUtil.colorCode;

public class ReduxDeathEvent extends Event implements Cancellable{
    private static final HandlerList HANDLERS = new HandlerList();
    private final ReduxPlayer attacker;
    private final ReduxPlayer defender;
    private double xp = 14;
    private int xp_cap = 200;
    private double mystic_chance=0;
    private double baseMysticChance=0;
    private double gold = 18;
    private double gold_cap = 2500;
    private boolean isCancelled;

    public static HandlerList getHandlerList() {
        return HANDLERS;
    }

    public ReduxDeathEvent(ReduxPlayer attacker, ReduxPlayer defender) {
        this.attacker = attacker;
        this.defender = defender;
        this.isCancelled = false;
    }

    public void run(){

        if(!isNPC(defender.getPlayerObject())){
            if(ClassInstances.megaStreakData.getMegaStreak(defender.getPlayerUUID()).equals("overdrive") &&
                    ClassInstances.streakData.getStreak(defender.getPlayerUUID())>=50){
                defender.addPlayerEXP(4000);
                Sounds.SHOCKWAVE.play(defender.getPlayerObject());
                defender.getPlayerObject().sendMessage(colorCode("&a&lCONGRATS! &7you earned &b4,000 XP &7from &cOverdrive&7!"));
            }
        }


        if(isNPC(attacker.getPlayerObject()) && isNPC(defender.getPlayerObject())){
            NPC npc = getNPC(defender.getPlayerObject());
            npc.teleport(getBotSpawnLocation(npc.getEntity().getWorld()), PlayerTeleportEvent.TeleportCause.PLUGIN);
            return;
        }
        
        if(!isNPC(defender.getPlayerObject())) deleteBlob(defender.getPlayerObject());
        if(!isNPC(defender.getPlayerObject())){
            if(defender.getPerks().contains(ClassInstances.assistantStreaker.getRefID()) && ClassInstances.promotion.hasValue(defender.getPlayerUUID()) &&
                    (((Integer)ClassInstances.promotion.getValue(defender.getPlayerUUID()))>=1) && ClassInstances.streakData.getStreak(defender.getPlayerUUID())>=100){
                if(!isNPC(defender.getPlayerObject())) ClearRegular(defender.getPlayerObject());
                defender.getPlayerObject().sendMessage(colorCode("&e&lPROMOTION! &7you managed to reach a &c100 killstreak &7and kept your mystic lives!"));
            }else if(!isNPC(defender.getPlayerObject())) {
                ClearAndCheck(defender.getPlayerObject());
            }
        }

        // Defender Streak tick
        if (!isNPC(defender.getPlayerObject())) {

            if(!isNPC(defender.getPlayerObject()) &&
                    ClassInstances.megaStreakData.getMegaStreak(getDefender().getPlayerUUID()).equals("moon") && ClassInstances.streakData.getStreak(defender.getPlayerUUID()) >= 100){
                DecimalFormat formatter = new DecimalFormat("#,###");
                defender.getPlayerObject().sendMessage(colorCode("&b&lTO THE MOON! &7Earned &b+"+formatter.format(defender.getMoonXP()*defender.getMoonMultiplier())+" XP &7from megastreak (&b"+defender.getMoonMultiplier()+"x &7multiplier)"));
                defender.addPlayerEXP((int) Math.round(defender.getMoonXP()*defender.getMoonMultiplier()));
                Sounds.DEATH_GHAST_SCREAM.play(defender.getPlayerObject());
            }

            defender.setMoonXP(0);
            // Check if user has pending uber rewards
            UberRewardClaimDeath(defender.getPlayerObject());
            
            // Resets streaks
            ClassInstances.streakData.setStreak(defender.getPlayerUUID(), 0);
            
            // Refil Health
            defender.getPlayerObject().setHealth(defender.getPlayerObject().getMaxHealth());
            if(!isNPC(defender.getPlayerObject())&&
            ClassInstances.extraHearts.hasValue(defender.getPlayerUUID())){
                defender.getPlayerObject().setMaxHealth(20+((Integer)ClassInstances.extraHearts.getValue(defender.getPlayerUUID(), 1)*2));
            }else{
                defender.getPlayerObject().setMaxHealth(20);
            }

            // Refresh tab name
            if(!isNPC(defender.getPlayerObject())) NametagEdit.getApi().setNametag(defender.getPlayerObject(), ChatManager.getLevelText(defender.getPlayerObject())+ RankColor.getNameColor(defender.getPlayerObject()), "");
        }

        // Mega Streak Calculations
        String streak = ClassInstances.megaStreakData.getMegaStreak(attacker.getPlayerUUID());

        // Clearing Potions
        defender.removePotionEffect(PotionEffectType.SPEED);
        attacker.removePotionEffect(PotionEffectType.SPEED);


        if(!isNPC(attacker.getPlayerObject())){
            StreakManager(attacker.getPlayerObject());
        }

        // Sound effects for mega
        if(streak.equals("beastmode") ||
                streak.equals("overdrive") ||
                streak.equals("highlander")){
            if(ClassInstances.streakData.getStreak(attacker.getPlayerUUID())==50){

                String megastreakMessage = "&c&lOVERDRIVE";

                if(streak.equals("beastmode")){
                    megastreakMessage="&a&lBEASTMODE";
                }else if(streak.equals("highlander")){
                    megastreakMessage="&6&lHIGHLANDER";
                }

                ChatManager.broadcastMessage(colorCode("&c&lMEGASTREAK! " +
                        ChatManager.getLevelText(attacker.getPlayerObject()) + RankColor.getNameColor(attacker.getPlayerObject()) + attacker.getPlayerObject().getDisplayName()
                        + " &7activated " + megastreakMessage), attacker.getPlayerObject().getWorld());

                Sounds.MEGA_GENERAL.play(attacker.getPlayerObject());
            }
        }else if(streak.equals("moon") ||
                    streak.equals("magnum")||
                streak.equals("uber")){
                if(ClassInstances.streakData.getStreak(attacker.getPlayerUUID())==100){
                    String megastreakMessage = "&b&lTO THE MOON";

                    if(streak.equals("uber")){
                        megastreakMessage="&d&lUBERSTREAK";
                        ChatManager.broadcastMessage(colorCode("&c&lMEGASTREAK! " +
                                ChatManager.getLevelText(attacker.getPlayerObject()) + RankColor.getNameColor(attacker.getPlayerObject()) + attacker.getPlayerObject().getDisplayName()
                                + " &7activated " + megastreakMessage), attacker.getPlayerObject().getWorld());
                    }else if(streak.equals("moon")){
                        ChatManager.broadcastMessage(colorCode("&c&lMEGASTREAK! " +
                                ChatManager.getLevelText(attacker.getPlayerObject()) + RankColor.getNameColor(attacker.getPlayerObject()) + attacker.getPlayerObject().getDisplayName()
                                +" &7activated " + megastreakMessage), attacker.getPlayerObject().getWorld());
                    }
                    Sounds.MEGA_GENERAL.play(attacker.getPlayerObject());
                }
            }

        // Megastreak calcs
        if(streak.equals("beastmode") && ClassInstances.streakData.getStreak(attacker.getPlayerUUID()) >= 50){
            addXp(xp*.5);
            addGold((int) Math.round(gold*.75));
            setXp_cap(getXp_cap()+200);

            addXp(100);
            addXp(Math.min(Math.round((float)ClassInstances.streakData.getStreak(getAttacker().getPlayerUUID())/3), 200));
        }else if(streak.equals("overdrive") && ClassInstances.streakData.getStreak(attacker.getPlayerUUID()) >= 50){
            addXp(xp);
            addGold((int) Math.round(getGold()*.5));
            setXp_cap(getXp_cap()+100);

            addXp(50);
            addXp(Math.min(Math.round((float)ClassInstances.streakData.getStreak(getAttacker().getPlayerUUID())/3), 100));
        }else if(streak.equals("moon") && ClassInstances.streakData.getStreak(attacker.getPlayerUUID()) >= 100){
            addXp(100);
            addXp(xp*.2);
            setXp_cap(getXp_cap()+550);
            addXp(Math.min(Math.round((float)ClassInstances.streakData.getStreak(getAttacker().getPlayerUUID())/2), 350));
        }

        // Gold/XP calculations
        if(!isNPC(attacker.getPlayerObject())){


            new XpbumpLore().run(this);
            new XpboostLore().run(this);
            new SweatyLore().run(this);

            new MoctezumaLore().run(this);
            new GoldbumpLore().run(this);
            new GoldboostLore().run(this);

        }

        if(streak.equals("highlander") && ClassInstances.streakData.getStreak(attacker.getPlayerUUID()) >= 50){
            addGold((int) Math.round(getGold()*1.1));

            addGold(Math.min(100, Math.round((float)ClassInstances.streakData.getStreak(getAttacker().getPlayerUUID())/3)));
        }else if(streak.equals("uber") && ClassInstances.streakData.getStreak(attacker.getPlayerUUID()) >= 100){
            mystic_chance=5;
        }else if(streak.equals("magnum") && ClassInstances.streakData.getStreak(attacker.getPlayerUUID()) >= 100){
            ChatManager.broadcastMessage(colorCode("&c&lMEGASTREAK! " + ChatManager.getLevelText(attacker.getPlayerObject()) + RankColor.getNameColor(attacker.getPlayerObject()) + attacker.getPlayerObject().getDisplayName() +" &7activated &e&lMAGNUM OPUS &7and exploded! So smart!"),attacker.getPlayerObject().getWorld());
            attacker.getPlayerObject().getWorld().playEffect(attacker.getPlayerObject().getLocation(), Effect.EXPLOSION_LARGE, 10);
            Sounds.JUGGERNAUT_EXPLOSION.play(attacker.getPlayerObject());
            ClassInstances.renownData.addRenown(attacker.getPlayerUUID(), 7);
            KillMan(defender.getPlayerObject(), attacker.getPlayerObject());
        }else if(streak.equals("uber") && ClassInstances.streakData.getStreak(attacker.getPlayerUUID()) >= 200 && attacker.getPlayerObject().getMaxHealth()/2 == 10){
            attacker.getPlayerObject().setMaxHealth(attacker.getPlayerObject().getMaxHealth()-4);
        }

        // Boosters
        int XP_BOOSTER = 1;
        int GOLD_BOOSTER = 1;
        if(Booster.xpActive) XP_BOOSTER+=1;
        if(Booster.goldActive) GOLD_BOOSTER+=1;

        if(ClassInstances.experienceIndustrialComplex.hasValue(attacker.getPlayerUUID())){
            this.xp += xp*.2;
        }

        gold = gold*GOLD_BOOSTER;
        xp = xp*XP_BOOSTER;

        gold = gold*twoTimesEvent;
        xp = xp*twoTimesEvent;

        if(XP_BOOSTER>1) xp_cap+=300;
        if(twoTimesEvent>1) xp_cap+=300;

        // Celebrity
        if(!isNPC(attacker.getPlayerObject())){
            if(ClassInstances.celebrity.hasValue(attacker.getPlayerUUID())) {
                gold += gold;
            }
        }

        if(!isNPC(attacker.getPlayerObject())){
            if(ClassInstances.renownXpBump.hasValue(attacker.getPlayerUUID())){
                this.xp += ((Integer)ClassInstances.renownXpBump.getValue(attacker.getPlayerUUID()));
            }

            if(ClassInstances.experienceIndustrialComplex.hasValue(attacker.getPlayerUUID())){
                this.xp_cap += 50;
            }

            if(ClassInstances.renownGoldBoost.hasValue(attacker.getPlayerUUID())){
                this.gold += gold*(((double)((Integer)ClassInstances.renownGoldBoost.getValue(attacker.getPlayerUUID())))/100);
            }

            if(ClassInstances.tenacity.hasValue(attacker.getPlayerUUID())){
                attacker.getPlayerObject().setHealth(Math.min(attacker.getPlayerObject().getMaxHealth(),
                        attacker.getPlayerObject().getHealth()+
                                (((double)((Integer)ClassInstances.tenacity.getValue(attacker.getPlayerUUID())))/10)));
            }
        }

        if(isNPC(defender.getPlayerObject())&&
        !isNPC(attacker.getPlayerObject())){
            if(ClassInstances.botKills.hasValue(attacker.getPlayerObject().getUniqueId().toString())){
                ClassInstances.botKills.addValue(attacker.getPlayerObject().getUniqueId().toString(),1);
            }else{
                ClassInstances.botKills.setValue(attacker.getPlayerObject().getUniqueId().toString(),1);
            }

            if(((int)ClassInstances.botKills.getValue(attacker.getPlayerObject().getUniqueId().toString()))==40000){
                ClassInstances.factionReward.setValue(attacker.getPlayerObject().getUniqueId().toString(), "unclaimed");
            }
        }

        xp = (int) Math.round(xp);
        gold = (int) Math.round(gold);

        // Attacker Streak tick
        if(!isNPC(attacker.getPlayerObject())){
            ClassInstances.streakData.addStreak(attacker.getPlayerUUID(), 1);
            multiKill(attacker.getPlayerObject());
            PlayerFinishedJewl(attacker.getPlayerObject());

            if(ClassInstances.streakData.getStreak(attacker.getPlayerUUID()) <= 49){
                NametagEdit.getApi().setNametag(attacker.getPlayerObject(), ChatManager.getLevelText(attacker.getPlayerObject())+ RankColor.getNameColor(attacker.getPlayerObject()), "");
            }

            if(!KillMessages.containsKey(attacker.getPlayerUUID())){
                KillMessages.put(attacker.getPlayerUUID(), true);
            }else if(KillMessages.get(attacker.getPlayerUUID()).equals(true)){
                if(isNPC(defender.getPlayerObject())){
                    attacker.getPlayerObject().sendMessage(ChatColor.GREEN + colorCode("&lKILL! ") + ChatColor.GRAY + "on " + getNPC(defender.getPlayerObject()).getName() + ChatColor.RESET + ChatColor.AQUA + " +" + String.valueOf((int)Math.min(this.xp, xp_cap)) + "XP" + ChatColor.GOLD + " +" + String.valueOf((int) Math.min(this.gold, this.gold_cap)) + "g");
                }else{
                    attacker.getPlayerObject().sendMessage(ChatColor.GREEN + colorCode("&lKILL! ") + ChatColor.GRAY + "on " + defender.getPlayerObject().getDisplayName() + ChatColor.RESET + ChatColor.AQUA + " +" + String.valueOf((int)Math.min(this.xp, xp_cap)) + "XP" + ChatColor.GOLD + " +" + String.valueOf((int) Math.min(this.gold, this.gold_cap)) + "g");
                }
            }

        }

        // Streak Messages
        if(!isNPC(attacker.getPlayerObject())){
            // &c&lSTREAK! &7of &c5 &7kills by <level_username>

            if(ClassInstances.streakData.getStreak(attacker.getPlayerUUID())%5==0){
                ChatManager.broadcastMessage(colorCode("&c&lSTREAK! &7of &c"+ClassInstances.streakData.getStreak(attacker.getPlayerUUID())+" &7kills by <level_username>", attacker),attacker.getPlayerObject().getWorld());
            }

        }

        // Bounty Handling
        if(!isNPC(defender.getPlayerObject())) Bounty.BountyClaimed(defender.getPlayerObject(), attacker.getPlayerObject());

        // Kill rewards
        if(!isNPC(attacker.getPlayerObject())){
            attacker.addPlayerEXP((int) Math.round(Math.min(this.xp_cap, this.xp)));
            GoldData.hasEconomy(attacker.getPlayerUUID());
            GoldData.addEconomy(attacker.getPlayerUUID(), (int) Math.min((int) Math.round(this.gold), gold_cap));
        }

        // Teleporting
        if(isNPC(defender.getPlayerObject())){
            NPC npc = getNPC(defender.getPlayerObject());
            if(npc.getEntity()!=null) npc.teleport(getBotSpawnLocation(npc.getEntity().getWorld()), PlayerTeleportEvent.TeleportCause.PLUGIN);
        }else if (!isNPC(defender.getPlayerObject())){
            defender.getPlayerObject().teleport(getSpawnLocation(defender.getPlayerObject().getWorld()), PlayerTeleportEvent.TeleportCause.PLUGIN);
        }

        // Dark pant
        ClassInstances.needlessSufferingLore.run(this);

        // Standard Messages
        if(!isNPC(defender.getPlayerObject())) {
            if(isNPC(attacker.getPlayerObject())){
                defender.getPlayerObject().sendMessage(ChatColor.RED + colorCode("&lDEATH! ") + ChatColor.GRAY + "by " + HunterAPI.getRandomName());
            }else{
                defender.getPlayerObject().sendMessage(ChatColor.RED + colorCode("&lDEATH! ") + ChatColor.GRAY + "by " + attacker.getPlayerObject().getDisplayName());
            }
        }
        killTitle(defender.getPlayerObject());
        Bounty.BountyClaimed(defender.getPlayerObject(), attacker.getPlayerObject());
        if(!isNPC(attacker.getPlayerObject())) Bounty.BountyManager(attacker.getPlayerObject());

        if(streak.equals("moon") && ClassInstances.streakData.getStreak(attacker.getPlayerUUID()) >= 100){
            attacker.addMoonXP((int)Math.round( Math.min(xp_cap, xp)));
        }

        if(!isNPC(attacker.getPlayerObject())) ClassInstances.goldRequirementData.addGoldReq(attacker.getPlayerUUID(), Math.min((int) Math.round(gold), xp_cap));

        if(!isNPC(attacker.getPlayerObject())) killEnchants();

        if(!isNPC(attacker.getPlayerObject())) customDrops();

        // Final TICK Scoreboard refresh
        /*
        if(!isNPC(defender.getPlayerObject()) &&
            !isNPC(attacker.getPlayerObject())){
            attacker.refreshScoreBoard();
            defender.refreshScoreBoard();
        }

         */
    }
    
    @Override
    public HandlerList getHandlers() {
        return HANDLERS;
    }

    @Override
    public boolean isCancelled() {
        return this.isCancelled;
    }

    @Override
    public void setCancelled(boolean isCancelled) {
        this.isCancelled = isCancelled;
    }

    public ReduxPlayer getAttacker() {
        return this.attacker;
    }

    public ReduxPlayer getDefender() {
        return this.defender;
    }

    public int getXp() {
        return (int) xp;
    }

    public void addXp(double xps) {
        this.xp += xps;
    }

    public int getGold() {
        return (int) gold;
    }

    public void addGold(int gold) {
        this.gold += gold;
    }

    public int getXp_cap() {
        return xp_cap;
    }

    public void setXp_cap(int xp_cap) {
        this.xp_cap = xp_cap;
    }

    private static Boolean percentChance(double chance) {
        return Math.random() <= chance;
    }

    private void killTitle(Player player){
        if(!isNPC(player)) ScoreboardCore.CreateScore(player);
        player.removePotionEffect(PotionEffectType.SLOW);
        PacketPlayOutTitle title = new PacketPlayOutTitle(PacketPlayOutTitle.EnumTitleAction.TITLE,
                IChatBaseComponent.ChatSerializer.a("{\"text\":\"YOU DIED\",\"color\":\"red\"}"), 100, 20, 20);
        ((CraftPlayer) player).getHandle().playerConnection.sendPacket(title);
    }

    private void killEnchants(){

        CraftPlayer craftAttacker = (CraftPlayer) attacker.getPlayerObject(); //CraftBukkit
        EntityHuman entityAttacker = craftAttacker.getHandle(); //NMS

        //EntityHuman entityDefender = craftDefender.getHandle(); //NMS
        double abs = entityAttacker.getAbsorptionHearts();


        // Applying Gold and checking for extra enchants

        List<String> list = attacker.getPantEnchants();
        if(list != null){
            for (String s : list) {
                switch (s) {

                    case "blobIII":
                    case "blobII":
                    case "blobI":
                        PitBlobMap.blobTick(attacker.getPlayerObject());
                        break;


                    case "goldheartIII":
                        attacker.getPlayerObject().removePotionEffect(PotionEffectType.ABSORPTION);
                        entityAttacker.setAbsorptionHearts((float) Math.min(abs + 4, 12.0));
                        break;
                    case "goldheartII":
                        attacker.getPlayerObject().removePotionEffect(PotionEffectType.ABSORPTION);
                        entityAttacker.setAbsorptionHearts((float) Math.min(abs + 2, 10.0));
                        break;
                    case "goldheartI":
                        attacker.getPlayerObject().removePotionEffect(PotionEffectType.ABSORPTION);
                        entityAttacker.setAbsorptionHearts((float) Math.min(abs + 1, 8.0));
                        break;

                    case "pantsradarIII":
                        mystic_chance+=9;
                        break;
                    case "pantsradarII":
                        mystic_chance+=5;
                        break;
                    case "pantsradarI":
                        mystic_chance+=2;
                        break;
                }
            }
        }
    }

    public void addMysticChance(double chance){
        mystic_chance+=chance;
    }

    public void setMysticChance(double chance){
        mystic_chance=chance;
    }

    public double getMysticChance(){
        return baseMysticChance*(mystic_chance*.01);
    }

    public double getBaseMysticChance() {
        return baseMysticChance;
    }

    public void setBaseMysticChance(double baseMysticChance) {
        this.baseMysticChance = baseMysticChance;
    }

    public void addBaseMysticChance(double baseMysticChance) {
        this.baseMysticChance += baseMysticChance;
    }

    public void customDrops(){
        if(isNPC(defender.getPlayerObject()) && percentChance(.005) &&
        ClassInstances.heresy.hasValue(attacker.getPlayerUUID())){
            StashCore.safeGive(attacker.getPlayerObject(), enchants.vile);
            attacker.getPlayerObject().sendMessage(ColorUtil.colorCode("&9&lDONE! &7(Kill reward) &b+"+Math.min(xp_cap, xp)+"XP! &5+1 Chunk of Vile"));
            Sounds.MEGA_RNGESUS.play(attacker.getPlayerObject());
            attacker.addPlayerEXP((int) Math.min(xp_cap, xp));
            attacker.getPlayerObject().playSound(attacker.getPlayerObject().getLocation(), Sound.NOTE_PLING, 1.0F, 1.0F);
        }else if(!isNPC(defender.getPlayerObject()) && percentChance(.1) &&
                ClassInstances.heresy.hasValue(attacker.getPlayerUUID())){
            StashCore.safeGive(attacker.getPlayerObject(), enchants.vile);
            attacker.getPlayerObject().sendMessage(ColorUtil.colorCode("&9&lDONE! &7(Kill reward) &b+"+Math.min(xp_cap, xp)+"XP! &5+1 Chunk of Vile"));
            attacker.addPlayerEXP((int) Math.min(xp_cap, xp));
            Sounds.MEGA_RNGESUS.play(attacker.getPlayerObject());
        }

        if(ClassInstances.mysticism.hasValue(attacker.getPlayerUUID())&&
                percentChance(getMysticChance())){
            while (true){
                if(percentChance(.20)){
                    StashCore.safeGive(attacker.getPlayerObject(), enchants.fresh_greens);
                    if(isNPC(defender.getPlayerObject())){
                        attacker.getPlayerObject().sendMessage(colorCode("&d&lMYSTIC ITEM!" +
                                " &7dropped from killing " + getNPC(defender.getPlayerObject()).getName() + "&7!"));
                    }else{
                        attacker.getPlayerObject().sendMessage(colorCode("&d&lMYSTIC ITEM!" +
                                " &7dropped from killing " +
                                ChatManager.getLevelText(defender.getPlayerObject())+ RankColor.getNameColor(defender.getPlayerObject()) + defender.getPlayerObject().getDisplayName() + "&7!"));
                    }
                    attacker.getPlayerObject().playSound(attacker.getPlayerObject().getLocation(), Sound.NOTE_PLING, 1.0F, 1.0F);
                    break;
                }else if(percentChance(.20)){
                    StashCore.safeGive(attacker.getPlayerObject(), enchants.fresh_blues);
                    if(isNPC(defender.getPlayerObject())){
                        attacker.getPlayerObject().sendMessage(colorCode("&d&lMYSTIC ITEM!" +
                                " &7dropped from killing " + getNPC(defender.getPlayerObject()).getName() + "&7!"));
                    }else{
                        attacker.getPlayerObject().sendMessage(colorCode("&d&lMYSTIC ITEM!" +
                                " &7dropped from killing " +
                                ChatManager.getLevelText(defender.getPlayerObject())+ RankColor.getNameColor(defender.getPlayerObject()) + defender.getPlayerObject().getDisplayName() + "&7!"));
                    }
                    attacker.getPlayerObject().playSound(attacker.getPlayerObject().getLocation(), Sound.NOTE_PLING, 1.0F, 1.0F);
                    break;
                }else if(percentChance(.20)){
                    StashCore.safeGive(attacker.getPlayerObject(), enchants.fresh_reds);
                    if(isNPC(defender.getPlayerObject())){
                        attacker.getPlayerObject().sendMessage(colorCode("&d&lMYSTIC ITEM!" +
                                " &7dropped from killing " + getNPC(defender.getPlayerObject()).getName() + "&7!"));
                    }else{
                        attacker.getPlayerObject().sendMessage(colorCode("&d&lMYSTIC ITEM!" +
                                " &7dropped from killing " +
                                ChatManager.getLevelText(defender.getPlayerObject())+ RankColor.getNameColor(defender.getPlayerObject()) + defender.getPlayerObject().getDisplayName() + "&7!"));
                    }
                    attacker.getPlayerObject().playSound(attacker.getPlayerObject().getLocation(), Sound.NOTE_PLING, 1.0F, 1.0F);
                    break;
                }else if(percentChance(.20)){
                    StashCore.safeGive(attacker.getPlayerObject(), enchants.fresh_oranges);
                    if(isNPC(defender.getPlayerObject())){
                        attacker.getPlayerObject().sendMessage(colorCode("&d&lMYSTIC ITEM!" +
                                " &7dropped from killing " + getNPC(defender.getPlayerObject()).getName() + "&7!"));
                    }else{
                        attacker.getPlayerObject().sendMessage(colorCode("&d&lMYSTIC ITEM!" +
                                " &7dropped from killing " +
                                ChatManager.getLevelText(defender.getPlayerObject())+ RankColor.getNameColor(defender.getPlayerObject()) + defender.getPlayerObject().getDisplayName() + "&7!"));
                    }
                    attacker.getPlayerObject().playSound(attacker.getPlayerObject().getLocation(), Sound.NOTE_PLING, 1.0F, 1.0F);
                    break;
                }else if(percentChance(.20)) {
                    StashCore.safeGive(attacker.getPlayerObject(), enchants.fresh_yellows);
                    if (isNPC(defender.getPlayerObject())) {
                        attacker.getPlayerObject().sendMessage(colorCode("&d&lMYSTIC ITEM!" +
                                " &7dropped from killing " + getNPC(defender.getPlayerObject()).getName() + "&7!"));
                    } else {
                        attacker.getPlayerObject().sendMessage(colorCode("&d&lMYSTIC ITEM!" +
                                " &7dropped from killing " +
                                ChatManager.getLevelText(defender.getPlayerObject()) + RankColor.getNameColor(defender.getPlayerObject()) + defender.getPlayerObject().getDisplayName() + "&7!"));
                    }
                    attacker.getPlayerObject().playSound(attacker.getPlayerObject().getLocation(), Sound.NOTE_PLING, 1.0F, 1.0F);
                    break;
                }
            }
        }else if (ClassInstances.mysticism.hasValue(attacker.getPlayerUUID())&&
                percentChance(getMysticChance())) {
            StashCore.safeGive(attacker.getPlayerObject(), enchants.fresh_bow);
            if(isNPC(defender.getPlayerObject())){
                attacker.getPlayerObject().sendMessage(colorCode("&d&lMYSTIC ITEM!" +
                        " &7dropped from killing " + getNPC(defender.getPlayerObject()).getName() + "&7!"));
            }else{
                attacker.getPlayerObject().sendMessage(colorCode("&d&lMYSTIC ITEM!" +
                        " &7dropped from killing " +
                        ChatManager.getLevelText(defender.getPlayerObject())+ RankColor.getNameColor(defender.getPlayerObject()) + defender.getPlayerObject().getDisplayName() + "&7!"));
            }
            attacker.getPlayerObject().playSound(attacker.getPlayerObject().getLocation(), Sound.NOTE_PLING, 1.0F, 1.0F);
        }else if(ClassInstances.mysticism.hasValue(attacker.getPlayerUUID())&&
                percentChance(getMysticChance())){
            StashCore.safeGive(attacker.getPlayerObject(), enchants.fresh_sword);
            if(isNPC(defender.getPlayerObject())){
                attacker.getPlayerObject().sendMessage(colorCode("&d&lMYSTIC ITEM!" +
                        " &7dropped from killing " + getNPC(defender.getPlayerObject()).getName() + "&7!"));
            }else{
                attacker.getPlayerObject().sendMessage(colorCode("&d&lMYSTIC ITEM!" +
                        " &7dropped from killing " +
                        ChatManager.getLevelText(defender.getPlayerObject())+ RankColor.getNameColor(defender.getPlayerObject()) + defender.getPlayerObject().getDisplayName() + "&7!"));
            }
            attacker.getPlayerObject().playSound(attacker.getPlayerObject().getLocation(), Sound.NOTE_PLING, 1.0F, 1.0F);
        }
    }

    public void multiKill(Player player) {

        new BukkitRunnable() {
            int count = 0;

            @Override
            public void run() {

                switch(count) {
                    case 0:
                        Sounds.MULTI_1.play(player);
                        break;
                    case 1:
                        Sounds.MULTI_2.play(player);
                        break;
                    case 2:
                        Sounds.MULTI_3.play(player);
                        break;
                    case 3:
                        Sounds.MULTI_4.play(player);
                        break;
                    case 4:
                        Sounds.MULTI_5.play(player);
                        break;
                }

                if(++count > 5) cancel();
            }
        }.runTaskTimer(KitPvP.INSTANCE, 0L, 2L);
    }
}
