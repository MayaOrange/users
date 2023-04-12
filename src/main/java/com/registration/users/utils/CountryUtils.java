package com.registration.users.utils;

/**
 * Utility class for handling countries.
 */
public class CountryUtils {

    /**
     * Enumeration of supported countries.
     */
    public enum CountryEnum {
        FR("FR", "France");

        private final String code;
        private final String name;

        CountryEnum(String code, String name) {
            this.code = code;
            this.name = name;
        }

        /**
         * Returns the country code.
         *
         * @return the country code
         */
        public String getCode() {
            return code;
        }

        /**
         * Returns the country name.
         *
         * @return the country name
         */
        public String getName() {
            return name;
        }
    }

    private CountryUtils() {
        throw new AssertionError("This class should not be instantiated.");
    }

    /**
     * Returns the name of the country of residence for a given country code or name, if it's France.
     *
     * @param country the country code or name
     * @return the name of the country of residence, or null if the country is not France
     */
    public static String getCountryResidenceFranceName(String country) {
        if (country == null || country.isEmpty()) {
            return null;
        }

        if (country.equalsIgnoreCase(CountryEnum.FR.getCode()) ||
                country.equalsIgnoreCase(CountryEnum.FR.getName())) {
            return CountryEnum.FR.getName();
        }

        return null;
    }
}
