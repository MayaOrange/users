package com.registration.users.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.registration.users.common.Response;
import com.registration.users.dto.rest.UserRequest;
import com.registration.users.dto.rest.UserResponse;
import com.registration.users.exception.UserFunctionalException;
import com.registration.users.exception.UserNotFoundException;
import com.registration.users.service.UserService;
import com.registration.users.utils.MessagesFunctionalExceptionUtils;

import org.springframework.http.HttpStatus;

@RestController
@RequestMapping("/user")
public class UserController {

	/**
	 * The userService field is used to perform operations related to User entities.
	 */

	private final UserService userService;

	public UserController(UserService userService) {
		this.userService = userService;
	}
    

    /**
     * Retrieves a User object by its ID.
     *
     * @param id the ID of the User to retrieve.
     * @return a ResponseEntity containing a Response object with a UserResponse object as data and an HTTP OK status code.
     * @throws UserNotFoundException if the User with the specified ID does not exist.
     */
	@GetMapping("/{id}")
	public ResponseEntity<Response<UserResponse>> getUserById(@PathVariable Long id) {
		Response<UserResponse> response = new Response<>();
		UserResponse userResponse = userService.getUser(id);
		response.setData(userResponse);
		return ResponseEntity.ok(response);
	}
	


	 /**
     * Retrieves a list of UserRequest objects.
     *
     * @return an Iterable of UserRequest objects.
     */
	@GetMapping("")
	public Iterable<UserResponse> getUsers() {
		return userService.getUsers();
	}

	@PostMapping("")
	public ResponseEntity<Response<UserResponse>> createUser(@RequestBody UserRequest userRequest) {
		Response<UserResponse> response = new Response<>();
		userService.checkUserRequest(userRequest);
		UserResponse createdUserResponse = userService.saveUser(userRequest);
		response.setData(createdUserResponse);
		response.setErrors(List.of(MessagesFunctionalExceptionUtils.getMessageNoErrors()));
		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}

	
	 /**
     * Creates a new User object.
     *
     * @param userRequest the UserRequest object containing the data for the new User.
     * @return a ResponseEntity containing a Response object with a UserResponse object as data, an HTTP CREATED status code, and a list with a single element containing the message "NO_ERRORS".
     * @throws UserFunctionalException if the UserRequest object contains invalid data.
     */
	@PutMapping("/{id}")
	public ResponseEntity<Response<UserResponse>> updateUser(@PathVariable Long id, @RequestBody UserRequest userRequest) {
		Response<UserResponse> response = new Response<>();
		userService.checkUserRequest(userRequest);
		UserResponse updatedUserResponse = userService.updateUser(id, userRequest);
		response.setData(updatedUserResponse);
		response.setErrors(List.of("NO_ERRORS"));
		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}

	 /**
     * Updates an existing User object.
     *
     * @param id          the ID of the User to update.
     * @param userRequest the UserRequest object containing the updated data for the User.
     * @return a ResponseEntity containing a Response object with a UserResponse object as data, an HTTP CREATED status code, and a list with a single element containing the message "NO_ERRORS".
     * @throws UserFunctionalException if the UserRequest object contains invalid data.
     * @throws UserNotFoundException   if the User with the specified ID does not exist.
     */
	@DeleteMapping("/{id}")
	public ResponseEntity<Response<String>> deleteUser(@PathVariable Long id) {
		Response<String> response = new Response<>();
		userService.deleteUser(id);
		response.setData("User with id " + id + " is correcty deleted");
		response.setErrors(List.of("NO_ERRORS"));
		return ResponseEntity.ok(response);
	}
	
}
