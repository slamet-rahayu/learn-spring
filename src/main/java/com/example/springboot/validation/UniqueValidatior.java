package com.example.springboot.validation;

import com.example.springboot.annotations.Unique;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.stereotype.Component;

@Component
public class UniqueValidatior implements ConstraintValidator<Unique, String> {

   private final UniqueConstraintValidator uniqueConstraint;

    public UniqueValidatior(UniqueConstraintValidator uniqueConstraint) {
        this.uniqueConstraint = uniqueConstraint;
    }

    @Override
    public void initialize(Unique constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return null != value && uniqueConstraint.isUnique(value);
    }
}
