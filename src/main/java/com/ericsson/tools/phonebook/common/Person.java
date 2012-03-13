/**
 * Created : Mar 11, 2012
 * 
 * Copyright (c) 2012 Ericsson (China) Communications Co.,Ltd. All rights reserved. The Copyright to the computer program(s) herein is the property of Ericsson
 * (China) Communications Co.,Ltd. The program(s) may be used and/or copied with the written permission from Ericsson AB or in accordance with the terms and
 * conditions stipulated in the agreement/contract under which the program(s) have been supplied.
 */
package com.ericsson.tools.phonebook.common;

import java.io.Serializable;

public class Person implements Serializable {

    private static final long serialVersionUID = 1513862870479192109L;

    // constructors
    public Person(String name, Address address, PhoneNumber phoneNumber) {
        super();

//        if (name == null || address == null || phoneNumber == null) {
//            throw new IllegalArgumentException("name,address and phone number are required");
//        }

        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }

    // fields
    private String name;
    private Address address;
    private PhoneNumber phoneNumber;

    // getters and setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public PhoneNumber getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumbers(PhoneNumber phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    // public methods
    public String toString() {
        String personString = String.format("Person# Name: %s | Address: %s | Phone Number: [", getName(), getAddress());

        PhoneNumber phoneNum = getPhoneNumber();
        personString += phoneNum.getPhoneNumber();
        personString += "]";

        return personString;
    }

}
