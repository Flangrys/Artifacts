package com.flangrys.artifacts.commands.annotations;

import com.flangrys.artifacts.commands.MixedExecutor;
import com.flangrys.artifacts.exceptions.MissingAnnotationException;
import org.bukkit.command.CommandExecutor;
import org.jetbrains.annotations.NotNull;

public class MetadataResolver {

    private final Metadatable metadata;

    public MetadataResolver(@NotNull Class<? extends MixedExecutor> commandExecutorClazz) throws MissingAnnotationException {
        if (!(commandExecutorClazz.isAnnotationPresent(Metadatable.class))) {
            throw new MissingAnnotationException("Missing 'Metadatable' annotation in 'commandExecutor' argument class where was instanced.");
        }

        this.metadata = commandExecutorClazz.getAnnotation(Metadatable.class);
    }

    public Metadatable getCommandMetadatable() {
        return this.metadata;
    }

    public String getCommandName() {
        return this.metadata.namedCommand();
    }
}