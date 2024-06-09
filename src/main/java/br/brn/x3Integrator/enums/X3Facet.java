package br.brn.x3Integrator.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum X3Facet {
    QUERY("query"),
    DATAILS("details");
    private String cod;
}
