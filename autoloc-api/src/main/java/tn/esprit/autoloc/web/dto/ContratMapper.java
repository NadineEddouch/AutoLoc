package tn.esprit.autoloc.web.dto;

import org.mapstruct.Mapper;
import tn.esprit.autoloc.domain.Contrat;

@Mapper(componentModel = "spring")
public interface ContratMapper {
    ContratDTO toDto(Contrat contrat);
    Contrat toEntity(ContratDTO contratDTO);
    java.util.List<ContratDTO> toDtoList(java.util.List<Contrat> contrats);
    java.util.List<Contrat> toEntityList(java.util.List<ContratDTO> dtos);
}
