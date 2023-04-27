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
    private Component notInChunkMessage;
    private Component inChunkMessage;
    private Component noPermissionMessage;
    private Component notPlayerMessage;

    public SlimeCommand(GreenChunk plugin) {
        this.plugin = plugin;
        loadConfig();
    }

    private void loadConfig() {
        plugin.saveDefaultConfig();
        plugin.reloadConfig();
        notInChunkMessage = Component.text(plugin.getConfig().getString("slime-command.messages.not-in-chunk")).color(NamedTextColor.RED);
        inChunkMessage = Component.text(plugin.getConfig().getString("slime-command.messages.in-chunk")).color(NamedTextColor.GREEN);
        noPermissionMessage = Component.text(plugin.getConfig().getString("slime-command.messages.no-permission")).color(NamedTextColor.RED);
        notPlayerMessage = Component.text(plugin.getConfig().getString("slime-command.messages.not-player")).color(NamedTextColor.RED);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(notPlayerMessage);
            return true;
        }

        Player player = (Player) sender;

        if (!player.hasPermission("slime.check")) {
            player.sendMessage(noPermissionMessage);
            return true;
        }

        if (SlimeChunkUtil.canSlimeSpawnAt(player.getLocation().getBlockX(), player.getLocation().getBlockZ(), player.getWorld().getSeed())) {
            player.sendMessage(inChunkMessage);
        } else {
            player.sendMessage(notInChunkMessage);
        }

        return true;
    }
}
