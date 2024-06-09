package br.brn.x3Integrator.x3objects;

import jakarta.xml.bind.annotation.*;
import lombok.Getter;
import lombok.Setter;

@XmlRootElement(name = "multiRef")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "multiRef", propOrder = {
        "type",
        "message"
})
@Getter
@Setter
public class X3MultiRef {

    @XmlElement
    private Long type;
    @XmlElement
    private String message;
    // Getters and setters omitted for brevity
}