package com.project.model;

import com.project.annotation.GenerateValidator;
import com.project.annotation.NotNull;
import com.project.annotation.NumberRange;
import com.project.annotation.StringLength;

/**
 * A model class representing an Order entity.
 * This class is annotated with custom validation annotations to demonstrate
 * the functionality of an Annotation Processor.
 */
@GenerateValidator
public class Order {
    @NotNull(message = "Order ID cannot be null")
    @StringLength(min = 5, max = 10)
    private String orderId;

    @NumberRange(min = 1, max = 100)
    private int quantity;

    /**
     * Gets the order ID.
     *
     * @return the order ID as a String.
     */
    public String getOrderId() {
        return orderId;
    }

    /**
     * Sets the order ID.
     *
     * @param orderId the new order ID to set.
     */
    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    /**
     * Gets the quantity of the order.
     *
     * @return the quantity as an integer.
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * Sets the quantity of the order.
     *
     * @param quantity the new quantity to set.
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

}
