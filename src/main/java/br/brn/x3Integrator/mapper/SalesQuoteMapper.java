package br.brn.x3Integrator.mapper;

import br.brn.x3Integrator.dto.SalesQuoteDTO;
import br.brn.x3Integrator.model.SalesQuote;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {})
public interface SalesQuoteMapper extends EntityMapper<SalesQuoteDTO, SalesQuote>{
}
