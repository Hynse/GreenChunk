package xyz.hynse.greenchunk.listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.inventory.Inventory;
import xyz.hynse.greenchunk.GreenChunk;

public class PreventPickupItemSlimeMap implements Listener {
    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        Inventory clickedInventory = event.getClickedInventory();
        if (clickedInventory != null && event.getView().getTitle().equals(GreenChunk.slimemapMessagesTitle)) {
            event.setCancelled(true);
        }
    }
    @EventHandler
    public void onInventoryDrag(InventoryDragEvent event) {
        Inventory draginventory = event.getInventory();
        if (draginventory != null && event.getView().getTitle().equals(GreenChunk.slimemapMessagesTitle)) {
            event.setCancelled(true);
        }
    }
}