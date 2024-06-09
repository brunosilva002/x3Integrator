package br.brn.x3Integrator.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequestDTO {
    private String firstName;
    private String lastName;
    private String email;
    private String password;

    private LocalDateTime dateCreated;
    private LocalDateTime dateUpdated;
    private UserDTO userCreate;
    private UserDTO userUpdate;
}
