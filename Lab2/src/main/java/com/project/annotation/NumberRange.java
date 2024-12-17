package com.project.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


/**
 * Annotation to enforce a range constraint on numeric fields.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface NumberRange {
    /**
     * Minimum allowable value.
     */
    double min() default Double.NEGATIVE_INFINITY;

    /**
     * Maximum allowable value.
     */
    double max() default Double.POSITIVE_INFINITY;

    /**
     * Error message when the number is out of range.
     */
    String message() default "Number must be between {min} and {max}";
}
