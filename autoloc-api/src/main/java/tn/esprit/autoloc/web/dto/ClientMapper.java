package tn.esprit.autoloc.web.dto;

import org.mapstruct.Mapper;
import tn.esprit.autoloc.domain.Client;

@Mapper(componentModel = "spring")
public interface ClientMapper {
    ClientDTO toDto(Client client);
    Client toEntity(ClientDTO clientDTO);
    java.util.List<ClientDTO> toDtoList(java.util.List<Client> clients);
    java.util.List<Client> toEntityList(java.util.List<ClientDTO> dtos);
}
