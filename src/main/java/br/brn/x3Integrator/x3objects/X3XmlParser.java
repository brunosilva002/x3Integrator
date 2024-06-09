package br.brn.x3Integrator.x3objects;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Unmarshaller;
import org.springframework.stereotype.Component;
import org.springframework.xml.transform.StringSource;

@Component
public class X3XmlParser {

    public static X3Envelope parseXml(String xmlString) throws JAXBException {
        JAXBContext jaxbContext = JAXBContext.newInstance(X3Envelope.class);
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
        return (X3Envelope) unmarshaller.unmarshal(new StringSource(xmlString));
    }

    public X3Envelope conveter (String xmlString) throws Exception {
        X3Envelope envelope = parseXml(xmlString);
        return envelope;
    }
}
