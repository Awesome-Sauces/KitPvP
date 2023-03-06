package me.alpha.kitpvp.PitRemake.MysticWell.enchanters;

import de.tr7zw.nbtapi.NBTCompound;
import de.tr7zw.nbtapi.NBTItem;
import me.alpha.kitpvp.ChatManager.RankColor;
import me.alpha.kitpvp.CustomEvents.ReduxInventoryEvent;
import me.alpha.kitpvp.Data.ClassInstances;
import me.alpha.kitpvp.PitRemake.Scoreboard.ScoreboardCore;
import me.alpha.kitpvp.utils.EnchantSound;
import me.alpha.kitpvp.utils.Sounds;
import me.alpha.kitpvp.utils.advancedInventory;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import static me.alpha.kitpvp.Data.GoldData.getEconomy;
import static me.alpha.kitpvp.Data.GoldData.removeEconomy;
import static me.alpha.kitpvp.PitRemake.MysticWell.MysticWellGUI.getMysticWellItem;
import static me.alpha.kitpvp.PitRemake.MysticWell.loreChecker.CheckEnchantOnSword;
import static me.alpha.kitpvp.utils.ColorUtil.colorCode;
import static me.alpha.kitpvp.utils.FancyText.compileListToString;
import static me.alpha.kitpvp.utils.FancyText.hoverText;
import static me.alpha.kitpvp.utils.IntegerHelper.integerToRoman;

public class MysticSword {

    private static boolean removeGold(Player player, String uuid, int amount){
        if(getEconomy(uuid) > amount-1){
            removeEconomy(uuid, amount);
            ScoreboardCore.CreateScore(player);
            return true;
        }else{
            player.sendMessage(colorCode("&l&cERROR! &7You need &6" + (amount-getEconomy(uuid)) + "g &7to afford this!"));
            return false;
        }
    }

    public static int getTokens(List<String> lore){
        int tokens = 0;
        if(lore!=null) for(String string : CheckEnchantOnSword(lore)){
            tokens+=string.length()-string.replaceAll("I", "").length();
        }

        return tokens;
    }

    public static void clickSword(ReduxInventoryEvent event){

        String uuid = String.valueOf(event.getPlayer().getUniqueId());
        Player player = event.getPlayer();
        ItemStack items = event.getInventory().getItem(20);

        int tokens = getTokens(items.getItemMeta().getLore());
        NBTItem nbtItem = new NBTItem(items);

        if (nbtItem.hasKey("mysticTier") && nbtItem.getInteger("mysticTier")==3){
            ItemMeta meta = items.getItemMeta();
            meta.setDisplayName(ChatColor.RED + "Tier III Sword");
            items.setItemMeta(meta);
            player.sendMessage(ChatColor.RED + "This sword is already max tier!");

            return;
        } else if (!nbtItem.hasKey("mysticTier")  && removeGold(player, uuid, 1000)) {
            Sounds.BUTTON.play(player);
            Sounds.PIN_DOWN.play(player);

            event.getInventory().setItem(20, createSword(player,1, null));
            //Bukkit.broadcastMessage(String.valueOf(getTokens(items.getItemMeta().getLore())));
        } else if (nbtItem.hasKey("mysticTier") && nbtItem.getInteger("mysticTier")==1 && removeGold(player, uuid, 4000)) {
            Sounds.BUTTON.play(player);
            Sounds.PIN_DOWN.play(player);
            //Bukkit.broadcastMessage(String.valueOf(getTokens(items.getItemMeta().getLore())));
            event.getInventory().setItem(20, createSword(player,2, event.getInventory().getItem(20)));
        }else if (
                nbtItem.hasKey("mysticTier") && nbtItem.getInteger("mysticTier")==2 && removeGold(player, uuid, 8000)) {
            Sounds.BUTTON.play(player);
            Sounds.PIN_DOWN.play(player);
            //Bukkit.broadcastMessage(String.valueOf(getTokens(items.getItemMeta().getLore())));
            event.getInventory().setItem(20, createSword(player,3, event.getInventory().getItem(20)));
        }

        advancedInventory.addInv(event.getInventory(), getMysticWellItem(uuid, event.getInventory().getItem(20)), 7, 3, false);

    }

