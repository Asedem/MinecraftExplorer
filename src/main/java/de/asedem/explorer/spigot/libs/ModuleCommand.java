package de.asedem.explorer.spigot.libs;

import de.asedem.explorer.spigot.ExplorerSpigot;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public abstract class ModuleCommand implements TabExecutor {

    protected final ExplorerSpigot main;
    protected final String name;
    private final List<CommandModule> commandModules;

    protected ModuleCommand(@NotNull ExplorerSpigot main, @NotNull String name) {
        this.main = main;
        this.name = name;
        this.commandModules = this.defineModules();
    }

    @NotNull
    public abstract List<CommandModule> defineModules();

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (args.length < 1) {
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&',
                    "&cBitte benutze &6/" + name + ' ' + this.buildArgs() + "&c!"));
            return true;
        }
        Optional<CommandModule> module = this.commandModules.stream()
                .filter(commandModule -> commandModule.getName().equals(args[0]))
                .findFirst();
        if (module.isEmpty()) {
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&',
                    "&cBitte benutze &6/" + name + " [" + this.buildArgs() + "]&c!"));
            return true;
        }
        module.get().onCommand(sender, removeFirstElement(args));
        return true;
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (args.length == 1) return this.commandModules.stream()
                .map(CommandModule::getName)
                .toList();
        Optional<CommandModule> module = this.commandModules.stream()
                .filter(commandModule -> commandModule.getName().equals(args[0]))
                .findFirst();
        if (module.isEmpty()) return Collections.emptyList();
        return module.get().onTabComplete(sender, removeFirstElement(args));
    }

    @NotNull
    private String buildArgs() {
        return this.commandModules
                .stream()
                .map(CommandModule::getName)
                .collect(Collectors.joining(", "));
    }

    @NotNull
    private String[] removeFirstElement(String[] array) {
        String[] newArray = new String[array.length - 1];
        System.arraycopy(array, 1, newArray, 0, array.length - 1);
        return newArray;
    }
}
