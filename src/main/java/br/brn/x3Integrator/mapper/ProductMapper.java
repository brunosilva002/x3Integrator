package br.brn.x3Integrator.mapper;

import br.brn.x3Integrator.dto.ProductDTO;
import br.brn.x3Integrator.model.Product;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {})
public interface ProductMapper  extends EntityMapper<ProductDTO, Product>{
}
