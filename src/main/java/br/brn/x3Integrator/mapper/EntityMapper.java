package br.brn.x3Integrator.mapper;

import org.mapstruct.Context;

import java.util.ArrayList;
import java.util.List;

public interface EntityMapper<D, E> {
    E toEntity(D dto,@Context CycleAvoidingMappingContext context);

    D toDto(E entity,@Context CycleAvoidingMappingContext context);

    default List<E> toEntity(List<D> dtoList,@Context CycleAvoidingMappingContext context){
        List<E> entityList = new ArrayList<>();
        if(dtoList!=null) {
            dtoList.stream().forEach(dto -> entityList.add(toEntity(dto, context)));
        }
        return entityList;
    }
    default List<D> toDto(List<E> entityList,@Context CycleAvoidingMappingContext context){
        List<D> DTOList = new ArrayList<>();
        if(entityList!=null) {
            entityList.stream().forEach(entity -> DTOList.add(toDto(entity, context)));
        }
        return DTOList;
    }
}
