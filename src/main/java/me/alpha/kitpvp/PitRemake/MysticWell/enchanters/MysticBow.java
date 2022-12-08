package me.alpha.kitpvp.PitRemake.MysticWell.enchanters;

import com.alpha.redux.apis.Sounds;
import com.alpha.redux.events.boards;
import com.alpha.redux.redux;
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

import static com.alpha.redux.apis.chatManager.rank.colorCode;
import static com.alpha.redux.events.boards.integerToRoman;
import static com.alpha.redux.playerdata.economy.getEconomy;
import static com.alpha.redux.playerdata.economy.removeEconomy;
import static com.alpha.redux.well.loreChecker.CheckEnchantOnBow;
import static com.alpha.redux.well.mysticWell.*;

public class MysticBow {

    private static boolean removeGold(Player player, String uuid, int amount){
        if(getEconomy(uuid) > amount-1){
            removeEconomy(uuid, amount);
            boards.CreateScore(player);
            return true;
        }else{
            player.sendMessage(colorCode("&l&cERROR! &7You need &6" + (amount-getEconomy(uuid)) + "g &7to afford this!"));
            return false;
        }
    }

    public static void clickBow(InventoryClickEvent event){
        event.setCancelled(true);

        String uuid = String.valueOf(event.getWhoClicked().getUniqueId());
        Player player = (Player) event.getWhoClicked();
        ItemStack items = event.getClickedInventory().getItem(20);

        if (items.getItemMeta().getDisplayName().contains("Tier III")){
            player.sendMessage(ChatColor.RED + "This sword is already max tier!");
            return;
        } else if (items.getItemMeta().getDisplayName().contains("Tier II") && removeGold(player, uuid, 8000)) {
            Sounds.BUTTON.play(player);
            Sounds.PIN_DOWN.play(player);
            event.getClickedInventory().setItem(20, createBow(player, 3, event.getClickedInventory().getItem(20)));
        }else if (!items.getItemMeta().getDisplayName().contains("Tier II") &&
                items.getItemMeta().getDisplayName().contains("Tier I") && removeGold(player, uuid, 4000)) {
            Sounds.BUTTON.play(player);
            Sounds.PIN_DOWN.play(player);
            event.getClickedInventory().setItem(20, createBow(player, 2, event.getClickedInventory().getItem(20)));
        } else if (items.getItemMeta().getDisplayName().contains("Mystic Bow") && removeGold(player, uuid, 1000)) {
            Sounds.BUTTON.play(player);
            Sounds.PIN_DOWN.play(player);
            event.getClickedInventory().setItem(20, createBow(player, 1, null));
        }


    }

    public static ItemStack createBow(Player player, int tier, ItemStack bow){
        if (bow == null){
            ItemStack item = new ItemStack(Material.BOW, 1);
            ItemMeta meta = item.getItemMeta();
            meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&cTier " + integerToRoman(tier) + " Super Bow"));
            item.addEnchantment(Enchantment.ARROW_INFINITE, 1);
            meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE, ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_ENCHANTS);
            meta.spigot().setUnbreakable(true);
            meta.setLore(enchantSword(player, bow, tier));
            item.setItemMeta(meta);
            //Shaped Recipe

            return item;
        }else{
            ItemMeta meta = bow.getItemMeta();
            meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&cTier " + integerToRoman(tier) + " Super Bow"));
            bow.addEnchantment(Enchantment.ARROW_INFINITE, 1);
            meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE, ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_ENCHANTS);
            meta.spigot().setUnbreakable(true);
            //enchantSword(player, bow, tier)
            meta.setLore(enchantSword(player, bow, tier));
            bow.setItemMeta(meta);
            //Shaped Recipe

            return bow;
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

