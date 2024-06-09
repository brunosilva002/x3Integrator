package br.brn.x3Integrator.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
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
public class ProductDTO implements Serializable {

	@JsonAlias({"cdnProduct"})
 	private Long cdnProduct;

	@JsonAlias({"ITMREF","cdnX3Product"})
	private String cdnX3Product;

	@JsonAlias({"YDESCSEFAZ","description"})
	private String description;

	@JsonAlias({"ITMSTA","productStatus"})
	private String productStatus;

	private LocalDateTime creationDate;
	private String creationUser;
	private LocalDateTime updateDate;
	private String updateUser;


}