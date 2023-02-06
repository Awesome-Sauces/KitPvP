package me.alpha.kitpvp.Objects.ReduxPlayerObject;

import me.alpha.kitpvp.CustomEvents.ReduxDamageEvent;
import me.alpha.kitpvp.Data.ClassInstances;
import me.alpha.kitpvp.KitPvP;
import me.alpha.kitpvp.PitRemake.MysticWell.loreChecker;
import me.alpha.kitpvp.PitRemake.Scoreboard.ScoreboardCore;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.ArrayList;
import java.util.List;

import static me.alpha.kitpvp.Data.GoldData.*;
import static me.alpha.kitpvp.PitRemake.Bounties.Bounty.BountiesMap;
import static me.alpha.kitpvp.PitRemake.DeathHandler.DeathHandler.KillMan;

public class ReduxPlayer {

    Player player;
    String uuid;
    int task;
    int assistantStreakerCount = 0;
    boolean assuredStrike = false;
    boolean regCD = true;
    boolean fightOrFlight = false;
    boolean feastSteak = false;
    boolean counterStrike = false;

    int toughSkinStack = 0;

    boolean leechAbility = false;
    int khanteStack = 0;
    boolean vampireCD = true;
    boolean mlbCD = true;
    boolean booCD = true;
    boolean fishingCD = true;

    public int shotCount = 0;
    public int fShotCount = 0;
    boolean telebowCD = true;
    boolean GoldenCD = true;
    boolean SoupCD = true;
    boolean PitPocketCD = true;
    boolean AttackCD = true;
    boolean perunCD = true;
    boolean volleyCD = true;
    boolean prickCD = true;
    boolean ComboDamageCD = true;
    boolean gambleCD = true;
    boolean escape = true;
    double damageIncrease;
    int STRENGTH_TIMER = 7;
    boolean STRENGTH_REPEATABLE = false;
    double damageDecrease;
    double xpBooster = 1;
    int moonXP = 0;
    double goldBooster = 1;
    int obbyTime = 2400*5;
    int superStreaker = 0;
    double strength = 0.0;
    long strengthTimer;

    List<String> perks = new ArrayList<>();

    public ReduxPlayer(Player player) {
        this.player = player;
        this.damageIncrease = 0.01;
        this.damageDecrease = 0.01;
        this.uuid = String.valueOf(this.player.getUniqueId());
    }
/*
    public void spawnPlayer(){
        commandUtils.spawnPlayer(this.player);
    }

 */

    public void killPlayer(Player killer){
        KillMan(killer, this.player);
    }

    public void addStreak(Player victim){
        KillMan(this.player, victim);
    }

    public void setFightOrFlight(){
        fightOrFlight = !fightOrFlight;
    }

    public int getSuperStreaker() {
        return superStreaker;
    }

    public void setSuperStreaker(int superStreaker) {
        this.superStreaker = superStreaker;
    }

    public void setAssuredStrike(){
        assuredStrike = true;
    }

    public void setFeastSteak(){
        feastSteak = !feastSteak;
    }

    public void setCounterStrike(){
        counterStrike = !counterStrike;
    }


    public void doLeechAbility(ReduxDamageEvent event) {
        if(this!=event.getAttacker()) return;
        if(leechAbility) {
            player.setHealth(Math.min(player.getMaxHealth(), player.getHealth() + 1));
            event.addReduxDamageMultiplier(20);
            leechAbility = false;
        }
    }

    public void doAssuredStrike(ReduxDamageEvent event) {
        if(this!=event.getAttacker()) return;
        if(assuredStrike) {
            event.addReduxDamageMultiplier(35);
            event.getAttacker().addPotionEffect(PotionEffectType.SPEED, 20, 1);
            assuredStrike = false;
        }
    }

    public void doKhanteAbility(ReduxDamageEvent event) {
        if(this!=event.getAttacker()) return;
        if(BountiesMap.containsKey(event.getDefender().getPlayerUUID()) &&
        BountiesMap.get(event.getDefender().getPlayerUUID())>0){
            event.addReduxDamageMultiplier(getKhanteStack());
        }
    }

    public void doToughSkinAbility(ReduxDamageEvent event){
        if(this!=event.getDefender()) return;
        event.subtractReduxDamageMultiplier(toughSkinStack);
    }

    public void setLeechAbility(){
        leechAbility = true;
    }

    public int getPlayerPrestige(){
        return ClassInstances.prestigeData.getPrestige(this.uuid);
    }

    public void setPlayerPrestige(int amount){
        ClassInstances.prestigeData.setPrestige(this.uuid, amount);
    }

    public void addPlayerPrestige(){
        ClassInstances.prestigeData.addPrestige(this.uuid, 1);
    }

    public void addPlayerPrestige(int amount){
        ClassInstances.prestigeData.addPrestige(this.uuid, amount);
    }

