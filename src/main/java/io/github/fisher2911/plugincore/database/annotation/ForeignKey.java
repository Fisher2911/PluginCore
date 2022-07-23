package io.github.fisher2911.plugincore.database.annotation;

import io.github.fisher2911.plugincore.database.key.DatabaseCascadeType;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({java.lang.annotation.ElementType.FIELD})
public @interface ForeignKey {

    Class<?> linksTo();
    String references();
    DatabaseCascadeType cascade() default DatabaseCascadeType.NONE;

}
