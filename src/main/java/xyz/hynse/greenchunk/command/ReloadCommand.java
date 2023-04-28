package xyz.hynse.greenchunk.command;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import xyz.hynse.greenchunk.GreenChunk;

public class ReloadCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!sender.hasPermission("greenchunk.reload")) {
            sendMessage(sender, GreenChunk.reloadCommandMessagesNoPermission);
            return true;
        }

        try {
            GreenChunk.instance.reload();
            sendMessage(sender, GreenChunk.reloadCommandMessagesReloadConfig);
        } catch (Exception e) {
            sendMessage(sender, GreenChunk.reloadCommandMessagesErrorReloadConfig);
            e.printStackTrace();
        }
        return true;
    }
    private void sendMessage(CommandSender sender, String message) {
        if (message != null) {
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', message));
        }
    }
}