package com.example.database;

import jakarta.persistence.*;

@Table(name = "food")
@Entity
public class Food {

    @Id
    @Column(name = "food_id")
    @GeneratedValue
    public Integer id;

    @Column
    public String name;

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Food{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
