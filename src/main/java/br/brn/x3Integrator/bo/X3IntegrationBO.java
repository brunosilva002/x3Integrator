package br.brn.x3Integrator.bo;

import br.brn.x3Integrator.dto.SalesQuoteDTO;
import br.brn.x3Integrator.dto.SalesQuoteProductDTO;
import br.brn.x3Integrator.enums.*;
import br.brn.x3Integrator.exception.ExceptionMessage;
import br.brn.x3Integrator.model.masterDataBase.SalesQuote;
import br.brn.x3Integrator.model.masterDataBase.SalesQuoteX3Log;
import br.brn.x3Integrator.repository.masterDataBase.SalesQuoteRepository;
import br.brn.x3Integrator.repository.masterDataBase.SalesQuoteX3LogRepository;
import br.brn.x3Integrator.x3objects.X3Data;
import br.brn.x3Integrator.exception.X3IntegrationException;
import br.brn.x3Integrator.x3objects.X3Envelope;
import br.brn.x3Integrator.x3objects.X3XmlParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.apache.hc.client5.http.cookie.BasicCookieStore;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
public class X3IntegrationBO {

    private final RestTemplate restTemplate;
    private final BasicCookieStore cookieStore = new BasicCookieStore();

    public X3IntegrationBO(SalesQuoteX3LogRepository salesQuoteX3LogRepository) {
        CloseableHttpClient httpClient = HttpClients.custom()
                .setDefaultCookieStore(cookieStore)
                .build();
        restTemplate = new RestTemplate(new HttpComponentsClientHttpRequestFactory(httpClient));
        this.salesQuoteX3LogRepository = salesQuoteX3LogRepository;
    }

    @Autowired
    private X3Data x3Data;

    @Autowired
    private X3XmlParser x3XmlParser;
    @Autowired
    private final SalesQuoteX3LogRepository salesQuoteX3LogRepository;

    @Autowired
    SalesQuoteRepository salesQuoteRepository;
    @Autowired
    UtilBO utilBO;

    @Autowired
    ExceptionMessage exceptionMessage;

    public <T> T consumeX3Rest(String x3Id, X3Representation x3Representation, X3Class x3Class, X3Facet x3Facet, HttpMethod httpMethod, Class<T> responseType) {

        //https://desenvottech.erpx3.com.br/sdata/x3/erp/HOM/SORDER('EST012208SON00000001')?representation=SORDER.$details
        StringBuilder url = new StringBuilder()
                .append(x3Data.restUrl)
                .append(x3Representation.getCod())
                .append("('").append(x3Id).append("')?")
                .append("representation=")
                .append(x3Class.getCod())
                .append(".$")
                .append(x3Facet.getCod())
                ;

        return getT(httpMethod, responseType, url.toString());
    }

    private <T> T getT(HttpMethod httpMethod, Class<T> responseType, String url) {
        // Configuração do cabeçalho para a autenticação básica
        HttpHeaders headers = new HttpHeaders();
        headers.setBasicAuth(x3Data.getUserName(), x3Data.getPassword());

        // Configuração da requisição
        HttpEntity<String> entity = new HttpEntity<>(headers);

        StringBuilder urlfull = new StringBuilder()
                .append(url)
                .append("&count=").append(x3Data.getRestCount());

        // Chamada da API
        ResponseEntity<String> response = restTemplate.exchange(urlfull.toString(), httpMethod, entity, String.class);

        // Processamento da resposta
        if (response.getStatusCode().is2xxSuccessful()) {
            try {
                ObjectMapper objectMapper = new ObjectMapper();
                T apiResponse = objectMapper.readValue(response.getBody(), responseType);

                return apiResponse;

            } catch (Exception e) {
                throw new X3IntegrationException(exceptionMessage.getMessage("erpIntegration.convertApiResponse",e.getMessage()));
            }
        } else {
            throw new X3IntegrationException(exceptionMessage.getMessage("erpIntegration.convertApiResponse",response.getStatusCode()));
        }
    }

