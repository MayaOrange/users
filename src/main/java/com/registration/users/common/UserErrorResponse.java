package com.registration.users.common;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ProblemDetail;
import org.springframework.web.ErrorResponse;

import lombok.Data;

@Data 
public class UserErrorResponse implements ErrorResponse {
	 	
		private HttpStatus status;
	    private String message;
	    List<String> errors;
	    public UserErrorResponse() {}


	    public UserErrorResponse(HttpStatus status, String message) {
	        this.status = status;
	        this.message = message;
	        this.errors = new ArrayList<>();
	    }
	    
	    public UserErrorResponse(HttpStatus status, List<String> errors) {
	        this.status = status;
	        this.errors = errors;
	        this.message = "";
	    }

		@Override
		public HttpStatusCode getStatusCode() {
			return this.status;
		}

		@Override
		public ProblemDetail getBody() {
			return null;
		}

}
