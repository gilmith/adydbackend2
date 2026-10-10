package org.jacobo.adyd.infraestructure.mapper.gamestatic;

import org.jacobo.adyd.api.dto.AlignmentDto;
import org.jacobo.adyd.api.dto.PaginatedDto;
import org.jacobo.adyd.domain.model.staticgamevalues.AlignmentModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.data.domain.Page;

@Mapper(componentModel = "spring")
public interface AlignmentDtoMapper {

    @Mapping(target = "page", source = "number")
    @Mapping(target = "totalPages", source = "totalPages")
    @Mapping(target = "totalElements", source = "totalElements")
    @Mapping(target = "content", source = "content")
    PaginatedDto toDto(Page<AlignmentModel> page);

    AlignmentDto toDto(AlignmentModel model);

}
