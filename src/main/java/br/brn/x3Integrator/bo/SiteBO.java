package br.brn.x3Integrator.bo;

import br.brn.x3Integrator.dto.SiteDTO;

import br.brn.x3Integrator.dto.SiteDTOList;
import br.brn.x3Integrator.enums.X3Class;
import br.brn.x3Integrator.enums.X3Facet;
import br.brn.x3Integrator.enums.X3Representation;
import br.brn.x3Integrator.mapper.CycleAvoidingMappingContext;
import br.brn.x3Integrator.mapper.SiteMapper;
import br.brn.x3Integrator.model.masterDataBase.Site;
import br.brn.x3Integrator.repository.masterDataBase.SiteRepository;
import jakarta.persistence.criteria.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationTargetException;
import java.util.*;

@Slf4j
@Component
public class SiteBO {

    @Autowired
    UtilBO utilBO;

    @Autowired
    SiteRepository siteRepository;

    @Autowired
    SiteMapper siteMapper;

    @Autowired
    X3IntegrationBO x3IntegrationBO;

    public SiteDTO save(SiteDTO siteDTO) throws IllegalAccessException {

        Site site = siteMapper.toEntity(siteDTO, new CycleAvoidingMappingContext());

        utilBO.assingObjectToList(site, "site");

        siteRepository.save(site);

        utilBO.entityToDtoCustom(site, siteDTO, new ArrayList<>());

        return siteDTO;

    }

    public SiteDTO obtain(Long cdnSite) {
        Site site = new Site();

        site = siteRepository.getReferenceById(cdnSite);

        SiteDTO siteDTO = new SiteDTO(); //siteMapper.toDto(site, new CycleAvoidingMappingContext());

        utilBO.entityToDtoCustom(site, siteDTO, new ArrayList<>());

        return siteDTO;
    }

    public void delete(Long cdnSite) {
        Site site = new Site(cdnSite);
        utilBO.checkRelationship(site,cdnSite);
        siteRepository.deleteById(cdnSite);

    }

    public Page<SiteDTO> pagination(Integer page, Integer pageSize, String sortBy, String direction, Long filter) {

        Site site = new Site();
        return siteRepository
                .findAll(Example.of(site),
                        PageRequest.of(page, pageSize, Sort.by(Sort.Direction.fromString(direction), sortBy)))
                .map(x -> {
                    SiteDTO siteDTOAux = new SiteDTO();
                    utilBO.entityToDtoCustom(x, siteDTOAux, new ArrayList<>());
                    return siteDTOAux;
                });
    }

    public List<SiteDTO> listAll() {
        List<Site> site = new ArrayList<>();

        site = siteRepository.findAll();

        //List<SiteDTO> siteDTO = siteMapper.toDto(site, new CycleAvoidingMappingContext());
        List<SiteDTO> siteDTOList = new ArrayList<>();
        site.forEach( t->{
            SiteDTO siteDTOAux = new SiteDTO();
            utilBO.entityToDtoCustom(t, siteDTOAux, new ArrayList<>());
            siteDTOList.add(siteDTOAux);
        });

        return siteDTOList;
    }

    public Object listExample(SiteDTO siteDTO) {
        List<Site> site = new ArrayList<>();

        Site siteExemple = siteMapper.toEntity(siteDTO, new CycleAvoidingMappingContext());

        site = siteRepository.findAll(Example.of(siteExemple));

        List<SiteDTO> siteDTOList = new ArrayList<>();
        site.forEach( t->{
            SiteDTO siteDTOAux = new SiteDTO();
            utilBO.entityToDtoCustom(t, siteDTOAux, new ArrayList<>());
            siteDTOList.add(siteDTOAux);
        });

        return siteDTOList;
    }

    public Page<SiteDTO> paginationFull(Integer page, Integer pageSize, String sortBy, String direction, Map<String, Object> filterMap) throws NoSuchFieldException, IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchMethodException {
        Page<SiteDTO> siteDTOPage = null;

        Specification<Site> spec = (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            for (Map.Entry<String, Object> entry : filterMap.entrySet()) {
                String[] propriedades = entry.getKey().split("\\.");
                String valor = "";
                String modo = "";
                Path<?> path = root;
                if (propriedades.length > 1) {
                    for (String propriedade : propriedades) {
                        path = path.get(propriedade);
                    }
                } else {
                    path = root.get(entry.getKey());
                }
                for (Map.Entry<String, Object> entry1 : ((Map<String, Object>) entry.getValue()).entrySet()) {
                    if (entry1.getKey().equals("matchMode")) {
                        modo = entry1.getValue().toString();
                    } else if (entry1.getKey().equals("value")) {
                        if (Objects.nonNull(entry1.getValue()))
                            valor = entry1.getValue().toString();
                        else
                            valor = null;
                    }
                }
                if (Objects.nonNull(valor)) {
                    switch (modo) {
                        case "startsWith":
                            predicates.add(criteriaBuilder.like((Expression<String>) path, valor + "%"));
                            break;
                        case "endsWith":
                            predicates.add(criteriaBuilder.like((Expression<String>) path, "%" + valor));
                            break;
                        case "equals":
                            predicates.add(criteriaBuilder.equal(path, valor));
                            break;
                        case "notEquals":
                            predicates.add(criteriaBuilder.notEqual(path, valor));
                            break;
                        case "notContains":
                            predicates.add(criteriaBuilder.notLike((Expression<String>) path, "%" + valor + "%"));
                            break;
                        case "contains":
                            predicates.add(criteriaBuilder.like((Expression<String>) path, "%" + valor + "%"));
                            break;
                    }
                }
            }
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };

        siteDTOPage = siteRepository
                .findAll(spec,
                        PageRequest.of(page, pageSize, Sort.by(Sort.Direction.fromString(direction), sortBy)))
                .map(x -> {
                    SiteDTO siteDTOAux = new SiteDTO();
                    utilBO.entityToDtoCustom(x, siteDTOAux, new ArrayList<>());
                    return siteDTOAux;
                });


        return siteDTOPage;
    }

    public Object updateX3SiteLot() throws IllegalAccessException {
        SiteDTOList siteDTOS =  new SiteDTOList();

        do {
            if (Objects.isNull(siteDTOS.getX3Link())){
                siteDTOS = x3IntegrationBO.consumeX3Rest("", X3Representation.PRODUCT, X3Class.PRODUCT, X3Facet.QUERY, HttpMethod.GET, siteDTOS.getClass());
            }else {
                siteDTOS = x3IntegrationBO.consumeX3RestGoToNext(siteDTOS.getX3Link().getNextUrl().getUrl(),HttpMethod.GET, siteDTOS.getClass());
            }

            for (SiteDTO siteDTO:siteDTOS.getList()
            ) {
                Site siteSearchX3 = new Site();
                utilBO.setNullAllAtributtes(siteSearchX3);
                siteSearchX3.setCdnX3Site(siteDTO.getCdnX3Site());

                Optional<Site> site = siteRepository.findOne(Example.of(siteSearchX3));
                if (site.isPresent()){
                    siteDTO.setCdnSite(site.get().getCdnSite());
                }
                save(siteDTO);
            }

        } while (Objects.nonNull(siteDTOS.getX3Link().getNextUrl()));

        return null;
    }
}
