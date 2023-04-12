package com.registration.users.common;

import java.util.ArrayList;

/**
 * A generic class for creating HTTP response objects.
 *
 * @param <T> the type of data returned in the response
 */
public class Response<T> {

    private T data;
    private Iterable<String> errors = new ArrayList<>();


    /**
     * Returns the data contained in the response.
     *
     * @return the data contained in the response
     */
    public T getData() {
        return data;
    }

    /**
     * Sets the data contained in the response.
     *
     * @param data the data to be set in the response
     */
    public void setData(T data) {
        this.data = data;
    }

    /**
	 * Returns the errors associated with the response.
	 *
	 * @return the errors associated with the response
	 */
	public Iterable<String> getErrors() {
		return errors;
	}

	/**
	 * Sets the errors associated with the response.
	 *
	 * @param errors the errors to be set in the response
	 */
	public void setErrors(Iterable<String> errors) {
		this.errors = errors;
	}


}