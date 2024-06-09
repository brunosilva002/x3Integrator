package br.brn.x3Integrator.repository.demoDataBase;

import br.brn.x3Integrator.model.demoDataBase.Product2;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface Product2Repository extends JpaRepository<Product2, Long>, JpaSpecificationExecutor<Product2> {
}
