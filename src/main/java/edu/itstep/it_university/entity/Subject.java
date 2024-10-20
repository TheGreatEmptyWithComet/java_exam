package edu.itstep.it_university.entity;

import javax.persistence.*;

@Entity
@Table(name = "grades")
public class Subject {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private String name;


    // Constructors
    public Subject(String name) {
        this.name = name;
    }

    public Subject() {
    }


    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
