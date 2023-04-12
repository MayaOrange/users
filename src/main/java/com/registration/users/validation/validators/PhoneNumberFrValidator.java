package com.registration.users.validation.validators;

import com.registration.users.utils.PhoneNumberUtils;
import com.registration.users.validation.annotations.PhoneNumberFr;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PhoneNumberFrValidator implements ConstraintValidator<PhoneNumberFr, String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null) {
            return true;
        }
        // Perform validation logic to check if the value is a valid format date
        // Return true if the format date is valid, false otherwise
        return isFrenchNumberValid(value);
    }

    /**
	 * Validates whether the given phone number is a valid French phone number.
	 * @param phoneNumber the phone number to validate.
	 * @return true if the phone number is valid, false otherwise.
	 */
	private boolean isFrenchNumberValid (String phoneNumber) {
		return PhoneNumberUtils.isFrenchNumberValid(phoneNumber);
		
	}
}