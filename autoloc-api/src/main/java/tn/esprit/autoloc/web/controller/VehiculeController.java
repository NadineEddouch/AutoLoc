package tn.esprit.autoloc.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.autoloc.domain.Vehicule;
import tn.esprit.autoloc.service.IVehiculeService;

import java.util.List;

// Chap 6 : question 31 - contrôleur Vehicule
@Tag(name = "Vehicules", description = "Gestion des véhicules")
@RestController
@RequestMapping("/vehicules")
@RequiredArgsConstructor
public class VehiculeController {
    private final IVehiculeService vehiculeService;

    @Operation(summary = "Lister tous les véhicules")
    @GetMapping
    public List<Vehicule> findAll() {
        return vehiculeService.findAll();
    }

    @Operation(summary = "Consulter un véhicule par son ID")
    @GetMapping("/{id}")
    public Vehicule findById(@Parameter(description = "Identifiant du véhicule") @PathVariable Long id) {
        return vehiculeService.findById(id);
    }

    @Operation(summary = "Créer un nouveau véhicule")
    @PostMapping
    public Vehicule create(@RequestBody Vehicule vehicule) {
        return vehiculeService.create(vehicule);
    }

    @Operation(summary = "Modifier un véhicule existant")
    @PutMapping("/{id}")
    public Vehicule update(@PathVariable Long id, @RequestBody Vehicule vehicule) {
        return vehiculeService.update(id, vehicule);
    }

    @Operation(summary = "Supprimer un véhicule")
    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        vehiculeService.deleteById(id);
    }

    // Chap 9: Question 2 - API pour désaffecter un véhicule de son agence
    @Operation(summary = "Désaffecter un véhicule de son agence")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Véhicule désaffecté avec succès"),
        @ApiResponse(responseCode = "404", description = "Véhicule introuvable")
    })
    @PutMapping("/{id}/desaffecter")
    public void desaffecter(@Parameter(description = "Identifiant du véhicule") @PathVariable Long id) {
        vehiculeService.desaffecter(id);
    }

    // Chap 9: Question 5 - API pour affecter une agence à un véhicule (les deux identifiants dans l'URL)
    @Operation(summary = "Affecter une agence à un véhicule")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Véhicule affecté à l'agence avec succès"),
        @ApiResponse(responseCode = "404", description = "Véhicule ou agence introuvable")
    })
    @PutMapping("/{vehiculeId}/affecter/{agenceId}")
    public void affecterAgence(
            @Parameter(description = "Identifiant du véhicule") @PathVariable Long vehiculeId,
            @Parameter(description = "Identifiant de l'agence") @PathVariable Long agenceId) {
        vehiculeService.affecterAgence(vehiculeId, agenceId);
    }

    // Chap 9: Question 7 - API pour affecter un équipement à un véhicule
    @Operation(summary = "Affecter un équipement à un véhicule")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Équipement affecté au véhicule avec succès"),
        @ApiResponse(responseCode = "404", description = "Véhicule ou équipement introuvable")
    })
    @PutMapping("/{vehiculeId}/affecter-equipement/{equipementId}")
    public void affecterEquipement(
            @Parameter(description = "Identifiant du véhicule") @PathVariable Long vehiculeId,
            @Parameter(description = "Identifiant de l'équipement") @PathVariable Long equipementId) {
        vehiculeService.affecterEquipement(vehiculeId, equipementId);
    }
}
