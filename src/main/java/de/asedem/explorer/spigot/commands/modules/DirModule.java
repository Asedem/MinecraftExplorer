package de.asedem.explorer.spigot.commands.modules;

import de.asedem.explorer.core.FileHandler;
import de.asedem.explorer.spigot.ExplorerSpigot;
import de.asedem.explorer.spigot.libs.CommandModule;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.util.*;

public class DirModule extends CommandModule {

    public DirModule(@NotNull ExplorerSpigot main, @NotNull String name) {
        super(main, name);
    }

    @Override
    public void onCommand(@NotNull CommandSender sender, @NotNull String[] args) {
        UUID uuid = sender instanceof Player player ? player.getUniqueId() : ExplorerSpigot.CONSOLE;
        File file = FileHandler.getCurrentFile(uuid);
        if (file == null || !file.isDirectory()) return;
        Arrays.stream(Objects.requireNonNull(file.listFiles()))
                .sorted(this::compareFiles)
                .map(current -> ChatColor.translateAlternateColorCodes('&',
                        (current.isFile() ? "&a▤" : "&d▥") + current.getName()))
                .forEach(sender::sendMessage);
    }

    @Override
    public List<String> onTabComplete(@NotNull CommandSender sender, @NotNull String[] args) {
        return Collections.emptyList();
    }

    private int compareFiles(@NotNull File file1, @NotNull File file2) {
        if (file1.isDirectory() == file2.isDirectory()) return 0;
        if (file1.isDirectory() && file2.isFile()) return -1;
        return 1;
    }
}
