package xyz.hynse.greenchunk.listener;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import xyz.hynse.greenchunk.GreenChunk;
import xyz.hynse.greenchunk.util.GuiBuilderUtil;

import static xyz.hynse.greenchunk.GreenChunk.enableSlimeMapItem;
import static xyz.hynse.greenchunk.GreenChunk.slimeMapItemMaterial;

public class SlimeMapItemListener implements Listener {
    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        if (!enableSlimeMapItem) {
            return;
        }

        Player player = event.getPlayer();
        ItemStack item = event.getItem();

        if (item == null || item.getType() != slimeMapItemMaterial) {
            return;
        }

        if (!player.hasPermission("greenchunk.map.item")) {
            player.sendMessage(GreenChunk.slimemapMessagesItem);
            return;
        }

        GuiBuilderUtil guiBuilder = new GuiBuilderUtil(GreenChunk.instance.getMorePaperLib());
        guiBuilder.buildSlimeMap(player);
        event.setCancelled(true);
    }
}
