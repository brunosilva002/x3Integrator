package br.brn.x3Integrator.dto;

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
public class UserDTO implements Serializable {
    private Long cdnUser;
    private String email;
    private String password;
    private String name;
    private String lastName;

    private String creationUser;
    private String updateUser;
}
