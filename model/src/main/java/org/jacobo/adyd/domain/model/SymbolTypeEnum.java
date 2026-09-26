package org.jacobo.adyd.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum SymbolTypeEnum {

    BONUS("+"),
    MALUS("-"),
    NONE("");

    private final String symbol;

    public static SymbolTypeEnum fromSymbol(String s) {
        for (SymbolTypeEnum e : values()) {
            if (e.symbol.equals(s)) return e;
        }
        throw new IllegalArgumentException("Unknown symbol: " + s);
    }
}
