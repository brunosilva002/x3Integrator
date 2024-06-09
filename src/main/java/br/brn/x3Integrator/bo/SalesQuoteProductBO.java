package br.brn.x3Integrator.bo;

import br.brn.x3Integrator.dto.SalesQuoteProductDTO;

import br.brn.x3Integrator.mapper.CycleAvoidingMappingContext;
import br.brn.x3Integrator.mapper.SalesQuoteProductMapper;
import br.brn.x3Integrator.model.SalesQuoteProduct;
import br.brn.x3Integrator.repository.SalesQuoteProductRepository;
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
public class SalesQuoteProductBO {

    @Autowired
    UtilBO utilBO;

    @Autowired
    SalesQuoteProductRepository salesorderproductRepository;

    @Autowired
    SalesQuoteProductMapper salesorderproductMapper;

    public SalesQuoteProductDTO save(SalesQuoteProductDTO salesorderproductDTO) throws IllegalAccessException {

        SalesQuoteProduct salesorderproduct = salesorderproductMapper.toEntity(salesorderproductDTO, new CycleAvoidingMappingContext());

        utilBO.assingObjectToList(salesorderproduct, "salesorderproduct");

        salesorderproductRepository.save(salesorderproduct);

        utilBO.entityToDtoCustom(salesorderproduct, salesorderproductDTO, new ArrayList<>());

        return salesorderproductDTO;

    }

    public SalesQuoteProductDTO obtain(Long cdnSalesOrderProduct) {
        SalesQuoteProduct salesorderproduct = new SalesQuoteProduct();

        salesorderproduct = salesorderproductRepository.getReferenceById(cdnSalesOrderProduct);

        SalesQuoteProductDTO salesorderproductDTO = new SalesQuoteProductDTO(); //salesorderproductMapper.toDto(salesorderproduct, new CycleAvoidingMappingContext());

        utilBO.entityToDtoCustom(salesorderproduct, salesorderproductDTO, new ArrayList<>());

        return salesorderproductDTO;
    }

//    public void delete(Long cdnSalesOrderProduct) {
//        SalesOrderProduct salesorderproduct = new SalesOrderProduct(cdnSalesOrderProduct);
//        utilBO.checkRelationship(salesorderproduct,cdnSalesOrderProduct);
//        salesorderproductRepository.deleteById(cdnSalesOrderProduct);
//
//    }

    public Page<SalesQuoteProductDTO> pagination(Integer page, Integer pageSize, String sortBy, String direction, Long filter) {

        SalesQuoteProduct salesorderproduct = new SalesQuoteProduct();
        return salesorderproductRepository
                .findAll(Example.of(salesorderproduct),
                        PageRequest.of(page, pageSize, Sort.by(Sort.Direction.fromString(direction), sortBy)))
                .map(x -> {
                    SalesQuoteProductDTO salesorderproductDTOAux = new SalesQuoteProductDTO();
                    utilBO.entityToDtoCustom(x, salesorderproductDTOAux, new ArrayList<>());
                    return salesorderproductDTOAux;
                });
    }

    public List<SalesQuoteProductDTO> listAll() {
        List<SalesQuoteProduct> salesorderproduct = new ArrayList<>();

        salesorderproduct = salesorderproductRepository.findAll();

        //List<SalesOrderProductDTO> salesorderproductDTO = salesorderproductMapper.toDto(salesorderproduct, new CycleAvoidingMappingContext());
        List<SalesQuoteProductDTO> salesorderproductDTOList = new ArrayList<>();
        salesorderproduct.forEach( t->{
            SalesQuoteProductDTO salesorderproductDTOAux = new SalesQuoteProductDTO();
            utilBO.entityToDtoCustom(t, salesorderproductDTOAux, new ArrayList<>());
            salesorderproductDTOList.add(salesorderproductDTOAux);
        });

        return salesorderproductDTOList;
    }

    public Object listExample(SalesQuoteProductDTO salesorderproductDTO) {
        List<SalesQuoteProduct> salesorderproduct = new ArrayList<>();

        SalesQuoteProduct salesorderproductExemple = salesorderproductMapper.toEntity(salesorderproductDTO, new CycleAvoidingMappingContext());

        salesorderproduct = salesorderproductRepository.findAll(Example.of(salesorderproductExemple));

        List<SalesQuoteProductDTO> salesorderproductDTOList = new ArrayList<>();
        salesorderproduct.forEach( t->{
            SalesQuoteProductDTO salesorderproductDTOAux = new SalesQuoteProductDTO();
            utilBO.entityToDtoCustom(t, salesorderproductDTOAux, new ArrayList<>());
            salesorderproductDTOList.add(salesorderproductDTOAux);
        });

        return salesorderproductDTOList;
    }

    public Page<SalesQuoteProductDTO> paginationFull(Integer page, Integer pageSize, String sortBy, String direction, Map<String, Object> filterMap) throws NoSuchFieldException, IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchMethodException {
        Page<SalesQuoteProductDTO> salesorderproductDTOPage = null;

        Specification<SalesQuoteProduct> spec = (root, query, criteriaBuilder) -> {
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

        salesorderproductDTOPage = salesorderproductRepository
                .findAll(spec,
                        PageRequest.of(page, pageSize, Sort.by(Sort.Direction.fromString(direction), sortBy)))
                .map(x -> {
                    SalesQuoteProductDTO salesorderproductDTOAux = new SalesQuoteProductDTO();
                    utilBO.entityToDtoCustom(x, salesorderproductDTOAux, new ArrayList<>());
                    return salesorderproductDTOAux;
                });


        return salesorderproductDTOPage;
    }

}
