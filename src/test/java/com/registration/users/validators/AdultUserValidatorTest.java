package com.registration.users.validators;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import com.registration.users.utils.DateUtils;
import com.registration.users.validation.validators.DateBirthFormatValidator;

@SpringBootTest
@ActiveProfiles("test")
@TestInstance(Lifecycle.PER_CLASS)
class AdultUserValidatorTest{

	private DateBirthFormatValidator validator;

    @BeforeAll
    void setUp() {
        validator = new DateBirthFormatValidator();
    }

    @Test
    void testIsValidWithNullValue() {
        assertTrue(validator.isValid(null, null));
    }

    @Test
    void testIsValidWithCorrectDateFormat() {
        assertTrue(validator.isValid("1990-01-01", null));
    }

    @Test
    void testIsValidWithIncorrectDateFormat() {
        assertFalse(validator.isValid("01.01.1990", null));
    }
    
    @Test
    void testIsInValidWithIncorrectDateFormat() {
    	
    	LocalDate today = LocalDate.now();
        assertTrue(validator.isValid(DateUtils.formatDate(today, "dd/MM/yyyy"), null));
    }
}
