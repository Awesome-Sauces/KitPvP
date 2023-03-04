package me.alpha.kitpvp.PitRemake.MysticWell.New;

import de.tr7zw.nbtapi.NBTItem;
import me.alpha.kitpvp.CustomEvents.ArmorEvents.ArmorEquipEvent;
import me.alpha.kitpvp.CustomEvents.ReduxBowEvent;
import me.alpha.kitpvp.CustomEvents.ReduxDamageEvent;
import me.alpha.kitpvp.CustomEvents.ReduxDeathEvent;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static me.alpha.kitpvp.utils.ColorUtil.colorCode;
import static me.alpha.kitpvp.utils.IntegerHelper.integerToRoman;

public abstract class MysticEnchant {
    private EnchantGeneral enchantGeneral = EnchantGeneral.NONE;
    private MysticType mysticType = MysticType.NONE;
    private String lore = "";
    private String title = "";
    private String refID = "";
    private List<RegisterEvent> registerEvents = new ArrayList<>();

    public abstract void DamageEvent(ReduxDamageEvent event);
    public abstract void DeathEvent(ReduxDeathEvent event);
    public abstract void BowEvent(ReduxBowEvent event);
    public abstract void ArmorEvent(ArmorEquipEvent event);

    public MysticEnchant (){
        /*
        Example of required inputs

        this.setRefID("sweaty");
        this.setTitle("Sweaty");
        this.setLore("&7Increase the streak XP bonus by &b[@lvl@*20]%&7. +[@lvl@*50] max &bXP &7on kill.");
        this.setMysticType(MysticType.ALL);
        this.setEnchantGeneral(EnchantGeneral.UNCOMMON);
        this.addEventListener(RegisterEvent.REDUX_DEATH_EVENT);

         */
    }

    public boolean isEnchant(ItemStack itemStack){
        if(itemStack==null ||
        itemStack.getType()==null ||
        !mysticType.getMaterials().contains(itemStack.getType())) return false;

        NBTItem nbtItem = new NBTItem(itemStack);

        return nbtItem.hasKey(getRefID());
    }

    public int getEnchantLevel(ItemStack itemStack){
        if(itemStack==null ||
                itemStack.getType()==null ||
                !mysticType.getMaterials().contains(itemStack.getType())) return 0;

        NBTItem nbtItem = new NBTItem(itemStack);

        return nbtItem.getInteger(getRefID());
    }

    public String getLore(int level) throws ScriptException {
        // Equation Syntax

        // Example: [@level@ + 3] will be level + 3 in lore

        setLore(lore.replaceAll("@lvl@", String.valueOf(level)));

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

        String result = "";
        String construct = "";
        int lineIndex = 0;


        for(int i = 0; i < lore.length(); i++){
            char Char = lore.charAt(i);
            construct+=Char;
            lineIndex++;

            if(Char == ' ' && lineIndex>=28){
                result+=construct+"\n" + "&7";
                construct="";
                lineIndex=0;
            }
        }

        if(construct.length()>0) result+=construct;

        return colorCode(getTitle(level) + "\n" + result + "\n&7");
    }

    public void setLore(String lore) {
        this.lore = lore;
    }

    public EnchantGeneral getEnchantGeneral() {
        return enchantGeneral;
    }

    public void setEnchantGeneral(EnchantGeneral enchantGeneral) {
        this.enchantGeneral = enchantGeneral;
    }

    public String getTitle(int level) {
        if(level==1) return colorCode(getEnchantGeneral().getColorCode() + title);
        return colorCode(getEnchantGeneral().getColorCode() + title + " " + integerToRoman(level));
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getRefID() {
        return refID;
    }

    public void setRefID(String refID) {
        this.refID = refID;
    }

    public MysticType getMysticType() {
        return mysticType;
    }

    public void setMysticType(MysticType mysticType) {
        this.mysticType = mysticType;
    }


    public List<RegisterEvent> getRegisterEvents() {
        return registerEvents;
    }

    public void addEventListener(RegisterEvent registerEvent) {
        this.registerEvents.add(registerEvent);
    }
}
