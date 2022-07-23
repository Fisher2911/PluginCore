package io.github.fisher2911.plugincore.database.insert;

import io.github.fisher2911.plugincore.database.column.DatabaseColumn;
import io.github.fisher2911.plugincore.database.condition.DatabaseCondition;
import io.github.fisher2911.plugincore.database.field.DatabaseField;
import io.github.fisher2911.plugincore.database.table.DatabaseTable;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class InsertStatement {

    private final DatabaseTable table;
    private final List<DatabaseColumn<?>> columns;
    private final List<List<DatabaseField<?>>> fields;

    public InsertStatement(DatabaseTable table, List<DatabaseColumn<?>> columns, List<List<DatabaseField<?>>> fields) {
        this.table = table;
        this.columns = columns;
        this.fields = fields;
    }

    public String getSqlString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("INSERT OR REPLACE INTO ");
        sb.append(this.table.getName());
        sb.append(" (");
        for (int i = 0; i < this.columns.size(); i++) {
            final DatabaseColumn<?> column = this.columns.get(i);
            sb.append(column.getName());
            if (i < this.columns.size() - 1) {
                sb.append(", ");
            }
        }
        sb.append(") VALUES (");
        for (int i = 0; i < this.columns.size(); i++) {
            sb.append("?");
            if (i < this.columns.size() - 1) {
                sb.append(", ");
            }
        }
        sb.append(")");
        return sb.toString();
    }

    public List<List<DatabaseField<?>>> getFields() {
        return fields;
    }

    public void applyValues(PreparedStatement statement, int index) throws SQLException {
        final List<DatabaseField<?>> fields = this.fields.get(index);
        for (int i = 0; i < fields.size(); i++) {
            final DatabaseField<?> field = fields.get(i);
            statement.setObject(i + 1, field.getValue());
        }
    }

    public static Builder builder(DatabaseTable table) {
        return new Builder(table);
    }

    public static class Builder {

        private final DatabaseTable table;
        private final Set<DatabaseColumn<?>> columns = new LinkedHashSet<>();
        private final List<List<DatabaseField<?>>> fields = new ArrayList<>();

        private Builder(DatabaseTable table) {
            this.table = table;
        }

        public <T> Builder field(DatabaseField<T> field) {
            this.getCurrentList().add(field);
            this.columns.add(field.getColumn());
            return this;
        }

        public void newRow() {
            this.fields.add(new ArrayList<>());
        }

        private List<DatabaseField<?>> getCurrentList() {
            if (this.fields.isEmpty()) {
                final List<DatabaseField<?>> fields = new ArrayList<>();
                this.fields.add(fields);
                return fields;
            }
            return this.fields.get(this.fields.size() - 1);
        }

        public InsertStatement build() {
            return new InsertStatement(this.table, new ArrayList<>(this.columns), this.fields);
        }
    }

}
