package com.registration.users.dto.rest;

import com.registration.users.validation.annotations.AdultUser;
import com.registration.users.validation.annotations.CountryFr;
import com.registration.users.validation.annotations.DateBirthFormat;
import com.registration.users.validation.annotations.PhoneNumberFr;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

/**
 * Represents the Data Transfer Object (DTO) for a user in a RESTful API.
 * Contains user information such as name, date of birth, country of residence,
 * phone number, and gender.
 */
@Data
public class UserRequest {

	/**
	 * The name of the user.
	 */
	@NotBlank(message = "Name is a mandatory field and cannot be null or empty.")
	@Size(max = 50, message = "Name must be 50 characters maximum.")
	private String name;

	/**
	 * The date of birth of the user in the formats - dd/MM/yyyy - dd-MM-yyyy -
	 * yyyy/MM/dd - yyyy-MM-dd
	 */
	@NotBlank(message = "Date of Birth is a mandatory field and cannot be null or empty.")
	@DateBirthFormat
	@AdultUser
	private String dateBirth;

	/**
	 * The country of residence of the user.
	 */
	@NotBlank(message = "Country of Residence is a mandatory field and cannot be null or empty.")
	@CountryFr()
	private String countryResidence;

	/**
	 * The phone number of the user.
	 */
	@PhoneNumberFr
	private String phoneNumber;

	/**
	 * The gender of the user.
	 */
	private String gender;
}
