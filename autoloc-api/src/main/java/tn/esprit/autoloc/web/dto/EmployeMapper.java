package tn.esprit.autoloc.web.dto;

import org.mapstruct.Mapper;
import tn.esprit.autoloc.domain.Employe;

@Mapper(componentModel = "spring")
public interface EmployeMapper {
    EmployeDTO toDto(Employe employe);
    Employe toEntity(EmployeDTO employeDTO);
    java.util.List<EmployeDTO> toDtoList(java.util.List<Employe> employes);
    java.util.List<Employe> toEntityList(java.util.List<EmployeDTO> dtos);
}
