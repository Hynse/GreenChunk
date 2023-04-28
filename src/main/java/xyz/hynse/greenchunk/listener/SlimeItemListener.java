package xyz.hynse.greenchunk.listener;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import xyz.hynse.greenchunk.GreenChunk;
import xyz.hynse.greenchunk.util.GuiBuilderUtil;
import xyz.hynse.greenchunk.util.SlimeChunkCheckUtil;

import static xyz.hynse.greenchunk.GreenChunk.enableSlimeMapItem;
import static xyz.hynse.greenchunk.GreenChunk.slimeMapItemMaterial;

public class SlimeItemListener implements Listener {
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
        if (!player.hasPermission("greenchunk.item")) {
            player.sendMessage(GreenChunk.slimemapMessagesItem);
            return;
        }
        if (event.getAction() != Action.LEFT_CLICK_AIR && event.getAction() != Action.LEFT_CLICK_BLOCK) {
            return;
        }
        SlimeChunkCheckUtil.sendSlimeChunkMessage(player);
    }
}
