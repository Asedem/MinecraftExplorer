package de.asedem.explorer.spigot.config;

import de.asedem.explorer.core.config.ExplorerConfig;
import org.bukkit.configuration.file.YamlConfiguration;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;

public class BukkitConfig implements ExplorerConfig {

    private final File file;
    private final YamlConfiguration yamlConfiguration;

    public BukkitConfig(@NotNull Path path) {
        this.file = path.toFile();
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException exception) {
                exception.printStackTrace();
            }
        }
        this.yamlConfiguration = YamlConfiguration.loadConfiguration(file);
    }

    @Override
    public String getString(String key, String notFound) {
        return yamlConfiguration.getString(key, notFound);
    }
}
