package com.project;

import com.project.annotation.GenerateValidator;
import com.project.annotation.NotNull;
import com.project.annotation.NumberRange;
import com.project.annotation.StringLength;

import javax.annotation.processing.*;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.*;
import javax.lang.model.type.TypeKind;
import javax.lang.model.type.TypeMirror;
import javax.tools.Diagnostic;
import javax.tools.JavaFileObject;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.Set;

/**
 * Annotation processor for validating custom annotations related to field constraints and generating validators.
 * This processor handles annotations such as {@link NotNull}, {@link StringLength}, {@link NumberRange}, and {@link GenerateValidator}.
 */
@SupportedAnnotationTypes({
        "com.project.annotation.NumberRange",
        "com.project.annotation.NotNull",
        "com.project.annotation.StringLength",
        "com.project.annotation.GenerateValidator",
})
@SupportedSourceVersion(SourceVersion.RELEASE_17)
public class ValidationProcessor extends AbstractProcessor {

    private Messager messager;

    /**
     * Initializes the processor with a Messager instance for error reporting.
     *
     * @param processingEnv the environment for annotation processing.
     */
    @Override
    public synchronized void init(ProcessingEnvironment processingEnv) {
        super.init(processingEnv);
        messager = processingEnv.getMessager();
    }

    /**
     * Processes the annotations to validate their usage and generate validator classes.
     *
     * @param annotations the annotations to process.
     * @param roundEnv the environment for the current processing round.
     * @return true if the annotations are processed successfully.
     */
    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        // Validates the usage of NotNull annotations.
        for (Element element : roundEnv.getElementsAnnotatedWith(NotNull.class)) {
            if (element.getKind() != ElementKind.FIELD) {
                processingEnv.getMessager().printMessage(
                        Diagnostic.Kind.ERROR,
                        "@NotNull can only be applied to fields",
                        element
                );
                continue;
            }

            TypeMirror typeMirror = element.asType();
            if (isNumericType(typeMirror)) {
                processingEnv.getMessager().printMessage(
                        Diagnostic.Kind.ERROR,
                        "@NotNull can't be applied to numeric types",
                        element
                );
            }
        }

        // Validates the usage of StringLength annotations.
        for (Element element : roundEnv.getElementsAnnotatedWith(StringLength.class)) {
            if (element.getKind() != ElementKind.FIELD) {
                processingEnv.getMessager().printMessage(
                        Diagnostic.Kind.ERROR,
                        "@StringLength can only be applied to fields",
                        element
                );
                continue;
            }

            TypeMirror typeMirror = element.asType();
            if (!typeMirror.toString().equals("java.lang.String")) {
                processingEnv.getMessager().printMessage(
                        Diagnostic.Kind.ERROR,
                        "@StringLength can only be applied to String fields",
                        element
                );
                continue;
            }

            StringLength annotation = element.getAnnotation(StringLength.class);
            if (annotation.min() > annotation.max()) {
                processingEnv.getMessager().printMessage(
                        Diagnostic.Kind.ERROR,
                        "min length cannot be greater than max length",
                        element);
            }
        }

        // Validates the usage of NumberRange annotations.
        for (Element element : roundEnv.getElementsAnnotatedWith(NumberRange.class)) {
            if (element.getKind() != ElementKind.FIELD) {
                processingEnv.getMessager().printMessage(
                        Diagnostic.Kind.ERROR,
                        "@NumberRange can only be applied to fields",
                        element
                );
                continue;
            }

            TypeMirror typeMirror = element.asType();
            if (!isNumericType(typeMirror)) {
                processingEnv.getMessager().printMessage(
                        Diagnostic.Kind.ERROR,
                        "@NumberRange can only be applied to numeric types",
                        element
                );
                continue;
            }

            NumberRange annotation = element.getAnnotation(NumberRange.class);
            if (annotation.min() > annotation.max()) {
                processingEnv.getMessager().printMessage(
                        Diagnostic.Kind.ERROR,
                        "Min value cannot be greater than max value",
                        element
                );
            }
        }

        // Generates validator classes for elements annotated with GenerateValidator.
        for (Element element : roundEnv.getElementsAnnotatedWith(GenerateValidator.class)) {
            if (element.getKind() == ElementKind.CLASS) {
                try {
                    generateValidator((TypeElement) element);
                } catch (IOException e) {
                    messager.printMessage(Diagnostic.Kind.ERROR, "Failed to generate validator: " + e.getMessage());
                }
            }
        }

