package tn.esprit.autoloc.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.autoloc.domain.Client;
import tn.esprit.autoloc.service.IClientService;
import tn.esprit.autoloc.web.dto.ClientDTO;
import tn.esprit.autoloc.web.dto.ClientMapper;

import java.util.List;

// Chap 6 : question 31 - contrôleur Client
// Atelier DTO - Question 9 : Utilisation du mapper pour convertir Entité <-> DTO
@Tag(name = "Clients", description = "Gestion des clients")
@RestController
@RequestMapping("/clients")
@RequiredArgsConstructor
public class ClientController {
    private final IClientService clientService;
    private final ClientMapper clientMapper;

    @Operation(summary = "Lister tous les clients")
    @GetMapping
    public List<ClientDTO> findAll() {
        return clientMapper.toDtoList(clientService.findAll());
    }

    @Operation(summary = "Consulter un client par son ID")
    @GetMapping("/{id}")
    public ClientDTO findById(@Parameter(description = "Identifiant du client") @PathVariable Long id) {
        return clientMapper.toDto(clientService.findById(id));
    }

    @Operation(summary = "Créer un nouveau client")
    @PostMapping
    public ClientDTO create(@RequestBody ClientDTO clientDTO) {
        Client client = clientMapper.toEntity(clientDTO);
        return clientMapper.toDto(clientService.create(client));
    }

    @Operation(summary = "Modifier un client existant")
    @PutMapping("/{id}")
    public ClientDTO update(@PathVariable Long id, @RequestBody ClientDTO clientDTO) {
        Client client = clientMapper.toEntity(clientDTO);
        return clientMapper.toDto(clientService.update(id, client));
    }

    @Operation(summary = "Supprimer un client")
    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        clientService.deleteById(id);
    }
}
