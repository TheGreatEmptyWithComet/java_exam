package edu.itstep.it_academy.dto;

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
public class TeacherStudentsDTO {
    private long subjectId;
    private Map<StudentOutDTO, List<GradeOutDTO>> students; // TODO add DTO
    private List<SubjectOutDTO> subjects; // TODO add DTO
    private TeacherOutDTO teacherOutDTO;
}