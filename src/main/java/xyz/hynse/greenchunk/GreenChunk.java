package xyz.hynse.greenchunk;

import org.bukkit.plugin.java.JavaPlugin;
import xyz.hynse.greenchunk.command.ReloadCommand;
import xyz.hynse.greenchunk.command.SlimeCommand;

public class GreenChunk extends JavaPlugin {

    public static GreenChunk instance;
    public static String slimeCommandMessagesNotInChunk;
    public static String slimeCommandMessagesInChunk;
    public static String slimeCommandMessagesNoPermission;
    public static String slimeCommandMessagesNotPlayer;
    public static String reloadCommandMessagesReloadConfig;
    public static String reloadCommandMessagesErrorReloadConfig;
    public static String reloadCommandMessagesNoPermission;

    @Override
    public void onEnable() {
        instance = this;
        saveDefaultConfig();
        register();
        reload();
    }
    public void reload() {
        saveDefaultConfig();
        reloadConfig();
        slimeCommandMessagesNotInChunk = getConfig().getString("slime-command.messages.not-in-chunk");
        slimeCommandMessagesInChunk = getConfig().getString("slime-command.messages.in-chunk");
        slimeCommandMessagesNoPermission = getConfig().getString("slime-command.messages.no-permission");
        slimeCommandMessagesNotPlayer = getConfig().getString("slime-command.messages.not-player");
        reloadCommandMessagesReloadConfig = getConfig().getString("reload-command.messages.reload-config");
        reloadCommandMessagesErrorReloadConfig = getConfig().getString("reload-command.messages.error-reload-config");
        reloadCommandMessagesNoPermission = getConfig().getString("reload-command.messages.no-permission");
    }
    private void register() {
        getCommand("greenchunkreload").setExecutor(new ReloadCommand());
        getCommand("slime").setExecutor(new SlimeCommand());
    }
}