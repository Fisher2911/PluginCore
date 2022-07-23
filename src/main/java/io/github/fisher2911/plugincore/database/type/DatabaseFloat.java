package io.github.fisher2911.plugincore.database.type;

public class DatabaseFloat extends DatabaseType<Float> {

    private final int length;

    public DatabaseFloat(int length) {
        super(float.class, "FLOAT(" + length + ")");
        this.length = length;
    }

    public int getLength() {
        return length;
    }

}
