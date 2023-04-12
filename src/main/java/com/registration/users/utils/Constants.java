package com.registration.users.utils;

public final class Constants {
    private Constants() {
        throw new AssertionError("This class should not be instantiated.");
    }

    // Age of majority in France is 18 years
    public static final int AGE_OF_MAJORITY = 18;

    // Oldest human in 2023 has 112 years
    public static final int AGE_USER_MAX = 112;

    // Maximum sizes for user input fields
    public static final int USER_NAME_SIZE_MAX = 50;
    public static final int COUNTRY_RESIDENCE_SIZE_MAX = 50;
    public static final int PHONE_NUMBER_SIZE_MAX = 20;


}