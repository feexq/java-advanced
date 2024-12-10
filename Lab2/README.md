# Lab 2

## Author

**Mozol Vlad**

## Group

**IA-23**

Number in the group : 11

## Description
This project demonstrates the implementation of a validation framework for Java objects using custom annotations and reflection. The project compares the performance of manual validation with reflection-based validation and provides a flexible way to validate object fields.

### Key Features
- **Custom Annotations**: Implements annotations such as `@NotNull`, `@NumberRange`, and `@StringLength` to validate fields.
- **Reflection-Based Validation**: A general-purpose validator that dynamically validates objects based on annotations.
- **Manual Validation**: Demonstrates manual validation as a baseline for performance comparison.
- **Performance Metrics**: Compares the execution time of manual and reflection-based validation.

## Project Structure
```
project/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   ├── com/project/anotation/      # Custom annotations
│   │   │   ├── com/project/model/          # Model classes (User, Product, Order)
│   │   │   ├── com/project/util/           # Validation utilities
│   │   │   └── com/project/                # Main class
│   └── test/                               # Test cases (if any)
├── README.md                               # Project documentation
└── pom.xml                                 # Maven build file
```

## Requirements
- **Java 17 or later**
- **Maven 3.8 or later**

## Build and Run Instructions

### Step 1: Clone the Repository
```bash
git clone https://github.com/feexq/java-advanced.git
```
Navigate to lab folder
```bash
cd java-advanced/lab2
```

### Step 2: Building Project
Navigate to the project directory and run the following command to compile the project:

```bash
mvn clean install
```

### Step 3: Compile the project
Compile project with maven
```bash
mvn compile
```

### Step 4: Run the Application
1. Compile Java files using:
```bash
javac -d bin src/main/java/com/project/PerformanceComparison.java src/main/java/com/project/anotation/*.java src/main/java/com/project/model/*.java src/main/java/com/project/util/*.java 
```
2. Run the application
```bash
java -cp bin com.project.PerformanceComparison
```
This will start the program and perform the tasks defined in the Main class.


## Expected Output
The application will display the following:
1. Errors from reflection-based validation.
2. Time taken for reflection-based validation.
3. Time taken for manual validation.
4. Detailed validation errors for `User`, `Product`, and `Order` objects.

## Example Output
```
Reflection Validation Errors: []
Reflection Validation Time: 123456 ns
Manual Validation Time: 7890 ns

User Validation:
["Username must be between 3 and 20 characters", "Age must be between 18 and 120"]

Product Validation:
[]
["Product name cannot be null", "Price must be between 0 and 10000"]

Order Validation:
[]
["String length must be between 5 and 10 characters", "Number must be between 1 and 100"]
```

---

**Enjoy using the project!**
