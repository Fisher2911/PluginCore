package io.github.fisher2911.plugincore.database.type;

import org.jetbrains.annotations.Nullable;

import java.util.Map;
import java.util.UUID;
import java.util.function.Function;

public class DatabaseTypes {

    private static final Map<Class<?>, Function<Object, DatabaseType<?>>> TYPES = Map.of(
            Integer.class, o -> ofInt(),
            Long.class, o -> ofBigInt(),
            String.class, o -> {
                if (o instanceof Integer i) return ofVarChar(i);
                return ofString();
            },
            Boolean.class, o -> ofBoolean(),
            UUID.class, o -> ofUUID(),
            Float.class, o -> {
                if (o instanceof Integer i) return ofFloat(i);
                return ofFloat(7);
            }
    );

    public static DatabaseInt ofInt() {
        return new DatabaseInt();
    }

    public static DatabaseBigInt ofBigInt() {
        return new DatabaseBigInt();
    }

    public static DatabaseString ofString() {
        return new DatabaseString();
    }

    public static DatabaseBoolean ofBoolean() {
        return new DatabaseBoolean();
    }

    public static DatabaseVarChar ofVarChar(int length) {
        return new DatabaseVarChar(length);
    }

    public static DatabaseVarChar ofUUID() {
        return new DatabaseVarChar(36);
    }

    public static DatabaseFloat ofFloat(int length) {
        return new DatabaseFloat(length);
    }

    @Nullable
    public static <T> DatabaseType<T> from(Class<T> clazz, @Nullable Object value) {
        return (DatabaseType<T>) TYPES.get(clazz).apply(value);
    }
}

