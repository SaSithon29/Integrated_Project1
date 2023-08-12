package com.example.int222_backend.validator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import com.example.int222_backend.entities.Category;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = CategoryIdValidator.class)
public @interface CategoryIdExists {
    String message() default "does not exists";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
