package br.brn.x3Integrator.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum X3StatusIntegration {
    PENDING("PENDING"),
    ERROR_INTEGRACAO("INTEGRATION ERROR"),
    ERROR_X3("X3 ERROR"),
    SUCCESS("SUCCESS"),
    ;
    private String cod;
}
