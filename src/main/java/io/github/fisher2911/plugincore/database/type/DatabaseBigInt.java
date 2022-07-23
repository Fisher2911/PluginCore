package io.github.fisher2911.plugincore.database.type;

public class DatabaseBigInt extends DatabaseType<Long> {

    public DatabaseBigInt() {
        super(long.class, "BIGINT");
    }

}
