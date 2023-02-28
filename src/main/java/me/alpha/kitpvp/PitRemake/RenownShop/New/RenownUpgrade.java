package me.alpha.kitpvp.PitRemake.RenownShop.New;

import de.tr7zw.nbtapi.NBTItem;
import me.alpha.kitpvp.Data.ClassInstances;
import me.alpha.kitpvp.utils.DataStore;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static me.alpha.kitpvp.utils.ColorUtil.colorCode;
import static me.alpha.kitpvp.utils.IntegerHelper.integerToRoman;
import static me.alpha.kitpvp.utils.advancedInventory.ItemMaker;

public abstract class RenownUpgrade extends DataStore {
    private String lore = "";
    private int prestigeRequirement = 0;
    private int maxLevel = 0;
    private Material material = Material.BARRIER;
    private String title = "NONE";
    private int costPerLevel = 0;

    public RenownUpgrade(String refID) {
        super(refID);
    }

    public ItemStack getShopItem(String uuid) throws ScriptException {
        ItemStack itemStack = ItemMaker(Material.BEDROCK, ChatColor.RED + "Unknown upgrade",
                colorCode("&7Prestige: &e"+integerToRoman(getPrestigeRequirement())),1, true);

        if(this.hasValue(uuid) && this.getInt(uuid) >= getMaxLevel()){
            itemStack = ItemMaker(getMaterial(), ChatColor.GREEN + getTitle(),
                    colorCode(getLore(getInt(uuid)) + "\n\n" +
                            "&aMaxed out!"),1, true);
        }else if(this.hasValue(uuid) && this.getInt(uuid) <= getMaxLevel() &&
        this.getInt(uuid) != 0){
            itemStack =  ItemMaker(getMaterial(), ChatColor.GREEN + getTitle(),
                    colorCode("&7Tier: &a" + integerToRoman(getInt(uuid)) + "\n\n" +
                            getLore(getInt(uuid)) + "\n\n" +
                            "&7Upgrade Cost: &e"+((getInt(uuid)+1)*costPerLevel)+" &eRenown\n" +
                            "&7You have: &e"+ClassInstances.renownData.getRenown(uuid)+" Renown\n\n" +
                            "&eClick to upgrade!"),1, true);
        }else if (ClassInstances.prestigeData.getPrestige(uuid) >= getPrestigeRequirement() &&
        ClassInstances.renownData.getRenown(uuid) >= getCostPerLevel()){
            itemStack = ItemMaker(getMaterial(), ChatColor.YELLOW + getTitle(),
                    colorCode(getLore(getInt(uuid)) + "\n\n" +
                            "&7Cost: &e"+(getCostPerLevel())+" Renown\n" +
                            "&7You have: &e"+ClassInstances.renownData.getRenown(uuid)+" Renown\n\n" +
                            "&eClick to purchase!"),1, true);
        }else if (ClassInstances.prestigeData.getPrestige(uuid) >= getPrestigeRequirement()){
            itemStack = ItemMaker(getMaterial(), ChatColor.RED + getTitle(),
                    colorCode(getLore(getInt(uuid)) + "\n\n" +
                            "&7Cost: &e"+(getCostPerLevel())+" Renown\n" +
                            "&7You have: &e"+ClassInstances.renownData.getRenown(uuid)+" Renown\n\n" +
                            "&cYou cannot afford this!"),1, true);
        }

        NBTItem nbtItem = new NBTItem(itemStack);

        nbtItem.setInteger(getRefID(), 1);

        itemStack = nbtItem.getItem();

        return itemStack;
    }

    public String getLore(int level) throws ScriptException {
        // Equation Syntax

        // Example: [@level@ + 3] will be level + 3 in lore

        setLore(lore.replaceAll("@level@", String.valueOf(level)));

        ScriptEngineManager mgr = new ScriptEngineManager();
        ScriptEngine engine = mgr.getEngineByName("JavaScript");

        Matcher m = Pattern.compile("\\[(.*?)]").matcher(lore);

        while (m.find()) {
            setLore(lore.replace(m.group(),
                    engine.eval(m.group().
                            replaceAll("[\\[\\]]*", "")).
                            toString()));
        }

        setLore(lore.replaceAll("[\\[\\]]*", ""));

        /*
        List<String> strings = new ArrayList<String>();
        int index = 0;
        while (index < lore.length()) {
            if(lore.charAt(index) != ' '){
                continue;
            }
            strings.add(lore.substring(index, Math.min(index + 28,lore.length())));
            index += 28;
        }




        String result = strings.stream()
                .map(n -> String.valueOf(n))
                .collect(Collectors.joining("\n", "", ""));

         */

        StringBuilder result = new StringBuilder();

        StringBuilder current = new StringBuilder();

        for(int i = 0; i < lore.length(); i++){
            if(lore.charAt(i) != ' ' && !(i-28>=28)){
                current.append(String.valueOf(lore.charAt(i)));
                continue;
            }

            current.append(String.valueOf(lore.charAt(i)));

            Bukkit.broadcastMessage(String.valueOf(lore.charAt(i)));
            result.append(current);
            current = new StringBuilder();
        }

        return colorCode(String.valueOf(result));
    }

    public void setLore(String lore){
        this.lore = colorCode(lore);
    }

    public int getPrestigeRequirement() {
        return prestigeRequirement;
    }

    public void setPrestigeRequirement(int prestigeRequirement) {
        this.prestigeRequirement = prestigeRequirement;
    }

    public int getMaxLevel() {
        return maxLevel;
    }

    public void setMaxLevel(int maxLevel) {
        this.maxLevel = maxLevel;
    }

    public Material getMaterial() {
        return material;
    }

    public void setMaterial(Material material) {
        this.material = material;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getCostPerLevel() {
        return costPerLevel;
    }

    public void setCostPerLevel(int costPerLevel) {
        this.costPerLevel = costPerLevel;
    }

}
