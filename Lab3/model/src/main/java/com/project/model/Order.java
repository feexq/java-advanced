package com.project.model;

import com.project.annotation.GenerateValidator;
import com.project.annotation.NotNull;
import com.project.annotation.NumberRange;
import com.project.annotation.StringLength;

@GenerateValidator
public class Order {
    @NotNull(message = "Order ID cannot be null")
    @StringLength(min = 5, max = 10)
    private String orderId;

    @NumberRange(min = 1, max = 100)
    private int quantity;

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

}
