package com.project.model;

import com.project.annotation.GenerateValidator;
import com.project.annotation.NotNull;
import com.project.annotation.NumberRange;
import com.project.annotation.StringLength;

/**
 * A model class representing a User entity.
 * Provides validation constraints for its fields to showcase the capabilities
 * of a custom Annotation Processor.
 */
@GenerateValidator
public class User {
    @NotNull(message = "Username cannot be null")
    @StringLength(min = 3, max = 20, message = "Username must be between {min} and {max} characters")
    private String username;

    @NumberRange(min = 18, max = 120, message = "Age must be between {min} and {max}")
    private int age;

    /**
     * Gets the username.
     *
     * @return the username as a String.
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets the username.
     *
     * @param username the new username to set.
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Gets the user's age.
     *
     * @return the age as an integer.
     */
    public int getAge() {
        return age;
    }

    /**
     * Sets the user's age.
     *
     * @param age the new age to set.
     */
    public void setAge(int age) {
        this.age = age;
    }
}
