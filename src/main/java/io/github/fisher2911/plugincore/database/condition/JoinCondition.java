package io.github.fisher2911.plugincore.database.condition;

import io.github.fisher2911.plugincore.database.column.TableColumn;

public class JoinCondition extends DatabaseCondition<TableColumn> {

    public JoinCondition(TableColumn first, TableColumn second, Operator operator) {
        super(first, second, operator);
    }

    @Override
    public String getSqlString() {
        return this.column.getName() + " " + this.operator.getOperator() + " " + ((TableColumn) this.value).getName();
    }

}
