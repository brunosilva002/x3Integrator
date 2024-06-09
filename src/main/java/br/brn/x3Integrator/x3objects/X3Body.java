package br.brn.x3Integrator.x3objects;

import jakarta.xml.bind.annotation.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@XmlRootElement(name = "Body")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Body", propOrder = {
        "saveResponse",
        "multiRef"
})
@Getter
@Setter
public class X3Body {

    @XmlElement(name = "saveResponse", namespace = "http://www.adonix.com/WSS")
    private X3SaveResponse saveResponse;

    @XmlElement(name = "multiRef")
    private List<X3MultiRef> multiRef;

    // Getters and setters omitted for brevity
}