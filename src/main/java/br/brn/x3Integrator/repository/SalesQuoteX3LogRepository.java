package br.brn.x3Integrator.repository;

import br.brn.x3Integrator.model.SalesQuoteX3Log;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface SalesQuoteX3LogRepository extends JpaRepository<SalesQuoteX3Log, Long>, JpaSpecificationExecutor<SalesQuoteX3Log> {
}
