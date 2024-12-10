package com.project.model;

import com.project.anotation.NotNull;
import com.project.anotation.NumberRange;
import com.project.anotation.StringLength;

/**
 * Represents an order entity with validation annotations.
 */
public class Order {
    @NotNull(message = "Order ID cannot be null")
    @StringLength(min = 5, max = 10)
    private String orderId;

    @NumberRange(min = 1, max = 100)
    private Integer quantity;

    /**
     * Constructs an Order instance.
     *
     * @param orderId the unique identifier of the order.
     * @param quantity the quantity of items in the order.
     */
    public Order(String orderId, Integer quantity) {
        this.orderId = orderId;
        this.quantity = quantity;
    }
}
