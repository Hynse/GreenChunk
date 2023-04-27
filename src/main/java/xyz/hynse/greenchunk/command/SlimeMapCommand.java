package xyz.hynse.greenchunk.command;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import space.arim.morepaperlib.MorePaperLib;
import xyz.hynse.greenchunk.GreenChunk;
import xyz.hynse.greenchunk.util.SlimeChunkUtil;

public class SlimeMapCommand implements CommandExecutor {
    private final MorePaperLib morePaperLib;

    public SlimeMapCommand(MorePaperLib morePaperLib) {
        this.morePaperLib = morePaperLib;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.RED + "You must be a player to use this command.");
            return true;
        }

        Player player = (Player) sender;

        if (!player.hasPermission("greenchunk.map")) {
            player.sendMessage(ChatColor.RED + "You do not have permission to use this command.");
            return true;
        }

        Inventory inventory = GreenChunk.instance.getServer().createInventory(null, 57, "Slime Map");

        int centerX = player.getLocation().getBlockX() >> 4;
        int centerZ = player.getLocation().getBlockZ() >> 4;

        for (int x = centerX - 5; x <= centerX + 5; x++) {
            for (int z = centerZ - 5; z <= centerZ + 5; z++) {
                boolean isSlimeChunk = SlimeChunkUtil.canSlimeSpawnInChunk(x, z, player.getWorld().getSeed());

                ItemStack itemStack = new ItemStack(isSlimeChunk ? Material.LIME_STAINED_GLASS_PANE : Material.RED_STAINED_GLASS_PANE);
                ItemMeta itemMeta = itemStack.getItemMeta();
                itemMeta.setDisplayName(ChatColor.RESET + String.format("(%d, %d)", x, z));

                List<String> lore = new ArrayList<>();
                lore.add(isSlimeChunk ? ChatColor.GREEN + "Slime chunk" : ChatColor.RED + "Not slime chunk");
                itemMeta.setLore(lore);

                itemStack.setItemMeta(itemMeta);
                int slot = (x - centerX + 5) * 9 + (z - centerZ + 5);

                morePaperLib.scheduling().asyncScheduler().run(() -> {
                    inventory.setItem(slot, itemStack);
                });
            }
        }

        player.openInventory(inventory);
        return true;
    }
}
