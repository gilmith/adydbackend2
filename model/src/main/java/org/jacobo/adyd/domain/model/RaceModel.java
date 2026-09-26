package org.jacobo.adyd.domain.model;

import lombok.*;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@SuperBuilder
@AllArgsConstructor
public class RaceModel extends BaseModel{

    private Long id;
    private String name;


}
