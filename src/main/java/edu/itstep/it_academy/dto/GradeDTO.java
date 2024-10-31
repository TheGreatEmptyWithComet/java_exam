package edu.itstep.it_academy.dto;

import edu.itstep.it_academy.entity.Student;
import edu.itstep.it_academy.entity.Subject;
import edu.itstep.it_academy.validation.DateRange;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
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
    @NotNull(message = "The grade must not be empty")
    @DateRange
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate date;
    private Subject subject;
    private Student student;
}