package xyz.hynse.greenchunk.command;

import org.bukkit.ChatColor;
import org.bukkit.Chunk;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import xyz.hynse.greenchunk.GreenChunk;
import xyz.hynse.greenchunk.util.SlimeChunkUtil;

public class SlimeCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(GreenChunk.slimeCommandMessagesNotPlayer);
            return true;
        }

        Player player = (Player) sender;

        if (!player.hasPermission("slime.check")) {
            player.sendMessage(GreenChunk.slimeCommandMessagesNoPermission);
            return true;
        }

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

        String message = String.format(messageColor + messageFormat, (int)playerX, (int)playerZ);
        player.sendMessage(ChatColor.translateAlternateColorCodes('&', message));

        return true;
    }
}
