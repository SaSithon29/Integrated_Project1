package com.example.int222_backend.validator;


import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = CloseDateValide.class)
public @interface ValidDate {
    String message() default "must be later than publish date";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
