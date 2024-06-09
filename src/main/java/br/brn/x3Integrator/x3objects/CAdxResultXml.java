package br.brn.x3Integrator.x3objects;

import jakarta.xml.bind.annotation.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@XmlRootElement(name = "saveReturn")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "saveReturn", propOrder = {
        "messages",
        "resultXml",
        "status",
        "technicalInfos"
})
@Getter
@Setter
public class CAdxResultXml {

    @XmlElementWrapper(name = "messages")
    @XmlElement(name = "messages")
    protected List<CAdxMessage> messages;
    @XmlElement
    protected String resultXml;
    @XmlElement
    protected int status;
    @XmlElement
    protected CAdxTechnicalInfos technicalInfos;

    // Getters and setters omitted for brevity
}