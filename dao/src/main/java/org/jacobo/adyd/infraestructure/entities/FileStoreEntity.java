package org.jacobo.adyd.infraestructure.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "file_store")
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@EqualsAndHashCode(callSuper = true, exclude = {"campaignEntity"})
@Data
public class FileStoreEntity extends CommonEntity{

    @Column(name = "url", nullable = false)
    private String url;

    @Column(name = "file_name", nullable = false)
    private String fileName;

    @OneToOne(mappedBy = "fileStoreEntity")
    private CampaignEntity campaignEntity;


}
