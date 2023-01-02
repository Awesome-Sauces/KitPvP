package me.alpha.kitpvp.PitRemake.ItemStacks;

import de.tr7zw.nbtapi.NBTItem;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.ArrayList;
import java.util.List;

import static me.alpha.kitpvp.utils.ColorUtil.colorCode;

public class enchants {
    public static ItemStack lores;
    public static ItemStack fresh_reds;
    public static ItemStack fresh_blues;
    public static ItemStack fresh_greens;
    public static ItemStack fresh_oranges;
    public static ItemStack fresh_yellows;
    public static ItemStack fresh_sword;
    public static ItemStack fresh_dark;
    public static ItemStack vile;
    public static ItemStack jewl_pant;
    public static ItemStack jewl_sword;
    public static ItemStack gem;
    public static ItemStack malding_pants;
    public static ItemStack malding_chestplate;
    public static ItemStack malding_boots;
    public static ItemStack malding_sword;
    public static ItemStack reaper_scythe;
    public static ItemStack playerSoul;
    public static ItemStack fresh_bow;
    public static ItemStack cactus;
    public static ItemStack pantsPB;
    public static ItemStack fullPantPB;
    public static ItemStack fullSwordPB;
    public static ItemStack swordPB;
    public static ItemStack goldenhead;
    public static ItemStack firstaidfull;
    public static ItemStack firstaidempty;
    public static ItemStack kingsHelmet;
    public static ItemStack archAngel;
    public static ItemStack arma;
    public static ItemStack soup;
    public static ItemStack rod;


    public static void init(){
        createGoldenHead();
        createLores();
        createFreshReds();
        createFreshBlues();
        createFreshGreens();
        createFreshOranges();
        createFreshYellows();
        createFreshSword();
        createVile();
        createJewlPant();
        createJewlSword();
        createGem();
        createMaldingPants();
        createMaldingChestplate();
        createMaldingBoots();
        createMaldingSword();
        createReaperScythe();
        createPlayerSoul();
        createFreshBow();
        createCactus();
        createFULLPantPB();
        createPantPB();
        createSwordPB();
        createFULLSwordPB();
        createFirstAidFull();
        createFirstAidEmpty();
        createKingsHelmet();
        createArchAngel();
        createArma();
        createDarks();
        createSoup();
        createRod();
    }

