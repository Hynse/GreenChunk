package xyz.hynse.greenchunk;

import org.bukkit.Material;
import org.bukkit.configuration.Configuration;
import org.bukkit.plugin.java.JavaPlugin;
import space.arim.morepaperlib.MorePaperLib;
import xyz.hynse.greenchunk.command.ReloadCommand;
import xyz.hynse.greenchunk.command.SlimeCommand;
import xyz.hynse.greenchunk.command.SlimeMapCommand;
import xyz.hynse.greenchunk.listener.PreventPickupItemSlimeMap;
import xyz.hynse.greenchunk.listener.SlimeMapItemListener;
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
    public static String slimemapMessagesNotSlimechunk;
    public static String slimemapMessagesIsSlimechunk;
    public static String slimemapMessagesItem;
    public static boolean enableSlimeMapItem;
    public static Material slimeMapItemMaterial;
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
        slimemapMessagesTitle = getConfig().getString("gui.messages.title");
        slimemapMessagesNoPermission = getConfig().getString("gui.messages.no-permission");
        slimemapMessagesNotPlayer = getConfig().getString("gui.messages.not-player");
        slimemapMessagesNotSlimechunk = getConfig().getString("gui.messages.not-slimechunk");
        slimemapMessagesIsSlimechunk = getConfig().getString("gui.messages.is-slimechunk");
        slimemapMessagesItem = getConfig().getString("gui.messages.item");
    }
    private void register() {
        Configuration config = GreenChunk.instance.getConfig();
        enableSlimeMapItem = config.getBoolean("enableSlimeMapItem", true);
        String materialName = config.getString("slimeMapItem", "SLIME_BALL");
        slimeMapItemMaterial = Material.getMaterial(materialName);
        getCommand("greenchunkreload").setExecutor(new ReloadCommand());
        getCommand("slime").setExecutor(new SlimeCommand());
        getCommand("slimemap").setExecutor(new SlimeMapCommand());
        getServer().getPluginManager().registerEvents(new PreventPickupItemSlimeMap(), this);
        getServer().getPluginManager().registerEvents(new SlimeMapItemListener(), this);
    }
    public MorePaperLib getMorePaperLib() {
        return morePaperLib;
    }


}