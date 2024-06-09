package br.brn.x3Integrator.x3objects;

import jakarta.xml.bind.annotation.*;
import lombok.Getter;
import lombok.Setter;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CAdxMessage", propOrder = {
        "type",
        "message"
})
@XmlRootElement(name = "CAdxMessage")
@Getter
@Setter
public class CAdxMessage {

    @XmlElement(required = true)
    protected int type;
    @XmlElement(required = true)
    protected String message;

    // Getters and setters omitted for brevity
}