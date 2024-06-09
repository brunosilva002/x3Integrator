package br.brn.x3Integrator.bo;

import br.brn.x3Integrator.dto.ProductDTO;
import br.brn.x3Integrator.dto.ProductDTOList;

import br.brn.x3Integrator.enums.X3Class;
import br.brn.x3Integrator.enums.X3Facet;
import br.brn.x3Integrator.enums.X3Representation;
import br.brn.x3Integrator.mapper.CycleAvoidingMappingContext;
import br.brn.x3Integrator.mapper.ProductMapper;
import br.brn.x3Integrator.model.masterDataBase.Product;
import br.brn.x3Integrator.repository.demoDataBase.Product2Repository;
import br.brn.x3Integrator.repository.masterDataBase.ProductRepository;
import br.brn.x3Integrator.repository.x3DataBase.Product3Repository;
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
public class ProductBO {

    @Autowired
    UtilBO utilBO;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    ProductMapper productMapper;

    @Autowired
    X3IntegrationBO x3IntegrationBO;

    public ProductDTO save(ProductDTO productDTO) throws IllegalAccessException {

        Product product = productMapper.toEntity(productDTO, new CycleAvoidingMappingContext());

        utilBO.assingObjectToList(product, "product");

        productRepository.save(product);

        utilBO.entityToDtoCustom(product, productDTO, new ArrayList<>());

        return productDTO;

    }

    public ProductDTO obtain(Long cdnProduct) {
        Product product = new Product();

        product = productRepository.getReferenceById(cdnProduct);

        ProductDTO productDTO = new ProductDTO(); //productMapper.toDto(product, new CycleAvoidingMappingContext());

        utilBO.entityToDtoCustom(product, productDTO, new ArrayList<>());

        return productDTO;
    }

    public void delete(Long cdnProduct) {
        Product product = new Product(cdnProduct);
        utilBO.checkRelationship(product,cdnProduct);
        productRepository.deleteById(cdnProduct);

    }

    public Page<ProductDTO> pagination(Integer page, Integer pageSize, String sortBy, String direction, Long filter) {

        Product product = new Product();
        return productRepository
                .findAll(Example.of(product),
                        PageRequest.of(page, pageSize, Sort.by(Sort.Direction.fromString(direction), sortBy)))
                .map(x -> {
                    ProductDTO productDTOAux = new ProductDTO();
                    utilBO.entityToDtoCustom(x, productDTOAux, new ArrayList<>());
                    return productDTOAux;
                });
    }

    public List<ProductDTO> listAll() {
        List<Product> product = new ArrayList<>();

        product = productRepository.findAll();

        //List<ProductDTO> productDTO = productMapper.toDto(product, new CycleAvoidingMappingContext());
        List<ProductDTO> productDTOList = new ArrayList<>();
        product.forEach( t->{
            ProductDTO productDTOAux = new ProductDTO();
            utilBO.entityToDtoCustom(t, productDTOAux, new ArrayList<>());
            productDTOList.add(productDTOAux);
        });

        return productDTOList;
    }

    public Object listExample(ProductDTO productDTO) {
        List<Product> product = new ArrayList<>();

        Product productExemple = productMapper.toEntity(productDTO, new CycleAvoidingMappingContext());

        product = productRepository.findAll(Example.of(productExemple));

        List<ProductDTO> productDTOList = new ArrayList<>();
        product.forEach( t->{
            ProductDTO productDTOAux = new ProductDTO();
            utilBO.entityToDtoCustom(t, productDTOAux, new ArrayList<>());
            productDTOList.add(productDTOAux);
        });

        return productDTOList;
    }

    public Page<ProductDTO> paginationFull(Integer page, Integer pageSize, String sortBy, String direction, Map<String, Object> filterMap) throws NoSuchFieldException, IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchMethodException {
        Page<ProductDTO> productDTOPage = null;

        Specification<Product> spec = (root, query, criteriaBuilder) -> {
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

        productDTOPage = productRepository
                .findAll(spec,
                        PageRequest.of(page, pageSize, Sort.by(Sort.Direction.fromString(direction), sortBy)))
                .map(x -> {
                    ProductDTO productDTOAux = new ProductDTO();
                    utilBO.entityToDtoCustom(x, productDTOAux, new ArrayList<>());
                    return productDTOAux;
                });


        return productDTOPage;
    }
    public ProductDTO getX3Product(String x3Cod) {
        ProductDTO productDTO =  new ProductDTO();

        productDTO = x3IntegrationBO.consumeX3Rest(x3Cod, X3Representation.PRODUCT, X3Class.PRODUCT, X3Facet.DATAILS, HttpMethod.GET, productDTO.getClass());

        return productDTO;
    }

    public List<ProductDTO> updateX3ProductLot() throws IllegalAccessException {

        ProductDTOList productDTOS =  new ProductDTOList();

        do {
            if (Objects.isNull(productDTOS.getX3Link())){
                productDTOS = x3IntegrationBO.consumeX3Rest("", X3Representation.PRODUCT, X3Class.PRODUCT, X3Facet.QUERY, HttpMethod.GET, productDTOS.getClass());
            }else {
                productDTOS = x3IntegrationBO.consumeX3RestGoToNext(productDTOS.getX3Link().getNextUrl().getUrl(),HttpMethod.GET, productDTOS.getClass());
            }

            for (ProductDTO productDTO:productDTOS.getList()
            ) {
                Product productSearcX3 = new Product();
                utilBO.setNullAllAtributtes(productSearcX3);
                productSearcX3.setCdnX3Product(productDTO.getCdnX3Product());

                Optional<Product> product = productRepository.findOne(Example.of(productSearcX3));
                if (product.isPresent()){
                    productDTO.setCdnProduct(product.get().getCdnProduct());
                }
                save(productDTO);
            }

        } while (Objects.nonNull(productDTOS.getX3Link().getNextUrl()));

        return null;
    }

    public Object createX3Product() throws Exception {

        //x3IntegrationBO.consumeX3Soap(X3PublicName.PRODUCT.getCod());

        return null;
    }
}
