package xyz.hynse.greenchunk;

import org.bukkit.plugin.java.JavaPlugin;
import xyz.hynse.greenchunk.command.SlimeCommand;

public final class GreenChunk extends JavaPlugin {

    @Override
    public void onEnable() {
        // Register command listener
        getCommand("slime").setExecutor(new SlimeCommand());
    }

}
