package br.brn.x3Integrator.mapper;

import br.brn.x3Integrator.dto.ProductCustomerDTO;
import br.brn.x3Integrator.model.masterDataBase.ProductCustomer;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {})
public interface ProductCustomerMapper  extends EntityMapper<ProductCustomerDTO, ProductCustomer>{
}
