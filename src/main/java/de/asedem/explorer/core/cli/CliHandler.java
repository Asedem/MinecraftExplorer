package de.asedem.explorer.core.cli;

import de.asedem.explorer.core.FileHandler;
import org.bukkit.ChatColor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Stream;

public class CliHandler {

    private CliHandler() {

    }

    @Nullable
    public static Stream<String> dir(@NotNull UUID uuid) {
        File file;
        if ((file = CliHandler.directory(uuid)) == null) return null;
        return Arrays.stream(Objects.requireNonNull(file.listFiles()))
                .sorted(CliHandler::compareFiles)
                .map(current -> ChatColor.translateAlternateColorCodes('&',
                        (current.isFile() ? "&a▤" : "&d▥") + current.getName()));
    }

    @Nullable
    public static String pwd(@NotNull UUID uuid) throws IOException {
        File file;
        if ((file = CliHandler.directory(uuid)) == null) return null;
        return file.getCanonicalPath();
    }

    @Nullable
    public static Boolean cd(@NotNull UUID uuid, @NotNull String path) {
        File file;
        if ((file = CliHandler.directory(uuid)) == null) return null;
        Path newDir = Paths.get(file.getAbsolutePath(), path);
        if (!newDir.toFile().exists() || !newDir.toFile().isDirectory()) return false;
        FileHandler.navigate(uuid, newDir);
        return true;
    }

    @Nullable
    public static File directory(@NotNull UUID uuid) {
        File file = FileHandler.getCurrentFile(uuid);
        if (file == null || !file.isDirectory()) return null;
        return file;
    }

    private static int compareFiles(@NotNull File file1, @NotNull File file2) {
        if (file1.isDirectory() == file2.isDirectory()) return 0;
        if (file1.isDirectory() && file2.isFile()) return -1;
        return 1;
    }
}
