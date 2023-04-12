package com.registration.users.utils;

import com.registration.users.dto.rest.UserRequest;
import com.registration.users.dto.rest.UserResponse;
import com.registration.users.model.User;

import java.util.List;
import java.util.ArrayList;

/**
 * A utility class for mapping.
 */
public class MappingUtils {
	
	 /**
     * Private constructor to prevent instantiation of the class.
     * This class provides only static methods and should not be instantiated.
     */
	private MappingUtils() {
		throw new AssertionError("This class should not be instantiated.");
	}
	
	/**
	 * Maps a User object to a UserDto object.
	 * 
	 * @param user the User object to be mapped.
	 * @return the UserDto object mapped from the given User object.
	 */
	public static UserResponse mapUserToUserResponse(User user) {
		if (user == null) {
			return null;
		}
		
		UserResponse userResponse = new UserResponse();
		userResponse.setId(user.getId());
		userResponse.setName(user.getName());
		userResponse.setCountryResidence(user.getCountryResidence());
		userResponse.setDateBirth(user.getDateBirth());
		userResponse.setPhoneNumber(user.getPhoneNumber());
		userResponse.setGender(GenderUtils.getGenderByCode(user.getGender()));
		return userResponse;
	}
	
	/**
	 * Maps a UserDto object to a User object.
	 * 
	 * @param userDto the UserDto object to be mapped.
	 * @return the User object mapped from the given UserDto object.
	 */
	public static User mapUserRequestToUser(UserRequest userRequest) {
		if (userRequest == null) {
			return null;
		}
		
		User user = new User();
		user.setName(userRequest.getName());
		user.setCountryResidence(CountryUtils.getCountryResidenceFranceName(userRequest.getCountryResidence()));
		user.setDateBirth(DateUtils.parseDate(userRequest.getDateBirth()));
		user.setPhoneNumber(userRequest.getPhoneNumber());
		user.setGender(GenderUtils.getCodeGenderByLabel(userRequest.getGender()));
		return user;
		
	}
	
	/**
	 * Maps an iterable of User objects to an iterable of UserDto objects.
	 * 
	 * @param users the iterable of User objects to be mapped.
	 * @return an iterable of UserDto objects mapped from the given iterable of User objects.
	 */
	public static Iterable<UserResponse> mapUsersToUsersResponse(Iterable<User> users) {
	    List<UserResponse> userDtos = new ArrayList<>();
	    for (User user : users) {
	        userDtos.add(mapUserToUserResponse(user));
	    }
	    return userDtos;
	}

}
