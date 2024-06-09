package br.brn.x3Integrator.bo;

import br.brn.x3Integrator.dto.CustomerDTO;

import br.brn.x3Integrator.dto.CustomerDTOList;
import br.brn.x3Integrator.enums.X3Class;
import br.brn.x3Integrator.enums.X3Facet;
import br.brn.x3Integrator.enums.X3Representation;
import br.brn.x3Integrator.mapper.CycleAvoidingMappingContext;
import br.brn.x3Integrator.mapper.CustomerMapper;
import br.brn.x3Integrator.model.Customer;
import br.brn.x3Integrator.repository.CustomerRepository;
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
public class CustomerBO {

    @Autowired
    UtilBO utilBO;

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    CustomerMapper customerMapper;

    @Autowired
    X3IntegrationBO x3IntegrationBO;

    public CustomerDTO save(CustomerDTO customerDTO) throws IllegalAccessException {

        Customer customer = customerMapper.toEntity(customerDTO, new CycleAvoidingMappingContext());

        utilBO.assingObjectToList(customer, "customer");

        customerRepository.save(customer);

        utilBO.entityToDtoCustom(customer, customerDTO, new ArrayList<>());

        return customerDTO;

    }

    public CustomerDTO obtain(Long cdnCustomer) {
        Customer customer = new Customer();

        customer = customerRepository.getReferenceById(cdnCustomer);

        CustomerDTO customerDTO = new CustomerDTO(); //customerMapper.toDto(customer, new CycleAvoidingMappingContext());

        utilBO.entityToDtoCustom(customer, customerDTO, new ArrayList<>());

        return customerDTO;
    }

    public void delete(Long cdnCustomer) {
        Customer customer = new Customer(cdnCustomer);
        utilBO.checkRelationship(customer,cdnCustomer);
        customerRepository.deleteById(cdnCustomer);

    }

    public Page<CustomerDTO> pagination(Integer page, Integer pageSize, String sortBy, String direction, Long filter) {

        Customer customer = new Customer();
        return customerRepository
                .findAll(Example.of(customer),
                        PageRequest.of(page, pageSize, Sort.by(Sort.Direction.fromString(direction), sortBy)))
                .map(x -> {
                    CustomerDTO customerDTOAux = new CustomerDTO();
                    utilBO.entityToDtoCustom(x, customerDTOAux, new ArrayList<>());
                    return customerDTOAux;
                });
    }

    public List<CustomerDTO> listAll() {
        List<Customer> customer = new ArrayList<>();

        customer = customerRepository.findAll();

        //List<CustomerDTO> customerDTO = customerMapper.toDto(customer, new CycleAvoidingMappingContext());
        List<CustomerDTO> customerDTOList = new ArrayList<>();
        customer.forEach( t->{
            CustomerDTO customerDTOAux = new CustomerDTO();
            utilBO.entityToDtoCustom(t, customerDTOAux, new ArrayList<>());
            customerDTOList.add(customerDTOAux);
        });

        return customerDTOList;
    }

    public Object listExample(CustomerDTO customerDTO) {
        List<Customer> customer = new ArrayList<>();

        Customer customerExemple = customerMapper.toEntity(customerDTO, new CycleAvoidingMappingContext());

        customer = customerRepository.findAll(Example.of(customerExemple));

        List<CustomerDTO> customerDTOList = new ArrayList<>();
        customer.forEach( t->{
            CustomerDTO customerDTOAux = new CustomerDTO();
            utilBO.entityToDtoCustom(t, customerDTOAux, new ArrayList<>());
            customerDTOList.add(customerDTOAux);
        });

        return customerDTOList;
    }

    public Page<CustomerDTO> paginationFull(Integer page, Integer pageSize, String sortBy, String direction, Map<String, Object> filterMap) throws NoSuchFieldException, IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchMethodException {
        Page<CustomerDTO> customerDTOPage = null;

        Specification<Customer> spec = (root, query, criteriaBuilder) -> {
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

        customerDTOPage = customerRepository
                .findAll(spec,
                        PageRequest.of(page, pageSize, Sort.by(Sort.Direction.fromString(direction), sortBy)))
                .map(x -> {
                    CustomerDTO customerDTOAux = new CustomerDTO();
                    utilBO.entityToDtoCustom(x, customerDTOAux, new ArrayList<>());
                    return customerDTOAux;
                });


        return customerDTOPage;
    }


    public CustomerDTO getX3Customer(String x3Cod) {
        CustomerDTO customerDTO =  new CustomerDTO();

        customerDTO = x3IntegrationBO.consumeX3Rest(x3Cod, X3Representation.CUSTOMER, X3Class.CUSTOMER, X3Facet.DATAILS, HttpMethod.GET, customerDTO.getClass());

        return customerDTO;
    }

    public List<CustomerDTO> updateX3CustomerLot() throws IllegalAccessException {

        CustomerDTOList customerDTOS =  new CustomerDTOList();

        do {
            if (Objects.isNull(customerDTOS.getX3Link())){
                customerDTOS = x3IntegrationBO.consumeX3Rest("", X3Representation.CUSTOMER, X3Class.CUSTOMER, X3Facet.QUERY, HttpMethod.GET, customerDTOS.getClass());
            }else {
                customerDTOS = x3IntegrationBO.consumeX3RestGoToNext(customerDTOS.getX3Link().getNextUrl().getUrl(),HttpMethod.GET, customerDTOS.getClass());
            }

            for (CustomerDTO customerDTO:customerDTOS.getList()
                 ) {
                save(customerDTO);
            }

        } while (Objects.nonNull(customerDTOS.getX3Link().getNextUrl()));

        return null;
    }
}
