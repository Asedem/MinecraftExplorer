package de.asedem.explorer.spigot.commands;

import de.asedem.explorer.spigot.ExplorerSpigot;
import de.asedem.minelibs.color.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Objects;

public class ExplorerCLICommandSpigot implements TabExecutor {

    private final ExplorerSpigot plugin;

    public ExplorerCLICommandSpigot(ExplorerSpigot plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (!sender.hasPermission(Objects.requireNonNull(command.getPermission()))) {
            sender.sendMessage(ChatColor.translate('&',
                    plugin.getConfigHandler().getPrefix() + " " + plugin.getMessageHandler().getNoPermissionMessage()));
            return true;
        }
        sender.sendMessage(ChatColor.translate('&',
                plugin.getConfigHandler().getPrefix() + " " + plugin.getMessageHandler().getNoPermissionMessage()));
        return true;
    }

    @Nullable
    @Override
    public List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        return List.of("cd", "nano", "touch", "mkdir", "dir");
    }
}
