package com.ericsson.tools.phonebook.common;

import java.io.Serializable;

public class Address implements Serializable {

    private static final long serialVersionUID = 4922256141952711189L;

    private String address;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Address(String address) {
        super();
        this.address = address;
    }

    public String toString() {
        return address;
    }
}
