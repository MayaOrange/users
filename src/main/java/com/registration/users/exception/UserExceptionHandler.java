package com.registration.users.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.registration.users.common.UserErrorResponse;

/**
 * This class handles exceptions thrown in the user API controllers.
 */
@ControllerAdvice
public class UserExceptionHandler {

    /**
     * Handles exceptions thrown when a user is not found.
     * @param exception The exception to handle.
     * @return A ResponseEntity with an appropriate error response and status code.
     */
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleUserNotFoundException(UserNotFoundException exception) {
        ErrorResponse errorResponse = new UserErrorResponse(HttpStatus.NOT_FOUND, exception.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    /**
     * Handles functional exceptions thrown in user operations.
     * @param exception The exception to handle.
     * @return A ResponseEntity with an appropriate error response and status code.
     */
    @ExceptionHandler(UserFunctionalException.class)
    public ResponseEntity<ErrorResponse> handleUserFunctionalException(UserFunctionalException exception) {
        ErrorResponse errorResponse;
        if (exception.getErrors() != null && !exception.getErrors().isEmpty()) {
            errorResponse = new UserErrorResponse(HttpStatus.BAD_REQUEST, exception.getErrors());
        } else {
            errorResponse = new UserErrorResponse(HttpStatus.BAD_REQUEST, exception.getMessage());
        }
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    /**
     * Handles all other exceptions.
     * @param exception The exception to handle.
     * @return A ResponseEntity with an appropriate error response and status code.
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleException(Exception exception) {
        ErrorResponse errorResponse = new UserErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, exception.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}