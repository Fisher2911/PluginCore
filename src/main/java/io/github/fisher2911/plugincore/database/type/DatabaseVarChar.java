package io.github.fisher2911.plugincore.database.type;

public class DatabaseVarChar extends DatabaseType<String> {

    private final int length;

    public DatabaseVarChar(int length) {
        super(String.class, "VARCHAR(" + length + ")");
        this.length = length;
    }

    public int getLength() {
        return length;
    }

}