        return true;
    }

    /**
     * Generates a validator class for a given type.
     *
     * This method creates a new Java class file that contains validation logic for
     * fields annotated with custom annotations such as {@link NotNull}, {@link StringLength}, and {@link NumberRange}.
     *
     * @param classElement the element representing the class for which the validator is generated.
     * @throws IOException if an error occurs while writing the generated class.
     */
    private void generateValidator(TypeElement classElement) throws IOException {
        String packageName = processingEnv.getElementUtils().getPackageOf(classElement).toString();
        String className = classElement.getSimpleName() + "Validator";
        String qualifiedClassName = packageName + "." + className;

        JavaFileObject file = processingEnv.getFiler().createSourceFile(qualifiedClassName);
        try (BufferedWriter writer = new BufferedWriter(file.openWriter())) {
            // Write package declaration
            writer.write("package " + packageName + ";\n\n");

            // Add Javadoc for the class
            writer.write("/**\n");
            writer.write(" * Auto-generated validator for the " + classElement.getSimpleName() + " class.\n");
            writer.write(" *\n");
            writer.write(" * This class is generated by the Annotation Processor based on the annotations\n");
            writer.write(" * provided in the " + classElement.getSimpleName() + " class.\n");
            writer.write(" *\n");
            writer.write(" * DO NOT MODIFY THIS CLASS MANUALLY. Any changes will be\n");
            writer.write(" * overridden when the class is regenerated. To modify the validation logic,\n");
            writer.write(" * update the annotated class or the Annotation Processor itself.\n");
            writer.write(" */\n");

            // Write class declaration
            writer.write("public class " + className + " {\n");

            // Write validate method
            writer.write("    public static void validate(" + classElement.getSimpleName() + " object) {\n");

            // Generate validation logic
            for (Element enclosed : classElement.getEnclosedElements()) {
                if (enclosed.getKind() == ElementKind.FIELD) {
                    VariableElement field = (VariableElement) enclosed;
                    writeFieldValidation(writer, field);
                }
            }

            writer.write("    }\n");
            writer.write("}\n");
        }
    }

    /**
     * Writes validation logic for a single field.
     *
     * @param writer the writer to output the generated code.
     * @param field the field to generate validation logic for.
     * @throws IOException if an error occurs while writing to the file.
     */
    private void writeFieldValidation(BufferedWriter writer, VariableElement field) throws IOException {
        String fieldName = field.getSimpleName().toString();
        String getterName = "get" + Character.toUpperCase(fieldName.charAt(0)) + fieldName.substring(1);

        // NotNull validation
        NotNull notNullAnnotation = field.getAnnotation(NotNull.class);
        if (notNullAnnotation != null) {
            writer.write("        if (object." + getterName + "() == null) {\n");
            writer.write("            throw new IllegalArgumentException(\"" +
                    notNullAnnotation.message() + "\");\n");
            writer.write("        }\n");
        }

        StringLength stringLengthAnnotation = field.getAnnotation(StringLength.class);
        if (stringLengthAnnotation != null) {
            String message = stringLengthAnnotation.message()
                    .replace("{min}", String.valueOf(stringLengthAnnotation.min()))
                    .replace("{max}", String.valueOf(stringLengthAnnotation.max()));

            writer.write("        if (object." + getterName + "() != null && (object." +
                    getterName + "().length() < " + stringLengthAnnotation.min() +
                    " || object." + getterName + "().length() > " + stringLengthAnnotation.max() + ")) {\n");
            writer.write("            throw new IllegalArgumentException(\"" +
                    message + "\");\n");
            writer.write("        }\n");
        }

        // NumberRange validation
        NumberRange numberRangeAnnotation = field.getAnnotation(NumberRange.class);
        if (numberRangeAnnotation != null) {
            String message = numberRangeAnnotation.message()
                    .replace("{min}", String.valueOf(numberRangeAnnotation.min()))
                    .replace("{max}", String.valueOf(numberRangeAnnotation.max()));

            writer.write("        if (object." + getterName + "() < " + numberRangeAnnotation.min() +
                    " || object." + getterName + "() > " + numberRangeAnnotation.max() + ") {\n");
            writer.write("            throw new IllegalArgumentException(\"" +
                    message + "\");\n");
            writer.write("        }\n");
        }
    }

    /**
     * Checks if a type is numeric.
     *
     * @param type the type to check.
     * @return true if the type is numeric, false otherwise.
     */
    private boolean isNumericType(TypeMirror type) {
        return type.getKind().isPrimitive() &&
                (type.getKind() == TypeKind.BYTE ||
                        type.getKind() == TypeKind.SHORT ||
                        type.getKind() == TypeKind.INT ||
                        type.getKind() == TypeKind.LONG ||
                        type.getKind() == TypeKind.FLOAT ||
                        type.getKind() == TypeKind.DOUBLE);
    }

}


