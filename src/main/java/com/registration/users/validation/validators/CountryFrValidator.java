package com.registration.users.validation.validators;

import com.registration.users.validation.annotations.CountryFr;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class CountryFrValidator implements ConstraintValidator<CountryFr, String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null) {
            return true;
        }
        // Perform validation logic to check if the value is a valid country name or code
        // Return true if the country name or code is valid, false otherwise
        return isValidCountryName(value);
    }

    private boolean isValidCountryName(String countryName) {
        // Implement validation logic to check if the country name or code is valid
        return countryName.equalsIgnoreCase("France") || countryName.equalsIgnoreCase("FR");
    }
}