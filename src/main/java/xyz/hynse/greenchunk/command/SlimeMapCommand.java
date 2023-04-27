package xyz.hynse.greenchunk.command;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import xyz.hynse.greenchunk.util.GuiBuilderUtil;
import xyz.hynse.greenchunk.GreenChunk;

public class SlimeMapCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(GreenChunk.slimemapMessagesNotPlayer);
            return true;
        }

        Player player = (Player) sender;

        if (!player.hasPermission("greenchunk.map")) {
            player.sendMessage(GreenChunk.slimemapMessagesNoPermission);
            return true;
        }

        GuiBuilderUtil guiBuilder = new GuiBuilderUtil(GreenChunk.instance.getMorePaperLib());
        guiBuilder.buildSlimeMap(player);

        return true;
    }
}
