package tn.esprit.autoloc.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.autoloc.domain.Paiement;
import tn.esprit.autoloc.service.IPaiementService;

import java.util.List;

// Chap 6 : question 31 - contrôleur Paiement (lecture seule)
@Tag(name = "Paiements", description = "Gestion des paiements (lecture seule)")
@RestController
@RequestMapping("/paiements")
@RequiredArgsConstructor
public class PaiementController {
    private final IPaiementService paiementService;

    @Operation(summary = "Lister tous les paiements")
    @GetMapping
    public List<Paiement> findAll() {
        return paiementService.findAll();
    }

    @Operation(summary = "Consulter un paiement par son ID")
    @GetMapping("/{id}")
    public Paiement findById(@Parameter(description = "Identifiant du paiement") @PathVariable Long id) {
        return paiementService.findById(id);
    }
}
