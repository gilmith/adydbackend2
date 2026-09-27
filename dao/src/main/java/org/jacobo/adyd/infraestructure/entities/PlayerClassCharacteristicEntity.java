package org.jacobo.adyd.infraestructure.entities;


import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.jacobo.adyd.domain.model.SymbolTypeEnum;

@EqualsAndHashCode(callSuper = true, exclude = "characteristic")
@ToString(callSuper = true, exclude = "characteristic")
@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Entity
@Table(name = "player_class_characteristics")
public class PlayerClassCharacteristicEntity extends CommonEntity{


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_player_class")
    private PlayerClassEntity playerClass;
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
