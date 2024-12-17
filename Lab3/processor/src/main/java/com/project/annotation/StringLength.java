package com.project.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotation to enforce a specific length range for string fields.
 * Includes customizable minimum and maximum lengths, with a default error message.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface StringLength {
    int min() default 0;
    int max() default Integer.MAX_VALUE;
    String message() default "String length must be between {min} and {max} characters";
}
