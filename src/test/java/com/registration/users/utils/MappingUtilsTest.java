package com.registration.users.utils;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import com.registration.users.dto.rest.UserResponse;
import com.registration.users.model.User;
import com.registration.users.utils.GenderUtils.GenderEnum;

@SpringBootTest
@ActiveProfiles("test")
class MappingUtilsTest {

	/***************************************************************/
	/***************** TESTS MAP USER TO USER RESPONSE ******************/
	/***************************************************************/
	@Test
	void testMapUserToUserResponse() {
		User user = new User();
		user.setId(Long.valueOf(13));
		user.setName("Maya");
		user.setCountryResidence("France");
		user.setDateBirth(LocalDate.of(1979, 07, 12));
		user.setPhoneNumber("+33645324541");
		user.setGender("FEMALE");

		UserResponse expected = new UserResponse();
		expected.setId(Long.valueOf(13));
		expected.setName("Maya");
		expected.setCountryResidence("France");
		expected.setDateBirth(LocalDate.of(1979, 7, 12));
		expected.setPhoneNumber("+33645324541");
		expected.setGender(GenderEnum.FEMALE);

		UserResponse result = MappingUtils.mapUserToUserResponse(user);

		assertEquals(expected, result);
	}

	@Test
	void testMapUserToUserResponse2() {
		User user = new User();
		user.setId(Long.valueOf(14));
		user.setName("Robert");
		user.setCountryResidence("France");
		user.setDateBirth(LocalDate.of(1959, 8, 12));
		user.setPhoneNumber("+3356765445");
		user.setGender("MALE");

		UserResponse expected = new UserResponse();
		expected.setId(Long.valueOf(14));
		expected.setName("Robert");
		expected.setCountryResidence("France");
		expected.setDateBirth(LocalDate.of(1959, 8, 12));
		expected.setPhoneNumber("+3356765445");
		expected.setGender(GenderEnum.MALE);

		UserResponse result = MappingUtils.mapUserToUserResponse(user);

		assertEquals(expected, result);
	}

	@Test
	void testMapUserToUserResponse3() {
		User user = new User();
		user.setId(Long.valueOf(15));
		user.setName("Frédérique");
		user.setCountryResidence("France");
		user.setDateBirth(LocalDate.of(1999, 6, 15));
		user.setPhoneNumber("+3344556677");
		user.setGender("NON_BINARY");

		UserResponse expected = new UserResponse();
		expected.setId(Long.valueOf(15));
		expected.setName("Frédérique");
		expected.setCountryResidence("France");
		expected.setDateBirth(LocalDate.of(1999, 6, 15));
		expected.setPhoneNumber("+3344556677");
		expected.setGender(GenderEnum.NON_BINARY);

		UserResponse result = MappingUtils.mapUserToUserResponse(user);

		assertEquals(expected, result);
	}

	@Test
	void testMapUserToUserResponse4() {
		User user = new User();
		user.setId(Long.valueOf(4));
		user.setName("Axxxx");
		user.setCountryResidence("France");
		user.setDateBirth(LocalDate.of(2000, 7, 15));
		user.setPhoneNumber(null);
		user.setGender(null);

		UserResponse expected = new UserResponse();
		expected.setId(Long.valueOf(4));
		expected.setName("Axxxx");
		expected.setCountryResidence("France");
		expected.setDateBirth(LocalDate.of(2000, 7, 15));
		expected.setPhoneNumber(null);
		expected.setGender(GenderEnum.UNKOWN);

		UserResponse result = MappingUtils.mapUserToUserResponse(user);

		assertEquals(expected, result);
	}

	@Test
	void testMapUserToUserResponse5() {
		User user = new User();
		user.setId(Long.valueOf(4));
		user.setName("Axxxx");
		user.setCountryResidence("France");
		user.setDateBirth(LocalDate.of(2000, 7, 15));
		user.setPhoneNumber("");
		user.setGender("");

		UserResponse expected = new UserResponse();
		expected.setId(Long.valueOf(4));
		expected.setName("Axxxx");
		expected.setCountryResidence("France");
		expected.setDateBirth(LocalDate.of(2000, 7, 15));
		expected.setPhoneNumber("");
		expected.setGender(GenderEnum.UNKOWN);

		UserResponse result = MappingUtils.mapUserToUserResponse(user);

		assertEquals(expected, result);
	}

	@Test
	void testMapUserToUserResponse_NullInput() {
		UserResponse result = MappingUtils.mapUserToUserResponse(null);
		assertNull(result);
	}

	/***************************************************************/
	/**************** TESTS MAP USER REQUEST TO USER *******************/
	/***************************************************************/

}
