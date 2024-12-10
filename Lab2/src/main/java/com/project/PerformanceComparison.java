package com.project;

import com.project.model.Order;
import com.project.model.Product;
import com.project.model.User;
import com.project.util.ManualValidator;
import com.project.util.Validator;

import java.util.List;

/**
 * Main class for comparing manual and reflection-based validation.
 */
public class PerformanceComparison {
    public static void main(String[] args) {
        // Create valid and invalid test cases for User, Product, and Order
        User validUser = new User("petro", 30);
        User invalidUser = new User("a", 150);
        Product validProduct = new Product("product", 1500.0);
        Product invalidProduct = new Product(null, 15000.0);
        Order validOrder = new Order("4455667", 5);
        Order invalidOrder = new Order("123", 150);

        // Measure time for reflection-based validation
        long reflectionStartTime = System.nanoTime();
        List<String> reflectionErrors = Validator.validate(validUser);
        long reflectionEndTime = System.nanoTime();

        // Measure time for manual validation
        long manualStartTime = System.nanoTime();
        try {
            ManualValidator.manualValidate(validUser);
        } catch (Exception e) {
            // Handle validation exceptions
            System.out.println(e.getMessage());
        }
        long manualEndTime = System.nanoTime();

        // Output reflection and manual validation results
        System.out.println("Reflection Validation Errors: " + reflectionErrors);
        System.out.println("Reflection Validation Time: " + (reflectionEndTime - reflectionStartTime) + " ns");
        System.out.println("Manual Validation Time: " + (manualEndTime - manualStartTime) + " ns");

        // Validate and print errors for User
        System.out.println("\nUser Validation:");
        System.out.println(Validator.validate(invalidUser));

        // Validate and print errors for Product
        System.out.println("\nProduct Validation:");
        System.out.println(Validator.validate(validProduct));
        System.out.println(Validator.validate(invalidProduct));

        // Validate and print errors for Order
        System.out.println("\nOrder Validation:");
        System.out.println(Validator.validate(validOrder));
        System.out.println(Validator.validate(invalidOrder));
    }
}