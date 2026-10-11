package org.jacobo.adyd.infraestructure.entities.staticgame;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;


@MappedSuperclass
public class StaticTableBasePointEntity extends StaticTableEntity {

    @Column(name = "base_point", insertable = false, updatable = false)
    private String basePoint;

}
