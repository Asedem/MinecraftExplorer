package de.asedem.explorer.spigot.commands.modules;

import de.asedem.explorer.core.cli.CliHandler;
import de.asedem.explorer.spigot.ExplorerSpigot;
import de.asedem.explorer.spigot.libs.CommandModule;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

import java.util.*;
import java.util.stream.Stream;

public class DirModule extends CommandModule {

    public DirModule(@NotNull ExplorerSpigot main, @NotNull String name) {
        super(main, name);
    }

    @Override
    public void onCommand(@NotNull CommandSender sender, @NotNull String[] args) {
        UUID uuid = ExplorerSpigot.bukkitUUID.get(sender);
        Stream<String> fileStream = CliHandler.dir(uuid);
        if (fileStream == null) return;
        fileStream.forEach(sender::sendMessage);
    }

    @Override
    public List<String> onTabComplete(@NotNull CommandSender sender, @NotNull String[] args) {
        return Collections.emptyList();
    }
}
