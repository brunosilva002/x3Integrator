package br.brn.x3Integrator.rest;

import br.brn.x3Integrator.dto.ProductDTO;
import br.brn.x3Integrator.dto.SiteDTO;
import br.brn.x3Integrator.dto.ResponseDTO;
import br.brn.x3Integrator.service.SiteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/api/v1/site")
public class SiteResource extends BaseResource {

    @Autowired
    SiteService siteService;

    @PostMapping("/save")
    public ResponseEntity<ResponseDTO<SiteDTO>> save (@RequestBody SiteDTO siteDTO){
        return (ResponseEntity<ResponseDTO<SiteDTO>>) findCodeReturn(siteService.save(siteDTO));
    }

    @GetMapping("/obtain")
    public ResponseEntity<ResponseDTO<SiteDTO>> obtain (@RequestParam Long cdnSite){
        return (ResponseEntity<ResponseDTO<SiteDTO>>) findCodeReturn(siteService.obtain(cdnSite));
    }

    @DeleteMapping("/delete")
    public ResponseEntity<ResponseDTO> delete (@RequestParam Long cdnSite){
        return (ResponseEntity<ResponseDTO>) findCodeReturn(siteService.delete(cdnSite));
    }

    @GetMapping("/pagination")
    ResponseEntity<ResponseDTO<Page<SiteDTO>>> pagination(@RequestParam(name= "page", defaultValue = "0") Integer page,
                               @RequestParam(name= "pageSize", defaultValue = "10") Integer pageSize,
                               @RequestParam(name= "sortBy", defaultValue = "cdnSite") String sortBy,
                               @RequestParam(name= "direction", defaultValue = "DESC") String direction,
                               @RequestParam(name = "filter",required = false) Long filter) {
        return (ResponseEntity<ResponseDTO<Page<SiteDTO>>>) findCodeReturn(siteService.pagination(page,pageSize,sortBy,direction,filter));
    }

    @PostMapping("/paginationFull")
    ResponseEntity<ResponseDTO<Page<SiteDTO>>> paginationFull(   @RequestParam(name= "page", defaultValue = "0") Integer page,
                                                                    @RequestParam(name= "pageSize", defaultValue = "10") Integer pageSize,
                                                                    @RequestParam(name= "sortBy", defaultValue = "cdnSite") String sortBy,
                                                                    @RequestParam(name= "direction", defaultValue = "DESC") String direction,
                                                                    @RequestBody Map<String, Object> filterMap) {
        return (ResponseEntity<ResponseDTO<Page<SiteDTO>>>) findCodeReturn(siteService.paginationFull(page,pageSize,sortBy,direction,filterMap));
    }

    @PostMapping("/listAll")
    public ResponseEntity<ResponseDTO<List<SiteDTO>>> listAll (){
        return (ResponseEntity<ResponseDTO<List<SiteDTO>>>) findCodeReturn(siteService.listAll());
    }

    @PostMapping("/listExample")
    public ResponseEntity<ResponseDTO<List<SiteDTO>>> listExample (@RequestBody SiteDTO siteDTO){
        return (ResponseEntity<ResponseDTO<List<SiteDTO>>>) findCodeReturn(siteService.listExample(siteDTO));
    }

    @GetMapping("/updateX3SiteLot")
    ResponseEntity<ResponseDTO<List<SiteDTO>>> updateX3SiteLot() {
        return (ResponseEntity<ResponseDTO<List<SiteDTO>>>) findCodeReturn(siteService.updateX3SiteLot());
    }

}
