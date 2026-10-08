package org.jacobo.adyd.domain.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import jakarta.validation.constraintvalidation.SupportedValidationTarget;
import jakarta.validation.constraintvalidation.ValidationTarget;
import org.springframework.data.core.PropertyPath;
import org.springframework.data.core.PropertyReferenceException;
import org.springframework.data.domain.PageRequest;

@SupportedValidationTarget(ValidationTarget.PARAMETERS)
public class SortValidatorImpl implements ConstraintValidator<SortValidator, Object[]> {

    private Class<?> clazz;

    @Override
    public void initialize(SortValidator constraintAnnotation) {
        clazz = constraintAnnotation.value();
    }

    @Override
    public boolean isValid(Object[] value, ConstraintValidatorContext context) {
        if (value == null || value.length == 0) return true;
        PageRequest pageRequest = (PageRequest) value[0];
        if (pageRequest == null || pageRequest.getSort().isUnsorted()) return true;
        try {
            pageRequest.getSort().stream().toList().forEach(sort ->
                    PropertyPath.from(sort.getProperty(), clazz));
            return true;
        } catch (PropertyReferenceException e) {
            return false;
        }
    }
}
