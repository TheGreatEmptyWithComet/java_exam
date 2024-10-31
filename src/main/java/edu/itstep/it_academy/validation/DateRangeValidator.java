package edu.itstep.it_academy.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.time.LocalDate;

public class DateRangeValidator implements ConstraintValidator<DateRange, LocalDate> {

    @Override
    public boolean isValid(LocalDate date, ConstraintValidatorContext context) {
        if (date == null) return false; // Allow null if that's acceptable

        LocalDate today = LocalDate.now();
        LocalDate threeMonthsAgo = today.minusMonths(3);

        return !date.isAfter(today) && !date.isBefore(threeMonthsAgo);
    }
}