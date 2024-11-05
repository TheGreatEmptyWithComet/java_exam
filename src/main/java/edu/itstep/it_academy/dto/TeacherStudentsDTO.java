package edu.itstep.it_academy.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TeacherStudentsDTO {
    private Long subjectId;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate date;
    private Map<StudentOutDTO, List<GradeOutDTO>> students;
    private List<SubjectOutDTO> subjects;
    private TeacherOutDTO teacherOutDTO;
}