package com.registration.users.utils;


/**
 * The GenderUtils class provides utility methods for working with gender-related data, such as converting gender codes to labels and vice versa.
 * This class cannot be instantiated and provides only static methods.
 */
public class GenderUtils {

	/**
	 * The GenderEnum enum represents the possible genders in the system, with a label representing their textual representation.
	 */
	public enum GenderEnum {
		MALE("Male"),
		FEMALE("Female"),
		NON_BINARY("Non-Binary"),
		BINARY("Non-Binary"),
		UNKOWN("Unknown");

	    private final String label;

	    /**
	     * Constructs a GenderEnum object with the given label.
	     * @param label the textual representation of the gender.
	     */
	    private GenderEnum(String label) {
	        this.label = label;
	    }

	    /**
	     * Returns the label associated with this GenderEnum object.
	     * @return the textual representation of the gender.
	     */
	    public String getLabel() {
	        return label;
	    }
	}
	
	/**
	 * Private constructor to prevent instantiation of the class.
	 * This class should be accessed only through its static methods.
	 * @throws AssertionError if this constructor is called.
	 */
	private GenderUtils() {
		throw new AssertionError("This class should not be instantiated.");
	} 
	
	/**
	 * Returns the label associated with the gender code specified as input.
	 * If no gender with that code exists, the label for the UNKNOWN gender is returned.
	 * @param code the gender code to look up.
	 * @return the label associated with the gender code.
	 */
	public static String getLabelGenderByCode(String code) {
		for (GenderEnum gender : GenderEnum.values()) {
			if (gender.name().equalsIgnoreCase(code)) {
				return gender.getLabel();
			}
		}
		return GenderEnum.UNKOWN.getLabel();
	}
	
	/**
	 * Returns the code associated with the gender label specified as input.
	 * If no gender with that label exists, the code for the UNKNOWN gender is returned.
	 * @param label the gender label to look up.
	 * @return the code associated with the gender label.
	 */
	public static String getCodeGenderByLabel(String label) {
		for (GenderEnum gender : GenderEnum.values()) {
			if (gender.getLabel().equalsIgnoreCase(label)) {
				return gender.name();
			}
		}
		/*exceptional for Non-Binary to accept binary word ignoring case */
		if (label != null && label.toUpperCase().contains(GenderEnum.BINARY.name())) {
			return GenderEnum.NON_BINARY.name();
		}
		
		return GenderEnum.UNKOWN.name();
	}
	
	/**
	 * Returns GenderEnum associated with the gender code specified as input.
	 * If no gender with that code exists, UNKNOWN gender is returned.
	 * @param code the gender code to look up.
	 * @return GenderEnum associated with the gender code.
	 */
	public static GenderEnum getGenderByCode(String code) {
		for (GenderEnum gender : GenderEnum.values()) {
			if (gender.name().equalsIgnoreCase(code)) {
				return gender;
			}
		}
		return GenderEnum.UNKOWN;
	}
	

	/**
	 * Returns GenderEnum associated with the gender label specified as input.
	 * If no gender with that code exists, UNKNOWN gender is returned.
	 * @param label the gender code to look up.
	 * @return GenderEnum associated with the gender label.
	 */
	public static GenderEnum getGenderByLabel(String label) {
		for (GenderEnum gender : GenderEnum.values()) {
			if (gender.getLabel().equalsIgnoreCase(label)) {
				return gender;
			}
		}
		return GenderEnum.UNKOWN;
	}

}
