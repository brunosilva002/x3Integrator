package br.brn.x3Integrator.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class SalesQuoteX3LogDTO implements Serializable {

	private Long cdnSalesOrderlogIntegration;
	private SalesQuoteDTO salesOrder;
	private String x3SendBody;
	private String x3ResponseBody;
	private String x3ResposneStatusWs;
	private String x3ResponseHeaders;
	private String x3ErrorMessages;
	private LocalDateTime creationDate;
	private String creationUser;
	private LocalDateTime updateDate;
	private String updateUser;

}