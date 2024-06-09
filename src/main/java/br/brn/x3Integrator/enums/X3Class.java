package br.brn.x3Integrator.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum X3Class {
    CUSTOMER("BPCUSTOMER"),
    PRODUCT("ITMMASTER"),
    SALESORDER("SORDER"),
    ;
    private String cod;
}