    public static ItemStack createSword(Player player, int tier, ItemStack sword){
        if (sword == null){
            ItemStack item = new ItemStack(Material.GOLD_SWORD, 1);
            ItemMeta meta = item.getItemMeta();
            meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&cTier " + integerToRoman(tier) + " Sword"));
            meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE, ItemFlag.HIDE_ATTRIBUTES);
            meta.spigot().setUnbreakable(true);
            //Shaped Recipe

            item.setItemMeta(meta);

            return enchantMystic(player, item, tier);
        }else{
            ItemMeta meta = sword.getItemMeta();
            meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&cTier " + integerToRoman(tier) + " Sword"));
            if(tier==3){
                meta.addEnchant(Enchantment.DAMAGE_ALL,2,false);
            }
            if(tier==2){
                meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
            }
            meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE, ItemFlag.HIDE_ATTRIBUTES);
            meta.spigot().setUnbreakable(true);
            //enchantSword(player, sword, tier)
            sword.setItemMeta(meta);
            //Shaped Recipe

            return enchantMystic(player, sword, tier);
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

        if(tier<=1){
            while (true){
                if(percentChance(.15)){
                    nbtItem.setInteger("maxLives", 7);
                    nbtItem.setInteger("lives", 7);
                    break;
                }else if(percentChance(.05)){
                    nbtItem.setInteger("maxLives", 10);
                    nbtItem.setInteger("lives", 10);
                    break;
                }else if(percentChance(.04)){
                    nbtItem.setInteger("maxLives", 10);
                    nbtItem.setInteger("lives", 10);
                    break;
                }else if(percentChance(.03)){
                    nbtItem.setInteger("maxLives", 15);
                    nbtItem.setInteger("lives", 15);
                    break;
                }else if(percentChance(.02)){
                    nbtItem.setInteger("maxLives", 20);
                    nbtItem.setInteger("lives", 20);
                    break;
                }else if(percentChance(.01)){
                    nbtItem.setInteger("maxLives", 25);
                    nbtItem.setInteger("lives", 25);
                    break;
                }else if(percentChance(.001)){
                    nbtItem.setInteger("maxLives", 100);
                    nbtItem.setInteger("lives", 100);
                    break;
                }
            }
        }

        nbtItem.setInteger("mysticTier", tier);
        nbtItem.addCompound("enchants");

        NBTCompound nbtCompound = nbtItem.getOrCreateCompound("enchants");

        List<String> lore = new ArrayList<>();
        List<String> enchants = new ArrayList<>(nbtCompound.getKeys());

        String ench;
        int tokens = 0;

        int loopnum = 0;

        String FINAL_ENCHANT = "";

        while(true){

            float tier1 = ((float) (40)/100);
            float tier2 = ((float) (35)/100);
            float tier3 = ((float) (25)/100);

            ench = getEnchant(enchants);

            if(percentChance(tier1)){

                if(nbtCompound.hasKey(ench)){
                    int level = nbtCompound.getInteger(ench);

                    if(level>=3) continue;

                    nbtCompound.setInteger(ench, Math.min(3, level+1));
                    break;
                }else{
                    nbtCompound.setInteger(ench, 1);
                    break;
                }
            }else if(percentChance(tier2)){

                if(nbtCompound.hasKey(ench)){
                    int level = nbtCompound.getInteger(ench);

                    if(level>=3) continue;

                    nbtCompound.setInteger(ench, Math.min(3, level+2));
                    break;
                }else{
                    nbtCompound.setInteger(ench, 2);
                    break;
                }

            }else if(percentChance(tier3)){

                if(nbtCompound.hasKey(ench)){
                    int level = nbtCompound.getInteger(ench);

                    if(level>=3) continue;

                    nbtCompound.setInteger(ench, Math.min(3, level+3));
                    break;
                }else{
                    nbtCompound.setInteger(ench, 3);
                    break;
                }
            }
        }


        nbtItem.mergeCompound(nbtCompound);

        ItemMeta itemMeta = nbtItem.getItem().getItemMeta();

        int currentLives = nbtItem.getInteger("lives");
        int maxLives = nbtItem.getInteger("maxLives");
        String currentLivesColor = "&a";

        if(currentLives<=(maxLives/3)) currentLivesColor = "&c";

        String livesTemplate = colorCode("&7Lives: " + currentLivesColor + currentLives+"&7/"+maxLives);

        lore.add(livesTemplate);
        lore.add("   ");

        for (String key : nbtItem.getCompound("enchants").getKeys()){
            int level = nbtItem.getInteger(key);


            lore.addAll(Arrays.asList(MysticSword.enchantTier(key, level).split("\n")));
        }

        lore.add(ChatColor.BLUE + "+6.5 Attack Damage");

