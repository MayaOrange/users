package com.registration.users.utils;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
class MessagesFunctionalExceptionUtilsTest {

    @Test
    void testGetNullMessage() {
        assertEquals("'testParam' is a mandatory field and cannot be null.", 
            MessagesFunctionalExceptionUtils.getNullMessage("testParam"));
    }

    @Test
    void testGetEmptyMessage() {
        assertEquals("'testParam' is a mandatory field and cannot empty.", 
            MessagesFunctionalExceptionUtils.getEmptyMessage("testParam"));
    }

    @Test
    void testGetNullOrEmptyMessage() {
        assertEquals("'testParam' is a mandatory field and cannot be null or empty.", 
            MessagesFunctionalExceptionUtils.getNullOrEmptyMessage("testParam"));
    }

    @Test
    void testGetMessageNameTooLong() {
        assertEquals("Name is too long. The maximum allowed length is 20. Received value: testName", 
            MessagesFunctionalExceptionUtils.getMessageNameTooLong(20, "testName"));
    }

    @Test
    void testGetMessageDateBirthInvalidFormat() {
        assertEquals("Date of Birth should be in the format of 'dd/MM/yyyy', 'dd-MM-yyyy', 'yyyy/MM/dd' or 'yyyy-MM-dd'. Received value: testDate", 
            MessagesFunctionalExceptionUtils.getMessageDateBirthInvalidFormat("testDate"));
    }

    @Test
    void testGetMessagePhoneNumberInvalidFr() {
        assertEquals("Phone number should be in French format (e.g. 06 01 01 01 01, 0601010101, +33701010101). Received value: testPhone", 
            MessagesFunctionalExceptionUtils.getMessagePhoneNumberInvalidFr("testPhone"));
    }

    @Test
    void testGetMessageIsNotOldEnoughFr() {
        assertEquals("User has not reached the age of majority witch is 18 years.  Received value: testAge", 
            MessagesFunctionalExceptionUtils.getMessageIsNotOldEnoughFr("testAge"));
    }

    @Test
    void testGetMessageCountryResidenceNotFrance() {
        assertEquals("France is the only Country of Residence accepted. Allowed values: FR, FRANCE, France. Received value: testCountry", 
            MessagesFunctionalExceptionUtils.getMessageCountryResidenceNotFrance("testCountry"));
    }

    @Test
    void testGetMessageNoErrors() {
        assertEquals("No Errors", MessagesFunctionalExceptionUtils.getMessageNoErrors());
    }

}