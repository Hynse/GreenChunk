package xyz.hynse.greenchunk.command;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import xyz.hynse.greenchunk.GreenChunk;

public class ReloadCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!sender.hasPermission("greenchunk.reload")) return true;
        try {
            GreenChunk.instance.reload();
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', GreenChunk.reloadCommandMessagesReloadConfig));
        } catch (Exception e) {
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', GreenChunk.reloadCommandMessagesErrorReloadConfig));
            e.printStackTrace();
        }
        return true;
    }
}
