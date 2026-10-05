package org.jacobo.adyd.infraestructure.mapper;


import org.jacobo.adyd.domain.model.RaceModel;
import org.jacobo.adyd.infraestructure.entities.RaceEntity;
import org.mapstruct.Mapper;
import org.springframework.data.domain.Page;

@Mapper(componentModel = "spring")
public interface RaceMapper {

    RaceModel toDomain(RaceEntity raceEntity);

    RaceEntity toEntity(RaceModel raceModel);


    default Page<RaceModel> toDomain(Page<RaceEntity> raceEntities) {
        return raceEntities.map(this::toDomain);
    }
}
