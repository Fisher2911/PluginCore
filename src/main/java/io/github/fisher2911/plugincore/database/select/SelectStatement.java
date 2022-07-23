package io.github.fisher2911.plugincore.database.select;

import io.github.fisher2911.plugincore.database.column.DatabaseColumn;
import io.github.fisher2911.plugincore.database.condition.DatabaseCondition;
import io.github.fisher2911.plugincore.database.join.DatabaseJoin;
import io.github.fisher2911.plugincore.database.table.DatabaseTable;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SelectStatement {

    private final DatabaseTable table;
    private final List<DatabaseColumn<?>> columns;
    private final List<DatabaseCondition<DatabaseColumn<?>>> conditions;
    @Nullable
    private final DatabaseJoin join;

    private SelectStatement(DatabaseTable table, List<DatabaseColumn<?>> columns, List<DatabaseCondition<DatabaseColumn<?>>> conditions, @Nullable DatabaseJoin join) {
        this.table = table;
        this.columns = columns;
        this.conditions = conditions;
        this.join = join;
    }

    public String getSqlString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("SELECT ");
        if (this.columns.isEmpty()) {
            sb.append("*");
        } else {
            for (int i = 0; i < this.columns.size(); i++) {
                final DatabaseColumn<?> column = this.columns.get(i);
                sb.append(column.getName());
                if (i < this.columns.size() - 1) {
                    sb.append(", ");
                }
            }
        }
        sb.append(" FROM ");
        sb.append(this.table.getName());
        if (this.join != null) {
            sb.append(" ");
            sb.append(this.join.getSqlString());
        }
        if (!this.conditions.isEmpty()) {
            sb.append(" WHERE ");
            for (int i = 0; i < this.conditions.size(); i++) {
                final DatabaseCondition<DatabaseColumn<?>> condition = this.conditions.get(i);
                sb.append(condition.getSqlString());
                if (i < this.conditions.size() - 1) {
                    sb.append(" AND ");
                }
            }
        }
        return sb.toString();
    }

    public static Builder builder(DatabaseTable table) {
        return new Builder(table);
    }

    public static class Builder {

        private final DatabaseTable table;
        private final List<DatabaseColumn<?>> columns = new ArrayList<>();
        private final List<DatabaseCondition<DatabaseColumn<?>>> conditions = new ArrayList<>();
        @Nullable
        private DatabaseJoin join;

        private Builder(DatabaseTable table) {
            this.table = table;
        }

        public Builder column(DatabaseColumn<?>... columns) {
            this.columns.addAll(Arrays.asList(columns));
            return this;
        }

        @SafeVarargs
        public final Builder where(DatabaseCondition<DatabaseColumn<?>>... conditions) {
            this.conditions.addAll(Arrays.asList(conditions));
            return this;
        }

        public Builder join(DatabaseJoin join) {
            this.join = join;
            return this;
        }

        public SelectStatement build() {
            return new SelectStatement(this.table, this.columns, this.conditions, this.join);
        }

    }

}
