package com.project.model;

import com.project.annotation.GenerateValidator;
import com.project.annotation.NotNull;
import com.project.annotation.NumberRange;

@GenerateValidator
public class Product {
    @NotNull(message = "Product name cannot be null")
    private String name;

    @NumberRange(min = 0, max = 10000, message = "Price must be between {min} and {max}")
    private double price;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
