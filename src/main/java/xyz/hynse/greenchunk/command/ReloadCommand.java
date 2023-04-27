package xyz.hynse.greenchunk.command;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import xyz.hynse.greenchunk.GreenChunk;

public class ReloadCommand implements CommandExecutor {
    private final GreenChunk plugin;
    private Component noPermissionMessage;

    public ReloadCommand(GreenChunk plugin) {
        this.plugin = plugin;
        loadConfig();
    }
    private void loadConfig() {
        plugin.saveDefaultConfig();
        plugin.reloadConfig();
        noPermissionMessage = Component.text(plugin.getConfig().getString("slime-command.messages.no-permission")).color(NamedTextColor.RED);
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
            sender.sendMessage(Component.text("GreenChunk config reloaded.").color(NamedTextColor.YELLOW));
        } catch (Exception e) {
            sender.sendMessage(Component.text("Something went wrong reloading GreenChunk config, see the console for more.").color(NamedTextColor.RED));
            e.printStackTrace();
        }

        return true;
    }
}
