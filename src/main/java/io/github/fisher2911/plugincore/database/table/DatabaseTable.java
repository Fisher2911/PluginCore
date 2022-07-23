package io.github.fisher2911.plugincore.database.table;

import io.github.fisher2911.plugincore.database.column.DatabaseColumn;
import io.github.fisher2911.plugincore.database.column.GroupColumns;
import io.github.fisher2911.plugincore.database.key.DatabaseCascade;
import io.github.fisher2911.plugincore.database.key.DatabaseKey;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DatabaseTable {

    private final String name;
    private final List<DatabaseColumn<?>> columns;
    private final List<GroupColumns> groupColumns;
    private final List<DatabaseCascade> cascades;

    private DatabaseTable(String name, List<DatabaseColumn<?>> columns, List<GroupColumns> groupColumns, List<DatabaseCascade> cascades) {
        this.name = name;
        this.columns = columns;
        this.groupColumns = groupColumns;
        this.cascades = cascades;
    }

    public String getName() {
        return name;
    }

    public List<DatabaseColumn<?>> getColumns() {
        return columns;
    }

    public List<GroupColumns> getGroupColumns() {
        return groupColumns;
    }

    public List<DatabaseCascade> getCascade() {
        return cascades;
    }

    public String getSqlString() {
        StringBuilder sb = new StringBuilder();
        sb.append("CREATE TABLE IF NOT EXISTS ");
        sb.append(name);
        sb.append(" (");
        for (int i = 0; i < columns.size(); i++) {
            final DatabaseColumn<?> column = columns.get(i);

            sb.append(column.getName());
            sb.append(" ");
            sb.append(column.getType().getSqlType());
            final DatabaseKey key = column.getKey();
            if (key != DatabaseKey.NONE) {
                sb.append(" ");
                sb.append(key.getValue());
            }
            if (i < columns.size() - 1) {
                sb.append(", ");
            }
        }
        for (GroupColumns groupColumn : this.groupColumns) {
            final String string = groupColumn.getSqlString();
            if (!string.isEmpty()) {
                sb.append(", ").append(groupColumn);
            }
        }
        for (DatabaseCascade cascade : this.cascades) {
            final String string = cascade.getSqlString();
            if (!string.isEmpty()) {
                sb.append(", ").append(cascade);
            }
        }
        sb.append(")");
        return sb.toString();
    }

    public static Builder builder(String name) {
        return new Builder(name);
    }

    public static class Builder {

        private final String name;
        private final List<DatabaseColumn<?>> columns = new ArrayList<>();
        private final List<GroupColumns> groupColumns = new ArrayList<>();
        private final List<DatabaseCascade> cascades = new ArrayList<>();

        private Builder(String name) {
            this.name = name;
        }

        public Builder column(DatabaseColumn<?>... column) {
            this.columns.addAll(Arrays.asList(column));
            return this;
        }

        public Builder columns(List<DatabaseColumn<?>> columns) {
            this.columns.addAll(columns);
            return this;
        }

        public Builder groupColumn(DatabaseKey key, DatabaseColumn<?>... column) {
            this.groupColumns.add(new GroupColumns(key, Arrays.asList(column)));
            return this;
        }

        public Builder groupColumns(List<GroupColumns> groupColumns) {
            this.groupColumns.addAll(groupColumns);
            return this;
        }

        public Builder cascade(DatabaseCascade cascade) {
            this.cascades.add(cascade);
            return this;
        }

        public Builder cascades(List<DatabaseCascade> cascades) {
            this.cascades.addAll(cascades);
            return this;
        }

        public DatabaseTable build() {
            return new DatabaseTable(name, columns, groupColumns, this.cascades);
        }
    }
}
