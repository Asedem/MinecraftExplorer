package de.asedem.explorer.spigot.commands.modules;

import de.asedem.explorer.core.cli.CliHandler;
import de.asedem.explorer.spigot.ExplorerSpigot;
import de.asedem.explorer.spigot.libs.CommandModule;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

public class PwdModule extends CommandModule {

    public PwdModule(@NotNull ExplorerSpigot main, @NotNull String name) {
        super(main, name);
    }

    @Override
    public void onCommand(@NotNull CommandSender sender, @NotNull String[] args) {
        UUID uuid = sender instanceof Player player ? player.getUniqueId() : ExplorerSpigot.CONSOLE;
        String path = null;
        try {
            path = CliHandler.pwd(uuid);
            if (path == null) return;
            sender.sendMessage(path);
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    @Override
    public List<String> onTabComplete(@NotNull CommandSender sender, @NotNull String[] args) {
        return Collections.emptyList();
    }
}
