package de.asedem.explorer.core.libs;

import org.jetbrains.annotations.NotNull;

import java.util.UUID;

public abstract class UUIDHandler<T> {

    public static final UUID CONSOLE = new UUID(0, 0);

    @NotNull
    public abstract UUID get(@NotNull T holder);
}
