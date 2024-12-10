# Lab 1

## Author

**Mozol Vlad**

## Group

**IA-23**

## Description

This project simulates a collection of mythical creatures, each having attributes such as name, type, first mention date, and attack power. The main tasks include filtering, grouping, and analyzing these creatures. The creatures are categorized by types such as UPYR, MAVKA, DOMOVYK, and others, and various operations are performed to gather, analyze, and display statistics about them.

Key Features:
- Generates random mythical creatures with varying attributes.
- Implements tasks such as filtering creatures by type, grouping by type, and calculating attack statistics.
- Uses streams and collectors to process and analyze data.

## Project Structure

The project consists of the following main components:
- **Model Classes**: Define the structure for mythical creatures and attack statistics.
- **Utility Classes**: Provide functionality for generating creatures, collecting statistics, and filtering based on specific criteria.
- **Tasks**: Contain the main operations (e.g., grouping by type, analyzing attack power, etc.).

## Requirements

- Java 23 
- Maven (or any build tool of your choice)

## How to Build and Run

### Step 1: Clone the repository
Clone the repository to your local machine:
```bash
git clone https://github.com/feexq/java-advanced.git
```

Navigate to lab folder
```bash
cd java-advanced/lab1
```

### Step 2: Compile the project

Navigate to the project directory and run the following command to compile the project:

```bash
mvn clean install
```

### Step 3: Building Project

Compile project with maven
```bash
mvn compile
```

### Step 4: Run the project

You can run the project using the following command:

1. Compile Java files using:
```bash
javac --enable-preview --release 23 -Xlint:preview -d bin src/main/java/com/project/Main.java src/main/java/com/project/common/*.java src/main/java/com/project/model/*.java src/main/java/com/project/task/*.java src/main/java/com/project/util/*.java 
```
2. Run the application
```bash
java --enable-preview -cp bin com.project.Main
```
This will start the program and perform the tasks defined in the Main class.

### Usage
 - The Main class executes the core tasks and prints the results.
 - The tasks include filtering creatures, grouping them by type, analyzing attack statistics, and detecting outliers in attack power.