    public <T> T consumeX3RestGoToNext(String url, HttpMethod httpMethod, Class<T> responseType){
        return getT(httpMethod, responseType, url);
    }


    public ResponseEntity<String>  consumeX3Soap (String publicName, String requestBody){
        RestTemplate restTemplate = new RestTemplate();

        // Prepare the request headers
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/soap+xml");
        headers.setBasicAuth(x3Data.getUserName(), x3Data.getPassword());
        headers.set("SOAPAction", x3Data.getSoapAction());

        // Create the HTTP entity
        HttpEntity<String> requestEntity = new HttpEntity<>(requestBody, headers);

        // Send the SOAP request and receive the response
        ResponseEntity<String> responseEntity = restTemplate.exchange(x3Data.getSoapUrl(), HttpMethod.POST, requestEntity, String.class);

        return responseEntity;
    }

    public String createBodySoapX3 (String publicName, String json){

        // Prepare the request body
        String requestBody = "<soapenv:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:wss=\"http://www.adonix.com/WSS\">\n" +
                "   <soapenv:Header/>\n" +
                "   <soapenv:Body>\n" +
                "      <wss:save soapenv:encodingStyle=\"http://schemas.xmlsoap.org/soap/encoding/\">\n" +
                "         <callContext xsi:type=\"wss:CAdxCallContext\">\n" +
                "            <codeLang xsi:type=\"xsd:string\">"+x3Data.getSoapLan()+"</codeLang>\n" +
                "            <poolAlias xsi:type=\"xsd:string\">"+x3Data.getSoapPoolAlias()+"</poolAlias>\n" +
                "            <poolId xsi:type=\"xsd:string\"></poolId>\n" +
                "            <requestConfig xsi:type=\"xsd:string\">"+x3Data.getSoapRequestConfig()+"</requestConfig>\n" +
                "         </callContext>\n" +
                "         <publicName xsi:type=\"xsd:string\">"+publicName+"</publicName>\n" +
                "         <objectXml xsi:type=\"xsd:string\">\n" +
                "         <![CDATA[" +json+"]]>\n" +
                "         </objectXml>\n" +
                "      </wss:save>\n" +
                "   </soapenv:Body>\n" +
                "</soapenv:Envelope>";

        return requestBody;
    }

    public String salesQuoteToX3 (SalesQuoteDTO salesQuote){
        ObjectMapper objectMapper = new ObjectMapper();
        ObjectNode jsonNode = objectMapper.createObjectNode();

        jsonNode.put("SALFCY",salesQuote.getSite().getCdnX3Site());
        jsonNode.put("STOFCY",salesQuote.getSite().getCdnX3Site());
        jsonNode.put("QUODAT",salesQuote.getQuoteDate().toString());
        jsonNode.put("BPCORD", salesQuote.getCustomer().getCdnX3Customer());
        jsonNode.put("XQCODOPF",101);

        // Adicionar a lista de produtos
        ArrayNode productsArrayNode = objectMapper.createArrayNode();
        for (SalesQuoteProductDTO product : salesQuote.getSalesQuoteProducts()) {
            ObjectNode productNode = objectMapper.createObjectNode();
            productNode.put("ITMREF", product.getProduct().getCdnX3Product());
            productNode.put("QTY", product.getQty());
            productNode.put("GROPRI", product.getNetPrice());
            productsArrayNode.add(productNode);
        }
        jsonNode.set("SQH2_1", productsArrayNode);

        String jsonString = jsonNode.toString();
        System.out.println(jsonString);

        return jsonNode.toString();
    }

