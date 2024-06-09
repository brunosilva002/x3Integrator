package br.brn.x3Integrator.mapper;

import br.brn.x3Integrator.dto.SiteDTO;
import br.brn.x3Integrator.model.masterDataBase.Site;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {})
public interface SiteMapper  extends EntityMapper<SiteDTO, Site>{
}
