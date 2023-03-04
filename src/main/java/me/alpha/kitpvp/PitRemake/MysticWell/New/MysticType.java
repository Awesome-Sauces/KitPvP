package me.alpha.kitpvp.PitRemake.MysticWell.New;

import com.google.common.collect.ImmutableList;
import org.bukkit.Material;

import java.util.List;

public enum MysticType {
    SWORD(ImmutableList.of(Material.GOLD_SWORD)),
    BOW(ImmutableList.of(Material.BOW)),
    PANT(ImmutableList.of(Material.LEATHER_LEGGINGS,
            Material.LEATHER_CHESTPLATE,
            Material.LEATHER_BOOTS)),
    ALL(ImmutableList.of(Material.LEATHER_LEGGINGS,
            Material.LEATHER_CHESTPLATE,
            Material.LEATHER_BOOTS,
            Material.BOW,
            Material.GOLD_SWORD)),
    NONE(ImmutableList.of(Material.AIR));
    private final List<Material> materials;

    MysticType(List<Material> materials) {
        this.materials = materials;
    }

    public List<Material> getMaterials() {
        return materials;
    }
}