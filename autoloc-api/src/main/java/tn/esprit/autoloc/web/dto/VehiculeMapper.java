package tn.esprit.autoloc.web.dto;

import org.mapstruct.Mapper;
import tn.esprit.autoloc.domain.Vehicule;

@Mapper(componentModel = "spring")
public interface VehiculeMapper {
    VehiculeDTO toDto(Vehicule vehicule);
    Vehicule toEntity(VehiculeDTO vehiculeDTO);
    java.util.List<VehiculeDTO> toDtoList(java.util.List<Vehicule> vehicules);
    java.util.List<Vehicule> toEntityList(java.util.List<VehiculeDTO> dtos);
}
