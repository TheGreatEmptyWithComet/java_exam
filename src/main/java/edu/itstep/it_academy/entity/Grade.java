package edu.itstep.it_academy.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "grades")
public class Grade {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Min(value = 1, message = "Grade must be 1 or bigger")
    @Max(value = 100, message = "Grade must be 100 or less")
    private int grade;
    private String comment;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate date;

    @ManyToOne(cascade = {CascadeType.REFRESH, CascadeType.DETACH, CascadeType.MERGE})
    @JoinColumn(name = "subject_id")
    private Subject subject;

    @ManyToOne(cascade = {CascadeType.REFRESH, CascadeType.DETACH, CascadeType.MERGE})
    @JoinColumn(name = "student_id")
    private Student student;


    // Custom constructors
    public Grade(Student student, Subject subject, LocalDate date, String comment, int grade) {
        this.student = student;
        this.subject = subject;
        this.date = date;
        this.comment = comment;
        this.grade = grade;
    }


    @Override
    public String toString() {
        return "Grade{" +
                "id=" + id +
                ", grade=" + grade +
                ", comment='" + comment + '\'' +
                ", date=" + date +
                ", subject=" + subject +
                '}';
    }
}
