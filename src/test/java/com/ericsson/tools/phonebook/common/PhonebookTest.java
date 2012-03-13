/**
 * Created : Mar 13, 2012
 * 
 * Copyright (c) 2012 Ericsson (China) Communications Co.,Ltd. All rights reserved. The Copyright to the computer program(s) herein is the property of Ericsson
 * (China) Communications Co.,Ltd. The program(s) may be used and/or copied with the written permission from Ericsson AB or in accordance with the terms and
 * conditions stipulated in the agreement/contract under which the program(s) have been supplied.
 */
package com.ericsson.tools.phonebook.common;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

/**
 * 
 */
public class PhonebookTest {
    private static final String XMLFILE = "test_phonebook.xml";
    Phonebook phonebook = null;

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        List<Person> personList = new ArrayList<Person>();
        personList.add(new Person("Mr.A", new Address("tian"), new PhoneNumber("1234567")));
        personList.add(new Person("Mr.B", new Address("shan"), new PhoneNumber("2345678")));
        phonebook = new Phonebook();
        phonebook = new Phonebook(personList);
    }

    /**
     * Test method for {@link com.ericsson.tools.phonebook.common.Phonebook#testfindPeopleWithName(java.lang.String)}.
     */
    @Test
    public void testfindPeopleWithName() {
        List<Person> personL = new ArrayList<Person>();
        personL = phonebook.findPeopleWithName("Mr.A");
        assertEquals("Mr.A", personL.get(0).getName());
    }

    @Test
    public void testfindPeopleWithPhoneNumber() {
        List<Person> personL = new ArrayList<Person>();
        personL = phonebook.findPeopleWithPhoneNumber(new PhoneNumber("1234567"));
        assertEquals("Mr.A", personL.get(0).getName());
    }
    
    @Test
    public void testtoString() {
        assertEquals("Person# Name: Mr.A | Address: tian | PhoneNumber: [1234567]\nPerson# Name: Mr.B | Address: shan | PhoneNumber: [2345678]\n", phonebook.toString());
    }
    
    @Test
    public void testaddItem() {
        phonebook.addItem(new Person("Mr.C", new Address("xi"), new PhoneNumber("3456789")));
        List<Person> personL = new ArrayList<Person>();
        personL = phonebook.findPeopleWithName("Mr.C");
        assertEquals("Mr.C", personL.get(0).getName());
    }

    /**
     * Test method for {@link com.ericsson.tools.phonebook.common.Phonebook#loadXML(java.lang.String)}.
     */
    @Test
    public void testXML() {
        phonebook.saveToXML(XMLFILE);
        phonebook.loadXML(XMLFILE);
    }

}
