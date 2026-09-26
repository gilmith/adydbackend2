package org.jacobo.adyd.domain.model;

public enum BuiltInPlayerCharacteristicsEnum {

    STRENGTH_MINIMUM_CLASS(CharacteristicModel.builder()
            .code("STRENGTH_MINIMUM_CLASS")
            .name("STRENGTH_MINIMUM")
            .type(TypeValueEnum.INTEGER)
            .builtIn(Boolean.TRUE)
            .build()),
    DEXTERITY_MINIMUM_CLASS(CharacteristicModel.builder()
            .code("DEXTERITY_MINIMUM_CLASS")
            .name("DEXTERITY_MINIMUM")
            .type(TypeValueEnum.INTEGER)
            .builtIn(Boolean.TRUE)
            .build()),
    CONSTITUTION_MINIMUM_CLASS(CharacteristicModel.builder()
            .code("CONSTITUTION_MINIMUM_CLASS")
            .name("CONSTITUTION_MINIMUM")
            .type(TypeValueEnum.INTEGER)
            .builtIn(Boolean.TRUE)
            .build()),
    INTELLIGENCE_MINIMUM_CLASS(CharacteristicModel.builder()
            .code("INTELLIGENCE_MINIMUM_CLASS")
            .name("INTELLIGENCE_MINIMUM")
            .type(TypeValueEnum.INTEGER)
            .builtIn(Boolean.TRUE)
            .build()),
    WISDOM_MINIMUM_CLASS(CharacteristicModel.builder()
            .code("WISDOM_MINIMUM_CLASS")
            .name("WISDOM_MINIMUM")
            .type(TypeValueEnum.INTEGER)
            .builtIn(Boolean.TRUE)
            .build()),
    CHARISMA_MINIMUM_CLASS(CharacteristicModel.builder()
            .code("CHARISMA_MINIMUM_CLASS")
            .name("CHARISMA_MINIMUM")
            .type(TypeValueEnum.INTEGER)
            .builtIn(Boolean.TRUE)
            .build());

    private final CharacteristicModel characteristicMeta;

    BuiltInPlayerCharacteristicsEnum(CharacteristicModel characteristicMeta) {
        this.characteristicMeta = characteristicMeta;
    }

    public CharacteristicModel getCharacteristicMeta() {
        return characteristicMeta;
    }

}
