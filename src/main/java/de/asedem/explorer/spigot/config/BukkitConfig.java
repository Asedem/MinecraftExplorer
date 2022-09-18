package de.asedem.explorer.spigot.config;

import de.asedem.explorer.core.config.ExplorerConfig;

public class BukkitConfig implements ExplorerConfig {

    @Override
    public String getString(String key, String notFound) {
        return notFound;
    }
}
