package io.github.fisher2911.plugincore.database.annotation;

import io.github.fisher2911.plugincore.database.key.DatabaseCascadeType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
public @interface Table {

    String value();
    DatabaseCascadeType type() default DatabaseCascadeType.NONE;

}
