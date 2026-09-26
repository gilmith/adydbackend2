package org.jacobo.adyd.application.service;

import lombok.RequiredArgsConstructor;
import org.jacobo.adyd.domain.exception.NotFoundRunTimeException;
import org.jacobo.adyd.domain.repository.CampaignRepository;
import org.jacobo.adyd.domain.repository.PlayerClassRepository;
import org.jacobo.adyd.domain.repository.RaceRepository;
import org.jacobo.adyd.domain.service.EntityExistenceService;
import org.jacobo.adyd.domain.validator.EntityType;
import org.jacobo.adyd.validators.EntityExistenceChecker;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EntityExistenceServiceImpl implements EntityExistenceService, EntityExistenceChecker {

    private final CampaignRepository campaignRepository;
    private final RaceRepository raceRepository;
    private final PlayerClassRepository playerClassRepository;

    @Override
    public boolean exists(EntityType type, Long id) {
        return switch (type) {
            case CAMPAIGN -> campaignRepository.existsById(id);
            case RACE -> raceRepository.existsById(id);
            case PLAYER_CLASS -> playerClassRepository.existsById(id);
        };
    }

    @Override
    public boolean exists(String entityName, Long id) {
        try {
            if (exists(EntityType.valueOf(entityName), id)){
                return true;
            }
            throw  new NotFoundRunTimeException("Not found entity with name: " + entityName);
        } catch (IllegalArgumentException e) {
            return false;
        }
    }
}