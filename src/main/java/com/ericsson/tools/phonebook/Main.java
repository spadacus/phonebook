package com.ericsson.tools.phonebook;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.ericsson.tools.phonebook.common.Address;
import com.ericsson.tools.phonebook.common.Person;
import com.ericsson.tools.phonebook.common.PhoneNumber;
import com.ericsson.tools.phonebook.common.Phonebook;

public class Main {
    private static final String DEFAULT_PHONEBOOK_NAME = "phonebook.xml";
    
    public static void main(String[] args) throws Exception {
        Phonebook phonebook = new Phonebook();
        loadPhonebook(phonebook);
        startOper(phonebook);
        
    }

    protected static void startOper(Phonebook phonebook) throws Exception {
        int number = 0;
        while (number != 4) {
            System.out.println("Pick an option: \n\t1. Add new item in phonebook \n\t2. Look up a person  \n\t3. List all \n\t4. Exit");
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            switch (Integer.parseInt(br.readLine())) {
            case 1:
                System.out.println("Please input Name,Address,PhoneNumber seperate by ,");
                addItemInPhonebook(br.readLine().split(","), phonebook);
                break;
            case 2:
                while (number != 3) {
                    System.out.println("Look up by:\n\t1. Name\n\t2. Phone Number\n\t3. Back");
                    switch (Integer.parseInt(br.readLine())) {
                    case 1:
                        System.out.print("Please enter a name to search for: ");
                        searchPeopleByName(phonebook, br.readLine().trim());
                        break;
                    case 2:
                        System.out.print("Please enter a phone number to search for: ");
                        searchPeopleByPhoneNumber(phonebook, br.readLine().trim());
                        break;
                    case 3:
                        break;
                    default:
                        System.out.println("Please enter a number between 1 and 3");
                        break;
                    }
                }
                break;
            case 3:
                System.out.println(phonebook.toString());
                break;
            case 4:
                System.exit(0);
                break;
            default:
                System.out.println("Please enter a number between 1 and 4");
                break;
            }
        }
        
    }

    protected static void loadPhonebook(Phonebook phonebook) {
        if (phonebookExists(DEFAULT_PHONEBOOK_NAME)) {
            phonebook = phonebook.loadXML(DEFAULT_PHONEBOOK_NAME);
        }
    }

    protected static void searchPeopleByName(final Phonebook phonebook, final String name) {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.execute(new Runnable() {
            public void run() {
                List<Person> peopleWithname = phonebook.findPeopleWithName(name);
                if (peopleWithname != null) {
                    for (Person person : peopleWithname) {
                        System.out.println(person);
                    }
                } else {
                    System.out.println("Couldn't find anyone with a name of '" + name + "'");
                }
            }
        });

    }

    protected static void searchPeopleByPhoneNumber(final Phonebook phonebook, final String phoneNumber) {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.execute(new Runnable() {
            public void run() {
                List<Person> peopleFromPhoneNumber = phonebook.findPeopleWithPhoneNumber(new PhoneNumber(phoneNumber));

                if (peopleFromPhoneNumber != null) {
                    for (Person person : peopleFromPhoneNumber) {
                        System.out.println(person);
                    }
                } else {
                    System.out.println("Couldn't find anyone with a phone number of '" + phoneNumber + "'");
                }
            }
        });
    }

    protected static boolean phonebookExists(String filename) {
        return new File(filename).exists();
    }

    protected static boolean addItemInPhonebook(final String[] str, final Phonebook phonebook) {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.execute(new Runnable() {
            public void run() {
                Address address = new Address(str[1]);
                PhoneNumber phoneNumber = new PhoneNumber(str[2]);
                Person person = new Person(str[0], address, phoneNumber);

                phonebook.addItem(person);
                phonebook.saveToXML(DEFAULT_PHONEBOOK_NAME);
            }
        });
        return true;
    }
}
