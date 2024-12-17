package com.project.model;

import com.project.annotation.NotNull;
import com.project.annotation.NumberRange;
import com.project.annotation.StringLength;

/**
 * Represents a user entity with validation annotations.
 */
public class User {
    @NotNull(message = "Username cannot be null")
    @StringLength(min = 3, max = 20, message = "Username must be between {min} and {max} characters")
    private String username;

    @NumberRange(min = 18, max = 120, message = "Age must be between {min} and {max}")
    private int age;

    /**
     * Constructs a User instance.
     *
     * @param username the username of the user.
     * @param age the age of the user.
     */
    public User(String username, int age) {
        this.username = username;
        this.age = age;
    }

    /**
     * Gets the username of the user.
     *
     * @return the username.
     */
    public String getUsername() {
        return username;
    }

    /**
     * Gets the age of the user.
     *
     * @return the age.
     */
    public Integer getAge() {
        return age;
    }
}
