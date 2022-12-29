package me.alpha.kitpvp.PitRemake.Pets.PetStore;

import de.tr7zw.nbtapi.NBTCompound;
import de.tr7zw.nbtapi.NBTItem;
import de.tr7zw.nbtapi.NBTListCompound;
import me.alpha.kitpvp.CustomEvents.ReduxBowEvent;
import me.alpha.kitpvp.CustomEvents.ReduxDamageEvent;
import me.alpha.kitpvp.CustomEvents.ReduxDeathEvent;
import me.alpha.kitpvp.Data.ClassInstances;
import me.alpha.kitpvp.PitRemake.Pets.PitPet;
import me.alpha.kitpvp.utils.CitizensHelper;
import me.alpha.kitpvp.utils.ColorUtil;
import me.alpha.kitpvp.utils.Sounds;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

import java.text.DecimalFormat;
import java.util.Arrays;

import static me.alpha.kitpvp.utils.ColorUtil.colorCode;

public class XpDragon extends PitPet {

    public XpDragon(String refID, String petName, String headName, int maxLevel, ChatColor nameColor, int xpPerLevel) {
        super(refID, petName, headName, maxLevel, nameColor, xpPerLevel);
    }

    @Override
    public ItemStack getPetItem(Player player){

        String textureValue = "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvOThmNjkyMDliMThhZjZkMDZkZjJkZmIzNmFmZTMyYzdhZDFjNmFmYjcxZDJjZDg4NWFjMWQyOGQyZDhmZTdiZCJ9fX0==="; // Pulled from the head link, scroll to the bottom and the "Other Value" field has this texture id.

        ItemStack head = new ItemStack(Material.SKULL_ITEM, 1, (short) 3); // Creating the ItemStack, your input may vary.
        NBTItem nbti = new NBTItem(head); // Creating the wrapper.

        NBTCompound skull = nbti.addCompound("SkullOwner"); // Getting the compound, that way we can set the skin information
        skull.setString("Name", "Storm Dragon"); // Owner's name
        skull.setString("Id", "fce0323d-7f50-4317-9720-5f6b14cf78ea");
// The UUID, note that skulls with the same UUID but different textures will misbehave and only one texture will load
// (They'll share it), if skulls have different UUIDs and same textures they won't stack. See UUID.randomUUID();

        NBTListCompound texture = skull.addCompound("Properties").getCompoundList("textures").addCompound();
        texture.setString("Value",  textureValue);

        head = nbti.getItem(); // Refresh the ItemStack

        NBTCompound nbtCompound = nbti.getOrCreateCompound("petData");

        SkullMeta skullMeta = (SkullMeta) nbti.getItem().getItemMeta();

        DecimalFormat formatter = new DecimalFormat("#,###");

        skullMeta.setDisplayName(colorCode("&7[Lvl " + ClassInstances.petData.getLevelFromXP(player.getUniqueId().toString(), getXpPerLevel()) + "] ") + getNameColor() + getPetName());

        if(ClassInstances.petData.getPetLevel(player.getUniqueId().toString(), getXpPerLevel())>=100){
            skullMeta.setLore(Arrays.asList(colorCode("&8Xp Pet\n\n" +
                    "&bBlue fire\n" +
                    "&7Earn &b+"+(ClassInstances.petData.getPetLevel(player.getUniqueId().toString(), getXpPerLevel())*.4)+"% xp &7from kills.\n\n" +
                    "&bRocket Ship\n" +
                    "&7All stored xp on Moon Streak is &btripled\n&7on death. " +
                    "&7Gain &b+"+(ClassInstances.petData.getPetLevel(player.getUniqueId().toString(), getXpPerLevel())*10)+" xp cap\n &7while moon &7is equipped.\n\n" +
                    "&bDragon Strike\n" +
                    "&7Deal &c+"+ClassInstances.petData.getPetLevel(player.getUniqueId().toString(), getXpPerLevel())+"% &7damage to all bots.\n" +
                    "&7But receive &c+"+ClassInstances.petData.getPetLevel(player.getUniqueId().toString(), getXpPerLevel())+"% &7damage\n" +
                    "&7from all bots.\n\n" +
                    "&b&lMAX LEVEL").split("\n")));
        }else skullMeta.setLore(Arrays.asList(colorCode("&8Xp Pet\n\n" +
                "&bBlue fire\n" +
                "&7Earn &b+"+(ClassInstances.petData.getPetLevel(player.getUniqueId().toString(), getXpPerLevel())*.4)+"% xp &7from kills.\n\n" +
                "&bRocket Ship\n" +
                "&7All stored xp on Moon Streak is &btripled\n&7on death. " +
                "&7Gain &b+"+(ClassInstances.petData.getPetLevel(player.getUniqueId().toString(), getXpPerLevel())*10)+" xp cap\n &7while moon &7is equipped.\n\n" +
                "&bDragon Strike\n" +
                "&7Deal &c+"+ClassInstances.petData.getPetLevel(player.getUniqueId().toString(), getXpPerLevel())+"% &7damage to all bots.\n" +
                "&7But receive &c+"+ClassInstances.petData.getPetLevel(player.getUniqueId().toString(), getXpPerLevel())+"% &7damage\n" +
                "&7from all bots.\n\n" +
                "&7Progress to Level "+(Math.min(100,1+ClassInstances.petData.getLevelFromXP(player.getUniqueId().toString(), getXpPerLevel())))+": &e" + "\n" +
                "&e"+formatter.format(ClassInstances.petData.getPetXp(player.getUniqueId().toString()))+"&6/&e"+
                formatter.format(ClassInstances.petData.getXPForLevel(player.getUniqueId().toString(), getXpPerLevel(), Math.min(100,ClassInstances.petData.getLevelFromXP(player.getUniqueId().toString(), getXpPerLevel())+1)))+" &eEXP").split("\n")));

        nbti.getItem().setItemMeta(skullMeta);

        return nbti.getItem();
    }

