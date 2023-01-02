package me.alpha.kitpvp.PitRemake.PitCommands.Crates;

import me.alpha.kitpvp.ChatManager.RankColor;
import me.alpha.kitpvp.PitRemake.ItemStacks.enchants;
import me.alpha.kitpvp.PitRemake.PitCommands.Stash.StashCore;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class crate {

    String crate;
    String broadcast;
    Player player;
    CrateItems items;

    public crate(String crate, String playerName){
        this.crate = crate;
        this.player = Bukkit.getPlayer(playerName);
        this.items = new CrateItems();

        run();

    }

    public void broadcastMessage(){
        Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', broadcast));
    }

    private void run(){
        
        Inventory inventory = player.getInventory();

        if(this.crate.equals("HJP")){
            this.broadcast = RankColor.getNameColor(player) + ChatColor.stripColor(player.getDisplayName()) + " &7has won &e&l3x Hidden Jewel Pants!";

            ItemStack pants = items.getJewelPant();


            StashCore.safeGiveMultiple(player, pants, 3);

        }else if(this.crate.equals("HJS")){
            this.broadcast = RankColor.getNameColor(player) + ChatColor.stripColor(player.getDisplayName()) + " &7has won &e&l3x Hidden Jewel Swords!";

            ItemStack sword = items.getJewelSword();


            StashCore.safeGiveMultiple(player, sword, 3);

        }else if(this.crate.equals("GEM")){
            this.broadcast = RankColor.getNameColor(player) + ChatColor.stripColor(player.getDisplayName()) + " &7has won &e&l1x Totally Legit Gem!";

            StashCore.safeGiveMultiple(player, items.getGem(), 1);

        }else if(this.crate.equals("BLOB")){
            this.broadcast = RankColor.getNameColor(player) + ChatColor.stripColor(player.getDisplayName()) + " &7has won &e&l1x Pit Blob III!";

            ItemStack blob = items.getPitBlob();

            StashCore.safeGiveMultiple(player, blob, 1);

        }else if(this.crate.equals("PPP")){
            this.broadcast = RankColor.getNameColor(player) + ChatColor.stripColor(player.getDisplayName()) + " &7has won &e&l16x Philosopher's Cactus!";

            StashCore.safeGiveMultiple(player, enchants.cactus, 16);

        }else if(this.crate.equals("PPH")){
            this.broadcast = RankColor.getNameColor(player) + ChatColor.stripColor(player.getDisplayName()) + " &7has won &e&l1x Protection II Diamond Helmet!";
            ItemStack helmet = CrateItems.getDiamondHelmet();
            StashCore.safeGiveMultiple(player, helmet, 1);
        }else if(this.crate.equals("PPC")){
            this.broadcast = RankColor.getNameColor(player) + ChatColor.stripColor(player.getDisplayName()) + " &7has won &e&l1x Protection II Diamond Chestplate!";
            ItemStack chestplate = CrateItems.getDiamondChestplate();
            StashCore.safeGiveMultiple(player, chestplate, 1);
        }else if(this.crate.equals("PPL")){
            this.broadcast = RankColor.getNameColor(player) + ChatColor.stripColor(player.getDisplayName()) + " &7has won &e&l1x Protection II Diamond Leggings!";
            ItemStack leggings = CrateItems.getDiamondLeggings();
            StashCore.safeGiveMultiple(player, leggings, 1);
        }else if(this.crate.equals("PPB")){
            this.broadcast = RankColor.getNameColor(player) + ChatColor.stripColor(player.getDisplayName()) + " &7has won &e&l1x Protection II Diamond Boots!";
            ItemStack boots = CrateItems.getDiamondBoots();
            StashCore.safeGiveMultiple(player, boots, 1);
        }else if(this.crate.equals("FSIX")){
            this.broadcast = RankColor.getNameColor(player) + ChatColor.stripColor(player.getDisplayName()) + " &7has won &e&l16x Funky Feathers!";
            ItemStack feather = items.getFeather();
            StashCore.safeGiveMultiple(player, feather, 16);
        }else if(this.crate.equals("FEIGHT")){
            this.broadcast = RankColor.getNameColor(player) + ChatColor.stripColor(player.getDisplayName()) + " &7has won &e&l8x Funky Feathers!";
            ItemStack feather = items.getFeather();
            StashCore.safeGiveMultiple(player, feather, 8);
        }else if(this.crate.equals("VSTACK")){
            this.broadcast = RankColor.getNameColor(player) + ChatColor.stripColor(player.getDisplayName()) + " &7has won &e&l64x Vile!";
            ItemStack vile = items.getVile();
            StashCore.safeGiveMultiple(player, vile, 64);
        }else if(this.crate.equals("VHALF")){
            this.broadcast = RankColor.getNameColor(player) + ChatColor.stripColor(player.getDisplayName()) + " &7has won &e&l32x Vile!";
            ItemStack vile = items.getVile();
            StashCore.safeGiveMultiple(player, vile, 32);
        }


    }
}