    public double getPlayerGold(){
        hasEconomy(this.uuid);
        return getEconomy(this.uuid);
    }

    public void setPlayerGold(int amount){
        hasEconomy(this.uuid);
        setEconomy(this.uuid, amount);
    }

    public void addPlayerGold(int amount){
        hasEconomy(this.uuid);
        addEconomy(this.uuid, amount);
    }

    public boolean getEscape(){
        if (this.escape){
            if(this.player.getHealth() / 2 == 10){
                this.escape = false;
                return true;
            }else return false;
        }
        return false;
    }

    public void setEscape(boolean set){this.escape = set;}

    public void resetEscape(){this.escape = true;}

    public void setMlbCD(){this.mlbCD = !this.mlbCD;}

    public boolean getMlbCD(){return this.mlbCD;}

    public void setVolleyCD(){this.volleyCD = !this.volleyCD;}

    public boolean getVolleyCD(){return this.volleyCD;}

    public void setVampireCD(){this.vampireCD = !this.vampireCD;}

    public boolean getVampireCD(){return this.vampireCD;}

    public void setPrickCD(){this.prickCD = !this.prickCD;}

    public boolean getPrickCD(){return this.prickCD;}

    public void setPitPocketCD(){this.PitPocketCD = !this.PitPocketCD;}

    public boolean getPitPocketCD(){return this.PitPocketCD;}

    public void setAttackCD(){this.AttackCD = !this.AttackCD;}

    public boolean getAttackCD(){return this.AttackCD;}

    public boolean getRegCD(){return this.regCD;}

    public void setRegCD(){this.regCD = !this.regCD;}

    public boolean getBooCD(){return this.booCD;}

    public void setBooCD(){this.booCD = !this.booCD;}

    public boolean getTelebowCD(){return this.telebowCD;}

    public void setTelebowCD(){this.telebowCD = !this.telebowCD;}

    public boolean getGoldenCD(){return this.GoldenCD;}

    public void setGoldenCD(){this.GoldenCD = !this.GoldenCD;}

    public boolean getPerunCD(){return this.perunCD;}
    public boolean getComboDamageCD(){return this.ComboDamageCD;}

    public void setPerunCD(){this.perunCD = !this.perunCD;}
    public void setComboDamageCD(){this.ComboDamageCD = !this.ComboDamageCD;}

    public boolean getGambleCD(){return this.gambleCD;}

    public void setGambleCD(){this.gambleCD = !this.gambleCD;}

    public boolean getFishingCD(){return this.fishingCD;}

    public void setFishingCD(){this.fishingCD = !this.fishingCD;}

    public double getPlayerGoldBooster(){
        return this.goldBooster;
    }

    public void setPlayerGoldBooster(int amount){
        this.goldBooster += amount;
    }

    public double getPlayerXpBooster(){
        return this.xpBooster;
    }

    public void setPlayerXpBooster(double amount){
        this.xpBooster += amount;
    }

    public void setPlayerIncrease(double amount){
        this.damageIncrease = amount;
    }

    public void addPlayerIncrease(double amount){
        this.damageIncrease += amount;
    }

    public void setPlayerEXP(int amount){
        ClassInstances.xpData.setXp(this.uuid, amount);
    }

    public void addPlayerEXP(int amount){
        ClassInstances.xpData.addXp(this.uuid, amount);
    }

    public void setPlayerDecrease(double amount){
        this.damageIncrease = amount;
    }

    public void addPlayerDecrease(double amount){
        this.damageIncrease += amount;
    }

    public double getPlayerIncrease(){
        return this.damageIncrease;
    }

    public double getPlayerDecrease(){
        return this.damageDecrease;
    }

    public boolean getSoupCD(){return this.SoupCD;}

    public void setSoupCD(){this.SoupCD = !this.SoupCD;}

    public int getKhanteStack() {
        return khanteStack;
    }

    public void setKhanteStack(int khanteStack) {
        this.khanteStack = khanteStack;
    }

    public int getToughSkinStack() {
        return toughSkinStack;
    }

    public void setToughSkinStack(int toughSkinStack) {
        this.toughSkinStack = toughSkinStack;
    }

    public int tickAssistantStreaker(){
        this.assistantStreakerCount++;
        return this.assistantStreakerCount;
    }

    public void resetAssistantStreaker(){
        this.assistantStreakerCount=0;
    }

    public String getPlayerUUID(){
        return this.uuid;
    }

    public Player getPlayerObject(){
        return this.player;
    }

    public void refreshScoreBoard(){
        ScoreboardCore.CreateScore(this.player);
    }

    public int getObsidianTime(){return this.obbyTime;}

    public ItemStack getHelmet(){
        if (this.player.getInventory().getHelmet() != null) return this.player.getInventory().getHelmet();
        else return null;
    }

