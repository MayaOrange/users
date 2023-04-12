package com.registration.users.validation.annotations;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.registration.users.validation.validators.AdultUserValidator;

@Target({ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = AdultUserValidator.class)
public @interface AdultUser {

    String message() default "User has not reached the age of majority witch is 18 years";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}