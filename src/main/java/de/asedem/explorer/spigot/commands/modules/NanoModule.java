package de.asedem.explorer.spigot.commands.modules;

import de.asedem.explorer.core.cli.CliHandler;
import de.asedem.explorer.spigot.ExplorerSpigot;
import de.asedem.explorer.spigot.libs.CommandModule;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

public class NanoModule extends CommandModule {

    public NanoModule(@NotNull ExplorerSpigot main, @NotNull String name) {
        super(main, name);
    }

    @Override
    public void onCommand(@NotNull CommandSender sender, @NotNull String[] args) {
        if (args.length != 1) return;
        UUID uuid = sender instanceof Player player ? player.getUniqueId() : ExplorerSpigot.CONSOLE;
        Bukkit.getScheduler().runTaskAsynchronously(super.getMain(), () -> {
            String result = null;
            try {
                result = CliHandler.nano(uuid, args[0]);
                if (result == null) return;
                sender.sendMessage(result);
            } catch (IOException exception) {
                sender.sendMessage("Provider not accessible!");
                exception.printStackTrace();
            }
        });
    }

    @Override
    public List<String> onTabComplete(@NotNull CommandSender sender, @NotNull String[] args) {
        UUID uuid = sender instanceof Player player ? player.getUniqueId() : ExplorerSpigot.CONSOLE;
        File file = CliHandler.directory(uuid);
        if (file == null || !file.exists()) return Collections.emptyList();
        File[] files = file.listFiles();
        if (files == null || files.length == 0) return Collections.emptyList();
        return Arrays.stream(files)
                .filter(File::isFile)
                .map(File::getName)
                .toList();
    }
}
