package io.github.fisher2911.plugincore;

import io.github.fisher2911.plugincore.database.example.PlayerExample;
import io.github.fisher2911.plugincore.database.parser.RegisteredDataTables;
import io.github.fisher2911.plugincore.database.table.DatabaseTable;

import java.util.HashMap;

public final class PluginCore/* extends JavaPlugin*/ {

    public static void main(String[] args) {
        final DatabaseTable table = new RegisteredDataTables(new HashMap<>()).parse(PlayerExample.class);
        if (table == null) System.out.println("Table is null");
        else System.out.println(table.getSqlString());

//        ServiceLoader<DatabaseProcessor> loader = ServiceLoader.load(DatabaseProcessor.class);
//        System.out.println(loader.findFirst().get());

    }

/*    @Override
    public void onEnable() {
    }

    @Override
    public void onDisable() {
    }*/
}
