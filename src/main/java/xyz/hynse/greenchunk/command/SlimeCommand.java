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
        loadConfig();
    }

    private void loadConfig() {
        plugin.saveDefaultConfig();
        plugin.reloadConfig();
        String prefix = plugin.getConfig().getString("messages.prefix");

        String notInChunk = plugin.getConfig().getString("slime-command.messages.not-in-chunk");
        notInChunkMessage = Component.text(ChatColor.translateAlternateColorCodes('&', notInChunk))
                .replaceText(TextReplacementConfig.builder().match("%x").replacement(Component.text("0")).build())
                .replaceText(TextReplacementConfig.builder().match("%z").replacement(Component.text("0")).build())
                .asComponent();

        String inChunk = plugin.getConfig().getString("slime-command.messages.in-chunk");
        inChunkMessage = Component.text(ChatColor.translateAlternateColorCodes('&', inChunk))
                .replaceText(TextReplacementConfig.builder().match("%x").replacement(Component.text("0")).build())
                .replaceText(TextReplacementConfig.builder().match("%z").replacement(Component.text("0")).build())
                .asComponent();

        noPermissionMessage = Component.text(prefix + plugin.getConfig().getString("slime-command.messages.no-permission"));
        notPlayerMessage = Component.text(prefix + plugin.getConfig().getString("slime-command.messages.not-player"));
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

        if (SlimeChunkUtil.canSlimeSpawnAt(x, z, seed)) {
            player.sendMessage(inChunkMessage.replaceText(TextReplacementConfig.builder().match("%x").replacement(Component.text(String.valueOf(x))).build())
                    .replaceText(TextReplacementConfig.builder().match("%z").replacement(Component.text(String.valueOf(z))).build())
                    .asComponent());
        } else {
            player.sendMessage(notInChunkMessage.replaceText(TextReplacementConfig.builder().match("%x").replacement(Component.text(String.valueOf(x))).build())
                    .replaceText(TextReplacementConfig.builder().match("%z").replacement(Component.text(String.valueOf(z))).build())
                    .asComponent());
        }

        return true;
    }


}
