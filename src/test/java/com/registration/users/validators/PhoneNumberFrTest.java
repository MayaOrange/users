package com.registration.users.validators;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import com.registration.users.validation.validators.PhoneNumberFrValidator;

@SpringBootTest
@ActiveProfiles("test")
@TestInstance(Lifecycle.PER_CLASS)
class PhoneNumberFrTest {

	private PhoneNumberFrValidator validator;

    @BeforeAll
    void setUp() {
        validator = new PhoneNumberFrValidator();
    }
    @Test
    void givenNullPhoneNumber_whenIsValid_thenReturnTrue() {
        assertTrue(validator.isValid(null, null));
    }

    @Test
    void givenInvalidPhoneNumber_whenIsValid_thenReturnFalse() {
        assertFalse(validator.isValid("123456789", null));
    }

    @Test
    void givenValidPhoneNumber_whenIsValid_thenReturnTrue() {
        assertTrue(validator.isValid("+33 6 12 34 56 78", null));
    }
}
