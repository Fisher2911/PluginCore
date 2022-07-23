package io.github.fisher2911.plugincore.database.condition;

import io.github.fisher2911.plugincore.database.column.DatabaseColumn;

public class DatabaseCondition<T extends DatabaseColumn> {

    protected final T column;
    protected final Object value;
    protected final Operator operator;

    protected DatabaseCondition(T column, Object value, Operator operator) {
        this.column = column;
        this.value = value;
        this.operator = operator;
    }

    public static DatabaseCondition<DatabaseColumn<?>> equal(DatabaseColumn<?> column, Object value) {
        return new DatabaseCondition<>(column, value, Operator.EQUAL);
    }

    public static DatabaseCondition<DatabaseColumn<?>> notEqual(DatabaseColumn<?> column, Object value) {
        return new DatabaseCondition<>(column, value, Operator.NOT_EQUAL);
    }

    public static DatabaseCondition<DatabaseColumn<?>> greaterThan(DatabaseColumn<?> column, Object value) {
        return new DatabaseCondition<>(column, value, Operator.GREATER_THAN);
    }

    public static DatabaseCondition<DatabaseColumn<?>> lessThan(DatabaseColumn<?> column, Object value) {
        return new DatabaseCondition<>(column, value, Operator.LESS_THAN);
    }

    public T getColumn() {
        return this.column;
    }

    public Object getValue() {
        return this.value;
    }

    public Operator getOperator() {
        return this.operator;
    }

    public String getSqlString() {
        return this.column.getName() + " " + this.operator.getOperator() + " " + this.value;
    }
}
