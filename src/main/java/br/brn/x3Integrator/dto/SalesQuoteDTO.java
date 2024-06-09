package br.brn.x3Integrator.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class SalesQuoteDTO implements Serializable {

	@JsonAlias({"cdnSalesQuote"})
	private Long cdnSalesQuote;

	@JsonAlias({"SQHNUM","cdnX3SalesQuote"})
	private String cdnX3SalesQuote;
	private SiteDTO site;
 	private CustomerDTO customer;
	private LocalDate quoteDate;
	private List<SalesQuoteProductDTO> salesQuoteProducts = new ArrayList<>();
	private List<SalesQuoteX3LogDTO> logIntegration = new ArrayList<>();

	private String x3IntegrationStatus;
	private String c3IntegrationMessage;

	private LocalDateTime creationDate;
	private String creationUser;
	private LocalDateTime updateDate;
	private String updateUser;

	@JsonProperty("SQH0_1")
	private void unpackSQH1(Map<String, Object> SQH1) {
		this.cdnX3SalesQuote = (String) SQH1.get("SQHNUM");
	}

	// Método personalizado para desserializar o campo cliente
//	@JsonSetter("BPCORD")
//	public void setClienteFromBpcord(Long bpcord) {
//		this.customer = new CustomerDTO(bpcord);
//	}
//
//	@JsonSetter("BPCORD")
//	public void setSiteFromSALFCY(String bpcord) {
//		this.customer = new CustomerDTO(bpcord);
//	}

}