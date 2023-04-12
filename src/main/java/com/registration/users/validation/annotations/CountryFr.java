package com.registration.users.validation.annotations;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import com.registration.users.validation.validators.CountryFrValidator;

@Target({ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = CountryFrValidator.class)
public @interface CountryFr {

    String message() default "France is the only Country of Residence accepted. Allowed values: FR, FRANCE, France.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}