package br.brn.x3Integrator.repository;

import br.brn.x3Integrator.model.SalesQuote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface SalesQuoteRepository extends JpaRepository<SalesQuote, Long>, JpaSpecificationExecutor<SalesQuote> {
}
