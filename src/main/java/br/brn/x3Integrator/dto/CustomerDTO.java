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
public class CustomerDTO implements Serializable {

	@JsonAlias({"cdnCustomer"})
	private Long cdnCustomer =0L;

	@JsonAlias ({"BPCNUM","cdnX3Customer"})
	private String cdnX3Customer = "";

	@JsonAlias({"XQRAZAO","razaoSocial","searchString"})
 	private String razaoSocial = "";

	@JsonAlias({"BPCSTA","status"})
	private Boolean status = false;

	private LocalDateTime creationDate;
	private String creationUser;
	private LocalDateTime updateDate;
	private String updateUser;

	public CustomerDTO(Long bpcord) {
		this.cdnCustomer = bpcord;
	}
}