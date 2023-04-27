package xyz.hynse.greenchunk.command;

import net.md_5.bungee.api.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import xyz.hynse.greenchunk.GreenChunk;
import xyz.hynse.greenchunk.util.SlimeChunkUtil;

public class SlimeCommand implements CommandExecutor {

    private final GreenChunk plugin;
    private boolean rgbSupport;
    private String notInChunkMessage;
    private String inChunkMessage;
    private String noPermissionMessage;
    private String notPlayerMessage;

    public SlimeCommand(GreenChunk plugin) {
        this.plugin = plugin;
        loadConfig();
    }

    private void loadConfig() {
        plugin.saveDefaultConfig();
        plugin.reloadConfig();
        rgbSupport = plugin.getConfig().getBoolean("slime-command.rgb-support");
        notInChunkMessage = plugin.getConfig().getString("slime-command.messages.not-in-chunk");
        inChunkMessage = plugin.getConfig().getString("slime-command.messages.in-chunk");
        noPermissionMessage = plugin.getConfig().getString("slime-command.messages.no-permission");
        notPlayerMessage = plugin.getConfig().getString("slime-command.messages.not-player");
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
            player.sendMessage(rgbSupport ? ChatColor.of("#00ff00") + inChunkMessage : inChunkMessage);
        } else {
            player.sendMessage(rgbSupport ? ChatColor.of("#ff0000") + notInChunkMessage : notInChunkMessage);
        }

        return true;
    }
}