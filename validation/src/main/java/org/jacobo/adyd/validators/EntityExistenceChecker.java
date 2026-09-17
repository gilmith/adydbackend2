package org.jacobo.adyd.validators;

public interface EntityExistenceChecker {

    boolean exists(String entityName, Long id);
}