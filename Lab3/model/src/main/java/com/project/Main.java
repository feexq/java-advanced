package com.project;

import com.project.model.*;

/**
 * A demo class to test the generated validation logic.
 * Uses the User, Product, and Order models to validate their fields
 * using custom annotations and an Annotation Processor.
 */
public class Main {

    public static void main(String[] args) {
        testUserValidation();

        testProductValidation();

        testOrderValidation();
    }

    /**
     * Validates the User model with different test cases.
     */
    private static void testUserValidation() {
        System.out.println("User Validation Tests:");

        // Valid User Test
        try {
            User user = new User();
            user.setUsername("JohnDoe");
            user.setAge(25);
            UserValidator.validate(user);
            System.out.println("User is valid");
        } catch (IllegalArgumentException e) {
            System.out.println("Validation error: " + e.getMessage());
        }

        // Invalid Username Test (too short)
        try {
            User invalidUser1 = new User();
            invalidUser1.setUsername("Hi");
            invalidUser1.setAge(25);
            UserValidator.validate(invalidUser1);
        } catch (IllegalArgumentException e) {
            System.out.println("Validation error for short username: " + e.getMessage());
        }

        // Invalid Age Test (underage)
        try {
            User invalidUser2 = new User();
            invalidUser2.setUsername("JohnDoe");
            invalidUser2.setAge(15);
            UserValidator.validate(invalidUser2);
        } catch (IllegalArgumentException e) {
            System.out.println("Validation error for underage user: " + e.getMessage());
        }
    }

    /**
     * Validates the Product model with different test cases.
     */
    private static void testProductValidation() {
        System.out.println("\nProduct Validation Tests:");

        // Valid Product Test
        try {
            Product validProduct = new Product();
            validProduct.setName("Laptop");
            validProduct.setPrice(999.99);
            ProductValidator.validate(validProduct);
            System.out.println("Valid product passed validation");
        } catch (IllegalArgumentException e) {
            System.out.println("Unexpected validation error: " + e.getMessage());
        }

        // Invalid Product Test (null name)
        try {
            Product nullNameProduct = new Product();
            nullNameProduct.setName(null);
            nullNameProduct.setPrice(500);
            ProductValidator.validate(nullNameProduct);
            System.out.println("Null name product validation failed");
        } catch (IllegalArgumentException e) {
            System.out.println("Caught expected error: " + e.getMessage());
        }

        // Invalid Product Test (price out of range)
        try {
            Product invalidPriceProduct = new Product();
            invalidPriceProduct.setName("Smartphone");
            invalidPriceProduct.setPrice(20000);
            ProductValidator.validate(invalidPriceProduct);
            System.out.println("Invalid price product validation failed");
        } catch (IllegalArgumentException e) {
            System.out.println("Caught expected error: " + e.getMessage());
        }
    }

    /**
     * Validates the Order model with different test cases.
     */
    private static void testOrderValidation() {
        System.out.println("\nOrder Validation Tests:");

        // Valid Order Test
        try {
            Order validOrder = new Order();
            validOrder.setOrderId("ORD12345");
            validOrder.setQuantity(50);
            OrderValidator.validate(validOrder);
            System.out.println("Valid order passed validation");
        } catch (IllegalArgumentException e) {
            System.out.println("Unexpected validation error: " + e.getMessage());
        }

        // Invalid Order Test (short ID)
        try {
            Order shortIdOrder = new Order();
            shortIdOrder.setOrderId("ORD1");
            shortIdOrder.setQuantity(50);
            OrderValidator.validate(shortIdOrder);
            System.out.println("Short ID order validation failed");
        } catch (IllegalArgumentException e) {
            System.out.println("Caught expected error: " + e.getMessage());
        }

        // Invalid Order Test (quantity out of range)
        try {
            Order invalidQuantityOrder = new Order();
            invalidQuantityOrder.setOrderId("ORD12345");
            invalidQuantityOrder.setQuantity(150);
            OrderValidator.validate(invalidQuantityOrder);
            System.out.println("Invalid quantity order validation failed");
        } catch (IllegalArgumentException e) {
            System.out.println("Caught expected error: " + e.getMessage());
        }
    }

}
