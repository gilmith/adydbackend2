package org.jacobo.adyd.infraestructure.entities.staticgame;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@MappedSuperclass
@Data
public abstract class StaticTableBasePointEntity extends StaticTableEntity {

    @Column(name = "base_point", insertable = false, updatable = false)
    private String basePoint;

}
