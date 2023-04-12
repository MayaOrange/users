package com.registration.users.exception;

/**
 * This exception is thrown when a user is not considered an adult according to certain criteria.
 */
public class UserNotAdultException extends RuntimeException {
    
    private static final long serialVersionUID = -2766379478322084249L;

    /**
     * Constructs a new UserNotAdultException with the specified error message.
     * 
     * @param message the error message
     */
    public UserNotAdultException(String message) {
        super(message);
    }
}