package com.registration.users.utils;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;
import java.util.List;

import com.registration.users.exception.InvalidDateException;
import com.registration.users.exception.InvalidFormatDateException;
import com.registration.users.exception.UserNotAdultException;

import lombok.Data;

/**
 * This class provides utilities for handling dates.
 */
public class DateUtils {
	
	/*
	 * Strict date formats
	 * withResolverStyle(ResolverStyle.STRICT) is used to prevent default behavior
	 * "smart parsing" when parsing dates for example 30/02/1979 will throw
	 * exception and not return 28/02/1979 use "uuuu" (proleptic year) and not
	 * "yyyy"(year of era) in this particular case (case STRICT)
	 */
    public static final String DATE_FORMAT_DEFAULT_STRICT = "dd/MM/uuuu";
    public static final String DATE_FORMAT_STRICT_1 = "dd-MM-uuuu";
    public static final String DATE_FORMAT_STRICT_2 = "uuuu/MM/dd";
    public static final String DATE_FORMAT_STRICT_3 = "uuuu-MM-dd";
    
	 /* Date formats */
    public static final String DATE_FORMAT_DEFAULT = "dd/MM/yyyy";
    public static final String DATE_FORMAT_1 = "dd-MM-yyyy";
    public static final String DATE_FORMAT_2 = "yyyy/MM/dd";
    public static final String DATE_FORMAT_3 = "yyyy-MM-dd";
	
	@Data
	private static class PatternDate {
		String pattern;
		boolean isStrict;

		PatternDate(String pattern, boolean isStrict) {
			this.pattern = pattern;
			this.isStrict = isStrict;
		}
	}

	private static final List<PatternDate> PATTERNS = List.of(
			new PatternDate(DATE_FORMAT_DEFAULT_STRICT, true),
			new PatternDate(DATE_FORMAT_STRICT_1, true), 
			new PatternDate(DATE_FORMAT_STRICT_2, true),
			new PatternDate(DATE_FORMAT_STRICT_3, true), 
			new PatternDate(DATE_FORMAT_DEFAULT, false),
			new PatternDate(DATE_FORMAT_1, false), 
			new PatternDate(DATE_FORMAT_2, false),
			new PatternDate(DATE_FORMAT_3, false));

	 /**
     * Private constructor to prevent instantiation of the class.
     * This class provides only static methods and should not be instantiated.
     */
	private DateUtils() {
		throw new AssertionError("This class should not be instantiated.");
	}

	/**
	 * Parses a string to obtain a date. This method iterates through a list of
	 * pre-defined date formats to try to convert the input string to a LocalDate
	 * object. If no format works, an exception is thrown.
	 *
	 * @param input the string to be parsed
	 * @return the LocalDate object corresponding to the input string
	 * @return null if cannot be converted to a LocalDate
	 */
	public static LocalDate parseDate(String input) {

		if (input == null || input.isEmpty()) {
			return null;
		}
		
	
		for (PatternDate pattern : PATTERNS) {
			if (pattern.isStrict) {
				try {

					DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern.getPattern()).withResolverStyle(ResolverStyle.STRICT);
					return LocalDate.parse(input, formatter);
				} catch (DateTimeParseException e) {
					// ignore and try the next format
				}
			}
		}
		return null;

	}

	/**
	 * Formats a date in a specified format.
	 *
	 * @param date   : the LocalDate object to format
	 * @param format : the format of the output string (ex "dd/MM/yyyy")
	 * @return the formatted string representing the date
	 * @throws DateTimeException if the date cannot be formatted according to the
	 *                           specified format
	 */
	public static String formatDate(LocalDate date, String format) throws DateTimeException {

		if (date == null ) {
			throw new InvalidDateException("Invalid date: null");
		}
		if (format == null) {
			throw new InvalidFormatDateException("Invalid format date: null");
		}
		if (format.isEmpty()) {
			throw new InvalidFormatDateException("Invalid format date: empty");
		}

		if (PATTERNS.stream().noneMatch(p -> p.getPattern().equals(format))) {
			throw new InvalidFormatDateException("Invalid format: " + format);
		}

		try {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
			return date.format(formatter);
		} catch (DateTimeException | IllegalArgumentException exception) {
			throw new InvalidFormatDateException("Invalid date/format: " + date + " " + format);
		}
	}

	/**
	 * This method checks whether the given date string is in the correct format.
	 * It returns a boolean value indicating whether the date format
	 * is correct or not.
	 * 
	 * @param date a string representing a date in the format of yyyy-MM-dd.
	 * 
	 * @return true if the date format is correct, false otherwise.
	 */
	public static boolean isDateFormatCorrect(String date) {
		return parseDate(date)!= null;
	}

	/**
	 * Checks if a person is an adult based on their date of birth.
	 *
	 * @param birthDate the person's date of birth
	 * @return true if the person is an adult, false otherwise
	 */
	public static boolean isOldEnoughToBeLegal(String birthDateIn) {
		LocalDate birthDate = DateUtils.parseDate(birthDateIn);
		if (birthDate != null) {
			LocalDate currentDate = LocalDate.now();
			Period age = Period.between(birthDate, currentDate);
			return age.getYears() >= Constants.AGE_OF_MAJORITY;
		} else {
			throw new UserNotAdultException("User not an adult");
		}
	}

	
}