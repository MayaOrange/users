package com.registration.users.utils;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import com.registration.users.exception.InvalidDateException;
import com.registration.users.exception.InvalidFormatDateException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;


@SpringBootTest
@ActiveProfiles("test")
class DateUtilsTest {

	@ParameterizedTest
	@CsvSource({ 
		"12/07/1979", 
		"12-07-1979", 
		"1979/07/12", 
		"1979-07-12" })
	void parseDate_validDates_returnsLocalDate(String dateString) {
		LocalDate expectedDate = LocalDate.of(1979, 7, 12);
		LocalDate result = DateUtils.parseDate(dateString);
		assertEquals(expectedDate, result);
	}

	@ParameterizedTest
	@CsvSource({ 
		"12.07.1979", 
		"Allemagne", 
		"31/02/1979",
		"12/07/1979 06:12:00"})
	void parseDate_invalidDates_returnsNull(String dateString) {

		LocalDate expectedDate = DateUtils.parseDate(dateString);
		assertNull(expectedDate);
	}

	@Test
	void parseDate_date_is_null_returnsNull() {
		//Given
		String dateString = null;
		LocalDate expectedDate = DateUtils.parseDate(dateString);
		assertNull(expectedDate);
	}

	@Test
	void parseDate_date_is_empty_returnsNull() {
		String dateString = "";
		LocalDate expectedDate = DateUtils.parseDate(dateString);
		assertNull(expectedDate);
	}

	@ParameterizedTest
	@CsvSource({ 
		"dd/MM/yyyy, 08/04/2023", 
		"dd-MM-yyyy, 08-04-2023", 
		"yyyy/MM/dd, 2023/04/08", 
		"yyyy-MM-dd, 2023-04-08",
		"dd/MM/uuuu, 08/04/2023",
		"dd-MM-uuuu, 08-04-2023",
		"uuuu/MM/dd, 2023/04/08", 
		"uuuu-MM-dd, 2023-04-08" })
	void formatDate_validFormats_returnsFormattedString(String format, String expectedString) {

		String resultString = DateUtils.formatDate(LocalDate.of(2023, 4, 8), format);
		assertEquals(expectedString, resultString);
	}

	
	
	@ParameterizedTest
	@CsvSource({ 
		"invalid format",
		"dd-yyyy-MM",
		"yyyy/MM/d", 
		"MM/dd/yyyyy", 
		"hh:mm:ss a", 
		"dd-MMM-yy", 
		"d/M/yy", 
		"dd-MM-uuuuuu"
	})
	void formatDate_invalidFormats_throwsInvalidFormatDateException(String format) {
		LocalDate date = LocalDate.of(2023, 4, 8);
		assertThrows(InvalidFormatDateException.class, () -> {
			DateUtils.formatDate(date, format);
		});
	}
	
	@Test
	void formatDate_format_is_null_throwsInvalidFormatDateException() {
		LocalDate date = LocalDate.of(2023, 4, 8);
	
		InvalidFormatDateException exception = assertThrows(InvalidFormatDateException.class, () -> {
			DateUtils.formatDate(date, null);
		});

		String expectedMessage = "Invalid format date: null";
		String actualMessage = exception.getMessage();

		assertEquals(expectedMessage, actualMessage);
	}
	
	@Test
	void formatDate_format_is_empty_throwsInvalidFormatDateException() {
		LocalDate date = LocalDate.of(2023, 4, 8);
	
		InvalidFormatDateException exception = assertThrows(InvalidFormatDateException.class, () -> {
			DateUtils.formatDate(date, "");
		});

		String expectedMessage = "Invalid format date: empty";
		String actualMessage = exception.getMessage();

		assertEquals(expectedMessage, actualMessage);
	}
	
	@Test
	void formatDate_date_is_null_throwsInvalidDateException() {
		LocalDate date = null;
		String format = "dd/MM/yyyy";
	
		InvalidDateException exception = assertThrows(InvalidDateException.class, () -> {
			DateUtils.formatDate(date, format);
		});

		String expectedMessage = "Invalid date: null";
		String actualMessage = exception.getMessage();

		assertEquals(expectedMessage, actualMessage);
	}
	
	@Test
	void formatDate_date_is_null_format_is_null_throwsInvalidDateException() {
		LocalDate date = null;
		String format = null;
	
		InvalidDateException exception = assertThrows(InvalidDateException.class, () -> {
			DateUtils.formatDate(date, format);
		});

		String expectedMessage = "Invalid date: null";
		String actualMessage = exception.getMessage();

		assertEquals(expectedMessage, actualMessage);
	}
	

	@Test
	void isAdult_returnsTrue() {
		String birthDate = "01/01/2000";
		assertTrue(DateUtils.isOldEnoughToBeLegal(birthDate));
	}
	
	@Test
	void isAdult_today_minus18_returnsTrue() {
		LocalDate today = LocalDate.now();
		LocalDate eighteenYearsAgo = today.minusYears(18);
		String dateformatted = DateUtils.formatDate(eighteenYearsAgo, "dd/MM/yyyy");
		assertTrue(DateUtils.isOldEnoughToBeLegal(dateformatted));
	}

	@Test
	void isAdult_today_returnsFalse() {
		LocalDate today = LocalDate.now();
		String todayFomatted = DateUtils.formatDate(today, "dd/MM/yyyy");
		assertFalse(DateUtils.isOldEnoughToBeLegal(todayFomatted));
	}
	

	@Test
	void isAdult_today_minus5_returnsFalse() {
		LocalDate today = LocalDate.now();
		LocalDate fiveYearsAgo = today.minusYears(5);
		String dateformatted = DateUtils.formatDate(fiveYearsAgo, "dd/MM/yyyy");
		assertFalse(DateUtils.isOldEnoughToBeLegal(dateformatted));
	}
	
	@Test
	void isAdult_today_minus17_returnsFalse() {
		LocalDate today = LocalDate.now();
		LocalDate seventeenYearsAgo = today.minusYears(17);
		String dateformatted = DateUtils.formatDate(seventeenYearsAgo, "dd/MM/yyyy");
		assertFalse(DateUtils.isOldEnoughToBeLegal(dateformatted));
	}
	@Test
	void isAdult_future_returnsFalse() {
	
	 LocalDate today = LocalDate.now();
	 LocalDate future = today.plusYears(2);
	 String dateformatted = DateUtils.formatDate(future, "dd/MM/yyyy");
	 
	 assertFalse(DateUtils.isOldEnoughToBeLegal(dateformatted));
	}
	
	
}