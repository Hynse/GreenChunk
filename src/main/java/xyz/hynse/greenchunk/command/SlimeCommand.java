package xyz.hynse.greenchunk.command;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextReplacementConfig;
import org.bukkit.ChatColor;
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
    }

    public void reloadMessages(Component noPermissionMessage, Component notPlayerMessage) {
        this.noPermissionMessage = noPermissionMessage;
        this.notPlayerMessage = notPlayerMessage;
        notInChunkMessage = Component.text(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("slime-command.messages.not-in-chunk")));
        inChunkMessage = Component.text(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("slime-command.messages.in-chunk")));
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

        int x = player.getLocation().getBlockX();
        int z = player.getLocation().getBlockZ();
        long seed = player.getWorld().getSeed();

        Component message;
        if (SlimeChunkUtil.canSlimeSpawnAt(x, z, seed)) {
            message = inChunkMessage;
        } else {
            message = notInChunkMessage;
        }
        message = message.replaceText(TextReplacementConfig.builder().match("%x").replacement(Component.text(x)).build())
                .replaceText(TextReplacementConfig.builder().match("%z").replacement(Component.text(z)).build());

        player.sendMessage(message);

        return true;
    }
}
