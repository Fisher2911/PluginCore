package io.github.fisher2911.plugincore.database.annotation;

import io.github.fisher2911.plugincore.database.key.DatabaseKey;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.METHOD})
public @interface Column {

    String value();
    DatabaseKey key() default DatabaseKey.NONE;
    int length() default -1;

}
