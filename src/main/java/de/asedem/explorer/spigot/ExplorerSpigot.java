package de.asedem.explorer.spigot;

import de.asedem.explorer.core.FileHandler;
import de.asedem.explorer.core.config.ConfigHandler;
import de.asedem.explorer.core.config.MessageHandler;
import de.asedem.explorer.spigot.commands.ExplorerCLICommandSpigot;
import de.asedem.explorer.spigot.config.BukkitConfig;
import de.asedem.explorer.spigot.events.JoinQuitEvent;
import org.bukkit.Bukkit;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

public final class ExplorerSpigot extends JavaPlugin {

    public static final UUID CONSOLE = new UUID(0, 0);
    public static Path DEFAULT_PATH;

    private ConfigHandler configHandler;
    private MessageHandler messageHandler;

    private BukkitConfig bukkitConfig;

    @Override
    public void onEnable() {

        ExplorerSpigot.DEFAULT_PATH = Paths.get(this.getDataFolder().toURI());

        if (!ExplorerSpigot.DEFAULT_PATH.toFile().exists()) ExplorerSpigot.DEFAULT_PATH.toFile().mkdirs();

        this.bukkitConfig = new BukkitConfig(Paths.get(this.getDataFolder().getAbsolutePath(), "config.yml"));

        this.configHandler = new ConfigHandler(this.bukkitConfig);
        this.messageHandler = new MessageHandler(this.bukkitConfig);

        FileHandler.navigate(ExplorerSpigot.CONSOLE, ExplorerSpigot.DEFAULT_PATH);

        Objects.requireNonNull(this.getCommand("explorercli")).setExecutor(new ExplorerCLICommandSpigot(this, "cli"));

        Bukkit.getPluginManager().registerEvents(new JoinQuitEvent(this), this);
    }

    public ConfigHandler getConfigHandler() {
        return configHandler;
    }

    public MessageHandler getMessageHandler() {
        return messageHandler;
    }

    public BukkitConfig getBukkitConfig() {
        return bukkitConfig;
    }
}
