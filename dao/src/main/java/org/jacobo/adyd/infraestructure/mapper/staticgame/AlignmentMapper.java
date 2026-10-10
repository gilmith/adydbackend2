package org.jacobo.adyd.infraestructure.mapper.staticgame;

import org.jacobo.adyd.domain.model.staticgamevalues.AlignmentModel;
import org.jacobo.adyd.infraestructure.entities.staticgame.AlignmentEntity;
import org.mapstruct.Mapper;
import org.springframework.data.domain.Page;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AlignmentMapper {


    AlignmentEntity toEntity(AlignmentModel alignment);

    AlignmentModel toModel(AlignmentEntity alignment);

    List<AlignmentEntity> toEntity(List<AlignmentModel> alignment);

    default Page<AlignmentModel> toModel(Page<AlignmentEntity> alignmentEntities) {
        return alignmentEntities.map(this::toModel);
    }
}
