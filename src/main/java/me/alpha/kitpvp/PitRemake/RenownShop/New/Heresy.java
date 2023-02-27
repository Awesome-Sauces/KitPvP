package me.alpha.kitpvp.PitRemake.RenownShop.New;

import org.bukkit.Material;

import static me.alpha.kitpvp.utils.ColorUtil.colorCode;

public class Heresy extends RenownUpgrade{
    public Heresy(String refID) {
        super(refID);

        setLore("&7You have trained in the profane\n" +
        "&7arts of counter-mysticism.");
        setMaterial(Material.COAL);
        setTitle("Heresy");
        setCostPerLevel(100);
        setMaxLevel(1);
        setPrestigeRequirement(6);
    }


}
