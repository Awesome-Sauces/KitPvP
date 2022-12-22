package me.alpha.kitpvp.PitRemake.Pets;

import de.tr7zw.nbtapi.NBTCompound;
import de.tr7zw.nbtapi.NBTItem;
import de.tr7zw.nbtapi.NBTListCompound;
import me.alpha.kitpvp.CustomEvents.ReduxBowEvent;
import me.alpha.kitpvp.CustomEvents.ReduxDamageEvent;
import me.alpha.kitpvp.CustomEvents.ReduxDeathEvent;
import me.alpha.kitpvp.Data.ClassInstances;
import me.alpha.kitpvp.KitPvP;
import me.alpha.kitpvp.utils.ColorUtil;
import org.bukkit.*;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.List;

import static me.alpha.kitpvp.utils.advancedInventory.HeadMaker;

public abstract class PitPet
{

    private String headName;
    private String petName;
    private int maxLevel;
    private int xpPerLevel;
    private ChatColor nameColor;
    private String refID;

    public PitPet(String refID, String petName, String headName, int maxLevel, ChatColor nameColor, int xpPerLevel){
        this.refID = refID;
        this.petName = petName;
        this.headName = headName;
        this.maxLevel = maxLevel;
        this.nameColor = nameColor;
        this.xpPerLevel = xpPerLevel;
    }

    public ArmorStand spawnPet(Player owner){

        String textureValue = getHeadName(); // Pulled from the head link, scroll to the bottom and the "Other Value" field has this texture id.

        ItemStack head = new ItemStack(Material.SKULL_ITEM, 1, (short) 3); // Creating the ItemStack, your input may vary.
        NBTItem nbti = new NBTItem(head); // Creating the wrapper.

        NBTCompound skull = nbti.addCompound("SkullOwner"); // Getting the compound, that way we can set the skin information
        skull.setString("Name", ChatColor.stripColor(getPetName())); // Owner's name
        skull.setString("Id", "fce0323d-7f50-4317-9720-5f6b14cf78ea");
// The UUID, note that skulls with the same UUID but different textures will misbehave and only one texture will load
// (They'll share it), if skulls have different UUIDs and same textures they won't stack. See UUID.randomUUID();

        NBTListCompound texture = skull.addCompound("Properties").getCompoundList("textures").addCompound();
        texture.setString("Value",  textureValue);

        head = nbti.getItem(); // Refresh the ItemStack

        ArmorStand stand = (ArmorStand) owner.getWorld().spawnEntity(owner.getLocation(), EntityType.ARMOR_STAND);

        stand.setCustomNameVisible(true);
        stand.setCustomName(ColorUtil.colorCode("&8[&7Lv"+ ClassInstances.petData.getPetLevel(owner.getUniqueId().toString(), getXpPerLevel())+"&8] "+ getNameColor() +
                ChatColor.stripColor(owner.getDisplayName()) +
                getNameColor() +"'s " + getPetName()));
        //Methods you can do to the armor stand
        stand.setVisible(true);
        stand.setSmall(true);
        stand.setHelmet(head);
        stand.setBasePlate(false);
        stand.setGravity(false);
        //stand.setBodyPose(new EulerAngle(Math.toRadians(0), Math.toRadians(45), Math.toRadians(0)));
        //stand.setHeadPose(new EulerAngle(0, 45, 0));
        stand.hasArms();

        new BukkitRunnable(){
            @Override
            public void run() {

                if(!owner.isOnline()) {
                    stand.remove();
                    this.cancel();

                }
                if (stand.isDead()) this.cancel();
                Location damageeLoc = owner.getLocation();
                double nX;
                double nZ;
                float nang = damageeLoc.getYaw() + 90;
                if (nang < 0) nang += 360;
                nX = Math.cos(Math.toRadians(nang));
                nZ = Math.sin(Math.toRadians(nang));

                Location newDamagerLoc = new Location(damageeLoc.getWorld(), damageeLoc.getX() - nX,
                        damageeLoc.getY() + .5, damageeLoc.getZ() - nZ, damageeLoc.getYaw(), damageeLoc.getPitch());

                stand.teleport(newDamagerLoc);
                stand.getWorld().playEffect(stand.getLocation().add(0,.5,0), Effect.WATERDRIP, 1);
            }
        }.runTaskTimer(KitPvP.INSTANCE,  3L, 3L);



        Bukkit.getScheduler().scheduleSyncDelayedTask(KitPvP.INSTANCE, new Runnable() {
            @Override
            public void run() {

                stand.remove();
            }
        }, 1000L);

        return stand;
    }

    public abstract ItemStack getPetItem(Player player);
    public abstract void doPetAbility(ReduxDamageEvent event);
    public abstract void doPetAbility(ReduxBowEvent event);
    public abstract void doPetAbility(ReduxDeathEvent event);

    public String getHeadName() {
        return headName;
    }

    public void setHeadName(String headName) {
        this.headName = headName;
    }

    public String getPetName() {
        return petName;
    }

    public void setPetName(String petName) {
        this.petName = petName;
    }

    public int getMaxLevel() {
        return maxLevel;
    }

    public void setMaxLevel(int maxLevel) {
        this.maxLevel = maxLevel;
    }

    public String getRefID() {
        return refID;
    }

    public void setRefID(String refID) {
        this.refID = refID;
    }

    public ChatColor getNameColor() {
        return nameColor;
    }

    public void setNameColor(ChatColor nameColor) {
        this.nameColor = nameColor;
    }

    public int getXpPerLevel() {
        return xpPerLevel;
    }

    public void setXpPerLevel(int xpPerLevel) {
        this.xpPerLevel = xpPerLevel;
    }
}