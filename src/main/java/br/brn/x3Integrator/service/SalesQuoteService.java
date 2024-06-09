package br.brn.x3Integrator.service;

import br.brn.x3Integrator.dto.SalesQuoteDTO;
import br.brn.x3Integrator.dto.ResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.Map;

import java.util.List;

@Component
public interface SalesQuoteService {
    ResponseDTO<SalesQuoteDTO> save(SalesQuoteDTO salesquoteDTO);

    ResponseDTO<SalesQuoteDTO> obtain(Long cdnSalesQuote);

    ResponseDTO<Page<SalesQuoteDTO>> pagination(Integer page, Integer pageSize, String sortBy, String direction, Long filter);

    ResponseDTO<List<SalesQuoteDTO>> listAll();

    ResponseDTO<List<SalesQuoteDTO>> listExample(SalesQuoteDTO salesquoteDTO);

    ResponseDTO delete(Long cdnSalesQuote);

    ResponseDTO<Page<SalesQuoteDTO>> paginationFull(Integer page, Integer pageSize, String sortBy, String direction, Map<String, Object> filterMap);

    ResponseDTO<SalesQuoteDTO> resendErp(Long cdnSalesQuote);
}
