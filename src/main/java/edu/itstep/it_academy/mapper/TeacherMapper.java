package edu.itstep.it_academy.mapper;

import edu.itstep.it_academy.dto.TeacherOutDTO;
import edu.itstep.it_academy.entity.Teacher;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TeacherMapper {
    TeacherOutDTO toDTO(Teacher teacher);
    Teacher toEntity(TeacherOutDTO teacherOutDTO);
}
