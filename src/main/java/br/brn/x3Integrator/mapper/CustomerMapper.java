package br.brn.x3Integrator.mapper;

import br.brn.x3Integrator.dto.CustomerDTO;
import br.brn.x3Integrator.model.masterDataBase.Customer;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {})
public interface CustomerMapper  extends EntityMapper<CustomerDTO, Customer>{
}
