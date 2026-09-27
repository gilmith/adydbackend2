package org.jacobo.adyd.domain.eav;

import org.jacobo.adyd.domain.model.CharacteristicModel;

import java.util.List;

public abstract class EavCharacteristicReadHelper<ID, OWNER, REL> extends AbstractEavCharacteristicService<ID, OWNER, REL> {

    public final List<CharacteristicModel> getCharacteristics(ID ownerId) {
        return extractCharacteristics(findRelationByOwnerId(ownerId));
    }
}