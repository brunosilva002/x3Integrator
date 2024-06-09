package br.brn.x3Integrator.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum X3Representation {
    CUSTOMER("BPCUSTOMER"),
    PRODUCT("ITMMASTER"),
    SALESORDER("SORDER"),
    SITE("FCY"),
    ;
    private String cod;
}
