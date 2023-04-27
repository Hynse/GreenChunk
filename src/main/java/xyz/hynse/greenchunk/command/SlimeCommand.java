package xyz.hynse.greenchunk.command;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import xyz.hynse.greenchunk.GreenChunk;
import xyz.hynse.greenchunk.util.SlimeChunkUtil;

public class SlimeCommand implements CommandExecutor {

    private final GreenChunk plugin;

    public SlimeCommand(GreenChunk plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player player)) {
            sender.sendMessage(Component.text(plugin.getConfig().getString("messages.not_player")).color(NamedTextColor.RED));
            return true;
        }

        if (!player.hasPermission("slime.check")) {
            player.sendMessage(Component.text(plugin.getConfig().getString("messages.no_permission")).color(NamedTextColor.RED));
            return true;
        }

        if (SlimeChunkUtil.canSlimeSpawnAt(player.getLocation().getBlockX(), player.getLocation().getBlockZ(), player.getWorld().getSeed())) {
            player.sendMessage(Component.text(plugin.getConfig().getString("messages.in_chunk")).color(NamedTextColor.GREEN));
        } else {
            player.sendMessage(Component.text(plugin.getConfig().getString("messages.not_in_chunk")).color(NamedTextColor.RED));
        }

        return true;
    }
}
