package io.github.fisher2911.plugincore.database.type;

public class DatabaseString extends DatabaseType<String> {

    public DatabaseString() {
        super(String.class, "VARCHAR");
    }

}
