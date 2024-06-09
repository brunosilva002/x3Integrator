package br.brn.x3Integrator.bo;

import br.brn.x3Integrator.dto.ProductCustomerDTO;

import br.brn.x3Integrator.mapper.CycleAvoidingMappingContext;
import br.brn.x3Integrator.mapper.ProductCustomerMapper;
import br.brn.x3Integrator.model.ProductCustomer;
import br.brn.x3Integrator.repository.ProductCustomerRepository;
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
public class ProductCustomerBO {

    @Autowired
    UtilBO utilBO;

    @Autowired
    ProductCustomerRepository productcustomerRepository;

    @Autowired
    ProductCustomerMapper productcustomerMapper;

    public ProductCustomerDTO save(ProductCustomerDTO productcustomerDTO) throws IllegalAccessException {

        ProductCustomer productcustomer = productcustomerMapper.toEntity(productcustomerDTO, new CycleAvoidingMappingContext());

        utilBO.assingObjectToList(productcustomer, "productcustomer");

        productcustomerRepository.save(productcustomer);

        utilBO.entityToDtoCustom(productcustomer, productcustomerDTO, new ArrayList<>());

        return productcustomerDTO;

    }

    public ProductCustomerDTO obtain(Long cdnProductCustomer) {
        ProductCustomer productcustomer = new ProductCustomer();

        productcustomer = productcustomerRepository.getReferenceById(cdnProductCustomer);

        ProductCustomerDTO productcustomerDTO = new ProductCustomerDTO(); //productcustomerMapper.toDto(productcustomer, new CycleAvoidingMappingContext());

        utilBO.entityToDtoCustom(productcustomer, productcustomerDTO, new ArrayList<>());

        return productcustomerDTO;
    }

    public void delete(Long cdnProductCustomer) {
        ProductCustomer productcustomer = new ProductCustomer(cdnProductCustomer);
        utilBO.checkRelationship(productcustomer,cdnProductCustomer);
        productcustomerRepository.deleteById(cdnProductCustomer);

    }

    public Page<ProductCustomerDTO> pagination(Integer page, Integer pageSize, String sortBy, String direction, Long filter) {

        ProductCustomer productcustomer = new ProductCustomer();
        return productcustomerRepository
                .findAll(Example.of(productcustomer),
                        PageRequest.of(page, pageSize, Sort.by(Sort.Direction.fromString(direction), sortBy)))
                .map(x -> {
                    ProductCustomerDTO productcustomerDTOAux = new ProductCustomerDTO();
                    utilBO.entityToDtoCustom(x, productcustomerDTOAux, new ArrayList<>());
                    return productcustomerDTOAux;
                });
    }

    public List<ProductCustomerDTO> listAll() {
        List<ProductCustomer> productcustomer = new ArrayList<>();

        productcustomer = productcustomerRepository.findAll();

        //List<ProductCustomerDTO> productcustomerDTO = productcustomerMapper.toDto(productcustomer, new CycleAvoidingMappingContext());
        List<ProductCustomerDTO> productcustomerDTOList = new ArrayList<>();
        productcustomer.forEach( t->{
            ProductCustomerDTO productcustomerDTOAux = new ProductCustomerDTO();
            utilBO.entityToDtoCustom(t, productcustomerDTOAux, new ArrayList<>());
            productcustomerDTOList.add(productcustomerDTOAux);
        });

        return productcustomerDTOList;
    }

    public Object listExample(ProductCustomerDTO productcustomerDTO) {
        List<ProductCustomer> productcustomer = new ArrayList<>();

        ProductCustomer productcustomerExemple = productcustomerMapper.toEntity(productcustomerDTO, new CycleAvoidingMappingContext());

        productcustomer = productcustomerRepository.findAll(Example.of(productcustomerExemple));

        List<ProductCustomerDTO> productcustomerDTOList = new ArrayList<>();
        productcustomer.forEach( t->{
            ProductCustomerDTO productcustomerDTOAux = new ProductCustomerDTO();
            utilBO.entityToDtoCustom(t, productcustomerDTOAux, new ArrayList<>());
            productcustomerDTOList.add(productcustomerDTOAux);
        });

        return productcustomerDTOList;
    }

    public Page<ProductCustomerDTO> paginationFull(Integer page, Integer pageSize, String sortBy, String direction, Map<String, Object> filterMap) throws NoSuchFieldException, IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchMethodException {
        Page<ProductCustomerDTO> productcustomerDTOPage = null;

        Specification<ProductCustomer> spec = (root, query, criteriaBuilder) -> {
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

        productcustomerDTOPage = productcustomerRepository
                .findAll(spec,
                        PageRequest.of(page, pageSize, Sort.by(Sort.Direction.fromString(direction), sortBy)))
                .map(x -> {
                    ProductCustomerDTO productcustomerDTOAux = new ProductCustomerDTO();
                    utilBO.entityToDtoCustom(x, productcustomerDTOAux, new ArrayList<>());
                    return productcustomerDTOAux;
                });


        return productcustomerDTOPage;
    }

}
