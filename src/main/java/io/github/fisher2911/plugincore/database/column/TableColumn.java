package io.github.fisher2911.plugincore.database.column;

import io.github.fisher2911.plugincore.database.key.DatabaseKey;
import io.github.fisher2911.plugincore.database.table.DatabaseTable;
import io.github.fisher2911.plugincore.database.type.DatabaseType;

public class TableColumn extends DatabaseColumn {

    private final DatabaseColumn column;
    private final DatabaseTable table;

    public TableColumn(DatabaseColumn column, DatabaseTable table) {
        this(column.getName(), column.getType(), column.getKey(), column, table);
    }

    private TableColumn(String name, DatabaseType type, DatabaseKey key, DatabaseColumn column, DatabaseTable table) {
        super(name, type, key);
        this.column = column;
        this.table = table;
    }

    public static TableColumn of(DatabaseColumn column, DatabaseTable joinTable) {
        return new TableColumn(column, joinTable);
    }

    public DatabaseColumn getColumn() {
        return column;
    }

    public DatabaseTable getTable() {
        return table;
    }

    @Override
    public String getName() {
        return this.table.getName() + "." + this.column.getName();
    }
}
