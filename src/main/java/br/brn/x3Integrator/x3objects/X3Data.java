package br.brn.x3Integrator.x3objects;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ConfigurationProperties("api")
public class X3Data {

    public String restUrl;
    public Long restCount;

    public String soapUrl;
    public String soapPoolAlias;
    public String soapLan;
    public String soapRequestConfig;
    public String soapAction;
    public String userName;
    public String password;

}
