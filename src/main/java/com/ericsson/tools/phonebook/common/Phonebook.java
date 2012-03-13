package com.ericsson.tools.phonebook.common;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.io.Serializable;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;


public class Phonebook implements Serializable {

    private static final long serialVersionUID = -6824316704912481869L;

    private List<Person> personList = new ArrayList<Person>();
    private HashMap<String, Person> phoneNumberMap = new HashMap<String, Person>();
    private HashMap<String, Person> nameMap = new HashMap<String, Person>();
    DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
    private static final Logger LOGGER = LoggerFactory.getLogger(Phonebook.class);
    // constructors
    public Phonebook(Collection<Person> listOfPeople) {
        personList = (List<Person>) listOfPeople;
        for (Person person : listOfPeople) {
            nameMap.put(person.getName().trim().toLowerCase(), person);
            phoneNumberMap.put(person.getPhoneNumber().toString().trim().replaceAll("\\D", ""), person);
        }
    }

    public Phonebook() {

    }

    public void addItem(Person person) {
        personList.add(person);
        nameMap.put(person.getName().toString().trim().toLowerCase(), person);
        phoneNumberMap.put(person.getPhoneNumber().toString().trim().replaceAll("\\D", ""), person);
        // searchablePhonebook.put(person.getAddress().toString().trim().toLowerCase(), person);
    }

    public String toString() {
        String phonebookString = "";
        for (String name : nameMap.keySet()) {
            Person person = nameMap.get(name);
            phonebookString += String.format("Person# Name: %s | Address: %s | PhoneNumber: [", person.getName(), person.getAddress());
            PhoneNumber phoneNum = person.getPhoneNumber();
            phonebookString += phoneNum.getPhoneNumber();
            phonebookString += "]\n";
        }
        return phonebookString;
    }

    public boolean saveToXML(String filename) {

        DocumentBuilder builder;
        try {
            builder = factory.newDocumentBuilder();
            Document doc = builder.newDocument();
            Element personsEle = doc.createElement("Persons");

            for (Person person : personList) {
                Element personEle = doc.createElement("Person");
                personEle.setAttribute("Name", person.getName().toString());
                personEle.setAttribute("Address", person.getAddress().toString());
                personEle.setAttribute("PhoneNumber", person.getPhoneNumber().toString());
                personsEle.appendChild(personEle);
            }
            doc.appendChild(personsEle);

            TransformerFactory tFactory = TransformerFactory.newInstance();
            Transformer trans = tFactory.newTransformer();
            Source s = new DOMSource(doc);
            Result res = new StreamResult(new FileOutputStream(filename));
            trans.transform(s, res);
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
            LOGGER.warn("Failed to parse conf.");
        } catch (TransformerConfigurationException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            LOGGER.warn("Failed to found xml.");
        } catch (TransformerException e) {
            e.printStackTrace();
        }

        return true;
    }

    public List<Person> findPeopleWithName(String name) {
        List<Person> people = new ArrayList<Person>();
        people.add(nameMap.get(name.trim().toLowerCase()));
        return people;
    }

    public List<Person> findPeopleWithPhoneNumber(PhoneNumber phoneNumber) {
        List<Person> people = new ArrayList<Person>();
        Pattern p = Pattern.compile(phoneNumber.toString().trim().replace("\\D", ""));
        for (String key : phoneNumberMap.keySet()) {
            Matcher m = p.matcher(key);
            if (m.find()) {
                people.add(phoneNumberMap.get(key));
            }
        }
        return people;
    }

    public Phonebook loadXML(String filename) {

        List<Person> personList = new ArrayList<Person>();

        DocumentBuilder builder;
        try {
            builder = factory.newDocumentBuilder();
            try {
                Document doc = builder.parse(filename);
                Element personsEle = doc.getDocumentElement();
                NodeList nodeList = personsEle.getElementsByTagName("Person");
                for (int i = 0; i < nodeList.getLength(); i++) {
                    Element personEle = (Element) nodeList.item(i);
                    Address address = new Address(personEle.getAttribute("Address"));
                    PhoneNumber phoneNumber = new PhoneNumber(personEle.getAttribute("PhoneNumber"));
                    Person person = new Person(personEle.getAttribute("Name"), address, phoneNumber);
                    personList.add(person);
                }

            } catch (SAXException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
                LOGGER.warn("Failed to read xml.");
            }

        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
        return new Phonebook(personList);
    }
}