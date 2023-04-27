package xyz.hynse.greenchunk.command;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import xyz.hynse.greenchunk.GreenChunk;

public class ReloadCommand implements CommandExecutor {
    private final GreenChunk plugin;

    public ReloadCommand(GreenChunk plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!sender.hasPermission("greenchunk.reload")) {
            sender.sendMessage(ChatColor.RED + "You don't have permission to use this command!");
            return true;
        }

        try {
            plugin.reloadConfig();
            plugin.saveDefaultConfig();
            sender.sendMessage(ChatColor.YELLOW + "GreenChunk config reloaded.");
        } catch (Exception e) {
            sender.sendMessage(ChatColor.RED + "Something went wrong reloading GreenChunk config, see the console for more.");
            e.printStackTrace();
        }

        return true;
    }
}
