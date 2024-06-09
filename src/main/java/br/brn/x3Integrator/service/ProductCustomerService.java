package br.brn.x3Integrator.service;

import br.brn.x3Integrator.dto.ProductCustomerDTO;
import br.brn.x3Integrator.dto.ResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.Map;

import java.util.List;

@Component
public interface ProductCustomerService {
    ResponseDTO<ProductCustomerDTO> save(ProductCustomerDTO productcustomerDTO);

    ResponseDTO<ProductCustomerDTO> obtain(Long cdnProductCustomer);

    ResponseDTO<Page<ProductCustomerDTO>> pagination(Integer page, Integer pageSize, String sortBy, String direction, Long filter);

    ResponseDTO<List<ProductCustomerDTO>> listAll();

    ResponseDTO<List<ProductCustomerDTO>> listExample(ProductCustomerDTO productcustomerDTO);

    ResponseDTO delete(Long cdnProductCustomer);

    ResponseDTO<Page<ProductCustomerDTO>> paginationFull(Integer page, Integer pageSize, String sortBy, String direction, Map<String, Object> filterMap);
}
