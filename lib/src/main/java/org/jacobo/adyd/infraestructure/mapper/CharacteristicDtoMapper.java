package org.jacobo.adyd.infraestructure.mapper;

import com.google.common.base.Strings;
import org.jacobo.adyd.api.dto.CharacteristicBuiltInDto;
import org.jacobo.adyd.api.dto.CharacteristicDto;
import org.jacobo.adyd.api.dto.CharacteristicToModifyDto;
import org.jacobo.adyd.domain.model.CharacteristicBuildInModel;
import org.jacobo.adyd.domain.model.CharacteristicModel;
import org.jacobo.adyd.domain.model.SymbolTypeEnum;
import org.jacobo.adyd.domain.model.TypeValueEnum;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface CharacteristicDtoMapper {

    CharacteristicDto toDto(CharacteristicModel characteristic);

    CharacteristicModel toModel(CharacteristicDto characteristicDto);

    CharacteristicModel toModel(CharacteristicToModifyDto characteristicDto);

    @Mapping(target = "name", source = "characteristicName")
    @Mapping(target = "type", source = "characteristicType")
    CharacteristicBuiltInDto toDto(CharacteristicBuildInModel characteristicBuildInModel);

    List<CharacteristicBuiltInDto> toDtos(List<CharacteristicBuildInModel> characteristicBuildInModelList);

    default CharacteristicBuiltInDto.NameEnum mapName(String name){
        return Strings.isNullOrEmpty(name) ? null : CharacteristicBuiltInDto.NameEnum.valueOf(name);
    }

    default CharacteristicBuiltInDto.TypeEnum mapType(String type) {
        return Strings.isNullOrEmpty(type) ? null : CharacteristicBuiltInDto.TypeEnum.valueOf(type);
    }

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