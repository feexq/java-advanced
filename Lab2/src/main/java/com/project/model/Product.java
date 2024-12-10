package com.project.model;

import com.project.anotation.NotNull;
import com.project.anotation.NumberRange;

public class Product {
    @NotNull(message = "Product name cannot be null")
    private String name;

    @NumberRange(min = 0, max = 10000, message = "Price must be between {min} and {max}")
    private Double price;

    public Product(String name, Double price) {
        this.name = name;
        this.price = price;
    }
}
