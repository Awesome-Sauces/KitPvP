package me.alpha.kitpvp.PitRemake.MysticWell;

import org.bukkit.ChatColor;

import java.util.ArrayList;
import java.util.List;

public class loreChecker {

    private static String colorCode(String text){
        return ChatColor.translateAlternateColorCodes('&', text);
    }
    
    public static List<String> CheckEnchantOnPant(List<String> lore) {

        List<String> list=new ArrayList<String>();

        if (lore.contains(colorCode("&9Boo-boo III"))){
            list.add("boobooIII");
        }else if (lore.contains(colorCode("&9Boo-boo II"))){
            list.add("boobooII");
        }else if (lore.contains(colorCode("&9Boo-boo")))
            list.add("boobooI");

        if (lore.contains(colorCode("&9Cricket III"))){
            list.add("cricketIII");
        }else if (lore.contains(colorCode("&9Cricket II"))){
            list.add("cricketII");
        }else if (lore.contains(colorCode("&9Cricket")))
            list.add("cricketI");

        if (lore.contains(colorCode("&9Prick III"))){
            list.add("prickIII");
        }else if (lore.contains(colorCode("&9Prick II"))){
            list.add("prickII");
        }else if (lore.contains(colorCode("&9Prick")))
            list.add("prickI");

        if (lore.contains(colorCode("&9Self-checkout III"))){
            list.add("self-checkoutIII");
        }else if (lore.contains(colorCode("&9Self-checkout II"))){
            list.add("self-checkoutII");
        }else if (lore.contains(colorCode("&9Self-checkout")))
            list.add("self-checkoutI");

        if (lore.contains(colorCode("&9Pebble III"))){
            list.add("pebbleIII");
        }else if (lore.contains(colorCode("&9Pebble II"))){
            list.add("pebbleII");
        }else if (lore.contains(colorCode("&9Pebble")))
            list.add("pebbleI");

        if (lore.contains(colorCode("&9Gotta go fast III"))){
            list.add("gottagofastIII");
        }else if (lore.contains(colorCode("&9Gotta go fast II"))){
            list.add("gottagofastII");
        }else if (lore.contains(colorCode("&9Gotta go fast")))
            list.add("gottagofastI");

        if (lore.contains(colorCode("&9Hidden Jewel")))
            list.add("jewel");

        if (lore.contains(colorCode("&9Peroxide III"))){
            list.add("peroxideIII");
        }else if (lore.contains(colorCode("&9Peroxide II"))){
            list.add("peroxideII");
        }else if (lore.contains(colorCode("&9Peroxide")))
            list.add("peroxideI");


        if (lore.contains(colorCode("&dRARE! &9Pit Blob III"))){
            list.add("blobIII");
        }else if (lore.contains(colorCode("&dRARE! &9Pit Blob II"))){
            list.add("blobII");
        }else if (lore.contains(colorCode("&dRARE! &9Pit Blob")))
            list.add("blobI");


        if (lore.contains(colorCode("&dRARE! &9Combo: Venom")))
            list.add("venom");


        if (lore.contains(colorCode("&9Golden Heart III"))){
            list.add("goldheartIII");
        }else if (lore.contains(colorCode("&9Golden Heart II"))){
            list.add("goldheartII");
        }else if (lore.contains(colorCode("&9Golden Heart")))
            list.add("goldheartI");


        if (lore.contains(colorCode("&9Moctezuma III"))){
            list.add("moctIII");
        }else if (lore.contains(colorCode("&9Moctezuma II"))){
            list.add("moctII");
        }else if (lore.contains(colorCode("&9Moctezuma")))
            list.add("moctI");


        if (lore.contains(colorCode("&dRARE! &9Escape Pod III"))){
            list.add("escapeIII");
        }else if (lore.contains(colorCode("&dRARE! &9Escape Pod II"))) {
            list.add("escapeII");
        }else if (lore.contains(colorCode("&dRARE! &9Escape Pod")))
            list.add("escapeI");

        if (lore.contains(colorCode("&dRARE! &9Regularity III"))){
            list.add("regIII");
        }else if (lore.contains(colorCode("&dRARE! &9Regularity II"))) {
            list.add("regII");
        }else if (lore.contains(colorCode("&dRARE! &9Regularity")))
            list.add("regI");


        if (lore.contains(colorCode("&9Gold Bump III"))){
            list.add("goldBumpIII");
        }else if (lore.contains(colorCode("&9Gold Bump II"))){
            list.add("goldBumpII");
        }else if (lore.contains(colorCode("&9Gold Bump")))
            list.add("goldBumpI");


        if (lore.contains(colorCode("&dRARE! &9Solitude III"))){
            list.add("soliIII");
        }else if (lore.contains(colorCode("&dRARE! &9Solitude II"))){
            list.add("soliII");
        }else if (lore.contains(colorCode("&dRARE! &9Solitude")))
            list.add("soliI");


        if (lore.contains(colorCode("&9Protection III"))){
            list.add("protIII");
        }else if (lore.contains(colorCode("&9Protection II"))){
            list.add("protII");
        }else if (lore.contains(colorCode("&9Protection")))
            list.add("protI");


        if (lore.contains(colorCode("&9David and Goliath III"))){
            list.add("davidIII");
        }else if (lore.contains(colorCode("&9David and Goliath II"))){
            list.add("davidII");
        }else if (lore.contains(colorCode("&9David and Goliath II")))
            list.add("davidI");

        if (lore.contains(colorCode("&9Billy III"))){
            list.add("billyIII");
        }else if (lore.contains(colorCode("&9Billy II"))){
            list.add("billyII");
        }else if (lore.contains(colorCode("&9Billy")))
            list.add("billyI");

        if (lore.contains(colorCode("&9\"Not\" Gladiator III"))){
            list.add("gladIII");
        }else if (lore.contains(colorCode("&9\"Not\" Gladiator II"))){
            list.add("gladII");
        }else if (lore.contains(colorCode("&9\"Not\" Gladiator")))
            list.add("gladI");


        if (lore.contains(colorCode("&9Critically Funky III"))){
            list.add("critIII");
        }else if (lore.contains(colorCode("&9Critically Funky II"))){
            list.add("critII");
        }if (lore.contains(colorCode("&9Critically Funky")))
            list.add("critI");


        if (lore.contains(colorCode("&9Misery")))
            list.add("misery");

        if (lore.contains(colorCode("&dRARE! &9Combo: Venom")))
            list.add("venom");

        if (lore.contains(colorCode("&9Somber")))
            list.add("somber");

        if (lore.contains(colorCode("&9Spite")))
            list.add("spite");

        if (lore.contains(colorCode("&9Mind Assault")))
            list.add("mind");

        if (lore.contains(colorCode("&9Needless Suffering")))
            list.add("suffering");

        if (lore.contains(colorCode("&9Fractional Reserve III"))){
            list.add("fracIII");
        }else if (lore.contains(colorCode("&9Fractional Reserve II"))){
            list.add("fracII");
        }else if (lore.contains(colorCode("&9Fractional Reserve")))
            list.add("fracI");


        if (lore.contains(colorCode("&9Mirror III"))){
            list.add("mirrorIII");
        }else if (lore.contains(colorCode("&9Mirror II"))){
            list.add("mirrorII");
        }else if (lore.contains(colorCode("&9Mirror")))
            list.add("mirrorIII");


        if (lore.contains(colorCode("&dRARE! &9Retro-Gravity Microcosm III"))){
            list.add("rgmIII");
        }else if (lore.contains(colorCode("&dRARE! &9Retro-Gravity Microcosm II"))){
            list.add("rgmII");
        }else if (lore.contains(colorCode("&dRARE! &9Retro-Gravity Microcosm"))){
            list.add("rgmI");
        }


        if (lore.contains(colorCode("&9Sweaty III"))){
            list.add("sweatyIII");
        }else if (lore.contains(colorCode("&9Sweaty II"))){
            list.add("sweatyII");
        }else if (lore.contains(colorCode("&9Sweaty")))
            list.add("sweatyI");


        if (lore.contains(colorCode("&9XP Boost III"))){
            list.add("xpIII");
        }else if (lore.contains(colorCode("&9XP Boost II"))){
            list.add("xpII");
        }else if (lore.contains(colorCode("&9XP Boost")))
            list.add("xpI");

        if (lore.contains(colorCode("&9XP Bump III"))){
            list.add("xpbIII");
        }else if (lore.contains(colorCode("&9XP Bump II"))){
            list.add("xpbII");
        }else if (lore.contains(colorCode("&9XP Bump")))
            list.add("xpbI");

        if (lore.contains(colorCode("&9Gold Boost III"))){
            list.add("gbIII");
        }else if (lore.contains(colorCode("&9Gold Boost II"))){
            list.add("gbII");
        }else if (lore.contains(colorCode("&9Gold Boost")))
            list.add("gbI");


        if (lore.contains(colorCode("&9Diamond Allergy III"))){
            list.add("diamondallergyIII");
        }else if (lore.contains(colorCode("&9Diamond Allergy II"))){
            list.add("diamondallergyII");
        }else if (lore.contains(colorCode("&9Diamond Allergy")))
            list.add("diamondallergyI");

        if (lore.contains(colorCode("&9Pants Radar III"))){
            list.add("pantsradarIII");
        }else if (lore.contains(colorCode("&9Pants Radar II"))){
            list.add("pantsradarI");
        }else if (lore.contains(colorCode("&9Pants Radar")))
            list.add("pantsradarI");

        if (lore.contains(colorCode("&9Spite")))
            list.add("spite");

        if (lore.contains(colorCode("&9McSwimmer III"))){
            list.add("mcswimmerIII");
        }else if (lore.contains(colorCode("&9McSwimmer II"))){
            list.add("mcswimmerII");
        }else if (lore.contains(colorCode("&9McSwimmer"))){
            list.add("mcswimmerI");
        }


        return list;
    }