        itemMeta.setLore(lore);

        nbtItem.getItem().setItemMeta(itemMeta);

        getRareEnchant(nbtItem.getItem().getItemMeta().getLore(), ench, player, tier);


        return nbtItem.getItem();
    }

    public static List<String> enchantSword(Player player, ItemStack sword, int tier) {

        double chanceII = 0.0001;
        double chanceIII = 0.0001;

        List<String> enchants = new ArrayList<>();


        if (sword != null &&
        sword.getItemMeta()!=null&&
        sword.getItemMeta().getLore()!=null){enchants = CheckEnchantOnSword(sword.getItemMeta().getLore());}

        List<String> lore = new ArrayList<>();
        lore.add(ChatColor.translateAlternateColorCodes('&', "&7Lives: &a5&7/5"));

        lore.add(" ");

        for (String bench : enchants){
            int ether = bench.length() - bench.replaceAll("I", "").length();

            lore.addAll(Arrays.asList(enchantTier(convertEnchant(bench.replaceAll("I", "")), ether).split("\n")));
            //lore.add(" ");
        }

     //   lore.addAll(enchants);

        String ench;
        int tokens = 0;
        boolean looping = true;

        int loopnum = 0;

        String FINAL_ENCHANT = "";

        while (looping){
            loopnum++;
            ench = getEnchant(enchants);
            FINAL_ENCHANT = ench;


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

                if (lore.contains(colorCode(getEnchantTitle(ench, 3)))){
                    continue;
                }else if (lore.contains(colorCode(getEnchantTitle(ench, 1)))){
                    //lore.remove(" ");
                    lore.removeAll(translateList(Arrays.asList(enchantTier(ench, 1).split("\n"))));
                    lore = renewEnchant(lore, translateList(Arrays.asList(enchantTier(ench, 2).split("\n"))));
                    looping = false;
                }else if (lore.contains(colorCode(getEnchantTitle(ench, 2)))){
                    //lore.remove(" ");
                    lore.removeAll(translateList(Arrays.asList(enchantTier(ench, 2).split("\n"))));
                    lore = renewEnchant(lore, translateList(Arrays.asList(enchantTier(ench, 3).split("\n"))));
                    looping = false;
                }else if (!lore.contains(colorCode(getEnchantTitle(ench, 3))) &&
                        !lore.contains(colorCode(getEnchantTitle(ench, 2))) &&
                        !lore.contains(colorCode(getEnchantTitle(ench, 1)))){
                    lore.addAll(Arrays.asList(enchantTier(ench, 1).split("\n")));
                    looping = false;
                }

            }else if (percentChance(tier2)){

                if (lore.contains(colorCode(getEnchantTitle(ench, 3)))){
                    continue;
                }else if (lore.contains(colorCode(getEnchantTitle(ench, 2)))){
                    //lore.remove(" ");
                    lore.removeAll(translateList(Arrays.asList(enchantTier(ench, 2).split("\n"))));
                    lore = renewEnchant(lore, translateList(Arrays.asList(enchantTier(ench, 3).split("\n"))));
                    looping = false;
                }else if (lore.contains(colorCode(getEnchantTitle(ench, 1)))){
                    //lore.remove(" ");
                    lore.removeAll(translateList(Arrays.asList(enchantTier(ench, 1).split("\n"))));
                    lore = renewEnchant(lore, translateList(Arrays.asList(enchantTier(ench, 3).split("\n"))));
                    looping = false;
                }else if(!lore.contains(colorCode(getEnchantTitle(ench, 3))) &&
                        !lore.contains(colorCode(getEnchantTitle(ench, 2))) &&
                        !lore.contains(colorCode(getEnchantTitle(ench, 1)))){
                    lore.addAll(Arrays.asList(enchantTier(ench, 2).split("\n")));
                    looping = false;
                }

            }else if (percentChance(tier3)){

                if (!lore.contains(colorCode(getEnchantTitle(ench, 3))) &&
                        !lore.contains(colorCode(getEnchantTitle(ench, 2))) &&
                        !lore.contains(colorCode(getEnchantTitle(ench, 1)))){
                    lore.addAll(Arrays.asList(enchantTier(ench, 3).split("\n")));
                    looping = false;
                }else if(lore.contains(colorCode(getEnchantTitle(ench, 2))) || lore.contains(colorCode(getEnchantTitle(ench, 1)))){

                    if (lore.contains(colorCode(getEnchantTitle(ench, 2)))){
                        lore.removeAll(translateList(Arrays.asList(enchantTier(ench, 2).split("\n"))));
                        lore = renewEnchant(lore, translateList(Arrays.asList(enchantTier(ench, 3).split("\n"))));
                    }else if (lore.contains(colorCode(getEnchantTitle(ench, 1)))){
                        lore.removeAll(translateList(Arrays.asList(enchantTier(ench, 1).split("\n"))));
                        lore = renewEnchant(lore, translateList(Arrays.asList(enchantTier(ench, 3).split("\n"))));
                    }

                    looping = false;
                }


            }/*else{

                lore.addAll(Arrays.asList(enchantTier(ench, 1).split("\n")));

                looping = false;
            }
            */
        }

        /*
        T = sword tokens

                E = enchant tokens

                C = chance

        Equation:

        > c = ((T/E)*10) / 100

         */

        lore.add(ChatColor.BLUE + "+6.5 Attack Damage");

        getRareEnchant(lore, FINAL_ENCHANT, player, tier);

        return lore;

       // return new EnchantingMechanics(lore, enchants.get(0), chanceIII, chanceII, "SWORD").getLore();

    }

    public static List<String> renewEnchant(List<String> lore, List<String> enchant){

        List<String> enchants = CheckEnchantOnSword(lore);

        // Bukkit.broadcastMessage(enchants.toString());

        lore = new ArrayList<>();
        lore.add(ChatColor.translateAlternateColorCodes('&', "&7Lives: &a5&7/5"));

        lore.add(" ");

        for (String bench : enchants){
            int ether = bench.length() - bench.replaceAll("I", "").length();

            lore.addAll(Arrays.asList(enchantTier(convertEnchant(bench.replaceAll("I", "")), ether).split("\n")));
            //lore.add(" ");
        }

        //hoverText(ChatColor.translateAlternateColorCodes('&', "&d&lRARE! " + RankColor.getNameColor(player) + player.getDisplayName() + "&7 created &cTier I Sword!"), compileListToString(lore));
        //player.playSound(player.getLocation(), Sound.ENDERDRAGON_GROWL, 1, 1);

        lore.addAll(enchant);

        return lore;
    }

    public static void getRareEnchant(List<String> lore, String enchant, Player player, int level){

        if(enchant.contains("billionaire")){
            Sounds.PRESTIGE.play(player);
            hoverText(ChatColor.translateAlternateColorCodes('&', "&d&lRARE! "
                    +  RankColor.getNameColor(player) +
                    player.getDisplayName() + ChatColor.GRAY + " created " + "&cTier " + integerToRoman(level) + " Sword&7, gg!"), compileListToString(lore, colorCode("&cTier " + integerToRoman(level) + " Sword"), true));
            new EnchantSound(player, player.getLocation()).play(EnchantSound.Tier.getTier(level), true);
        }else if(enchant.contains("perun")){
            Sounds.PRESTIGE.play(player);
            hoverText(ChatColor.translateAlternateColorCodes('&', "&d&lRARE! "
                    +  RankColor.getNameColor(player) +
                    player.getDisplayName() + ChatColor.GRAY + " created " + "&cTier " + integerToRoman(level) + " Sword&7, gg!"), compileListToString(lore, colorCode("&cTier " + integerToRoman(level) + " Sword"), true));
            new EnchantSound(player, player.getLocation()).play(EnchantSound.Tier.getTier(level), true);
        }else if(enchant.contains("executioner")){
            Sounds.PRESTIGE.play(player);
            hoverText(ChatColor.translateAlternateColorCodes('&', "&d&lRARE! "
                    +  RankColor.getNameColor(player) +
                    player.getDisplayName() + ChatColor.GRAY + " created " + "&cTier " + integerToRoman(level) + " Sword&7, gg!"), compileListToString(lore, colorCode("&cTier " + integerToRoman(level) + " Sword"), true));
            new EnchantSound(player, player.getLocation()).play(EnchantSound.Tier.getTier(level), true);
        }else if(enchant.contains("gamble")){
            Sounds.PRESTIGE.play(player);
            hoverText(ChatColor.translateAlternateColorCodes('&', "&d&lRARE! "
                    +  RankColor.getNameColor(player) +
                    player.getDisplayName() + ChatColor.GRAY + " created " + "&cTier " + integerToRoman(level) + " Sword&7, gg!"), compileListToString(lore, colorCode("&cTier " + integerToRoman(level) + " Sword"), true));

            new EnchantSound(player, player.getLocation()).play(EnchantSound.Tier.getTier(level), true);
        }else{
            new EnchantSound(player, player.getLocation()).play(EnchantSound.Tier.getTier(level), false);
        }


    }

    public static String getEnchantTitle(String enchant, int tier){
        if (Objects.equals(enchant, "billionaire")) {
            return colorCode(ClassInstances.billionaireLore.title(tier));
        }else if (Objects.equals(enchant, "perun")) {
            return colorCode(ClassInstances.perunLore.title(tier));
        }else if (Objects.equals(enchant, "fancyraider")) {
            return colorCode(ClassInstances.fancyraiderLore.title(tier));
        }else if (Objects.equals(enchant, "executioner")) {
            return colorCode(ClassInstances.executionerLore.title(tier));
        }else if (Objects.equals(enchant, "gamble")) {
            return colorCode(ClassInstances.gambleLore.title(tier));
        }else if (Objects.equals(enchant, "painfocus")) {
            return colorCode(ClassInstances.painFocusLore.title(tier));
        }else if (Objects.equals(enchant, "lifesteal")) {
            return colorCode(ClassInstances.lifestealLore.title(tier));
        }else if (Objects.equals(enchant, "sharp")) {
            return colorCode(ClassInstances.sharpLore.title(tier));
        }else if (Objects.equals(enchant, "shark")) {
            return colorCode(ClassInstances.sharkLore.title(tier));
        }else if (Objects.equals(enchant, "diamondstomp")) {
            return colorCode(ClassInstances.diamondStompLore.title(tier));
        }else if (Objects.equals(enchant, "combodamage")) {
            return colorCode(ClassInstances.combodamageLore.title(tier));
        }else if (Objects.equals(enchant, "kingbuster")) {
            return colorCode(ClassInstances.kingBusterLore.title(tier));
        }else if (Objects.equals(enchant, "punisher")) {
            return colorCode(ClassInstances.punisherLore.title(tier));
        }else if (Objects.equals(enchant, "grasshopper")) {
            return colorCode(ClassInstances.grasshopperLore.title(tier));
        }else if (Objects.equals(enchant, "goldandboosted")) {
            return colorCode(ClassInstances.goldBoostedLore.title(tier));
        }else if (Objects.equals(enchant, "pitpocket")) {
            return colorCode(ClassInstances.pitPocketLore.title(tier));
        }else if (Objects.equals(enchant, "berserker")) {
            return colorCode(ClassInstances.berserkerLore.title(tier));
        }else if (Objects.equals(enchant, "moctezuma")){
            return colorCode(ClassInstances.moctezumaLore.title(tier));
        }else if (Objects.equals(enchant, "goldbump")){
            return colorCode(ClassInstances.goldbumpLore.title(tier));
        }else if (Objects.equals(enchant, "goldboost")){
            return colorCode(ClassInstances.goldboostLore.title(tier));
        }else if (Objects.equals(enchant, "speedykill")){
            return colorCode(ClassInstances.speedyKillLore.title(tier));
        }else if (Objects.equals(enchant, "pantsradar")){
            return colorCode(ClassInstances.pantsRadarLore.title(tier));
        }else if (Objects.equals(enchant, "sweaty")){
            return colorCode(ClassInstances.sweatyLore.title(tier));
        }else if (Objects.equals(enchant, "xpbump")){
            return colorCode(ClassInstances.xpbumpLore.title(tier));
        }else if (Objects.equals(enchant, "xpboost")){
            return colorCode(ClassInstances.xpboostLore.title(tier));
        }else{
            return "ERROR";
        }
    }

    public static String convertEnchant(String enchant) {
        if (Objects.equals(enchant, "bill")){
            return "billionaire";
        }else if (Objects.equals(enchant, "pf")){
            return "painfocus";
        }else if (Objects.equals(enchant, "ls")){
            return "lifesteal";
        }else if (Objects.equals(enchant, "diamond")){
            return "diamondstomp";
        }else if(Objects.equals(enchant, "pantsradar")){
            return "pantsradar";
        }else if(Objects.equals(enchant, "combodamage")){
            return "combodamage";
        }else if(Objects.equals(enchant, "punisher")){
            return "punisher";
        }else if(Objects.equals(enchant, "pitpocket")){
            return "pitpocket";
        }else if(Objects.equals(enchant, "berserker")){
            return "berserker";
        }else if(Objects.equals(enchant, "goldandboosted")){
            return "goldandboosted";
        }else if(Objects.equals(enchant, "self-checkout")){
            return "self-checkout";
        }else if(Objects.equals(enchant, "grasshopper")){
            return "grasshopper";
        }else if (Objects.equals(enchant, "gamb")){
            return "gamble";
        }else if (Objects.equals(enchant, "king")){
            return "kingbuster";
        }else if (Objects.equals(enchant, "exe")){
            return "executioner";
        }else if (Objects.equals(enchant, "moct")){
            return "moctezuma";
        }else if (Objects.equals(enchant, "goldBump")){
            return "goldbump";
        }else if (Objects.equals(enchant, "xp")){
            return "xpboost";
        }else if (Objects.equals(enchant, "speedykill")){
            return "speedykill";
        }else if (Objects.equals(enchant, "xpb")){
            return "xpbump";
        }else if (Objects.equals(enchant, "fancyraider")){
            return "fancyraider";
        }else if (Objects.equals(enchant, "gb")){
            return "goldboost";
        }else{
            return enchant;
        }
    }

    public static String enchantTier(String enchant, int tier){
        if (Objects.equals(enchant, "billionaire")) {
            return ClassInstances.billionaireLore.lore(tier);
        }else if (Objects.equals(enchant, "perun")) {
            return ClassInstances.perunLore.lore(tier);
        }else if (Objects.equals(enchant, "executioner")) {
            return ClassInstances.executionerLore.lore(tier);
        }else if (Objects.equals(enchant, "gamble")) {
            return ClassInstances.gambleLore.lore(tier);
        }else if (Objects.equals(enchant, "painfocus")) {
            return ClassInstances.painFocusLore.lore(tier);
        }else if (Objects.equals(enchant, "lifesteal")) {
            return ClassInstances.lifestealLore.lore(tier);
        }else if (Objects.equals(enchant, "sharp")) {
            return ClassInstances.sharpLore.lore(tier);
        }else if (Objects.equals(enchant, "shark")) {
            return ClassInstances.sharkLore.lore(tier);
        }else if (Objects.equals(enchant, "diamondstomp")) {
            return ClassInstances.diamondStompLore.lore(tier);
        }else if (Objects.equals(enchant, "kingbuster")) {
            return ClassInstances.kingBusterLore.lore(tier);
        }else if (Objects.equals(enchant, "punisher")) {
            return colorCode(ClassInstances.punisherLore.lore(tier));
        }else if (Objects.equals(enchant, "grasshopper")) {
            return colorCode(ClassInstances.grasshopperLore.lore(tier));
        }else if (Objects.equals(enchant, "goldandboosted")) {
            return colorCode(ClassInstances.goldBoostedLore.lore(tier));
        }else if (Objects.equals(enchant, "pitpocket")) {
            return colorCode(ClassInstances.pitPocketLore.lore(tier));
        }else if (Objects.equals(enchant, "berserker")) {
            return colorCode(ClassInstances.berserkerLore.lore(tier));
        }else if (Objects.equals(enchant, "moctezuma")){
            return ClassInstances.moctezumaLore.lore(tier);
        }else if (Objects.equals(enchant, "goldbump")){
            return ClassInstances.goldbumpLore.lore(tier);
        }else if (Objects.equals(enchant, "pantsradar")){
            return colorCode(ClassInstances.pantsRadarLore.lore(tier));
        }else if (Objects.equals(enchant, "goldboost")){
            return ClassInstances.goldboostLore.lore(tier);
        }else if (Objects.equals(enchant, "speedykill")){
            return colorCode(ClassInstances.speedyKillLore.lore(tier));
        }else if (Objects.equals(enchant, "combodamage")){
            return ClassInstances.combodamageLore.lore(tier);
        }else if (Objects.equals(enchant, "sweaty")){
            return ClassInstances.sweatyLore.lore(tier);
        }else if (Objects.equals(enchant, "xpbump")){
            return ClassInstances.xpbumpLore.lore(tier);
        }else if (Objects.equals(enchant, "xpboost")){
            return ClassInstances.xpboostLore.lore(tier);
        }else if (Objects.equals(enchant, "fancyraider")){
            return ClassInstances.fancyraiderLore.lore(tier);
        } else{
            return "ERROR";
        }
    }

    private static double calcEnchant(List<String> lore, String name){
        if(lore.contains(name) &&
                (name.equals("billionaire") ||
                        name.equals("perun") ||
                        name.equals("executioner") ||
                        name.equals("gamble"))) return 8;
        if (lore.contains(name)) return 7;
        return 1;
    }

    public static String getEnchant(List<String> lore){

        // Rare
        double billionaire = .0125 * calcEnchant(lore, "billionaire");
        double perun = .0125 * calcEnchant(lore, "perun");
        double executioner = .0125 * calcEnchant(lore, "executioner");
        double gamble = .0125 * calcEnchant(lore, "gamble");

        // Common Normal
        double speedykill = .0535 * calcEnchant(lore, "speedykill");

        double combodamage = .0535 * calcEnchant(lore, "combodamage");
        double sharp = .0535 * calcEnchant(lore, "sharp");
        double kingbuster = .0535 * calcEnchant(lore, "kingbuster");
        double fancyraider = .0535 * calcEnchant(lore, "fancyraider");
        double punisher = .0535 * calcEnchant(lore, "punisher");
        double pitpocket = .0535 * calcEnchant(lore, "pitpocket");
        double berserker = .0535 * calcEnchant(lore, "berserker");
        double grasshopper = .0535 * calcEnchant(lore, "grasshopper");

        // Uncommon Normal
        double painfocus = .0425 * calcEnchant(lore, "painfocus");
        double lifesteal = .0425 * calcEnchant(lore, "lifesteal");
        double diamondstomp = .0425 * calcEnchant(lore, "diamondstomp");
        double shark = .0425 * calcEnchant(lore, "shark");
        double goldandboosted = .0425 * calcEnchant(lore, "goldandboosted");

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
            }else if (percentChance(xpboost)){
                // Xp Boost
                // 5% chance of being here
                return "xpboost";
            }else if (percentChance(xpboost)){
                // Xp Boost
                // 5% chance of being here
                return "speedykill";
            }else if (percentChance(painfocus)){
                // Pain Focus
                // 5.25% chance of being here
                return "painfocus";
            }else if(percentChance(punisher)){
                return "punisher";
            }else if(percentChance(goldandboosted)){
                return "goldandboosted";
            }else if(percentChance(pitpocket)){
                return "pitpocket";
            }else if(percentChance(grasshopper)){
                return "grasshopper";
            }else if(percentChance(berserker)){
                return "berserker";
            }else if (percentChance(lifesteal)){
                // Lifesteal
                // 6.25% chance of being here
                return "lifesteal";
            }else if (percentChance(goldboost)){
                // Gold Boost
                // 6.5% chance of being here
                return "goldboost";
            }else if (percentChance(combodamage)){
                // Combo Damage
                // Huys is the best Pit Pvper!!!!
                // 4.35% chance of being here
                return "combodamage";
            }else if (percentChance(fancyraider)){
                // Fancy Raider
                // U Need to learn to get females. (Like huys does)
                // 5.35% chance of being here
                return "fancyraider";
            }else if (percentChance(sharp)){
                // Sharp
                // 6.75% chance of being here
                return "sharp";
            }else if (percentChance(shark)){
                // Shark
                // 7.25% chance of being here
                return "shark";
            }else if (percentChance(xpbump)){
                // Xp Bump
                // 7.50% chance of being here
                return "xpbump";
            }else if (percentChance(goldbump)){
                // Gold Bump
                // 7.75% chance of being here
                return "goldbump";
            }else if (percentChance(diamondstomp)){
                // Diamond Stomp
                // 8.25% chance of being here
                return "diamondstomp";
            }else if (percentChance(sweaty)){
                // Sweaty
                // 9.25% chance of being here
                return "sweaty";
            }else if (percentChance(moctezuma)){
                // Moctezuma
                // 10.25% chance of being here
                return "moctezuma";
            }else if (percentChance(kingbuster)){
                // King Buster
                // 11.25% chance of being here
                return "kingbuster";
            }else if (percentChance(billionaire)){
                // Billionaire
                // 1% chance of being here
                return "billionaire";
            } else if (percentChance(perun)){
                // Perun
                // 2% chance of being here
                return "perun";
            }else if (percentChance(executioner)){
                // Executioner
                // 2.5% chance of being here
                return "executioner";
            }else if (percentChance(gamble)){
                // Gamble
                // 3.25% chance of being here
                return "gamble";
            }
        }
    }

    public static String getJewelEnchant(List<String> lore){

        for (String ench : lore){
            lore.set(lore.indexOf(ench), convertEnchant(ench.replaceAll("I", "")));
        }

        // Rare
        double billionaire = .02 * calcEnchant(lore, "billionaire");
        double perun = .02 * calcEnchant(lore, "perun");
        double executioner = .02 * calcEnchant(lore, "executioner");
        double gamble = .02 * calcEnchant(lore, "gamble");

        // Common Normal
        double combodamage = .0735 * calcEnchant(lore, "combodamage");
        double sharp = .0735 * calcEnchant(lore, "sharp");
        double kingbuster = .0735 * calcEnchant(lore, "kingbuster");
        double fancyraider = .0735 * calcEnchant(lore, "fancyraider");
        double punisher = .0735 * calcEnchant(lore, "punisher");
        double pitpocket = .0535 * calcEnchant(lore, "pitpocket");
        double berserker = .0535 * calcEnchant(lore, "berserker");
        double grasshopper = .0535 * calcEnchant(lore, "grasshopper");

        // Uncommon Normal
        double painfocus = .0525 * calcEnchant(lore, "painfocus");
        double lifesteal = .0525 * calcEnchant(lore, "lifesteal");
        double diamondstomp = .0525 * calcEnchant(lore, "diamondstomp");
        double shark = .0525 * calcEnchant(lore, "shark");
        double goldandboosted = .0625 * calcEnchant(lore, "goldandboosted");

        // Resource - Gold
        double goldbump = .0575 * calcEnchant(lore, "goldbump");
        double goldboost = .0550 * calcEnchant(lore, "goldboost");
        double moctezuma = .0525 * calcEnchant(lore, "moctezuma");

        // Resource - Sweaty
        double sweaty = .0525 * calcEnchant(lore, "sweaty");
        double xpbump = .0575 * calcEnchant(lore, "xpbump");
        double xpboost = .0575 * calcEnchant(lore, "xpboost");

        // Resource - Misc
        double pantsRadar = .0325 * calcEnchant(lore, "pantsradar");

        while (true) {
            if(percentChance(pantsRadar)){
                // Pants radar
                // 5.25% of being here
                return "pantsradar";
            }else if (percentChance(xpboost)){
                // Xp Boost
                // 5% chance of being here
                return "xpboost";
            }else if (percentChance(painfocus)){
                // Pain Focus
                // 5.25% chance of being here
                return "painfocus";
            }else if(percentChance(punisher)){
                return "punisher";
            }else if(percentChance(goldandboosted)){
                return "goldandboosted";
            }else if(percentChance(pitpocket)){
                return "pitpocket";
            }else if(percentChance(grasshopper)){
                return "grasshopper";
            }else if(percentChance(berserker)){
                return "berserker";
            }else if (percentChance(lifesteal)){
                // Lifesteal
                // 6.25% chance of being here
                return "lifesteal";
            }else if (percentChance(goldboost)){
                // Gold Boost
                // 6.5% chance of being here
                return "goldboost";
            }else if (percentChance(combodamage)){
                // Combo Damage
                // Huys is the best Pit Pvper!!!!
                // 4.35% chance of being here
                return "combodamage";
            }else if (percentChance(fancyraider)){
                // Fancy Raider
                // U Need to learn to get females. (Like huys does)
                // 5.35% chance of being here
                return "fancyraider";
            }else if (percentChance(sharp)){
                // Sharp
                // 6.75% chance of being here
                return "sharp";
            }else if (percentChance(shark)){
                // Shark
                // 7.25% chance of being here
                return "shark";
            }else if (percentChance(xpbump)){
                // Xp Bump
                // 7.50% chance of being here
                return "xpbump";
            }else if (percentChance(goldbump)){
                // Gold Bump
                // 7.75% chance of being here
                return "goldbump";
            }else if (percentChance(diamondstomp)){
                // Diamond Stomp
                // 8.25% chance of being here
                return "diamondstomp";
            }else if (percentChance(sweaty)){
                // Sweaty
                // 9.25% chance of being here
                return "sweaty";
            }else if (percentChance(moctezuma)){
                // Moctezuma
                // 10.25% chance of being here
                return "moctezuma";
            }else if (percentChance(kingbuster)){
                // King Buster
                // 11.25% chance of being here
                return "kingbuster";
            }else if (percentChance(billionaire)){
                // Billionaire
                // 1% chance of being here
                return "billionaire";
            } else if (percentChance(perun)){
                // Perun
                // 2% chance of being here
                return "perun";
            }else if (percentChance(executioner)){
                // Executioner
                // 2.5% chance of being here
                return "executioner";
            }else if (percentChance(gamble)){
                // Gamble
                // 3.25% chance of being here
                return "gamble";
            }
        }
    }


}
