package com.ericsson.tools.phonebook.common;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class PhoneNumberTest {
    Person person = null;
    PhoneNumber phoneNumber = null;
    Address address = null;

    @Before
    public void setUp() throws Exception {
        phoneNumber = new PhoneNumber("");
    }

    @Test
    public void testEqual() {
        phoneNumber.setPhoneNumber("1234567");
        assertEquals("1234567", phoneNumber.getPhoneNumber());
        assertEquals("1234567", phoneNumber.toString());
    }

}
