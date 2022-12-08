package me.alpha.kitpvp.PitRemake.MysticWell.enchanters;

import com.alpha.redux.apis.Sounds;
import com.alpha.redux.apis.chatManager.rank;
import com.alpha.redux.events.boards;
import com.alpha.redux.redux;
import de.tr7zw.nbtapi.NBTCompound;
import de.tr7zw.nbtapi.NBTItem;
import me.alpha.kitpvp.ChatManager.RankColor;
import me.alpha.kitpvp.Data.GoldData;
import me.alpha.kitpvp.PitRemake.MysticWell.loreChecker;
import me.alpha.kitpvp.PitRemake.Scoreboard.ScoreboardCore;
import me.alpha.kitpvp.utils.ColorUtil;
import me.alpha.kitpvp.utils.IntegerHelper;
import me.alpha.kitpvp.utils.Sounds;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static me.alpha.kitpvp.utils.ColorUtil.colorCode;

public class FreshPants {

    private static boolean removeGold(Player player, String uuid, int amount){
        if(GoldData.getEconomy(uuid) > amount-1){
            GoldData.removeEconomy(uuid, amount);
            ScoreboardCore.CreateScore(player);
            return true;
        }else{
            player.sendMessage(colorCode("&l&cERROR! &7You need &6" + (amount - GoldData.getEconomy(uuid)) + "g &7to afford this!"));
            return false;
        }
    }

    public static int getTokens(List<String> lore){
        int tokens = 0;
        if(lore!=null) for(String string : loreChecker.CheckEnchantOnPant(lore)){
            tokens+=string.length()-string.replaceAll("I", "").length();
        }

        return tokens;
    }

    public static void clickFresh(InventoryClickEvent event){
        event.setCancelled(true);

        String uuid = String.valueOf(event.getWhoClicked().getUniqueId());
        Player player = (Player) event.getWhoClicked();
        ItemStack items = event.getClickedInventory().getItem(20);

        int tokens = getTokens(items.getItemMeta().getLore());

        if (items.getItemMeta().getItemFlags().contains(ItemFlag.HIDE_ENCHANTS) && tokens>0){
            player.sendMessage(ChatColor.RED + "This pant is already max tier!");
            return;
        } else if (items.getItemMeta().getItemFlags().contains(ItemFlag.HIDE_ATTRIBUTES) &&
                !items.getItemMeta().getItemFlags().contains(ItemFlag.HIDE_ENCHANTS) && removeGold(player, uuid, 8000)) {
            Sounds.BUTTON.play(player);
            Sounds.PIN_DOWN.play(player);

            LeatherArmorMeta meta = ((LeatherArmorMeta)items.getItemMeta());

            meta.hasItemFlag(ItemFlag.HIDE_ENCHANTS);

            event.getClickedInventory().setItem(20, createPant(player,3, event.getClickedInventory().getItem(20), null));
        }else if (loreChecker.CheckEnchantOnPant(items.getItemMeta().getLore()).size()==1&&
        !items.getItemMeta().getItemFlags().contains(ItemFlag.HIDE_ATTRIBUTES)&& removeGold(player, uuid, 4000)) {
            Sounds.BUTTON.play(player);
            Sounds.PIN_DOWN.play(player);
            event.getClickedInventory().setItem(20, createPant(player,2, event.getClickedInventory().getItem(20), null));
        } else if (tokens == 0 && removeGold(player, uuid, 1000)) {
            Sounds.BUTTON.play(player);
            Sounds.PIN_DOWN.play(player);
            event.getClickedInventory().setItem(20, createPant(player,1, null, event.getClickedInventory().getItem(20)));
        }


    }

