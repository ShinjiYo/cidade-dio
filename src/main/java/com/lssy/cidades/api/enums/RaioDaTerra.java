package com.lssy.cidades.api.enums;

import lombok.Getter;

@Getter
public enum RaioDaTerra {
    METERS("m", 6378168),
    KILOMETERS("km", 6378.168f),
    MILES("mi", 3958.747716f);

    private final String unidade;
    private final float valor;

    RaioDaTerra(final String unidade, final float valor) {
        this.unidade = unidade;
        this.valor = valor;
    }
}
