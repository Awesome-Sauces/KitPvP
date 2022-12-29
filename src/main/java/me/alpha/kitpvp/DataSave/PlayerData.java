package me.alpha.kitpvp.DataSave;

import me.alpha.kitpvp.Data.ClassInstances;
import me.alpha.kitpvp.Data.GoldData;
import org.bukkit.configuration.serialization.ConfigurationSerializable;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class PlayerData implements Cloneable, ConfigurationSerializable {

    public PlayerData(String uuid){
        this.uuid = uuid;
    }

    Player player;

    public void loadData(Player player) throws IOException {
        // Inventory and Armor
        Inventory inventory = player.getInventory();
        Inventory enderchest = player.getEnderChest();

        try {
            List<ItemStack> itemStackList = Converter64.inventoryItemsFrom64(getInventory());

            for(int i=0; i < inventory.getSize(); i++){
                inventory.setItem(i, itemStackList.get(Math.min(i, itemStackList.size())));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try {
            List<ItemStack> itemStackList = Converter64.inventoryItemsFrom64(getEnderChest());

            for(int i=0; i < enderchest.getSize(); i++){
                enderchest.setItem(i, itemStackList.get(Math.min(i, itemStackList.size())));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        if(!Objects.equals(getHelmet(), "")) player.getInventory().setHelmet(Converter64.itemFrom64(getHelmet()));
        if(!Objects.equals(getChestplate(), "")) player.getInventory().setChestplate(Converter64.itemFrom64(getChestplate()));
        if(!Objects.equals(getLeggings(), "")) player.getInventory().setLeggings(Converter64.itemFrom64(getLeggings()));
        if(!Objects.equals(getBoots(), "")) player.getInventory().setBoots(Converter64.itemFrom64(getBoots()));

        // Boosters
        ClassInstances.botBoosterData.setBooster(getUuid(), getBotBoosters());
        ClassInstances.xpBoosterData.setBooster(getUuid(), getXpBoosters());
        ClassInstances.goldBoosterData.setBooster(getUuid(), getGoldBoosters());

        // Streak/Perks data
        ClassInstances.streakData.setStreak(getUuid(), getStreakAmount());
        ClassInstances.megaStreakData.setMegaStreak(getUuid(), getMegaStreak());
        ClassInstances.perkSlotOne.setValue(getUuid(), getPerkSlotOne());
        ClassInstances.perkSlotTwo.setValue(getUuid(), getPerkSlotTwo());
        ClassInstances.perkSlotThree.setValue(getUuid(), getPerkSlotThree());
        ClassInstances.perkSlotFour.setValue(getUuid(), getPerkSlotFour());

        // Int/Double(s)
        ClassInstances.prestigeData.setPrestige(getUuid(), getPrestige());
        ClassInstances.renownData.setRenown(getUuid(), getRenown());
        ClassInstances.xpData.setXp(getUuid(), (int) getXp());
        GoldData.setEconomy(getUuid(), (int) getGold());
        ClassInstances.goldRequirementData.setGoldReq(getUuid(), (int) getGoldRequirement());

        // Renown Shop Data:
        // Renown Shop - KillStreaks
        ClassInstances.beastmodeStreak.setValue(uuid, getBeastMode());
        ClassInstances.highlanderStreak.setValue(uuid, getHighlander());
        ClassInstances.magnumOpus.setValue(uuid, getMagnumOpus());
        ClassInstances.moonStreak.setValue(uuid, getMoon());
        ClassInstances.uberStreak.setValue(uuid, getUber());
        // Renown Shop - Upgrades
        ClassInstances.heresy.setValue(uuid, getHeresy());
        ClassInstances.celebrity.setValue(uuid, getCelebrity());
        ClassInstances.experienceIndustrialComplex.setValue(uuid, getIndustrial());
        ClassInstances.extraHearts.setValue(uuid, getExtraHearts());
        ClassInstances.fastPass.setValue(uuid, getFastPass());
        ClassInstances.tenacity.setValue(uuid, getTenacity());
        ClassInstances.promotion.setValue(uuid, getPromotion());
        ClassInstances.theWay.setValue(uuid, getTheWay());
        ClassInstances.renownXpBump.setValue(uuid, getRenownXpBump());
        ClassInstances.renownGoldBoost.setValue(uuid, getRenownGoldBoost());
        ClassInstances.mysticism.setMysticismChance(uuid, getMysticism());
    }

    /*
                    Inventory inventory = player.getInventory();

                try {
                    List<ItemStack> itemStackList = Base64.inventoryItemsFrom64(Inventories.get(player.getUniqueId()));

                    for(int i=0; i < inventory.getSize(); i++){
                        inventory.setItem(i, itemStackList.get(Math.min(i, itemStackList.size())));
                    }
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
     */

    public void saveData(Player player){

        // Armor/Inventory
        setHelmet(Converter64.itemTo64(player.getInventory().getHelmet()));
        setChestplate(Converter64.itemTo64(player.getInventory().getChestplate()));
        setLeggings(Converter64.itemTo64(player.getInventory().getLeggings()));
        setBoots(Converter64.itemTo64(player.getInventory().getBoots()));

        setInventory(Converter64.inventoryTo64(player.getInventory()));
        setEnderChest(Converter64.inventoryTo64(player.getEnderChest()));

        // Boosters
        setBotBoosters(ClassInstances.botBoosterData.getBooster(getUuid()));
        setXpBoosters(ClassInstances.xpBoosterData.getBooster(getUuid()));
        setGoldBoosters(ClassInstances.goldBoosterData.getBooster(getUuid()));

        // Streak/Perks data
        setStreakAmount(ClassInstances.streakData.getStreak(getUuid()));
        setMegaStreak(ClassInstances.megaStreakData.getMegaStreak(getUuid()));
        setPerkSlotOne((String) ClassInstances.perkSlotOne.getValue(getUuid(), ""));
        setPerkSlotTwo((String) ClassInstances.perkSlotTwo.getValue(getUuid(), ""));
        setPerkSlotThree((String) ClassInstances.perkSlotThree.getValue(getUuid(), ""));
        setPerkSlotFour((String) ClassInstances.perkSlotFour.getValue(getUuid(), ""));

        // Int/Double(s)
        setPrestige(ClassInstances.prestigeData.getPrestige(getUuid()));
        setRenown(ClassInstances.renownData.getRenown(getUuid()));
        setXp(ClassInstances.xpData.getXp(getUuid()));
        setGold(GoldData.getEconomy(getUuid()));
        setGoldRequirement(ClassInstances.goldRequirementData.getGoldReq(getUuid()));

        // Renown Shop Data:
        // Renown Shop - KillStreaks
        setOverdrive(1);
        setBeastMode((Integer) ClassInstances.beastmodeStreak.getValue(uuid, 0));
        setHighlander((Integer) ClassInstances.highlanderStreak.getValue(uuid, 0));
        setMagnumOpus((Integer) ClassInstances.magnumOpus.getValue(uuid, 0));
        setMoon((Integer) ClassInstances.moonStreak.getValue(uuid, 0));
        setUber((Integer) ClassInstances.uberStreak.getValue(uuid, 0));
        // Renown Shop - Upgrades
        setHeresy((Integer) ClassInstances.heresy.getValue(uuid, 0));
        setCelebrity((Integer) ClassInstances.celebrity.getValue(uuid, 0));
        setIndustrial((Integer) ClassInstances.experienceIndustrialComplex.getValue(uuid, 0));
        setExtraHearts((Integer) ClassInstances.extraHearts.getValue(uuid, 0));
        setFastPass((Integer) ClassInstances.fastPass.getValue(uuid, 0));
        setTenacity((Integer) ClassInstances.tenacity.getValue(uuid, 0));
        setPromotion((Integer) ClassInstances.promotion.getValue(uuid, 0));
        setTheWay((Integer) ClassInstances.theWay.getValue(uuid, 0));
        setRenownXpBump((Integer) ClassInstances.renownXpBump.getValue(uuid, 0));
        setRenownGoldBoost((Integer) ClassInstances.renownGoldBoost.getValue(uuid, 0));
        setMysticism((Integer) ClassInstances.mysticism.getValue(uuid, 0));
    }

    public static PlayerData deserialize(Map<String, Object> args) {
        PlayerData playerData = new PlayerData((String) args.get("uuid"),
                (Integer) args.get("botBooster"), (Integer) args.get("goldBooster"),
                (Integer) args.get("xpBooster"), (String) args.get("megastreak"), (String) args.get("perkSlotOne"),
                (String) args.get("perkSlotTwo"), (String) args.get("perkSlotThree"), (String) args.get("perkSlotFour"),
                (Integer) args.get("streakAmount"), (Integer) args.get("prestige"), (Integer) args.get("renown"), (Double) args.get("xp"),
                (Double) args.get("gold"), (Double) args.get("goldRequirement"), 1, (Integer) args.get("beastmode"),
                (Integer) args.get("highlander"), (Integer) args.get("magnumOpus"), (Integer) args.get("moon"),
                (Integer) args.get("uber"), (Integer) args.get("heresy"), (Integer) args.get("celebrity"),
                (Integer) args.get("industrial"), (Integer) args.get("extraHearts"), (Integer) args.get("fastPass"),
                (Integer) args.get("tenacity"), (Integer) args.get("promotion"), (Integer) args.get("theWay"),
                (Integer) args.get("renownXpBump"), (Integer) args.get("renownGoldBoost"), (Integer) args.get("mysticism"),
                (String) args.get("helmet"), (String) args.get("chestplate"), (String) args.get("leggings"),
                (String) args.get("boots"), (String) args.get("inventory"), (String) args.get("enderChest"));

        return playerData;
    }

    public Map<String, Object> serialize() {
        Map<String, Object> result = new LinkedHashMap<>();

        result.put("uuid", getUuid());

        // Armor and inventory
        result.put("inventory", getInventory());
        result.put("enderChest", getEnderChest());

        result.put("helmet", getHelmet());
        result.put("chestplate", getChestplate());
        result.put("leggings", getLeggings());
        result.put("boots", getBoots());

        // Boosters
        result.put("botBooster", getBotBoosters());
        result.put("goldBooster", getGoldBoosters());
        result.put("xpBooster", getXpBoosters());

        // Int/Double(s)
        result.put("prestige", getPrestige());
        result.put("renown", getRenown());
        result.put("xp", getXp());
        result.put("gold", getGold());
        result.put("goldRequirement", getGoldRequirement());

        // Streak/Perks data
        result.put("megastreak", getMegaStreak());
        result.put("perkSlotOne", getPerkSlotOne());
        result.put("perkSlotTwo", getPerkSlotTwo());
        result.put("perkSlotThree", getPerkSlotThree());
        result.put("perkSlotFour", getPerkSlotFour());
        result.put("streakAmount", getStreakAmount());

        // Renown Shop Data
        // Renown Shop - KillStreaks
        result.put("beastmode", getBeastMode());
        result.put("highlander", getHighlander());
        result.put("magnumOpus", getMagnumOpus());
        result.put("moon", getMoon());
        result.put("uber", getUber());

        // Renown Shop - Upgrades
        result.put("heresy", getHeresy());
        result.put("celebrity", getCelebrity());
        result.put("industrial", getIndustrial());
        result.put("extraHearts", getExtraHearts());
        result.put("fastPass", getFastPass());
        result.put("tenacity", getTenacity());
        result.put("promotion", getPromotion());
        result.put("theWay", getTheWay());
        result.put("renownXpBump", getRenownXpBump());
        result.put("renownGoldBoost", getRenownGoldBoost());
        result.put("mysticism", getMysticism());

        return result;
    }

    @Override
    public PlayerData clone() {
        try {
            return (PlayerData) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }

    public PlayerData(String uuid, int botBoosters, int goldBoosters,
                      int xpBoosters, String megaStreak, String perkSlotOne,
                      String perkSlotTwo, String perkSlotThree, String perkSlotFour,
                      int streakAmount, int prestige, int renown, double xp,
                      double gold, double goldRequirement, int overdrive, int beastMode,
                      int highlander, int magnumOpus, int moon, int uber, int heresy, int celebrity,
                      int industrial, int extraHearts, int fastPass, int tenacity, int promotion, int theWay,
                      int renownXpBump, int renownGoldBoost, int mysticism,
                      String helmet, String chestplate, String leggings, String boots, String inventory, String enderChest) {
        this.uuid = uuid; this.botBoosters = botBoosters;
        this.goldBoosters = goldBoosters; this.xpBoosters = xpBoosters;
        this.megaStreak = megaStreak; this.perkSlotOne = perkSlotOne;
        this.perkSlotTwo = perkSlotTwo; this.perkSlotThree = perkSlotThree;
        this.perkSlotFour = perkSlotFour; this.streakAmount = streakAmount;
        this.prestige = prestige; this.renown = renown;
        this.xp = xp; this.gold = gold;
        this.goldRequirement = goldRequirement; this.overdrive = overdrive;
        this.beastMode = beastMode; this.highlander = highlander;
        this.magnumOpus = magnumOpus; this.moon = moon;
        this.uber = uber; this.heresy = heresy;
        this.celebrity = celebrity; this.industrial = industrial;
        this.extraHearts = extraHearts; this.fastPass = fastPass;
        this.tenacity = tenacity; this.promotion = promotion;
        this.theWay = theWay; this.renownXpBump = renownXpBump;
        this.renownGoldBoost = renownGoldBoost; this.mysticism = mysticism;
        this.helmet = helmet; this.chestplate = chestplate;
        this.leggings = leggings; this.boots = boots;
        this.inventory = inventory; this.enderChest = enderChest;
    }

    String uuid;

    // Inventory
    String helmet = "";
    String chestplate = "";
    String leggings = "";
    String boots = "";
    String inventory = "";
    String enderChest = "";

    // Boosters
    int botBoosters = 0;
    int goldBoosters = 0;
    int xpBoosters = 0;

    // Streak/Perks data
    String megaStreak = "overdrive";
    String perkSlotOne = "";
    String perkSlotTwo = "";
    String perkSlotThree = "";
    String perkSlotFour = "";
    int streakAmount = 0;

    // Int/Double(s)
    int prestige = 0;
    int renown = 0;
    double xp = 0;
    double gold = 0;
    double goldRequirement = 0;

    // Renown Shop Data

    // Renown Shop - KillStreaks
    int overdrive = 1;
    int beastMode = 0;
    int highlander = 0;
    int magnumOpus = 0;
    int moon = 0;
    int uber = 0;
    // Renown Shop - Upgrades
    int heresy = 0;
    int celebrity = 0;
    int industrial = 0;
    int extraHearts = 0;
    int fastPass = 0;
    int tenacity = 0;
    int promotion = 0;
    int theWay = 0;
    int renownXpBump = 0;
    int renownGoldBoost = 0;
    int mysticism = 0;

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public int getBotBoosters() {
        return botBoosters;
    }

    public void setBotBoosters(int botBoosters) {
        this.botBoosters = botBoosters;
    }

    public int getGoldBoosters() {
        return goldBoosters;
    }

    public void setGoldBoosters(int goldBoosters) {
        this.goldBoosters = goldBoosters;
    }

    public int getXpBoosters() {
        return xpBoosters;
    }

    public void setXpBoosters(int xpBoosters) {
        this.xpBoosters = xpBoosters;
    }

    public String getMegaStreak() {
        return megaStreak;
    }

    public void setMegaStreak(String megaStreak) {
        this.megaStreak = megaStreak;
    }

    public String getPerkSlotOne() {
        return perkSlotOne;
    }

    public void setPerkSlotOne(String perkSlotOne) {
        this.perkSlotOne = perkSlotOne;
    }

    public String getPerkSlotTwo() {
        return perkSlotTwo;
    }

    public void setPerkSlotTwo(String perkSlotTwo) {
        this.perkSlotTwo = perkSlotTwo;
    }

    public String getPerkSlotThree() {
        return perkSlotThree;
    }

    public void setPerkSlotThree(String perkSlotThree) {
        this.perkSlotThree = perkSlotThree;
    }

    public String getPerkSlotFour() {
        return perkSlotFour;
    }

    public void setPerkSlotFour(String perkSlotFour) {
        this.perkSlotFour = perkSlotFour;
    }

    public int getStreakAmount() {
        return streakAmount;
    }

    public void setStreakAmount(int streakAmount) {
        this.streakAmount = streakAmount;
    }

    public int getPrestige() {
        return prestige;
    }

    public void setPrestige(int prestige) {
        this.prestige = prestige;
    }

    public int getRenown() {
        return renown;
    }

    public void setRenown(int renown) {
        this.renown = renown;
    }

    public double getXp() {
        return xp;
    }

    public void setXp(double xp) {
        this.xp = xp;
    }

    public double getGold() {
        return gold;
    }

    public void setGold(double gold) {
        this.gold = gold;
    }

    public double getGoldRequirement() {
        return goldRequirement;
    }

    public void setGoldRequirement(double goldRequirement) {
        this.goldRequirement = goldRequirement;
    }

    public int getOverdrive() {
        return overdrive;
    }

    public void setOverdrive(int overdrive) {
        this.overdrive = overdrive;
    }

    public int getBeastMode() {
        return beastMode;
    }

    public void setBeastMode(int beastMode) {
        this.beastMode = beastMode;
    }

    public int getHighlander() {
        return highlander;
    }

    public void setHighlander(int highlander) {
        this.highlander = highlander;
    }

    public int getMagnumOpus() {
        return magnumOpus;
    }

    public void setMagnumOpus(int magnumOpus) {
        this.magnumOpus = magnumOpus;
    }

    public int getMoon() {
        return moon;
    }

    public void setMoon(int moon) {
        this.moon = moon;
    }

    public int getUber() {
        return uber;
    }

    public void setUber(int uber) {
        this.uber = uber;
    }

    public int getHeresy() {
        return heresy;
    }

    public void setHeresy(int heresy) {
        this.heresy = heresy;
    }

    public int getCelebrity() {
        return celebrity;
    }

    public void setCelebrity(int celebrity) {
        this.celebrity = celebrity;
    }

    public int getIndustrial() {
        return industrial;
    }

    public void setIndustrial(int industrial) {
        this.industrial = industrial;
    }

    public int getExtraHearts() {
        return extraHearts;
    }

    public void setExtraHearts(int extraHearts) {
        this.extraHearts = extraHearts;
    }

    public int getFastPass() {
        return fastPass;
    }

    public void setFastPass(int fastPass) {
        this.fastPass = fastPass;
    }

    public int getTenacity() {
        return tenacity;
    }

    public void setTenacity(int tenacity) {
        this.tenacity = tenacity;
    }

    public int getPromotion() {
        return promotion;
    }

    public void setPromotion(int promotion) {
        this.promotion = promotion;
    }

    public int getTheWay() {
        return theWay;
    }

    public void setTheWay(int theWay) {
        this.theWay = theWay;
    }

    public int getRenownXpBump() {
        return renownXpBump;
    }

    public void setRenownXpBump(int renownXpBump) {
        this.renownXpBump = renownXpBump;
    }

    public int getRenownGoldBoost() {
        return renownGoldBoost;
    }

    public void setRenownGoldBoost(int renownGoldBoost) {
        this.renownGoldBoost = renownGoldBoost;
    }

    public int getMysticism() {
        return mysticism;
    }

    public void setMysticism(int mysticism) {
        this.mysticism = mysticism;
    }

    public String getHelmet() {
        return helmet;
    }

    public void setHelmet(String helmet) {
        this.helmet = helmet;
    }

    public String getChestplate() {
        return chestplate;
    }

    public void setChestplate(String chestplate) {
        this.chestplate = chestplate;
    }

    public String getLeggings() {
        return leggings;
    }

    public void setLeggings(String leggings) {
        this.leggings = leggings;
    }

    public String getBoots() {
        return boots;
    }

    public void setBoots(String boots) {
        this.boots = boots;
    }

    public String getInventory() {
        return inventory;
    }

    public void setInventory(String inventory) {
        this.inventory = inventory;
    }

    public String getEnderChest() {
        return enderChest;
    }

    public void setEnderChest(String enderChest) {
        this.enderChest = enderChest;
    }
}
