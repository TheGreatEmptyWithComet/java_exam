package edu.itstep.it_academy.dto;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
public class TeacherOutDTO {
    private String firstName;
    private String lastName;
}