    public static List<String> CheckEnchantOnSword(List<String> lore) {

        List<String> list=new ArrayList<>();

        if (lore.contains(colorCode("&9Fancy Raider III"))){
            list.add("fancyraiderIII");
        }else if (lore.contains(colorCode("&9Fancy Raider II"))){
            list.add("fancyraiderII");
        }else if (lore.contains(colorCode("&9Fancy Raider"))){
            list.add("fancyraiderI");
        }

        if (lore.contains(colorCode("&9Speedy Kill III"))){
            list.add("speedykillIII");
        }else if (lore.contains(colorCode("&9Speedy Kill II"))){
            list.add("speedykillII");
        }else if (lore.contains(colorCode("&9Speedy Kill"))){
            list.add("speedykillI");
        }

        if (lore.contains(colorCode("&9Pitpocket III"))){
            list.add("pitpocketIII");
        }else if (lore.contains(colorCode("&9Pitpocket II"))){
            list.add("pitpocketII");
        }else if (lore.contains(colorCode("&9Pitpocket"))){
            list.add("pitpocketI");
        }

        if (lore.contains(colorCode("&9Gold and Boosted III"))){
            list.add("goldandboostedIII");
        }else if (lore.contains(colorCode("&9Gold and Boosted II"))){
            list.add("goldandboostedII");
        }else if (lore.contains(colorCode("&9Gold and Boosted"))){
            list.add("goldandboostedI");
        }

        if (lore.contains(colorCode("&9Punisher III"))){
            list.add("punisherIII");
        }else if (lore.contains(colorCode("&9Punisher II"))){
            list.add("punisherII");
        }else if (lore.contains(colorCode("&9Punisher"))){
            list.add("punisherI");
        }

        if (lore.contains(colorCode("&9Berserker III"))){
            list.add("berserkerIII");
        }else if (lore.contains(colorCode("&9Berserker II"))){
            list.add("berserkerII");
        }else if (lore.contains(colorCode("&9Berserker"))){
            list.add("berserkerI");
        }

        if (lore.contains(colorCode("&9Grasshopper III"))){
            list.add("grasshopperIII");
        }else if (lore.contains(colorCode("&9Grasshopper II"))){
            list.add("grasshopperII");
        }else if (lore.contains(colorCode("&9Grasshopper"))){
            list.add("grasshopperI");
        }

        if (lore.contains(colorCode("&9Combo: Damage III"))){
            list.add("combodamageIII");
        }else if (lore.contains(colorCode("&9Combo: Damage II"))){
            list.add("combodamageII");
        }else if (lore.contains(colorCode("&9Combo: Damage"))) {
            list.add("combodamageI");
        }

        if (lore.contains(colorCode("&dRARE! &9Billionaire III"))){
            list.add("billIII");
        }else if (lore.contains(colorCode("&dRARE! &9Billionaire II"))){
            list.add("billII");
        }else if (lore.contains(colorCode("&dRARE! &9Billionaire")))
            list.add("billI");

        if (lore.contains(colorCode("&dRARE! &9Combo: Perun's Wrath III"))){
            list.add("perunIII");
        }else if (lore.contains(colorCode("&dRARE! &9Combo: Perun's Wrath II"))){
            list.add("perunII");
        }else if (lore.contains(colorCode("&dRARE! &9Combo: Perun's Wrath")))
            list.add("perunI");

        if (lore.contains(colorCode("&9Lifesteal III"))){
            list.add("lsIII");
        }else if (lore.contains(colorCode("&9Lifesteal II"))){
            list.add("lsII");
        }else if (lore.contains(colorCode("&9Lifesteal")))
            list.add("lsI");

        if (lore.contains(colorCode("&9Pain Focus III"))){
            list.add("pfIII");
        }else if (lore.contains(colorCode("&9Pain Focus II"))){
            list.add("pfII");
        }else if (lore.contains(colorCode("&9Pain Focus")))
            list.add("pfI");

        if (lore.contains(colorCode("&9Diamond Stomp III"))){
            list.add("diamondIII");
        }else if (lore.contains(colorCode("&9Diamond Stomp II"))){
            list.add("diamondII");
        }else if (lore.contains(colorCode("&9Diamond Stomp")))
            list.add("diamondI");

        if (lore.contains(colorCode("&dRARE! &9Gamble! III"))){
            list.add("gambIII");
        }else if (lore.contains(colorCode("&dRARE! &9Gamble! II"))){
            list.add("gambII");
        }else if (lore.contains(colorCode("&dRARE! &9Gamble!")))
            list.add("gambI");


        if (lore.contains(colorCode("&9King Buster III"))){
            list.add("kingIII");
        }else if (lore.contains(colorCode("&9King Buster II"))){
            list.add("kingII");
        }else if (lore.contains(colorCode("&9King Buster")))
            list.add("kingI");

        if (lore.contains(colorCode("&9Sharp III"))){
            list.add("sharpIII");
        }else if (lore.contains(colorCode("&9Sharp II"))){
            list.add("sharpII");
        }else if (lore.contains(colorCode("&9Sharp")))
            list.add("sharpI");

        if (lore.contains(colorCode("&9Shark III"))){
            list.add("sharkIII");
        }else if (lore.contains(colorCode("&9Shark II"))){
            list.add("sharkII");
        }else if (lore.contains(colorCode("&9Shark")))
            list.add("sharkI");

        if (lore.contains(colorCode("&dRARE! &9Executioner III"))){
            list.add("exeIII");
        }else if (lore.contains(colorCode("&dRARE! &9Executioner II"))){
            list.add("exeII");
        }else if (lore.contains(colorCode("&dRARE! &9Executioner")))
            list.add("exeI");


        if (lore.contains(colorCode("&9Moctezuma III"))){
            list.add("moctIII");
        }else if (lore.contains(colorCode("&9Moctezuma II"))){
            list.add("moctII");
        }else if (lore.contains(colorCode("&9Moctezuma")))
            list.add("moctI");


        if (lore.contains(colorCode("&9Gold Bump III"))){
            list.add("goldBumpIII");
        }else if (lore.contains(colorCode("&9Gold Bump II"))){
            list.add("goldBumpII");
        }else if (lore.contains(colorCode("&9Gold Bump")))
            list.add("goldBumpI");


        if (lore.contains(colorCode("&9Sweaty III"))){
            list.add("sweatyIII");
        }else if (lore.contains(colorCode("&9Sweaty II"))){
            list.add("sweatyII");
        }else if (lore.contains(colorCode("&9Sweaty")))
            list.add("sweatyI");


        if (lore.contains(colorCode("&9XP Boost III"))){
            list.add("xpIII");
        }else if (lore.contains(colorCode("&9XP Boost II"))){
            list.add("xpII");
        }else if (lore.contains(colorCode("&9XP Boost")))
            list.add("xpI");

        if (lore.contains(colorCode("&9XP Bump III"))){
            list.add("xpbIII");
        }else if (lore.contains(colorCode("&9XP Bump II"))){
            list.add("xpbII");
        }else if (lore.contains(colorCode("&9XP Bump")))
            list.add("xpbI");

        if (lore.contains(colorCode("&9Gold Boost III"))){
            list.add("gbIII");
        }else if (lore.contains(colorCode("&9Gold Boost II"))){
            list.add("gbII");
        }else if (lore.contains(colorCode("&9Gold Boost")))
            list.add("gbI");

        if (lore.contains(colorCode("&9Pants Radar III"))){
            list.add("pantsradarIII");
        }else if (lore.contains(colorCode("&9Pants Radar II"))){
            list.add("pantsradarI");
        }else if (lore.contains(colorCode("&9Pants Radar")))
            list.add("pantsradarI");


        return list;
    }

