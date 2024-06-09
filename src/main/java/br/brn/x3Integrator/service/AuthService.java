package br.brn.x3Integrator.service;

import br.brn.x3Integrator.dto.UserDTO;
import br.brn.x3Integrator.dto.*;
import org.springframework.stereotype.Component;

@Component
public interface AuthService {

    ResponseDTO<UserDTO> authenticate(AuthenticationRequestDTO request);

    ResponseDTO<UserDTO> register(RegisterRequestDTO register);

    ResponseDTO<UserDTO> reNewToken(String email);
}
