package com.project.util;

import com.project.model.User;

public class ManualValidator {
    public static void manualValidate(User user) throws Exception {
        if (user.getUsername() == null || user.getUsername().length() < 3 || user.getUsername().length() > 20) {
            throw new IllegalArgumentException("Invalid username");
        }

        if (user.getAge() == null || user.getAge() < 18 || user.getAge() > 120) {
            throw new IllegalArgumentException("Invalid age");
        }
    }
}