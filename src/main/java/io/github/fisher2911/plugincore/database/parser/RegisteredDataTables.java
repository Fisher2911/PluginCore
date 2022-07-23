package io.github.fisher2911.plugincore.database.parser;

import com.google.common.collect.ListMultimap;
import com.google.common.collect.Multimaps;
import io.github.fisher2911.plugincore.database.annotation.Column;
import io.github.fisher2911.plugincore.database.annotation.ForeignKey;
import io.github.fisher2911.plugincore.database.annotation.Table;
import io.github.fisher2911.plugincore.database.column.DatabaseColumn;
import io.github.fisher2911.plugincore.database.column.GroupColumns;
import io.github.fisher2911.plugincore.database.column.TableColumn;
import io.github.fisher2911.plugincore.database.key.DatabaseCascade;
import io.github.fisher2911.plugincore.database.key.DatabaseCascadeType;
import io.github.fisher2911.plugincore.database.key.DatabaseKey;
import io.github.fisher2911.plugincore.database.table.DatabaseTable;
import io.github.fisher2911.plugincore.database.type.DatabaseType;
import io.github.fisher2911.plugincore.database.type.DatabaseTypes;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RegisteredDataTables {

    private final Map<Class<?>, DatabaseTable> databaseTableMap;

    public RegisteredDataTables(Map<Class<?>, DatabaseTable> databaseTableMap) {
        this.databaseTableMap = databaseTableMap;
    }

    public DatabaseTable get(Class<?> clazz) {
        return databaseTableMap.get(clazz);
    }

    @Nullable
    public DatabaseTable parse(Class<?> clazz) {
        final Table table = clazz.getAnnotation(Table.class);
        if (table == null) return null;
        final String tableName = table.value();

        final List<DatabaseColumn<?>> columns = new ArrayList<>();
        final List<DatabaseCascade> cascades = new ArrayList<>();
        for (Method method : clazz.getMethods()) {
            final DatabaseColumn<?> column = this.parseColumn(method);
            if (column != null) columns.add(column);
            final DatabaseCascade cascade = this.parseCascade(method, columns);
            if (cascade != null) cascades.add(cascade);
        }

        final List<GroupColumns> groupColumns = this.getGroupColumns(columns);


        final DatabaseTable databaseTable = DatabaseTable.
                builder(tableName).
                columns(columns).
                groupColumns(groupColumns).
                cascades(cascades).
                build();
        this.databaseTableMap.put(clazz, databaseTable);
        return databaseTable;
    }

    @Nullable
    private DatabaseColumn<?> parseColumn(Method method) {
        final Column column = method.getAnnotation(Column.class);
        if (column == null) return null;
        final String columnName = column.value();
        final DatabaseKey key = column.key();
        final int length = column.length();
        final DatabaseType<?> type = DatabaseTypes.from(method.getReturnType(), length == -1 ? null : length);
        if (type == null) return null;
        return DatabaseColumn.
                builder(columnName).
                type((DatabaseType<Object>) type).
                key(key).
                build();
    }

    @Nullable
    private DatabaseCascade parseCascade(Method method, List<DatabaseColumn<?>> columns) {
        final ForeignKey foreignKey = method.getAnnotation(ForeignKey.class);
        if (foreignKey == null) return null;
        final String foreignKeyName = foreignKey.references();
        final DatabaseCascadeType type = foreignKey.cascade();
        final Class<?> linksTo = foreignKey.linksTo();
        if (this.databaseTableMap.get(linksTo) == null) {
            this.parse(linksTo);
        }
        final DatabaseTable joinTable = this.databaseTableMap.get(linksTo);
        if (joinTable == null) return null;
        DatabaseColumn<?> foreignColumn = null;
        for (DatabaseColumn<?> column : columns) {
            if (column.getName().equals(foreignKeyName)) {
                foreignColumn = column;
                break;
            }
        }
        if (foreignColumn == null) return null;
        DatabaseColumn<?> joinColumn = null;
        for (DatabaseColumn<?> column : joinTable.getColumns()) {
            if (column.getName().equals(foreignKeyName)) {
                joinColumn = column;
                break;
            }
        }
        final TableColumn tableColumn = TableColumn.of(joinColumn, joinTable);
        return DatabaseCascade.
                builder().
                foreignKey(foreignColumn).
                tableColumn(tableColumn).
                type(type).
                build();
    }

    private @NotNull List<GroupColumns> getGroupColumns(List<DatabaseColumn<?>> columns) {
        final List<GroupColumns> groupColumnsList = new ArrayList<>();
        final ListMultimap<DatabaseKey, DatabaseColumn<?>> keyColumns = Multimaps.newListMultimap(new HashMap<>(), ArrayList::new);
        for (DatabaseColumn<?> column : columns) {
            keyColumns.put(column.getKey(), column);
        }
        for (DatabaseKey key : keyColumns.keySet()) {
            if (key == null || key == DatabaseKey.NONE) continue;
            final List<DatabaseColumn<?>> groupColumns = keyColumns.get(key);
            if (groupColumns.size() > 1) {
                groupColumnsList.add(new GroupColumns(key, groupColumns));
            }
        }
        return groupColumnsList;
    }

}
