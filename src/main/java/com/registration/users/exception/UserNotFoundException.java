package com.registration.users.exception;

/**
 * This exception is thrown when a user cannot be found in a user-related operation.
 */
public class UserNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 4118336506987059565L;

    /**
     * Constructs a new UserNotFoundException with the specified user ID.
     *
     * @param id the ID of the user that could not be found
     */
    public UserNotFoundException(Long id) {
        super("User " + id + " not found");
    }
}