package de.asedem.explorer.core.config;

public class MessageHandler {

    private final ExplorerConfig explorerConfig;

    private String noPermissionMessage;

    public MessageHandler(ExplorerConfig explorerConfig) {
        this.explorerConfig = explorerConfig;

        noPermissionMessage = explorerConfig.getString("noPermission", "&cYou dont have the permissions for this!");
    }

    public String getNoPermissionMessage() {
        return noPermissionMessage;
    }
}
