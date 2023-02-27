package me.alpha.kitpvp.PitRemake.RenownShop.New;

import org.bukkit.Material;

public class FishingClub extends RenownUpgrade{
    public FishingClub(String refID) {
        super(refID);

        setLore("&7Fish &b+[@level@*5]% &7better loot.");
        setMaterial(Material.WATER_BUCKET);
        setTitle("Fishing Club");
        setCostPerLevel(10);
        setMaxLevel(10);
        setPrestigeRequirement(0);
    }
}
