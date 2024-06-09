package br.brn.x3Integrator.dto;

import br.brn.x3Integrator.x3objects.X3Link;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.io.Serializable;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProductDTOList implements Serializable {
	@JsonProperty("$resources")
	private List<ProductDTO> list;

	@JsonProperty("$links")
	private X3Link x3Link;

}