package com.registration.users.validation.validators;

import com.registration.users.utils.DateUtils;
import com.registration.users.validation.annotations.DateBirthFormat;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class DateBirthFormatValidator implements ConstraintValidator<DateBirthFormat, String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null) {
            return true;
        }
        // Perform validation logic to check if the value is a valid format date
        // Return true if the format date is valid, false otherwise
        return isDateFormatCorrect(value);
    }


	/**
	 * This method checks whether the given date string is in the correct format.
	 * It returns a boolean value indicating whether the date format
	 * is correct or not.
	 * 
	 * @param date a string representing a date
	 * 
	 * @return true if the date format is correct, false otherwise.
	 */
	private boolean isDateFormatCorrect(String date) {
		return DateUtils.isDateFormatCorrect(date);
	}
}