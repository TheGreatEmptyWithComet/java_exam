package edu.itstep.it_academy.response;

import lombok.Getter;

import java.util.List;

@Getter
public class ValidationErrorResponse extends ErrorResponse {
    private List<ValidationError> errors;

    public ValidationErrorResponse(int status, String message, List<ValidationError> errors) {
        super(status, message);
        this.errors = errors;
    }
}
