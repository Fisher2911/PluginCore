package io.github.fisher2911.plugincore.database.join;

public enum DatabaseJoinType {

    INNER("INNER JOIN"),
    LEFT("LEFT OUTER JOIN"),
    CROSS("CROSS JOIN");

    private final String sqlString;

    private DatabaseJoinType(String sqlString) {
        this.sqlString = sqlString;
    }

    public String getSqlString() {
        return this.sqlString;
    }
}
