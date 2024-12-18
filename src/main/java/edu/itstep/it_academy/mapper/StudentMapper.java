package edu.itstep.it_academy.mapper;

import edu.itstep.it_academy.dto.StudentOutDTO;
import edu.itstep.it_academy.entity.Student;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface StudentMapper {
    StudentOutDTO toDTO(Student student);
    Student toEntity(StudentOutDTO studentOutDTO);
}
