package com.example.beans;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource("classpath:application.properties")
public class Employee {

    @Autowired
    public Address address;

    @Value("${authorName}")
    public String name;

    @Override
    public String toString() {
        return "Employee{" +
                "address=" + address +
                ", name='" + name + '\'' +
                '}';
    }
}
