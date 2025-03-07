package com.example.telpback.validators;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ActivityValidator.class)
public @interface ActivityConstraints {
    String message() default "Activity must have a single null list. Both lists cannot be null or non-null.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
