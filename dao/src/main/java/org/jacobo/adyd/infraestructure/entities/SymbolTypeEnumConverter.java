package org.jacobo.adyd.infraestructure.entities;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import org.jacobo.adyd.domain.model.SymbolTypeEnum;

@Converter(autoApply = true)
public class SymbolTypeEnumConverter implements AttributeConverter<SymbolTypeEnum, String> {

    @Override
    public String convertToDatabaseColumn(SymbolTypeEnum attribute) {
        return attribute == null ? null : attribute.getSymbol();
    }

    @Override
    public SymbolTypeEnum convertToEntityAttribute(String dbData) {
        return dbData == null ? null : SymbolTypeEnum.fromSymbol(dbData);
    }
}