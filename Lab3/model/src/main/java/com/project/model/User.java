package com.project.model;

import com.project.annotation.GenerateValidator;
import com.project.annotation.NotNull;
import com.project.annotation.NumberRange;
import com.project.annotation.StringLength;

@GenerateValidator
public class User {
    @NotNull(message = "Username cannot be null")
    @StringLength(min = 3, max = 20, message = "Username must be between {min} and {max} characters")
    private String username;

    @NumberRange(min = 18, max = 120, message = "Age must be between {min} and {max}")
    private int age;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
