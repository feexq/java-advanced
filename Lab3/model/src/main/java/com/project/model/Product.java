package com.project.model;

import com.project.annotation.GenerateValidator;
import com.project.annotation.NotNull;
import com.project.annotation.NumberRange;

/**
 * A model class representing a Product entity.
 * Demonstrates validation annotations for field constraints using an Annotation Processor.
 */
@GenerateValidator
public class Product {
    @NotNull(message = "Product name cannot be null")
    private String name;

    @NumberRange(min = 0, max = 10000, message = "Price must be between {min} and {max}")
    private double price;

    /**
     * Gets the product name.
     *
     * @return the product name as a String.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the product name.
     *
     * @param name the new name to set.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the price of the product.
     *
     * @return the price as a double.
     */
    public double getPrice() {
        return price;
    }

    /**
     * Sets the price of the product.
     *
     * @param price the new price to set.
     */
    public void setPrice(double price) {
        this.price = price;
    }
}
