package xyz.hynse.greenchunk.command;

import net.kyori.adventure.text.Component;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import xyz.hynse.greenchunk.GreenChunk;

public class ReloadCommand implements CommandExecutor {
    private final GreenChunk plugin;
    private Component noPermissionMessage;
    private Component reloadedConfgMessage;
    private Component errorreloadConfgMessage;

    public ReloadCommand(GreenChunk plugin) {
        this.plugin = plugin;
        loadConfig();
    }
    private void loadConfig() {
        plugin.saveDefaultConfig();
        plugin.reloadConfig();
        String prefix = plugin.getConfig().getString("messages.prefix");
        noPermissionMessage = Component.text(prefix + plugin.getConfig().getString("reload-command.messages.no-permission"));
        reloadedConfgMessage = Component.text(prefix + plugin.getConfig().getString("reload-command.messages.reload-config"));
        errorreloadConfgMessage = Component.text(prefix + plugin.getConfig().getString("reload-command.messages.error-reload-config"));
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!sender.hasPermission("greenchunk.reload")) {
            sender.sendMessage(noPermissionMessage);
            return true;
        }

        try {
            plugin.reloadConfig();
            plugin.saveDefaultConfig();
            sender.sendMessage(reloadedConfgMessage);
        } catch (Exception e) {
            sender.sendMessage(errorreloadConfgMessage);
            e.printStackTrace();
        }

        return true;
    }
}
