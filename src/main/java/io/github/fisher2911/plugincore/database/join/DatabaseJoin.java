package io.github.fisher2911.plugincore.database.join;

import io.github.fisher2911.plugincore.database.column.TableColumn;

public class DatabaseJoin {

    private final DatabaseJoinType type;
    private final TableColumn fromColumn;
    private final TableColumn toColumn;

    private DatabaseJoin(DatabaseJoinType type, TableColumn fromColumn, TableColumn toColumn) {
        this.type = type;
        this.fromColumn = fromColumn;
        this.toColumn = toColumn;
    }

    public DatabaseJoinType getType() {
        return type;
    }

    public TableColumn getFromColumn() {
        return fromColumn;
    }

    public TableColumn getToColumn() {
        return toColumn;
    }

    public String getSqlString() {
        return this.type.getSqlString() + " " + this.toColumn.getTable().getName() + " ON " + this.fromColumn.getName() + " = " + this.toColumn.getName();
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private DatabaseJoinType type;
        private TableColumn fromColumn;
        private TableColumn toColumn;

        private Builder() {}

        public Builder type(DatabaseJoinType type) {
            this.type = type;
            return this;
        }

        public Builder fromColumn(TableColumn fromColumn) {
            this.fromColumn = fromColumn;
            return this;
        }

        public Builder toColumn(TableColumn toColumn) {
            this.toColumn = toColumn;
            return this;
        }

        public DatabaseJoin build() {
            return new DatabaseJoin(this.type, this.fromColumn, this.toColumn);
        }

    }
}
