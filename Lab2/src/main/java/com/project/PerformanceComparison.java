package com.project;

import com.project.model.Order;
import com.project.model.Product;
import com.project.model.User;
import com.project.util.ManualValidator;
import com.project.util.Validator;

import java.util.List;

public class PerformanceComparison {
    public static void main(String[] args) {
        User validUser = new User("petro", 30);
        User invalidUser = new User("a", 150);
        Product validProduct = new Product("product", 1500.0);
        Product invalidProduct = new Product(null, 15000.0);
        Order validOrder = new Order("4455667", 5);
        Order invalidOrder = new Order("123", 150);

        long reflectionStartTime = System.nanoTime();
        List<String> reflectionErrors = Validator.validate(validUser);
        long reflectionEndTime = System.nanoTime();

        long manualStartTime = System.nanoTime();
        try {
            ManualValidator.manualValidate(validUser);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        long manualEndTime = System.nanoTime();

        System.out.println("Reflection Validation Errors: " + reflectionErrors);
        System.out.println("Reflection Validation Time: " + (reflectionEndTime - reflectionStartTime) + " ns");
        System.out.println("Manual Validation Time: " + (manualEndTime - manualStartTime) + " ns");

        System.out.println("\nUser Validation:");
        System.out.println(Validator.validate(invalidUser));

        System.out.println("\nProduct Validation:");
        System.out.println(Validator.validate(validProduct));
        System.out.println(Validator.validate(invalidProduct));

        System.out.println("\nOrder Validation:");
        System.out.println(Validator.validate(validOrder));
        System.out.println(Validator.validate(invalidOrder));
    }
}