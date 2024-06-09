package br.brn.x3Integrator.bo;

import br.brn.x3Integrator.dto.SalesQuoteDTO;

import br.brn.x3Integrator.enums.X3StatusIntegration;
import br.brn.x3Integrator.mapper.CycleAvoidingMappingContext;
import br.brn.x3Integrator.mapper.SalesQuoteMapper;
import br.brn.x3Integrator.model.SalesQuote;
import br.brn.x3Integrator.repository.SalesQuoteRepository;
import br.brn.x3Integrator.repository.SalesQuoteX3LogRepository;
import br.brn.x3Integrator.x3objects.X3Envelope;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationTargetException;
import java.util.*;

@Slf4j
@Component
public class SalesQuoteBO {
    @Autowired
    private SalesQuoteX3LogRepository salesQuoteX3LogRepository;

    @Autowired
    UtilBO utilBO;

    @Autowired
    SalesQuoteRepository salesquoteRepository;

    @Autowired
    SalesQuoteMapper salesquoteMapper;

    @Autowired
    X3IntegrationBO x3IntegrationBO;

    @PersistenceContext
    private EntityManager entityManager;


    public SalesQuoteDTO save(SalesQuoteDTO salesquoteDTO) throws Exception {

        SalesQuote salesquote = salesquoteMapper.toEntity(salesquoteDTO, new CycleAvoidingMappingContext());

        utilBO.assingObjectToList(salesquote, "salesQuote");
        salesquote.setX3IntegrationStatus(X3StatusIntegration.PENDING.getCod());

        salesquoteRepository.save(salesquote);

        if (Objects.nonNull(salesquote.getCdnSalesQuote())){
            salesquoteDTO = preIntegrationSalesQuoteX3(salesquote);
        }
        return salesquoteDTO;

    }

    private SalesQuoteDTO preIntegrationSalesQuoteX3(SalesQuote salesquote) {

        SalesQuoteDTO salesQuoteDTO = new SalesQuoteDTO();

        X3Envelope x3Envelope = x3IntegrationBO.sendSalesQuoteX3(salesquote);

        SalesQuote salesQuoteUpdate = x3IntegrationBO.updateSalesQuoteFromX3(salesquote, x3Envelope);

        utilBO.entityToDtoCustom(salesQuoteUpdate, salesQuoteDTO, new ArrayList<>());

        return salesQuoteDTO;
    }


    public SalesQuoteDTO obtain(Long cdnSalesQuote) {
        SalesQuote salesquote = new SalesQuote();

        salesquote = salesquoteRepository.getReferenceById(cdnSalesQuote);

        SalesQuoteDTO salesquoteDTO = new SalesQuoteDTO(); //salesquoteMapper.toDto(salesquote, new CycleAvoidingMappingContext());

        utilBO.entityToDtoCustom(salesquote, salesquoteDTO, new ArrayList<>());

        return salesquoteDTO;
    }

    public void delete(Long cdnSalesQuote) {
        SalesQuote salesquote = new SalesQuote(cdnSalesQuote);
        utilBO.checkRelationship(salesquote,cdnSalesQuote);
        salesquoteRepository.deleteById(cdnSalesQuote);

    }

    public Page<SalesQuoteDTO> pagination(Integer page, Integer pageSize, String sortBy, String direction, Long filter) {

        SalesQuote salesquote = new SalesQuote();
        return salesquoteRepository
                .findAll(Example.of(salesquote),
                        PageRequest.of(page, pageSize, Sort.by(Sort.Direction.fromString(direction), sortBy)))
                .map(x -> {
                    SalesQuoteDTO salesquoteDTOAux = new SalesQuoteDTO();
                    utilBO.entityToDtoCustom(x, salesquoteDTOAux, new ArrayList<>());
                    return salesquoteDTOAux;
                });
    }

    public List<SalesQuoteDTO> listAll() {
        List<SalesQuote> salesquote = new ArrayList<>();

        salesquote = salesquoteRepository.findAll();

        //List<SalesQuoteDTO> salesquoteDTO = salesquoteMapper.toDto(salesquote, new CycleAvoidingMappingContext());
        List<SalesQuoteDTO> salesquoteDTOList = new ArrayList<>();
        salesquote.forEach( t->{
            SalesQuoteDTO salesquoteDTOAux = new SalesQuoteDTO();
            utilBO.entityToDtoCustom(t, salesquoteDTOAux, new ArrayList<>());
            salesquoteDTOList.add(salesquoteDTOAux);
        });

        return salesquoteDTOList;
    }

    public Object listExample(SalesQuoteDTO salesquoteDTO) {
        List<SalesQuote> salesquote = new ArrayList<>();

        SalesQuote salesquoteExemple = salesquoteMapper.toEntity(salesquoteDTO, new CycleAvoidingMappingContext());

        salesquote = salesquoteRepository.findAll(Example.of(salesquoteExemple));

        List<SalesQuoteDTO> salesquoteDTOList = new ArrayList<>();
        salesquote.forEach( t->{
            SalesQuoteDTO salesquoteDTOAux = new SalesQuoteDTO();
            utilBO.entityToDtoCustom(t, salesquoteDTOAux, new ArrayList<>());
            salesquoteDTOList.add(salesquoteDTOAux);
        });

        return salesquoteDTOList;
    }

    public Page<SalesQuoteDTO> paginationFull(Integer page, Integer pageSize, String sortBy, String direction, Map<String, Object> filterMap) throws NoSuchFieldException, IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchMethodException {
        Page<SalesQuoteDTO> salesquoteDTOPage = null;

        Specification<SalesQuote> spec = (root, query, criteriaBuilder) -> {
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

        salesquoteDTOPage = salesquoteRepository
                .findAll(spec,
                        PageRequest.of(page, pageSize, Sort.by(Sort.Direction.fromString(direction), sortBy)))
                .map(x -> {
                    SalesQuoteDTO salesquoteDTOAux = new SalesQuoteDTO();
                    utilBO.entityToDtoCustom(x, salesquoteDTOAux, new ArrayList<>());
                    return salesquoteDTOAux;
                });


        return salesquoteDTOPage;
    }

    public SalesQuoteDTO resendErp(Long cdnSalesQuote) {

        SalesQuoteDTO salesQuoteDTO = new SalesQuoteDTO();
        Optional<SalesQuote> salesQuote = salesquoteRepository.findById(cdnSalesQuote);

        if (salesQuote.isPresent()){

            salesQuoteDTO = preIntegrationSalesQuoteX3(salesQuote.get());
        }

        return salesQuoteDTO;
    }
}
