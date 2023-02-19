package com.example.database;

import jakarta.persistence.*;

import java.sql.Date;

@Entity
@Table(name = "tasks")
public class Task {

    @Id
    @GeneratedValue
    @Column(name = "id")
    public Integer id;

    @Column(name = "name", length = 100)
    public String name;

    @Column(name = "register_date")
    public Date register_date;


    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    public User user_id;

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", register_date=" + register_date +
                ", user_id=" + user_id +
                '}';
    }
}
