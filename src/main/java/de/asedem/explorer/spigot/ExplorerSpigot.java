package de.asedem.explorer.spigot;

import de.asedem.explorer.core.config.ConfigHandler;
import de.asedem.explorer.core.config.MessageHandler;
import de.asedem.explorer.spigot.commands.ExplorerCLICommandSpigot;
import de.asedem.explorer.spigot.config.BukkitConfig;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public final class ExplorerSpigot extends JavaPlugin {

    private ConfigHandler configHandler;
    private MessageHandler messageHandler;

    @Override
    public void onEnable() {

        this.configHandler = new ConfigHandler(new BukkitConfig());
        this.messageHandler = new MessageHandler(new BukkitConfig());

        Objects.requireNonNull(this.getCommand("explorercli")).setExecutor(new ExplorerCLICommandSpigot(this));
    }

    public ConfigHandler getConfigHandler() {
        return configHandler;
    }

    public MessageHandler getMessageHandler() {
        return messageHandler;
    }
}
