package com.registration.users.exception;

/**
 * This exception is thrown when a date provided in a user-related operation is invalid.
 */
public class InvalidDateException extends RuntimeException {
    
    private static final long serialVersionUID = 1L;

    /**
     * Constructs a new InvalidDateException with the specified error message.
     * 
     * @param message the error message
     */
    public InvalidDateException(String message) {
        super(message);
    }
}