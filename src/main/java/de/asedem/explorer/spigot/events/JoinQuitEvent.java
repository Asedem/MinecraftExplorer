package de.asedem.explorer.spigot.events;

import de.asedem.explorer.core.FileHandler;
import de.asedem.explorer.spigot.ExplorerSpigot;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class JoinQuitEvent implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        FileHandler.navigate(event.getPlayer().getUniqueId(), ExplorerSpigot.DEFAULT_PATH);
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        FileHandler.delete(event.getPlayer().getUniqueId());
    }
}
