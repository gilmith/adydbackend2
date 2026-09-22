package org.jacobo.adyd.domain.model;

public enum BuiltInCharacteristicsEnum {

    STRENGTH_MINIMUM_RACE(CharacteristicModel.builder()
            .code("STRENGTH_MINIMUM_RACE")
            .name("STRENGTH_MINIMUM")
            .type(TypeValueEnum.INTEGER)
            .builtIn(Boolean.TRUE)
            .build()),
    DEXTERITY_MINIMUM_RACE(CharacteristicModel.builder()
            .code("DEXTERITY_MINIMUM_RACE")
            .name("DEXTERITY_MINIMUM")
            .type(TypeValueEnum.INTEGER)
            .builtIn(Boolean.TRUE)
            .build()),
    CONSTITUTION_MINIMUM_RACE(CharacteristicModel.builder()
            .code("CONSTITUTION_MINIMUM_RACE")
            .name("CONSTITUTION_MINIMUM")
            .type(TypeValueEnum.INTEGER)
            .builtIn(Boolean.TRUE)
            .build()),
    INTELLIGENCE_MINIMUM_RACE(CharacteristicModel.builder()
            .code("INTELLIGENCE_MINIMUM_RACE")
            .name("INTELLIGENCE_MINIMUM")
            .type(TypeValueEnum.INTEGER)
            .builtIn(Boolean.TRUE)
            .build()),
    WISDOM_MINIMUM_RACE(CharacteristicModel.builder()
            .code("WISDOM_MINIMUM_RACE")
            .name("WISDOM_MINIMUM")
            .type(TypeValueEnum.INTEGER)
            .builtIn(Boolean.TRUE)
            .build()),
    CHARISMA_MINIMUM_RACE(CharacteristicModel.builder()
            .code("CHARISMA_MINIMUM_RACE")
            .name("CHARISMA_MINIMUM")
            .type(TypeValueEnum.INTEGER)
            .builtIn(Boolean.TRUE)
            .build());

    private final CharacteristicModel characteristicMeta;

    BuiltInCharacteristicsEnum(CharacteristicModel characteristicMeta) {
        this.characteristicMeta = characteristicMeta;
    }

    public CharacteristicModel getCharacteristicMeta() {
        return characteristicMeta;
    }
}