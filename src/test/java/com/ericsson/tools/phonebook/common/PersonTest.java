/**
 * Created : Mar 12, 2012
 * 
 * Copyright (c) 2012 Ericsson (China) Communications Co.,Ltd. All rights reserved. The Copyright to the computer program(s) herein is the property of Ericsson
 * (China) Communications Co.,Ltd. The program(s) may be used and/or copied with the written permission from Ericsson AB or in accordance with the terms and
 * conditions stipulated in the agreement/contract under which the program(s) have been supplied.
 */
package com.ericsson.tools.phonebook.common;

import org.junit.Before;
import org.junit.Test;

import junit.framework.TestCase;

/**
 */
public class PersonTest extends TestCase {
    Person person = null;
    PhoneNumber phoneNumber = null;
    Address address = null;

    @Before
    public void setUp() throws Exception {
        address = new Address("");
        phoneNumber = new PhoneNumber("");
        person = new Person("", address, phoneNumber);
    }

    @Test
    public void testEqual() throws Exception {
        person.setName("name");
        person.setAddress(new Address("abc"));
        person.setPhoneNumbers(new PhoneNumber("1234567"));
        assertEquals("name", person.getName());
        assertEquals("abc", person.getAddress().toString());
        assertEquals("1234567", person.getPhoneNumber().toString());
        assertEquals("Person# Name: name | Address: abc | Phone Number: [1234567]", person.toString());
    }

}
