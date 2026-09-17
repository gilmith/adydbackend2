package org.jacobo.adyd.validators;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ExistingEntityValidatorTest {

    private ExistingEntity annotation(String value) {
        return new ExistingEntity() {
            @Override
            public String value() {
                return value;
            }

            @Override
            public String message() {
                return "The entity does not exist";
            }

            @Override
            public Class<?>[] groups() {
                return new Class[0];
            }

            @Override
            public Class<? extends jakarta.validation.Payload>[] payload() {
                return new Class[0];
            }

            @Override
            public Class<? extends java.lang.annotation.Annotation> annotationType() {
                return ExistingEntity.class;
            }
        };
    }

    @Test
    void validWhenEntityExists() {
        ExistingEntityValidator validator = new ExistingEntityValidator((entityName, id) -> true);

        validator.initialize(annotation("CAMPAIGN"));

        assertTrue(validator.isValid(1L, null));
    }

    @Test
    void invalidWhenEntityDoesNotExist() {
        ExistingEntityValidator validator = new ExistingEntityValidator((entityName, id) -> false);

        validator.initialize(annotation("CAMPAIGN"));

        assertFalse(validator.isValid(1L, null));
    }

    @Test
    void nullValueIsValid() {
        ExistingEntityValidator validator = new ExistingEntityValidator((entityName, id) -> false);

        validator.initialize(annotation("CAMPAIGN"));

        assertTrue(validator.isValid(null, null));
    }

    @Test
    void passesEntityNameAndIdToChecker() {
        String[] receivedName = new String[1];
        Long[] receivedId = new Long[1];
        ExistingEntityValidator validator = new ExistingEntityValidator((entityName, id) -> {
            receivedName[0] = entityName;
            receivedId[0] = id;
            return true;
        });

        validator.initialize(annotation("RACE"));

        validator.isValid(42L, null);
        assertEquals("RACE", receivedName[0]);
        assertEquals(42L, receivedId[0]);
    }
}