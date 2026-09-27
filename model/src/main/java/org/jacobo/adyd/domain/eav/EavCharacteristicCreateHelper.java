package org.jacobo.adyd.domain.eav;

import org.jacobo.adyd.domain.model.CharacteristicModel;

import java.util.Optional;

public abstract class EavCharacteristicCreateHelper<ID, OWNER, REL> extends AbstractEavCharacteristicService<ID, OWNER, REL> {

    public final CharacteristicModel createNewProperty(ID ownerId, CharacteristicModel model, String value) {
        OWNER owner = loadOwner(ownerId);
        Optional<CharacteristicModel> current = findCurrentCharacteristic(ownerId, model.getCode());
        if (matchesBuiltIn(model)) {
            publishBuiltIn(model, value, owner, current);
        } else {
            publishCustom(model, value, owner, current);
        }
        return model;
    }
}