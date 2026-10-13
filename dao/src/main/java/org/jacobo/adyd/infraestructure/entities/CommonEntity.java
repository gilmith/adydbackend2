package org.jacobo.adyd.infraestructure.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.jacobo.adyd.authetication.infrastructure.security.AuthenticationUtils;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@MappedSuperclass
@AllArgsConstructor
@NoArgsConstructor
@Data
@SuperBuilder
@EntityListeners(AuditingEntityListener.class)
public abstract class CommonEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(allocationSize = 1)
    private Long id;

    @CreatedDate
    @Column(name = "create_date")
    private LocalDateTime createDate;

    @LastModifiedDate
    @Column(name = "update_date")
    private LocalDateTime updateDate;

    @CreatedBy
    @Column(name = "create_user")
    private String createUser;

    @LastModifiedBy
    @Column(name = "update_user")
    private String updateUser;

    @PrePersist
    public void prePersist() {
        this.createUser = AuthenticationUtils.getCurrentUser();
        this.updateDate = null;
    }

    @PreUpdate
    public void preUpdate() {
        this.updateUser = AuthenticationUtils.getCurrentUser();
    }

}
