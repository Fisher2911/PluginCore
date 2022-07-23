package io.github.fisher2911.plugincore.database.key;

public enum DatabaseKey {

    PRIMARY("PRIMARY KEY"),
    UNIQUE("UNIQUE"),
    INDEX("INDEX"),
    FOREIGN_KEY("FOREIGN KEY"),
    NONE("");

    private final String value;

    private DatabaseKey(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

}
