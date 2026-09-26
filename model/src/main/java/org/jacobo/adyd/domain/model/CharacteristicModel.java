package org.jacobo.adyd.domain.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
@SuperBuilder
public class CharacteristicModel extends BaseModel{

    private Long id;
    private String code;
    private String name;
    private String description;
    private TypeValueEnum type;
    private Boolean builtIn;
    private String value;
    private SymbolTypeEnum symbol;
    private String shortDescription;


}
