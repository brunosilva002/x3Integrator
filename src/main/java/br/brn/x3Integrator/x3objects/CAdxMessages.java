package br.brn.x3Integrator.x3objects;

import jakarta.xml.bind.annotation.*;
import lombok.Getter;
import lombok.Setter;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "messages", propOrder = {
        "messages"
})
@XmlRootElement(name = "CAdxMessages")
@Getter
@Setter
public class CAdxMessages {

    @XmlElement
    protected CAdxMessage messages; // Changed to singular form based on XML

    @XmlAttribute(name = "href")
    protected String href;

    @XmlAttribute(name = "soapenc:arrayType")
    protected String soapencArrayType;

    // Getters and setters omitted for brevity
}