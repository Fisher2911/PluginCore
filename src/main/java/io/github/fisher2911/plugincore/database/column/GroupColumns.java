package io.github.fisher2911.plugincore.database.column;

import io.github.fisher2911.plugincore.database.key.DatabaseKey;

import java.util.List;

public class GroupColumns {

    private final DatabaseKey key;
    private final List<DatabaseColumn<?>> columns;

    public GroupColumns(DatabaseKey key, List<DatabaseColumn<?>> columns) {
        this.key = key;
        this.columns = columns;
    }

    public DatabaseKey getKey() {
        return key;
    }

    public List<DatabaseColumn<?>> getColumns() {
        return columns;
    }

    public String getSqlString() {
        StringBuilder sb = new StringBuilder();
        if (this.key == DatabaseKey.NONE) return "";
        sb.append(key.name());
        sb.append(" (");
        for (int i = 0; i < columns.size(); i++) {
            DatabaseColumn<?> column = columns.get(i);
            sb.append(column.getName());
            if (i < columns.size() - 1) {
                sb.append(", ");
            }
        }
        sb.append(")");
        return sb.toString();
    }

}
