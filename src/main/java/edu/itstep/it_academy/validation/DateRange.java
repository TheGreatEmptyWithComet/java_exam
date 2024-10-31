package edu.itstep.it_academy.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = DateRangeValidator.class)
@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface DateRange {
    String message() default "Date must be within the last 3 months and cannot be in the future";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
