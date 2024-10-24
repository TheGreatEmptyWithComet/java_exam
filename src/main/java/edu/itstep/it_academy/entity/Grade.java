package edu.itstep.it_academy.entity;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "grades")
public class Grade {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private int grade;

    @Column
    private String comment;

    @Column
    private LocalDate date;

    @ManyToOne(cascade = {CascadeType.REFRESH, CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "subject_id")
    private Subject subject;

    @ManyToOne(cascade = {CascadeType.REFRESH, CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "student_id")
    private Student student;

    @Transient
    private List<LocalDate> scheduleDates = new ArrayList<>();


    // Constructors
    public Grade(Student student, Subject subject, LocalDate date, String comment, int grade) {
        this.student = student;
        this.subject = subject;
        this.date = date;
        this.comment = comment;
        this.grade = grade;
    }

    public Grade() {
    }


    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public List<LocalDate> getScheduleDates() {
        return scheduleDates;
    }

    public void setScheduleDates(List<LocalDate> scheduleDates) {
        this.scheduleDates = scheduleDates;
    }

    @Override
    public String toString() {
        return "Grade{" +
                "id=" + id +
                ", grade=" + grade +
                ", comment='" + comment + '\'' +
                ", date=" + date +
                ", subject=" + subject +
                ", student=" + student +
                '}';
    }
}
