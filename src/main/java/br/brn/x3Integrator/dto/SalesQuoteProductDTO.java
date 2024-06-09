package br.brn.x3Integrator.dto;

import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
@Getter
@Setter
public class SalesQuoteProductDTO implements Serializable {
	private Long cdnSalesQuoteProduct;
	private Long lineNumber;
 	private SalesQuoteDTO salesOrder;
 	private ProductDTO product;
 	private Double qty;
 	private Double netPrice;
 	private Double netPriceToltal;
 	private LocalDate deliveryDate;

	private String creationUser;
	private String updateUser;
}