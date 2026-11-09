package tn.esprit.autoloc.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.autoloc.domain.Equipement;
import tn.esprit.autoloc.service.IEquipementService;

import java.util.List;

// Chap 6 : question 31 - contrôleur Equipement
@Tag(name = "Equipements", description = "Gestion des équipements")
@RestController
@RequestMapping("/equipements")
@RequiredArgsConstructor
public class EquipementController {
    private final IEquipementService equipementService;

    @Operation(summary = "Lister tous les équipements")
    @GetMapping
    public List<Equipement> findAll() {
        return equipementService.findAll();
    }

    @Operation(summary = "Consulter un équipement par son ID")
    @GetMapping("/{id}")
    public Equipement findById(@Parameter(description = "Identifiant de l'équipement") @PathVariable Long id) {
        return equipementService.findById(id);
    }

    @Operation(summary = "Créer un nouvel équipement")
    @PostMapping
    public Equipement create(@RequestBody Equipement equipement) {
        return equipementService.create(equipement);
    }

    @Operation(summary = "Modifier un équipement existant")
    @PutMapping("/{id}")
    public Equipement update(@PathVariable Long id, @RequestBody Equipement equipement) {
        return equipementService.update(id, equipement);
    }

    @Operation(summary = "Supprimer un équipement")
    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        equipementService.deleteById(id);
    }
}
