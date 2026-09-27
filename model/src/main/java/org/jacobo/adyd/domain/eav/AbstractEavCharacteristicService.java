package org.jacobo.adyd.domain.eav;

import org.jacobo.adyd.domain.exception.ConflictRunTimeException;
import org.jacobo.adyd.domain.exception.NotFoundRunTimeException;
import org.jacobo.adyd.domain.model.CharacteristicModel;
import org.jacobo.adyd.domain.model.SymbolTypeEnum;

import java.util.List;
import java.util.Optional;

public abstract class AbstractEavCharacteristicService<ID, OWNER, REL> {

    protected abstract OWNER loadOwner(ID ownerId);

    protected abstract Optional<CharacteristicModel> findCurrentCharacteristic(ID ownerId, String code);

    protected abstract List<CharacteristicModel> builtInMetas();

    protected abstract CharacteristicModel persistStandard(CharacteristicModel model);

    protected abstract CharacteristicModel persistCustom(CharacteristicModel model, OWNER owner);

    protected abstract REL buildRelation(OWNER owner, CharacteristicModel characteristic);

    protected abstract REL saveRelation(REL relation);

    protected abstract REL findRelationByOwnerId(ID ownerId);

    protected abstract List<CharacteristicModel> extractCharacteristics(REL relation);

    protected String conflictSubject() {
        return "Characteristic";
    }

    protected boolean matchesBuiltIn(CharacteristicModel model) {
        return builtInMetas().stream().anyMatch(meta ->
                model.getCode().equals(meta.getCode()) || model.getCode().equals(meta.getName()));
    }

    protected Optional<CharacteristicModel> resolveBuiltInMeta(CharacteristicModel model) {
        return builtInMetas().stream()
                .filter(meta -> model.getCode().equals(meta.getCode()) || model.getCode().equals(meta.getName()))
                .findFirst();
    }

    protected void checkCurrentConflict(Optional<CharacteristicModel> current, String code) {
        current.ifPresent(characteristic -> {
            throw new ConflictRunTimeException(conflictSubject() + " already exists");
        });
    }

    protected void applyValueAndSymbol(CharacteristicModel model, String value) {
        model.setValue(value);
        model.setSymbol(resolveSymbol(value));
    }

    protected SymbolTypeEnum resolveSymbol(String value) {
        try {
            return Integer.parseInt(value) > 0 ? SymbolTypeEnum.BONUS : SymbolTypeEnum.MALUS;
        } catch (NumberFormatException e) {
            return SymbolTypeEnum.NONE;
        }
    }

    protected void publishBuiltIn(CharacteristicModel model, String value, OWNER owner, Optional<CharacteristicModel> current) {
        checkCurrentConflict(current, model.getCode());
        CharacteristicModel meta = resolveBuiltInMeta(model)
                .orElseThrow(() -> new NotFoundRunTimeException(
                        "Built in " + conflictSubject().toLowerCase() + " not found: " + model.getCode()));
        meta.setDescription(model.getDescription());
        meta.setShortDescription(model.getShortDescription());
        CharacteristicModel persisted = persistStandard(meta);
        applyValueAndSymbol(persisted, value);
        saveRelation(buildRelation(owner, persisted));
    }

    protected void publishCustom(CharacteristicModel model, String value, OWNER owner, Optional<CharacteristicModel> current) {
        checkCurrentConflict(current, model.getCode());
        CharacteristicModel persisted = persistCustom(model, owner);
        applyValueAndSymbol(persisted, value);
        model.setBuiltIn(false);
        saveRelation(buildRelation(owner, persisted));
    }
}