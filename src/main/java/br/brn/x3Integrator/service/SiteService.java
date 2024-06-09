package br.brn.x3Integrator.service;

import br.brn.x3Integrator.dto.SiteDTO;
import br.brn.x3Integrator.dto.ResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.Map;

import java.util.List;

@Component
public interface SiteService {
    ResponseDTO<SiteDTO> save(SiteDTO siteDTO);

    ResponseDTO<SiteDTO> obtain(Long cdnSite);

    ResponseDTO<Page<SiteDTO>> pagination(Integer page, Integer pageSize, String sortBy, String direction, Long filter);

    ResponseDTO<List<SiteDTO>> listAll();

    ResponseDTO<List<SiteDTO>> listExample(SiteDTO siteDTO);

    ResponseDTO delete(Long cdnSite);

    ResponseDTO<Page<SiteDTO>> paginationFull(Integer page, Integer pageSize, String sortBy, String direction, Map<String, Object> filterMap);

    ResponseDTO<?> updateX3SiteLot();
}
