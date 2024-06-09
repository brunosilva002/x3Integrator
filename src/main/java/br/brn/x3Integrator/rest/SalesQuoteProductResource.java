package br.brn.x3Integrator.rest;

import br.brn.x3Integrator.dto.SalesQuoteProductDTO;
import br.brn.x3Integrator.dto.ResponseDTO;
import br.brn.x3Integrator.service.SalesQuoteProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/api/v1/salesquoteproduct")
public class SalesQuoteProductResource extends BaseResource {

    @Autowired
    SalesQuoteProductService salesquoteproductService;

    @PostMapping("/save")
    public ResponseEntity<ResponseDTO<SalesQuoteProductDTO>> save (@RequestBody SalesQuoteProductDTO salesquoteproductDTO){
        return (ResponseEntity<ResponseDTO<SalesQuoteProductDTO>>) findCodeReturn(salesquoteproductService.save(salesquoteproductDTO));
    }

    @GetMapping("/obtain")
    public ResponseEntity<ResponseDTO<SalesQuoteProductDTO>> obtain (@RequestParam Long cdnSalesQuoteProduct){
        return (ResponseEntity<ResponseDTO<SalesQuoteProductDTO>>) findCodeReturn(salesquoteproductService.obtain(cdnSalesQuoteProduct));
    }

    @DeleteMapping("/delete")
    public ResponseEntity<ResponseDTO> delete (@RequestParam Long cdnSalesQuoteProduct){
        return (ResponseEntity<ResponseDTO>) findCodeReturn(salesquoteproductService.delete(cdnSalesQuoteProduct));
    }

    @GetMapping("/pagination")
    ResponseEntity<ResponseDTO<Page<SalesQuoteProductDTO>>> pagination(@RequestParam(name= "page", defaultValue = "0") Integer page,
                                                                       @RequestParam(name= "pageSize", defaultValue = "10") Integer pageSize,
                                                                       @RequestParam(name= "sortBy", defaultValue = "cdnSalesQuoteProduct") String sortBy,
                                                                       @RequestParam(name= "direction", defaultValue = "DESC") String direction,
                                                                       @RequestParam(name = "filter",required = false) Long filter) {
        return (ResponseEntity<ResponseDTO<Page<SalesQuoteProductDTO>>>) findCodeReturn(salesquoteproductService.pagination(page,pageSize,sortBy,direction,filter));
    }

    @PostMapping("/paginationFull")
    ResponseEntity<ResponseDTO<Page<SalesQuoteProductDTO>>> paginationFull(@RequestParam(name= "page", defaultValue = "0") Integer page,
                                                                           @RequestParam(name= "pageSize", defaultValue = "10") Integer pageSize,
                                                                           @RequestParam(name= "sortBy", defaultValue = "cdnSalesQuoteProduct") String sortBy,
                                                                           @RequestParam(name= "direction", defaultValue = "DESC") String direction,
                                                                           @RequestBody Map<String, Object> filterMap) {
        return (ResponseEntity<ResponseDTO<Page<SalesQuoteProductDTO>>>) findCodeReturn(salesquoteproductService.paginationFull(page,pageSize,sortBy,direction,filterMap));
    }

    @PostMapping("/listAll")
    public ResponseEntity<ResponseDTO<List<SalesQuoteProductDTO>>> listAll (){
        return (ResponseEntity<ResponseDTO<List<SalesQuoteProductDTO>>>) findCodeReturn(salesquoteproductService.listAll());
    }

    @PostMapping("/listExample")
    public ResponseEntity<ResponseDTO<List<SalesQuoteProductDTO>>> listExample (@RequestBody SalesQuoteProductDTO salesquoteproductDTO){
        return (ResponseEntity<ResponseDTO<List<SalesQuoteProductDTO>>>) findCodeReturn(salesquoteproductService.listExample(salesquoteproductDTO));
    }

}
