package xyz.hynse.greenchunk.util;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import xyz.hynse.greenchunk.GreenChunk;
public class SlimeChunkCheckUtil {

    public static void sendSlimeChunkMessage(Player player) {
        int x = player.getLocation().getBlockX();
        int z = player.getLocation().getBlockZ();
        double playerX = player.getLocation().getX();
        double playerZ = player.getLocation().getZ();
        long seed = player.getWorld().getSeed();

        String messageFormat;
        String messageColor;

        if (SlimeChunkUtil.canSlimeSpawnAt(x, z, seed)) {
            messageFormat = GreenChunk.slimeCommandMessagesInChunk;
            messageColor = ChatColor.GREEN.toString();
        } else {
            messageFormat = GreenChunk.slimeCommandMessagesNotInChunk;
            messageColor = ChatColor.RED.toString();
        }

        String message = String.format(messageColor + messageFormat, (int) playerX, (int) playerZ);
        player.sendMessage(ChatColor.translateAlternateColorCodes('&', message));
    }
}
