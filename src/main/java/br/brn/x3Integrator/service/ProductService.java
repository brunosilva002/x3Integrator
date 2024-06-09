package br.brn.x3Integrator.service;

import br.brn.x3Integrator.dto.ProductDTO;
import br.brn.x3Integrator.dto.ProductDTO;
import br.brn.x3Integrator.dto.ResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.Map;

import java.util.List;

@Component
public interface ProductService {
    ResponseDTO<ProductDTO> save(ProductDTO productDTO);

    ResponseDTO<ProductDTO> obtain(Long cdnProduct);

    ResponseDTO<Page<ProductDTO>> pagination(Integer page, Integer pageSize, String sortBy, String direction, Long filter);

    ResponseDTO<List<ProductDTO>> listAll();

    ResponseDTO<List<ProductDTO>> listExample(ProductDTO productDTO);

    ResponseDTO delete(Long cdnProduct);

    ResponseDTO<Page<ProductDTO>> paginationFull(Integer page, Integer pageSize, String sortBy, String direction, Map<String, Object> filterMap);

    ResponseDTO<ProductDTO> getX3Product(String x3Cod);

    ResponseDTO<List<ProductDTO>> updateX3ProductLot();

    ResponseDTO<?> createX3Product();
}
