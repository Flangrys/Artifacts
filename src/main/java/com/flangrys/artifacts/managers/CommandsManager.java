package com.flangrys.artifacts.managers;

import com.flangrys.artifacts.Artifacts;
import com.flangrys.artifacts.commands.MixedExecutor;
import com.flangrys.artifacts.commands.annotations.MetadataResolver;
import com.flangrys.artifacts.commands.annotations.Metadatable;
import com.flangrys.artifacts.exceptions.MissingAnnotationException;
import com.flangrys.artifacts.exceptions.MissingCommandException;
import org.bukkit.command.PluginCommand;
import org.bukkit.command.TabCompleter;
import org.jetbrains.annotations.NotNull;

public final class CommandsManager {

    private final Artifacts plugin;

    public CommandsManager(Artifacts plugin) {
        this.plugin = plugin;
    }

    private PluginCommand resolvePluginCommand(@NotNull String commandName) throws MissingCommandException {
        PluginCommand pluginCommand = this.plugin.getCommand(commandName);
        if (pluginCommand == null) {
            throw new MissingCommandException("No command were registered as : '" + commandName + "' in 'plugin.yml' file.");
        }

        return pluginCommand;
    }

    private MetadataResolver resolveCommandMeta(@NotNull MixedExecutor commandExecutor) throws MissingAnnotationException {
        return new MetadataResolver(commandExecutor.getClass());
    }

    public void registerCommands(@NotNull MixedExecutor[] mixing) {
        for (MixedExecutor mixingItem : mixing) {
            MetadataResolver commandMetadata;
            PluginCommand pluginCommand;

            try {
                commandMetadata = this.resolveCommandMeta(mixingItem);
                pluginCommand = this.resolvePluginCommand(commandMetadata.getCommandName());

                pluginCommand.setExecutor(mixingItem);
                pluginCommand.setTabCompleter(mixingItem);

            } catch (MissingCommandException e) {
                this.plugin.getLogger().severe("There is not any command declared in the [plugin.yml] file, named as: " + e.getCause());

            } catch (MissingAnnotationException e) {
                this.plugin.getLogger().severe("There missing " + Metadatable.class + " annotation in: " + e.getCause());
            }
        }
    }

}
