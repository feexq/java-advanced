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

        System.out.println("\nUser Validation:");
        System.out.println(Validator.validate(invalidUser));
        System.out.println(Validator.validate(validUser));

        System.out.println("\nProduct Validation:");
        System.out.println(Validator.validate(validProduct));
        System.out.println(Validator.validate(invalidProduct));

        System.out.println("\nOrder Validation:");
        System.out.println(Validator.validate(validOrder));
        System.out.println(Validator.validate(invalidOrder));
    }
}