    public X3Envelope sendSalesQuoteX3(SalesQuote salesQuote){
        X3Envelope x3Envelope = null;
        SalesQuoteDTO salesQuoteDTO = new SalesQuoteDTO();
        SalesQuoteX3Log salesQuoteX3Log = new SalesQuoteX3Log();
        try {
            utilBO.entityToDtoCustom(salesQuote, salesQuoteDTO, new ArrayList<>());

            String jsonBody = salesQuoteToX3(salesQuoteDTO);
            String xmlBody = createBodySoapX3(X3PublicName.SALESQUOTE.getCod(),jsonBody );

            salesQuoteX3Log.setSalesQuote(salesQuote);
            salesQuoteX3Log.setX3SendBody(xmlBody);
            salesQuoteX3LogRepository.save(salesQuoteX3Log);

            ResponseEntity<String> resX3 = consumeX3Soap(X3PublicName.SALESQUOTE.getCod(),xmlBody);

            salesQuoteX3Log.setX3ResponseBody(resX3.getBody());
            salesQuoteX3Log.setX3ResponseHeaders(resX3.getHeaders().toString());
            salesQuoteX3Log.setX3ResposneStatusWs(resX3.getStatusCode().toString());
            salesQuoteX3LogRepository.save(salesQuoteX3Log);

            x3Envelope = x3XmlParser.conveter(resX3.getBody());
            if (Objects.nonNull(x3Envelope.getBody().getMultiRef()) &&
                    x3Envelope.getBody().getMultiRef().stream()
                            .anyMatch(t->t.getType().equals(3L) || t.getType().equals(4L)))
            {
                String msg = "Erro x3: " + x3Envelope.getBody().getMultiRef().stream()
                        .map(t->t.getMessage())
                        .collect(Collectors.joining(";"));
                salesQuote.setC3IntegrationMessage(msg);
                salesQuote.setX3IntegrationStatus(X3StatusIntegration.ERROR_X3.getCod());
                salesQuoteRepository.save(salesQuote);

                salesQuoteX3Log.setX3ErrorMessages(msg);
                salesQuoteX3Log.setX3ResposneStatusWs(resX3.getStatusCode().toString());
                salesQuoteX3LogRepository.save(salesQuoteX3Log);

                throw new X3IntegrationException(msg);
            }
        } catch (Exception e) {
            salesQuote.setC3IntegrationMessage(exceptionMessage.getMessage("erpIntegration.convertApiResponse"));
            salesQuote.setX3IntegrationStatus(X3StatusIntegration.ERROR_INTEGRACAO.getCod());
            salesQuoteRepository.save(salesQuote);

            salesQuoteX3Log.setX3ErrorMessages(e.getMessage());
            salesQuoteX3Log.setX3ResposneStatusWs(X3StatusIntegration.ERROR_INTEGRACAO.getCod());
            salesQuoteX3LogRepository.save(salesQuoteX3Log);

            throw new X3IntegrationException(e);
        }

        return x3Envelope;
    }

    public SalesQuote updateSalesQuoteFromX3(SalesQuote salesQuote, X3Envelope x3Envelope ){

        if (Objects.nonNull(x3Envelope.getBody().getSaveResponse().getSaveReturn().getResultXml())) {
            try {
                ObjectMapper objectMapper = new ObjectMapper();
                SalesQuoteDTO apiResponse = objectMapper.readValue(x3Envelope.getBody().getSaveResponse().getSaveReturn().getResultXml(), SalesQuoteDTO.class);

                salesQuote.setC3IntegrationMessage(exceptionMessage.getMessage("erpIntegration.successfully"));
                salesQuote.setX3IntegrationStatus(X3StatusIntegration.SUCCESS.getCod());
                salesQuote.setCdnX3SalesQuote(apiResponse.getCdnX3SalesQuote());

                return salesQuoteRepository.save(salesQuote);

            } catch (X3IntegrationException | JsonProcessingException e) {
                String msg = exceptionMessage.getMessage("erpIntegration.convertApiResponse",e.getMessage());
                salesQuote.setC3IntegrationMessage(msg);
                salesQuote.setX3IntegrationStatus(X3StatusIntegration.ERROR_INTEGRACAO.getCod());
                salesQuoteRepository.save(salesQuote);
                throw new X3IntegrationException(msg);
            }
        }
        return salesQuote;
    }
}
