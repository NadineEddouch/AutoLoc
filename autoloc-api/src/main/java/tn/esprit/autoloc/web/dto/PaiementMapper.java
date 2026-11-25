package tn.esprit.autoloc.web.dto;

import org.mapstruct.Mapper;
import tn.esprit.autoloc.domain.Paiement;

@Mapper(componentModel = "spring")
public interface PaiementMapper {
    PaiementDTO toDto(Paiement paiement);
    Paiement toEntity(PaiementDTO paiementDTO);
    java.util.List<PaiementDTO> toDtoList(java.util.List<Paiement> paiements);
    java.util.List<Paiement> toEntityList(java.util.List<PaiementDTO> dtos);
}
