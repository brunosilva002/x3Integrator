package br.brn.x3Integrator.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum X3PublicName {
    CUSTOMER("BPCUSTOMER"),
    PRODUCT("YITM"),
    SALESORDER("SORDER"),
    SALESQUOTE("YSQH"),
    ;
    private String cod;
}
