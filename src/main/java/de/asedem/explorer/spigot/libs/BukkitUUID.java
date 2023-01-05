package de.asedem.explorer.spigot.libs;

import de.asedem.explorer.core.libs.UUIDHandler;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;

public class BukkitUUID extends UUIDHandler<CommandSender> {

    @Override
    public @NotNull UUID get(@NotNull CommandSender sender) {
        return sender instanceof Player player ? player.getUniqueId() : UUIDHandler.CONSOLE;
    }
}