    public static ItemStack createPant(Player player, int tier, ItemStack pant, ItemStack last){
        if (pant == null && last != null){
            ItemStack item = new ItemStack(Material.LEATHER_LEGGINGS, 1);
            LeatherArmorMeta meta = (LeatherArmorMeta) item.getItemMeta();

            LeatherArmorMeta lmeta = (LeatherArmorMeta) last.getItemMeta();

            meta.setColor(lmeta.getColor());
            meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&cTier " + IntegerHelper.integerToRoman(tier) + " Pants"));
            meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
            meta.spigot().setUnbreakable(true);
            meta.setLore(enchantPant(player, pant, tier));
            item.setItemMeta(meta);
            //Shaped Recipe

            return item;
        }else{
            ItemMeta meta = pant.getItemMeta();
            meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&cTier " + IntegerHelper.integerToRoman(tier) + " Pants"));
            meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);

            if(tier==2){
                meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
            }else if(tier==3){
                meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
            }

            meta.spigot().setUnbreakable(true);
            //enchantpant(player, pant, tier)
            meta.setLore(enchantPant(player, pant, tier));
            pant.setItemMeta(meta);
            //Shaped Recipe

            return pant;
        }
    }

    public static List<String> translateList(List<String> lore){
        for(String text : lore){
            lore.set(lore.indexOf(text), colorCode(text));
        }

        return lore;
    }

    public static Boolean percentChance(double chance) {
        return Math.random() <= chance;
    }

    public static ItemStack enchantMystic(Player player, ItemStack itemStack, int tier){
        NBTItem nbtItem = new NBTItem(itemStack);

        NBTCompound nbtCompound = nbtItem.getCompound("enchants");

        List<String> lore = new ArrayList<>();

        List<String> enchants = new ArrayList<>();

        String ench;
        int tokens = 0;
        boolean looping = true;

        int loopnum = 0;

        String FINAL_ENCHANT = "";

        while (looping){
            loopnum++;
            ench = getEnchant(enchants);
            FINAL_ENCHANT=ench;

            for (String str : enchants){
                tokens += str.length() - str.replaceAll("I", "").length();
            }

            double d = Math.random();

            if (tokens <= 0){
                tokens = 1;
            }

            float tier1 = ((float) ((tokens) * 35) / 100);
            float tier2 = ((float) ((tokens) * 34) / 100);
            float tier3 = ((float) ((tokens) * 33) / 100);

            tokens = 0;


            if (percentChance(tier1)){
                if(nbtItem.getInteger(FINAL_ENCHANT)!=null &&
                nbtItem.getInteger(FINAL_ENCHANT)>=3){
                    continue;
                }

                if(nbtItem.getInteger(FINAL_ENCHANT)!=null){
                    nbtItem.setInteger(FINAL_ENCHANT, Math.max(3, nbtItem.getInteger(FINAL_ENCHANT)+1));
                }else{
                    nbtItem.setInteger(FINAL_ENCHANT, 1);
                }
            }else if (percentChance(tier2)){
                if(nbtItem.getInteger(FINAL_ENCHANT)!=null &&
                        nbtItem.getInteger(FINAL_ENCHANT)>=3){
                    continue;
                }

                if(nbtItem.getInteger(FINAL_ENCHANT)!=null){
                    nbtItem.setInteger(FINAL_ENCHANT, Math.max(3, nbtItem.getInteger(FINAL_ENCHANT)+2));
                }else{
                    nbtItem.setInteger(FINAL_ENCHANT, 2);
                }
            }else if (percentChance(tier3)){
                if(nbtItem.getInteger(FINAL_ENCHANT)!=null &&
                        nbtItem.getInteger(FINAL_ENCHANT)>=3){
                    continue;
                }

                if(nbtItem.getInteger(FINAL_ENCHANT)!=null){
                    nbtItem.setInteger(FINAL_ENCHANT, Math.max(3, nbtItem.getInteger(FINAL_ENCHANT)+3));
                }else{
                    nbtItem.setInteger(FINAL_ENCHANT, 3);
                }
            }/*else{

                lore.addAll(Arrays.asList(enchantTier(ench, 1).split("\n")));

                looping = false;
            }
            */
        }

        for (String key : nbtItem.getCompound("enchants").getKeys()){
            int level = nbtItem.getInteger(key);

            StringBuilder enchant = new StringBuilder(key);

            for(int i = 0; i < level; i++){
                enchant.append("I");
            }

            enchants.add(String.valueOf(enchant));

        }

        for (String bench : enchants){
            int ether = bench.length() - bench.replaceAll("I", "").length();

            lore.addAll(Arrays.asList(enchantTier(convertEnchant(bench.replaceAll("I", "")), ether).split("\n")));
            //lore.add(" ");
        }

        lore.add(ChatColor.RED + "As strong as iron");

        getRareEnchant(lore, FINAL_ENCHANT, player, tier);


        return nbtItem.getItem();
    }

    public static List<String> renewEnchant(List<String> lore, List<String> enchant){

        List<String> enchants = loreChecker.CheckEnchantOnPant(lore);



        lore = new ArrayList<>();
        lore.add(ChatColor.translateAlternateColorCodes('&', "&7Lives: &a5&7/5"));

        lore.add(" ");

        for (String bench : enchants){
            int ether = bench.length() - bench.replaceAll("I", "").length();

            lore.addAll(Arrays.asList(enchantTier(convertEnchant(bench.replaceAll("I", "")), ether).split("\n")));
            //lore.add(" ");
        }

        lore.addAll(enchant);

        return lore;
    }

    public static void getRareEnchant(List<String> lore, String enchant, Player player, int level){

        if(enchant.contains("pitblob")){
            Sounds.PRESTIGE.play(player);
            hoverText(ChatColor.translateAlternateColorCodes('&', "&d&lRARE! "
                    +  RankColor.getNameColor(player) +
                    player.getDisplayName() + ChatColor.GRAY + " created " + "&cTier " + IntegerHelper.integerToRoman(level) + " Pants&7, gg!"), compileListToString(lore, colorCode("&cTier " + IntegerHelper.integerToRoman(level) + " Pants"), true));
        }else if(enchant.contains("retro-gravitymicrocosm")){
            Sounds.PRESTIGE.play(player);
            hoverText(ChatColor.translateAlternateColorCodes('&', "&d&lRARE! "
                    +  RankColor.getNameColor(player) +
                    player.getDisplayName() + ChatColor.GRAY + " created " + "&cTier " + IntegerHelper.integerToRoman(level) + " Pants&7, gg!"), compileListToString(lore, colorCode("&cTier " + IntegerHelper.integerToRoman(level) + " Pants"), true));
        }else if(enchant.contains("regularity")){
            Sounds.PRESTIGE.play(player);
            hoverText(ChatColor.translateAlternateColorCodes('&', "&d&lRARE! "
                    +  RankColor.getNameColor(player) +
                    player.getDisplayName() + ChatColor.GRAY + " created " + "&cTier " + IntegerHelper.integerToRoman(level) + " Pants&7, gg!"), compileListToString(lore, colorCode("&cTier " + IntegerHelper.integerToRoman(level) + " Pants"), true));
        }else if(enchant.contains("solitude")){
            Sounds.PRESTIGE.play(player);
            hoverText(ChatColor.translateAlternateColorCodes('&', "&d&lRARE! "
                    +  RankColor.getNameColor(player) +
                    player.getDisplayName() + ChatColor.GRAY + " created " + "&cTier " + IntegerHelper.integerToRoman(level) + " Pants&7, gg!"), compileListToString(lore, colorCode("&cTier " + IntegerHelper.integerToRoman(level) + " Pants"), true));
        }else if(enchant.contains("escapepod")){
            Sounds.PRESTIGE.play(player);
            hoverText(ChatColor.translateAlternateColorCodes('&', "&d&lRARE! "
                    +  RankColor.getNameColor(player) +
                    player.getDisplayName() + ChatColor.GRAY + " created " + "&cTier " + IntegerHelper.integerToRoman(level) + " Pants&7, gg!"), compileListToString(lore, colorCode("&cTier " + IntegerHelper.integerToRoman(level) + " Pants"), true));
        }
    }

    public static String getEnchantTitle(String enchant, int tier){
        if (Objects.equals(enchant, "retro-gravitymicrocosm")) {
            return colorCode(redux.retroGravityMicrocosmLore.title(tier));
        }else if (Objects.equals(enchant, "criticallyfunky")) {
            return colorCode(redux.criticallyFunkyLore.title(tier));
        }else if (Objects.equals(enchant, "goldenheart")) {
            return colorCode(redux.goldenHeartLore.title(tier));
        }else if (Objects.equals(enchant, "regularity")) {
            return colorCode(redux.regularityLore.title(tier));
        }else if (Objects.equals(enchant, "billy")) {
            return colorCode(redux.billyLore.title(tier));
        }else if (Objects.equals(enchant, "cricket")) {
            return colorCode(redux.cricketLore.title(tier));
        }else if (Objects.equals(enchant, "pebble")) {
            return colorCode(redux.pebbleLore.title(tier));
        }else if (Objects.equals(enchant, "gottagofast")) {
            return colorCode(redux.gottaGoFastLore.title(tier));
        }else if (Objects.equals(enchant, "self-checkout")) {
            return colorCode(redux.selfCheckoutLore.title(tier));
        }else if (Objects.equals(enchant, "prick")) {
            return colorCode(redux.prickLore.title(tier));
        }else if (Objects.equals(enchant, "protection")) {
            return colorCode(redux.protectionLore.title(tier));
        }else if (Objects.equals(enchant, "pitblob")) {
            return colorCode(redux.pitBlobLore.title(tier));
        }else if (Objects.equals(enchant, "solitude")) {
            return colorCode(redux.solitudeLore.title(tier));
        }else if (Objects.equals(enchant, "diamondallergy")) {
            return colorCode(redux.diamondAllergyLore.title(tier));
        }else if (Objects.equals(enchant, "notgladiator")) {
            return colorCode(redux.notGladiatorLore.title(tier));
        }else if (Objects.equals(enchant, "booboo")) {
            return colorCode(redux.booBooLore.title(tier));
        }else if (Objects.equals(enchant, "mirror")) {
            return colorCode(redux.mirrorLore.title(tier));
        }else if (Objects.equals(enchant, "escapepod")) {
            return colorCode(redux.escapePodLore.title(tier));
        }else if (Objects.equals(enchant, "peroxide")) {
            return colorCode(redux.peroxideLore.title(tier));
        }else if (Objects.equals(enchant, "fractionalreserve")) {
            return colorCode(redux.fractionalReserveLore.title(tier));
        }else if (Objects.equals(enchant, "moctezuma")){
            return colorCode(redux.moctezumaLore.title(tier));
        }else if (Objects.equals(enchant, "goldbump")){
            return colorCode(redux.goldbumpLore.title(tier));
        }else if (Objects.equals(enchant, "pantsradar")){
            return colorCode(redux.pantsRadarLore.title(tier));
        }else if (Objects.equals(enchant, "davidgoliath")){
            return colorCode(redux.davidGoliathLore.title(tier));
        }else if (Objects.equals(enchant, "goldboost")){
            return colorCode(redux.goldboostLore.title(tier));
        }else if (Objects.equals(enchant, "sweaty")){
            return colorCode(redux.sweatyLore.title(tier));
        }else if (Objects.equals(enchant, "xpbump")){
            return colorCode(redux.xpbumpLore.title(tier));
        }else if (Objects.equals(enchant, "xpboost")){
            return colorCode(redux.xpboostLore.title(tier));
        }else{
            return "ERROR";
        }
    }

    public static String convertEnchant(String enchant) {
        if (Objects.equals(enchant, "escape")){
            return "escapepod";
        }else if (Objects.equals(enchant, "glad")){
            return "notgladiator";
        }else if (Objects.equals(enchant, "mirror")){
            return "mirror";
        }else if (Objects.equals(enchant, "crit")){
            return "criticallyfunky";
        }else if (Objects.equals(enchant, "rgm")){
            return "retro-gravitymicrocosm";
        }else if (Objects.equals(enchant, "prot")){
            return "protection";
        }else if(Objects.equals(enchant, "diamondallergy")){
            return "diamondallergy";
        }else if(Objects.equals(enchant, "pantsradar")){
            return "pantsradar";
        }else if(Objects.equals(enchant, "booboo")){
            return "booboo";
        }else if(Objects.equals(enchant, "blob")){
            return "pitblob";
        }else if (Objects.equals(enchant, "soli")){
            return "solitude";
        }else if (Objects.equals(enchant, "frac")){
            return "fractionalreserve";
        }else if (Objects.equals(enchant, "peroxide")){
            return "peroxide";
        }else if (Objects.equals(enchant, "reg")){
            return "regularity";
        }else if (Objects.equals(enchant, "moct")){
            return "moctezuma";
        }else if(Objects.equals(enchant, "david")){
            return "davidgoliath";
        }else if (Objects.equals(enchant, "goldBump")){
            return "goldbump";
        }else if (Objects.equals(enchant, "xp")){
            return "xpboost";
        }else if (Objects.equals(enchant, "xpb")){
            return "xpbump";
        }else if (Objects.equals(enchant, "gb")){
            return "goldboost";
        }else if (Objects.equals(enchant, "goldheart")){
            return "goldenheart";
        }else if(Objects.equals(enchant, "prick")){
            return "prick";
        }else if(Objects.equals(enchant, "billy")){
            return "billy";
        }else if(Objects.equals(enchant, "cricket")){
            return "cricket";
        }else if(Objects.equals(enchant, "pebble")){
            return "pebble";
        }else if(Objects.equals(enchant, "gottagofast")){
            return "gottagofast";
        }else if(Objects.equals(enchant, "self-checkout")){
            return "self-checkout";
        }else{
            return enchant;
        }
    }

    public static String enchantTier(String enchant, int tier){
        if (Objects.equals(enchant, "retro-gravitymicrocosm")) {
            return colorCode(redux.retroGravityMicrocosmLore.lore(tier));
        }else if (Objects.equals(enchant, "criticallyfunky")) {
            return colorCode(redux.criticallyFunkyLore.lore(tier));
        }else if (Objects.equals(enchant, "goldenheart")) {
            return colorCode(redux.goldenHeartLore.lore(tier));
        }else if (Objects.equals(enchant, "regularity")) {
            return colorCode(redux.regularityLore.lore(tier));
        }else if (Objects.equals(enchant, "protection")) {
            return colorCode(redux.protectionLore.lore(tier));
        }else if (Objects.equals(enchant, "solitude")) {
            return colorCode(redux.solitudeLore.lore(tier));
        }else if (Objects.equals(enchant, "billy")) {
            return colorCode(redux.billyLore.lore(tier));
        }else if (Objects.equals(enchant, "cricket")) {
            return colorCode(redux.cricketLore.lore(tier));
        }else if (Objects.equals(enchant, "pebble")) {
            return colorCode(redux.pebbleLore.lore(tier));
        }else if (Objects.equals(enchant, "gottagofast")) {
            return colorCode(redux.gottaGoFastLore.lore(tier));
        }else if (Objects.equals(enchant, "self-checkout")) {
            return colorCode(redux.selfCheckoutLore.lore(tier));
        }else if (Objects.equals(enchant, "prick")) {
            return colorCode(redux.prickLore.lore(tier));
        }else if (Objects.equals(enchant, "booboo")) {
            return colorCode(redux.booBooLore.lore(tier));
        }else if (Objects.equals(enchant, "pitblob")) {
            return colorCode(redux.pitBlobLore.lore(tier));
        }else if (Objects.equals(enchant, "notgladiator")) {
            return colorCode(redux.notGladiatorLore.lore(tier));
        }else if (Objects.equals(enchant, "pantsradar")){
            return colorCode(redux.pantsRadarLore.lore(tier));
        }else if (Objects.equals(enchant, "mirror")) {
            return colorCode(redux.mirrorLore.lore(tier));
        }else if (Objects.equals(enchant, "escapepod")) {
            return colorCode(redux.escapePodLore.lore(tier));
        }else if (Objects.equals(enchant, "peroxide")) {
            return colorCode(redux.peroxideLore.lore(tier));
        }else if (Objects.equals(enchant, "diamondallergy")) {
            return colorCode(redux.diamondAllergyLore.lore(tier));
        }else if (Objects.equals(enchant, "fractionalreserve")) {
            return colorCode(redux.fractionalReserveLore.lore(tier));
        }else if (Objects.equals(enchant, "moctezuma")){
            return colorCode(redux.moctezumaLore.lore(tier));
        }else if (Objects.equals(enchant, "goldbump")){
            return colorCode(redux.goldbumpLore.lore(tier));
        }else if (Objects.equals(enchant, "davidgoliath")){
            return colorCode(redux.davidGoliathLore.lore(tier));
        }else if (Objects.equals(enchant, "goldboost")){
            return colorCode(redux.goldboostLore.lore(tier));
        }else if (Objects.equals(enchant, "sweaty")){
            return colorCode(redux.sweatyLore.lore(tier));
        }else if (Objects.equals(enchant, "xpbump")){
            return colorCode(redux.xpbumpLore.lore(tier));
        }else if (Objects.equals(enchant, "xpboost")){
            return colorCode(redux.xpboostLore.lore(tier));
        }else{
            return "ERROR";
        }
    }

    private static double calcEnchant(List<String> lore, String name){
        if (lore.contains(name)) return 3;
        return 1;
    }

    public static String getEnchant(List<String> lore){

        for (String ench : lore){
            lore.set(lore.indexOf(ench), convertEnchant(ench.replaceAll("I", "")));
        }

        // Super Rare
        double pitBlob = .001 * calcEnchant(lore, "pitblob");

        // Rare
        double retroGravityMicrocosm = .0125 * calcEnchant(lore, "retro-gravitymicrocosm");
        double regularity = .0125 * calcEnchant(lore, "regularity");
        double solitude = .0125 * calcEnchant(lore, "solitude");
        double escapePod = .0125 * calcEnchant(lore, "escapepod");

        // Common Normal
        double protection = .0535 * calcEnchant(lore, "protection");
        double booBoo = .0535 * calcEnchant(lore, "booboo");
        double peroxide = .0535 * calcEnchant(lore, "peroxide");
        double diamondAllergy = .0535 * calcEnchant(lore , "diamondallergy");
        double cricket = .0535 * calcEnchant(lore, "cricket");
        double billy = .0535 * calcEnchant(lore, "billy");

        // Uncommon Normal
        double criticallyFunky = .0425 * calcEnchant(lore, "criticallyfunky");
        double davidGoliath = .0425 * calcEnchant(lore , "davidgoliath");
        double goldenHeart = .0125 * calcEnchant(lore, "goldenheart");
        double fractionalReserve = .0425 * calcEnchant(lore, "fractionalreserve");
        double mirror = .0425 * calcEnchant(lore, "mirror");
        double notGladiator = .0425 * calcEnchant(lore, "notgladiator");
        double selfCheckout = .0425 * calcEnchant(lore, "self-checkout");
        double pebble = .0425 * calcEnchant(lore, "pebble");
        double gottaGoFast = .0425 * calcEnchant(lore, "gottagofast");
        double prick = .0425 * calcEnchant(lore, "prick");

        // Resource - Gold
        double goldbump = .0475 * calcEnchant(lore, "goldbump");
        double goldboost = .0450 * calcEnchant(lore, "goldboost");
        double moctezuma = .0425 * calcEnchant(lore, "moctezuma");

        // Resource - Sweaty
        double sweaty = .0425 * calcEnchant(lore, "sweaty");
        double xpbump = .0475 * calcEnchant(lore, "xpbump");
        double xpboost = .0475 * calcEnchant(lore, "xpboost");

        // Resource - Misc
        double pantsRadar = .0425 * calcEnchant(lore, "pantsradar");


        while (true) {
            if(percentChance(pantsRadar)){
                // Pants radar
                // 5.25% of being here
                return "pantsradar";
            }else if(percentChance(davidGoliath)){
                return "davidgoliath";
            }else if(percentChance(prick)){
                return "prick";
            }else if(percentChance(selfCheckout)){
                return "self-checkout";
            }else if(percentChance(pebble)){
                return "pebble";
            }else if(percentChance(gottaGoFast)){
                return "gottagofast";
            }else if(percentChance(cricket)){
                return "cricket";
            }else if(percentChance(billy)){
                return "billy";
            }else if(percentChance(diamondAllergy)){
                return "diamondallergy";
            }else if (percentChance(criticallyFunky)){
                // Perun
                // 2% chance of being here
                return "criticallyfunky";
            }else if (percentChance(protection)){
                // Gamble
                // 3.25% chance of being here
                return "protection";
            }else if (percentChance(booBoo)){
                // Gamble
                // 3.25% chance of being here
                return "booboo";
            }else if (percentChance(goldenHeart)){
                // Golden Heart
                // 7.35% chance of being here
                return "goldenheart";
            }else if (percentChance(xpboost)){
                // Xp Boost
                // 5% chance of being here
                return "xpboost";
            }else if (percentChance(notGladiator)){
                // Lifesteal
                // 6.25% chance of being here
                return "notgladiator";
            }else if (percentChance(goldboost)){
                // Gold Boost
                // 6.5% chance of being here
                return "goldboost";
            }else if (percentChance(peroxide)){
                // Shark
                // 7.25% chance of being here
                return "peroxide";
            }else if (percentChance(xpbump)){
                // Xp Bump
                // 7.50% chance of being here
                return "xpbump";
            }else if (percentChance(pitBlob)){
                // Pitblob
                // .1% chance of being here
                return "pitblob";
            }else if (percentChance(goldbump)){
                // Gold Bump
                // 7.75% chance of being here
                return "goldbump";
            }else if (percentChance(mirror)){
                // Diamond Stomp
                // 8.25% chance of being here
                return "mirror";
            }else if (percentChance(sweaty)){
                // Sweaty
                // 9.25% chance of being here
                return "sweaty";
            }else if (percentChance(moctezuma)){
                // Moctezuma
                // 10.25% chance of being here
                return "moctezuma";
            }else if (percentChance(fractionalReserve)){
                // King Buster
                // 11.25% chance of being here
                return "fractionalreserve";
            }else if (percentChance(retroGravityMicrocosm)){
                // Billionaire
                // 1% chance of being here
                return "retro-gravitymicrocosm";
            }else if (percentChance(regularity)){
                // Executioner
                // 2.5% chance of being here
                return "regularity";
            }else if (percentChance(solitude)){
                // Pain Focus
                // 5.25% chance of being here
                return "solitude";
            }else if (percentChance(escapePod)){
                // Sharp
                // 6.75% chance of being here
                return "escapepod";
            }
        }
    }

}
