package com.example.beans;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PCSetup {
    @Autowired
    public Keyboard keyboard;

    @Autowired
    public Monitor monitor;

    @Override
    public String toString() {
        return "PCSetup{" +
                "keyboard=" + keyboard +
                ", monitor=" + monitor +
                '}';
    }
}
