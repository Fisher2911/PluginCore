package io.github.fisher2911.plugincore.database.type;

public class DatabaseType<T> {

    protected final Class<T> javaClass;
    protected final String sqlType;

    protected DatabaseType(Class<T> javaClass, String sqlType) {
        this.javaClass = javaClass;
        this.sqlType = sqlType;
    }

    public Class<?> getJavaClass() {
        return javaClass;
    }

    public String getSqlType() {
        return sqlType;
    }
}
