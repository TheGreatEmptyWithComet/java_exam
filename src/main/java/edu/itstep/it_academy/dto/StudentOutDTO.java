package edu.itstep.it_academy.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class StudentOutDTO {
    private Long id;
    private String firstName;
    private String lastName;
}