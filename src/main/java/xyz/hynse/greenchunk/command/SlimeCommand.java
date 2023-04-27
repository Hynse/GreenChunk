package xyz.hynse.greenchunk.command;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import xyz.hynse.greenchunk.util.SlimeChunkUtil;

public class SlimeCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.RED + "You must be a player to use this command!");
            return true;
        }

        Player player = (Player) sender;

        if (!player.hasPermission("slime.check")) {
            player.sendMessage(ChatColor.RED + "You don't have permission to use this command!");
            return true;
        }

        if (SlimeChunkUtil.canSlimeSpawnAt(player.getLocation().getBlockX(), player.getLocation().getBlockZ(), player.getWorld().getSeed())) {
            player.sendMessage(ChatColor.GREEN + "You are in a slime chunk!");
        } else {
            player.sendMessage(ChatColor.RED + "You are not in a slime chunk!");
        }

        return true;
    }
}
