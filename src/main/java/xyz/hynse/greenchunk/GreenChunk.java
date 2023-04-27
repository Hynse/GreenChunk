package xyz.hynse.greenchunk;

import net.kyori.adventure.text.Component;
import org.bukkit.plugin.java.JavaPlugin;
import xyz.hynse.greenchunk.command.ReloadCommand;
import xyz.hynse.greenchunk.command.SlimeCommand;

import java.util.List;

public class GreenChunk extends JavaPlugin {

    private static GreenChunk instance;
    private SlimeCommand slimeCommand;
    private ReloadCommand reloadCommand;

    @Override
    public void onEnable() {
        instance = this;
        registerCommands();
    }

    public static GreenChunk getInstance() {
        return instance;
    }

    private void registerCommands() {
        slimeCommand = new SlimeCommand(this);
        reloadCommand = new ReloadCommand(this, slimeCommand);
        getCommand("slime").setExecutor(slimeCommand);
        getCommand("greenchunkreload").setExecutor(reloadCommand);
        getCommand("slimereload").setAliases(List.of("greenchunkreload"));
    }


    public void reloadMessages() {
        reloadConfig();
        Component noPermissionMessage = Component.text(this.getConfig().getString("slime-command.messages.no-permission"));
        Component notPlayerMessage = Component.text(this.getConfig().getString("slime-command.messages.not-player"));
        slimeCommand.reloadMessages(noPermissionMessage, notPlayerMessage);
    }


}
