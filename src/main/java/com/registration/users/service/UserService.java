package com.registration.users.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.registration.users.dto.rest.UserRequest;
import com.registration.users.dto.rest.UserResponse;
import com.registration.users.exception.UserFunctionalException;
import com.registration.users.exception.UserNotFoundException;
import com.registration.users.model.User;
import com.registration.users.repository.UserRepository;
import com.registration.users.utils.MappingUtils;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;


/**
 * Service class for managing users in the system.
 */
@Service
public class UserService {

	
	/**
	 * The userRepository field is used to access User entities in the database.
	 */

	private final UserRepository userRepository;
	
	
	/**
	 * Constructs a new UserService with the specified UserRepository.
	 *
	 * @param userRepository the UserRepository to use for accessing User entities
	 *                       in the database.
	 */
	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	

	/**
	 * Get a user by ID.
	 *
	 * @param id The ID of the user to retrieve.
	 * @return UserResponse.
	 * @throws UserNotFoundException if the user with the given ID is not found.
	 */
	public UserResponse getUser(final Long id) {
		Optional<User> user = userRepository.findById(id);
		if (user.isPresent()) {
			return MappingUtils.mapUserToUserResponse(user.get());
		} else {
			throw new UserNotFoundException(id);
		}
	}

	 /**
     * Get all users in the system.
     *
     * @return Iterable<UserResponse> A list of all users in the system.
     */
	public Iterable<UserResponse> getUsers() {
		Iterable<User> users = userRepository.findAll();
		return MappingUtils.mapUsersToUsersResponse(users);
	}

	 /**
     * Delete a user by ID.
     *
     * @param userId The ID of the user to delete.
     * @throws UserNotFoundException if the user with the given ID is not found.
     */
	public void deleteUser(final Long userId) {
		 Optional<User> userOptional = userRepository.findById(userId);
	        if(userOptional.isEmpty()) {
	            throw new UserNotFoundException(userId);
	        }
	        User user = userOptional.get();
	        userRepository.delete(user);
	}

	 /**
     * Create a new user.
     *
     * @param userRequest The userRequest object representing the user to create.
     * @return userResponse The userResponse object representing the created user.
     */
	public UserResponse saveUser(final UserRequest userRequest) {
		User user = MappingUtils.mapUserRequestToUser(userRequest);
		user = userRepository.save(user);
		return MappingUtils.mapUserToUserResponse(user);
	}

	  /**
     * Update an existing user.
     *
     * @param id The ID of the user to update.
     * @param userRequest The UserRequest object representing the updated user.
     * @return UserRequest The UserRequest object representing the updated user.
     * @throws UserNotFoundException if the user with the given ID is not found.
     */
	public UserResponse updateUser(final Long id, final UserRequest userRequest) {
		Optional<User> existingUser = userRepository.findById(id);
		if (existingUser.isPresent()) {
			User updatedUser = MappingUtils.mapUserRequestToUser(userRequest);
			updatedUser.setId(existingUser.get().getId());
			updatedUser = userRepository.save(updatedUser);
			return MappingUtils.mapUserToUserResponse(updatedUser);
		} else {
			throw new UserNotFoundException(id);
		}
	}

	 /**
     * Check that the given userRequest object is valid according to business rules.
     *
     * @param userRequest The userRequest object to check.
     * @return true if not UserFunctionalException is thrown
     * @throws UserFunctionalException if the userRequest object is not valid.
     */
	public boolean checkUserRequest(UserRequest userRequest) {
		List<String> errors = new ArrayList<>();
		Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
		Set<ConstraintViolation<UserRequest>> violations = validator.validate(userRequest);

		if (!violations.isEmpty()) {
		    for (ConstraintViolation<UserRequest> violation : violations) {
		    	errors.add(violation.getMessage());
		    }
		}
	  
	    // Throw exception if there are errors
	    if (!errors.isEmpty()) {
	        throw new UserFunctionalException(errors);
	    } else {
	    	return true;
	    }
	}
	

}
