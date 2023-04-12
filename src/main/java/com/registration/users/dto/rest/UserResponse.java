package com.registration.users.dto.rest;

import java.time.LocalDate;

import com.registration.users.utils.GenderUtils.GenderEnum;

import lombok.Data;

@Data
public class UserResponse {
	/**
	 * The ID of the user.
	 */

	private Long id;

	/**
	 * The name of the user.
	 */
	private String name;

	/**
	 * The date of birth of the user in the format yyyy-MM-dd.
	 */
	private LocalDate dateBirth;

	/**
	 * The country of residence of the user.
	 */
	private String countryResidence;

	/**
	 * The phone number of the user.
	 */
	private String phoneNumber;

	/**
	 * The gender of the user.
	 */
	private GenderEnum gender;
}
