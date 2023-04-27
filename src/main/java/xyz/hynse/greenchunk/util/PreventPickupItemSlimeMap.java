package xyz.hynse.greenchunk.util;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;

public class PreventPickupItemSlimeMap implements Listener {
    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        Inventory clickedInventory = event.getClickedInventory();
        if (clickedInventory != null && event.getView().getTitle().equals("Slime Map")) {
            event.setCancelled(true);
        }
    }
}