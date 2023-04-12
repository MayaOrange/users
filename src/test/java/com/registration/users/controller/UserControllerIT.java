package com.registration.users.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import java.time.LocalDate;

import org.json.JSONException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.registration.users.utils.GenderUtils.GenderEnum;
import com.registration.users.dto.rest.UserRequest;
import com.registration.users.dto.rest.UserResponse;
import com.registration.users.UsersApplication;
import com.registration.users.common.Response;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = UsersApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class UserControllerIT {
	
	/**
	 * The objectMapper field is used to convert objects to JSON format and vice
	 * versa.
	 */
	@Autowired
	private ObjectMapper objectMapper;
	
	@LocalServerPort
	private int port;
	

	TestRestTemplate restTemplate = new TestRestTemplate();

	HttpHeaders headers = new HttpHeaders();
	
	/**
	 * SCENARIO CRUD 
	 * STEP 1 - create user 1 -> user 1 
	 * STEP 2 - Get user 1 -> user 1
	 * STEP 3 - UPDATE user 1 -> user 1 
	 * STEP 4 - DELETE user 1 -> user deleted 
	 * STEP 5 - GET user 1 -> user NOT FOUND
	 */
	@Test
	void testScenarioCrudUser1_createUser1() throws JSONException, JsonMappingException, JsonProcessingException {
		Long userId = createUser();
		getUser(userId);
		updateUser(userId);
		deleteUser(userId);
		getUserNotFound(userId);
	}
	
	
	/**
	 * Creates a new user by sending a POST request to the "/user" endpoint with the given user request data.
	 * @return the ID of the created user
	 * @throws JsonMappingException if there is an error mapping the JSON response
	 * @throws JsonProcessingException if there is an error processing the JSON response
	 */
	private Long createUser() throws JsonMappingException, JsonProcessingException {
		// GIVEN
		UserRequest userRequest1 = new UserRequest();
		userRequest1.setName("Maya");
		userRequest1.setDateBirth("12/07/1979");
		userRequest1.setCountryResidence("France");
		userRequest1.setPhoneNumber("0654765543");
		userRequest1.setGender(GenderEnum.FEMALE.getLabel());

		// THEN

		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<UserRequest> entityCreate = new HttpEntity<>(userRequest1, headers);

		ResponseEntity<String> responseCreate = restTemplate.exchange(createURLWithPort("/user"), HttpMethod.POST,
				entityCreate, String.class);

		// FINALLY
		String responseJson = responseCreate.getBody();
		Response<UserResponse> actualResponseCreate = objectMapper.readValue(responseJson, new TypeReference<>() {
		});

		assertNotNull(actualResponseCreate);
		assertNotNull(actualResponseCreate.getData());
		assertNotNull(actualResponseCreate.getData().getId());
		assertEquals("Maya", actualResponseCreate.getData().getName());
		assertEquals(LocalDate.of(1979, 7, 12), actualResponseCreate.getData().getDateBirth());
		assertEquals("France", actualResponseCreate.getData().getCountryResidence());
		assertEquals("0654765543", actualResponseCreate.getData().getPhoneNumber());
		assertEquals(GenderEnum.FEMALE, actualResponseCreate.getData().getGender());

		return actualResponseCreate.getData().getId();

	}


	/**
	 * Gets a  by sending a GET request to the "/user/id" endpoint with the given user request data.
	 * @return void
	 * @throws JsonMappingException if there is an error mapping the JSON response
	 * @throws JsonProcessingException if there is an error processing the JSON response
	 */
	private void getUser(Long userId) throws JsonMappingException, JsonProcessingException {
		// GIVEN
		UserRequest userRequest1 = new UserRequest();
		userRequest1.setName("Maya");
		userRequest1.setDateBirth("12/07/1979");
		userRequest1.setCountryResidence("France");
		userRequest1.setPhoneNumber("0654765543");
		userRequest1.setGender(GenderEnum.FEMALE.getLabel());

		// THEN

		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<UserRequest> entityCreate = new HttpEntity<>(userRequest1, headers);

		ResponseEntity<String> responseCreate = restTemplate.exchange(createURLWithPort("/user"), HttpMethod.POST,
				entityCreate, String.class);

		// FINALLY
		String responseJson = responseCreate.getBody();
		Response<UserResponse> actualResponseCreate = objectMapper.readValue(responseJson, new TypeReference<>() {
		});

		assertNotNull(actualResponseCreate);
		assertNotNull(actualResponseCreate.getData());
		assertNotNull(actualResponseCreate.getData().getId());
		assertEquals("Maya", actualResponseCreate.getData().getName());
		assertEquals(LocalDate.of(1979, 7, 12), actualResponseCreate.getData().getDateBirth());
		assertEquals("France", actualResponseCreate.getData().getCountryResidence());
		assertEquals("0654765543", actualResponseCreate.getData().getPhoneNumber());
		assertEquals(GenderEnum.FEMALE, actualResponseCreate.getData().getGender());

	}

	/**
	 * Updates a user by sending a PUT request to the "/user/id" endpoint with the given user request data.
	 * @return void
	 * @throws JsonMappingException if there is an error mapping the JSON response
	 * @throws JsonProcessingException if there is an error processing the JSON response
	 */
	private void updateUser(Long userId) throws JsonMappingException, JsonProcessingException {
		// GIVEN
		UserRequest userRequest2 = new UserRequest();
		userRequest2.setName("Maya Orange");
		userRequest2.setDateBirth("12/07/1979");
		userRequest2.setCountryResidence("France");
		userRequest2.setPhoneNumber("06 06 06 06 06");
		userRequest2.setGender(GenderEnum.FEMALE.getLabel());

		// THEN
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<UserRequest> entityUpdate = new HttpEntity<>(userRequest2, headers);

		ResponseEntity<String> responseUpdate = restTemplate.exchange(createURLWithPort("/user/" + userId),
				HttpMethod.PUT, entityUpdate, String.class);

		// FINALLY
		String responseJsonUpdate = responseUpdate.getBody();
		Response<UserResponse> actualResponseUpdate = objectMapper.readValue(responseJsonUpdate, new TypeReference<>() {
		});

		assertNotNull(actualResponseUpdate);
		assertNotNull(actualResponseUpdate.getData());
		assertEquals("Maya Orange", actualResponseUpdate.getData().getName());
		assertEquals(LocalDate.of(1979, 7, 12), actualResponseUpdate.getData().getDateBirth());
		assertEquals("France", actualResponseUpdate.getData().getCountryResidence());
		assertEquals("06 06 06 06 06", actualResponseUpdate.getData().getPhoneNumber());
		assertEquals(GenderEnum.FEMALE, actualResponseUpdate.getData().getGender());

	}
	
	
	/**
	 * Sends a GET request to the "/user/{userId}" endpoint with a non-existing user ID and verifies that the response
	 * contains an error message indicating that the user was not found.
	 * @param userId the ID of the non-existing user to retrieve
	 * @throws JsonMappingException if an error occurs while mapping JSON to Java objects
	 * @throws JsonProcessingException if an error occurs while processing JSON data
	 */
	private void getUserNotFound(Long userId) throws JsonMappingException, JsonProcessingException {
		
		// GIVEN And THEN
		headers.setContentType(MediaType.APPLICATION_JSON);

		HttpEntity<String> entityGetAfterDelete = new HttpEntity<>(null, headers);

		ResponseEntity<String> responseGetAfterDelete = restTemplate.exchange(createURLWithPort("/user/" + userId),
				HttpMethod.GET, entityGetAfterDelete, String.class);

		// FINALLY
		String responseJsonGetAfterDelete = responseGetAfterDelete.getBody();
		Response<UserResponse> actualResponseGetAfterDelete = objectMapper.readValue(responseJsonGetAfterDelete,
				new TypeReference<>() {
				});

		assertNotNull(actualResponseGetAfterDelete);
		assertNotNull(actualResponseGetAfterDelete.getErrors());

	}

	
	/**
	 * Deletes a user by its ID and verifies the response.
	 * @param userId The ID of the user to delete.
	 * @throws JsonMappingException If an error occurs while mapping JSON to a Java object.
	 * @throws JsonProcessingException If an error occurs while processing JSON data.
	 */
	private void deleteUser(Long userId) throws JsonMappingException, JsonProcessingException {
		// GIVEN

		headers.setContentType(MediaType.APPLICATION_JSON);

		HttpEntity<String> entityDelete = new HttpEntity<>(null, headers);

		ResponseEntity<String> responseDelete = restTemplate.exchange(createURLWithPort("/user/" + userId),
				HttpMethod.DELETE, entityDelete, String.class);

		// FINALLY
		String responseJsonDelete = responseDelete.getBody();
		Response<String> actualResponseDelete = objectMapper.readValue(responseJsonDelete, new TypeReference<>() {
		});

		assertNotNull(actualResponseDelete);
		assertNotNull(actualResponseDelete.getData());
		assertEquals("User with id " + userId + " is correcty deleted", actualResponseDelete.getData().toString());

	}

	private String createURLWithPort(String uri) {
		return "http://localhost:" + port + uri;
	}

}
