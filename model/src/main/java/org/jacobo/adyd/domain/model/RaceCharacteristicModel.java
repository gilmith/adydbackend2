package org.jacobo.adyd.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
@SuperBuilder
public class RaceCharacteristicModel extends BaseModel {

    private Long id;
    private RaceModel race;
    private CharacteristicModel characteristic;
    private List<CharacteristicModel> characteristicsList;

}
