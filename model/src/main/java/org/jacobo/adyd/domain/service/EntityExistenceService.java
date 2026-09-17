package org.jacobo.adyd.domain.service;

import org.jacobo.adyd.domain.validator.EntityType;

public interface EntityExistenceService {

    boolean exists(EntityType entityType, Long id);
}
