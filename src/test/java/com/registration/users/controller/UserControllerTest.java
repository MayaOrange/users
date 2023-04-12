package com.registration.users.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.mockito.BDDMockito.given;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import com.registration.users.dto.rest.UserRequest;
import com.registration.users.dto.rest.UserResponse;
import com.registration.users.exception.UserFunctionalException;
import com.registration.users.exception.UserNotFoundException;
import com.registration.users.service.UserService;
import com.registration.users.utils.Constants;
import com.registration.users.utils.DateUtils;
import com.registration.users.utils.GenderUtils.GenderEnum;
import com.registration.users.utils.MessagesFunctionalExceptionUtils;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.registration.users.common.Response;
import com.registration.users.common.UserErrorResponse;
import com.registration.users.utils.GenderUtils;

/**
 * 
 * This class contains unit tests for the {@link UserController} class.
 *
 */
@WebMvcTest(UserController.class)
@ExtendWith(MockitoExtension.class)
class UserControllerTest {

	/**
	 * The mockMvc field is a mock MVC object used to simulate HTTP requests and
	 * responses.
	 */
	@Autowired
	private MockMvc mockMvc;

	/**
	 * The objectMapper field is used to convert objects to JSON format and vice
	 * versa.
	 */
	@Autowired
	private ObjectMapper objectMapper;

	/**
	 * The userService field is a mock service used to test the UserController
	 * class.
	 */
	@MockBean
	private UserService userService;

	/***************************************************************/
	/********************* TESTS GetUserById **********************/
	/***************************************************************/
	@Test
	void testGetUserById_returns_userResponse() throws Exception {
		// Given
		Long userId = 1L;
		UserResponse userResponse = new UserResponse();
		userResponse.setId(userId);
		userResponse.setName("Maya");
		userResponse.setCountryResidence("France");
		userResponse.setDateBirth(LocalDate.of(1979, 7, 12));
		userResponse.setPhoneNumber("+33625346540");
		userResponse.setGender(GenderEnum.FEMALE);

		when(userService.getUser(userId)).thenReturn(userResponse);

		// When and Then
		mockMvc.perform(get("/user/{id}", userId)).andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.data.id").value(userId))
				.andExpect(jsonPath("$.data.name").value(userResponse.getName()))
				.andExpect(jsonPath("$.data.countryResidence").value(userResponse.getCountryResidence()))
				.andExpect(jsonPath("$.data.dateBirth").value(userResponse.getDateBirth().toString()))
				.andExpect(jsonPath("$.data.phoneNumber").value(userResponse.getPhoneNumber()))
				.andExpect(jsonPath("$.data.gender").value(userResponse.getGender().name()));

	}

	@Test
	void testGetUserByIdNotFound() throws Exception {
		// GIVEN
		Long userId = 1L;

		// WHEN
		when(userService.getUser(userId)).thenThrow(new UserNotFoundException(userId));

		// THEN
		mockMvc.perform(get("/user/{id}", userId)).andExpect(status().isNotFound())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.message", is("User 1 not found")))
				.andExpect(jsonPath("$.status", is(HttpStatus.NOT_FOUND.name())))
				.andExpect(jsonPath("$.errors", is(new ArrayList<>())));

	}

	/***************************************************************/
	/********************* TESTS createUser ***********************/
	/***************************************************************/
	@Test
	void testCreateUser_returns_userResponse() throws Exception {
		// Given
		UserRequest userRequest = new UserRequest();
		userRequest.setName("Maya");
		userRequest.setDateBirth("12/07/1979");
		userRequest.setCountryResidence("FR");
		userRequest.setPhoneNumber("0634567895");
		userRequest.setGender("Female");

		UserResponse createdUserResponse = new UserResponse();
		createdUserResponse.setId(1L);
		createdUserResponse.setName(userRequest.getName());
		createdUserResponse.setDateBirth(DateUtils.parseDate(userRequest.getDateBirth()));
		createdUserResponse.setCountryResidence(userRequest.getCountryResidence());
		createdUserResponse.setPhoneNumber(userRequest.getPhoneNumber());
		createdUserResponse.setGender(GenderUtils.getGenderByLabel(userRequest.getGender()));

		Response<UserResponse> expectedResponse = new Response<>();
		expectedResponse.setData(createdUserResponse);
		expectedResponse.setErrors(List.of(MessagesFunctionalExceptionUtils.getMessageNoErrors()));

		when(userService.checkUserRequest(userRequest)).thenReturn(true);
		when(userService.saveUser(userRequest)).thenReturn(createdUserResponse);

		// When
		MvcResult result = mockMvc
				.perform(post("/user").content(objectMapper.writeValueAsString(userRequest))
						.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isCreated()).andReturn();

		// Then
		String responseJson = result.getResponse().getContentAsString();
		Response<UserResponse> actualResponse = objectMapper.readValue(responseJson, new TypeReference<>() {
		});

		assertNotNull(actualResponse.getData());
		assertEquals(expectedResponse.getData().getName(), actualResponse.getData().getName());
		assertEquals(expectedResponse.getData().getDateBirth(), actualResponse.getData().getDateBirth());
		assertEquals(expectedResponse.getData().getCountryResidence(), actualResponse.getData().getCountryResidence());
		assertEquals(expectedResponse.getData().getPhoneNumber(), actualResponse.getData().getPhoneNumber());
		assertEquals(expectedResponse.getData().getGender(), actualResponse.getData().getGender());
		assertEquals(expectedResponse.getErrors(), actualResponse.getErrors());
	}