    public static List<String> CheckEnchantOnBow(List<String> lore) {

        List<String> list=new ArrayList<String>();

        if (lore.contains(colorCode("&dRARE! &9Mega Longbow III"))){
            list.add("mlbIII");
        }else if (lore.contains(colorCode("&dRARE! &9Mega Longbow II"))){
            list.add("mlbII");
        }else if (lore.contains(colorCode("&dRARE! &9Mega Longbow")))
            list.add("mlbI");

        if (lore.contains(colorCode("&dRARE! &9Telebow III"))){
            list.add("teleIII");
        }else if (lore.contains(colorCode("&dRARE! &9Telebow II"))){
            list.add("teleII");
        }else if (lore.contains(colorCode("&dRARE! &9Telebow")))
            list.add("teleI");

        if (lore.contains(colorCode("&dRARE! &9Volley III"))){
            list.add("vIII");
        }else if (lore.contains(colorCode("&dRARE! &9Volley II"))){
            list.add("vII");
        }else if (lore.contains(colorCode("&dRARE! &9Volley")))
            list.add("vI");

        if (lore.contains(colorCode("&dRARE! &9Explosive III"))){
            list.add("exIII");
        }else if (lore.contains(colorCode("&dRARE! &9Explosive II"))){
            list.add("exII");
        }else if (lore.contains(colorCode("&dRARE! &9Explosive")))
            list.add("exI");

        if (lore.contains(colorCode("&dRARE! &9Pullbow III"))){
            list.add("pullIII");
        }else if (lore.contains(colorCode("&dRARE! &9Pullbow II"))){
            list.add("pullII");
        }else if (lore.contains(colorCode("&dRARE! &9Pullbow")))
            list.add("pullI");


        if (lore.contains(colorCode("&9Faster than their shadow III"))){
            list.add("fttsIII");
        }else if (lore.contains(colorCode("&9Faster than their shadow II"))){
            list.add("fttsII");
        }else if (lore.contains(colorCode("&9Faster than their shadow")))
            list.add("fttsI");

        if (lore.contains(colorCode("&9Sprint Drain III"))){
            list.add("sprintIII");
        }else if (lore.contains(colorCode("&9Sprint Drain II"))){
            list.add("sprintII");
        }else if (lore.contains(colorCode("&9Sprint Drain")))
            list.add("sprintI");

        if (lore.contains(colorCode("&9Arrow Armory III"))){
            list.add("arrowarmoryIII");
        }else if (lore.contains(colorCode("&9Arrow Armory II"))){
            list.add("arrowarmoryII");
        }else if (lore.contains(colorCode("&9Arrow Armory")))
            list.add("arrowarmoryI");

        if (lore.contains(colorCode("&9Wasp III"))){
            list.add("waspIII");
        }else if (lore.contains(colorCode("&9Wasp II"))){
            list.add("waspII");
        }else if (lore.contains(colorCode("&9Wasp")))
            list.add("waspI");

        if (lore.contains(colorCode("&9Parasite III"))){
            list.add("parasiteIII");
        }else if (lore.contains(colorCode("&9Parasite II"))){
            list.add("parasiteII");
        }else if (lore.contains(colorCode("&9Parasite")))
            list.add("parasiteI");

        if (lore.contains(colorCode("&9Fletching III"))){
            list.add("fletchingIII");
        }else if (lore.contains(colorCode("&9Fletching II"))){
            list.add("fletchingII");
        }else if (lore.contains(colorCode("&9Fletching")))
            list.add("fletchingI");

        if (lore.contains(colorCode("&9Jumpspammer III"))){
            list.add("jumpspammerIII");
        }else if (lore.contains(colorCode("&9Jumpspammer II"))){
            list.add("jumpspammerII");
        }else if (lore.contains(colorCode("&9Jumpspammer")))
            list.add("jumpspammerI");

        if (lore.contains(colorCode("&9Moctezuma III"))){
            list.add("moctIII");
        }else if (lore.contains(colorCode("&9Moctezuma II"))){
            list.add("moctII");
        }else if (lore.contains(colorCode("&9Moctezuma")))
            list.add("moctI");


        if (lore.contains(colorCode("&9Gold Bump III"))){
            list.add("goldBumpIII");
        }else if (lore.contains(colorCode("&9Gold Bump II"))){
            list.add("goldBumpII");
        }else if (lore.contains(colorCode("&9Gold Bump")))
            list.add("goldBumpI");

        if (lore.contains(colorCode("&9Pants Radar III"))){
            list.add("pantsradarIII");
        }else if (lore.contains(colorCode("&9Pants Radar II"))){
            list.add("pantsradarI");
        }else if (lore.contains(colorCode("&9Pants Radar")))
            list.add("pantsradarI");

        if (lore.contains(colorCode("&9Sweaty III"))){
            list.add("sweatyIII");
        }else if (lore.contains(colorCode("&9Sweaty II"))){
            list.add("sweatyII");
        }else if (lore.contains(colorCode("&9Sweaty")))
            list.add("sweatyI");


        if (lore.contains(colorCode("&9XP Boost III"))){
            list.add("xpIII");
        }else if (lore.contains(colorCode("&9XP Boost II"))){
            list.add("xpII");
        }else if (lore.contains(colorCode("&9XP Boost")))
            list.add("xpI");

        if (lore.contains(colorCode("&9XP Bump III"))){
            list.add("xpbIII");
        }else if (lore.contains(colorCode("&9XP Bump II"))){
            list.add("xpbII");
        }else if (lore.contains(colorCode("&9XP Bump")))
            list.add("xpbI");

        if (lore.contains(colorCode("&9Gold Boost III"))){
            list.add("gbIII");
        }else if (lore.contains(colorCode("&9Gold Boost II"))){
            list.add("gbII");
        }else if (lore.contains(colorCode("&9Gold Boost")))
            list.add("gbI");

        return list;
    }

}