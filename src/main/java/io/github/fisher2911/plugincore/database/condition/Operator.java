package io.github.fisher2911.plugincore.database.condition;

public enum Operator {

    EQUAL("="),
    NOT_EQUAL("!="),
    GREATER_THAN(">"),
    LESS_THAN("<");

    private final String operator;

    private Operator(String operator) {
        this.operator = operator;
    }

    public String getOperator() {
        return operator;
    }

}
