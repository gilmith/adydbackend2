package org.jacobo.adyd.infraestructure.mapper.gamestatic;

import org.jacobo.adyd.api.dto.*;
import org.jacobo.adyd.domain.model.staticgamevalues.*;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AbilitiesDtoMapper {


    StrengthDto toStrengthDto(StrengthModel abilityDto);

    DexterityDto toDexterityDto(DexterityModel abilityDto);

    CharismaDto toCharismaDto(CharismaModel abilityDto);

    IntelligenceDto toIntelligenceDto(IntelligenceModel abilityDto);

    WisdomDto toWisdomDto(WisdomModel abilityDto);

    ConstitutionDto toConstitutionDto(ConstitutionModel abilityDto);

    default AbilityDto toDto(BaseStaticPointModel abilityByBasePointId) {
        if (abilityByBasePointId == null) {
            return null;
        }
        return switch (abilityByBasePointId) {
            case StrengthModel m -> toStrengthDto(m);
            case DexterityModel m -> toDexterityDto(m);
            case IntelligenceModel m -> toIntelligenceDto(m);
            case WisdomModel m -> toWisdomDto(m);
            case ConstitutionModel m -> toConstitutionDto(m);
            case CharismaModel m -> toCharismaDto(m);
            default -> throw new IllegalArgumentException("Unsupported model: " + abilityByBasePointId.getClass());
        };
    }


}