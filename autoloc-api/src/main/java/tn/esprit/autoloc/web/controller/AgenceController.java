package tn.esprit.autoloc.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.autoloc.domain.Agence;
import tn.esprit.autoloc.service.IAgenceService;

import java.util.List;

// Chap 6 : question 8 - création de la classe AgenceController
// Chap 6 : question 9 - annotations @RestController et @RequestMapping
// Chap 6 : question 10 - dépendance sur IAgenceService
// Chap 6 : question 11 - méthode findAll avec @GetMapping
// Chap 6 : question 30 - enrichissement de la documentation avec SpringDoc
@Tag(name = "Agences", description = "Gestion des agences de location")
@RestController
@RequestMapping("/agences")
@RequiredArgsConstructor
public class AgenceController {
    private final IAgenceService agenceService;

    @Operation(summary = "Lister toutes les agences")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Liste des agences trouvée")
    })
    @GetMapping
    public List<Agence> findAll() {
        return agenceService.findAll();
    }

    // Chap 6 : question 19 - consultation d'une agence par ID via le chemin de l'URL
    @Operation(summary = "Consulter une agence par son ID")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Agence trouvée"),
        @ApiResponse(responseCode = "404", description = "Agence introuvable")
    })
    @GetMapping("/{id}")
    public Agence findById(@Parameter(description = "Identifiant de l'agence") @PathVariable Long id) {
        return agenceService.findById(id);
    }

    // Chap 6 : question 21 - API d'ajout d'une agence (POST)
    @Operation(summary = "Créer une nouvelle agence")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Agence créée avec succès"),
        @ApiResponse(responseCode = "400", description = "Données invalides")
    })
    @PostMapping
    public Agence create(@RequestBody Agence agence) {
        return agenceService.create(agence);
    }

    // Chap 6 : question 24 - API de modification d'une agence (PUT)
    @Operation(summary = "Modifier une agence existante")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Agence modifiée avec succès"),
        @ApiResponse(responseCode = "404", description = "Agence introuvable"),
        @ApiResponse(responseCode = "400", description = "Données invalides")
    })
    @PutMapping("/{id}")
    public Agence update(@PathVariable Long id, @RequestBody Agence agence) {
        return agenceService.update(id, agence);
    }

    // Chap 6 : question 25 - API de suppression d'une agence (DELETE)
    @Operation(summary = "Supprimer une agence")
    @ApiResponses({
        @ApiResponse(responseCode = "204", description = "Agence supprimée avec succès"),
        @ApiResponse(responseCode = "404", description = "Agence introuvable")
    })
    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        agenceService.deleteById(id);
    }
}
