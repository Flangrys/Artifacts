package com.flangrys.artifacts;

import com.flangrys.artifacts.commands.MixedExecutor;
import com.flangrys.artifacts.commands.PingPong;
import com.flangrys.artifacts.managers.CommandsManager;
import com.flangrys.artifacts.managers.ConfigurationManager;
import org.bukkit.plugin.java.JavaPlugin;


public final class Artifacts extends JavaPlugin {

    private final CommandsManager commandManager;
    private final ConfigurationManager configManager;

    public Artifacts() {
        this.commandManager = new CommandsManager(this);
        this.configManager = new ConfigurationManager(this);
    }

    @Override
    public void onEnable() {
        this.commandManager.registerCommands(new MixedExecutor[] {
                new PingPong(this),
        });
    }

    @Override
    public void onDisable() {}

}
