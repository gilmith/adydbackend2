package org.jacobo.adyd.infraestructure.mapper;

import org.jacobo.adyd.domain.model.PlayerClassModel;
import org.jacobo.adyd.infraestructure.entities.PlayerClassEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PlayerClassMapper {

    PlayerClassModel toDomain(PlayerClassEntity playerClassEntity);

    PlayerClassEntity toEntity(PlayerClassModel playerClass);
}
