package io.github.fisher2911.plugincore.database.example;

import io.github.fisher2911.plugincore.database.annotation.Column;
import io.github.fisher2911.plugincore.database.annotation.Table;
import io.github.fisher2911.plugincore.database.key.DatabaseKey;

import java.util.UUID;

@Table("player")
public class PlayerExample {

    private final UUID uuid;

    private final String name;

    public PlayerExample(UUID uuid, String name) {
        this.uuid = uuid;
        this.name = name;
    }

    @Column(value = "id", key = DatabaseKey.PRIMARY)
    public UUID getUuid() {
        return uuid;
    }

    @Column(value = "name", length = 16)
    public String getName() {
        return name;
    }

}
