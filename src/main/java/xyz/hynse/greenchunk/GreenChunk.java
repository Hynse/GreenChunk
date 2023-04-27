package xyz.hynse.greenchunk;

import org.bukkit.plugin.java.JavaPlugin;
import space.arim.morepaperlib.MorePaperLib;
import xyz.hynse.greenchunk.command.ReloadCommand;
import xyz.hynse.greenchunk.command.SlimeCommand;
import xyz.hynse.greenchunk.command.SlimeMapCommand;
import xyz.hynse.greenchunk.util.PreventPickupItemSlimeMap;

public class GreenChunk extends JavaPlugin {

    public static GreenChunk instance;
    public static String slimeCommandMessagesNotInChunk;
    public static String slimeCommandMessagesInChunk;
    public static String slimeCommandMessagesNoPermission;
    public static String slimeCommandMessagesNotPlayer;
    public static String reloadCommandMessagesReloadConfig;
    public static String reloadCommandMessagesErrorReloadConfig;
    public static String reloadCommandMessagesNoPermission;
    public static String slimemapMessagesTitle;
    public static String slimemapMessagesNoPermission;
    public static String slimemapMessagesNotPlayer;
    private MorePaperLib morePaperLib;

    @Override
    public void onEnable() {
        instance = this;
        this.morePaperLib = new MorePaperLib(this);
        getServer().getPluginManager().registerEvents(new PreventPickupItemSlimeMap(), this);
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
        slimemapMessagesTitle = getConfig().getString("gui.messages.title");
        slimemapMessagesNoPermission = getConfig().getString("gui.messages.no-permission");
        slimemapMessagesNotPlayer = getConfig().getString("gui.messages.not-player");
    }
    private void register() {
        getCommand("greenchunkreload").setExecutor(new ReloadCommand());
        getCommand("slime").setExecutor(new SlimeCommand());
        getCommand("slimemap").setExecutor(new SlimeMapCommand());
    }
    public MorePaperLib getMorePaperLib() {
        return morePaperLib;
    }


}