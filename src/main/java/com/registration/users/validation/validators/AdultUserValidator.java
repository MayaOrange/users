package com.registration.users.validation.validators;

import java.time.LocalDate;
import java.time.Period;

import com.registration.users.utils.Constants;
import com.registration.users.utils.DateUtils;
import com.registration.users.validation.annotations.AdultUser;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class AdultUserValidator implements ConstraintValidator<AdultUser, String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null) {
            return true;
        }
        // Perform validation logic to check if the user is adult by his birth date
        // Return true if the user is adult , false otherwise
        return isOldEnoughToBeLegal(value);
    }

    /**
	 * Checks if a person is an adult based on their date of birth.
	 *
	 * @param birthDate the person's date of birth
	 * @return true if the person is an adult, false otherwise
	 */
	private boolean isOldEnoughToBeLegal(String birthDateIn) {
		LocalDate birthDate = DateUtils.parseDate(birthDateIn);
		if (birthDate != null) {
			LocalDate currentDate = LocalDate.now();
			Period age = Period.between(birthDate, currentDate);
			return age.getYears() >= Constants.AGE_OF_MAJORITY;
		} else {
			return true; /*if date not valid, we don't throw exception Not an adult*/
		}
	}
}