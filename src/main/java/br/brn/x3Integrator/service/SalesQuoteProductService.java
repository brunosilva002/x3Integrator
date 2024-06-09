package br.brn.x3Integrator.service;

import br.brn.x3Integrator.dto.SalesQuoteProductDTO;
import br.brn.x3Integrator.dto.ResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.Map;

import java.util.List;

@Component
public interface SalesQuoteProductService {
    ResponseDTO<SalesQuoteProductDTO> save(SalesQuoteProductDTO salesorderproductDTO);

    ResponseDTO<SalesQuoteProductDTO> obtain(Long cdnSalesOrderProduct);

    ResponseDTO<Page<SalesQuoteProductDTO>> pagination(Integer page, Integer pageSize, String sortBy, String direction, Long filter);

    ResponseDTO<List<SalesQuoteProductDTO>> listAll();

    ResponseDTO<List<SalesQuoteProductDTO>> listExample(SalesQuoteProductDTO salesorderproductDTO);

    ResponseDTO delete(Long cdnSalesOrderProduct);

    ResponseDTO<Page<SalesQuoteProductDTO>> paginationFull(Integer page, Integer pageSize, String sortBy, String direction, Map<String, Object> filterMap);
}
