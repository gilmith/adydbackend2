package org.jacobo.adyd.infraestructure.entities;


import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.jacobo.adyd.domain.model.SymbolTypeEnum;

import java.util.Set;

@EqualsAndHashCode(callSuper = true, exclude = "characteristic")
@ToString(callSuper = true, exclude = "characteristic")
@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Entity
@Table(name = "race_characteristics")
public class RaceCharacteristicsEntity extends CommonEntity{

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_race")
    private RaceEntity race;
    @Column(name = "id_pattern")
    private Long idPattern;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_characteristic")
    private CharacteristicsEntity characteristic;
    @Column(name = "value")
    private String value;
    @Column(name = "symbol")
    @Convert(converter = SymbolTypeEnumConverter.class)
    private SymbolTypeEnum symbol;

}
