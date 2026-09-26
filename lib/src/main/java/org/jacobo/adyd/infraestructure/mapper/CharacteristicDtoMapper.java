package org.jacobo.adyd.infraestructure.mapper;

import org.jacobo.adyd.api.dto.CharacteristicDto;
import org.jacobo.adyd.api.dto.CharacteristicToModifyDto;
import org.jacobo.adyd.domain.model.CharacteristicModel;
import org.jacobo.adyd.domain.model.SymbolTypeEnum;
import org.jacobo.adyd.domain.model.TypeValueEnum;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface CharacteristicDtoMapper {

    CharacteristicDto toDto(CharacteristicModel characteristic);

    CharacteristicModel toModel(CharacteristicDto characteristicDto);

    CharacteristicModel toModel(CharacteristicToModifyDto characteristicDto);

    default TypeValueEnum map(CharacteristicDto.TypeEnum type) {
        return type == null ? null : TypeValueEnum.valueOf(type.name());
    }

    default CharacteristicDto.TypeEnum map(TypeValueEnum type) {
        return type == null ? null : CharacteristicDto.TypeEnum.valueOf(type.name());
    }

    default CharacteristicDto.SymbolEnum map(SymbolTypeEnum symbol) {
        return symbol == null ? null : CharacteristicDto.SymbolEnum.valueOf(symbol.name());
    }

    default SymbolTypeEnum map(CharacteristicDto.SymbolEnum symbol) {
        return symbol == null ? null : SymbolTypeEnum.valueOf(symbol.name());
    }
}