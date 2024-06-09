package br.brn.x3Integrator.rest;

import br.brn.x3Integrator.dto.RegisterRequestDTO;
import br.brn.x3Integrator.dto.ResponseDTO;
import br.brn.x3Integrator.dto.UserDTO;
import br.brn.x3Integrator.dto.*;
import br.brn.x3Integrator.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationResource extends BaseResource{

    private final AuthService authService;

    @PostMapping("/authenticate")
    public ResponseEntity<ResponseDTO<AuthenticatoinResponseDTO>> authenticate(
            @RequestBody AuthenticationRequestDTO request
    ){
        return (ResponseEntity<ResponseDTO<AuthenticatoinResponseDTO>>) findCodeReturn(authService.authenticate(request));
    }

    @PostMapping("/reNewToken")
    public ResponseEntity<ResponseDTO<UserDTO>> reNewToken(
            @RequestParam String email
    ){
        return (ResponseEntity<ResponseDTO<UserDTO>>) findCodeReturn(authService.reNewToken(email));
    }

    @PostMapping("/register")
    public ResponseEntity<ResponseDTO<UserDTO>> register (@RequestBody RegisterRequestDTO register){
        return (ResponseEntity<ResponseDTO<UserDTO>>) findCodeReturn(authService.register(register));
    }
}
