package com.samples.demo.annotation.custom;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import java.util.Map;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Retention(RUNTIME)
@Target({ FIELD })
public @interface JsonArray {
    public String key() default "";
}
