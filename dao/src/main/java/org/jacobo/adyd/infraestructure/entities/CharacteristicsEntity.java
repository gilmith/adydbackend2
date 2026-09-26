package org.jacobo.adyd.infraestructure.entities;


import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.jacobo.adyd.domain.model.TypeValueEnum;

import java.util.Set;

@EqualsAndHashCode(callSuper = true, exclude = "raceCharacteristicsEntity")
@ToString(callSuper = true, exclude = "raceCharacteristicsEntity")
@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Entity
@Table(name = "characteristics")
public class CharacteristicsEntity extends CommonEntity{
    @Column(name = "code")
    private String code;
    @Column(name = "name")
    private String name;
    @Column(name = "description")
    private String description;
    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    private TypeValueEnum type;
    @Column(name = "built_in")
    private Boolean builtIn;
    @OneToOne(mappedBy = "characteristic")
    private RaceCharacteristicsEntity raceCharacteristicsEntity;
    @Column(name = "short_description", nullable = false)
    private String shortDescription;
}
