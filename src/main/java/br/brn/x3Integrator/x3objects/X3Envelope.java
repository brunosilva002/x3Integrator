package br.brn.x3Integrator.x3objects;

import jakarta.xml.bind.annotation.*;
import lombok.Getter;
import lombok.Setter;

@XmlRootElement(name = "Envelope", namespace = "http://schemas.xmlsoap.org/soap/envelope/")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Envelope", propOrder = {
        "Body"
})
@Getter
@Setter
public class X3Envelope {

    @XmlElement(namespace = "http://schemas.xmlsoap.org/soap/envelope/")
    private X3Body Body;

    // Getters and setters omitted for brevity
}