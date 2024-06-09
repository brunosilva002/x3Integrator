package br.brn.x3Integrator.rest;

import br.brn.x3Integrator.dto.ProductCustomerDTO;
import br.brn.x3Integrator.dto.ResponseDTO;
import br.brn.x3Integrator.service.ProductCustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/api/v1/productcustomer")
public class ProductCustomerResource extends BaseResource {

    @Autowired
    ProductCustomerService productcustomerService;

    @PostMapping("/save")
    public ResponseEntity<ResponseDTO<ProductCustomerDTO>> save (@RequestBody ProductCustomerDTO productcustomerDTO){
        return (ResponseEntity<ResponseDTO<ProductCustomerDTO>>) findCodeReturn(productcustomerService.save(productcustomerDTO));
    }

    @GetMapping("/obtain")
    public ResponseEntity<ResponseDTO<ProductCustomerDTO>> obtain (@RequestParam Long cdnProductCustomer){
        return (ResponseEntity<ResponseDTO<ProductCustomerDTO>>) findCodeReturn(productcustomerService.obtain(cdnProductCustomer));
    }

    @DeleteMapping("/delete")
    public ResponseEntity<ResponseDTO> delete (@RequestParam Long cdnProductCustomer){
        return (ResponseEntity<ResponseDTO>) findCodeReturn(productcustomerService.delete(cdnProductCustomer));
    }

    @GetMapping("/pagination")
    ResponseEntity<ResponseDTO<Page<ProductCustomerDTO>>> pagination(@RequestParam(name= "page", defaultValue = "0") Integer page,
                               @RequestParam(name= "pageSize", defaultValue = "10") Integer pageSize,
                               @RequestParam(name= "sortBy", defaultValue = "cdnProductCustomer") String sortBy,
                               @RequestParam(name= "direction", defaultValue = "DESC") String direction,
                               @RequestParam(name = "filter",required = false) Long filter) {
        return (ResponseEntity<ResponseDTO<Page<ProductCustomerDTO>>>) findCodeReturn(productcustomerService.pagination(page,pageSize,sortBy,direction,filter));
    }

    @PostMapping("/paginationFull")
    ResponseEntity<ResponseDTO<Page<ProductCustomerDTO>>> paginationFull(   @RequestParam(name= "page", defaultValue = "0") Integer page,
                                                                    @RequestParam(name= "pageSize", defaultValue = "10") Integer pageSize,
                                                                    @RequestParam(name= "sortBy", defaultValue = "cdnProductCustomer") String sortBy,
                                                                    @RequestParam(name= "direction", defaultValue = "DESC") String direction,
                                                                    @RequestBody Map<String, Object> filterMap) {
        return (ResponseEntity<ResponseDTO<Page<ProductCustomerDTO>>>) findCodeReturn(productcustomerService.paginationFull(page,pageSize,sortBy,direction,filterMap));
    }

    @PostMapping("/listAll")
    public ResponseEntity<ResponseDTO<List<ProductCustomerDTO>>> listAll (){
        return (ResponseEntity<ResponseDTO<List<ProductCustomerDTO>>>) findCodeReturn(productcustomerService.listAll());
    }

    @PostMapping("/listExample")
    public ResponseEntity<ResponseDTO<List<ProductCustomerDTO>>> listExample (@RequestBody ProductCustomerDTO productcustomerDTO){
        return (ResponseEntity<ResponseDTO<List<ProductCustomerDTO>>>) findCodeReturn(productcustomerService.listExample(productcustomerDTO));
    }

}
