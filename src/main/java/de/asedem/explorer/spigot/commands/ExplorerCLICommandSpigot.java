package de.asedem.explorer.spigot.commands;

import de.asedem.explorer.spigot.ExplorerSpigot;
import de.asedem.explorer.spigot.commands.modules.CdModule;
import de.asedem.explorer.spigot.commands.modules.DirModule;
import de.asedem.explorer.spigot.commands.modules.PwdModule;
import de.asedem.explorer.spigot.libs.CommandModule;
import de.asedem.explorer.spigot.libs.ModuleCommand;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class ExplorerCLICommandSpigot extends ModuleCommand {

    public ExplorerCLICommandSpigot(@NotNull ExplorerSpigot main, @NotNull String name) {
        super(main, name);
    }

    @Override
    public @NotNull List<CommandModule> defineModules() {
        return List.of(
                new CdModule(this.main, "cd"),
                new DirModule(this.main, "dir"),
                new PwdModule(this.main, "pwd")
        );
    }
}
