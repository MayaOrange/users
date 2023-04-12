package com.registration.users.utils;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
class PhoneNumberUtilsTest {
	

	@ParameterizedTest
	@CsvSource({ 
		/* National formats */
		"0644444444", 
		"06 44 44 44 44", 
		"06-44-44-44-44", 
		"06.44.44.44.44",
		/* International formats */
		"+33644444444", 
		"+336.44.44.44.44", 
		"+33 6.44.44.44.44", 
		"0033644444444", 
		"00336.44.44.44.44",
		"0033 6.44.44.44.44",
		/* sometimes used */
		"+33(0)644444444", 
		"+33 (0) 644444444"})
	void testValidPhoneNumbers(String phoneNumber) {
		assertTrue(PhoneNumberUtils.isFrenchNumberValid(phoneNumber));
	}
	
	@ParameterizedTest
	@CsvSource({ 
		/* Not accepted because the separator between pairs of digits is not the same */
		"06 44.44-44.44",
		"06 44 44-44.44",
		"06 44 44-4444",
		"06 44 44-4444",
		/* Too many digits */
		"06444444444444",
		/* Missing leading 0 */
		"6644444444",
		/*Too much non-numeric characters*/
		"06  44.44-44.44",
		"+33 (0)  644444444",
		/* Bad parenthesis */
		"(0)644444444",
		/* Bad separator after the international prefix */
		" +33-(0)-644444444",
		"+33 (0)-644444444",
		"+33-(0) 644444444",
		/* Trailing separator */
		"06.44.44.44.44."})
	void testInvalidValidPhoneNumbers(String phoneNumber) {
		assertFalse(PhoneNumberUtils.isFrenchNumberValid(phoneNumber));
	}
	
	
	
}
