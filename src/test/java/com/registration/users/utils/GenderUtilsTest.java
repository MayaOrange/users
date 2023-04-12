package com.registration.users.utils;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
class GenderUtilsTest {
	
	
	
	@Test
	void testGetLabelGenderByCode() {
		assertEquals("Male", GenderUtils.getLabelGenderByCode("MALE"));
		assertEquals("Female", GenderUtils.getLabelGenderByCode("FEMALE"));
		assertEquals("Non-Binary", GenderUtils.getLabelGenderByCode("NON_BINARY"));
		assertEquals("Unknown", GenderUtils.getLabelGenderByCode("XYZ"));
	}
	
	@Test
	void testGetCodeGenderByLabel() {
		assertEquals("MALE", GenderUtils.getCodeGenderByLabel("Male"));
		assertEquals("FEMALE", GenderUtils.getCodeGenderByLabel("Female"));
		assertEquals("NON_BINARY", GenderUtils.getCodeGenderByLabel("Non-Binary"));
		assertEquals("UNKOWN", GenderUtils.getCodeGenderByLabel("XYZ"));
	}
	
	@Test
	void testGetCodeGenderByLabel1() {
		String genderLibelle = "non-Binary";
		String code = GenderUtils.getCodeGenderByLabel(genderLibelle);
		assertEquals("NON_BINARY", code);
		
	}
	
	@Test
	void testGetLabelGenderByCode2() {
		String genderCode = "NON_BINARY";
		String label = GenderUtils.getLabelGenderByCode(genderCode);
		assertEquals("Non-Binary", label);
		
	}
	
	@Test
	void testGetCodeGenderByLabelContainsBinary() {
		String genderLibelle = "non binary";
		String code = GenderUtils.getCodeGenderByLabel(genderLibelle);
		assertEquals("NON_BINARY", code);
		
	}

}
