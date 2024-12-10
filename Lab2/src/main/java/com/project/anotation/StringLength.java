package com.project.anotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotation to enforce string length constraints on fields.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface StringLength {
    /**
     * Minimum allowable string length.
     */
    int min() default 0;

    /**
     * Maximum allowable string length.
     */
    int max() default Integer.MAX_VALUE;

    /**
     * Error message when the string length is out of range.
     */
    String message() default "String length must be between {min} and {max} characters";
}
