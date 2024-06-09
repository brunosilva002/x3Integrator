package br.brn.x3Integrator.repository.masterDataBase;

import br.brn.x3Integrator.model.masterDataBase.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository("UserRepository")
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);

}
