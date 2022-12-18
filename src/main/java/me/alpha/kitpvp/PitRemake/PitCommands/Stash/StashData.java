package me.alpha.kitpvp.PitRemake.PitCommands.Stash;

import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class StashData{
    private static HashMap<UUID, List<ItemStack>> data = new HashMap<>();

    public static List<ItemStack> getStashData(UUID uuid){
        if (!data.containsKey(uuid)) {
            List<ItemStack> items = new ArrayList<>();
            data.put(uuid, items);
        }

        return data.get(uuid);
    }

    public static ItemStack getItemFromStashData(UUID uuid, int index){
        if (!data.containsKey(uuid)) {
            List<ItemStack> items = new ArrayList<>();
            data.put(uuid, items);
        }

        return data.get(uuid).get(index);
    }

    public static void setStashData(UUID uuid, List<ItemStack> items){
        data.put(uuid, items);
    }

    public static void addStashData(UUID uuid, ItemStack item){

        getStashData(uuid).add(item);
        //setStashData(uuid, );
    }

    public static void addListToStashData(UUID uuid, List<ItemStack> itemStacks){
        List<ItemStack> items = getStashData(uuid);

        items.addAll(itemStacks);

        setStashData(uuid, items);
    }

    public static void removeStashData(UUID uuid, ItemStack itemStack){
        List<ItemStack> items = getStashData(uuid);

        items.remove(itemStack);
    }

}
