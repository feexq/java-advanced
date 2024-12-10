package com.project.util;

import com.project.model.User;

/**
 * Utility class for manual validation of objects.
 */
public class ManualValidator {

    /**
     * Validates a User object manually.
     *
     * @param user the user to validate.
     * @throws Exception if validation fails.
     */
    public static void manualValidate(User user) throws Exception {
        if (user.getUsername() == null || user.getUsername().length() < 3 || user.getUsername().length() > 20) {
            throw new IllegalArgumentException("Invalid username");
        }

        if (user.getAge() == null || user.getAge() < 18 || user.getAge() > 120) {
            throw new IllegalArgumentException("Invalid age");
        }
    }
}
