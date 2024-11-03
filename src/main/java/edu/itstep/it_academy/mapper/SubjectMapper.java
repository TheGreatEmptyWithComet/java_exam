package edu.itstep.it_academy.mapper;

import edu.itstep.it_academy.dto.StudentOutDTO;
import edu.itstep.it_academy.dto.SubjectOutDTO;
import edu.itstep.it_academy.entity.Student;
import edu.itstep.it_academy.entity.Subject;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SubjectMapper {
    SubjectOutDTO toDTO(Subject subject);
    Subject toEntity(SubjectOutDTO subjectOutDTO);
}
