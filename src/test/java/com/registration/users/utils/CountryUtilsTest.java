package com.registration.users.utils;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;


@SpringBootTest
@ActiveProfiles("test")
class CountryUtilsTest {
	
	@Test
	void testGetCountryResidenceFranceNameWithValidCode() {
		assertEquals("France", CountryUtils.getCountryResidenceFranceName("FR"));
	}
	
	@Test
	void testGetCountryResidenceFranceNameWithValidName() {
		assertEquals("France", CountryUtils.getCountryResidenceFranceName("France"));
	}
	
	@Test
	void testGetCountryResidenceFranceNameWithInvalidInput() {
		assertNull(CountryUtils.getCountryResidenceFranceName(null));
		assertNull(CountryUtils.getCountryResidenceFranceName(""));
		assertNull(CountryUtils.getCountryResidenceFranceName("USA"));
	}
	
	
	@Test
	void testGetCode() {
		assertEquals("FR", CountryUtils.CountryEnum.FR.getCode());
	}

	@Test
	void testGetName() {
		assertEquals("France", CountryUtils.CountryEnum.FR.getName());
	}

}
