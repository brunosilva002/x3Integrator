package br.brn.x3Integrator.dto;

import br.brn.x3Integrator.dto.CustomerDTO;
import br.brn.x3Integrator.dto.ProductDTO;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
@Getter
@Setter
public class ProductCustomerDTO implements Serializable {
	private Long cdnProduct;
 	private ProductDTO product;
 	private CustomerDTO customer;

	private LocalDateTime creationDate;
	private String creationUser;
	private LocalDateTime updateDate;
	private String updateUser;

}