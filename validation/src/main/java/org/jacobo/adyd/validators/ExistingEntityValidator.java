package org.jacobo.adyd.validators;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class ExistingEntityValidator implements ConstraintValidator<ExistingEntity, Long> {

    private final EntityExistenceChecker checker;

    private String entityName;

    public ExistingEntityValidator(EntityExistenceChecker checker) {
        this.checker = checker;
    }

    @Override
    public void initialize(ExistingEntity constraintAnnotation) {
        this.entityName = constraintAnnotation.value();
    }

    @Override
    public boolean isValid(Long value, ConstraintValidatorContext context) {
        if (value == null) {
            return true;
        }
        return checker.exists(entityName, value);
    }
}