package br.brn.x3Integrator.repository.x3DataBase;

import br.brn.x3Integrator.model.x3DataBase.Product3;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface Product3Repository extends JpaRepository<Product3, Long>, JpaSpecificationExecutor<Product3> {
}
