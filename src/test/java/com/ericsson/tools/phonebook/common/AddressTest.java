package com.ericsson.tools.phonebook.common;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class AddressTest {
    Address address = null;

    @Before
    public void setUp() throws Exception {
        address = new Address("");
    }

    @Test
    public void testEqual() {
        address.setAddress("abc");
        assertEquals("abc", address.getAddress());
        assertEquals("abc", address.toString());
    }

}
