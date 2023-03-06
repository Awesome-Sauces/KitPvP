package me.alpha.kitpvp.PitRemake.MysticWell.New;

import de.tr7zw.nbtapi.NBTItem;
import me.alpha.kitpvp.CustomEvents.ArmorEvents.ArmorEquipEvent;
import me.alpha.kitpvp.CustomEvents.ReduxBowEvent;
import me.alpha.kitpvp.CustomEvents.ReduxDamageEvent;
import me.alpha.kitpvp.CustomEvents.ReduxDeathEvent;
import me.alpha.kitpvp.utils.IntegerHelper;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.inventory.ItemStack;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.math.BigDecimal;
import java.math.RoundingMode;
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
    public abstract void ShootEvent(EntityShootBowEvent event);
    public abstract void ArrowHitEvent(ProjectileHitEvent event);

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

        String tempLore = lore;

        tempLore = tempLore.replaceAll("@lvl@", String.valueOf(level));

        ScriptEngineManager mgr = new ScriptEngineManager();
        ScriptEngine engine = mgr.getEngineByName("JavaScript");

        Matcher m = Pattern.compile("\\[(.*?)]").matcher(tempLore);

        while (m.find()) {

            String equation = engine.eval(m.group().
                    replaceAll("[\\[\\]]*", "")).toString();

            BigDecimal bigDecimal = new BigDecimal(equation).setScale(2, RoundingMode.HALF_UP).stripTrailingZeros();

            tempLore = tempLore.replace(m.group(),
                    bigDecimal.toPlainString());
        }

        tempLore = tempLore.replaceAll("[\\[\\]]*", "");

        tempLore = romanNumeral(tempLore);

        String result = "";
        String construct = "";
        int lineIndex = 0;


        for(int i = 0; i < tempLore.length(); i++){
            char Char = tempLore.charAt(i);
            construct+=Char;
            lineIndex++;

            if(Char == '\n'){
                result+=construct;
                construct="";
                lineIndex=0;
            }else if(Char == ' ' && lineIndex>=32){
                result+=construct+"\n" + "&7";
                construct="";
                lineIndex=0;
            }
        }

        if(construct.length()>0) result+=construct;

        return colorCode(getTitle(level) + "\n" + result + "\n&7");
    }

    public static String romanNumeral(String str) {

        String tempString = "";

        List<Character> characters = new ArrayList<>();

        characters.add('r');
        characters.add('o');
        characters.add('m');
        characters.add('a');
        characters.add('n');
        characters.add('(');
        characters.add(')');

        characters.add('0');
        characters.add('1');
        characters.add('2');
        characters.add('3');
        characters.add('4');
        characters.add('5');
        characters.add('6');
        characters.add('7');
        characters.add('8');
        characters.add('9');

        for(int i = 0; i < str.length(); i++){
            char Char = str.charAt(i);

            if(Char == ')'){
                if(tempString.contains("roman")){
                    tempString+=Char;

                    Matcher m = Pattern.compile("\\((.*?)\\)").matcher(tempString);

                    while (m.find()) {

                        String found = "roman\\("+m.group(1)+"\\)";

                        if(m.group(1).length()>0) str = str.replaceAll(found, IntegerHelper.integerToRoman(Integer.parseInt(m.group(1))).toString());
                    }

                    tempString="";

                    continue;
                }
            }

            if(characters.contains(Char)){
                tempString+=Char;
            }

        }

        return str;
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
