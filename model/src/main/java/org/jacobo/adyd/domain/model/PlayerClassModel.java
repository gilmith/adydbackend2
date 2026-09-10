package org.jacobo.adyd.domain.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class PlayerClassModel extends BaseModel{
    private String name;
    private String diceHit;
}
