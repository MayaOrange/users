package com.registration.users.validators;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import com.registration.users.validation.validators.CountryFrValidator;

@SpringBootTest
@ActiveProfiles("test")
@TestInstance(Lifecycle.PER_CLASS)
class CountryFrValidatorTest {

	 private CountryFrValidator validator;

	    @BeforeAll
	    public void setUp() {
	        validator = new CountryFrValidator();
	    }

	    @Test
	    public void testValidCountryName() {
	        boolean result = validator.isValid("France", null);
	        assertTrue(result);
	    }

	    @Test
	    public void testValidCountryCode() {
	        boolean result = validator.isValid("FR", null);
	        assertTrue(result);
	    }

	    @Test
	    public void testInvalidCountryName() {
	        boolean result = validator.isValid("Germany", null);
	        assertFalse(result);
	    }

	    @Test
	    public void testInvalidCountryCode() {
	        boolean result = validator.isValid("DE", null);
	        assertFalse(result);
	    }

	    @Test
	    public void testNullCountryName() {
	        boolean result = validator.isValid(null, null);
	        assertTrue(result);
	    }
}
