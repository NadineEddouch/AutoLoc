package tn.esprit.autoloc.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.autoloc.domain.Paiement;
import tn.esprit.autoloc.service.IPaiementService;
import tn.esprit.autoloc.web.dto.PaiementDTO;
import tn.esprit.autoloc.web.dto.PaiementMapper;

import java.util.List;

@Tag(name = "Paiements", description = "Gestion des paiements (lecture seule)")
@RestController
@RequestMapping("/paiements")
@RequiredArgsConstructor
public class PaiementController {
    private final IPaiementService paiementService;
    private final PaiementMapper paiementMapper;

    @GetMapping
    public List<PaiementDTO> findAll() { return paiementMapper.toDtoList(paiementService.findAll()); }
    @GetMapping("/{id}")
    public PaiementDTO findById(@PathVariable Long id) { return paiementMapper.toDto(paiementService.findById(id)); }
}
