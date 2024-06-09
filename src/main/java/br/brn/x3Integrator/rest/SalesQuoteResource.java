package br.brn.x3Integrator.rest;

import br.brn.x3Integrator.dto.SalesQuoteDTO;
import br.brn.x3Integrator.dto.ResponseDTO;
import br.brn.x3Integrator.service.SalesQuoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/api/v1/salesquote")
public class SalesQuoteResource extends BaseResource {

    @Autowired
    SalesQuoteService salesquoteService;

    @PostMapping("/save")
    public ResponseEntity<ResponseDTO<SalesQuoteDTO>> save (@RequestBody SalesQuoteDTO salesquoteDTO){
        return (ResponseEntity<ResponseDTO<SalesQuoteDTO>>) findCodeReturn(salesquoteService.save(salesquoteDTO));
    }

    @GetMapping("/obtain")
    public ResponseEntity<ResponseDTO<SalesQuoteDTO>> obtain (@RequestParam Long cdnSalesQuote){
        return (ResponseEntity<ResponseDTO<SalesQuoteDTO>>) findCodeReturn(salesquoteService.obtain(cdnSalesQuote));
    }

    @DeleteMapping("/delete")
    public ResponseEntity<ResponseDTO> delete (@RequestParam Long cdnSalesQuote){
        return (ResponseEntity<ResponseDTO>) findCodeReturn(salesquoteService.delete(cdnSalesQuote));
    }

    @GetMapping("/pagination")
    ResponseEntity<ResponseDTO<Page<SalesQuoteDTO>>> pagination(@RequestParam(name= "page", defaultValue = "0") Integer page,
                                                                @RequestParam(name= "pageSize", defaultValue = "10") Integer pageSize,
                                                                @RequestParam(name= "sortBy", defaultValue = "cdnSalesQuote") String sortBy,
                                                                @RequestParam(name= "direction", defaultValue = "DESC") String direction,
                                                                @RequestParam(name = "filter",required = false) Long filter) {
        return (ResponseEntity<ResponseDTO<Page<SalesQuoteDTO>>>) findCodeReturn(salesquoteService.pagination(page,pageSize,sortBy,direction,filter));
    }

    @PostMapping("/paginationFull")
    ResponseEntity<ResponseDTO<Page<SalesQuoteDTO>>> paginationFull(@RequestParam(name= "page", defaultValue = "0") Integer page,
                                                                    @RequestParam(name= "pageSize", defaultValue = "10") Integer pageSize,
                                                                    @RequestParam(name= "sortBy", defaultValue = "cdnSalesQuote") String sortBy,
                                                                    @RequestParam(name= "direction", defaultValue = "DESC") String direction,
                                                                    @RequestBody Map<String, Object> filterMap) {
        return (ResponseEntity<ResponseDTO<Page<SalesQuoteDTO>>>) findCodeReturn(salesquoteService.paginationFull(page,pageSize,sortBy,direction,filterMap));
    }

    @PostMapping("/listAll")
    public ResponseEntity<ResponseDTO<List<SalesQuoteDTO>>> listAll (){
        return (ResponseEntity<ResponseDTO<List<SalesQuoteDTO>>>) findCodeReturn(salesquoteService.listAll());
    }

    @PostMapping("/listExample")
    public ResponseEntity<ResponseDTO<List<SalesQuoteDTO>>> listExample (@RequestBody SalesQuoteDTO salesquoteDTO){
        return (ResponseEntity<ResponseDTO<List<SalesQuoteDTO>>>) findCodeReturn(salesquoteService.listExample(salesquoteDTO));
    }

    @GetMapping("/resendErp")
    public ResponseEntity<ResponseDTO<SalesQuoteDTO>> resendErp (@RequestParam(name= "cdnSalesQuote") Long cdnSalesQuote){
        return (ResponseEntity<ResponseDTO<SalesQuoteDTO>>) findCodeReturn(salesquoteService.resendErp(cdnSalesQuote));
    }
}
