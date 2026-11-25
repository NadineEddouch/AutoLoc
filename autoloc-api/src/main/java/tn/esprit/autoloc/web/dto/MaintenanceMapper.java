package tn.esprit.autoloc.web.dto;

import org.mapstruct.Mapper;
import tn.esprit.autoloc.domain.Maintenance;

@Mapper(componentModel = "spring")
public interface MaintenanceMapper {
    MaintenanceDTO toDto(Maintenance maintenance);
    Maintenance toEntity(MaintenanceDTO maintenanceDTO);
    java.util.List<MaintenanceDTO> toDtoList(java.util.List<Maintenance> maintenances);
    java.util.List<Maintenance> toEntityList(java.util.List<MaintenanceDTO> dtos);
}
