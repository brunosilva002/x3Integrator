package br.brn.x3Integrator.repository;

import br.brn.x3Integrator.model.SalesQuoteProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface SalesQuoteProductRepository extends JpaRepository<SalesQuoteProduct, Long>, JpaSpecificationExecutor<SalesQuoteProduct> {
}