	@Test
	void testCreateUser_user_not_old_enough_throws_UserFunctionalException() throws Exception {
		// Given
		UserRequest userRequest = new UserRequest();
		userRequest.setName("a".repeat(51));
		userRequest.setDateBirth(DateUtils.formatDate(LocalDate.now(), "dd/MM/yyyy"));
		userRequest.setCountryResidence("FR");
		userRequest.setPhoneNumber("0634567895");
		userRequest.setGender("Female");
		String messageNotOldEnough = MessagesFunctionalExceptionUtils
				.getMessageIsNotOldEnoughFr(userRequest.getDateBirth());

		Response<UserResponse> expectedResponse = new Response<>();
		List<String> expectedErrors = List.of(messageNotOldEnough);
		expectedResponse.setData(null);
		expectedResponse.setErrors(expectedErrors);

		given(userService.checkUserRequest(userRequest))
				.willThrow(new UserFunctionalException(List.of(messageNotOldEnough)));

		// When
		MvcResult result = mockMvc
				.perform(post("/user").content(objectMapper.writeValueAsString(userRequest))
						.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isBadRequest()).andReturn();

		// Then
		String responseJson = result.getResponse().getContentAsString();
		UserErrorResponse actualResponse = objectMapper.readValue(responseJson, new TypeReference<>() {
		});

		assertNotNull(actualResponse);
		assertNotNull(actualResponse.getErrors());
		assertEquals(1, ((Collection<?>) actualResponse.getErrors()).size());
		assertEquals(actualResponse.getErrors(), List.of(messageNotOldEnough));
	}

	@Test
	void testCreateUser_name_too_long_throws_UserFunctionalException() throws Exception {
		// Given
		UserRequest userDto = new UserRequest();
		userDto.setName("a".repeat(51));
		userDto.setDateBirth("12/07/1979");
		userDto.setCountryResidence("FR");
		userDto.setPhoneNumber("0634567895");
		userDto.setGender("Female");
		String messageNameTooLong = MessagesFunctionalExceptionUtils.getMessageNameTooLong(Constants.USER_NAME_SIZE_MAX,
				userDto.getName());

		given(userService.checkUserRequest(userDto))
				.willThrow(new UserFunctionalException(List.of(messageNameTooLong)));

		// When
		MvcResult result = mockMvc
				.perform(post("/user").content(objectMapper.writeValueAsString(userDto))
						.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isBadRequest()).andReturn();

		// Then
		String responseJson = result.getResponse().getContentAsString();
		UserErrorResponse actualResponse = objectMapper.readValue(responseJson, new TypeReference<>() {
		});

		assertNotNull(actualResponse);
		assertNotNull(actualResponse.getErrors());
		assertEquals(1, ((Collection<?>) actualResponse.getErrors()).size());
		assertEquals(actualResponse.getErrors(), List.of(messageNameTooLong));
	}

	@Test
	void testCreateUser_name_null_throws_UserFunctionalException() throws Exception {
		// Given
		UserRequest userRequest = new UserRequest();
		userRequest.setName(null);
		userRequest.setDateBirth("12/07/1979");
		userRequest.setCountryResidence("FR");
		userRequest.setPhoneNumber("0634567895");
		userRequest.setGender("Female");
		String messageNameMandatoryField = MessagesFunctionalExceptionUtils.getMessageNoErrors();

		given(userService.checkUserRequest(userRequest))
				.willThrow(new UserFunctionalException(List.of(messageNameMandatoryField)));

		// When
		MvcResult result = mockMvc
				.perform(post("/user").content(objectMapper.writeValueAsString(userRequest))
						.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isBadRequest()).andReturn();

		// Then
		String responseJson = result.getResponse().getContentAsString();
		UserErrorResponse actualResponse = objectMapper.readValue(responseJson, new TypeReference<>() {
		});

		assertNotNull(actualResponse);
		assertNotNull(actualResponse.getErrors());
		assertEquals(1, ((Collection<?>) actualResponse.getErrors()).size());
		assertEquals(actualResponse.getErrors(), List.of(messageNameMandatoryField));
	}

