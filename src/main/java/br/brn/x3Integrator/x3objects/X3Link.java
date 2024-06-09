package br.brn.x3Integrator.x3objects;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class X3Link implements Serializable {

	@JsonProperty("$next")
	private NextUrl nextUrl;

	@JsonProperty("$previous")
	private PreviousUrl previousUrl;

	@JsonProperty("$first")
	private FirstUrl firstUrl;

	@JsonProperty("$last")
	private LastUrl lastUrl;

	@Getter
	@Setter
	@JsonIgnoreProperties(ignoreUnknown = true)
	public static class PreviousUrl {
		@JsonProperty("$url")
		private String url;
	}

	@Getter
	@Setter
	@JsonIgnoreProperties(ignoreUnknown = true)
	public static class FirstUrl {
		@JsonProperty("$url")
		private String url;
	}

	@Getter
	@Setter
	@JsonIgnoreProperties(ignoreUnknown = true)
	public static class LastUrl {
		@JsonProperty("$url")
		private String url;
	}

	@Getter
	@Setter
	@JsonIgnoreProperties(ignoreUnknown = true)
	public static class NextUrl {
		@JsonProperty("$url")
		private String url;
	}
}