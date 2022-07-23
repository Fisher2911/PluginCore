package io.github.fisher2911.plugincore.database.column;

import io.github.fisher2911.plugincore.database.key.DatabaseKey;
import io.github.fisher2911.plugincore.database.type.DatabaseType;

public class DatabaseColumn<T> {

    protected final String name;
    protected final DatabaseType<T> type;
    protected final DatabaseKey key;

    protected DatabaseColumn(String name, DatabaseType<T> type, DatabaseKey key) {
        this.name = name;
        this.type = type;
        this.key = key;
    }

    public String getName() {
        return name;
    }

    public DatabaseType<T> getType() {
        return type;
    }

    public DatabaseKey getKey() {
        return key;
    }

    public static <T> Builder<T> builder(String name) {
        return new Builder<>(name);
    }

    public static class Builder<T> {

        private final String name;
        private DatabaseType<T> type;
        private DatabaseKey key = DatabaseKey.NONE;

        private Builder(String name) {
            this.name = name;
        }

        public Builder<T> type(DatabaseType<T> type) {
            this.type = type;
            return this;
        }

        public Builder<T> key(DatabaseKey key) {
            this.key = key;
            return this;
        }

        public DatabaseColumn<T> build() {
            return new DatabaseColumn<>(this.name, this.type, this.key);
        }
    }
}
