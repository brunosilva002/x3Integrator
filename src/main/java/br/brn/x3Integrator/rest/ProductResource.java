package br.brn.x3Integrator.rest;

import br.brn.x3Integrator.dto.ProductDTO;
import br.brn.x3Integrator.dto.ProductDTO;
import br.brn.x3Integrator.dto.ResponseDTO;
import br.brn.x3Integrator.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/api/v1/product")
public class ProductResource extends BaseResource {

    @Autowired
    ProductService productService;

    @PostMapping("/save")
    public ResponseEntity<ResponseDTO<ProductDTO>> save (@RequestBody ProductDTO productDTO){
        return (ResponseEntity<ResponseDTO<ProductDTO>>) findCodeReturn(productService.save(productDTO));
    }

    @GetMapping("/obtain")
    public ResponseEntity<ResponseDTO<ProductDTO>> obtain (@RequestParam Long cdnProduct){
        return (ResponseEntity<ResponseDTO<ProductDTO>>) findCodeReturn(productService.obtain(cdnProduct));
    }

    @DeleteMapping("/delete")
    public ResponseEntity<ResponseDTO> delete (@RequestParam Long cdnProduct){
        return (ResponseEntity<ResponseDTO>) findCodeReturn(productService.delete(cdnProduct));
    }

    @GetMapping("/pagination")
    ResponseEntity<ResponseDTO<Page<ProductDTO>>> pagination(@RequestParam(name= "page", defaultValue = "0") Integer page,
                               @RequestParam(name= "pageSize", defaultValue = "10") Integer pageSize,
                               @RequestParam(name= "sortBy", defaultValue = "cdnProduct") String sortBy,
                               @RequestParam(name= "direction", defaultValue = "DESC") String direction,
                               @RequestParam(name = "filter",required = false) Long filter) {
        return (ResponseEntity<ResponseDTO<Page<ProductDTO>>>) findCodeReturn(productService.pagination(page,pageSize,sortBy,direction,filter));
    }

    @PostMapping("/paginationFull")
    ResponseEntity<ResponseDTO<Page<ProductDTO>>> paginationFull(   @RequestParam(name= "page", defaultValue = "0") Integer page,
                                                                    @RequestParam(name= "pageSize", defaultValue = "10") Integer pageSize,
                                                                    @RequestParam(name= "sortBy", defaultValue = "cdnProduct") String sortBy,
                                                                    @RequestParam(name= "direction", defaultValue = "DESC") String direction,
                                                                    @RequestBody Map<String, Object> filterMap) {
        return (ResponseEntity<ResponseDTO<Page<ProductDTO>>>) findCodeReturn(productService.paginationFull(page,pageSize,sortBy,direction,filterMap));
    }

    @PostMapping("/listAll")
    public ResponseEntity<ResponseDTO<List<ProductDTO>>> listAll (){
        return (ResponseEntity<ResponseDTO<List<ProductDTO>>>) findCodeReturn(productService.listAll());
    }

    @PostMapping("/listExample")
    public ResponseEntity<ResponseDTO<List<ProductDTO>>> listExample (@RequestBody ProductDTO productDTO){
        return (ResponseEntity<ResponseDTO<List<ProductDTO>>>) findCodeReturn(productService.listExample(productDTO));
    }

    @GetMapping("/getX3Product")
    ResponseEntity<ResponseDTO<ProductDTO>> getX3Product(@RequestParam(name= "x3Cod") String x3Cod) {
        return (ResponseEntity<ResponseDTO<ProductDTO>>) findCodeReturn(productService.getX3Product(x3Cod));
    }

    @GetMapping("/updateX3ProductLot")
    ResponseEntity<ResponseDTO<List<ProductDTO>>> updateX3ProductLot() {
        return (ResponseEntity<ResponseDTO<List<ProductDTO>>>) findCodeReturn(productService.updateX3ProductLot());
    }

    @PostMapping("/createX3Product")
    ResponseEntity<ResponseDTO<List<ProductDTO>>> createX3Product() {
        return (ResponseEntity<ResponseDTO<List<ProductDTO>>>) findCodeReturn(productService.createX3Product());
    }
}
