package com.project.model;

import com.project.anotation.NotNull;
import com.project.anotation.NumberRange;
import com.project.anotation.StringLength;

public class Order {
    @NotNull(message = "Order ID cannot be null")
    @StringLength(min = 5, max = 10)
    private String orderId;

    @NumberRange(min = 1, max = 100)
    private Integer quantity;

    public Order(String orderId, Integer quantity) {
        this.orderId = orderId;
        this.quantity = quantity;
    }
}
