package com.example.beans;

public class Keyboard {

    public String name;
    public Integer price;

    public Keyboard(String name, Integer price) {
        this.name = name;
        this.price = price;
    }

    @Override
    public String toString() {
        return "Keyboard{" +
                "name='" + name + '\'' +
                ", price=" + price +
                '}';
    }
}
