package br.brn.x3Integrator.mapper;

import br.brn.x3Integrator.dto.SalesQuoteProductDTO;
import br.brn.x3Integrator.model.SalesQuoteProduct;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {})
public interface SalesQuoteProductMapper extends EntityMapper<SalesQuoteProductDTO, SalesQuoteProduct>{
}
