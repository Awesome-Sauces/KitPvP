package me.alpha.kitpvp.DataSave;

import de.tr7zw.nbtapi.NBTItem;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.util.io.BukkitObjectInputStream;
import org.bukkit.util.io.BukkitObjectOutputStream;
import org.yaml.snakeyaml.external.biz.base64Coder.Base64Coder;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Converter64 {
    public static String playerDataTo64(PlayerData stack) throws IllegalStateException {
        try {
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            PlayerDataObjectOutputStream dataOutput = new PlayerDataObjectOutputStream(outputStream);
            dataOutput.writeObject(stack);

            // Serialize that array
            dataOutput.close();
            return Base64Coder.encodeLines(outputStream.toByteArray());
        } catch(Exception e) {
            throw new IllegalStateException("Unable to save item stack.", e);
        }
    }

    public static PlayerData playerDataFrom64(String data) throws IOException {
        try {
            ByteArrayInputStream inputStream = new ByteArrayInputStream(Base64Coder.decodeLines(data));
            PlayerDataObjectInputStream dataInput = new PlayerDataObjectInputStream(inputStream);
            try {
                return (PlayerData) dataInput.readObject();
            } finally {
                dataInput.close();
            }
        } catch(ClassNotFoundException e) {
            throw new IOException("Unable to decode class type.", e);
        }
    }

    public static String itemTo64(ItemStack stack) throws IllegalStateException {
        try {
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            BukkitObjectOutputStream dataOutput = new BukkitObjectOutputStream(outputStream);
            dataOutput.writeObject(stack);

            // Serialize that array
            dataOutput.close();
            return Base64Coder.encodeLines(outputStream.toByteArray());
        } catch(Exception e) {
            throw new IllegalStateException("Unable to save item stack.", e);
        }
    }

    public static String inventoryTo64(Inventory inventory) {
        StringBuilder inv = new StringBuilder();

        for(ItemStack item : inventory){
            /*if(item!=null &&
            item.getType()!= Material.AIR){
                Material material = item.getType();
                NBTItem nbtItem = new NBTItem(item);

                if(nbtItem.getCompound("enchants")!=null){
                    ItemMeta itemMeta = item.getItemMeta();

                    itemMeta.setLore(new ArrayList<>());

                    item.setItemMeta(itemMeta);
                }

            }*/
            inv.append(itemTo64(item)).append(">");
        }

        return inv.toString();
    }

    public static List<ItemStack> inventoryItemsFrom64(String data) throws IOException {

        String[] items = data.split(">");
        List<ItemStack> itemStacks = new ArrayList<>();

        for(String item : items){
            ItemStack itemStack = itemFrom64(item);

            /*if(itemStack!=null&&
            itemStack.getType()!=Material.AIR){
                NBTItem nbtItem = new NBTItem(itemStack);

                if(nbtItem.getCompound("enchants")!=null){

                }
            }*/

            itemStacks.add(itemStack);
        }

        return itemStacks;
    }

    public static ItemStack itemFrom64(String data) throws IOException {
        try {
            ByteArrayInputStream inputStream = new ByteArrayInputStream(Base64Coder.decodeLines(data));
            BukkitObjectInputStream dataInput = new BukkitObjectInputStream(inputStream);
            try {
                return (ItemStack) dataInput.readObject();
            } finally {
                dataInput.close();
            }
        } catch(ClassNotFoundException e) {
            throw new IOException("Unable to decode class type.", e);
        }
    }
}
