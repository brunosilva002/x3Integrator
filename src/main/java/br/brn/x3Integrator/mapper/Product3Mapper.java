package br.brn.x3Integrator.mapper;

import br.brn.x3Integrator.dto.ProductDTO;
import br.brn.x3Integrator.model.masterDataBase.Product;
import br.brn.x3Integrator.model.x3DataBase.Product3;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {})
public interface Product3Mapper extends EntityMapper<ProductDTO, Product3>{
}
