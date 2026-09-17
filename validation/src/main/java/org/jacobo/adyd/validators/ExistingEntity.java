package org.jacobo.adyd.validators;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Documented
@Target({ElementType.PARAMETER, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ExistingEntityValidator.class)
public @interface ExistingEntity {

    String value();

    String message() default "The entity does not exist";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}