    private static void createArma() {
        ItemStack item = new ItemStack(Material.LEATHER_BOOTS, 1);

        NBTItem nbtItem = new NBTItem(item);

        nbtItem.setInteger("arma", 1);

        item = nbtItem.getItem();

        LeatherArmorMeta meta = (LeatherArmorMeta) item.getItemMeta();
        meta.setColor(Color.RED);
        meta.setDisplayName(colorCode("&cArmageddon Boots"));
        List<String> lore = new ArrayList<>();
        lore.add(colorCode("&7Lives: &a5&7/5"));
        lore.add("");
        lore.add(colorCode("&9Evil Within"));
        lore.add(colorCode("&7Your hits ignore Somber"));
        lore.add("  ");
        lore.add(colorCode("&cDemon Faction Reward"));
        meta.setLore(lore);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS, ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_UNBREAKABLE);
        meta.spigot().setUnbreakable(true);
        item.setItemMeta(meta);
        arma = item;
    }

    private static void createSoup() {
        ItemStack item = new ItemStack(Material.MUSHROOM_SOUP, 1);

        NBTItem nbtItem = new NBTItem(item);

        nbtItem.setInteger("soup", 1);

        item = nbtItem.getItem();

        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(colorCode("&aTasty Soup"));
        List<String> lore = new ArrayList<>();
        lore.add(colorCode("&9Speed I (0:07)"));
        lore.add(colorCode("&a1.5\u2764 Heal &7+ &61\u2764 Absorption"));
        lore.add(colorCode("&cNext melee hit +15% damage"));
        meta.setLore(lore);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS, ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_UNBREAKABLE);
        meta.spigot().setUnbreakable(true);
        item.setItemMeta(meta);
        soup = item;
    }

    private static void createRod() {
        ItemStack item = new ItemStack(Material.FISHING_ROD, 1);

        NBTItem nbtItem = new NBTItem(item);

        nbtItem.setInteger("fishing_rod", 1);

        nbtItem.setInteger("damage_rod", 1);

        item = nbtItem.getItem();

        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(colorCode("&aLucky Rod"));
        List<String> lore = new ArrayList<>();
        lore.add(colorCode("&7Fishing Item"));
        lore.add(colorCode(" "));
        lore.add(colorCode("&9Shark Hunter III"));
        lore.add(colorCode("&7Deal &c+27% &7damage to sharks"));
        lore.add(colorCode("  "));
        lore.add(colorCode("&9+6.5 Attack Damage"));
        lore.add(colorCode("&8(Against Sea Creatures)"));
        meta.setLore(lore);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS, ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_UNBREAKABLE);
        meta.spigot().setUnbreakable(true);
        item.setItemMeta(meta);
        rod = item;
    }

    private static void createArchAngel() {
        ItemStack item = new ItemStack(Material.DIAMOND_CHESTPLATE, 1);

        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(colorCode("&bArchangel Chestplate"));
        List<String> lore = new ArrayList<>();
        lore.add(colorCode("&7Lives: &a5&7/5"));
        lore.add(" ");
        lore.add(colorCode("&9Guardian"));
        lore.add(colorCode("&7Receive &9-10% &7less damage"));
        lore.add("  ");
        lore.add(colorCode("&bAngel Faction Reward"));
        meta.setLore(lore);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS, ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_UNBREAKABLE);
        meta.spigot().setUnbreakable(true);
        item.setItemMeta(meta);
        archAngel = item;
    }

    private static void createKingsHelmet() {
        ItemStack item = new ItemStack(Material.GOLD_HELMET, 1);
        ItemMeta meta = item.getItemMeta();
        List<String> lore = new ArrayList<>();
        meta.setDisplayName(colorCode("&6Golden Helmet"));
        lore.add(colorCode("&7Lives: &a5&7/5"));
        lore.add("   ");
        lore.add(ChatColor.BLUE + "Royalty");
        lore.add(colorCode("&7Earn ") + ChatColor.AQUA + "+50 xp cap");
        lore.add(" ");
        lore.add(ChatColor.GOLD + "As strong as diamond");
        meta.setLore(lore);
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_UNBREAKABLE);
        meta.spigot().setUnbreakable(true);
        item.setItemMeta(meta);
        kingsHelmet = item;
    }

    private static void createFirstAidFull() {
        ItemStack item = new ItemStack((short)383, 1, (short) 96);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(colorCode("&cFirst-Aid Egg"));
        List<String> lore = new ArrayList<>();
        lore.add(colorCode("&7Heals &c2.5\u2764"));
        lore.add(colorCode("&75 second cooldown."));
        meta.spigot().setUnbreakable(true);
        meta.setLore(lore);
        item.setItemMeta(meta);
        firstaidfull = item;
        //Shaped Recipe
        ShapedRecipe sr = new ShapedRecipe(item);
        sr.shape("SSS", "S S", "S S");
        sr.setIngredient('S', Material.STICK);
        Bukkit.getServer().addRecipe(sr);
    }

    private static void createFirstAidEmpty() {
        ItemStack item = new ItemStack((short)383, 1, (short) 51);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(colorCode("&7First-Aid Egg"));
        List<String> lore = new ArrayList<>();
        lore.add(colorCode("&7Heals &c2.5\u2764"));
        lore.add(colorCode("&7On Cooldown!"));
        meta.spigot().setUnbreakable(true);
        meta.setLore(lore);
        item.setItemMeta(meta);
        firstaidempty = item;
        //Shaped Recipe
        ShapedRecipe sr = new ShapedRecipe(item);
        sr.shape("SSS", "S S", "S S");
        sr.setIngredient('S', Material.STICK);
        Bukkit.getServer().addRecipe(sr);
    }

    private static void createGoldenHead(){
        ItemStack skull = new ItemStack(Material.SKULL_ITEM,1, (byte) 3);
        SkullMeta skullMeta = (SkullMeta) skull.getItemMeta();

        skullMeta.setDisplayName(colorCode("&6Golden Head"));

        List<String> lore = new ArrayList<>();

        lore.add(colorCode("&9Speed I (0:08)"));
        lore.add(colorCode("&9Regeneration II (0:05)"));
        lore.add(colorCode("&63\u2764 absorption!"));
        lore.add(colorCode("&71 second between eats"));

        skullMeta.setLore(lore);

        skullMeta.setOwner("ifishdupe");
        skull.setItemMeta(skullMeta);


        goldenhead = skull;
    }

    private static void createLores() {
        ItemStack item = new ItemStack(Material.LEATHER_LEGGINGS, 1);
        LeatherArmorMeta meta = (LeatherArmorMeta) item.getItemMeta();
        meta.setColor(Color.RED);
        meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&cTier I Red Pants"));
        List<String> lore = new ArrayList<>();


        lore.add(colorCode("&9Critically Funky III"));
        lore.add(colorCode("&7Critical hits against you deal"));
        lore.add(colorCode("&950% &7of the damage they"));
        lore.add(colorCode("&7normally would and empower your"));
        lore.add(colorCode("&7next strike for &c+30%&7 damage"));

        lore.add(colorCode("&9Peroxide III"));
        lore.add(colorCode("&7Gain &cRegen II &7(8s) when hit"));

        lore.add(colorCode("&9Boo-boo III"));
        lore.add(colorCode("&7Passively regain &c1\u2764&7 every 4"));
        lore.add(colorCode("&7seconds"));

        lore.add(colorCode("&9Fractional Reserve III"));
        lore.add(colorCode("&7Recieve &9-1% damage&7 per"));
        lore.add(colorCode("&610,000g &7you have (&9-30%"));
        lore.add(colorCode("&7max)"));

        lore.add(colorCode("&9Mirror III"));
        lore.add(colorCode("&7You do not take true damage and"));
        lore.add(colorCode("&7instead reflect &e50% &7of it to"));
        lore.add(colorCode("&7your attacker"));

        lore.add(colorCode("&dRARE! &9Retro-Gravity Microcosm III"));
        lore.add(colorCode("&7When a player hits you from"));
        lore.add(colorCode("&7above ground &e3 times&7 in a row:"));
        lore.add(colorCode("&7You heal &c1.25\u2764"));
        lore.add(colorCode("&7Gain &c+1.5\u2764 &7damage vs them for 30s"));
        lore.add(colorCode("&7They take &c0.5\u2764&7 true damage"));

        lore.add(colorCode("&9Sweaty III"));
        lore.add(colorCode("&7Earn &b+60% XP&7 from streak XP"));
        lore.add(colorCode("&7bonus and &b+100 max XP&7 per kill"));

        lore.add(colorCode("&9XP Boost III"));
        lore.add(colorCode("&7Earn &b+30% XP&7 from kills"));

        lore.add(colorCode("&dRARE! &9Solitude III"));
        lore.add(colorCode("&7Recieve &9-60% &7damage when two"));
        lore.add(colorCode("&7or less players are within 7"));
        lore.add(colorCode("&7blocks"));

        lore.add(colorCode("&9Moctezuma III"));
        lore.add(colorCode("&7Earn &6+18g&7 on kill (assists"));
        lore.add(colorCode("&7excluded)"));

        lore.add(colorCode("&9Gold Bump III"));
        lore.add(colorCode("&7Earn &6+12g&7 on kill"));

        lore.add(colorCode("&9Gold Boost III"));
        lore.add(colorCode("&7Earn &6+45% gold (g)&7 from kill"));

        lore.add(colorCode("&9Protection III"));
        lore.add(colorCode("&7Recieve &9-10%&7 damage"));

        lore.add(colorCode("&9Diamond Allergy III"));
        lore.add(colorCode("&7Receive &9-30% damage from"));
        lore.add(colorCode("&7diamond weapons"));

        lore.add(colorCode("&9David and Goliath III"));
        lore.add(colorCode("&7Receive &9-40% damage from"));
        lore.add(colorCode("&7players with a bounty"));

        lore.add(colorCode("&9\"Not\" Gladiator III"));
        lore.add(colorCode("&7Receive &9-2% damage per nearby"));
        lore.add(colorCode("&7player (max 10 players)"));

        lore.add(colorCode("&9Golden Heart III"));
        lore.add(colorCode("&7Gain &6+2\u2764&7 absorption on kill"));
        lore.add(colorCode("&7(max &6\u2764&7)"));

        lore.add(colorCode("&9Spite"));
        lore.add(colorCode("&7Deal &c+20% damage&7 but receive"));
        lore.add(colorCode("&c+5% damage&7 versus players"));
        lore.add(colorCode("&7wearing leather armor"));

        lore.add(colorCode("&dRARE! &9Pit Blob III"));
        lore.add(colorCode("&7Kills respawn &aThe Blob&7. This"));
        lore.add(colorCode("&7slimy pet will follow you around"));
        lore.add(colorCode("&7and kill your enemies. &aThe Blob"));
        lore.add(colorCode("&7grows and gains health for every"));
        lore.add(colorCode("&7enemy you kill."));

        lore.add(colorCode("&dRARE! &9Escape Pod III"));
        lore.add(colorCode("&7When hit below &c2&7, launch"));
        lore.add(colorCode("&7into the air dealing &c3 &7damage"));
        lore.add(colorCode("&7to nearby enemies and gaining"));
        lore.add(colorCode("&aRegen IV&7 (30s). Can launch"));
        lore.add(colorCode("&7once per life"));

        lore.add(colorCode("&dRARE! &9Combo: Venom"));
        lore.add(colorCode("&7Every &ethird &7strike &apoisons"));
        lore.add(colorCode("&7enemies, temporarily applying"));
        lore.add(colorCode("&7Somber for &512 seconds."));
        lore.add(colorCode("&7Also &apoisons &7yourself!"));

        lore.add(colorCode("&9Misery"));
        lore.add(colorCode("&7Deal &c+0.5\u2764 &7true damage against"));
        lore.add(colorCode("&7players wearing leather pants but"));
        lore.add(colorCode("&7take &c0.3\u2764"));

        meta.spigot().setUnbreakable(true);
        meta.setLore(lore);
        item.setItemMeta(meta);
        lores = item;
        //Shaped Recipe
        ShapedRecipe sr = new ShapedRecipe(item);
        sr.shape("SSS", "SSS", "SSS");
        sr.setIngredient('S', Material.STICK);
        Bukkit.getServer().addRecipe(sr);
    }

    private static void createFreshReds() {
        ItemStack item = new ItemStack(Material.LEATHER_LEGGINGS, 1);
        LeatherArmorMeta meta = (LeatherArmorMeta) item.getItemMeta();
        meta.setColor(Color.fromRGB(255,85,85));
        meta.setDisplayName(colorCode("&cFresh Red Pants"));
        List<String> lore = new ArrayList<>();
        lore.add(colorCode("&7Kept on death"));
        lore.add("   ");
        lore.add(colorCode("&cUsed in the mystic well"));
        lore.add(colorCode("&cAlso, a fashion statement"));

        meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE, ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_ENCHANTS);
        meta.spigot().setUnbreakable(true);
        meta.setLore(lore);
        item.setItemMeta(meta);
        fresh_reds = item;
        //Shaped Recipe
        ShapedRecipe sr = new ShapedRecipe(item);
        sr.shape("S S", "S S", "S S");
        sr.setIngredient('S', Material.STICK);
        Bukkit.getServer().addRecipe(sr);
    }

    private static void createFreshBlues() {
        ItemStack item = new ItemStack(Material.LEATHER_LEGGINGS, 1);
        LeatherArmorMeta meta = (LeatherArmorMeta) item.getItemMeta();
        meta.setColor(Color.fromRGB(85, 85, 255));
        meta.setDisplayName(colorCode("&9Fresh Blue Pants"));
        List<String> lore = new ArrayList<>();
        lore.add(colorCode("&7Kept on death"));
        lore.add("   ");
        lore.add(colorCode("&9Used in the mystic well"));
        lore.add(colorCode("&9Also, a fashion statement"));

        meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE, ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_ENCHANTS);
        meta.spigot().setUnbreakable(true);
        meta.setLore(lore);
        item.setItemMeta(meta);
        fresh_blues = item;
        //Shaped Recipe
        ShapedRecipe sr = new ShapedRecipe(item);
        sr.shape("S S", "S S", "S S");
        sr.setIngredient('S', Material.STICK);
        Bukkit.getServer().addRecipe(sr);
    }

    private static void createDarks() {
        ItemStack item = new ItemStack(Material.LEATHER_LEGGINGS, 1);

        NBTItem nbtItem = new NBTItem(item);

        nbtItem.setInteger("darkPant", 1);

        item = nbtItem.getItem();

        LeatherArmorMeta meta = (LeatherArmorMeta) item.getItemMeta();
        meta.setColor(Color.fromRGB(0, 0, 0));
        meta.setDisplayName(colorCode("&5Fresh Dark Pants"));
        List<String> lore = new ArrayList<>();
        lore.add(colorCode("&7Kept on death"));
        lore.add("   ");
        lore.add(colorCode("&5Enchants require heresy"));
        lore.add(colorCode("&5Tier I bypasses mysticism"));
        lore.add(colorCode("&5Edgy fashion"));

        meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE, ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_ENCHANTS);
        meta.spigot().setUnbreakable(true);
        meta.setLore(lore);
        item.setItemMeta(meta);

        fresh_dark = item;
        //Shaped Recipe
        ShapedRecipe sr = new ShapedRecipe(item);
        sr.shape("S S", "S S", "S S");
        sr.setIngredient('S', Material.STICK);
        Bukkit.getServer().addRecipe(sr);
    }

    private static void createFreshOranges() {
        ItemStack item = new ItemStack(Material.LEATHER_LEGGINGS, 1);
        LeatherArmorMeta meta = (LeatherArmorMeta) item.getItemMeta();
        meta.setColor(Color.ORANGE);
        meta.setDisplayName(colorCode("&6Fresh Orange Pants"));
        List<String> lore = new ArrayList<>();
        lore.add(colorCode("&7Kept on death"));
        lore.add("   ");
        lore.add(colorCode("&6Used in the mystic well"));
        lore.add(colorCode("&6Also, a fashion statement"));

        meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE, ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_ENCHANTS);
        meta.spigot().setUnbreakable(true);
        meta.setLore(lore);
        item.setItemMeta(meta);
        fresh_oranges = item;
        //Shaped Recipe
        ShapedRecipe sr = new ShapedRecipe(item);
        sr.shape("S S", "S S", "S S");
        sr.setIngredient('S', Material.STICK);
        Bukkit.getServer().addRecipe(sr);
    }

    private static void createFreshGreens() {
        ItemStack item = new ItemStack(Material.LEATHER_LEGGINGS, 1);
        LeatherArmorMeta meta = (LeatherArmorMeta) item.getItemMeta();
        meta.setColor(Color.LIME);
        meta.setDisplayName(colorCode("&aFresh Green Pants"));
        List<String> lore = new ArrayList<>();
        lore.add(colorCode("&7Kept on death"));
        lore.add("   ");
        lore.add(colorCode("&aUsed in the mystic well"));
        lore.add(colorCode("&aAlso, a fashion statement"));

        meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE, ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_ENCHANTS);
        meta.spigot().setUnbreakable(true);
        meta.setLore(lore);
        item.setItemMeta(meta);
        fresh_greens = item;
        //Shaped Recipe
        ShapedRecipe sr = new ShapedRecipe(item);
        sr.shape("S S", "S S", "S S");
        sr.setIngredient('S', Material.STICK);
        Bukkit.getServer().addRecipe(sr);
    }

    private static void createFreshYellows() {
        ItemStack item = new ItemStack(Material.LEATHER_LEGGINGS, 1);
        LeatherArmorMeta meta = (LeatherArmorMeta) item.getItemMeta();
        meta.setColor(Color.YELLOW);
        meta.setDisplayName(colorCode("&eFresh Yellow Pants"));
        List<String> lore = new ArrayList<>();
        lore.add(colorCode("&7Kept on death"));
        lore.add("   ");
        lore.add(colorCode("&eUsed in the mystic well"));
        lore.add(colorCode("&eAlso, a fashion statement"));

        meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE, ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_ENCHANTS);
        meta.spigot().setUnbreakable(true);
        meta.setLore(lore);
        item.setItemMeta(meta);
        fresh_yellows = item;
        //Shaped Recipe
        ShapedRecipe sr = new ShapedRecipe(item);
        sr.shape("S S", "S S", "S S");
        sr.setIngredient('S', Material.STICK);
        Bukkit.getServer().addRecipe(sr);
    }

    private static void createCactus() {
        ItemStack item = new ItemStack(Material.CACTUS, 1);

        NBTItem nbtItem = new NBTItem(item);

        nbtItem.setInteger("cactus", 1);

        item = nbtItem.getItem();

        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.translateAlternateColorCodes('&',
                "&aPhilosopher's Cactus"));
        List<String> lore = new ArrayList<>();
        lore.add(ChatColor.translateAlternateColorCodes('&',
                "&eSpecial Item"));
        lore.add(ChatColor.translateAlternateColorCodes('&',
                "&7Right-click while holding"));
        lore.add(ChatColor.translateAlternateColorCodes('&',
                "&7this item to summon fresh"));
        lore.add(ChatColor.translateAlternateColorCodes('&',
                "&cP&6a&en&at&9s &7of your"));
        lore.add(ChatColor.translateAlternateColorCodes('&',
                "&7choice."));
        lore.add("   ");
        lore.add(ChatColor.translateAlternateColorCodes('&',
                "&7(Special pants excluded)"));

        meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE, ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_ENCHANTS);
        meta.spigot().setUnbreakable(true);
        meta.setLore(lore);
        item.setItemMeta(meta);
        cactus = item;
        //Shaped Recipe
        ShapedRecipe sr = new ShapedRecipe(item);
        sr.shape("SSS", "SSS", "SSS");
        sr.setIngredient('S', Material.CACTUS);
        Bukkit.getServer().addRecipe(sr);
    }

    private static void createPantPB() {
        ItemStack item = new ItemStack(Material.MINECART, 1);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.translateAlternateColorCodes('&',
                "&bPants Bundle"));
        List<String> lore = new ArrayList<>();
        lore.add(ChatColor.translateAlternateColorCodes('&',
                "&7Kept on death"));
        lore.add(ChatColor.translateAlternateColorCodes('&',
                "&7Hold and right-click to store 10"));
        lore.add(ChatColor.translateAlternateColorCodes('&',
                "&7fresh pair of pants."));

        meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE, ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_ENCHANTS);
        meta.spigot().setUnbreakable(true);
        meta.setLore(lore);
        item.setItemMeta(meta);
        pantsPB = item;
        //Shaped Recipe
        ShapedRecipe sr = new ShapedRecipe(item);
        sr.shape("SSS", "SSS", "SSS");
        sr.setIngredient('S', Material.MINECART);
        Bukkit.getServer().addRecipe(sr);
    }

    private static void createFULLPantPB() {
        ItemStack item = new ItemStack(Material.STORAGE_MINECART, 1);

        NBTItem nbtItem = new NBTItem(item);

        nbtItem.setInteger("pb", 1);

        item = nbtItem.getItem();

        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.translateAlternateColorCodes('&',
                "&bPants Bundle"));
        List<String> lore = new ArrayList<>();
        lore.add(ChatColor.translateAlternateColorCodes('&',
                "&7Kept on death"));
        lore.add(ChatColor.translateAlternateColorCodes('&',
                "&7Contents:"));
        lore.add(ChatColor.translateAlternateColorCodes('&',
                "&f10x &cRed"));
        lore.add(ChatColor.translateAlternateColorCodes('&',
                " "));
        lore.add(ChatColor.translateAlternateColorCodes('&',
                "&7Hold and right-click to open!"));

        meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE, ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_ENCHANTS);
        meta.spigot().setUnbreakable(true);
        meta.setLore(lore);
        item.setItemMeta(meta);
        fullPantPB = item;
        //Shaped Recipe
        ShapedRecipe sr = new ShapedRecipe(item);
        sr.shape("SSS", "SSS", "SSS");
        sr.setIngredient('S', Material.MINECART);
        Bukkit.getServer().addRecipe(sr);
    }

    private static void createSwordPB() {
        ItemStack item = new ItemStack(Material.MINECART, 1);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.translateAlternateColorCodes('&',
                "&bSword Bundle"));
        List<String> lore = new ArrayList<>();
        lore.add(ChatColor.translateAlternateColorCodes('&',
                "&7Kept on death"));
        lore.add(ChatColor.translateAlternateColorCodes('&',
                "&7Hold and right-click to store 10"));
        lore.add(ChatColor.translateAlternateColorCodes('&',
                "&7mystic swords."));

        meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE, ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_ENCHANTS);
        meta.spigot().setUnbreakable(true);
        meta.setLore(lore);
        item.setItemMeta(meta);
        swordPB = item;
        //Shaped Recipe
        ShapedRecipe sr = new ShapedRecipe(item);
        sr.shape("SSS", "SSS", "SSS");
        sr.setIngredient('S', Material.MINECART);
        Bukkit.getServer().addRecipe(sr);
    }

    private static void createFULLSwordPB() {
        ItemStack item = new ItemStack(Material.STORAGE_MINECART, 1);

        NBTItem nbtItem = new NBTItem(item);

        nbtItem.setInteger("sb", 1);

        item = nbtItem.getItem();

        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.translateAlternateColorCodes('&',
                "&bSword Bundle"));
        List<String> lore = new ArrayList<>();
        lore.add(ChatColor.translateAlternateColorCodes('&',
                "&7Kept on death"));
        lore.add(ChatColor.translateAlternateColorCodes('&',
                "&7Contents:"));
        lore.add(ChatColor.translateAlternateColorCodes('&',
                "&f10x &cMystic Sword"));
        lore.add(ChatColor.translateAlternateColorCodes('&',
                " "));
        lore.add(ChatColor.translateAlternateColorCodes('&',
                "&7Hold and right-click to open!"));

        meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE, ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_ENCHANTS);
        meta.spigot().setUnbreakable(true);
        meta.setLore(lore);
        item.setItemMeta(meta);
        fullSwordPB = item;
        //Shaped Recipe
        ShapedRecipe sr = new ShapedRecipe(item);
        sr.shape("SSS", "SSS", "SSS");
        sr.setIngredient('S', Material.MINECART);
        Bukkit.getServer().addRecipe(sr);
    }


    private static void createMaldingChestplate() {
        ItemStack item = new ItemStack(Material.LEATHER_CHESTPLATE, 1);
        LeatherArmorMeta meta = (LeatherArmorMeta) item.getItemMeta();
        meta.setColor(Color.fromRGB(145, 49, 49));
        meta.setDisplayName(colorCode("&cTier I Chestplate"));
        List<String> lore = new ArrayList<>();

        lore.add(ChatColor.translateAlternateColorCodes('&', "&7Lives: &a5&7/5"));
        lore.add("    ");
        lore.add(colorCode("&dRARE! &9Flaming"));
        lore.add(colorCode("&7If the final damage of your strike"));
        lore.add(colorCode("&7deals less than &c1.5\u2764&7 &7damage,"));
        lore.add(colorCode("&7ignite opponent in &a0.1s &7for &c75%"));
        lore.add(colorCode("&7damage"));

        meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE, ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_ENCHANTS);
        meta.spigot().setUnbreakable(true);
        meta.setLore(lore);
        meta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 4, false);
        item.setItemMeta(meta);
        malding_chestplate = item;
        //Shaped Recipe
        ShapedRecipe sr = new ShapedRecipe(item);
        sr.shape("S S", "S S", "S S");
        sr.setIngredient('S', Material.STICK);
        Bukkit.getServer().addRecipe(sr);
    }

    private static void createMaldingBoots() {
        ItemStack item = new ItemStack(Material.LEATHER_BOOTS, 1);
        LeatherArmorMeta meta = (LeatherArmorMeta) item.getItemMeta();
        meta.setColor(Color.fromRGB(145, 49, 49));
        meta.setDisplayName(colorCode("&cTier I Boots"));
        List<String> lore = new ArrayList<>();

        lore.add(ChatColor.translateAlternateColorCodes('&', "&7Lives: &a5&7/5"));
        lore.add("    ");
        lore.add(colorCode("&dRARE! &9Glamorous"));
        lore.add(colorCode("&7Leave a fancy trail behind you"));

        meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE, ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_ENCHANTS);
        meta.spigot().setUnbreakable(true);
        meta.setLore(lore);
        meta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 4, false);
        item.setItemMeta(meta);
        malding_boots = item;
        //Shaped Recipe
        ShapedRecipe sr = new ShapedRecipe(item);
        sr.shape("S S", "S S", "S S");
        sr.setIngredient('S', Material.STICK);
        Bukkit.getServer().addRecipe(sr);
    }

    private static void createMaldingPants() {
        ItemStack item = new ItemStack(Material.LEATHER_LEGGINGS, 1);
        LeatherArmorMeta meta = (LeatherArmorMeta) item.getItemMeta();
        meta.setColor(Color.fromRGB(145, 49, 49));
        meta.setDisplayName(colorCode("&cTier III Red Pants"));
        List<String> lore = new ArrayList<>();

        lore.add(ChatColor.translateAlternateColorCodes('&', "&7Lives: &a5&7/5"));
        lore.add("    ");

        meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE, ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_ENCHANTS);
        meta.spigot().setUnbreakable(true);
        meta.setLore(lore);
        meta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 1, false);
        item.setItemMeta(meta);
        malding_pants = item;
        //Shaped Recipe
        ShapedRecipe sr = new ShapedRecipe(item);
        sr.shape("S S", "S S", "S S");
        sr.setIngredient('S', Material.STICK);
        Bukkit.getServer().addRecipe(sr);
    }

    private static void createMaldingSword() {
        ItemStack item = new ItemStack(Material.WOOD_SWORD, 1);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(colorCode("&cTier I Sword"));
        List<String> lore = new ArrayList<>();

        lore.add(ChatColor.translateAlternateColorCodes('&', "&7Lives: &a5&7/5"));
        lore.add("    ");

        meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE, ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_ENCHANTS);
        meta.spigot().setUnbreakable(true);
        meta.setLore(lore);
        item.setItemMeta(meta);
        malding_sword = item;
        //Shaped Recipe
        ShapedRecipe sr = new ShapedRecipe(item);
        sr.shape("S S", "S S", "S S");
        sr.setIngredient('S', Material.STICK);
        Bukkit.getServer().addRecipe(sr);
    }

    private static void createReaperScythe() {
        ItemStack item = new ItemStack(Material.DIAMOND_HOE, 1);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(colorCode("&3Reaper Scythe"));
        List<String> lore = new ArrayList<>();

        lore.add(colorCode("&7Lives: &aNONE"));
        lore.add("    ");
        lore.add(colorCode("&dRARE! &9Harvest! III"));
        lore.add(colorCode("&d5% chance &7to get a"));
        lore.add(colorCode("&3Player Soul &7on kill!"));

        meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE, ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_ENCHANTS);
        meta.addEnchant(Enchantment.DAMAGE_ALL, 5, false);
        meta.spigot().setUnbreakable(true);
        meta.setLore(lore);
        item.setItemMeta(meta);
        reaper_scythe = item;
        //Shaped Recipe
        ShapedRecipe sr = new ShapedRecipe(item);
        sr.shape("S S", "S S", "S S");
        sr.setIngredient('S', Material.DIAMOND_BLOCK);
        Bukkit.getServer().addRecipe(sr);
    }

    private static void createFreshSword() {
        ItemStack item = new ItemStack(Material.GOLD_SWORD, 1);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(colorCode("&eMystic Sword"));
        List<String> lore = new ArrayList<>();
        lore.add(colorCode("&7Kept on death"));
        lore.add("");
        lore.add(colorCode("&7Used in the mystic well"));
        meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE, ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_ENCHANTS);
        meta.spigot().setUnbreakable(true);
        meta.setLore(lore);
        item.setItemMeta(meta);
        fresh_sword = item;
        //Shaped Recipe
        ShapedRecipe sr = new ShapedRecipe(item);
        sr.shape("SSS", "S S", "S S");
        sr.setIngredient('S', Material.STICK);
        Bukkit.getServer().addRecipe(sr);
    }

    private static void createFreshBow() {
        ItemStack item = new ItemStack(Material.BOW, 1);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(colorCode("&bMystic Bow"));
        List<String> lore = new ArrayList<>();
        lore.add(colorCode("&7Kept on death"));
        lore.add("");
        lore.add(colorCode("&7Used in the mystic well"));
        meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE, ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_ENCHANTS);
        meta.spigot().setUnbreakable(true);
        meta.addEnchant(Enchantment.ARROW_INFINITE, 1, false);
        meta.setLore(lore);
        item.setItemMeta(meta);
        fresh_bow = item;
        //Shaped Recipe
        ShapedRecipe sr = new ShapedRecipe(item);
        sr.shape("SSS", "SSS", "SSS");
        sr.setIngredient('S', Material.GHAST_TEAR);
        Bukkit.getServer().addRecipe(sr);
    }

    private static void createPlayerSoul() {
        ItemStack item = new ItemStack(Material.GHAST_TEAR, 1);

        NBTItem nbtItem = new NBTItem(item);

        nbtItem.setInteger("soul", 1);

        item = nbtItem.getItem();

        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(colorCode("&3Player Soul"));
        List<String> lore = new ArrayList<>();
        lore.add(colorCode("&7Kept on death"));
        lore.add("");
        lore.add(colorCode("&7Used to summon the &cMalding"));
        meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE, ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_ENCHANTS);
        meta.spigot().setUnbreakable(true);
        meta.setLore(lore);
        item.setItemMeta(meta);
        playerSoul = item;
        //Shaped Recipe
        ShapedRecipe sr = new ShapedRecipe(item);
        sr.shape("SSS", "SSS", "SSS");
        sr.setIngredient('S', Material.GHAST_TEAR);
        Bukkit.getServer().addRecipe(sr);
    }


    private static void createVile() {
        ItemStack item = new ItemStack(Material.COAL, 1);

        NBTItem nbtItem = new NBTItem(item);

        nbtItem.setInteger("vile", 1);

        item = nbtItem.getItem();

        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(colorCode("&5Chunk of Vile"));
        List<String> lore = new ArrayList<>();
        lore.add(colorCode("&7Kept on death"));
        lore.add("");
        lore.add(colorCode("&cHeretic artifact"));
        meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE, ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_ENCHANTS);
        meta.spigot().setUnbreakable(true);
        meta.setLore(lore);
        item.setItemMeta(meta);
        vile = item;
        //Shaped Recipe
        ShapedRecipe sr = new ShapedRecipe(item);
        sr.shape("SSS", "SSS", "SSS");
        sr.setIngredient('S', Material.COAL);
        Bukkit.getServer().addRecipe(sr);
    }

    private static void createJewlPant() {
        ItemStack item = new ItemStack(Material.LEATHER_LEGGINGS, 1);
        LeatherArmorMeta meta = (LeatherArmorMeta) item.getItemMeta();
        meta.setColor(Color.fromRGB(125,195,131));
        meta.setDisplayName(colorCode("&3Tier I Sewer Pants"));
        List<String> lore = new ArrayList<>();
        lore.add(ChatColor.translateAlternateColorCodes('&', "&7Lives: &a5&7/5"));
        lore.add("        ");
        lore.add(colorCode("&9Hidden Jewel"));
        lore.add(colorCode("&7Kill &c42&7 players to recycle"));
        lore.add(colorCode("&7into Tier I pants with a Tier III"));
        lore.add(colorCode("&7enchant."));
        lore.add("          ");
        lore.add(colorCode("&3As strong as iron"));

        meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE, ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_ENCHANTS);
        meta.spigot().setUnbreakable(true);
        meta.setLore(lore);
        item.setItemMeta(meta);
        jewl_pant = item;
        //Shaped Recipe
        ShapedRecipe sr = new ShapedRecipe(item);
        sr.shape("S S", "S S", "S S");
        sr.setIngredient('S', Material.STONE);
        Bukkit.getServer().addRecipe(sr);
    }

    private static void createJewlSword() {
        ItemStack item = new ItemStack(Material.GOLD_SWORD, 1);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(colorCode("&cTier I Sword"));
        List<String> lore = new ArrayList<>();
        lore.add(ChatColor.translateAlternateColorCodes('&', "&7Lives: &a5&7/5"));
        lore.add("    ");
        lore.add(colorCode("&9Hidden Jewel"));
        lore.add(colorCode("&7Kill &c42&7 players to recycle"));
        lore.add(colorCode("&7into Tier I sword with a Tier III"));
        lore.add(colorCode("&7enchant."));

        meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE, ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_ENCHANTS);
        meta.addEnchant(Enchantment.DAMAGE_ALL, 3, false);
        meta.spigot().setUnbreakable(true);
        meta.setLore(lore);
        item.setItemMeta(meta);
        jewl_sword = item;
        //Shaped Recipe
        ShapedRecipe sr = new ShapedRecipe(item);
        sr.shape("S S", "S S", "S S");
        sr.setIngredient('S', Material.DIAMOND_BLOCK);
        Bukkit.getServer().addRecipe(sr);
    }

    private static void createGem() {
        ItemStack item = new ItemStack(Material.EMERALD, 1);

        NBTItem nbtItem = new NBTItem(item);

        nbtItem.setInteger("gem", 1);

        item = nbtItem.getItem();

        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(colorCode("&aTotally Legit Gem"));
        List<String> lore = new ArrayList<>();
        lore.add(colorCode("&7Kept on death"));
        lore.add(colorCode("&7Adds &d1 tier&7 to a mystic enchant."));
        lore.add(colorCode("&8Once per item!"));
        lore.add("");
        lore.add(colorCode("&eHold and right-click to use!"));
        meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE, ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_ENCHANTS);
        meta.spigot().setUnbreakable(true);
        meta.setLore(lore);
        item.setItemMeta(meta);
        gem = item;
        //Shaped Recipe
        ShapedRecipe sr = new ShapedRecipe(item);
        sr.shape("SSS", "SSS", "SSS");
        sr.setIngredient('S', Material.EMERALD_BLOCK);
        Bukkit.getServer().addRecipe(sr);
    }
}