    public static List<String> enchantSword(Player player, ItemStack bow, int tier) {

        double chanceII = 0.0001;
        double chanceIII = 0.0001;

        List<String> enchants = new ArrayList<>();

        try{

            if (bow != null){enchants = CheckEnchantOnBow(bow.getItemMeta().getLore());}

        }catch (Exception ignored){}



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

        while (looping){
            loopnum++;
            ench = getEnchant(enchants);


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
        T = bow tokens

                E = enchant tokens

                C = chance

        Equation:

        > c = ((T/E)*10) / 100

         */

        lore.add(ChatColor.BLUE + "+6.5 Attack Damage");



        return lore;

        // return new EnchantingMechanics(lore, enchants.get(0), chanceIII, chanceII, "SWORD").getLore();

    }

    public static List<String> renewEnchant(List<String> lore, List<String> enchant){

        List<String> enchants = CheckEnchantOnBow(lore);

        // Bukkit.broadcastMessage(enchants.toString());

        lore = new ArrayList<>();
        lore.add(ChatColor.translateAlternateColorCodes('&', "&7Lives: &a5&7/5"));

        lore.add(" ");

        for (String bench : enchants){
            int ether = bench.length() - bench.replaceAll("I", "").length();

            lore.addAll(Arrays.asList(enchantTier(convertEnchant(bench.replaceAll("I", "")), ether).split("\n")));
            //lore.add(" ");
        }

        //hoverText(ChatColor.translateAlternateColorCodes('&', "&d&lRARE! " + rank.getNameColor(player) + player.getDisplayName() + "&7 created &cTier I Sword!"), compileListToString(lore));
        //player.playSound(player.getLocation(), Sound.ENDERDRAGON_GROWL, 1, 1);

        lore.addAll(enchant);

        return lore;
    }

    public static String getEnchantTitle(String enchant, int tier){
        if (Objects.equals(enchant, "megalongbow")) {
            return redux.megaLongBowLore.title(tier);
        }else if (Objects.equals(enchant, "volley")) {
            return redux.volleyLore.title(tier);
        }else if (Objects.equals(enchant, "telebow")) {
            return redux.telebowLore.title(tier);
        }else if (Objects.equals(enchant, "sprintdrain")) {
            return redux.sprintDrainLore.title(tier);
        }else if (Objects.equals(enchant, "fasterthantheirshadow")) {
            return redux.fasterThenTheirShadowLore.title(tier);
        }else if (Objects.equals(enchant, "pullbow")) {
            return redux.pullBowLore.title(tier);
        }else if (Objects.equals(enchant, "explosive")) {
            return redux.explosiveLore.title(tier);
        }else if (Objects.equals(enchant, "moctezuma")){
            return colorCode(redux.moctezumaLore.title(tier));
        }else if (Objects.equals(enchant, "goldbump")){
            return colorCode(redux.goldbumpLore.title(tier));
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
        if (Objects.equals(enchant, "mlb")){
            return "megalongbow";
        }else if (Objects.equals(enchant, "tele")){
            return "telebow";
        }else if (Objects.equals(enchant, "v")){
            return "volley";
        }else if (Objects.equals(enchant, "ex")){
            return "explosive";
        }else if (Objects.equals(enchant, "pull")){
            return "pullbow";
        }else if (Objects.equals(enchant, "ftts")){
            return "fasterthantheirshadow";
        }else if (Objects.equals(enchant, "sprint")){
            return "sprintdrain";
        }else if (Objects.equals(enchant, "moct")){
            return "moctezuma";
        }else if (Objects.equals(enchant, "goldBump")){
            return "goldbump";
        }else if (Objects.equals(enchant, "xp")){
            return "xpboost";
        }else if (Objects.equals(enchant, "xpb")){
            return "xpbump";
        }else if (Objects.equals(enchant, "gb")){
            return "goldboost";
        }else{
            return enchant;
        }
    }

    public static String enchantTier(String enchant, int tier){
        if (Objects.equals(enchant, "megalongbow")) {
            return redux.megaLongBowLore.lore(tier);
        }else if (Objects.equals(enchant, "volley")) {
            return redux.volleyLore.lore(tier);
        }else if (Objects.equals(enchant, "telebow")) {
            return redux.telebowLore.lore(tier);
        }else if (Objects.equals(enchant, "sprintdrain")) {
            return redux.sprintDrainLore.lore(tier);
        }else if (Objects.equals(enchant, "fasterthantheirshadow")) {
            return redux.fasterThenTheirShadowLore.lore(tier);
        }else if (Objects.equals(enchant, "pullbow")) {
            return redux.pullBowLore.lore(tier);
        }else if (Objects.equals(enchant, "explosive")) {
            return redux.explosiveLore.lore(tier);
        }else if (Objects.equals(enchant, "moctezuma")){
            return redux.moctezumaLore.lore(tier);
        }else if (Objects.equals(enchant, "goldbump")){
            return redux.goldbumpLore.lore(tier);
        }else if (Objects.equals(enchant, "goldboost")){
            return redux.goldboostLore.lore(tier);
        }else if (Objects.equals(enchant, "sweaty")){
            return redux.sweatyLore.lore(tier);
        }else if (Objects.equals(enchant, "xpbump")){
            return redux.xpbumpLore.lore(tier);
        }else if (Objects.equals(enchant, "xpboost")){
            return redux.xpboostLore.lore(tier);
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

        // Rare
        double megalongbow = .0125 * calcEnchant(lore, "megalongbow");
        double volley = .01 * calcEnchant(lore, "volley");
        double telebow = .0125 * calcEnchant(lore, "telebow");
        double pullbow = .0125 * calcEnchant(lore, "pullbow");
        double explosive = .0125 * calcEnchant(lore, "explosive");

        // Common Normal

        // Uncommon Normal
        double sprintdrain = .0425 * calcEnchant(lore, "sprintdrain");
        double fasterthantheirshadow = .0425 * calcEnchant(lore, "fasterthantheirshadow");

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
            if (percentChance(megalongbow)){
                // Billionaire
                // 1% chance of being here
                return "megalongbow";
            } else if (percentChance(volley)){
                // Perun
                // 2% chance of being here
                return "volley";
            }else if (percentChance(telebow)){
                // Executioner
                // 2.5% chance of being here
                return "telebow";
                // Gamble
            }else if (percentChance(sprintdrain)){
                // 3.25% chance of being here
                return "sprintdrain";
            }else if (percentChance(fasterthantheirshadow)){
                // Xp Boost
                // 5% chance of being here
                return "fasterthantheirshadow";
            }/*else if (percentChance(pullbow)){
                // Pain Focus
                // 5.25% chance of being here
                return "pullbow";
            }*/else if (percentChance(explosive)){
                // Lifesteal
                // 6.25% chance of being here
                return "explosive";
            }else if (percentChance(sweaty)){
                // Sweaty
                // 9.25% chance of being here
                return "sweaty";
            }else if (percentChance(moctezuma)){
                // Moctezuma
                // 10.25% chance of being here
                return "moctezuma";
            }else if (percentChance(xpbump)){
                // Xp Bump
                // 7.50% chance of being here
                return "xpbump";
            }else if (percentChance(goldbump)){
                // Gold Bump
                // 7.75% chance of being here
                return "goldbump";
            }else if (percentChance(goldboost)){
                // Gold Boost
                // 6.5% chance of being here
                return "goldboost";
            }else if (percentChance(xpboost)){
                // Xp Boost
                // 5% chance of being here
                return "xpboost";
            }
        }
    }


}
