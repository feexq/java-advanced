# Lab3

## Author

**Mozol Vlad**

## Group

**IA-23**

Number in the group : 11

## Project Description

This project demonstrates the use of an **Annotation Processor** for automatically generating validation logic for Java object fields annotated with custom annotations such as `@NotNull`, `@StringLength`, `@NumberRange`, and `@GenerateValidator`. The `ValidationProcessor` scans the annotated fields and generates corresponding validator classes to enforce field constraints at runtime.

### Key Components:
- **`ValidationProcessor`**: The core annotation processor that validates the usage of custom annotations and generates the appropriate validation code.
- **Custom Annotations**:
    - `@NotNull`: Ensures the annotated field is not null.
    - `@StringLength`: Validates that the length of a string field falls within a specified range.
    - `@NumberRange`: Validates that a numeric field falls within a specified range.
    - `@GenerateValidator`: Tells the annotation processor to generate a corresponding validator class for the annotated class.

The project also includes **test classes** for the `Order`, `Product`, and `User` entities, which demonstrate the functionality of the annotations and the generated validation logic.

## Dependencies

This project requires:
- **Java 17 or higher** for compatibility with the latest language features.
- **Maven** for project building and dependency management.

## Build Instructions

### Step 1: Clone the Repository

Clone the repository to your local machine using Git:

```bash
git clone https://github.com/feexq/java-advanced.git
```

Navigate to lab folder
```bash
cd java-advanced/lab3
```

### Step 2: Building Project
Navigate to the project directory and run the following command to compile the project:
```bash
mvn clean install
```

### Step 3: Open work directory

```bash
cd model
```

### Step 4: Run the Application
1. Compile Java files using:
```bash
javac -d bin src/main/java/com/project/*
```

2. Run the application
```bash
 java -cp bin com.project.PerformanceComparison
```
This will start the program and perform the tasks defined in the Main class.

### Classes
The project includes classes for Order, Product, and User entities that demonstrate how the annotations work and how to use the generated validators. The test classes include:

- Order.java: Represents an order with validated fields.
- Product.java: Represents a product with validated fields.
- User.java: Represents a user with validated fields.

These classes use annotations like @NotNull, @StringLength, and @NumberRange to validate their fields, and the project generates corresponding validators such as OrderValidator, ProductValidator, and UserValidator.

### Important Notes

The annotation processor automatically generates validator classes during the compilation process. The generated code is placed in the same package as the annotated class.
Do not manually edit the generated validator classes, as they will be overwritten during subsequent builds.