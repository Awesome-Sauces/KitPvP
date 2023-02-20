package me.alpha.kitpvp.CustomEvents;

import me.alpha.kitpvp.Objects.ReduxPlayerObject.ReduxPlayer;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.inventory.InventoryAction;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.List;

import static me.alpha.kitpvp.Objects.ReduxPlayerObject.ReduxPlayerHandler.playerExists;
import static me.alpha.kitpvp.PitRemake.MysticWell.loreChecker.CheckEnchantOnBow;

public class ReduxInventoryEvent extends Event implements Cancellable {
    private static final HandlerList HANDLERS = new HandlerList();
    private final ReduxPlayer reduxPlayer;
    private final Player player;
    private final Inventory inventory;
    private final InventoryClickEvent event;
    private boolean isCancelled;

    public static HandlerList getHandlerList() {
        return HANDLERS;
    }

    public ReduxInventoryEvent(InventoryClickEvent event) {
        if(event == null ||
                event.getClickedInventory()==null){
            this.event = event;
            this.isCancelled = true;

            this.player = (Player) event.getWhoClicked();
            this.reduxPlayer = null;

            this.inventory = null;
            return;
        }

        this.event = event;
        this.inventory = event.getClickedInventory();

        this.player = (Player) event.getWhoClicked();
        this.reduxPlayer = playerExists(player);

        this.isCancelled = false;

    }

    public void run(){
        if((ChatColor.stripColor(this.getPlayer().getOpenInventory().getTopInventory().getName()).contains("Vault #")||
                ChatColor.stripColor(this.getPlayer().getOpenInventory().getTopInventory().getName()).contains("Disposal")||
                ChatColor.stripColor(this.getPlayer().getOpenInventory().getTitle()).contains("inventory") ||
                ChatColor.stripColor(this.getPlayer().getOpenInventory().getTitle()).contains("enderchest") ||
                ChatColor.stripColor(this.getPlayer().getOpenInventory().getTitle()).contains("crafting"))){
            return;
        }else if(ChatColor.stripColor(this.getEvent().getClickedInventory().getName()).equalsIgnoreCase("inventory")) {
            return;
        }

        this.setCancelled(true);
        this.getEvent().setCancelled(true);
    }

    @Override
    public HandlerList getHandlers() {
        return HANDLERS;
    }

    @Override
    public boolean isCancelled() {
        return this.isCancelled;
    }

    @Override
    public void setCancelled(boolean isCancelled) {
        this.isCancelled = isCancelled;
    }

    public ReduxPlayer getReduxPlayer() {
        return this.reduxPlayer;
    }

    public Player getPlayer() {
        return this.player;
    }

    public Inventory getInventory(){return this.inventory;}

    public InventoryClickEvent getEvent(){return this.event;}

    public ItemStack getClickedItem(){if(event.getCurrentItem()==null) return new ItemStack(Material.AIR);
                                        return getEvent().getCurrentItem();}
    public int getSlot(){return getEvent().getSlot();}
    public InventoryAction getAction(){return getEvent().getAction();}
    public String getItemName(){if(getClickedItem()==null ||
            getClickedItem().getItemMeta()==null ||
            getClickedItem().getItemMeta().getDisplayName() == null) return "";
        return getClickedItem().getItemMeta().getDisplayName();}

    public Material getItemType(){if(getEvent() == null ||
    getEvent().getClickedInventory() == null ||
            getClickedItem()==null) return Material.AIR;
    return getClickedItem().getType();}

    public boolean isInventory(String name) {return !getInventory().getTitle().equals(name);}
}
