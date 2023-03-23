package com.example.beans;

public class Monitor {

    public String name;
    public String size;

    public Monitor(String name, String size) {
        this.name = name;
        this.size = size;
    }

    @Override
    public String toString() {
        return "Monitor{" +
                "name='" + name + '\'' +
                ", size='" + size + '\'' +
                '}';
    }
}
