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
    private final SlimeCommand slimeCommand;

    public ReloadCommand(GreenChunk plugin, SlimeCommand slimeCommand) {
        this.plugin = plugin;
        noPermissionMessage = Component.text(plugin.getConfig().getString("reload-command.messages.no-permission"));
        reloadedConfigMessage = Component.text(plugin.getConfig().getString("reload-command.messages.reload-config"));
        errorReloadConfigMessage = Component.text(plugin.getConfig().getString("reload-command.messages.error-reload-config"));
        this.slimeCommand = slimeCommand;
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
            slimeCommand.reloadMessages(
                    Component.text(plugin.getConfig().getString("slime-command.messages.no-permission")),
                    Component.text(plugin.getConfig().getString("slime-command.messages.not-player"))
            );

        } catch (Exception e) {
            sender.sendMessage(errorReloadConfigMessage);
            e.printStackTrace();
        }

        return true;
    }

    public Component getNoPermissionMessage() {
        return noPermissionMessage;
    }

    public Component getNotPlayerMessage() {
        return Component.text(plugin.getConfig().getString("slime-command.messages.not-player"));
    }
}
