package com.registration.users.validation.annotations;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import com.registration.users.validation.validators.DateBirthFormatValidator;

@Target({ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = DateBirthFormatValidator.class)
public @interface DateBirthFormat {

    String message() default "Date of birth must be in the formats dd/MM/yyyy | dd-MM-yyyy | yyyy/MM/dd | yyyy-MM-dd.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}