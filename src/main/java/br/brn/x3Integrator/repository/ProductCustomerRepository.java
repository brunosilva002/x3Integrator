package br.brn.x3Integrator.repository;

import br.brn.x3Integrator.model.ProductCustomer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ProductCustomerRepository extends JpaRepository<ProductCustomer, Long>, JpaSpecificationExecutor<ProductCustomer> {
}
