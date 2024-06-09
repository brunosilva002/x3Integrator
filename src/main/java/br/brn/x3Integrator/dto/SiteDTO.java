package br.brn.x3Integrator.dto;

import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
@Getter
@Setter
public class SiteDTO implements Serializable {
	private Long cdnSite;
 	private String cdnX3Site;

	private String creationUser;
	private String updateUser;

}