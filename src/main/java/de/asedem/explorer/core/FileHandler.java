package de.asedem.explorer.core;

import org.apache.commons.io.FileUtils;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class FileHandler {

    private FileHandler() {
    }

    private static final Map<@NotNull UUID, Path> currentFiles = new HashMap<>();
    private static final Map<@NotNull UUID, File> copyFiles = new HashMap<>();

    public static void delete(@NotNull UUID uuid) {
        currentFiles.remove(uuid);
        copyFiles.remove(uuid);
    }

    public static void navigate(@NotNull UUID uuid, @NotNull Path path) {
        currentFiles.put(uuid, path);
    }

    public static void copy(@NotNull UUID uuid, @NotNull File file) {
        copyFiles.put(uuid, file);
    }

    public static void down(@NotNull UUID uuid) {
        currentFiles.put(uuid, currentFiles.get(uuid).getParent());
    }

    public static void up(@NotNull UUID uuid, @NotNull String name) {
        currentFiles.put(uuid, Paths.get(currentFiles.get(uuid).toString(), name));
    }

    public static boolean paste(@NotNull UUID uuid) throws IOException {
        if (!copyFiles.containsKey(uuid)) return false;
        File from = copyFiles.get(uuid);
        File directory = currentFiles.get(uuid).toFile();
        if (!directory.exists() || !directory.isDirectory()) return false;
        File to = new File(directory, from.getName());
        if (from.isDirectory()) {
            if (!to.exists()) to.mkdirs();
            FileUtils.copyDirectory(to, from);
            return true;
        }
        if (!to.exists()) to.createNewFile();
        FileUtils.copyFile(to, from);
        return true;
    }

    @Nullable
    public static File getCurrentFile(@NotNull UUID uuid) {
        Path path = currentFiles.get(uuid);
        if (path == null) return null;
        return path.toFile();
    }

    @Nullable
    public static File directory(@NotNull UUID uuid) {
        File file = FileHandler.getCurrentFile(uuid);
        if (file == null || !file.isDirectory()) return null;
        return file;
    }

    @Nullable
    public static File[] subfiles(@NotNull UUID uuid) {
        File file = FileHandler.directory(uuid);
        if (file == null || !file.exists()) return null;
        File[] files = file.listFiles();
        if (files == null || files.length == 0) return null;
        return files;
    }
}
