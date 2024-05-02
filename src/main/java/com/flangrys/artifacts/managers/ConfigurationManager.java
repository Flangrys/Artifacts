package com.flangrys.artifacts.managers;

import com.flangrys.artifacts.Artifacts;
import org.bukkit.configuration.Configuration;
import org.jetbrains.annotations.NotNull;

public final class ConfigurationManager {

    private final Artifacts plugin;
    private final Configuration config;

    public ConfigurationManager(Artifacts plugin) {
        this.plugin = plugin;

        this.plugin.saveDefaultConfig();

        this.config = this.plugin.getConfig();
    }

    @NotNull
    public Configuration getConfig() {
        return config;
    }
}
