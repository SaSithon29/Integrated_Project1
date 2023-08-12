package com.example.int222_backend.validator;

import jakarta.validation.*;

import java.lang.annotation.*;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = DisplayValidator.class)
public @interface YN {

        String message() default "must be 'Y' or 'N'";
        Class<?>[] groups() default {};
        Class<? extends Payload>[] payload() default {};
    }


