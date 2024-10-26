package edu.itstep.it_academy.entity;

import javax.persistence.*;

@Entity
@Table(name = "subjects")
public class Subject {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;


    // Constructors
    public Subject(String name) {
        this.name = name;
    }

    public Subject() {
    }


    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
