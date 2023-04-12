package com.registration.users.validation.annotations;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import com.registration.users.validation.validators.PhoneNumberFrValidator;

@Target({ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = PhoneNumberFrValidator.class)
public @interface PhoneNumberFr {

    String message() default "Phone number should be in French format (e.g. 06 01 01 01 01, 0601010101, +33701010101)";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}