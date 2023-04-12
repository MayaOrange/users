package com.registration.users.validators;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import com.registration.users.validation.validators.AdultUserValidator;

@SpringBootTest
@ActiveProfiles("test")
@TestInstance(Lifecycle.PER_CLASS)
class DateBirthFormatValidatorTest {

    private AdultUserValidator validator;

    @BeforeAll
    void setUp() {
        validator = new AdultUserValidator();
    }

    @Test
    void testValidAdultUser() {
        boolean result = validator.isValid("1990-01-01", null);
        assertTrue(result);
    }

    @Test
    void testValidMinorUser() {
        boolean result = validator.isValid("2010-01-01", null);
        assertFalse(result);
    }

    @Test
    void testInvalidDateOfBirth() {
        boolean result = validator.isValid("not a date", null);
        assertTrue(result);
    }
}
