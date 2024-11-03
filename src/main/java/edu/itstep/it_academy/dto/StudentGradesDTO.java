package edu.itstep.it_academy.dto;

import edu.itstep.it_academy.entity.Grade;
import edu.itstep.it_academy.entity.Student;
import edu.itstep.it_academy.entity.Subject;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentGradesDTO {
    private long subjectId;
    private Map<String, List<Grade>> grades;
    private List<Subject> subjects;
    private StudentOutDTO studentOutDTO;
}
