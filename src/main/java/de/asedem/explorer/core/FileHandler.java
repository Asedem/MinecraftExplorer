package de.asedem.explorer.core;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class FileHandler {

    private final Map<UUID, Path> currentFiles = new HashMap<>();
    private final Map<UUID, File> copyFiles = new HashMap<>();

    public void navigate(UUID uuid, Path path) {
        currentFiles.put(uuid, path);
    }

    public void copy(UUID uuid, File file) {
        copyFiles.put(uuid, file);
    }

    public void down(UUID uuid) {
        currentFiles.put(uuid, currentFiles.get(uuid).getParent());
    }

    public void up(UUID uuid, String name) {
        currentFiles.put(uuid, Paths.get(currentFiles.get(uuid).toString(), name));
    }

    public boolean paste(UUID uuid) throws IOException {
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
}
