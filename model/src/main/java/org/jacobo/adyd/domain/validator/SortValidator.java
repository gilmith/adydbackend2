package org.jacobo.adyd.domain.validator;


import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ ElementType.PARAMETER, ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = SortValidatorImpl.class)
public @interface SortValidator {

    Class<?> value();

    String message() default "Invalid field to sort";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
