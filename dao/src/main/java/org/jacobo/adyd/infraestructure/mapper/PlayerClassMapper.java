package org.jacobo.adyd.infraestructure.mapper;

import org.jacobo.adyd.domain.model.PlayerClassModel;
import org.jacobo.adyd.infraestructure.entities.PlayerClassEntity;
import org.mapstruct.Mapper;
import org.springframework.data.domain.Page;

@Mapper(componentModel = "spring")
public interface PlayerClassMapper {

    PlayerClassModel toDomain(PlayerClassEntity playerClassEntity);

    PlayerClassEntity toEntity(PlayerClassModel playerClass);

    default Page<PlayerClassModel> toDomain(Page<PlayerClassEntity> playerClassEntities) {
        return playerClassEntities.map(this::toDomain);
    }
}