    public ItemStack getChestplate(){
        if (this.player.getInventory().getChestplate() != null) return this.player.getInventory().getChestplate();
        else return null;
    }

    public ItemStack getLeggings(){
        if (this.player.getInventory().getLeggings() != null) return this.player.getInventory().getLeggings();
        else return null;
    }

    public ItemStack getBoots(){
        if (this.player.getInventory().getBoots() != null) return this.player.getInventory().getBoots();
        else return null;
    }

    public ItemStack getMainHand(){
        if (this.player.getInventory().getItemInHand() != null) return this.player.getInventory().getItemInHand();
        else return null;
    }

    public List<String> getPantEnchants(){
        if(getLeggings() != null && getLeggings().getItemMeta().getLore() != null) return loreChecker.CheckEnchantOnPant(getLeggings().getItemMeta().getLore());
        else return null;
    }

    public List<String> getSwordEnchants(){
        if(getMainHand() != null && getMainHand().getItemMeta() != null && getMainHand().getItemMeta().getLore() != null) return loreChecker.CheckEnchantOnSword(getMainHand().getItemMeta().getLore());
        else return null;
    }


    public String getPerks() {
        String perks = "";
        perks+= ClassInstances.perkSlotOne.getValue(uuid, "");
        perks+= ClassInstances.perkSlotTwo.getValue(uuid, "");
        perks+= ClassInstances.perkSlotThree.getValue(uuid, "");
        perks+= ClassInstances.perkSlotFour.getValue(uuid, "");
        return perks;
    }

    public int getPerksAmount(){
        return perks.size();
    }

    public void addPerks(String perk){
        this.perks.add(perk);
    }

    public void setPerks(List<String> perks) {
        this.perks = perks;
    }

    public void strengthTick(){
        STRENGTH_TIMER=7;
        strength = Math.min(.40, strength+.08);

        if (!this.STRENGTH_REPEATABLE) {
            this.STRENGTH_REPEATABLE = true;
            task = Bukkit.getScheduler().scheduleSyncRepeatingTask(KitPvP.INSTANCE, new Runnable() {
                @Override
                public void run() {

                    if (STRENGTH_TIMER > 0) {
                        STRENGTH_TIMER -= 1;
                    }

                    if (STRENGTH_TIMER <= 0) {
                        strength = 0.0;
                    }
                }
            }, 20, 20);
        }
    }

    public void doFightOrFlight(ReduxDamageEvent event){
        if(this!=event.getAttacker()) return;

        if(fightOrFlight){
            event.addReduxDamageMultiplier(20);
        }
    }

    public void doCounterStrike(ReduxDamageEvent event){

        if(counterStrike){
            if(this==event.getAttacker()) event.addReduxDamageMultiplier(15);

            if(event.getDefender()==this){
                event.subtractBaseDamage(1);
            }
        }

    }

    public void doFeastSteak(ReduxDamageEvent event){
        if(this!=event.getAttacker()) return;

        if(feastSteak){
            event.addReduxDamageMultiplier(20);
        }
    }

    public void cancelSTRENGTH_TIMER(){
        this.STRENGTH_REPEATABLE=false;
        Bukkit.getScheduler().cancelTask(this.task);
    }

    public int getSTRENGTH_TIMER() {
        return STRENGTH_TIMER;
    }

    public void setSTRENGTH_TIMER(int STRENGTH_TIMER) {
        this.STRENGTH_TIMER = STRENGTH_TIMER;
    }

    public void setStrength(double strength){
        this.strength = strength;
    }

    public double getStrength(){
        return strength;
    }

    public int getStrengthTier(){
        return (int) (strength/.08);
    }

    public int getMoonXP() {
        return moonXP;
    }

    public void setMoonXP(int moonXP) {
        this.moonXP = moonXP;
    }

    public void addMoonXP(int moonXP) {
        this.moonXP += moonXP;
    }

    public double getMoonMultiplier(){
        if(ClassInstances.streakData.getStreak(this.uuid)>100){
            return Math.min((double)(ClassInstances.streakData.getStreak(this.uuid)-100)*.005, 1.5);
        }else return 0.0;
    }

    public void addPotionEffect(PotionEffectType type, int time, int power){
        if(!player.hasPotionEffect(type)){
            player.addPotionEffect(new PotionEffect(type, time*20, Math.max(power-1, 0), true, true));
        }
    }

    public void removePotionEffect(PotionEffectType type){
        if(player.hasPotionEffect(type)){
            player.removePotionEffect(type);
        }
    }

    public static float getWalkSpeed(float enchantLvl) {
        if(enchantLvl == 0) return 0.2F;
        else return (0.2F+(0.2F*enchantLvl));
    }

    public void setSpeed(float speed){
        player.setWalkSpeed(getWalkSpeed(speed));
    }

    public float getSpeed(){
        return getWalkSpeed(0);
    }
}
