package com.registration.users.utils;

public class MessagesFunctionalExceptionUtils {
	   
	public static final String MESSAGE_NO_ERRORS = "No Errors";
	public static final String MESSAGE_NAME_TOO_LONG = "Name is too long. The maximum allowed length is %d. Received value: %s";
	public static final String MESSAGE_DATE_OF_BIRTH_INVALID_FORMAT = "Date of Birth should be in the format of '%s', '%s', '%s' or '%s'. Received value: %s";
	public static final String MESSAGE_PHONE_NUMBER_INVALID_FORMAT_FR = "Phone number should be in French format (e.g. 06 01 01 01 01, 0601010101, +33701010101). Received value: %s";
    public static final String MESSAGE_RESIDENT_FRANCE_MANDATORY = "France is the only Country of Residence accepted. Allowed values: FR, FRANCE, France. Received value: %s";
	public static final String MESSAGE_NOT_OLD_ENOUGH = "User has not reached the age of majority witch is %d years.  Received value: %s";
	

	 /**
    * Private constructor to prevent instantiation of the class.
    * This class provides only static methods and should not be instantiated.
    */
	private MessagesFunctionalExceptionUtils() {
		throw new AssertionError("This class should not be instantiated.");
	}
	
	public static String getNullMessage(String paramName) {
		return String.format("'%s' is a mandatory field and cannot be null.", paramName);
	}

	public static String getEmptyMessage(String paramName) {
		return String.format("'%s' is a mandatory field and cannot empty.", paramName);
	}

	public static String getNullOrEmptyMessage(String paramName) {
		return String.format("'%s' is a mandatory field and cannot be null or empty.", paramName);
	}
	
	public static String getMessageNameTooLong(int sizeMax, String receivedValue) {
		return String.format(MESSAGE_NAME_TOO_LONG, sizeMax, receivedValue);	
	}
	
	public static String getMessageDateBirthInvalidFormat(String receivedValue) {
		return String.format(MESSAGE_DATE_OF_BIRTH_INVALID_FORMAT, 
				DateUtils.DATE_FORMAT_DEFAULT, 
				DateUtils.DATE_FORMAT_1, 
				DateUtils.DATE_FORMAT_2, 
				DateUtils.DATE_FORMAT_3,
				receivedValue);	
	}
	
	public static String getMessagePhoneNumberInvalidFr(String receivedValue) {
		return String.format(MESSAGE_PHONE_NUMBER_INVALID_FORMAT_FR, receivedValue);	
	}
	
	public static String getMessageIsNotOldEnoughFr(String receivedValue) {
		return String.format(MESSAGE_NOT_OLD_ENOUGH, Constants.AGE_OF_MAJORITY, receivedValue);	
	}
	
	public static String getMessageCountryResidenceNotFrance(String receivedValue) {
		return  String.format(MESSAGE_RESIDENT_FRANCE_MANDATORY, receivedValue);	
	}
	
	
	public static String getMessageNoErrors() {
		return MESSAGE_NO_ERRORS;
	}


}
