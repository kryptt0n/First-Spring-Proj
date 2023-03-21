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

    @Override
    public String toString() {
        return "Food{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