	@Test
	void testCreateUser_all_fields_invalids_throws_UserFunctionalException() throws Exception {
		// Given
		UserRequest userRequest = new UserRequest();
		
		userRequest.setName(null);
		userRequest.setDateBirth("12/07/19799");
		userRequest.setCountryResidence("Belgique");
		userRequest.setPhoneNumber("06345678959999");
		userRequest.setGender("Female");
		
		String messageNameMandatoryField = MessagesFunctionalExceptionUtils.getNullOrEmptyMessage("Name");
		
		String messageDateBirthInvalid = MessagesFunctionalExceptionUtils
				.getMessageDateBirthInvalidFormat(userRequest.getDateBirth());
		
		String messageCountryResidenceNotFrance = MessagesFunctionalExceptionUtils
				.getMessageCountryResidenceNotFrance(userRequest.getCountryResidence());
		
		String messagephoneNumberInvalid = MessagesFunctionalExceptionUtils
				.getMessagePhoneNumberInvalidFr(userRequest.getPhoneNumber());

		given(userService.checkUserRequest(userRequest))
				.willThrow(new UserFunctionalException(List.of(messageNameMandatoryField, messageDateBirthInvalid,
						messageCountryResidenceNotFrance, messagephoneNumberInvalid)));

		// When
		MvcResult result = mockMvc
				.perform(post("/user").content(objectMapper.writeValueAsString(userRequest))
						.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isBadRequest()).andReturn();

		// Then
		String responseJson = result.getResponse().getContentAsString();
		UserErrorResponse actualResponse = objectMapper.readValue(responseJson, new TypeReference<>() {
		});

		assertNotNull(actualResponse);
		assertNotNull(actualResponse.getErrors());
		assertEquals(4, ((Collection<?>) actualResponse.getErrors()).size());
		assertEquals(actualResponse.getErrors(), List.of(messageNameMandatoryField, messageDateBirthInvalid,
				messageCountryResidenceNotFrance, messagephoneNumberInvalid));
	}

	/***************************************************************/
	/********************* TESTS updateUser ***********************/
	/***************************************************************/
	@Test
	void testUpdateUser_returns_userResponse() throws Exception {
		// Given
		UserRequest userRequestUpdate = new UserRequest();
		userRequestUpdate.setName("Maya Orange");
		userRequestUpdate.setDateBirth("12/07/1979");
		userRequestUpdate.setCountryResidence("FR");

		UserResponse userResponseExpected = new UserResponse();
		userResponseExpected.setId(1L);
		userResponseExpected.setName("Maya");
		userResponseExpected.setDateBirth(LocalDate.of(1979, 7, 12));
		userResponseExpected.setCountryResidence("FR");

		Response<UserResponse> expectedResponse = new Response<>();
		expectedResponse.setData(userResponseExpected);
		expectedResponse.setErrors(List.of(MessagesFunctionalExceptionUtils.MESSAGE_NO_ERRORS));

		when(userService.checkUserRequest(userRequestUpdate)).thenReturn(true);
		when(userService.updateUser(1L, userRequestUpdate)).thenReturn(userResponseExpected);

		// When
		MvcResult result = mockMvc
				.perform(put("/user/1").content(objectMapper.writeValueAsString(userRequestUpdate))
						.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isCreated()).andReturn();

		// Then
		String responseJson = result.getResponse().getContentAsString();
		Response<UserResponse> actualResponse = objectMapper.readValue(responseJson, new TypeReference<>() {
		});

		assertNotNull(actualResponse.getData());
		assertEquals(userResponseExpected.getName(), actualResponse.getData().getName());
		assertEquals(userResponseExpected.getDateBirth(), actualResponse.getData().getDateBirth());
		assertEquals(userResponseExpected.getCountryResidence(), actualResponse.getData().getCountryResidence());
	}

	@Test
	void testUpdateUser_returns_NotFoundException() throws Exception {
		// Given
		Long userId = 1L;
		UserRequest userRequest = new UserRequest();
		userRequest.setName("Maya");
		userRequest.setDateBirth("12/07/1979");
		userRequest.setCountryResidence("FR");
		userRequest.setPhoneNumber("0634567895");
		userRequest.setGender("Female");

		// When
		when(userService.updateUser(userId, userRequest)).thenThrow(new UserNotFoundException(userId));

		// THEN
		mockMvc.perform(put("/user/1").content(objectMapper.writeValueAsString(userRequest))
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isNotFound()).andReturn();

	}

	/***************************************************************/
	/********************* TESTS deleteUser ***********************/
	/***************************************************************/
	@Test
	void testDeleteUser_returns_NotFoundException() throws Exception {
		// Given
		Long userId = 1L;

		// When
		doThrow(new UserNotFoundException(userId)).when(userService).deleteUser(userId);

		// THEN
		mockMvc.perform(delete("/user/1").contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isNotFound()).andReturn();

	}

}
