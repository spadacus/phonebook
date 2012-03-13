package com.ericsson.tools.phonebook;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.Matchers.anyObject;

import com.ericsson.tools.phonebook.common.Address;
import com.ericsson.tools.phonebook.common.Person;
import com.ericsson.tools.phonebook.common.PhoneNumber;
import com.ericsson.tools.phonebook.common.Phonebook;

@RunWith(PowerMockRunner.class)
@PrepareForTest({ Phonebook.class, Logger.class, LoggerFactory.class })
public class MainTest {
    private static final String XMLFILE = "phonebook.xml";
    Phonebook phonebook = null;
    Phonebook phonebookMoc = mock(Phonebook.class);

    @Before
    public void setUp() throws Exception {

        List<Person> personList = new ArrayList<Person>();
        personList.add(new Person("Mr.A", new Address("tian"), new PhoneNumber("1234567")));
        personList.add(new Person("Mr.B", new Address("shan"), new PhoneNumber("2345678")));
        phonebook = new Phonebook(personList);
        when(phonebookMoc.saveToXML(anyString())).thenReturn(true);
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testStart() throws Exception {
        //TODO
    }

    @Test
    public void test() throws Exception {
        when(phonebookMoc.loadXML(anyString())).thenReturn(phonebook);
        Main.loadPhonebook(phonebookMoc);
        Main.phonebookExists(XMLFILE);
        String[] str = { "Mr.C", "xi", "3456789" };
        Main.addItemInPhonebook(str, phonebookMoc);
        Main.searchPeopleByName(phonebook, "Mr.A");
        Main.searchPeopleByPhoneNumber(phonebook, "1234567");

    }

    @Test
    public void testNeg() throws Exception {
        when(phonebookMoc.findPeopleWithPhoneNumber((PhoneNumber) anyObject())).thenReturn(null);
        when(phonebookMoc.findPeopleWithName(anyString())).thenReturn(null);
        Main.searchPeopleByName(phonebookMoc, "Mr.A");
        Main.searchPeopleByPhoneNumber(phonebookMoc, "1234567");
    }
}
