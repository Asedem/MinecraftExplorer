package de.asedem.explorer.core.config;

public class ConfigHandler {

    private String prefix;
    private final ExplorerConfig explorerConfig;

    public ConfigHandler(ExplorerConfig explorerConfig) {
        this.explorerConfig = explorerConfig;

        this.prefix = explorerConfig.getString("prefix", "&7[&eExplorer&7]");
    }

    public String getPrefix() {
        return prefix;
    }
}
