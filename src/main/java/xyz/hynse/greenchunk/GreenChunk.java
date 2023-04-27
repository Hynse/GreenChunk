package xyz.hynse.greenchunk;

import org.bukkit.plugin.java.JavaPlugin;
import xyz.hynse.greenchunk.command.ReloadCommand;
import xyz.hynse.greenchunk.command.SlimeCommand;

import java.util.List;

public class GreenChunk extends JavaPlugin {

    @Override
    public void onEnable() {
        reloadConfig();
        registerCommands();
    }

    private void registerCommands() {
        getCommand("slime").setExecutor(new SlimeCommand(this));
        getCommand("greenchunkreload").setExecutor(new ReloadCommand(this));
        getCommand("slimereload").setAliases(List.of("greenchunkreload"));
    }
}
