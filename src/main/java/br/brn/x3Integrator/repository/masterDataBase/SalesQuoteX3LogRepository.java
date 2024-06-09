package br.brn.x3Integrator.repository.masterDataBase;

import br.brn.x3Integrator.model.masterDataBase.SalesQuoteX3Log;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface SalesQuoteX3LogRepository extends JpaRepository<SalesQuoteX3Log, Long>, JpaSpecificationExecutor<SalesQuoteX3Log> {
}
