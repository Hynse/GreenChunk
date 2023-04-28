package xyz.hynse.greenchunk.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import xyz.hynse.greenchunk.GreenChunk;
import xyz.hynse.greenchunk.util.SlimeChunkCheckUtil;

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

        SlimeChunkCheckUtil.sendSlimeChunkMessage(player);

        return true;
    }
}
