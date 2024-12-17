package com.project.util;

import com.project.annotation.NotNull;
import com.project.annotation.NumberRange;
import com.project.annotation.StringLength;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * Utility class for reflection-based validation.
 */
public class Validator {

    /**
     * Validates an object using reflection and annotations.
     *
     * @param object the object to validate.
     * @param <T> the type of the object.
     * @return a list of validation error messages.
     */
    public static <T> List<String> validate(T object) {
        List<String> validationErrors = new ArrayList<>();

        for (Field field : object.getClass().getDeclaredFields()) {
            field.setAccessible(true);

            try {
                if (field.isAnnotationPresent(NotNull.class)) {
                    NotNull notNull = field.getAnnotation(NotNull.class);
                    if (field.get(object) == null) {
                        validationErrors.add(notNull.message());
                    }
                }

                if (field.isAnnotationPresent(StringLength.class)) {
                    StringLength stringLength = field.getAnnotation(StringLength.class);
                    Object value = field.get(object);

                    if (value != null && !(value instanceof String)) {
                        throw new IllegalArgumentException("StringLength annotation can only be used with String fields");
                    }

                    String strValue = (String) value;
                    if (strValue != null && (strValue.length() < stringLength.min() || strValue.length() > stringLength.max())) {
                        String message = stringLength.message()
                                .replace("{min}", String.valueOf(stringLength.min()))
                                .replace("{max}", String.valueOf(stringLength.max()));
                        validationErrors.add(message);
                    }
                }

                if (field.isAnnotationPresent(NumberRange.class)) {
                    NumberRange numberRange = field.getAnnotation(NumberRange.class);
                    Object value = field.get(object);

                    if (value != null && !(value instanceof Number)) {
                        throw new IllegalArgumentException("NumberRange annotation can only be used with numeric fields");
                    }

                    if (value != null) {
                        double doubleValue = ((Number) value).doubleValue();
                        if (doubleValue < numberRange.min() || doubleValue > numberRange.max()) {
                            String message = numberRange.message()
                                    .replace("{min}", String.valueOf(numberRange.min()))
                                    .replace("{max}", String.valueOf(numberRange.max()));
                            validationErrors.add(message);
                        }
                    }
                }
            } catch (IllegalAccessException e) {
                validationErrors.add("Error accessing field: " + field.getName());
            } catch (IllegalArgumentException e) {
                validationErrors.add(e.getMessage());
            }
        }

        return validationErrors;
    }
}
