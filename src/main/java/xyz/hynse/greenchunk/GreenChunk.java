package xyz.hynse.greenchunk;

import org.bukkit.plugin.java.JavaPlugin;
import space.arim.morepaperlib.MorePaperLib;
import xyz.hynse.greenchunk.command.ReloadCommand;
import xyz.hynse.greenchunk.command.SlimeCommand;
import xyz.hynse.greenchunk.command.SlimeMapCommand;

public class GreenChunk extends JavaPlugin {

    public static GreenChunk instance;
    public static String slimeCommandMessagesNotInChunk;
    public static String slimeCommandMessagesInChunk;
    public static String slimeCommandMessagesNoPermission;
    public static String slimeCommandMessagesNotPlayer;
    public static String reloadCommandMessagesReloadConfig;
    public static String reloadCommandMessagesErrorReloadConfig;
    public static String reloadCommandMessagesNoPermission;
    private MorePaperLib morePaperLib;

    @Override
    public void onEnable() {
        instance = this;
        this.morePaperLib = new MorePaperLib(this);
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
        getCommand("slimemap").setExecutor(new SlimeMapCommand(morePaperLib)); // Pass morePaperLib to SlimeMapCommand constructor
    }

}