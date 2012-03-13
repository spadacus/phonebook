package com.ericsson.tools.phonebook.common;

import java.io.Serializable;

public class PhoneNumber implements Serializable {

    private static final long serialVersionUID = 4922256141952711189L;

    private String phoneNumber;

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public PhoneNumber(String phoneNumber) {
        super();
        this.phoneNumber = phoneNumber;
    }

    public String toString() {
        return phoneNumber;
    }
}
