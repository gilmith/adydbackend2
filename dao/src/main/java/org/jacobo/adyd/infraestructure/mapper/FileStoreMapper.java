package org.jacobo.adyd.infraestructure.mapper;


import org.jacobo.adyd.domain.model.FileStoreModel;
import org.jacobo.adyd.infraestructure.entities.FileStoreEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface FileStoreMapper {

    FileStoreEntity toEntity(FileStoreModel fileStore);

    FileStoreModel toModel(FileStoreEntity fileStore);

}
