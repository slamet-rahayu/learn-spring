package com.example.springboot.annotations;
import com.example.springboot.validation.UniqueConstraintValidator;
import com.example.springboot.validation.UniqueValidatior;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = UniqueValidatior.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Unique {
    String message() default "Value already exist";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    Class<? extends UniqueConstraintValidator> service();
}
