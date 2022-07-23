package io.github.fisher2911.plugincore.database.key;

import io.github.fisher2911.plugincore.database.column.DatabaseColumn;
import io.github.fisher2911.plugincore.database.column.TableColumn;

public class DatabaseCascade {

    private final DatabaseColumn<?> foreignKey;
    private final TableColumn tableColumn;
    private final DatabaseCascadeType type;

    private DatabaseCascade(DatabaseColumn<?> foreignKey, TableColumn tableColumn, DatabaseCascadeType type) {
        this.foreignKey = foreignKey;
        this.tableColumn = tableColumn;
        this.type = type;
    }

    public String getSqlString() {
        return "FOREIGN KEY (" + this.foreignKey.getName() + ") REFERENCES " + this.tableColumn.getTable().getName() + " (" + this.tableColumn.getColumn().getName() + ") ON " + this.type.toString() + " CASCADE";
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private DatabaseColumn<?> foreignKey;
        private TableColumn tableColumn;
        private DatabaseCascadeType type;

        private Builder() {}

        public Builder foreignKey(DatabaseColumn<?> foreignKey) {
            this.foreignKey = foreignKey;
            return this;
        }

        public Builder tableColumn(TableColumn tableColumn) {
            this.tableColumn = tableColumn;
            return this;
        }

        public Builder type(DatabaseCascadeType type) {
            this.type = type;
            return this;
        }

        public DatabaseCascade build() {
            return new DatabaseCascade(this.foreignKey, this.tableColumn, this.type);
        }
    }


}
