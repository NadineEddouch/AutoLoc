package tn.esprit.autoloc.web.dto;

import org.mapstruct.Mapper;
import tn.esprit.autoloc.domain.Equipement;

@Mapper(componentModel = "spring")
public interface EquipementMapper {
    EquipementDTO toDto(Equipement equipement);
    Equipement toEntity(EquipementDTO equipementDTO);
    java.util.List<EquipementDTO> toDtoList(java.util.List<Equipement> equipements);
    java.util.List<Equipement> toEntityList(java.util.List<EquipementDTO> dtos);
}
