package de.asedem.explorer.spigot.libs;

import de.asedem.explorer.spigot.ExplorerSpigot;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public abstract class CommandModule {

    protected final ExplorerSpigot main;
    protected final String name;

    protected CommandModule(@NotNull ExplorerSpigot main, @NotNull String name) {
        this.main = main;
        this.name = name;
    }

    public abstract void onCommand(@NotNull CommandSender sender, @NotNull String[] args);

    public abstract List<String> onTabComplete(@NotNull CommandSender sender, @NotNull String[] args);

    @NotNull
    public ExplorerSpigot getMain() {
        return main;
    }

    @NotNull
    public String getName() {
        return name;
    }
}
