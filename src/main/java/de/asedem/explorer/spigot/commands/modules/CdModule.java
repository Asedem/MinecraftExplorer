package de.asedem.explorer.spigot.commands.modules;

import de.asedem.explorer.core.cli.CliHandler;
import de.asedem.explorer.spigot.ExplorerSpigot;
import de.asedem.explorer.spigot.libs.CommandModule;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

public class CdModule extends CommandModule {

    public CdModule(@NotNull ExplorerSpigot main, @NotNull String name) {
        super(main, name);
    }

    @Override
    public void onCommand(@NotNull CommandSender sender, @NotNull String[] args) {
        if (args.length != 1) return;
        UUID uuid = sender instanceof Player player ? player.getUniqueId() : ExplorerSpigot.CONSOLE;
        Boolean result = CliHandler.cd(uuid, args[0]);
        if (result == null) return;
        if (result.equals(Boolean.FALSE)) {
            sender.sendMessage("Directory not found!");
            return;
        }
        sender.sendMessage("Directory found!");
    }

    @Override
    public List<String> onTabComplete(@NotNull CommandSender sender, @NotNull String[] args) {
        UUID uuid = sender instanceof Player player ? player.getUniqueId() : ExplorerSpigot.CONSOLE;
        File file = CliHandler.directory(uuid);
        if (file == null || !file.exists()) return Collections.emptyList();
        File[] files = file.listFiles();
        if (files == null || files.length == 0) return Collections.emptyList();
        return Arrays.stream(files)
                .map(File::getName)
                .toList();
    }
}
