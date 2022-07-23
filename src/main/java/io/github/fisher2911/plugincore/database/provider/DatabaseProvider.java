package io.github.fisher2911.plugincore.database.provider;

import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.Map;

public class DatabaseProvider {

    // Statement class -> Map<Class<?>, Statement
    private final Map<Class<?>, Map<Class<?>, Object>> providers;

    public DatabaseProvider(Map<Class<?>, Map<Class<?>, Object>> providers) {
        this.providers = providers;
    }

    @Nullable
    public <T> T get(Class<T> statementType, Object object) {
        final Map<Class<?>, Object> statements = this.providers.get(statementType);
        if (statements == null) return null;
        return (T) statements.get(object.getClass());
    }

    public <T> void register(Class<T> statementType, Class<?> clazz, T statement) {
        final Map<Class<?>, Object> statements = this.providers.computeIfAbsent(statementType, k -> new HashMap<>());
        statements.put(clazz, statement);
    }


}
