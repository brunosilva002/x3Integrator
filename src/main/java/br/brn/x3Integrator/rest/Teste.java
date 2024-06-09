package br.brn.x3Integrator.rest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/v1/teste")
public class Teste {
//
//    @GetMapping
//    public ResponseEntity obter() {
//        consumeApi();
//        return null;
//    }
//
//    @GetMapping(("/cirarSOH"))
//    public ResponseEntity criarSOH() throws Exception {
//        criarSOH1();
//        return null;
//    }
//    @Autowired
//    private X3Data x3Data;
//
//    @Autowired
//    private X3XmlParser x3XmlParser;
//
//    private static final String SOAP_ENDPOINT_URL = "http://desenvottech.erpx3.com.br/soap-generic/syracuse/collaboration/syracuse/CAdxWebServiceXmlCC";
//    private static final String USERNAME = "your_username";
//    private static final String PASSWORD = "your_password";
//    private static final String SOAP_ACTION = "http://example.com/soap-service/";
//    private static ClientHttpRequestInterceptor createBasicAuthInterceptor(String username, String password) {
//        return new BasicAuthenticationInterceptor(username, password);
//    }
//
//    public void criarSOH1() throws Exception {
//        // Create a RestTemplate
//        RestTemplate restTemplate = new RestTemplate();
//
//        // Prepare the request headers
//        HttpHeaders headers = new HttpHeaders();
//        headers.set("Content-Type", "application/soap+xml");
//        headers.setBasicAuth(x3Data.getUserName(), x3Data.getPassword());
//        headers.set("SOAPAction", SOAP_ACTION);
//
//        // Prepare the request body
//        String requestBody = "<soapenv:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:wss=\"http://www.adonix.com/WSS\">\n" +
//                "   <soapenv:Header/>\n" +
//                "   <soapenv:Body>\n" +
//                "      <wss:save soapenv:encodingStyle=\"http://schemas.xmlsoap.org/soap/encoding/\">\n" +
//                "         <callContext xsi:type=\"wss:CAdxCallContext\">\n" +
//                "            <codeLang xsi:type=\"xsd:string\">POR</codeLang>\n" +
//                "            <poolAlias xsi:type=\"xsd:string\">HOM</poolAlias>\n" +
//                "            <poolId xsi:type=\"xsd:string\"></poolId>\n" +
//                "            <requestConfig xsi:type=\"xsd:string\"></requestConfig>\n" +
//                "         </callContext>\n" +
//                "         <publicName xsi:type=\"xsd:string\">YITM</publicName>\n" +
//                "         <objectXml xsi:type=\"xsd:string\">\n" +
//                "         <![CDATA[\n" +
//                "\t\t{\n" +
//                "\t\t\t\"TCLCOD\":\"MP\",\n" +
//                "\t\t\t\"ITMREF\":\"TESTE_002\",\n" +
//                "\t\t\t\"DES1AXX\":\"TESTE_002\"\n" +
//                "\t\t}\n" +
//                "         ]]>\n" +
//                "         </objectXml>\n" +
//                "      </wss:save>\n" +
//                "   </soapenv:Body>\n" +
//                "</soapenv:Envelope>";
//
//        // Create the HTTP entity
//        HttpEntity<String> requestEntity = new HttpEntity<>(requestBody, headers);
//
//        // Send the SOAP request and receive the response
//        ResponseEntity<String> responseEntity = restTemplate.exchange(SOAP_ENDPOINT_URL, HttpMethod.POST, requestEntity, String.class);
//        x3XmlParser.conveter(responseEntity.getBody());
//        // Process the SOAP response
//        System.out.println("SOAP Response:");
//        System.out.println(responseEntity.getBody());
//    }
//
//    public void consumeApi() {
//        // Configuração básica da autenticação
//
//        RestTemplate restTemplate = new RestTemplate();
//        X3Data x3Data1 = new X3Data();
//        String password = "suaSenha";
//        StringBuilder url = new StringBuilder().append(x3Data.restUrl).append("SORDER?representation=SORDER.$query");
//
//        // Configuração do cabeçalho para a autenticação básica
//        HttpHeaders headers = new HttpHeaders();
//        headers.setBasicAuth(x3Data.userName, x3Data.password);
//
//        // Configuração da requisição
//        HttpEntity<String> entity = new HttpEntity<>(headers);
//
//        // Chamada da API
//        ResponseEntity<String> response = restTemplate.exchange(url.toString(), HttpMethod.GET, entity, String.class);
//
//        // Processamento da resposta
//        if (response.getStatusCode().is2xxSuccessful()) {
//            try {
//                ObjectMapper objectMapper = new ObjectMapper();
//                X3SalesOrderList apiResponse = objectMapper.readValue(response.getBody(), X3SalesOrderList.class);
//
//                System.out.println(apiResponse);
//
//            } catch (Exception e) {
//                System.out.println("Erro ao converter a resposta da API em objeto: " + e.getMessage());
//            }
//        } else {
//            System.out.println("Erro ao chamar a API. Código de status: " + response.getStatusCodeValue());
//        }
//    }

}
