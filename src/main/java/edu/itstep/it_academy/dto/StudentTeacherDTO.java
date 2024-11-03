package edu.itstep.it_academy.dto;

import edu.itstep.it_academy.entity.Student;
import edu.itstep.it_academy.entity.Subject;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentTeacherDTO {
    private long subjectId;
    private List<Student> students; // TODO add DTO
    private List<Subject> subjects; // TODO add DTO
    private TeacherOutDTO teacherOutDTO;
}