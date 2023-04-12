package com.registration.users.exception;

/**
 * This exception is thrown when a date provided in a user-related operation is in an invalid format.
 */
public class InvalidFormatDateException extends UserFunctionalException {
    
    private static final long serialVersionUID = -6218416997216308827L;

    /**
     * Constructs a new InvalidFormatDateException with the specified error message.
     * 
     * @param message the error message
     */
    public InvalidFormatDateException(String message) {
        super(message);
    }
}