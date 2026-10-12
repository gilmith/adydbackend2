package org.jacobo.adyd.infraestructure.entities.staticgame;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@MappedSuperclass
@AllArgsConstructor
@NoArgsConstructor
@Data
public abstract class StaticTableEntity {

    @Id
    private Long id;
}
