package com.registration.users.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.Optional;

import com.registration.users.dto.rest.UserRequest;
import com.registration.users.dto.rest.UserResponse;
import com.registration.users.model.User;

import com.registration.users.repository.UserRepository;
import com.registration.users.utils.Constants;
import com.registration.users.utils.GenderUtils.GenderEnum;
import com.registration.users.utils.MappingUtils;
import com.registration.users.exception.UserNotFoundException;
import com.registration.users.exception.UserFunctionalException;


@ExtendWith(MockitoExtension.class)
class UserServiceTest {
	@Mock
    private UserRepository userRepository;

  
    private UserService userService;

    @BeforeEach
    void setUp() {
    	userService = new UserService(userRepository);
    }

    //TODO BEFORE EACH newService
    @Test
    void testGetUser() {
        // given
        Long userId = 1L;
        User user = new User();
		user.setId(userId);
		user.setName("Maya");
		user.setCountryResidence("France");
		user.setDateBirth(LocalDate.of(1979, 07, 12));
		user.setPhoneNumber("+33645324541");
		user.setGender("FEMALE");
        given(userRepository.findById(userId)).willReturn(Optional.of(user));

        // when
       UserResponse userResponse = userService.getUser(userId);

        // then
        assertNotNull(userResponse);
        assertEquals(userId, userResponse.getId());
        assertEquals("Maya", userResponse.getName());
        assertEquals(LocalDate.of(1979, 7, 12), userResponse.getDateBirth());
        assertEquals("France", userResponse.getCountryResidence());
        assertEquals("+33645324541", userResponse.getPhoneNumber());
        assertEquals(GenderEnum.FEMALE, userResponse.getGender());
      
    }
    
    @Test
    void testGetUser_userNotFound() {
        // When a user with the given ID is not found, the repository should return an empty optional
        when(userRepository.findById(1L)).thenReturn(Optional.empty());
        
        // Call the getUser method with the user's ID and verify that it throws a UserNotFoundException
        assertThrows(UserNotFoundException.class, () -> userService.getUser(1L));
    }
    
    @Test
    void testCreateUser() {
    	   UserRequest userRequest= new UserRequest();
    	   userRequest.setName("Maya");
    	   userRequest.setDateBirth("12/07/1979");
    	   userRequest.setCountryResidence("FR");
    	   userRequest.setPhoneNumber("0634567895");
           userRequest.setGender("Female");
           
           User user = MappingUtils.mapUserRequestToUser(userRequest);
           given(userRepository.save(user)).willReturn(user);
           UserResponse userResponse = userService.saveUser(userRequest);
           assertNotNull(userResponse);
    }
    
    /*****************************METIER**********************************/
    @Test
    void testCheckUserDtoRest0() {
        UserRequest userDto = new UserRequest();
        userDto.setName("Maya");
        userDto.setDateBirth("10/04/1990");
        userDto.setCountryResidence("France");
        
        // Test mandatory name
        assertDoesNotThrow(() -> {
            userService.checkUserRequest(userDto);
        });
        }
    
    @Test
    void testCheckUserDtoRest() {
        UserRequest userDto = new UserRequest();
        userDto.setName("");
        userDto.setDateBirth("10/04/1990");
        userDto.setCountryResidence("FR");
        
        // Test mandatory name
        assertThrows(UserFunctionalException.class, () -> {
            userService.checkUserRequest(userDto);
        });
        
        userDto.setName("John Doe");
        
        // Test name length
        String longName = "A".repeat(Constants.USER_NAME_SIZE_MAX + 1);
        userDto.setName(longName);
        assertThrows(UserFunctionalException.class, () -> {
            userService.checkUserRequest(userDto);
        });
        
        // Test mandatory date of birth
        userDto.setDateBirth("");
        assertThrows(UserFunctionalException.class, () -> {
            userService.checkUserRequest(userDto);
        });
        
        userDto.setDateBirth("1990-04-10");
        
        // Test invalid date format
        userDto.setDateBirth("10/04/1990");
        assertThrows(UserFunctionalException.class, () -> {
            userService.checkUserRequest(userDto);
        });
        
        userDto.setDateBirth("1990-04-10");
        
        // Test mandatory country of residence
        userDto.setCountryResidence("");
        assertThrows(UserFunctionalException.class, () -> {
            userService.checkUserRequest(userDto);
        });
        
        userDto.setCountryResidence("US");
        
        // Test non-French country of residence
        assertThrows(UserFunctionalException.class, () -> {
            userService.checkUserRequest(userDto);
        });
        
        userDto.setCountryResidence("France");
        userDto.setName("Maya");
        
        // Test valid userDto
        assertDoesNotThrow(() -> {
            userService.checkUserRequest(userDto);
        });
    }
}
