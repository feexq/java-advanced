package com.project.processor;

import com.project.annotation.NotNull;
import com.project.annotation.NumberRange;
import com.project.annotation.StringLength;

import javax.annotation.processing.*;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.TypeElement;
import javax.lang.model.type.TypeKind;
import javax.lang.model.type.TypeMirror;
import javax.tools.Diagnostic;
import java.util.Set;


/**
 * Processor class that validates the application of custom annotations (@NotNull, @StringLength, @NumberRange)
 * to the fields of classes. Ensures that annotations are applied to the correct field types
 * and validates that the annotation constraints are not violated.
 * This class processes annotations during the compile-time annotation processing.
 */
@SupportedAnnotationTypes({
        "com.project.annotation.NumberRange",
        "com.project.annotation.NotNull",
        "com.project.annotation.StringLength",
})
@SupportedSourceVersion(SourceVersion.RELEASE_17)
public class ValidationProcessor extends AbstractProcessor {

    /**
     * Initializes the processing environment.
     *
     * @param processingEnv the processing environment provided by the annotation processing tool
     */
    @Override
    public synchronized void init(ProcessingEnvironment processingEnv) {
        super.init(processingEnv);
    }


    /**
     * Processes the annotations found in the source code.
     *
     * This method checks whether the annotations are applied to valid field types and ensures
     * the constraints defined in the annotations are adhered to.
     *
     * @param annotations the set of annotations to be processed
     * @param roundEnv the environment in which the round of annotation processing is executed
     * @return true if the annotations are processed successfully
     */
    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        // Processing @NotNull annotations
        for (Element element : roundEnv.getElementsAnnotatedWith(NotNull.class)) {
            if (element.getKind() != ElementKind.FIELD) {
                processingEnv.getMessager().printMessage(
                        Diagnostic.Kind.ERROR,
                        "@NotNull can only be applied to fields",
                        element
                );
                continue;
            }

            // Check if the field type is numeric, which is not allowed for @NotNull
            TypeMirror typeMirror = element.asType();
            if (isNumericType(typeMirror)) {
                processingEnv.getMessager().printMessage(
                        Diagnostic.Kind.ERROR,
                        "@NotNull can't be applied to numeric types",
                        element
                );
            }
        }

        // Processing @StringLength annotations
        for (Element element : roundEnv.getElementsAnnotatedWith(StringLength.class)) {
            // Check if the annotation is applied to a field
            if (element.getKind() != ElementKind.FIELD) {
                processingEnv.getMessager().printMessage(
                        Diagnostic.Kind.ERROR,
                        "@StringLength can only be applied to fields",
                        element
                );
                continue;
            }

            // Check if the field is of type String
            TypeMirror typeMirror = element.asType();
            if (!typeMirror.toString().equals("java.lang.String")) {
                processingEnv.getMessager().printMessage(
                        Diagnostic.Kind.ERROR,
                        "@StringLength can only be applied to String fields",
                        element
                );
                continue;
            }

            // Check if min length is not greater than max length
            StringLength annotation = element.getAnnotation(StringLength.class);
            if (annotation.min() > annotation.max()) {
                processingEnv.getMessager().printMessage(
                        Diagnostic.Kind.ERROR,
                        "min length cannot be greater than max length",
                        element);
            }
        }

        // Processing @NumberRange annotations
        for (Element element : roundEnv.getElementsAnnotatedWith(NumberRange.class)) {
            // Check if the annotation is applied to a field
            if (element.getKind() != ElementKind.FIELD) {
                processingEnv.getMessager().printMessage(
                        Diagnostic.Kind.ERROR,
                        "@NumberRange can only be applied to fields",
                        element
                );
                continue;
            }

            // Check if the field type is numeric
            TypeMirror typeMirror = element.asType();
            if (!isNumericType(typeMirror)) {
                processingEnv.getMessager().printMessage(
                        Diagnostic.Kind.ERROR,
                        "@NumberRange can only be applied to numeric types",
                        element
                );
                continue;
            }

            // Check if min value is not greater than max value
            NumberRange annotation = element.getAnnotation(NumberRange.class);
            if (annotation.min() > annotation.max()) {
                processingEnv.getMessager().printMessage(
                        Diagnostic.Kind.ERROR,
                        "Min value cannot be greater than max value",
                        element
                );
            }
        }

        return true;
    }

    /**
     * Checks if the provided type mirror represents a numeric type.
     *
     * @param type the type mirror to check
     * @return true if the type is numeric, false otherwise
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