    @Override
    public void doPetAbility(ReduxDamageEvent event) {
        if(CitizensHelper.isNPC(event.getAttacker().getPlayerObject()) &&
        !CitizensHelper.isNPC(event.getDefender().getPlayerObject())){
            if(ClassInstances.petData.getPet(event.getDefender().getPlayerUUID()).contains(getRefID())){
                event.addReduxDamageMultiplier(ClassInstances.petData.getPetLevel(event.getAttacker().getPlayerUUID(), getXpPerLevel()));
            }
        }

        if(!CitizensHelper.isNPC(event.getAttacker().getPlayerObject()) &&
                CitizensHelper.isNPC(event.getDefender().getPlayerObject())){
            if(ClassInstances.petData.getPet(event.getAttacker().getPlayerUUID()).contains(getRefID())){
                event.addReduxDamageMultiplier(ClassInstances.petData.getPetLevel(event.getAttacker().getPlayerUUID(), getXpPerLevel()));
            }
        }
    }

    public void addPetExp(Player player, int xp){
        int levelBefore = ClassInstances.petData.getPetLevel(player.getUniqueId().toString(), getXpPerLevel());

        if(levelBefore>=100) return;

        ClassInstances.petData.addPetXp(player.getUniqueId().toString(), xp);

        int levelAfter = ClassInstances.petData.getPetLevel(player.getUniqueId().toString(), getXpPerLevel());

        if(levelAfter>levelBefore){
            player.sendMessage(ColorUtil.colorCode("&aYour "+getNameColor()+getPetName()+" &aleveled up to level &9"+ClassInstances.petData.getPetLevel(player.getUniqueId().toString(),  getXpPerLevel())));
            Sounds.SUCCESS.play(player);
            Sounds.LEVEL_UP.play(player);
        }

    }

    @Override
    public void doPetAbility(ReduxBowEvent event) {

    }

    @Override
    public void doPetAbility(ReduxDeathEvent event) {
        if(!CitizensHelper.isNPC(event.getAttacker().getPlayerObject()) &&
                CitizensHelper.isNPC(event.getDefender().getPlayerObject())){
            if(ClassInstances.petData.getPet(event.getAttacker().getPlayerUUID()).contains(getRefID())){
                if(ClassInstances.megaStreakData.getMegaStreak(event.getAttacker().getPlayerUUID()).contains("moon")){
                    event.setXp_cap(event.getXp_cap()+(ClassInstances.petData.getPetLevel(event.getAttacker().getPlayerUUID(), getXpPerLevel())*10));
                }

                event.addXp(event.getXp()*(ClassInstances.petData.getPetLevel(event.getAttacker().getPlayerUUID(), getXpPerLevel())*.004));
            }
        }
    }
}
