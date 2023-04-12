package com.registration.users.utils;

/**
 * Utility class for validating phone numbers.
 */
public class PhoneNumberUtils {
	
	/*
	 * The regex pattern is used to validate French phone numbers
	 *
	 * Phone Number valid example :
	 * - 0644444444
	 * - 06 44 44 44 44
	 * - 06-44-44-44-44
	 * - 06.44.44.44.44
	 * International formats
	 * - 33644444444 
	 * - +336.44.44.44.44
	 * - +33 6.44.44.44.44
	 * - 003364444444
	 * - 00336.44.44.44.44
	 * - 0033 6.44.44.44.44
	 * sometimes used
	 * - +33(0)644444444
	 * - +33 (0) 644444444
	 */

	public static final String PHONE_NUMBER_FRENCH_REGEX = "^" // Start
			// Matches either the dialing code "+33" or "0033" with optional whitespace and
			// optional "(0)" in parentheses, followed by a space or not, or a plain "0"
			+ "(?:(?:(?:\\+|00)33[ ]?(?:\\(0\\)[ ]?)?)|0){1}"
			// Matches any non-zero digit between 1 and 9.
			+ "[1-9]{1}"
			// Captures an optional separator character (a space, dot, or hyphen).
			+ "([ .-]?)"
			// Matches three sets of two digits, optionally separated by the same separator
			// character captured earlier.
			+ "(?:\\d{2}\\1?){3}"
			// Matches the last two digits of the phone number.
			+ "\\d{2}"
			// Fin
			+ "$";
	
	/**
	 * Private constructor to prevent instantiation of this utility class.
	 * @throws AssertionError if this constructor is called.
	 */
	private PhoneNumberUtils(){
		throw new AssertionError("This class should not be instantiated.");
	}
	
	/**
	 * Validates whether the given phone number is a valid French phone number.
	 * @param phoneNumber the phone number to validate.
	 * @return true if the phone number is valid, false otherwise.
	 */
	public static boolean isFrenchNumberValid (String phoneNumber) {
		if (phoneNumber == null || phoneNumber.isEmpty()) {
			return false;
		}
		
		return phoneNumber.matches(PHONE_NUMBER_FRENCH_REGEX);
	}
	
}
