package xyz.hynse.greenchunk.util;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import space.arim.morepaperlib.MorePaperLib;
import xyz.hynse.greenchunk.GreenChunk;

public class GuiBuilderUtil {

    private final MorePaperLib morePaperLib;

    public GuiBuilderUtil(MorePaperLib morePaperLib) {
        this.morePaperLib = morePaperLib;
    }

    public void buildSlimeMap(Player player) {
        Inventory inventory = GreenChunk.instance.getServer().createInventory(null, 54, GreenChunk.slimemapMessagesTitle);

        int centerX = player.getLocation().getBlockX() >> 4;
        int centerZ = player.getLocation().getBlockZ() >> 4;
        float playerYaw = player.getLocation().getYaw();

        for (int x = centerX - 5; x <= centerX + 5; x++) {
            for (int z = centerZ - 5; z <= centerZ + 5; z++) {
                boolean isSlimeChunk = SlimeChunkUtil.canSlimeSpawnInChunk(x, z, player.getWorld());
                boolean isCurrentChunk = (x == centerX && z == centerZ);

                Material paneColor = isSlimeChunk ? Material.LIME_STAINED_GLASS_PANE :
                        isCurrentChunk ? Material.BLUE_STAINED_GLASS_PANE : Material.RED_STAINED_GLASS_PANE;

                ItemStack itemStack = new ItemStack(paneColor);
                ItemMeta itemMeta = itemStack.getItemMeta();
                itemMeta.setDisplayName(ChatColor.RESET + String.format("(%d, %d)", x << 4, z << 4));

                List<String> lore = new ArrayList<>();
                lore.add(isSlimeChunk ? ChatColor.GREEN + GreenChunk.slimemapMessagesIsSlimechunk : ChatColor.RED + GreenChunk.slimemapMessagesNotSlimechunk);
                itemMeta.setLore(lore);

                itemStack.setItemMeta(itemMeta);
                int slot = getSlotFromCoordinateUtil.getSlotFromCoordinates(playerYaw, x - centerX, z - centerZ);
                morePaperLib.scheduling().asyncScheduler().run(() -> {
                    try {
                        inventory.setItem(slot, itemStack);
                    } catch (ArrayIndexOutOfBoundsException ignored) {
                    }
                });
            }
        }

        player.openInventory(inventory);
    }
}
