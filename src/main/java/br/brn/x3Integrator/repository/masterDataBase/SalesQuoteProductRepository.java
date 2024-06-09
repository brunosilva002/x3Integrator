package br.brn.x3Integrator.repository.masterDataBase;

import br.brn.x3Integrator.model.masterDataBase.SalesQuoteProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface SalesQuoteProductRepository extends JpaRepository<SalesQuoteProduct, Long>, JpaSpecificationExecutor<SalesQuoteProduct> {
}
