package edu.itstep.it_academy.exception;

public class GradeNotFoundException extends RuntimeException {
    public GradeNotFoundException(Long id) {
        super("Grade with id " + id + " not found");
    }
}
