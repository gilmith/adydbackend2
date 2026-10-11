package org.jacobo.adyd.infraestructure.mapper.staticgame;


import org.jacobo.adyd.domain.model.staticgamevalues.*;
import org.jacobo.adyd.domain.service.staticgame.StaticAbilitiesEnum;
import org.jacobo.adyd.infraestructure.entities.staticgame.*;
import org.jacobo.adyd.infraestructure.persistence.staticgame.StaticAbilitiesEntityEnum;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface StaticAbilityMapper {

    default StaticAbilitiesEntityEnum toStaticAbilitiesEntityEnum(StaticAbilitiesEnum staticAbilitiesEnum) {
        if (staticAbilitiesEnum == null) {
            return null;
        }
        return switch (staticAbilitiesEnum) {
            case STRENGTH -> StaticAbilitiesEntityEnum.STRENGTH;
            case DEXTERITY -> StaticAbilitiesEntityEnum.DEXTERITY;
            case INTELLIGENCE -> StaticAbilitiesEntityEnum.INTELLIGENCE;
            case WISDOM -> StaticAbilitiesEntityEnum.WISDOM;
            case CONSTITUTION -> StaticAbilitiesEntityEnum.CONSTITUTION;
            case CHARISMA -> StaticAbilitiesEntityEnum.CHARISMA;
        };
    }

    @SuppressWarnings("unchecked")
    default <T extends BaseStaticPointModel> T toDomain(StaticTableBasePointEntity entity) {
        if (entity == null) {
            return null;
        }
        return (T) switch (entity) {
            case StrengthEntity e -> toStrengthModel(e);
            case DexterityEntity e -> toDexterityModel(e);
            case IntelligenceEntity e -> toIntelligenceModel(e);
            case WisdomEntity e -> toWisdomModel(e);
            case ConstitutionEntity e -> toConstitutionModel(e);
            case CharismaEntity e -> toCharismaModel(e);
            default -> throw new IllegalArgumentException("Unsupported entity: " + entity.getClass());
        };
    }

    StrengthModel toStrengthModel(StrengthEntity strengthEntity);

    DexterityModel toDexterityModel(DexterityEntity dexterityEntity);

    IntelligenceModel toIntelligenceModel(IntelligenceEntity intelligenceEntity);

    WisdomModel toWisdomModel(WisdomEntity wisdomEntity);

    ConstitutionModel toConstitutionModel(ConstitutionEntity constitutionEntity);

    CharismaModel toCharismaModel(CharismaEntity charismaEntity);
}
