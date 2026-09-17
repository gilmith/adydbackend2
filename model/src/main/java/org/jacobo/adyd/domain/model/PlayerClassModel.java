package org.jacobo.adyd.domain.model;


import lombok.*;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@Data
@NoArgsConstructor
@SuperBuilder
public class PlayerClassModel extends BaseModel{
    private Long id;
    private String name;
    private String hitDice;
}
