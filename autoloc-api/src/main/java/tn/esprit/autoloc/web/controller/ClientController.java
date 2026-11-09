package tn.esprit.autoloc.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.autoloc.domain.Client;
import tn.esprit.autoloc.service.IClientService;

import java.util.List;

// Chap 6 : question 31 - contrôleur Client
@Tag(name = "Clients", description = "Gestion des clients")
@RestController
@RequestMapping("/clients")
@RequiredArgsConstructor
public class ClientController {
    private final IClientService clientService;

    @Operation(summary = "Lister tous les clients")
    @GetMapping
    public List<Client> findAll() {
        return clientService.findAll();
    }

    @Operation(summary = "Consulter un client par son ID")
    @GetMapping("/{id}")
    public Client findById(@Parameter(description = "Identifiant du client") @PathVariable Long id) {
        return clientService.findById(id);
    }

    @Operation(summary = "Créer un nouveau client")
    @PostMapping
    public Client create(@RequestBody Client client) {
        return clientService.create(client);
    }

    @Operation(summary = "Modifier un client existant")
    @PutMapping("/{id}")
    public Client update(@PathVariable Long id, @RequestBody Client client) {
        return clientService.update(id, client);
    }

    @Operation(summary = "Supprimer un client")
    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        clientService.deleteById(id);
    }
}
