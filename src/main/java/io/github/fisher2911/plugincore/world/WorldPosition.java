package io.github.fisher2911.plugincore.world;

import java.util.UUID;

public class WorldPosition {

    private final UUID world;
    private final Position position;

    private WorldPosition(UUID world, double x, double y, double z) {
        this.world = world;
        this.position = Position.of(x, y, z);
    }

    private WorldPosition(UUID world, Position position) {
        this.world = world;
        this.position = position;
    }

    public static WorldPosition of(UUID world, double x, double y, double z) {
        return new WorldPosition(world, x, y, z);
    }

    public static WorldPosition of(UUID world, Position position) {
        return new WorldPosition(world, position);
    }

    public UUID getWorld() {
        return world;
    }

    public Position getPosition() {
        return position;
    }
}
