package br.brn.x3Integrator.x3objects;

import jakarta.xml.bind.annotation.*;
import lombok.Getter;
import lombok.Setter;

@XmlRootElement(name = "saveResponse")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "saveResponse", propOrder = {
        "saveReturn"
})
@Getter
@Setter
public class X3SaveResponse {

    @XmlElement(name = "saveReturn")
    private CAdxResultXml saveReturn;

    // Getters and setters omitted for brevity
}