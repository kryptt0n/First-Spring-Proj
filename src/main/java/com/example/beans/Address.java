package com.example.beans;

public class Address {
    public String street;
    public Integer homeNumber;

    public Address(String street, Integer homeNumber) {
        this.street = street;
        this.homeNumber = homeNumber;
    }

    @Override
    public String toString() {
        return "Address{" +
                "street='" + street + '\'' +
                ", homeNumber=" + homeNumber +
                '}';
    }
}
