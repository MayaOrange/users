package com.registration.users.exception;

import java.util.ArrayList;
import java.util.List;

/**
 * This exception is thrown when there are functional errors in user-related operations.
 */
public class UserFunctionalException extends RuntimeException {
    
    private static final long serialVersionUID = 4388524708701618418L;
    
    private List<String> errors;

    /**
     * Constructs a new UserFunctionalException with the specified error message.
     * 
     * @param message the error message
     */
    public UserFunctionalException(String message) {
        super(message);
        errors = new ArrayList<>();
    }
    
    /**
     * Constructs a new UserFunctionalException with the specified list of errors.
     * 
     * @param errors the list of errors
     */
    public UserFunctionalException(List<String> errors) {
        super("User functional errors occurred");
        this.errors = new ArrayList<>(errors);
    }

    /**
     * Returns the list of errors associated with this exception.
     * 
     * @return the list of errors
     */
    public List<String> getErrors() {
        return new ArrayList<>(errors);
    }

    /**
     * Sets the list of errors associated with this exception.
     * 
     * @param errors the list of errors
     */
    public void setErrors(List<String> errors) {
        this.errors = new ArrayList<>(errors);
    }
}