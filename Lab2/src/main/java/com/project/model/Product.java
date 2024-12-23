package com.project.model;

import com.project.annotation.NotNull;
import com.project.annotation.NumberRange;

/**
 * Represents a product entity with validation annotations.
 */
public class Product {
    @NotNull(message = "Product name cannot be null")
    private String name;

    @NumberRange(min = 0, max = 10000, message = "Price must be between {min} and {max}")
    private double price;

    /**
     * Constructs a Product instance.
     *
     * @param name the name of the product.
     * @param price the price of the product.
     */
    public Product(String name, double price) {
        this.name = name;
        this.price = price;
    }
}
