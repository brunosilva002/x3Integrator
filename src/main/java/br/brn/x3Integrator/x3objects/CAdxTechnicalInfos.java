package br.brn.x3Integrator.x3objects;

import jakarta.xml.bind.annotation.*;
import lombok.Getter;
import lombok.Setter;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CAdxTechnicalInfos", propOrder = {
        "busy",
        "changeLanguage",
        "changeUserId",
        "flushAdx",
        "loadWebsDuration",
        "nbDistributionCycle",
        "poolDistribDuration",
        "poolEntryIdx",
        "poolExecDuration",
        "poolRequestDuration",
        "poolWaitDuration",
        "processReport",
        "processReportSize",
        "reloadWebs",
        "resumitAfterDBOpen",
        "rowInDistribStack",
        "totalDuration",
        "traceRequest",
        "traceRequestSize"
})
@XmlRootElement(name = "CAdxTechnicalInfos")
@Getter
@Setter
public class CAdxTechnicalInfos {

    @XmlElement
    protected boolean busy;
    @XmlElement
    protected boolean changeLanguage;
    @XmlElement
    protected boolean changeUserId;
    @XmlElement
    protected boolean flushAdx;
    @XmlElement
    protected double loadWebsDuration;
    @XmlElement
    protected int nbDistributionCycle;
    @XmlElement
    protected double poolDistribDuration;
    @XmlElement
    protected int poolEntryIdx;
    @XmlElement
    protected double poolExecDuration;
    @XmlElement
    protected Double poolRequestDuration;  // Changed to Double for nullable type
    @XmlElement
    protected double poolWaitDuration;
    @XmlElement
    protected String processReport;
    @XmlElement
    protected int processReportSize;
    @XmlElement
    protected boolean reloadWebs;
    @XmlElement
    protected boolean resumitAfterDBOpen;
    @XmlElement
    protected Integer rowInDistribStack;  // Changed to Integer for nullable type
    @XmlElement
    protected double totalDuration;
    @XmlElement
    protected String traceRequest;
    @XmlElement
    protected int traceRequestSize;

    // Getters and setters omitted for brevity
}