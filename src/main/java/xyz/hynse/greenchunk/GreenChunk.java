package xyz.hynse.greenchunk;

import org.bukkit.plugin.java.JavaPlugin;
import xyz.hynse.greenchunk.command.ReloadCommand;
import xyz.hynse.greenchunk.command.SlimeCommand;

public class GreenChunk extends JavaPlugin {

    @Override
    public void onEnable() {
        register();
        saveDefaultConfig();
        reloadConfig();


    }
    private void register() {
        getCommand("slime").setExecutor(new SlimeCommand(this));
        getCommand("greenchunkreload").setExecutor(new ReloadCommand(this));
    }
}