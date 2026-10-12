package org.jacobo.adyd.infraestructure.entities.staticgame;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
@Table(name = "alignment")
public class AlignmentEntity extends StaticTableEntity {

    @Column(name = "name", insertable = false, updatable = false)
    private String name;

    @Column(name = "description", insertable = false, updatable = false)
    private String description;
}
