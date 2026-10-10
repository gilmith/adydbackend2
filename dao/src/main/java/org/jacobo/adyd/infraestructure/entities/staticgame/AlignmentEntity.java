package org.jacobo.adyd.infraestructure.entities.staticgame;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
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
@Entity
@Table(name = "alignment")
public class AlignmentEntity extends StaticTableEntity {

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;
}
