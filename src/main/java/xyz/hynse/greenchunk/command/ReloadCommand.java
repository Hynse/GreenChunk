package xyz.hynse.greenchunk.command;

import net.kyori.adventure.text.Component;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import xyz.hynse.greenchunk.GreenChunk;

public class ReloadCommand implements CommandExecutor {
    private final GreenChunk plugin;
    private final Component noPermissionMessage;
    private final Component reloadedConfigMessage;
    private final Component errorReloadConfigMessage;

    public ReloadCommand(GreenChunk plugin) {
        this.plugin = plugin;
        this.noPermissionMessage = Component.text(plugin.getConfig().getString("reload-command.messages.no-permission"));
        this.reloadedConfigMessage = Component.text(plugin.getConfig().getString("reload-command.messages.reload-config"));
        this.errorReloadConfigMessage = Component.text(plugin.getConfig().getString("reload-command.messages.error-reload-config"));
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!sender.hasPermission("greenchunk.reload")) {
            sender.sendMessage(noPermissionMessage);
            return true;
        }

        try {
            plugin.reloadConfig();
            sender.sendMessage(reloadedConfigMessage);
        } catch (Exception e) {
            sender.sendMessage(errorReloadConfigMessage);
            e.printStackTrace();
        }

        return true;
    }
}
