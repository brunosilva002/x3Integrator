package br.brn.x3Integrator.bo;

import br.brn.x3Integrator.dto.AuthenticationRequestDTO;
import br.brn.x3Integrator.dto.RegisterRequestDTO;
import br.brn.x3Integrator.dto.UserDTO;
import br.brn.x3Integrator.exception.BussineRuleException;
import br.brn.x3Integrator.exception.ExceptionMessage;
import br.brn.x3Integrator.model.masterDataBase.User;
import br.brn.x3Integrator.repository.masterDataBase.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthBO {

    @Autowired
    private ExceptionMessage exceptionMessage;

    private final UserRepository userRepository;

    public UserDTO register(RegisterRequestDTO request) {

        if (Objects.isNull(request.getEmail()) || request.getEmail().isEmpty()){
            throw new BussineRuleException(exceptionMessage.getMessage("email.mandatory", new Object[]{}));
        }

        Optional<User> userExample = userRepository.findByEmail(request.getEmail());
        Long auxCdn = null;
        if (userExample.isPresent()){
            auxCdn = userExample.get().getCdnUser();
        }

        User user = new User();
        user.setCdnUser(auxCdn);
        user.setName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword());
        userRepository.save(user);
        UserDTO userDTO = new UserDTO();
        UtilBO.entityToDtoCustom(user, userDTO, new ArrayList<>());
        return userDTO;
    }

    public UserDTO authenticate(AuthenticationRequestDTO request) {
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new BussineRuleException(exceptionMessage.getMessage("user.not.found", new Object[]{})));
        UserDTO userDTO = new UserDTO();
        UtilBO.entityToDtoCustom(user, userDTO, new ArrayList<>());
        return userDTO;
    }

    public Object reNewToken(String email) {

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new BussineRuleException(exceptionMessage.getMessage("user.not.found", new Object[]{})));
        UserDTO userDTO = new UserDTO();
        UtilBO.entityToDtoCustom(user, userDTO, new ArrayList<>());
        return userDTO;
    }
}
