package xyz.hynse.greenchunk;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;
import xyz.hynse.greenchunk.util.SlimeChunkUtil;

public final class GreenChunk extends JavaPlugin {
    public static GreenChunk instance;
    @Override
    public void onEnable() {
        instance = this;
    }
    @Override
    public boolean onCommand(@NotNull CommandSender sender, Command cmd, @NotNull String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase("slime")) {
            if (!(sender instanceof Player player)) {
                sender.sendMessage(ChatColor.RED + "You must be a player to use this command!");
                return true;
            }
            if (player.hasPermission("slime.check")) {
                player.sendMessage(ChatColor.RED + "You don't have permission to use this command!");
                return true;
            }
            if (SlimeChunkUtil.isSlimeChunk(player.getLocation().getBlockX() >> 4, player.getLocation().getBlockZ() >> 4, player.getWorld().getSeed())) {
                player.sendMessage(ChatColor.GREEN + "You are in a slime chunk!");
            } else {
                player.sendMessage(ChatColor.RED + "You are not in a slime chunk!");
            }
            return true;
        }
        return false;
    }
}