package io.github.fisher2911.plugincore.database.field;

import io.github.fisher2911.plugincore.database.column.DatabaseColumn;

public class DatabaseField<T> {

    private final DatabaseColumn<T> column;
    private final T value;

    public DatabaseField(DatabaseColumn<T> column, T value) {
        this.column = column;
        this.value = value;
    }

    public static <T> DatabaseField<T> of(DatabaseColumn<T> column, T value) {
        return new DatabaseField<>(column, value);
    }

    public DatabaseColumn<T> getColumn() {
        return column;
    }

    public T getValue() {
        return value;
    }
}
