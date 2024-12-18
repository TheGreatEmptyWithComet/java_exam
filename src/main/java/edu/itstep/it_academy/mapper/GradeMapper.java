package edu.itstep.it_academy.mapper;

import edu.itstep.it_academy.dto.GradeDTO;
import edu.itstep.it_academy.dto.GradeOutDTO;
import edu.itstep.it_academy.entity.Grade;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface GradeMapper {
    GradeDTO toDTO(Grade grade);
    GradeOutDTO toOutDTO(Grade grade);
    Grade toEntity(GradeDTO gradeDTO);
}
