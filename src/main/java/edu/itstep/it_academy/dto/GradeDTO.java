package edu.itstep.it_academy.dto;

import edu.itstep.it_academy.entity.Grade;
import edu.itstep.it_academy.entity.Student;
import edu.itstep.it_academy.entity.Subject;
import jakarta.persistence.CascadeType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GradeDTO {
    private long subjectId;
    private long studentId;
    private List<Student> students;
    private List<Subject> subjects;

    private Long id;
    @Min(value = 1, message = "Grade must be 1 or bigger")
    @Max(value = 100, message = "Grade must be 100 or less")
    private int grade;
    private String comment;
    private LocalDate date;
    private Subject subject;
    private Student student;
}